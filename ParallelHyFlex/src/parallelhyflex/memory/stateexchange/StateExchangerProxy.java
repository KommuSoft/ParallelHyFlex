package parallelhyflex.memory.stateexchange;

import java.io.Serializable;

/**
 *
 * @author kommusoft
 */
public interface StateExchangerProxy<T extends Serializable> extends Iterable<T> {

    /**
     * @return the exchanger
     */
    StateExchanger getExchanger();

    /**
     * @return the index
     */
    int getIndex();
    
}
