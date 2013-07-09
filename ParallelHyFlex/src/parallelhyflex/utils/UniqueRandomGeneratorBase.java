package parallelhyflex.utils;

import java.util.Iterator;

/**
 *
 * @param <T> 
 * @author kommusoft
 */
public abstract class UniqueRandomGeneratorBase<T> implements UniqueRandomGenerator<T> {

    @Override
    public Iterator<T> iterator() {
        return this;
    }
}
