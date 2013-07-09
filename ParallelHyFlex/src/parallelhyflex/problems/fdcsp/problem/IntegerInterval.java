package parallelhyflex.problems.fdcsp.problem;

import java.util.Iterator;
import parallelhyflex.algebra.InductiveBiasException;
import parallelhyflex.algebra.WithAddSubNegOperators;
import parallelhyflex.algebra.WithSetOperators;
import parallelhyflex.utils.Utils;

/**
 *
 * @author kommusoft
 */
public final class IntegerInterval implements Comparable<IntegerInterval>, Iterable<Integer>, Cloneable, WithSetOperators<IntegerInterval, IntegerInterval>, WithAddSubNegOperators<IntegerInterval, IntegerInterval>, FiniteDomain<Integer> {

    private int low;
    private int high;

    /**
     *
     * @param low
     * @param high
     */
    public IntegerInterval(int low, int high) {
        this.low = low;
        this.high = high;
    }

    IntegerInterval(int value) {
        this(value, value);
    }

    /**
     *
     * @return
     */
    @Override
    public int size() {
        if (this.low <= this.high) {
            return this.high + 1 - this.low;
        } else {
            return 0x00;
        }
    }

    /**
     *
     * @param value
     * @return
     */
    @Override
    public boolean contains(Integer value) {
        return (low <= value && value <= high);
    }

    /**
     *
     * @return
     */
    @Override
    public boolean clear() {
        boolean res = this.notEmpty();
        this.high = -1;
        this.low = 0;
        return res;
    }

    /**
     *
     * @param low
     * @param high
     * @return
     */
    public boolean contains(int low, int high) {
        return high < low || (this.low <= low && high <= this.high);
    }

    /**
     *
     * @param si
     * @return
     */
    public boolean contains(IntegerInterval si) {
        return contains(si.low, si.high);
    }

    /**
     *
     * @param si
     * @return
     */
    public boolean overlap(IntegerInterval si) {
        return Math.max(this.low, si.low) <= Math.min(this.high, si.high);
    }

    /**
     *
     * @param si
     * @return
     */
    @Override
    public boolean canUnion(IntegerInterval si) {
        return (Math.max(this.low, si.low) - 1 <= Math.min(this.high, si.high)) || this.empty() || si.empty();
    }

    /**
     *
     * @return
     */
    public boolean empty() {
        return this.high < this.low;
    }

    /**
     *
     * @return
     */
    public boolean notEmpty() {
        return this.high >= this.low;
    }

    /**
     * @return the low
     */
    @Override
    public Integer low() {
        return low;
    }

    /**
     * @param low the low to set
     */
    public void setLow(int low) {
        this.low = low;
    }

    /**
     * @return the high
     */
    @Override
    public Integer high() {
        return high;
    }

    /**
     * @param high the high to set
     */
    public void setHigh(int high) {
        this.high = high;
    }

