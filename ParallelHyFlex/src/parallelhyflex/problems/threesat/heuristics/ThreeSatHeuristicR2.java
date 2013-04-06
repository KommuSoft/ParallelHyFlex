package parallelhyflex.problems.threesat.heuristics;

import java.util.HashSet;
import parallelhyflex.problemdependent.heuristics.RuinRecreateHeuristicBase;
import parallelhyflex.problems.threesat.ClauseUtils;
import parallelhyflex.problems.threesat.problem.ThreeSatProblem;
import parallelhyflex.problems.threesat.solution.ThreeSatSolution;

/**
 * A ruin-recreate heuristic where an entire block of 64 consecutive bits are
 * cleared and set to a new value based on a greedy approach
 *
 * @author kommusoft
 */
public class ThreeSatHeuristicR2 extends RuinRecreateHeuristicBase<ThreeSatSolution, ThreeSatProblem> {

    public ThreeSatHeuristicR2(ThreeSatProblem problem) {
        super(problem);
    }

    @Override
    public void applyHeuristicLocally(ThreeSatSolution from) {
        long[] constraints = this.getProblem().getConstraints();
        int ci = ClauseUtils.getFalseClauseIndex(from,constraints);
        long clause = constraints[ci];
        int[][] infl = this.getProblem().getInfluences();
        int[] infli;
        HashSet<Integer> influencedClauses = new HashSet<>();
        for(int i = 0; i < 3; i++) {
            //TODO: more strict filter (only constraints who are not influenced by secondary variables)
            infli = infl[i];
            for(int j = 0; j < infli.length; j++) {
                influencedClauses.add(infli[j]);
            }
        }
    }
}