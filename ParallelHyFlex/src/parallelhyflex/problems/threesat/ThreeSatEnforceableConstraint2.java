package parallelhyflex.problems.threesat;

import parallelhyflex.problemdependent.EnforceableConstraintBase;

/**
 *
 * @author kommusoft
 */
public class ThreeSatEnforceableConstraint2 extends EnforceableConstraintBase<ThreeSatSolution, ThreeSatProblem> {

    private final ThreeSatSolution root;
    private final int maxDistance;

    public ThreeSatEnforceableConstraint2(ThreeSatProblem problem, ThreeSatSolution root, int maxDistance) {
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
}
