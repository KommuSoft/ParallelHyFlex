package parallelhyflex.problems.fdcsp.problem;

import parallelhyflex.algebra.PinnedFleightWeight;

/**
 *
 * @author kommusoft
 */
public class VariableStore extends PinnedFleightWeight<String,Variable> {
    
    public VariableStore () {
        super(VariableGenerator.getInstance());
    }
    
}
