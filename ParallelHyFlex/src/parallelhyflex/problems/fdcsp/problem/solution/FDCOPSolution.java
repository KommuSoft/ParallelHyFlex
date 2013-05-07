package parallelhyflex.problems.fdcsp.problem.solution;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import parallelhyflex.communication.SerialisationUtils;
import parallelhyflex.problemdependent.solution.Solution;
import parallelhyflex.utils.Utils;

/**
 *
 * @author kommusoft
 */
public class FDCOPSolution implements Solution<FDCOPSolution> {

    private final double[] evals;
    private final int[] values;

    private FDCOPSolution(double[] evals, int[] values) {
        this.evals = (double[]) evals.clone();
        this.values = (int[]) values.clone();
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
}
