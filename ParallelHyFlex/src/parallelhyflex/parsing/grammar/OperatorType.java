package parallelhyflex.parsing.grammar;

/**
 *
 * @author kommusoft
 */
public enum OperatorType {

    BindLeft(1),
    BindRight(2),
    BindBoth(3);
    
    private final int data;
    
    private OperatorType (int data) {
        this.data = data;
    }
    
    public boolean withLeft() {
        return (this.data&1) != 0;
    }
    public boolean withRight() {
        return (this.data&2) != 0;
    }
}
