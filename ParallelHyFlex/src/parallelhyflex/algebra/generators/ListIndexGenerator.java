package parallelhyflex.algebra.generators;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import parallelhyflex.algebra.Generator;

/**
 *
 * @param <TList>
 * @author kommusoft
 */
public class ListIndexGenerator<TList> implements Generator<Integer, TList>, List<TList> {

    private final List<TList> list;

    /**
     *
     * @param list
     */
    public ListIndexGenerator(List<TList> list) {
        this.list = list;
    }

    /**
     *
     * @return
     */
    @Override
    public int size() {
        return list.size();
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     *
     * @param o
     * @return
     */
    @Override
    public boolean contains(Object o) {
        return list.contains(o);
    }

    /**
     *
     * @return
     */
    @Override
    public Iterator<TList> iterator() {
        return list.iterator();
    }

    /**
     *
     * @return
     */
    @Override
    public Object[] toArray() {
        return list.toArray();
    }

    /**
     *
     * @param <T>
     * @param ts
     * @return
     */
    @Override
    public <T> T[] toArray(T[] ts) {
        return list.toArray(ts);
    }

    /**
     *
     * @param e
     * @return
     */
    @Override
    public boolean add(TList e) {
        return list.add(e);
    }

    /**
     *
     * @param o
     * @return
     */
    @Override
    public boolean remove(Object o) {
        return list.remove(o);
    }

    /**
     *
     * @param clctn
     * @return
     */
    @Override
    public boolean containsAll(Collection<?> clctn) {
        return list.containsAll(clctn);
    }

    /**
     *
     * @param clctn
     * @return
     */
    @Override
    public boolean addAll(Collection<? extends TList> clctn) {
        return list.addAll(clctn);
    }

    /**
     *
     * @param i
     * @param clctn
     * @return
     */
    @Override
    public boolean addAll(int i, Collection<? extends TList> clctn) {
        return list.addAll(i, clctn);
    }

    /**
     *
     * @param clctn
     * @return
     */
    @Override
    public boolean removeAll(Collection<?> clctn) {
        return list.removeAll(clctn);
    }

    /**
     *
     * @param clctn
     * @return
     */
    @Override
    public boolean retainAll(Collection<?> clctn) {
        return list.retainAll(clctn);
    }

    /**
     *
     */
    @Override
    public void clear() {
        list.clear();
    }

    /**
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        return list.equals(o);
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        return list.hashCode();
    }

    /**
     *
     * @param i
     * @return
     */
    @Override
    public TList get(int i) {
        return list.get(i);
    }

    /**
     *
     * @param i
     * @param e
     * @return
     */
    @Override
    public TList set(int i, TList e) {
        return list.set(i, e);
    }

    /**
     *
     * @param i
     * @param e
     */
    @Override
    public void add(int i, TList e) {
        list.add(i, e);
    }

    /**
     *
     * @param i
     * @return
     */
    @Override
    public TList remove(int i) {
        return list.remove(i);
    }

    /**
     *
     * @param o
     * @return
     */
    @Override
    public int indexOf(Object o) {
        return list.indexOf(o);
    }

    /**
     *
     * @param o
     * @return
     */
    @Override
    public int lastIndexOf(Object o) {
        return list.lastIndexOf(o);
    }

    /**
     *
     * @return
     */
    @Override
    public ListIterator<TList> listIterator() {
        return list.listIterator();
    }

    /**
     *
     * @param i
     * @return
     */
    @Override
    public ListIterator<TList> listIterator(int i) {
        return list.listIterator(i);
    }

    /**
     *
     * @param i
     * @param i1
     * @return
     */
    @Override
    public List<TList> subList(int i, int i1) {
        return list.subList(i, i1);
    }

    /**
     *
     * @param variable
     * @return
     */
    @Override
    public TList generate(Integer variable) {
        return this.list.get(variable);
    }
}
