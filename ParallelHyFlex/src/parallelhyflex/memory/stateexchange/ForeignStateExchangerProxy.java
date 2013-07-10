package parallelhyflex.memory.stateexchange;

import java.io.Serializable;
import java.util.Iterator;
import java.util.logging.Logger;
import parallelhyflex.communication.Communication;

/**
 *
 * @param <T>
 * @author kommusoft
 */
public class ForeignStateExchangerProxy<T extends Serializable> extends StateExchangerProxyBase {

    private static final Logger LOG = Logger.getLogger(ForeignStateExchangerProxy.class.getName());

    /**
     *
     * @param exchanger
     * @param index
     */
    public ForeignStateExchangerProxy(StateExchanger exchanger, int index) {
        super(exchanger, index);
    }

    /**
     *
     * @return
     */
    @Override
    public Iterator<T> iterator() {
        return new StateExchangerProxyIterator();
    }

    private class StateExchangerProxyIterator implements Iterator<T> {

        private int processor = 0;

        @Override
        public boolean hasNext() {
            return processor < Communication.getCommunication().getSize() - 1;
        }

        @Override
        public T next() {
            int delta = -1;
            if (processor >= Communication.getCommunication().getRank()) {
                delta = 0;
            }
            processor++;
            return getExchanger().getState(processor + delta).readObject(getIndex());
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Cannot remove objects from StateExchanger.");
        }
    }
}
