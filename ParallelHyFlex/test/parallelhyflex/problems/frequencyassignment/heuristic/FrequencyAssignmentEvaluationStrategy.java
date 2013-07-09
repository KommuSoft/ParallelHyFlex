package parallelhyflex.problems.frequencyassignment.heuristic;

import parallelhyflex.problems.frequencyassignment.problem.FrequencyAssignmentObjectiveFunction1;
import parallelhyflex.problems.frequencyassignment.problem.FrequencyAssignmentProblem;
import parallelhyflex.problems.frequencyassignment.problem.FrequencyAssignmentProblemGenerator;
import parallelhyflex.problems.frequencyassignment.solution.FrequencyAssignmentSolution;
import parallelhyflex.problems.frequencyassignment.solution.FrequencyAssignmentSolutionGenerator;
import parallelhyflex.problems.heuristics.ProblemHeuristicTestBase;
import parallelhyflex.problems.heuristics.TestHeuristicEvaluationStrategy;

/**
 *
 * @author kommusoft
 */
class FrequencyAssignmentEvaluationStrategy implements TestHeuristicEvaluationStrategy<FrequencyAssignmentSolutionGenerator, FrequencyAssignmentProblem, FrequencyAssignmentProblemGenerator, FrequencyAssignmentSolution> {

    FrequencyAssignmentEvaluationStrategy() {
    }

    @Override
    public double[] calculateApproximatedEvaluations(ProblemHeuristicTestBase<FrequencyAssignmentSolutionGenerator, FrequencyAssignmentProblem, FrequencyAssignmentProblemGenerator, FrequencyAssignmentSolution> heuristicTestbase) {
        return new double[]{heuristicTestbase.getTss().getEvaluation(), heuristicTestbase.getTss().getnConflicts(), heuristicTestbase.getTss().getInterference()};
    }

    @Override
    public double[] calculateRealEvaluations(ProblemHeuristicTestBase<FrequencyAssignmentSolutionGenerator, FrequencyAssignmentProblem, FrequencyAssignmentProblemGenerator, FrequencyAssignmentSolution> heuristicTestbase) {
        double a = heuristicTestbase.getTss().calculateNConflicts(heuristicTestbase.getTsp());
        double b = heuristicTestbase.getTss().calculateInterference(heuristicTestbase.getTsp());
        return new double[]{a * FrequencyAssignmentObjectiveFunction1.K + b, a, b};
    }
}
