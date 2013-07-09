/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parallelhyflex.problems.frequencyassignment.heuristic;

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
        return new double[]{heuristicTestbase.getTss().getInterference(), heuristicTestbase.getTss().getnConflicts()};
    }
    
    @Override
    public double[] calculateRealEvaluations(ProblemHeuristicTestBase<FrequencyAssignmentSolutionGenerator, FrequencyAssignmentProblem, FrequencyAssignmentProblemGenerator, FrequencyAssignmentSolution> heuristicTestbase) {
        return new double[]{heuristicTestbase.getTss().calculateInterference(heuristicTestbase.getTsp()), heuristicTestbase.getTss().calculateNConflicts(heuristicTestbase.getTsp())};
    }
}
