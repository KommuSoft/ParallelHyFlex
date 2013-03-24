package parallelhyflex;

import java.io.IOException;
import parallelhyflex.algebra.CloningGenerator;
import parallelhyflex.communication.Communication;
import parallelhyflex.problems.threesat.experience.ThreeSatExperience;
import parallelhyflex.problems.threesat.ThreeSatProblem;
import parallelhyflex.problems.threesat.ThreeSatProblemGenerator;
import parallelhyflex.problems.threesat.ThreeSatSolutionGenerator;

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
            ThreeSatExperience exp = new ThreeSatExperience(null);
            ThreeSatSolutionGenerator tssg = new ThreeSatSolutionGenerator(null);
            CloningGenerator<ThreeSatProblem, ThreeSatExperience> generator = new CloningGenerator<ThreeSatProblem, ThreeSatExperience>(exp);
            if (Communication.getCommunication().getRank() == 0) {
                ThreeSatProblem tsp = tspg.generateProblem();
                dummy = new HyperHeuristic(tsp, generator, tssg);
            } else {
                dummy = new HyperHeuristic(tspg, generator, tssg);
            }
        } catch (Exception e) {
            Communication.Log(e.toString());
            e.printStackTrace();
        }
        Communication.finalizeCommunication();
    }
}
