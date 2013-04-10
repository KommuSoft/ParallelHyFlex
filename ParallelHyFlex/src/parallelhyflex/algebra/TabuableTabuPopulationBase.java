/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parallelhyflex.algebra;

import java.util.Collection;


public class TabuableTabuPopulationBase<TIndividual extends Tabuable> extends TabuPopulationBase<TIndividual> implements TabuableTabuPopulation<TIndividual> {

    @Override
    public boolean tabu(TIndividual individual) {
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
