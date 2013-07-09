package parallelhyflex.problems.threesat.constraint;

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

    /**
     *
     * @param problem
     * @param root
     * @param maxDistance
     */
    public ThreeSatWritableEnforceableConstraint2(ThreeSatProblem problem, ThreeSatSolution root, int maxDistance) {
        super(problem);
        this.root = root;
        this.maxDistance = maxDistance;
    }

    private int calculateDistance(ThreeSatSolution solution) {
        return (int) Math.floor(this.getProblem().getDistanceFunction(0).evaluateDistance(this.getRoot(), solution));
    }

    /**
     *
     * @param solution
     */
    @Override
    public void enforceTrue(ThreeSatSolution solution) {
        int distance = this.calculateDistance(solution);
        CompactBitArray rootcba = this.root.getCompactBitArray();
        CompactBitArray solucba = solution.getCompactBitArray();
        for (; distance > maxDistance; distance--) {
            int index = ClauseUtils.getNonEqualVariableIndex(rootcba,solucba);
            solution.swapBit(index, this.getProblem());
        }
    }

    /**
     *
     * @param solution
     */
    @Override
    public void enforceFalse(ThreeSatSolution solution) {
        int distance = this.calculateDistance(solution);
        CompactBitArray rootcba = this.root.getCompactBitArray();
        CompactBitArray solucba = solution.getCompactBitArray();
        //System.out.println("Distance is now: "+this.getProblem().getDistanceFunction(0).evaluateDistance(this.root,solution));
        //System.out.println("starting loop");
        for (; distance <= maxDistance; distance++) {
            solution.swapBit(ClauseUtils.getEqualVariableIndex(rootcba,solucba), this.getProblem());
            //System.out.println("Distance is now: "+this.getProblem().getDistanceFunction(0).evaluateDistance(this.root,solution));
        }
        //System.out.println(String.format("end for (distance must be larger than %s)",maxDistance));
    }

    /**
     *
     * @param solution
     * @return
     */
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

    /**
     *
     * @param dos
     * @throws IOException
     */
    @Override
    public void write(DataOutputStream dos) throws IOException {
        dos.writeLong((long) this.maxDistance);
        this.root.write(dos);
    }

    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ThreeSatWritableEnforceableConstraint2) {
            ThreeSatWritableEnforceableConstraint2 ob = (ThreeSatWritableEnforceableConstraint2) obj;
            return (this.maxDistance == ob.maxDistance && this.root.equalSolution(ob.root));
        } else {
            return false;
        }
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.root);
        hash = 67 * hash + this.maxDistance;
        return hash;
    }
}
