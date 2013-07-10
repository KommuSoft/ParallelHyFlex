package parallelhyflex.algebra.probability;

import java.io.Serializable;

/**
 *
 * @author kommusoft
 */
public interface NormalizedProbabilityVector extends Serializable {

    /**
     *
     * @param index
     * @param probability
     */
    void addProbability(int index, double probability);

    /**
     *
     * @param index
     * @return
     */
    double getProbability(int index);

    /**
     *
     * @return
     */
    int getSize();

    /**
     *
     */
    void reset();

    /**
     *
     * @param index
     * @param value
     */
    void setProbability(int index, double value);

    /**
     *
     * @param size
     */
    void setSize(int size);

    /**
     *
     * @param index
     * @param probability
     */
    void subtractProbability(int index, double probability);
    
}
