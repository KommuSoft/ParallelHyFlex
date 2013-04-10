package parallelhyflex.algebra.generators;

import java.util.List;
import parallelhyflex.algebra.Generator;

/**
 *
 * @author kommusoft
 */
public class ListIndexGenerator<TList> implements Generator<Integer,TList> {
    
    private final List<TList> list;
    
    public ListIndexGenerator (List<TList> list) {
        this.list = list;   
    }

    @Override
    public TList generate(Integer variable) {
        return this.list.get(variable);
    }
    
}
