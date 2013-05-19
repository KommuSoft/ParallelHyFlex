package parallelhyflex.memory.stateexchange;

import parallelhyflex.algebra.collections.ArrayIterator;
import parallelhyflex.communication.AsynchronousGatherAll;
import parallelhyflex.communication.Communication;

/**
 *
 * @author kommusoft
 */
public class StateExchanger extends AsynchronousGatherAll<byte[]> {
    
    public static final int SendTag = 1;
    private final ExchangeState[] states;
    
    public StateExchanger () {
        super(SendTag);
        int s = Communication.getCommunication().getSize();
        this.states = new ExchangeState[s];
        for(int i = 0; i < s; i++) {
            this.states[i] = new ExchangeState(0);
        }
    }
    
    public ExchangeState getLocalState () {
        return states[Communication.getCommunication().getRank()];
    }
    
    public ExchangeState getState (int rank) {
        return this.states[rank];
    }

    public ArrayIterator<ExchangeState> stateIterator() {
        return new ArrayIterator<>(this.states);
    }
    
}
