package parallelhyflex;

import java.io.IOException;
import parallelhyflex.parsing.ParsingException;
import parallelhyflex.problems.fdcsp.problem.FDCOPProblem;
import parallelhyflex.problems.fdcsp.problem.FDCOPProblemParser;

/**
 *
 * @author kommusoft
 */
public class OptPackMain {

    /**
     *
     * @param args
     * @throws IOException
     * @throws ParsingException
     */
    public static void main(String[] args) throws IOException, ParsingException {
        FDCOPProblemParser pp = new FDCOPProblemParser();
        FDCOPProblem prob = pp.parse("X in [1,2] Y in [1,2] Z in [1,2] Z #!= X X #!= Y minimizing X minimizing Y minimizing Z");
        System.out.println(prob.getSolutionGenerator().generateSolution());
    }
}
