package parallelhyflex.algebra;

import parallelhyflex.communication.serialisation.ReadWriteable;

/**
 *
 * @author kommusoft
 */
interface UpperMatrix<T> extends ReadWriteable {

    T get(int i, int j);

    void set(int i, int j, T value);
    
}