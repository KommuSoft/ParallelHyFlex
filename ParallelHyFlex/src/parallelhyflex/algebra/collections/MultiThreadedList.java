package parallelhyflex.algebra.collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author kommusoft
 */
public class MultiThreadedList<TElement> implements List<TElement> {

    private int size = 0;
    private MultiThreadedListNode<TElement> first = null, last = null;

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size() < 0;
    }

    @Override
    public boolean contains(Object o) {
        MultiThreadedListNode<TElement> node = this.first;
        while (node != null) {
            if (node.getElement() != null && node.getElement().equals(o)) {
                return true;
            }
            node = node.getNext();
        }
        return false;
    }

    @Override
    public Iterator<TElement> iterator() {
        return new MultiThreadedListIterator<>(this.first);
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean add(TElement e) {
        MultiThreadedListNode<TElement> element = new MultiThreadedListNode<>(e);
        if (this.size() > 0) {
            element.setPrevious(this.last);
            this.last.setNext(element);
            this.last = element;
        } else {
            this.first = element;
            this.last = element;
        }
        this.size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object obj : c) {
            if (!this.contains(obj)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends TElement> c) {
        boolean chang = false;
        for (TElement t : c) {
            chang |= this.add(t);
        }
        return chang;
    }

    @Override
    public boolean addAll(int index, Collection<? extends TElement> c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Removes an element by its node in constant time
     *
     * @param node The node containing the element that should be removed
     * @pre The node must be part of the MultiThreadedList or null, if not the
     * list will become corrupt
     */
    public void removeNode(MultiThreadedListNode<TElement> node) {
        if (node != null) {
            MultiThreadedListNode<TElement> prev = node.getPrevious();
            MultiThreadedListNode<TElement> next = node.getNext();
            if (prev != null) {
                prev.setNext(next);
            } else {
                this.first = next;
            }
            if (next != null) {
                next.setPrevious(prev);
            } else {
                this.last = prev;
            }
            this.size--;
        }
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean ch = false;
        if (!c.isEmpty()) {
            for (MultiThreadedListNode<TElement> elem = this.first, prev = null, nxt; elem != null; elem = nxt) {
                nxt = elem.getNext();
                if (c.contains(elem.getElement())) {
                    ch = true;
                    if (prev != null) {
                        prev.setNext(nxt);
                    } else {
                        this.first = nxt;
                    }
                    if (nxt != null) {
                        nxt.setPrevious(prev);
                    } else {
                        this.last = prev;
                    }
                    size--;
                } else {
                    prev = elem;
                }
            }
        }
        return ch;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return this.removeAll(new OppositeCollection(c));
    }

    @Override
    public void clear() {
        this.size = 0;
        this.first = null;
        this.last = null;
    }

    @Override
    public TElement get(int index) {
        MultiThreadedListNode<TElement> elem = this.getNode(index);
        if (elem != null) {
            return elem.getElement();
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public MultiThreadedListNode<TElement> getNode(int index) {
        MultiThreadedListNode<TElement> elem = this.first;
        for (int i = 0; elem != null && i < index; i++) {
            elem = elem.getNext();
        }
        return elem;
    }

    public MultiThreadedListNode<TElement> getFirstNode() {
        return this.first;
    }

    public MultiThreadedListNode<TElement> getLastNode() {
        return this.last;
    }

    @Override
    public TElement set(int index, TElement element) {
        MultiThreadedListNode<TElement> elem = this.getNode(index);
        if (elem != null) {
            TElement val = elem.getElement();
            elem.setElement(element);
            return val;
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public void add(int index, TElement element) {
        MultiThreadedListNode<TElement> elem = new MultiThreadedListNode<>(element);
        if (index == 0) {
            if (this.size == 0) {
                this.last = elem;
            }
            this.first = elem;
            elem.setNext(this.first);
        } else {
            MultiThreadedListNode<TElement> prev = this.getNode(index - 1);
            elem.setNext(prev.getNext());
            if (index == size) {
                this.last = elem;
            }
            prev.setNext(elem);
        }
        this.size++;
    }

    @Override
    public TElement remove(int index) {
        TElement data = null;
        if (index == 0) {
            if (this.size > 0) {
                if (this.size == 1) {
                    this.last = null;
                }
                data = this.first.getElement();
                this.first = this.first.getNext();
            }
            this.size--;
        } else if (index < size) {
            MultiThreadedListNode<TElement> prev = this.getNode(index - 1);
            if (index == size - 1) {
                this.last = prev;
            }
            prev.setNext(prev.getNext().getNext());
            this.size--;
        }
        return data;
    }

    @Override
    public int indexOf(Object o) {
        MultiThreadedListNode<TElement> node = this.first;
        int index = 0;
        while (node != null) {
            if (node.getElement() != null && node.getElement().equals(o)) {
                return index;
            }
            node = node.getNext();
            index++;
        }
        return -0x01;
    }

    @Override
    public int lastIndexOf(Object o) {
        MultiThreadedListNode<TElement> node = this.first;
        int index = 0;
        int lastSeen = -0x01;
        while (node != null) {
            if (node.getElement() != null && node.getElement().equals(o)) {
                lastSeen = index;
            }
            node = node.getNext();
            index++;
        }
        return lastSeen;
    }

    @Override
    public ListIterator<TElement> listIterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ListIterator<TElement> listIterator(int index) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TElement> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        for (TElement t : this) {
            if (t == null) {
                sb.append("null");
            } else {
                sb.append(t.toString());
            }
            sb.append(" ");
        }
        sb.append(']');
        return sb.toString();
    }

    private class MultiThreadedListIterator<T> implements Iterator<T> {

        private MultiThreadedListNode<T> current;
        private MultiThreadedListNode<T> prev;

        MultiThreadedListIterator(MultiThreadedListNode<T> current) {
            this.current = new MultiThreadedListNode(null);
            this.current.setNext(current);
        }

        @Override
        public boolean hasNext() {
            return this.current.getNext() != null;
        }

        @Override
        public T next() {
            this.current = this.current.getNext();
            return this.current.getElement();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
}
