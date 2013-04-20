package parallelhyflex.communication;

/**
 *
 * @author kommusoft
 */
public class MultiThreadedListNode<TElement> {
    
    private TElement element;
    private MultiThreadedListNode<TElement> next = null;
    
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
    public void setNext(MultiThreadedListNode<TElement> next) {
        this.next = next;
    }
    
}
