package parallelhyflex.problems.circlepositioning.solution;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import parallelhyflex.communication.serialisation.SerialisationUtils;
import parallelhyflex.problemdependent.solution.Solution;
import parallelhyflex.utils.Utils;

/**
 *
 * @author kommusoft
 */
public class CirclePositioningSolution implements Solution<CirclePositioningSolution> {
    
    private final double[] positions;
    private double overlapArea;
    
    public CirclePositioningSolution (double[] positions, double overlapArea) {
        this.positions = positions;
        this.overlapArea = overlapArea;
    }
    
    public double getXi (int i) {
        return getPositions()[i<<0x01];
    }
    public double getYi (int i) {
        return getPositions()[(i<<0x01)+0x01];
    }

    @Override
    public CirclePositioningSolution clone() {
        double[] values = new double[this.getPositions().length];
        System.arraycopy(this.getPositions(), 0, values, 0, this.getPositions().length);
        return new CirclePositioningSolution(values, this.getOverlapArea());
    }

    @Override
    public boolean equalSolution(CirclePositioningSolution other) {
        double[] va = this.getPositions();
        double[] vb = other.getPositions();
        int n = va.length;
        if(n != vb.length || Math.abs(this.getOverlapArea()-other.getOverlapArea()) > Utils.Tolerance) {
            return false;
        }
        for(int i = 0; i < n; i++) {
            if(Math.abs(va[i]-vb[i]) > Utils.Tolerance) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean hasFastDifferenceWith(CirclePositioningSolution other) {
        return false;
    }

    @Override
    public void read(DataInputStream dis) throws IOException {
        SerialisationUtils.readDoubleArray(dis, positions);
        this.overlapArea = dis.readDouble();
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {
        SerialisationUtils.writeDoubleArray(dos, positions);
        dos.writeDouble(this.overlapArea);
    }

    /**
     * @return the positions
     */
    public double[] getPositions() {
        return positions;
    }

    /**
     * @return the overlapArea
     */
    public double getOverlapArea() {
        return overlapArea;
    }
    
}
