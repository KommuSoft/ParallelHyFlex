package parallelhyflex;

import java.io.IOException;
import parallelhyflex.algebra.CloningGenerator;
import parallelhyflex.algebra.Generator;
import parallelhyflex.communication.Communication;
import parallelhyflex.hyperheuristics.adaphh.AdapHH;
import parallelhyflex.problemdependent.searchspace.negotation.TwoSetWriteableSearchSpaceNegotiator;
import parallelhyflex.problems.threesat.constraints.ThreeSatWritableEnforceableConstraint1;
import parallelhyflex.problems.threesat.constraints.ThreeSatWritableEnforceableConstraintGenerator1;
import parallelhyflex.problems.threesat.experience.ThreeSatExperience;
import parallelhyflex.problems.threesat.problem.ThreeSatProblem;
import parallelhyflex.problems.threesat.problem.ThreeSatProblemGenerator;
import parallelhyflex.problems.threesat.solution.ThreeSatSolution;
import parallelhyflex.problems.threesat.solution.ThreeSatSolutionGenerator;

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
        int nVariables = 10_000;
        int nClauses = 42_000;
        try {
            ThreeSatProblemGenerator tspg = new ThreeSatProblemGenerator(nVariables, nClauses);// = new ThreeSatProblemGenerator(128,500);
            HyperHeuristic dummy;
            long timespan = 10_000;//ten seconds
            //long timespan = 360000;//six minutes
            //long timespan = 7200000;//two hours
            if (Communication.getCommunication().getRank() == 0) {
                ThreeSatProblem tsp = tspg.generateProblem();
                dummy = new AdapHH(tsp, timespan, new CloningGenerator<>(new ThreeSatExperience(null)), negoGenerator(), 1_000, new ThreeSatSolutionGenerator(null));
            } else {
                dummy = new AdapHH(tspg, timespan, new CloningGenerator<>(new ThreeSatExperience(null)), negoGenerator(), 1_000, new ThreeSatSolutionGenerator(null));
            }
            dummy.startExecute();
        } catch (ProtocolException | IOException e) {
            Communication.log(e.toString());
            e.printStackTrace();
        }
        Communication.finalizeCommunication();
    }

    private static Generator<ThreeSatProblem, TwoSetWriteableSearchSpaceNegotiator<ThreeSatSolution, ThreeSatProblem, ThreeSatWritableEnforceableConstraint1, ThreeSatWritableEnforceableConstraintGenerator1>> negoGenerator() {
        return new CloningGenerator(new TwoSetWriteableSearchSpaceNegotiator<ThreeSatSolution, ThreeSatProblem, ThreeSatWritableEnforceableConstraint1, ThreeSatWritableEnforceableConstraintGenerator1>(new ThreeSatWritableEnforceableConstraintGenerator1(null)));
    }
}
