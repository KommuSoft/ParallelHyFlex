package parallelhyflex.algebra;

import java.util.Collection;

/**
 *
 * @author kommusoft
 */
public interface TabuPopulation<TIndividual> extends Population<TIndividual> {
    
    public boolean tabu (TIndividual individual, int ticks);
    public boolean tabu (Collection<TIndividual> individuals, int ticks);
    public boolean tabu (Predicate<TIndividual> predicate, int ticks);
    public boolean isTabued (TIndividual individual);
    public boolean tabuTick ();
    public int getRemainingTabuTicks (TIndividual individual);
    public boolean untabu (TIndividual individual);
    public int getNumberOfTabuedIndividuals ();
    
}
