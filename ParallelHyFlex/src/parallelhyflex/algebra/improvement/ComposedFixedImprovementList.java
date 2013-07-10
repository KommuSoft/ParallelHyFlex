package parallelhyflex.algebra.improvement;

import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.logging.Logger;
import parallelhyflex.algebra.collections.iterables.ArrayIterable;
import parallelhyflex.algebra.collections.iterables.HeadTailIterator;
import parallelhyflex.algebra.collections.iterables.HeadTailIteratorComparator;
import parallelhyflex.utils.Utils;

/**
 *
 * @param <T>
 * @author kommusoft
 */
public class ComposedFixedImprovementList<T> implements FixedImprovementList<T> {

    private static final Logger LOG = Logger.getLogger(ComposedFixedImprovementList.class.getName());
    private final int minimumLocalLength;
    private final Comparator<T> comparator;
    private final HeadTailIteratorComparator<T> iteratorComparator;
    private final FixedImprovementList<T> localList;
    private final Iterable<? extends FixedImprovementList<T>> foreignLists;

    /**
     *
     * @param comparator
     * @param localList
     * @param minimumLocalLength
     * @param foreignLists
     */
    public ComposedFixedImprovementList(Comparator<T> comparator, FixedImprovementList<T> localList, int minimumLocalLength, Iterable<? extends FixedImprovementList<T>> foreignLists) {
        this.comparator = comparator;
        this.iteratorComparator = new HeadTailIteratorComparator<>(comparator);
        this.localList = localList;
        this.minimumLocalLength = minimumLocalLength;
        this.foreignLists = foreignLists;
    }

    /**
     *
     * @param comparator
     * @param localLength
     * @param localInit
     * @param minimumLocalLength
     * @param foreignLists
     */
    public ComposedFixedImprovementList(Comparator<T> comparator, int localLength, T localInit, int minimumLocalLength, Iterable<? extends FixedImprovementList<T>> foreignLists) {
        this(comparator, new SingleFixedImprovementList<>(localLength, localInit), minimumLocalLength, foreignLists);
    }

    /**
     *
     * @param comparator
     * @param localList
     * @param minimumLocalLength
     * @param foreigns
     */
    public ComposedFixedImprovementList(Comparator<T> comparator, FixedImprovementList<T> localList, int minimumLocalLength, FixedImprovementList<T>... foreigns) {
        this(comparator, localList, minimumLocalLength, new ArrayIterable<>(foreigns));
    }

    /**
     *
     * @param comparator
     * @param localLength
     * @param localInit
     * @param minimumLocalLength
     * @param foreigns
     */
    public ComposedFixedImprovementList(Comparator<T> comparator, int localLength, T localInit, int minimumLocalLength, FixedImprovementList<T>... foreigns) {
        this(comparator, localLength, localInit, minimumLocalLength, new ArrayIterable<>(foreigns));
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{ ");
        sb.append(this.localList);
        sb.append(" ");
        for (FixedImprovementList<T> fil : this.foreignLists) {
            sb.append(fil);
            sb.append(' ');
        }
        sb.append("}");
        return sb.toString();
    }

    /**
     *
     * @param improvement
     */
    @Override
    public void addImprovement(T improvement) {
        localList.addImprovement(improvement);
    }

    /**
     *
     * @param improvements
     */
    @Override
    public void addImprovements(T... improvements) {
        localList.addImprovements(improvements);
    }

    /**
     *
     * @param initvalue
     */
    @Override
    public void reset(T initvalue) {
        localList.reset(initvalue);
    }

    /**
     *
     * @param generations
     * @return
     */
    @Override
    public T getHistoryElement(int generations) {
        return Utils.iThOrNull(this, generations);
    }

    /**
     *
     * @return
     */
    @Override
    public T getTop() {
        T top = this.localList.getTop();
        T bot;
        if (this.minimumLocalLength < this.size() && this.foreignLists != null) {
            for (FixedImprovementList<T> other : this.foreignLists) {
                if (other != null) {
                    bot = other.getTop();
                    if (this.comparator.compare(bot, top) < 0x00) {
                        top = bot;
                    }
                }
            }
        }
        return top;
    }

    /**
     *
     * @return
     */
    @Override
    public int size() {
        return this.localList.size();
    }

    /**
     *
     * @return
     */
    @Override
    public Iterator<T> iterator() {
        return new ComposedFixedImprovementListIterator();
    }

    private class ComposedFixedImprovementListIterator implements Iterator<T> {

        private final PriorityQueue<HeadTailIterator<T>> pq = new PriorityQueue<>(4, iteratorComparator);
        private int index = 0;
        private int slack = size() - minimumLocalLength;
        private final HeadTailIterator<T> localht = new HeadTailIterator<>(localList);

        private ComposedFixedImprovementListIterator() {
            pq.add(localht);
            for (FixedImprovementList<T> list : foreignLists) {
                if (list != null) {
                    pq.add(new HeadTailIterator<>(list));
                }
            }

        }

        @Override
        public boolean hasNext() {
            return index < size();
        }

        @Override
        public T next() {
            index++;
            T ret;
            if (slack >= 0x00) {
                HeadTailIterator<T> val = pq.poll();
                ret = val.head();
                if (val.hasNext()) {
                    val.skip();
                    pq.add(val);
                }
                slack--;
                if (slack < 0x00) {
                    this.pq.clear();
                }
            } else {
                ret = localht.head();
                localht.skip();
            }
            return ret;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not supported for FixedImprovementLists.");
        }
    }
}
