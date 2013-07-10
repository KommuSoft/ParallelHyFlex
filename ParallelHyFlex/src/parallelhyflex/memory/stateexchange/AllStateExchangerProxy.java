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
public class AllStateExchangerProxy<T extends Serializable> extends StateExchangerProxyBase<T> {

    private static final Logger LOG = Logger.getLogger(AllStateExchangerProxy.class.getName());

    /**
     *
     * @param exchanger
     * @param index
     */
    public AllStateExchangerProxy(StateExchanger exchanger, int index) {
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
            return processor < Communication.getCommunication().getSize();
        }

        @Override
        public T next() {
            return getExchanger().getState(processor++).readObject(getIndex());
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Cannot remove objects from StateExchanger.");
        }
    }
}
