package parallelhyflex;

import java.io.IOException;
import parallelhyflex.communication.Communication;
import parallelhyflex.problems.threesat.hyperheuristics.SimpleThreeSatHyperHeuristic;
import parallelhyflex.problems.threesat.problem.ThreeSatProblem;
import parallelhyflex.problems.threesat.problem.ThreeSatProblemGenerator;

/**
 *
 * @author kommusoft
 */
public class ParallelHyFlex {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ProtocolException, IOException {


        Communication.initializeCommunication(args);
        try {
            ThreeSatProblemGenerator tspg = new ThreeSatProblemGenerator(128, 10);// = new ThreeSatProblemGenerator(128,500);
            HyperHeuristic dummy;
            long timespan = 100000000;
            if (Communication.getCommunication().getRank() == 0) {
                ThreeSatProblem tsp = tspg.generateProblem();
                dummy = new SimpleThreeSatHyperHeuristic(tsp, timespan);
            } else {
                dummy = new SimpleThreeSatHyperHeuristic(tspg, timespan);
            }
        } catch (Exception e) {
            Communication.Log(e.toString());
            e.printStackTrace();
        }
        Communication.finalizeCommunication();
    }
}
