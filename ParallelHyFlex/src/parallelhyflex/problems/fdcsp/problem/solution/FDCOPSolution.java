package parallelhyflex.problems.fdcsp.problem.solution;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import parallelhyflex.communication.SerialisationUtils;
import parallelhyflex.problemdependent.solution.Solution;
import parallelhyflex.problems.fdcsp.problem.Variable;
import parallelhyflex.utils.Utils;

/**
 *
 * @author kommusoft
 */
public class FDCOPSolution implements Solution<FDCOPSolution> {

    private final double[] evals;
    private final int[] values;

    public FDCOPSolution(double[] evals, int[] values) {
        this.evals = (double[]) evals.clone();
        this.values = (int[]) values.clone();
    }

    public FDCOPSolution(int[] values, double[] evals) {
        this(evals, values);
    }

    public int getVariableValue(int variableIndex) {
        return this.values[variableIndex];
    }

    public void setVariableValue(int variableIndex, int value) {
        this.values[variableIndex] = value;
    }

    public int getVariableValue(Variable var) {
        return this.values[var.getIndex()];
    }

    public void setVariableValue(Variable var, int value) {
        this.values[var.getIndex()] = value;
    }

    @Override
    public FDCOPSolution clone() {
        return new FDCOPSolution(this.evals, this.values);
    }

    @Override
    public boolean equalSolution(FDCOPSolution other) {
        return Utils.arrayEquality(this.evals, other.evals) && Utils.arrayEquality(this.values, other.values);
    }

    @Override
    public boolean hasFastDifferenceWith(FDCOPSolution other) {
        return false;
    }

    @Override
    public void read(DataInputStream dis) throws IOException {
        SerialisationUtils.readIntArray(dis, this.values);
        SerialisationUtils.readDoubleArray(dis, this.evals);
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {
        SerialisationUtils.writeIntArray(dos, values);
        SerialisationUtils.writeDoubleArray(dos, evals);
    }

    public void setEvaluationValue(int index, double value) {
        this.evals[index] = value;
    }
}
