package parallelhyflex.problems.threesat;

import parallelhyflex.HeuristicType;
import parallelhyflex.problemdependent.HeuristicBase;
import parallelhyflex.utils.Utils;

/**
 *
 * @author kommusoft
 */
public class ThreeSatHeuristicM1 extends HeuristicBase<ThreeSatSolution,ThreeSatProblem> {
    //swaps a random variable
    
    public ThreeSatHeuristicM1 (ThreeSatProblem problem) {
        super(problem,HeuristicType.Mutation);
    }

    @Override
    public void applyHeuristicLocally(ThreeSatSolution from) {
        do {
            int i = Utils.StaticRandom.nextInt(from.getLength());
            from.swap(i);
        }
        while(Utils.StaticRandom.nextDouble() < this.getIntensityOfMutation());
    }
    
}
