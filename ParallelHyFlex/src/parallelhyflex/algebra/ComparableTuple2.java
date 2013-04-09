/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parallelhyflex.algebra;

/**
 *
 * @author kommusoft
 */
public class ComparableTuple2<TItem1 extends Comparable<? super TItem1>,TItem2 extends Comparable<? super TItem2>> extends Tuple2<TItem1,TItem2> implements Comparable<ComparableTuple2<TItem1,TItem2>> {
    
    public ComparableTuple2 (TItem1 item1, TItem2 item2) {
        super(item1,item2);
    }

    @Override
    public int compareTo(ComparableTuple2<TItem1,TItem2> o) {
        if(o == null) {
            return -1;
        }
        else {
            int c = this.getItem1().compareTo(o.getItem1());
            if(c != 0) {
                return c;
            }
            else {
                return this.getItem2().compareTo(o.getItem2());
            }
        }
    }
    
}
