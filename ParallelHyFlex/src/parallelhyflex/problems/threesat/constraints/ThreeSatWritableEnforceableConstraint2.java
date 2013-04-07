package parallelhyflex.problems.threesat.constraints;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Objects;
import parallelhyflex.problems.threesat.ClauseUtils;
import parallelhyflex.problems.threesat.problem.ThreeSatProblem;
import parallelhyflex.problems.threesat.solution.ThreeSatSolution;
import parallelhyflex.utils.CompactBitArray;

/**
 *
 * @author kommusoft
 */
public class ThreeSatWritableEnforceableConstraint2 extends ThreeSatWritableEnforceableConstraint {

    private final ThreeSatSolution root;
    private final int maxDistance;

    public ThreeSatWritableEnforceableConstraint2(ThreeSatProblem problem, ThreeSatSolution root, int maxDistance) {
        super(problem);
        this.root = root;
        this.maxDistance = maxDistance;
    }

    private int calculateDistance(ThreeSatSolution solution) {
        return (int) Math.floor(this.getProblem().getDistanceFunction(0).evaluateDistance(this.getRoot(), solution));
    }

    @Override
    public void enforceTrue(ThreeSatSolution solution) {
        int distance = this.calculateDistance(solution);
        CompactBitArray rootcba = this.root.getCompactBitArray();
        CompactBitArray solucba = solution.getCompactBitArray();
        for (int i = distance; i > maxDistance; i--) {
            int index = ClauseUtils.getNonEqualVariableIndex(rootcba,solucba);
            solution.swapBit(index, this.getProblem());
        }
    }

    @Override
    public void enforceFalse(ThreeSatSolution solution) {
        int distance = this.calculateDistance(solution);
        CompactBitArray rootcba = this.root.getCompactBitArray();
        CompactBitArray solucba = solution.getCompactBitArray();
        for (int i = distance; i <= maxDistance; i++) {
            int index = ClauseUtils.getEqualVariableIndex(rootcba,solucba);
            solution.swapBit(index, this.getProblem());
        }
    }

    @Override
    public boolean isSatisfied(ThreeSatSolution solution) {
        return this.getProblem().getDistanceFunction(0).evaluateDistanceSmallerThanOrEqual(this.getRoot(), solution, this.getMaxDistance());
    }

    /**
     * @return the root
     */
    public ThreeSatSolution getRoot() {
        return root;
    }

    /**
     * @return the maxDistance
     */
    public int getMaxDistance() {
        return maxDistance;
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {
        dos.writeLong((long) this.maxDistance);
        this.root.write(dos);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ThreeSatWritableEnforceableConstraint2) {
            ThreeSatWritableEnforceableConstraint2 ob = (ThreeSatWritableEnforceableConstraint2) obj;
            return (this.maxDistance == ob.maxDistance && this.root.equalSolution(ob.root));
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.root);
        hash = 67 * hash + this.maxDistance;
        return hash;
    }
}
