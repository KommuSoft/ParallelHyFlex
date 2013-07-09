package parallelhyflex.algebra;

/**
 *
 * @author kommusoft
 */
public class Tuple2<TValue1, TValue2> {

    private final TValue1 value1;
    private final TValue2 value2;

    public Tuple2(TValue1 value1, TValue2 value2) {
        this.value1 = value1;
        this.value2 = value2;
    }

    /**
     * @return the key
     */
    public TValue1 getValue1() {
        return value1;
    }

    /**
     * @return the value
     */
    public TValue2 getValue2() {
        return value2;
    }
}
