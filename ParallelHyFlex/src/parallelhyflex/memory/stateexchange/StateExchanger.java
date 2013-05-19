package parallelhyflex.memory.stateexchange;

import java.io.IOException;
import parallelhyflex.algebra.collections.ArrayIterator;

/**
 *
 * @author kommusoft
 */
public interface StateExchanger {

    ExchangeState getLocalState();

    ExchangeState getState(int rank);

    void synchronizeState() throws IOException;

    ArrayIterator<ExchangeState> stateIterator();
}
