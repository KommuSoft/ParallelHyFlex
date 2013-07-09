package parallelhyflex.algebra.tuples;

/**
 *
 * @author kommusoft
 */
public final class Tuple3<TItem1,TItem2,TItem3> {
    
    private final TItem1 item1;
    private final TItem2 item2;
    private final TItem3 item3;
    
    /**
     *
     * @param item1
     * @param item2
     * @param item3
     */
    public Tuple3 (TItem1 item1, TItem2 item2, TItem3 item3) {
        this.item1 = item1;
        this.item2 = item2;
        this.item3 = item3;
    }

    /**
     * @return the item1
     */
    public TItem1 getItem1() {
        return item1;
    }

    /**
     * @return the item2
     */
    public TItem2 getItem2() {
        return item2;
    }
    
    /**
     * @return the item3
     */
    public TItem3 getItem3() {
        return item3;
    }
    
}