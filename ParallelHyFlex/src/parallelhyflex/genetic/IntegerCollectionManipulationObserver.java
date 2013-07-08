package parallelhyflex.genetic;

import java.util.Collection;
import java.util.Iterator;

public class IntegerCollectionManipulationObserver implements ManipulationObserver, Collection<Integer> {
    
    private final Collection<Integer> collection;
    
    public IntegerCollectionManipulationObserver(Collection<Integer> collection) {
        this.collection = collection;
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
    public Iterator<Integer> iterator() {
        return collection.iterator();
    }
    
    @Override
    public Object[] toArray() {
        return collection.toArray();
    }
    
    @Override
    public <T> T[] toArray(T[] ts) {
        return collection.toArray(ts);
    }
    
    @Override
    public boolean add(Integer e) {
        return collection.add(e);
    }
    
    @Override
    public boolean remove(Object o) {
        return collection.remove(o);
    }
    
    @Override
    public boolean containsAll(Collection<?> clctn) {
        return collection.containsAll(clctn);
    }
    
    @Override
    public boolean addAll(Collection<? extends Integer> clctn) {
        return collection.addAll(clctn);
    }
    
    @Override
    public boolean removeAll(Collection<?> clctn) {
        return collection.removeAll(clctn);
    }
    
    @Override
    public boolean retainAll(Collection<?> clctn) {
        return collection.retainAll(clctn);
    }
    
    @Override
    public void clear() {
        collection.clear();
    }
    
    @Override
    public boolean equals(Object o) {
        return collection.equals(o);
    }
    
    @Override
    public int hashCode() {
        return collection.hashCode();
    }
    
    @Override
    public void modify(int index, int value) {
        this.collection.add(index);
    }
}
