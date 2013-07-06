package parallelhyflex.algebra.generators;

import parallelhyflex.algebra.Generator;

/**
 *
 * @author kommusoft
 */
public class IntegerArrayIndexGenerator implements Generator<Integer,Integer> {
    
    private final int[] array;
    
    public IntegerArrayIndexGenerator (int[] array) {
        this.array = array;
    }

    @Override
    public Integer generate(Integer variable) {
        return array[variable];
    }
    
}
