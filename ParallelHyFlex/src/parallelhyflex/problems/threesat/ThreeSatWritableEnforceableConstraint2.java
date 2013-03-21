package parallelhyflex.problems.threesat;

import java.io.DataOutputStream;
import java.io.IOException;
import parallelhyflex.problemdependent.WritableEnforceableConstraintBase;

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
        //TODO: make gap closer
    }

    @Override
    public void enforceFalse(ThreeSatSolution solution) {
        int distance = this.calculateDistance(solution);
        //TODO: make gap wider
    }

    @Override
    public boolean isSatisfied(ThreeSatSolution solution) {
        return this.calculateDistance(solution) <= this.getMaxDistance();
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
}
