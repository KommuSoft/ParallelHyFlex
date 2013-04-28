package hyflextests;

import AbstractClasses.HyperHeuristic;
import AbstractClasses.ProblemDomain;
import Examples.ExampleHyperHeuristic1;
import SAT.SAT;
import java.util.Random;

/**
 *
 * @author kommusoft
 */
public class HyFlexTests {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Random rand = new Random();
        for (int f = 1; f < 30; f++) {
            for (int i = 0; i < 10; i++) {
                ProblemDomain problem = new SAT(rand.nextInt());
                HyperHeuristic hyper_heuristic_object = new ProbabilityVectorTests(rand.nextInt(),f);
                problem.loadInstance(2);
                hyper_heuristic_object.setTimeLimit(60000);
                hyper_heuristic_object.loadProblemDomain(problem);
                hyper_heuristic_object.run();
                System.out.println(hyper_heuristic_object.getBestSolutionValue());
            }
        }
    }
}
