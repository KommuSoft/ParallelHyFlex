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

    public ListIndexGenerator(List<TList> list) {
        this.list = list;
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return list.contains(o);
    }

    @Override
    public Iterator<TList> iterator() {
        return list.iterator();
    }

    @Override
    public Object[] toArray() {
        return list.toArray();
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        return list.toArray(ts);
    }

    @Override
    public boolean add(TList e) {
        return list.add(e);
    }

    @Override
    public boolean remove(Object o) {
        return list.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> clctn) {
        return list.containsAll(clctn);
    }

    @Override
    public boolean addAll(Collection<? extends TList> clctn) {
        return list.addAll(clctn);
    }

    @Override
    public boolean addAll(int i, Collection<? extends TList> clctn) {
        return list.addAll(i, clctn);
    }

    @Override
    public boolean removeAll(Collection<?> clctn) {
        return list.removeAll(clctn);
    }

    @Override
    public boolean retainAll(Collection<?> clctn) {
        return list.retainAll(clctn);
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public boolean equals(Object o) {
        return list.equals(o);
    }

    @Override
    public int hashCode() {
        return list.hashCode();
    }

    @Override
    public TList get(int i) {
        return list.get(i);
    }

    @Override
    public TList set(int i, TList e) {
        return list.set(i, e);
    }

    @Override
    public void add(int i, TList e) {
        list.add(i, e);
    }

    @Override
    public TList remove(int i) {
        return list.remove(i);
    }

    @Override
    public int indexOf(Object o) {
        return list.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return list.lastIndexOf(o);
    }

    @Override
    public ListIterator<TList> listIterator() {
        return list.listIterator();
    }

    @Override
    public ListIterator<TList> listIterator(int i) {
        return list.listIterator(i);
    }

    @Override
    public List<TList> subList(int i, int i1) {
        return list.subList(i, i1);
    }

    @Override
    public TList generate(Integer variable) {
        return this.list.get(variable);
    }
}
