package parallelhyflex.problems.fdcsp.problem.solution;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;
import parallelhyflex.communication.serialisation.SerialisationUtils;
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

    /**
     *
     * @param evals
     * @param values
     */
    public FDCOPSolution(double[] evals, int[] values) {
        this.evals = (double[]) evals.clone();
        this.values = (int[]) values.clone();
    }

    /**
     *
     * @param values
     * @param evals
     */
    public FDCOPSolution(int[] values, double[] evals) {
        this(evals, values);
    }

    /**
     *
     * @param variableIndex
     * @return
     */
    public int getVariableValue(int variableIndex) {
        return this.values[variableIndex];
    }

    /**
     *
     * @param variableIndex
     * @param value
     */
    public void setVariableValue(int variableIndex, int value) {
        this.values[variableIndex] = value;
    }

    /**
     *
     * @param var
     * @return
     */
    public int getVariableValue(Variable var) {
        return this.values[var.getIndex()];
    }

    /**
     *
     * @param var
     * @param value
     */
    public void setVariableValue(Variable var, int value) {
        this.values[var.getIndex()] = value;
    }

    /**
     *
     * @return
     */
    @Override
    public FDCOPSolution clone() {
        return new FDCOPSolution(this.evals, this.values);
    }

    /**
     *
     * @param other
     * @return
     */
    @Override
    public boolean equalSolution(FDCOPSolution other) {
        return Utils.arrayEquality(this.evals, other.evals) && Utils.arrayEquality(this.values, other.values);
    }
    
    /**
     *
     * @return
     */
    public int[] getVariableValues () {
        return this.values;
    }
    
    /**
     *
     * @return
     */
    public double[] getEvaluationValues () {
        return this.evals;
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        return Utils.hashCode(values);
    }

    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof FDCOPSolution) {
            final FDCOPSolution other = (FDCOPSolution) obj;
            return Utils.arrayEquality(values, other.values);
        } else {
            return false;
        }
    }

    /**
     *
     * @param other
     * @return
     */
    @Override
    public boolean hasFastDifferenceWith(FDCOPSolution other) {
        return false;
    }

    /**
     *
     * @param dis
     * @throws IOException
     */
    @Override
    public void read(DataInputStream dis) throws IOException {
        SerialisationUtils.readIntArray(dis, this.values);
        SerialisationUtils.readDoubleArray(dis, this.evals);
    }

    /**
     *
     * @param dos
     * @throws IOException
     */
    @Override
    public void write(DataOutputStream dos) throws IOException {
        SerialisationUtils.writeIntArray(dos, values);
        SerialisationUtils.writeDoubleArray(dos, evals);
    }

    /**
     *
     * @param index
     * @param value
     */
    public void setEvaluationValue(int index, double value) {
        this.evals[index] = value;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return String.format("FDCOPSolution < f(%s) = %s >", Arrays.toString(this.values), Arrays.toString(this.evals));
    }

    /**
     *
     * @param index
     * @return
     */
    public double getEvaluationValue(int index) {
        return this.evals[index];
    }
}
