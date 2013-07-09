package parallelhyflex.problems.fdcsp.problem;

import parallelhyflex.algebra.PinnedFlightWeight;

/**
 *
 * @author kommusoft
 */
public class VariableStore extends PinnedFlightWeight<String,Variable> {
    
    /**
     *
     */
    public VariableStore () {
        super(VariableGenerator.getInstance());
    }
    
}
