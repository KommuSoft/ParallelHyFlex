package parallelhyflex.algebra.population;

import java.util.Collection;
import parallelhyflex.algebra.Tabuable;


/**
 *
 * @author kommusoft
 * @param <TIndividual>
 */
public class TabuableTabuPopulationBase<TIndividual extends Tabuable> extends TabuPopulationBase<TIndividual> implements TabuableTabuPopulation<TIndividual> {

    /**
     *
     * @return
     */
    @Override
    public Collection<TIndividual> tabuTick () {
        Collection<TIndividual> result = super.tabuTick();
        for(TIndividual ind : result) {
            ind.willUntabu();
        }
        return result;
    }
    
    /**
     *
     * @param individual
     * @return
     */
    @Override
    public boolean untabu (TIndividual individual) {
        boolean mod = super.untabu(individual);
        if(mod) {
            individual.willUntabu();
        }
        return mod;
    }
    
    /**
     *
     * @param individual
     * @return
     */
    @Override
    public boolean tabu(TIndividual individual) {
        individual.willTabu();
        return this.tabu(individual,individual.getTabuDuration());
    }

    /**
     *
     * @param individuals
     * @return
     */
    @Override
    public boolean tabu(Collection<TIndividual> individuals) {
        boolean mod = false;
        for(TIndividual ind : individuals) {
            mod |= this.tabu(ind);
        }
        return mod;
    }
    
}
