package parallelhyflex.algebra.collections;

import java.util.Arrays;

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
        this.data[this.pointer] = item;
        this.pointer = (this.pointer+1)%data.length;
    }
    
    public TItem get (int index) {
        return (TItem) this.data[(index+this.pointer)%this.data.length];
    }

    public int size() {
        return data.length;
    }

    @Override
    public String toString() {
        return "StridingList{" + "data=" + Arrays.toString(data) + ", pointer=" + pointer + '}';
    }
    
}
