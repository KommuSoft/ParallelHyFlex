package parallelhyflex.algebra;

/**
 *
 * @author kommusoft
 */
public interface TabuPopulation<TIndividual> extends Population<TIndividual> {
    
    public void tabu (TIndividual individual, int ticks);
    public boolean isTabued (TIndividual individual);
    public void tabuTick ();
    public int getRemainingTabuTicks (TIndividual individual);
    public void untabu (TIndividual individual);
    
}
