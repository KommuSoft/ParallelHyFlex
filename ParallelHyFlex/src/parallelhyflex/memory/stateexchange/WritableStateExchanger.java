package parallelhyflex.memory.stateexchange;

import parallelhyflex.communication.ReadWriteable;

/**
 *
 * @author kommusoft
 */
public interface WritableStateExchanger<TSingleState extends ReadWriteable & LocalState> extends StateExchanger<TSingleState> {
    
}
