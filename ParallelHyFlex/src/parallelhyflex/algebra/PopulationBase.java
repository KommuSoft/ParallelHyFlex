package parallelhyflex.algebra;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;


public class PopulationBase<TIndividual> implements Population<TIndividual> {

    private Collection<TIndividual> collection = new HashSet<>();
    
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

    @Override
    public int size() {
        return collection.size();
    }

    @Override
    public boolean isEmpty() {
        return collection.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return collection.contains(o);
    }

    @Override
    public Iterator<TIndividual> iterator() {
        return collection.iterator();
    }

    @Override
    public Object[] toArray() {
        return collection.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return collection.toArray(a);
    }

    @Override
    public boolean add(TIndividual e) {
        return collection.add(e);
    }

    @Override
    public boolean remove(Object o) {
        return collection.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return collection.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends TIndividual> c) {
        return collection.addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return collection.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return collection.retainAll(c);
    }

    @Override
    public void clear() {
        collection.clear();
    }

    @Override
    public void processAll(Procedure<TIndividual> procedure) {
        for(TIndividual ind : this.collection) {
            procedure.execute(ind);
        }
    }
    
}
