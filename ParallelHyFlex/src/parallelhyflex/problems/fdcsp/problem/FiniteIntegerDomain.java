package parallelhyflex.problems.fdcsp.problem;

import java.util.Iterator;
import parallelhyflex.algebra.WithSetOperators;

/**
 *
 * @author kommusoft
 */
public interface FiniteIntegerDomain extends WithSetOperators<FiniteIntegerDomain, FiniteIntegerDomain>, Iterable<IntegerInterval>, FiniteDomain<Integer> {

    public boolean add(int value);

    public boolean add(int low, int high);

    public boolean add(IntegerInterval si);

    public FiniteIntegerDomain clone();

    public boolean unionWith(Iterable<IntegerInterval> other);

    public boolean unionWith(IntegerInterval interval);

    public boolean unionWith(int low, int high);

    public boolean unionWith(int value);

    public boolean intersectWith(IntegerInterval interval);

    public boolean intersectWith(int low, int high);

    public boolean intersectWith(int value);

    public boolean minusWith(IntegerInterval interval);

    public boolean minusWith(int low, int high);

    public boolean minusWith(int value);

    public boolean minusWith(Iterable<IntegerInterval> other);

    public Iterator<Integer> integerIterator();

    public boolean intersectWith(Iterable<IntegerInterval> other);

    public void setToSingle(IntegerInterval interval);

    public void setToSingle(int low, int high);

    public void setToSingle(int value);
}
