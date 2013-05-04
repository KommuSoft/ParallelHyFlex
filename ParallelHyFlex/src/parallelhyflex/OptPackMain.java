package parallelhyflex;

import parallelhyflex.problems.fdcsp.problem.Interval;

/**
 *
 * @author kommusoft
 */
public class OptPackMain {
    
    public static void main (String[] args) {
        Interval interval = new Interval(1,2);
        System.out.println(interval);
        interval.add(5,5);
        System.out.println(interval);
        interval.add(7,9);
        System.out.println(interval);
        interval.add(4,4);
        System.out.println(interval);
        interval.add(3,6);
        System.out.println(interval);
    }
    
}
