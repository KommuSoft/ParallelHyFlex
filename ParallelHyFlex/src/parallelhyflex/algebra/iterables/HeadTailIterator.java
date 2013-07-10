package parallelhyflex.algebra.collections.iterables;

import java.util.Iterator;
import java.util.logging.Logger;

/**
 *
 * @param <T>
 * @author kommusoft
 */
public class HeadTailIterator<T> implements Iterable<T> {

    private static final Logger LOG = Logger.getLogger(HeadTailIterator.class.getName());
    private Iterator<? extends T> iterator;
    private T head;
    private HeadTailIterator<T> tail;

    /**
     *
     * @param iterator
     */
    public HeadTailIterator(Iterator<? extends T> iterator) {
        this.iterator = iterator;
    }

    /**
     *
     * @param iterable
     */
    public HeadTailIterator(Iterable<? extends T> iterable) {
        this(iterable.iterator());
    }

    /**
     *
     * @return
     */
    public T head() {
        expand();
        return head;
    }

    /**
     *
     * @return
     */
    public HeadTailIterator<T> tail() {
        expand();
        return tail;
    }

    private void expand() {
        if (!this.isExpanded()) {
            head = iterator.next();
            if (iterator.hasNext()) {
                tail = new HeadTailIterator<>(iterator);
            }
            iterator = null;
        }
    }

    /**
     *
     * @return
     */
    public boolean hasNext() {
        if (this.iterator != null) {
            return this.iterator.hasNext();
        } else {
            return tail != null;
        }
    }

    private boolean isExpanded() {
        return (this.iterator == null);
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        if(this.isExpanded()) {
            return String.format("%s|%s",this.head,this.tail);
        }
        else {
            return "<>";
        }
    }

    /**
     *
     */
    public void skip() {
        this.expand();
        if (this.tail != null) {
            if (this.tail.isExpanded()) {
                this.head = tail.head;
                this.tail = tail.tail;
            } else {
                this.iterator = tail.iterator;
                this.head = null;
                this.tail = null;
            }

        } else {
            this.head = null;
            this.tail = null;
            this.iterator = null;
        }
    }

    /**
     *
     * @return
     */
    @Override
    public Iterator<T> iterator() {
        return new HeadTailIteratorIterator(this);
    }

    private class HeadTailIteratorIterator implements Iterator<T> {

        private HeadTailIterator<T> iterator;

        private HeadTailIteratorIterator(HeadTailIterator<T> iterator) {
            this.iterator = iterator;
        }

        @Override
        public boolean hasNext() {
            return (this.iterator != null) && (this.iterator.head() != null);
        }

        @Override
        public T next() {
            T val = null;
            if (this.iterator != null) {
                val = this.iterator.head();
                this.iterator = this.iterator.tail();
            }
            return val;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Removing items is not supported in a Head-Tail Construction");
        }
    }
}
