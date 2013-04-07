package parallelhyflex.memory.stateexchange;

/**
 *
 * @author kommusoft
 */
public interface LocalState {
    
    public StateExchanger getStateExchanger ();
    void setStateExchanger (StateExchanger exchanger);
    public void pushChanges ();
    
}
