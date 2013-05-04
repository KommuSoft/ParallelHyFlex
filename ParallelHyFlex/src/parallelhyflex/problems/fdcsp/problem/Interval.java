package parallelhyflex.problems.fdcsp.problem;

import parallelhyflex.algebra.collections.Tree;

/**
 *
 * @author kommusoft
 */
public class Interval {

    private final Tree<SingleInterval> singleIntervals = new Tree<>();

    public Interval(int low, int high) {
        this.singleIntervals.add(new SingleInterval(low, high));
    }

    @Override
    public String toString() {
        if (this.singleIntervals.isEmpty()) {
            return "<empty interval>";
        } else {
            StringBuilder sb = new StringBuilder();
            boolean union = false;
            for (SingleInterval si : singleIntervals) {
                if (union) {
                    sb.append(String.format(" u %s", si));
                } else {
                    sb.append(si);
                    union = true;
                }
            }
            return sb.toString();
        }
    }
}
