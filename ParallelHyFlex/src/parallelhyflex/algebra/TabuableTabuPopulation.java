package parallelhyflex.algebra;

import java.util.Collection;

/**
 *
 * @author kommusoft
 */
public interface TabuableTabuPopulation<TIndividual extends Tabuable> extends TabuPopulation<TIndividual> {
    
    public boolean tabu (TIndividual individual);
    public boolean tabu (Collection<TIndividual> individuals);
    
}
