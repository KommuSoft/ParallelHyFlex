package parallelhyflex.problems.fdcsp.problem;

import parallelhyflex.algebra.InductiveBiasException;
import parallelhyflex.algebra.WithSetOperators;

/**
 *
 * @author kommusoft
 */
public class SingleInterval implements Comparable<SingleInterval>, Cloneable, WithSetOperators<SingleInterval,SingleInterval> {

    private int low;
    private int high;

    public SingleInterval(int low, int high) {
        this.low = low;
        this.high = high;
    }

    public boolean contains(int value) {
        return (low <= value && value <= high);
    }

    public boolean contains(int low, int high) {
        return this.low <= low && high <= this.high;
    }

    public boolean contains(SingleInterval si) {
        return contains(si.low, si.high);
    }
    
    public boolean overlap (SingleInterval si) {
        return Math.max(this.low,si.low) <= Math.min(this.high,si.high);
    }
    public boolean canUnite (SingleInterval si) {
        return Math.max(this.low,si.low)-1 <= Math.min(this.high,si.high);
    }

    public boolean notEmpty() {
        return this.high >= this.low;
    }

    /**
     * @return the low
     */
    public int getLow() {
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
    public int getHigh() {
        return high;
    }

    /**
     * @param high the high to set
     */
    public void setHigh(int high) {
        this.high = high;
    }

    @Override
    public int compareTo(SingleInterval t) {
        return ((Integer) this.getLow()).compareTo(t.getLow());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.low;
        hash = 79 * hash + this.high;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SingleInterval other = (SingleInterval) obj;
        if (this.low != other.low) {
            return false;
        }
        if (this.high != other.high) {
            return false;
        }
        return true;
    }

    @Override
    protected SingleInterval clone() {
        return new SingleInterval(this.low, this.high);
    }

    @Override
    public String toString() {
        if(this.low < this.high) {
            return String.format("[%s,%s]", this.low, this.high);
        }
        else if(this.low == this.high) {
            return String.format("{%s}",this.low);
        }
        else {
            return "/";
        }
    }

    @Override
    public SingleInterval union(SingleInterval other) throws InductiveBiasException {
        if (this.canUnite(other)) {
            return new SingleInterval(Math.min(this.low, other.low), Math.max(this.high, other.high));
        } else {
            throw new InductiveBiasException();
        }
    }

    @Override
    public void unionWith(SingleInterval other) throws InductiveBiasException {
        if (this.canUnite(other)) {
            this.low = Math.min(this.low, other.low);
            this.high = Math.max(this.high, other.high);
        } else {
            throw new InductiveBiasException();
        }
    }

    @Override
    public SingleInterval intersection(SingleInterval other) {
        int low = Math.max(this.low, other.low);
        int high = Math.min(this.high, other.high);
        if (low <= high) {
            return new SingleInterval(low, high);
        } else {
            return null;
        }
    }

    @Override
    public void intersectWith(SingleInterval other) {
        this.low = Math.max(this.low, other.low);
        this.high = Math.min(this.high, other.high);
    }

    @Override
    public SingleInterval minus(SingleInterval other) throws InductiveBiasException {
        if (other.low > this.low && other.high < this.high) {
            throw new InductiveBiasException();
        } else if (other.low >= this.low && other.low <= this.high) {
            return new SingleInterval(this.low, other.low - 1);
        } else if (other.high >= this.low && other.high < this.high) {
            return new SingleInterval(other.high + 1, this.high);
        } else {
            return new SingleInterval(this.low, this.high);
        }
    }

    @Override
    public void minusWith(SingleInterval other) throws InductiveBiasException {
        if (other.low > this.low && other.high < this.high) {
            throw new InductiveBiasException();
        } else if (other.low >= this.low && other.low <= this.high) {
            this.high = other.low - 1;
        } else if (other.high >= this.low && other.high < this.high) {
            this.low = other.high + 1;
        }
    }
}
