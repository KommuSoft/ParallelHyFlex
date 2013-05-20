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

    public <T extends Serializable> StateExchangerProxy<T> generateProxy(int index);

    ArrayIterator<ExchangeState> stateIterator();
}
