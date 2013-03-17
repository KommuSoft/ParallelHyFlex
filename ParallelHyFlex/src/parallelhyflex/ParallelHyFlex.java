package parallelhyflex;

import java.io.IOException;
import parallelhyflex.communication.Communication;
import parallelhyflex.problems.threesat.ThreeSatProblem;
import parallelhyflex.problems.threesat.ThreeSatProblemGenerator;

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
        ThreeSatProblemGenerator tspg = new ThreeSatProblemGenerator(128,10);// = new ThreeSatProblemGenerator(128,500);
        HyperHeuristic dummy;
        if(Communication.getCommunication().getRank() == 0) {
            ThreeSatProblem tsp = tspg.generateProblem();
            dummy = new HyperHeuristic(tsp);
        }
        else {
            dummy = new HyperHeuristic(tspg);
        }
        }
        catch(Exception e) {
            Communication.Log(e.toString());
            e.printStackTrace();
        }
        Communication.finalizeCommunication();
    }
}
