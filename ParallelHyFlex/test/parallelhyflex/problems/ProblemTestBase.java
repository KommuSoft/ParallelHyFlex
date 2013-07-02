/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parallelhyflex.problems;

import parallelhyflex.problems.circlepositioning.problem.CirclePositioningProblemGenerator;

/**
 *
 * @author kommusoft
 */
public abstract class ProblemTestBase<T0, T1, T2, T3> {

    protected T0 tsg;
    protected T1 tsp;
    protected T2 tspg;
    protected T3 tss;

    protected abstract void renewProblem();

    protected abstract void renewProblemGenerator();

    protected abstract void renewSolution();

    protected abstract void renewSolutionGenerator();
}
