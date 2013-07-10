/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parallelhyflex.algebra.improvement;

/**
 *
 * @param <T>
 * @author kommusoft
 */
public interface FixedImprovementList<T> extends Iterable<T> {

    /**
     *
     * @param improvement
     */
    void addImprovement(T improvement);

    /**
     *
     * @param improvement
     */
    void addImprovements(T... improvements);

    /**
     *
     * @param generations
     * @return
     */
    T getHistoryElement(int generations);

    /**
     *
     * @return
     */
    T getTop();

    /**
     *
     * @param initvalue
     */
    void reset(T initvalue);

    /**
     *
     * @return
     */
    int size();
}
