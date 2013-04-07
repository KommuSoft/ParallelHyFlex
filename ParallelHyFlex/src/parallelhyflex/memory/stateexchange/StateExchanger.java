package parallelhyflex.memory.stateexchange;

import parallelhyflex.algebra.Observer0;

/**
 *
 * @author kommusoft
 */
public interface StateExchanger<TSingleState extends LocalState> extends Observer0 {
    
    public TSingleState getStateOf (int machineId);
    public TSingleState getLocalState ();
    
}
