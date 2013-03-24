package parallelhyflex;

import java.io.IOException;
import parallelhyflex.communication.Communication;
import parallelhyflex.experiencestorage.SetExperienceStore;
import parallelhyflex.problemdependent.Experience;
import parallelhyflex.problems.threesat.ThreeSatProblem;
import parallelhyflex.problems.threesat.ThreeSatProblemGenerator;
import parallelhyflex.problems.threesat.ThreeSatSolution;
import parallelhyflex.problems.threesat.ThreeSatWritableEnforceableConstraint1;

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
        Experience exp = new SetExperienceStore<ThreeSatSolution,ThreeSatProblem,ThreeSatWritableEnforceableConstraint1>();
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
