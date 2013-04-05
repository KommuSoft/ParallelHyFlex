/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parallelhyflex.problems.threesat.heuristic;

import parallelhyflex.problemdependent.heuristics.MutationHeuristicBase;
import parallelhyflex.problems.threesat.solution.ThreeSatSolution;
import parallelhyflex.problems.threesat.problem.ThreeSatProblem;

/**
 *
 * @author kommusoft
 */
public abstract class ThreeSatHeuristicMTestBase extends ThreeSatHeuristicTestBase {
    
    @Override
    public abstract MutationHeuristicBase<ThreeSatSolution,ThreeSatProblem> renewHeuristic ();
    
}