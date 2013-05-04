package parallelhyflex.problems.fdcsp.problem;

/**
 *
 * @author kommusoft
 */
public class SingleInterval implements Comparable<SingleInterval>, Cloneable {

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
    
    public boolean notEmpty () {
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
    protected Object clone() {
        return new SingleInterval(this.low, this.high);
    }

    @Override
    public String toString() {
        return String.format("[%s,%s]", this.low, this.high);
    }
}
