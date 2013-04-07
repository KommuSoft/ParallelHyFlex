package parallelhyflex.memory.stateexchange;

import parallelhyflex.algebra.SpecificCloneable;
import parallelhyflex.communication.Communication;
import parallelhyflex.communication.ReadWriteable;

/**
 *
 * @author kommusoft
 */
public class WritableStateExchangerBase<TSingleState extends LocalState & ReadWriteable & SpecificCloneable<TSingleState>> implements WritableStateExchanger {
    
    private final Object[] states;
    
    public WritableStateExchangerBase (TSingleState singleState) {
        int n = Communication.getCommunication().getSize();
        int r = Communication.getCommunication().getRank();
        states = new Object[n];
        for(int i = 0; i < r; i++) {
            states[i] = singleState.cloneSpecific();
        }
        states[r] = singleState;
        for(int i = r+1; i < n; i++) {
            states[i] = singleState.cloneSpecific();
        }
    }

    @Override
    public LocalState getStateOf(int machineId) {
        return (LocalState) states[machineId];
    }

    @Override
    public LocalState getLocalState() {
        return (LocalState) states[Communication.getCommunication().getRank()];
    }

    @Override
    public void notifyObserver() {
        //push state
    }
    
}
