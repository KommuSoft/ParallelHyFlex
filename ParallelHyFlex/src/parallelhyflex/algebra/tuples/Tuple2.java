package parallelhyflex.algebra.tuples;

/**
 *
 * @author kommusoft
 */
public class Tuple2<TItem1,TItem2> {
    
    private final TItem1 item1;
    private final TItem2 item2;
    
    public Tuple2 (TItem1 item1, TItem2 item2) {
        this.item1 = item1;
        this.item2 = item2;
    }

    /**
     * @return the item1
     */
    public final TItem1 getItem1() {
        return item1;
    }

    /**
     * @return the item2
     */
    public final TItem2 getItem2() {
        return item2;
    }
    
}
