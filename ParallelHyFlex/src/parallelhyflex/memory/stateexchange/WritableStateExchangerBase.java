package parallelhyflex.memory.stateexchange;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import mpi.MPI;
import parallelhyflex.algebra.SpecificCloneable;
import parallelhyflex.communication.Communication;
import parallelhyflex.communication.PacketReceiver;
import parallelhyflex.communication.ReadWriteable;
import parallelhyflex.communication.SendTagged;

/**
 *
 * @author kommusoft
 */
public class WritableStateExchangerBase<TSingleState extends LocalState & ReadWriteable & SpecificCloneable<TSingleState>> implements WritableStateExchanger, SendTagged, PacketReceiver {
    
    public static final int SendTag = 0x01;
    private final Object[] states;
    
    public WritableStateExchangerBase (TSingleState singleState) {
        int n = Communication.getCommunication().getSize();
        int r = Communication.getCommunication().getRank();
        states = new Object[n];
        TSingleState tmp;
        for(int i = 0; i < r; i++) {
            tmp = singleState.cloneSpecific();
            states[i] = tmp;
            tmp.setStateExchanger(this);
        }
        states[r] = singleState;
        singleState.setStateExchanger(this);
        for(int i = r+1; i < n; i++) {
            tmp = singleState.cloneSpecific();
            states[i] = tmp;
            tmp.setStateExchanger(this);
        }
    }

    @Override
    public TSingleState getStateOf(int machineId) {
        return (TSingleState) states[machineId];
    }

    @Override
    public TSingleState getLocalState() {
        return (TSingleState) states[Communication.getCommunication().getRank()];
    }
    
    private Object[] generatePacket () throws IOException {
        Object[] data;
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream(); DataOutputStream dos = new DataOutputStream(baos)) {
            this.getLocalState().write(dos);
            data = new Object[] {baos.toByteArray()};
        }
        return data;
    }

    @Override
    public void notifyObserver() {
        try {
            Communication.nbB(this.generatePacket(), 0, 1, MPI.OBJECT, SendTag);
        } catch (IOException ex) {
            Communication.log(ex);
        }
    }

    @Override
    public int getSendTag() {
        return SendTag;
    }

    @Override
    public int[] getPacketTags() {
        return new int[] {SendTag};
    }

    @Override
    public void receivePacket(int from, int tag, Object data) throws Exception {
        Object[] packet = (Object[]) data;
        try (ByteArrayInputStream baos = new ByteArrayInputStream((byte[]) packet[0x00]); DataInputStream dis = new DataInputStream(baos)) {
            this.getStateOf(from).read(dis);
        }
    }
    
}
