package parallelhyflex.problems.fdcsp.problem.solution;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Iterator;
import parallelhyflex.communication.SerialisationUtils;
import parallelhyflex.problemdependent.solution.SolutionGeneratorBase;
import parallelhyflex.problems.fdcsp.problem.FDCOPProblem;
import parallelhyflex.problems.fdcsp.problem.expressions.Expression;
import parallelhyflex.utils.Utils;

/**
 *
 * @author kommusoft
 */
public class FDCOPSolutionGenerator extends SolutionGeneratorBase<FDCOPSolution, FDCOPProblem> {
    
    public FDCOPSolutionGenerator (FDCOPProblem problem) {
        super(problem);
    }

    @Override
    public FDCOPSolution generateSolution() {
        FDCOPProblem prob = this.getProblem();
        int n = prob.getNumberOfVariables();
        int k = prob.getNumberOfMinimalisations();
        int[] values = new int[n];
        double[] evals = new double[k];
        for(int i = 0; i < n; i++) {
            values[i] = prob.getDomain(i).getIth(Utils.StaticRandom.nextInt(prob.getDomainSize(i)));
        }
        FDCOPSolution sol = new FDCOPSolution(values,evals);
        int i = 0;
        for(Iterator<Expression> it = prob.minimalisationIterator(); it.hasNext(); i++) {
            sol.setEvaluationValue(i,it.next().getExpressionValue(sol));
        }
        return sol;
    }

    @Override
    public FDCOPSolution readAndGenerate(DataInputStream dis) throws IOException {
        int[] values = SerialisationUtils.readIntArray(dis);
        double[] evals = SerialisationUtils.readDoubleArray(dis);
        return new FDCOPSolution(values,evals);
    }
    
}
