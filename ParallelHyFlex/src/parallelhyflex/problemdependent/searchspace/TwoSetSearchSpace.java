package parallelhyflex.problemdependent.searchspace;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import parallelhyflex.problemdependent.constraints.EnforceableConstraint;
import parallelhyflex.problemdependent.solution.Solution;
import parallelhyflex.utils.ProbabilityUtils;

/**
 *
 * @author kommusoft
 */
public class TwoSetSearchSpace<TSolution extends Solution<TSolution>> extends SearchSpaceBase<TSolution> {

    private final ArrayList<EnforceableConstraint<TSolution>> positive = new ArrayList<>();
    private final ReentrantReadWriteLock posLock = new ReentrantReadWriteLock();
    private final ArrayList<EnforceableConstraint<TSolution>> negative = new ArrayList<>();
    private final ReentrantReadWriteLock negLock = new ReentrantReadWriteLock();

    @Override
    public void correct(TSolution solution) {
        Lock lo = this.posLock.readLock();
        lo.lock();
        try {
            for (EnforceableConstraint<TSolution> constraint : this.getPositive()) {
                constraint.enforceTrue(solution);
            }
        } finally {
            lo.unlock();
        }
        lo = this.negLock.readLock();
        lo.lock();
        try {
            if (this.getNegative().size() > 0) {
                ProbabilityUtils.randomElement(this.getNegative()).enforceFalse(solution);
            }
        } finally {
            lo.unlock();
        }
    }

    @Override
    public boolean isInSearchSpace(TSolution solution) {
        //TODO: positive
        Lock lo = this.negLock.readLock();
        lo.lock();
        try {
            for (EnforceableConstraint<TSolution> constraint : this.getNegative()) {
                if (!constraint.isSatisfied(solution)) {
                    return true;
                }
            }
            return false;
        } finally {
            lo.unlock();
        }
    }

    /**
     * @return the positive
     */
    public ArrayList<EnforceableConstraint<TSolution>> getPositive() {
        return positive;
    }

    public void replacePositive(Collection<? extends EnforceableConstraint<TSolution>> positive) {
        Lock lo = this.posLock.writeLock();
        lo.lock();
        try {
            this.positive.clear();
            this.positive.addAll(positive);
        } finally {
            lo.unlock();
        }
    }

    /**
     * @return the negative
     */
    public ArrayList<EnforceableConstraint<TSolution>> getNegative() {
        return negative;
    }

    public void replaceNegative(Collection<? extends EnforceableConstraint<TSolution>> positive) {
        Lock lo = this.negLock.writeLock();
        lo.lock();
        try {
            this.negative.clear();
            this.negative.addAll(positive);
        } finally {
            lo.unlock();
        }
    }
}
