/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parallelhyflex.problems.threesat.heuristic;

import parallelhyflex.problems.heuristics.ProblemHeuristicTestBase;
import parallelhyflex.problems.heuristics.TestHeuristicEvaluationStrategy;
import parallelhyflex.problems.threesat.ClauseUtils;
import parallelhyflex.problems.threesat.problem.ThreeSatProblem;
import parallelhyflex.problems.threesat.problem.ThreeSatProblemGenerator;
import parallelhyflex.problems.threesat.solution.ThreeSatSolution;
import parallelhyflex.problems.threesat.solution.ThreeSatSolutionGenerator;

/**
 *
 * @author kommusoft
 */
public class ThreeSatEvaluationStrategy implements TestHeuristicEvaluationStrategy<ThreeSatSolutionGenerator,ThreeSatProblem,ThreeSatProblemGenerator,ThreeSatSolution> {
    

    @Override
    public double[] calculateApproximatedEvaluations(ProblemHeuristicTestBase<ThreeSatSolutionGenerator, ThreeSatProblem, ThreeSatProblemGenerator, ThreeSatSolution> heuristicTestbase) {
        return new double[] {heuristicTestbase.getTss().getConflictingClauses()};
    }

    @Override
    public double[] calculateRealEvaluations(ProblemHeuristicTestBase<ThreeSatSolutionGenerator, ThreeSatProblem, ThreeSatProblemGenerator, ThreeSatSolution> heuristicTestbase) {
        return new double[] {ClauseUtils.getNumberOfFailedClauses(heuristicTestbase.getTss().getCompactBitArray(), heuristicTestbase.getTsp().getClauses())};
    }
    
}
