package parallelhyflex.problems.threesat.solution;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.logging.Logger;
import parallelhyflex.problemdependent.solution.SolutionGeneratorBase;
import parallelhyflex.problems.threesat.problem.ThreeSatProblem;
import parallelhyflex.utils.CompactBitArray;

/**
 *
 * @author kommusoft
 */
public class ThreeSatSolutionGenerator extends SolutionGeneratorBase<ThreeSatSolution, ThreeSatProblem> {

    /**
     *
     * @param problem
     */
    public ThreeSatSolutionGenerator(ThreeSatProblem problem) {
        super(problem);
    }

    /**
     *
     * @return
     */
    @Override
    public ThreeSatSolution generateSolution() {
        ThreeSatProblem problem = this.getProblem();
        CompactBitArray cba = CompactBitArray.randomInstance(problem.getV());
        int nfail = 0;
        long[] clauses = problem.getClauses();
        for (int i = 0; i < clauses.length; i++) {
            if (!cba.satisfiesClause(clauses[i])) {
                nfail++;
            }
        }
        return new ThreeSatSolution(nfail, cba);
    }

    /**
     *
     * @param dis
     * @return
     * @throws IOException
     */
    @Override
    public ThreeSatSolution readAndGenerate(DataInputStream dis) throws IOException {
        return new ThreeSatSolution(dis.readInt(), CompactBitArray.fromDataInputStream(dis));
    }
    private static final Logger LOG = Logger.getLogger(ThreeSatSolutionGenerator.class.getName());
}
