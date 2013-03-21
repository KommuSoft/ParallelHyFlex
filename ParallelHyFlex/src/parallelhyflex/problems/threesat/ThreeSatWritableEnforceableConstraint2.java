package parallelhyflex.problems.threesat;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Objects;
import parallelhyflex.problemdependent.WritableEnforceableConstraintBase;
import parallelhyflex.utils.Utils;

/**
 *
 * @author kommusoft
 */
public class ThreeSatWritableEnforceableConstraint2 extends WritableEnforceableConstraintBase<ThreeSatSolution, ThreeSatProblem> {

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
        if(distance > this.getMaxDistance()) {
            for(int i = distance; i > maxDistance; i--) {
                int index = Utils.StaticRandom.nextInt(solution.getLength());
                if(solution.getBit(index) != this.getRoot().getBit(index)) {
                    solution.swapBit(index,this.getProblem());
                }
                else {
                    i++;
                }
            }
        }
    }

    @Override
    public void enforceFalse(ThreeSatSolution solution) {
        int distance = this.calculateDistance(solution);
        if(distance <= this.getMaxDistance()) {
            for(int i = distance; i <= maxDistance; i++) {
                int index = Utils.StaticRandom.nextInt(solution.getLength());
                if(solution.getBit(index) == this.getRoot().getBit(index)) {
                    solution.swapBit(index,this.getProblem());
                }
                else {
                    i--;
                }
            }
        }
    }

    @Override
    public boolean isSatisfied(ThreeSatSolution solution) {
        return this.getProblem().getDistanceFunction(0).evaluateDistanceSmallerThanOrEqual(this.getRoot(), solution,this.getMaxDistance());
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

    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.root);
        hash = 67 * hash + this.maxDistance;
        return hash;
    }

}
