/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parallelhyflex.problems.threesat.heuristic;

import parallelhyflex.problems.threesat.solution.ThreeSatSolution;
import parallelhyflex.problems.threesat.problem.ThreeSatProblem;
import parallelhyflex.problemdependent.heuristics.CrossoverHeuristicBase;

/**
 *
 * @author kommusoft
 */
public abstract class ThreeSatHeuristicCTestBase extends ThreeSatHeuristicTestBase {
    
    @Override
    public abstract CrossoverHeuristicBase<ThreeSatSolution,ThreeSatProblem> renewHeuristic ();
    
    @Override
    public void applyHeuristic() {
        ThreeSatSolution tss2 = this.tsg.generateSolution();
        hb.applyHeuristicLocally(tss2);
    }
    
}