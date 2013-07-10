package parallelhyflex.algebra.probability;

import java.util.logging.Logger;

/**
 *
 * @author kommusoft
 */
public class ComposedNormalizedProbabilityVector implements NormalizedProbabilityVector {

    private static final Logger LOG = Logger.getLogger(ComposedNormalizedProbabilityVector.class.getName());
    private final NormalizedProbabilityVector localData;
    private Iterable<? extends NormalizedProbabilityVector> others;
    private double localInfluence = 1.0d;

    /**
     *
     */
    public ComposedNormalizedProbabilityVector() {
        this.localData = new SingleNormalizedProbabilityVector();
    }

    /**
     *
     * @param localInfluence
     */
    public ComposedNormalizedProbabilityVector(double localInfluence) {
        this();
        this.setLocalInfluence(localInfluence);
    }

    /**
     *
     * @param localData
     */
    public ComposedNormalizedProbabilityVector(NormalizedProbabilityVector localData) {
        this.localData = localData;
    }

    /**
     *
     * @param localData
     * @param localInfluence
     */
    public ComposedNormalizedProbabilityVector(NormalizedProbabilityVector localData, double localInfluence) {
        this(localData);
        this.setLocalInfluence(localInfluence);
    }

    /**
     *
     */
    @Override
    public void reset() {
        getLocalData().reset();
    }

    /**
     *
     * @param size
     */
    @Override
    public void setSize(int size) {
        getLocalData().setSize(size);
    }

    /**
     *
     * @param index
     * @param value
     */
    @Override
    public void setProbability(int index, double value) {
        getLocalData().setProbability(index, value);
    }

    /**
     *
     * @return
     */
    @Override
    public int getSize() {
        return getLocalData().getSize();
    }

    /**
     *
     * @param index
     * @param probability
     */
    @Override
    public void addProbability(int index, double probability) {
        getLocalData().addProbability(index, probability);
    }

    /**
     *
     * @param index
     * @param probability
     */
    @Override
    public void subtractProbability(int index, double probability) {
        getLocalData().subtractProbability(index, probability);
    }

    /**
     *
     * @param index
     * @return
     */
    @Override
    public double getProbability(int index) {
        double p = this.getLocalData().getProbability(index);
        if (this.localInfluence < 1.0d && this.others != null) {
            double q = this.getOtherProbability(index);
            if (!Double.isNaN(q)) {
                return p * this.localInfluence + q * (1.0d - this.localInfluence);
            } else {
                return p;
            }
        } else {
            return p;
        }
    }

    private double getOtherProbability(int index) {
        int n = 0;
        double val = 0.0d;
        for (NormalizedProbabilityVector vector : this.others) {
            if (vector != null && vector.getSize() > index) {
                val += vector.getProbability(index);
                n++;
            }
        }
        return val / n;
    }

    /**
     * @return the localInfluence
     */
    public double getLocalInfluence() {
        return localInfluence;
    }

    /**
     * @param localInfluence the localInfluence to set
     */
    public void setLocalInfluence(double localInfluence) {
        this.localInfluence = Math.max(0.0d, Math.min(1.0d, localInfluence));
    }

    /**
     * @return the others
     */
    public Iterable<? extends NormalizedProbabilityVector> getOthers() {
        return others;
    }

    /**
     * @param others the others to set
     */
    public void setOthers(Iterable<? extends NormalizedProbabilityVector> others) {
        this.others = others;
    }

    /**
     * @return the localData
     */
    public NormalizedProbabilityVector getLocalData() {
        return localData;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[ ");
        sb.append(this.localData);
        sb.append(':');
        sb.append(this.localInfluence);
        for (NormalizedProbabilityVector npv : this.others) {
            sb.append(npv);
            sb.append(" ");
        }
        sb.append(']');
        return sb.toString();
    }
}
