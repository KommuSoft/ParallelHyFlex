package hyflextests;

import AbstractClasses.HyperHeuristic;
import AbstractClasses.ProblemDomain;
import BinPacking.BinPacking;
import FlowShop.FlowShop;
import PersonnelScheduling.PersonnelScheduling;
import SAT.SAT;
import VRP.VRP;
import java.util.ArrayList;
import java.util.Random;
import travelingSalesmanProblem.TSP;

/**
 *
 * @author kommusoft
 */
public class HyFlexTests {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Random rand = new Random(20130607);
        ArrayList<ProblemDomain> domains = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            domains.add(new SAT(rand.nextInt()));
            domains.add(new VRP(rand.nextInt()));
            domains.add(new BinPacking(rand.nextInt()));
            domains.add(new FlowShop(rand.nextInt()));
            domains.add(new PersonnelScheduling(rand.nextInt()));
            domains.add(new TSP(rand.nextInt()));
        }
        for (int i = 0; i < 100; i++) {
            for (ProblemDomain problem : domains) {
                HyperHeuristic hyper_heuristic_object = generateHyperHeuristic(rand, i);
                problem.loadInstance(2);
                hyper_heuristic_object.setTimeLimit(60000);
                hyper_heuristic_object.loadProblemDomain(problem);
                hyper_heuristic_object.run();
                System.out.println(hyper_heuristic_object.getBestSolutionValue());
            }
        }
    }

    public static HyperHeuristic generateHyperHeuristic(Random rand, int i) {
        return new ProbabilityVectorTests(rand.nextInt(), i);
    }
}
