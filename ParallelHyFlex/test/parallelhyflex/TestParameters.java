package parallelhyflex;

/**
 *
 * @author kommusoft
 */
public class TestParameters {
    
    /**
     *
     */
    public static final int LOOP_PARAMETER = 80_000_000;
    /**
     *
     */
    public static final int LOOP2_PARAMETER = 10_000;
    /**
     *
     */
    public static final int NUMBER_OF_VARIABLES = 100_003;
    /**
     *
     */
    public static final int NUMBER_OF_CLAUSES = 42*NUMBER_OF_VARIABLES/100;
    /**
     *
     */
    public static final int DOMAIN_LOW = -19;
    /**
     *
     */
    public static final int DOMAIN_HIGH = 79;
    /**
     *
     */
    public static final double TOLERANCE = 10e-9;
    
    private TestParameters () {}
    
}
