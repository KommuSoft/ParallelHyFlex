package parallelhyflex.algebra.generators;

import parallelhyflex.algebra.Generator;

/**
 *
 * @author kommusoft
 */
public class IntegerListIndexGenerator implements Generator<Integer,Integer> {
    
    private final int[] array;
    
    public IntegerListIndexGenerator (int[] array) {
        this.array = array;
    }

    @Override
    public Integer generate(Integer variable) {
        return array[variable];
    }
    
}
