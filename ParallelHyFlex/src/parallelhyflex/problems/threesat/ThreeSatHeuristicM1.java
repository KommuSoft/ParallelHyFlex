package parallelhyflex.problems.threesat;

import parallelhyflex.Heuristic;
import parallelhyflex.HeuristicType;
import parallelhyflex.Problem;
import parallelhyflex.utils.Utils;

/**
 *
 * @author kommusoft
 */
public class ThreeSatHeuristicM1 extends Heuristic<ThreeSatSolution> {
    //swaps a random variable
    
    public ThreeSatHeuristicM1 (Problem<ThreeSatSolution> problem) {
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
