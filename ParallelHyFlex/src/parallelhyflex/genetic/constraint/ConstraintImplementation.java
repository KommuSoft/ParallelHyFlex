package parallelhyflex.genetic.constraint;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import parallelhyflex.genetic.observer.ManipulationObserver;

/**
 *
 * @param <TConstraint>
 * @author kommusoft
 */
public interface ConstraintImplementation<TConstraint> {

    public void enforceTrue(ManipulationObserver observer, TConstraint constraint, int[] solution, int[][] ranges);

    public void enforceFalse(ManipulationObserver observer, TConstraint constraint, int[] solution, int[][] ranges);

    public boolean isSatisfied(TConstraint constraint, int[] solution, int[][] ranges);

    public boolean isNotSatisfied(TConstraint constraint, int[] solution, int[][] ranges);

    public void write(TConstraint constraint, DataOutputStream dos) throws IOException;

    public TConstraint read(DataInputStream dis) throws IOException;

    public TConstraint generate(int[] solution, int[][] ranges) throws ConstraintRepresentationException;
}
