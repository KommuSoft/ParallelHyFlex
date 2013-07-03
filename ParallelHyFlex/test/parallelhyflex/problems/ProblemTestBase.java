/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parallelhyflex.problems;

/**
 *
 * @author kommusoft
 */
public abstract class ProblemTestBase<T0, T1, T2, T3> {

    private T0 tsg;
    private T1 tsp;
    private T2 tspg;
    private T3 tss;

    protected abstract void renewProblem();

    protected abstract void renewProblemGenerator();

    protected abstract void renewSolution();

    protected abstract void renewSolutionGenerator();

    /**
     * @return the tsg
     */
    public T0 getTsg() {
        return tsg;
    }

    /**
     * @param tsg the tsg to set
     */
    public void setTsg(T0 tsg) {
        this.tsg = tsg;
    }

    /**
     * @return the tsp
     */
    public T1 getTsp() {
        return tsp;
    }

    /**
     * @param tsp the tsp to set
     */
    public void setTsp(T1 tsp) {
        this.tsp = tsp;
    }

    /**
     * @return the tspg
     */
    public T2 getTspg() {
        return tspg;
    }

    /**
     * @param tspg the tspg to set
     */
    public void setTspg(T2 tspg) {
        this.tspg = tspg;
    }

    /**
     * @return the tss
     */
    public T3 getTss() {
        return tss;
    }

    /**
     * @param tss the tss to set
     */
    public void setTss(T3 tss) {
        this.tss = tss;
    }
}
