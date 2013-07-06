package parallelhyflex.problems.threesat.heuristics;

import java.util.ArrayList;
import parallelhyflex.problemdependent.heuristics.CrossoverHeuristicBase;
import parallelhyflex.problems.threesat.problem.ThreeSatProblem;
import parallelhyflex.problems.threesat.solution.ThreeSatSolution;
import parallelhyflex.utils.Utils;

/**
 * A crossover heuristic where the variable is selected based on the number of
 * influenced clauses that are true
 *
 * @author kommusoft
 */
public class ThreeSatHeuristicC2 extends CrossoverHeuristicBase<ThreeSatSolution, ThreeSatProblem> {
    
    public ThreeSatHeuristicC2(ThreeSatProblem problem) {
        super(problem);
    }
    
    @Override
    public void applyHeuristicLocally(ThreeSatSolution from1, ThreeSatSolution from2) {
        int[][] influence = this.getProblem().getInfluences();
        long[] clauses = this.getProblem().getClauses();
        int[] infli;
        long clause;
        int v = this.getProblem().getV();
        int na = from1.getConflictingClauses(), nb = from2.getConflictingClauses();
        ArrayList<Integer> swap = new ArrayList<>((int) Math.ceil(5.0d*nb*influence.length)/(8*(na+nb)));
        for (int i = 0; i < influence.length; i++) {
            if (from1.getBit(i) != from2.getBit(i)) {
                infli = influence[i];
                na = 0;
                nb = 0;
                for (int j = 0; j < infli.length; j++) {
                    clause = clauses[infli[j]];
                    if (from1.satisfiesClause(clause)) {
                        na++;
                    }
                    if (from2.satisfiesClause(clause)) {
                        nb++;
                    }
                }
                if(Utils.nextDouble() < (double) nb/(na+nb)) {
                    swap.add(i);
                }
            }
        }
        for(Integer i : swap) {
            from1.swapBit(i,this.getProblem());
        }
    }
}
