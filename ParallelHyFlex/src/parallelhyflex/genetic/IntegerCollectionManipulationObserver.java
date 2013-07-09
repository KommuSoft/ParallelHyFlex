package parallelhyflex.genetic;

import java.util.Collection;
import java.util.Iterator;

/**
 *
 * @author kommusoft
 */
public class IntegerCollectionManipulationObserver implements ManipulationObserver, Collection<Integer> {
    
    private final Collection<Integer> collection;
    
    /**
     *
     * @param collection
     */
    public IntegerCollectionManipulationObserver(Collection<Integer> collection) {
        this.collection = collection;
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
    public Iterator<Integer> iterator() {
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
     * @param ts
     * @return
     */
    @Override
    public <T> T[] toArray(T[] ts) {
        return collection.toArray(ts);
    }
    
    /**
     *
     * @param e
     * @return
     */
    @Override
    public boolean add(Integer e) {
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
     * @param clctn
     * @return
     */
    @Override
    public boolean containsAll(Collection<?> clctn) {
        return collection.containsAll(clctn);
    }
    
    /**
     *
     * @param clctn
     * @return
     */
    @Override
    public boolean addAll(Collection<? extends Integer> clctn) {
        return collection.addAll(clctn);
    }
    
    /**
     *
     * @param clctn
     * @return
     */
    @Override
    public boolean removeAll(Collection<?> clctn) {
        return collection.removeAll(clctn);
    }
    
    /**
     *
     * @param clctn
     * @return
     */
    @Override
    public boolean retainAll(Collection<?> clctn) {
        return collection.retainAll(clctn);
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
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        return collection.equals(o);
    }
    
    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        return collection.hashCode();
    }
    
    /**
     *
     * @param index
     * @param value
     */
    @Override
    public void modify(int index, int value) {
        this.collection.add(index);
    }
}
