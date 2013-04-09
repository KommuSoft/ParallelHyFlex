package parallelhyflex.algebra;

/**
 *
 * @author kommusoft
 */
public interface TabuableTabuPopulation<TIndividual extends Tabuable> extends TabuPopulation<TIndividual> {
    
    public boolean tabu (TIndividual individual);
    
}
