package parallelhyflex.algebra.population;

import java.util.Collection;
import parallelhyflex.algebra.Tabuable;

/**
 *
 * @author kommusoft
 */
public interface TabuableTabuPopulation<TIndividual extends Tabuable> extends TabuPopulation<TIndividual> {
    
    public boolean tabu (TIndividual individual);
    public boolean tabu (Collection<TIndividual> individuals);
    
}
