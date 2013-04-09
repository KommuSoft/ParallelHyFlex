package parallelhyflex.algebra;

/**
 *
 * @author kommusoft
 */
public interface TabuPopulation<TIndividual> extends Population<TIndividual> {
    
    public boolean tabu (TIndividual individual, int ticks);
    public boolean tabu (Predicate<TIndividual> predicate, int ticks);
    public boolean isTabued (TIndividual individual);
    public boolean tabuTick ();
    public int getRemainingTabuTicks (TIndividual individual);
    public boolean untabu (TIndividual individual);
    
}
