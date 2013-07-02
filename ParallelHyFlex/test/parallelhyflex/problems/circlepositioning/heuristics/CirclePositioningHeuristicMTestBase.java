/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parallelhyflex.problems.circlepositioning.heuristics;

import parallelhyflex.problemdependent.heuristics.MutationHeuristicBase;
import parallelhyflex.problems.circlepositioning.problem.CirclePositioningProblem;
import parallelhyflex.problems.circlepositioning.solution.CirclePositioningSolution;

/**
 *
 * @author kommusoft
 */
public abstract class CirclePositioningHeuristicMTestBase extends CirclePositioningHeuristicTestBase {

    @Override
    public abstract MutationHeuristicBase<CirclePositioningSolution, CirclePositioningProblem> renewHeuristic();
}
