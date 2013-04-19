package parallelhyflex.algebra.population;

import java.util.Collection;
import parallelhyflex.algebra.Tabuable;


public class TabuableTabuPopulationBase<TIndividual extends Tabuable> extends TabuPopulationBase<TIndividual> implements TabuableTabuPopulation<TIndividual> {

    @Override
    public Collection<TIndividual> tabuTick () {
        Collection<TIndividual> result = super.tabuTick();
        for(TIndividual ind : result) {
            ind.willUntabu();
        }
        return result;
    }
    
    @Override
    public boolean untabu (TIndividual individual) {
        boolean mod = super.untabu(individual);
        if(mod) {
            individual.willUntabu();
        }
        return mod;
    }
    
    @Override
    public boolean tabu(TIndividual individual) {
        individual.willTabu();
        return this.tabu(individual,individual.getTabuDuration());
    }

    @Override
    public boolean tabu(Collection<TIndividual> individuals) {
        boolean mod = false;
        for(TIndividual ind : individuals) {
            mod |= this.tabu(ind);
        }
        return mod;
    }
    
}
