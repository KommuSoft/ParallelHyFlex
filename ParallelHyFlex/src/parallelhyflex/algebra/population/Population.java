package parallelhyflex.algebra.population;

import java.util.Collection;
import java.util.Comparator;
import java.util.SortedSet;
import parallelhyflex.algebra.Predicate;
import parallelhyflex.algebra.Procedure;

/**
 *
 * @author kommusoft
 */
public interface Population<TIndividual> extends Collection<TIndividual> {
    
    public Collection<TIndividual> removeWithPredicate (Predicate<TIndividual> predicate);
    public void processAll (Procedure<TIndividual> procedure);
    public SortedSet<TIndividual> getBests (Comparator<? super TIndividual> comparator, int length);
    public SortedSet<TIndividual> getWorsts (Comparator<? super TIndividual> comparator, int length);
    
}