    /**
     *
     * @param t
     * @return
     */
    @Override
    public int compareTo(IntegerInterval t) {
        return this.low - t.low;
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        return 79 * this.low + this.high + 43_687;
    }

    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof IntegerInterval) {
            final IntegerInterval other = (IntegerInterval) obj;
            return ((this.low == other.low && this.high == other.high) || (this.empty() && other.empty()));
        }
        return false;
    }

    /**
     *
     * @return
     */
    @Override
    protected IntegerInterval clone() {
        return new IntegerInterval(this.low, this.high);
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        if (this.low < this.high) {
            return String.format("[%s,%s]", this.low, this.high);
        } else if (this.low == this.high) {
            return String.format("{%s}", this.low);
        } else {
            return "/";
        }
    }

    /**
     *
     * @param other
     * @return
     * @throws InductiveBiasException
     */
    @Override
    public IntegerInterval union(IntegerInterval other) throws InductiveBiasException {
        if (this.canUnion(other)) {
            return new IntegerInterval(Math.min(this.low, other.low), Math.max(this.high, other.high));
        } else {
            throw new InductiveBiasException();
        }
    }

    /**
     *
     * @param other
     * @return
     * @throws InductiveBiasException
     */
    @Override
    public boolean unionWith(IntegerInterval other) throws InductiveBiasException {
        if (this.canUnion(other)) {
            boolean ch = (other.low < this.low || other.high > this.low);
            this.low = Math.min(this.low, other.low);
            this.high = Math.max(this.high, other.high);
            return ch;
        } else {
            throw new InductiveBiasException();
        }
    }

    /**
     *
     * @param other
     * @return
     */
    @Override
    public IntegerInterval intersection(IntegerInterval other) {
        int low = Math.max(this.low, other.low);
        int high = Math.min(this.high, other.high);
        return new IntegerInterval(low, high);
    }

    /**
     *
     * @param other
     * @return
     */
    @Override
    public boolean intersectWith(IntegerInterval other) {
        boolean ch = (other.low > this.low || other.high < this.high);
        this.low = Math.max(this.low, other.low);
        this.high = Math.min(this.high, other.high);
        return ch;
    }

    /**
     *
     * @param other
     * @return
     * @throws InductiveBiasException
     */
    @Override
    public IntegerInterval minus(IntegerInterval other) throws InductiveBiasException {
        if (!canMinus(other)) {
            throw new InductiveBiasException();
        } else if (other.low >= this.low && other.low <= this.high) {
            return new IntegerInterval(this.low, other.low - 1);
        } else if (other.high >= this.low && other.high < this.high) {
            return new IntegerInterval(other.high + 1, this.high);
        } else {
            return new IntegerInterval(this.low, this.high);
        }
    }

    /**
     *
     * @param other
     * @return
     * @throws InductiveBiasException
     */
    @Override
    public boolean minusWith(IntegerInterval other) throws InductiveBiasException {
        boolean ch = true;
        if (!canMinus(other)) {
            throw new InductiveBiasException();
        } else if (other.low >= this.low && other.low <= this.high) {
            this.high = other.low - 1;
        } else if (other.high >= this.low && other.high < this.high) {
            this.low = other.high + 1;
        } else if (other.low <= this.low && other.high >= this.high) {
            this.clear();
        } else {
            ch = false;
        }
        return ch;
    }

    /**
     *
     * @param tr
     * @return
     */
    @Override
    public boolean canIntersect(IntegerInterval tr) {
        return true;
    }

    /**
     *
     * @param tr
     * @return
     */
    @Override
    public boolean canMinus(IntegerInterval tr) {
        return (tr.low <= this.low || tr.high >= this.high);
    }

    /**
     *
     * @param other
     * @return
     */
    @Override
    public IntegerInterval add(IntegerInterval other) {
        return new IntegerInterval(this.low + other.low, this.high + other.high);
    }

    /**
     *
     * @param other
     */
    @Override
    public void addWith(IntegerInterval other) {
        this.low += other.low;
        this.high += other.high;
    }

    /**
     *
     * @param other
     * @return
     */
    @Override
    public IntegerInterval sub(IntegerInterval other) {
        return new IntegerInterval(this.low - other.high, this.high - other.low);
    }

    /**
     *
     * @param other
     */
    @Override
    public void subWith(IntegerInterval other) {
        this.low -= other.high;
        this.high -= other.low;
    }

    /**
     *
     * @param other
     * @return
     */
    @Override
    public boolean canAdd(IntegerInterval other) {
        return true;
    }

    /**
     *
     * @param other
     * @return
     */
    @Override
    public boolean canSub(IntegerInterval other) {
        return true;
    }

    /**
     *
     * @return
     */
    @Override
    public IntegerInterval neg() {
        return new IntegerInterval(-this.high, -this.low);
    }

    /**
     *
     */
    @Override
    public void negWith() {
        int tmp = -this.low;
        this.low = -this.high;
        this.high = tmp;
    }

    /**
     *
     * @return
     */
    @Override
    public boolean canNeg() {
        return true;
    }

    /**
     *
     * @param index
     * @return
     */
    @Override
    public Integer getIth(int index) {
        return this.low + index;
    }

    /**
     *
     * @return
     */
    @Override
    public Iterator<Integer> iterator() {
        return Utils.sequence(low, high + 1).iterator();
    }
}
