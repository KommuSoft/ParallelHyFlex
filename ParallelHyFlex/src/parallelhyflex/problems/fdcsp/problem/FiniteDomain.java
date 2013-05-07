package parallelhyflex.problems.fdcsp.problem;

/**
 *
 * @author kommusoft
 */
public interface FiniteDomain<T> {

    T low();

    T high();

    int size();

    T getIth(int index);
}
