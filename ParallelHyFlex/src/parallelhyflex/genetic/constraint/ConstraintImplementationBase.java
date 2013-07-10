package parallelhyflex.genetic.constraint;

/**
 *
 * @author kommusoft
 * @param <TConstraint>
 * @param <TSolution>
 */
public abstract class ConstraintImplementationBase<TConstraint> implements ConstraintImplementation<TConstraint> {

    @Override
    public boolean isNotSatisfied(TConstraint constraint, int[] solution, int[][] ranges) {
        return !this.isSatisfied(constraint, solution, ranges);
    }
}
