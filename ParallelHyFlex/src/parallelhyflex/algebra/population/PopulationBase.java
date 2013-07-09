package parallelhyflex.algebra.population;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.SortedSet;
import java.util.TreeSet;
import parallelhyflex.algebra.Predicate;
import parallelhyflex.algebra.Procedure;
import parallelhyflex.algebra.ReversedComparator;
import parallelhyflex.utils.ProbabilityUtils;


/**
 *
 * @author kommusoft
 * @param <TIndividual>
 */
public class PopulationBase<TIndividual> implements Population<TIndividual> {

    private Collection<TIndividual> collection = new HashSet<>();
    
    /**
     *
     * @param predicate
     * @return
     */
    @Override
    public Collection<TIndividual> removeWithPredicate(Predicate<TIndividual> predicate) {
        Iterator<TIndividual> iterator = collection.iterator();
        LinkedList<TIndividual> removed = new LinkedList<>();
        TIndividual ind;
        while(iterator.hasNext()) {
            ind = iterator.next();
            if(predicate.generate(ind)) {
                removed.add(ind);
            }
        }
        return removed;
    }

    /**
     *
     * @return
     */
    @Override
    public int size() {
        return collection.size();
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return collection.isEmpty();
    }

    /**
     *
     * @param o
     * @return
     */
    @Override
    public boolean contains(Object o) {
        return collection.contains(o);
    }

    /**
     *
     * @return
     */
    @Override
    public Iterator<TIndividual> iterator() {
        return collection.iterator();
    }

    /**
     *
     * @return
     */
    @Override
    public Object[] toArray() {
        return collection.toArray();
    }

    /**
     *
     * @param <T>
     * @param a
     * @return
     */
    @Override
    public <T> T[] toArray(T[] a) {
        return collection.toArray(a);
    }

    /**
     *
     * @param e
     * @return
     */
    @Override
    public boolean add(TIndividual e) {
        return collection.add(e);
    }

    /**
     *
     * @param o
     * @return
     */
    @Override
    public boolean remove(Object o) {
        return collection.remove(o);
    }

    /**
     *
     * @param c
     * @return
     */
    @Override
    public boolean containsAll(Collection<?> c) {
        return collection.containsAll(c);
    }

    /**
     *
     * @param c
     * @return
     */
    @Override
    public boolean addAll(Collection<? extends TIndividual> c) {
        return collection.addAll(c);
    }

    /**
     *
     * @param c
     * @return
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        return collection.removeAll(c);
    }

    /**
     *
     * @param c
     * @return
     */
    @Override
    public boolean retainAll(Collection<?> c) {
        return collection.retainAll(c);
    }

    /**
     *
     */
    @Override
    public void clear() {
        collection.clear();
    }

    /**
     *
     * @param procedure
     */
    @Override
    public void processAll(Procedure<TIndividual> procedure) {
        for(TIndividual ind : this.collection) {
            procedure.execute(ind);
        }
    }

    /**
     *
     * @param comparator
     * @param length
     * @return
     */
    @Override
    public SortedSet<TIndividual> getBests(Comparator<? super TIndividual> comparator, int length) {
        SortedSet<TIndividual> sortedset = new TreeSet<>(comparator);
        for(TIndividual ind : this.collection) {
            if(sortedset.size() < length) {
                sortedset.add(ind);
            }
            else if(comparator.compare(ind,sortedset.last()) < 0x00) {
                sortedset.remove(sortedset.last());
                sortedset.add(ind);
            }
        }
        return sortedset;
    }

    /**
     *
     * @param comparator
     * @param length
     * @return
     */
    @Override
    public SortedSet<TIndividual> getWorsts(Comparator<? super TIndividual> comparator, int length) {
        return this.getBests(new ReversedComparator<>(comparator), length);
    }

    /**
     *
     * @return
     */
    @Override
    public TIndividual getRandomIndividual() {
        return ProbabilityUtils.randomElement(this.collection);
    }
    
}
