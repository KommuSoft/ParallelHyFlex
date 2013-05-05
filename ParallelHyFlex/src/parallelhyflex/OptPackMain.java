package parallelhyflex;

import java.io.IOException;
import parallelhyflex.parsing.ParsingException;
import parallelhyflex.problems.fdcsp.problem.FDCOPProblemParser;

/**
 *
 * @author kommusoft
 */
public class OptPackMain {
    
    public static void main (String[] args) throws IOException, ParsingException {
        FDCOPProblemParser pp = new FDCOPProblemParser();
        pp.parse("X in [2,4] Y in [1,4] Z in [1,4]u[9,14] X #>= Y");
        /*FiniteIntegerDomain interval = new FiniteIntegerDomain(1,2);
        System.out.println(interval);
        interval.add(5,5);
        System.out.println(interval);
        interval.add(7,9);
        System.out.println(interval);
        interval.add(4,4);
        System.out.println(interval);
        interval.add(3,6);
        System.out.println(interval);
        FiniteIntegerDomain interval2 = new FiniteIntegerDomain();
        interval.minusWith(interval2);
        System.out.println(interval);
        interval2.add(4,5);
        interval.minusWith(interval2);
        System.out.println(interval);
        interval2.add(3,5);
        interval.minusWith(interval2);
        System.out.println(String.format("minus %s",interval2));
        System.out.println(interval);
        DomainToken dt = new DomainToken();
        System.out.println(dt.generate("[1,2]u[7,9]u{4}u[5,17]"));*/
    }
    
}
