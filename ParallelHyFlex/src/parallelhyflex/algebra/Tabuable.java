package parallelhyflex.algebra;

/**
 *
 * @author kommusoft
 */
public interface Tabuable {
    
    public void willTabu ();
    public void willUntabu ();
    public int getTabuDuration ();
    
}