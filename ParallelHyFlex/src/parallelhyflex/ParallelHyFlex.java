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
            ThreeSatProblemGenerator tspg = new ThreeSatProblemGenerator(2_380, 10_000);// = new ThreeSatProblemGenerator(128,500);
            HyperHeuristic dummy;
            long timespan = 10_000;//ten seconds
            //long timespan = 360000;//six minutes
            //long timespan = 7200000;//two hours
            if (Communication.getCommunication().getRank() == 0) {
                ThreeSatProblem tsp = tspg.generateProblem();
                dummy = new SimpleThreeSatHyperHeuristic(tsp, timespan);
            } else {
                dummy = new SimpleThreeSatHyperHeuristic(tspg, timespan);
            }
            dummy.startExecute();
        } catch (ProtocolException | IOException e) {
            Communication.Log(e.toString());
            e.printStackTrace();
        }
        Communication.finalizeCommunication();
    }
}
