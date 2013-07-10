package parallelhyflex;

import parallelhyflex.problems.frequencyassignment.constraint.FrequencyAssignmentWritableEnforceableConstraintGenerator1;
import java.io.IOException;
import parallelhyflex.algebra.CloningGenerator;
import parallelhyflex.algebra.Generator;
import parallelhyflex.communication.Communication;
import parallelhyflex.hyperheuristics.adaphh.AdapHH;
import parallelhyflex.problemdependent.searchspace.negotation.TwoSetWriteableSearchSpaceNegotiator;
import parallelhyflex.problems.frequencyassignment.experience.FrequencyAssignmentExperience;
import parallelhyflex.problems.frequencyassignment.constraint.FrequencyAssignmentWritableEnforceableConstraint1;
import parallelhyflex.problems.frequencyassignment.problem.FrequencyAssignmentProblem;
import parallelhyflex.problems.frequencyassignment.problem.FrequencyAssignmentProblemGenerator;
import parallelhyflex.problems.frequencyassignment.solution.FrequencyAssignmentSolution;
import parallelhyflex.problems.frequencyassignment.solution.FrequencyAssignmentSolutionGenerator;

/**
 *
 * @author kommusoft
 */
public class FrequencyAssignmentProblemMain {

    public static void main(String[] args) throws IOException {
        FrequencyAssignmentProblemGenerator tspg = new FrequencyAssignmentProblemGenerator();

        Communication.initializeCommunication(args);
        try {
            // = new FrequencyAssignmentProblemGenerator(128,500);
            HyperHeuristic dummy;
            long timespan = 10_000;//ten seconds
            //long timespan = 360000;//six minutes
            //long timespan = 7200000;//two hours
            if (Communication.getCommunication().getRank() == 0) {
                FrequencyAssignmentProblem tsp = tspg.generateProblem();
                dummy = new AdapHH(tsp, timespan, new CloningGenerator<>(new FrequencyAssignmentExperience(null)), negoGenerator(), 1_000, 1_000, new FrequencyAssignmentSolutionGenerator(null));
            } else {
                dummy = new AdapHH(tspg, timespan, new CloningGenerator<>(new FrequencyAssignmentExperience(null)), negoGenerator(), 1_000, 1_000, new FrequencyAssignmentSolutionGenerator(null));
            }
            dummy.startExecute();
        } catch (ProtocolException | IOException e) {
            Communication.log(e.toString());
            e.printStackTrace();
        }
        Communication.finalizeCommunication();
    }

    private static Generator<FrequencyAssignmentProblem, TwoSetWriteableSearchSpaceNegotiator<FrequencyAssignmentSolution, FrequencyAssignmentProblem, FrequencyAssignmentWritableEnforceableConstraint1, FrequencyAssignmentWritableEnforceableConstraintGenerator1>> negoGenerator() {
        return new CloningGenerator(new TwoSetWriteableSearchSpaceNegotiator<FrequencyAssignmentSolution, FrequencyAssignmentProblem, FrequencyAssignmentWritableEnforceableConstraint1, FrequencyAssignmentWritableEnforceableConstraintGenerator1>(new FrequencyAssignmentWritableEnforceableConstraintGenerator1(null)));
    }
}
