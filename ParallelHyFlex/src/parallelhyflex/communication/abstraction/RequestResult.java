package parallelhyflex.communication.abstraction;

import mpi.Status;

/**
 *
 * @author kommusoft
 */
public interface RequestResult extends CommModeSensitive {

    Status Wait();

    Status Test();
}
