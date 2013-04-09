package parallelhyflex.algebra;

import java.util.Collection;

/**
 *
 * @author kommusoft
 */
public interface Population<TIndividual> extends Collection<TIndividual> {
    
    public Collection<TIndividual> removeWithPredicate (Predicate<TIndividual> predicate);
    public void processAll (Procedure<TIndividual> procedure);
    
}
