package parallelhyflex.algebra.collections;

/**
 *
 * @author kommusoft
 */
public class StridingList<TItem> {
    
    private Object[] data;
    private int pointer;
    
    public StridingList (int length) {
        this.data = new Object[length];
        this.pointer = 0;
    }
    
    public void add (TItem item) {
        this.data[this.pointer++] = data;
    }
    
    public TItem get (int index) {
        return (TItem) this.data[(index+this.pointer)%this.data.length];
    }

    public int size() {
        return data.length;
    }
    
}
