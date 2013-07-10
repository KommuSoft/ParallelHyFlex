package parallelhyflex.algebra.probability;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.logging.Logger;

/**
 *
 * @author kommusoft
 */
public class SingleNormalizedProbabilityVector implements Serializable, NormalizedProbabilityVector {
    
    private static final Logger LOG = Logger.getLogger(SingleNormalizedProbabilityVector.class.getName());
    private static final long serialVersionUID = 1L;
    private double[] probabilities;
    private static final DecimalFormat df = new DecimalFormat("##.##");
    
    public SingleNormalizedProbabilityVector() {
        this.probabilities = new double[0x00];
    }

    /**
     *
     */
    @Override
    public void reset() {
        double[] vals = this.probabilities;
        double val = 1.0d / vals.length;
        for (int i = 0; i < vals.length; i++) {
            vals[i] = val;
        }
    }

    /**
     *
     * @param size
     */
    @Override
    public void setSize(int size) {
        if (this.probabilities.length != size) {
            this.probabilities = new double[size];
        }
        this.reset();
    }

    /**
     *
     * @param index
     * @param value
     */
    @Override
    public void setProbability(int index, double value) {
        this.probabilities[index] = value;
    }

    /**
     *
     * @param index
     * @return
     */
    @Override
    public double getProbability(int index) {
        return this.probabilities[index];
    }

    /**
     *
     * @return
     */
    @Override
    public int getSize() {
        return this.probabilities.length;
    }

    /**
     *
     * @param index
     * @param probability
     */
    @Override
    public void addProbability(int index, double probability) {
        this.probabilities[index] += probability;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[ ");
        for(double v : this.probabilities) {
            sb.append(df.format(v));
            sb.append(' ');
        }
        sb.append(']');
        return sb.toString();
    }

    /**
     *
     * @param index
     * @param probability
     */
    @Override
    public void subtractProbability(int index, double probability) {
        this.probabilities[index] -= probability;
    }
}
