package parallelhyflex.memory.stateexchange;

import java.io.IOException;
import java.io.Serializable;
import parallelhyflex.algebra.collections.ArrayIterator;

/**
 *
 * @author kommusoft
 */
public interface StateExchanger {

    ExchangeState getLocalState();

    ExchangeState getState(int rank);

    void synchronizeState() throws IOException;

    public <T extends Serializable> AllStateExchangerProxy<T> generateAllProxy(int index);

    /**
     *
     * @param <T>
     * @param index
     * @return
     */
    public <T extends Serializable> ForeignStateExchangerProxy<T> generateForeignProxy(int index);

    /**
     *
     * @param <T>
     * @param toAdd
     * @return
     */
    public <T extends Serializable> ForeignStateExchangerProxy<T> turnForeignProxy(T toAdd);

    /**
     *
     * @param <T>
     * @param toAdd
     * @return
     */
    public <T extends Serializable> AllStateExchangerProxy<T> turnAllProxy(T toAdd);

    /**
     *
     * @return
     */
    ArrayIterator<ExchangeState> stateIterator();
}
