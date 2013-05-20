package parallelhyflex.memory.stateexchange;

import java.io.Serializable;
import java.util.Iterator;
import parallelhyflex.communication.Communication;

/**
 *
 * @author kommusoft
 */
public class StateExchangerProxy<T extends Serializable> implements Iterable<T> {

    private final StateExchanger exchanger;
    private final int index;

    public StateExchangerProxy(StateExchanger exchanger, int index) {
        this.exchanger = exchanger;
        this.index = index;
    }

    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private class StateExchangerProxyIterator implements Iterator<T> {

        private int processor = 0;

        @Override
        public boolean hasNext() {
            return processor < Communication.getCommunication().getSize();
        }

        @Override
        public T next() {
            return exchanger.getState(processor).readObject(index);
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Cannot remove objects from StateExchanger.");
        }
    }
}
