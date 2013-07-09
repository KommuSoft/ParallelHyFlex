package parallelhyflex.algebra.collections;

/**
 *
 * @author kommusoft
 */
public class MultiThreadedListNode<TElement> {
    
    private TElement element;
    private MultiThreadedListNode<TElement> next = null, previous = null;
    
    /**
     *
     * @param element
     */
    public MultiThreadedListNode (TElement element) {
        this.element = element;
    }

    /**
     * @return the element
     */
    public TElement getElement() {
        return element;
    }

    /**
     * @param element the element to set
     */
    public void setElement(TElement element) {
        this.element = element;
    }

    /**
     * @return the next
     */
    public MultiThreadedListNode<TElement> getNext() {
        return next;
    }

    /**
     * @param next the next to set
     */
    void setNext(MultiThreadedListNode<TElement> next) {
        this.next = next;
    }

    /**
     * @return the previous
     */
    public MultiThreadedListNode<TElement> getPrevious() {
        return previous;
    }

    /**
     * @param previous the previous to set
     */
    void setPrevious(MultiThreadedListNode<TElement> previous) {
        this.previous = previous;
    }
    
}
