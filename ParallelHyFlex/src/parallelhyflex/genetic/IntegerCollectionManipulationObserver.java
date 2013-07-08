package parallelhyflex.genetic;

import java.util.Collection;
import java.util.Iterator;

public class IntegerCollectionManipulationObserver implements ManipulationObserver, Collection<Integer> {
    
    private final Collection<Integer> collection;
    
    public IntegerCollectionManipulationObserver(Collection<Integer> collection) {
        this.collection = collection;
    }
    
    public int size() {
        return collection.size();
    }
    
    public boolean isEmpty() {
        return collection.isEmpty();
    }
    
    public boolean contains(Object o) {
        return collection.contains(o);
    }
    
    public Iterator<Integer> iterator() {
        return collection.iterator();
    }
    
    public Object[] toArray() {
        return collection.toArray();
    }
    
    public <T> T[] toArray(T[] ts) {
        return collection.toArray(ts);
    }
    
    public boolean add(Integer e) {
        return collection.add(e);
    }
    
    public boolean remove(Object o) {
        return collection.remove(o);
    }
    
    public boolean containsAll(Collection<?> clctn) {
        return collection.containsAll(clctn);
    }
    
    public boolean addAll(Collection<? extends Integer> clctn) {
        return collection.addAll(clctn);
    }
    
    public boolean removeAll(Collection<?> clctn) {
        return collection.removeAll(clctn);
    }
    
    public boolean retainAll(Collection<?> clctn) {
        return collection.retainAll(clctn);
    }
    
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
