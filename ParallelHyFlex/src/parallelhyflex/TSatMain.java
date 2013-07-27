package parallelhyflex;

import java.io.IOException;
import java.util.logging.Logger;
import parallelhyflex.algebra.CloningGenerator;
import parallelhyflex.algebra.Generator;
import parallelhyflex.communication.Communication;
import parallelhyflex.config.ConfigReader;
import parallelhyflex.hyperheuristics.paradaphh.ParAdapHH;
import parallelhyflex.problemdependent.searchspace.negotation.TwoSetWriteableSearchSpaceNegotiator;
import parallelhyflex.problems.threesat.constraints.ThreeSatWriteableEnforceableConstraint1;
import parallelhyflex.problems.threesat.constraints.ThreeSatWriteableEnforceableConstraintGenerator1;
import parallelhyflex.problems.threesat.experience.ThreeSatNormalExperience;
import parallelhyflex.problems.threesat.problem.ThreeSatProblem;
import parallelhyflex.problems.threesat.problem.ThreeSatProblemGenerator;
import parallelhyflex.problems.threesat.solution.ThreeSatSolution;
import parallelhyflex.problems.threesat.solution.ThreeSatSolutionGenerator;

//-jar /home/kommusoft/Libraries/mpj-v0_38/lib/starter.jar  -np 2
/**
 *
 * @author kommusoft
 */
public class TSatMain {

    private static final Logger LOG = Logger.getLogger(TSatMain.class.getName());

    /**
     * @param args the command line arguments
     * @throws ProtocolException
     * @throws IOException
     */
    public static void main(String[] args) throws ProtocolException, IOException {

        /*for (Entry<String, String> entry : System.getenv().entrySet()) {
         System.out.println(entry);
         }*/

        Communication.initializeCommunication(args);

        int nVariables = 10_000;
        int nClauses = 42_000;

        try {
            ThreeSatProblemGenerator tspg = new ThreeSatProblemGenerator(nVariables, nClauses);// = new ThreeSatProblemGenerator(128,500);
            ParAdapHH dummy;
            long timespan = 30_000;//ten seconds
            //long timespan = 360000;//six minutes
            //long timespan = 7200000;//two hours
            ThreeSatProblem tsp;
            if (args.length > 4 && args[4] != null && !args[4].isEmpty()) {
                ConfigReader.getInstance().readFromFile(args[4]);
            }
            if (Communication.getCommunication().getRank() == 0) {
                if (args.length <= 3 || args[3] == null || args[3].isEmpty()) {
                    tsp = tspg.generateProblem();
                } else {
                    tsp = tspg.readFromFile(args[3]);
                }
                dummy = new ParAdapHH(tsp, timespan, new CloningGenerator<>(new ThreeSatNormalExperience(null)), negoGenerator(), 1_000, 1_000, new ThreeSatSolutionGenerator(null));
            } else {
                dummy = new ParAdapHH(tspg, timespan, new CloningGenerator<>(new ThreeSatNormalExperience(null)), negoGenerator(), 1_000, 1_000, new ThreeSatSolutionGenerator(null));
            }
            dummy.startExecute();
        } catch (ProtocolException | IOException e) {
            Communication.log(e);
            e.printStackTrace();
        }
        Communication.finalizeCommunication();
    }

    private static Generator<ThreeSatProblem, TwoSetWriteableSearchSpaceNegotiator<ThreeSatSolution, ThreeSatProblem, ThreeSatWriteableEnforceableConstraint1, ThreeSatWriteableEnforceableConstraintGenerator1>> negoGenerator() {
        return new CloningGenerator(new TwoSetWriteableSearchSpaceNegotiator<ThreeSatSolution, ThreeSatProblem, ThreeSatWriteableEnforceableConstraint1, ThreeSatWriteableEnforceableConstraintGenerator1>(new ThreeSatWriteableEnforceableConstraintGenerator1(null)));
    }
}
