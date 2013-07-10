package parallelhyflex.problemdependent.solution;

import java.io.Serializable;
import parallelhyflex.communication.serialisation.ReadWriteable;

/**
 *
 * @param <TSolution>
 * @author kommusoft
 */
public interface Solution<TSolution> extends Serializable, ReadWriteable {

    public TSolution clone();

    public boolean equalSolution(TSolution other);

    public boolean hasFastDifferenceWith(TSolution other);
}
