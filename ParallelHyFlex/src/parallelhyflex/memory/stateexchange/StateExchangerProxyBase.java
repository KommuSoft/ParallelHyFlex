package parallelhyflex.memory.stateexchange;

import java.io.Serializable;

/**
 *
 * @param <T>
 * @author kommusoft
 */
public abstract class StateExchangerProxyBase<T extends Serializable> implements StateExchangerProxy<T> {

    /**
     *
     */
    protected final StateExchanger exchanger;
    /**
     *
     */
    protected final int index;

    /**
     *
     * @param exchanger
     * @param index
     */
    public StateExchangerProxyBase(StateExchanger exchanger, int index) {
        this.exchanger = exchanger;
        this.index = index;
    }

    /**
     * @return the exchanger
     */
    @Override
    public StateExchanger getExchanger() {
        return exchanger;
    }

    /**
     * @return the index
     */
    @Override
    public int getIndex() {
        return index;
    }
}
