package parallelhyflex.problems.circlepositioning.solution;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import parallelhyflex.communication.serialisation.SerialisationUtils;
import parallelhyflex.problemdependent.solution.Solution;
import parallelhyflex.problems.circlepositioning.CirclePositioningUtils;
import parallelhyflex.problems.circlepositioning.problem.CirclePositioningProblem;
import parallelhyflex.utils.Utils;

/**
 *
 * @author kommusoft
 */
public class CirclePositioningSolution implements Solution<CirclePositioningSolution> {

    private final double[] positions;
    private double overlapArea;

    public CirclePositioningSolution(double[] positions, double overlapArea) {
        this.positions = positions;
        this.overlapArea = overlapArea;
    }
    public CirclePositioningSolution(double[] positions, double[] radia) {
        this(positions,0.0d);
        this.overlapArea = this.calculateOverlap(radia);
    }
    public CirclePositioningSolution(double[] positions, CirclePositioningProblem problem) {
        this(positions,problem.getRadia());
    }

    public double getXi(int index) {
        return getPositions()[index << 0x01];
    }

    public double getYi(int index) {
        return getPositions()[(index << 0x01) + 0x01];
    }
    
    private double calculateOverlap(double[] rad) {
        int n = rad.length;
        double overlap = 0.0d;
        double[] pos = this.positions;
        for (int i = 0, i2 = 0; i < n;) {
            double x1 = pos[i2++];
            double y1 = pos[i2++];
            double r1 = rad[i++];
            for (int j = i, j2 = i2; j < n;) {
                double x2 = pos[j2++];
                double y2 = pos[j2++];
                double r2 = rad[j++];
                overlap += CirclePositioningUtils.calculateCircleOverlapArea(x1, y1, r1, x2, y2, r2);
            }
        }
        return overlap;
    }

    public void moveCircle(CirclePositioningProblem problem, int index, double dx, double dy) {
        int i = index << 0x01;
        double x = positions[i++] + dx;
        double y = positions[i] + dy;
        this.setCircle(problem, index, x, y);
    }

    public void setCircle(CirclePositioningProblem problem, int index, double x3, double y3) {
        double doverlap = 0.0d;
        int n = problem.getNumberOfCircles();
        double[] radia = problem.getRadia();
        int i = index << 0x01, i2 = 0x00;
        double x1 = positions[i++];
        double y1 = positions[i];
        double r13 = radia[index];
        for (i = 0; i < n;) {
            if (i != index) {
                double x2 = positions[i2++];
                double y2 = positions[i2++];
                double r2 = radia[i++];
                doverlap += CirclePositioningUtils.calculateCircleOverlapArea(x3, y3, r13, x2, y2, r2) - CirclePositioningUtils.calculateCircleOverlapArea(x1, y1, r13, x2, y2, r2);
            }
        }
        this.overlapArea += doverlap;
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
        if (n != vb.length || Math.abs(this.getOverlapArea() - other.getOverlapArea()) > Utils.Tolerance) {
            return false;
        }
        for (int i = 0; i < n; i++) {
            if (Math.abs(va[i] - vb[i]) > Utils.Tolerance) {
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
