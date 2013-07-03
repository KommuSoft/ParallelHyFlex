/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parallelhyflex.problems.heuristics;

import parallelhyflex.problemdependent.heuristics.MutationHeuristicBase;
import parallelhyflex.problemdependent.problem.Problem;
import parallelhyflex.problemdependent.problem.ProblemReader;
import parallelhyflex.problemdependent.solution.Solution;
import parallelhyflex.problemdependent.solution.SolutionGenerator;

/**
 *
 * @author kommusoft
 */
public abstract class ProblemHeuristicMTestBase<TSG extends SolutionGenerator<TS>, TP extends Problem<TS>, TPG extends ProblemReader<TS, TP>, TS extends Solution<TS>> extends ProblemHeuristicTestBase<TSG,TP,TPG,TS> {

    @Override
    public abstract MutationHeuristicBase<TS,TP> renewHeuristic();
}
