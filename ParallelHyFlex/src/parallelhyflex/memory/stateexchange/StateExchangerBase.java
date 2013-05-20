package parallelhyflex.memory.stateexchange;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import parallelhyflex.algebra.collections.ArrayIterator;
import parallelhyflex.communication.AsynchronousGatherAll;
import parallelhyflex.communication.Communication;
import parallelhyflex.utils.CompactBitArray;

/**
 *
 * @author kommusoft
 */
public class StateExchangerBase extends AsynchronousGatherAll<byte[]> implements StateExchanger {

    public static final int SendTag = 1;
    private final ExchangeState[] states;
    private final CompactBitArray synced = new CompactBitArray(Communication.getCommunication().getSize());
    private boolean active = false;

    public StateExchangerBase() {
        super(SendTag);
        int s = Communication.getCommunication().getSize();
        int r = Communication.getCommunication().getRank();
        this.states = new ExchangeState[s];
        for (int i = 0; i < s; i++) {
            this.states[i] = new ExchangeState();
        }
        synced.set(r, true);
    }

    @Override
    public void synchronizeState() throws IOException {
        if (!this.isActive()) {
            int s = Communication.getCommunication().getSize();
            this.send(this.getLocalState().writePacket());
            int r = Communication.getCommunication().getRank();
            synced.resetRange(0, s - 1);
            synced.set(r, true);
        }
    }

    @Override
    public ExchangeState getLocalState() {
        return states[Communication.getCommunication().getRank()];
    }

    @Override
    public ExchangeState getState(int rank) {
        loadState(rank);
        return this.states[rank];
    }

    @Override
    public void send(byte[] value) {
        this.active = true;
        super.send(value);
    }

    @Override
    public void receivePacket(int from, int tag, Object data) throws Exception {
        super.receivePacket(from, tag, data);
        if (this.isReady()) {
            int s = Communication.getCommunication().getSize();
            for (int i = 0; i < s; i++) {
                this.loadState(i);
            }
            this.reset();
            this.active = false;
        }
    }

    @Override
    public ArrayIterator<ExchangeState> stateIterator() {
        return new ArrayIterator<>(this.states);
    }

    public boolean isActive() {
        return this.active;
    }

    private void loadState(int rank) {
        if (!this.synced.get(rank) && this.available(rank)) {
            try {
                this.states[rank].readPacket(this.getData(rank));
                this.synced.set(rank, true);
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(StateExchangerBase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
