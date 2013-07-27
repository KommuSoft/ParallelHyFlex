package parallelhyflex;

import java.io.IOException;
import parallelhyflex.algebra.CloningGenerator;
import parallelhyflex.algebra.Generator;
import parallelhyflex.communication.Communication;
import parallelhyflex.config.ConfigReader;
import parallelhyflex.hyperheuristics.paradaphh.ParAdapHH;
import parallelhyflex.problemdependent.searchspace.negotation.TwoSetWriteableSearchSpaceNegotiator;
import parallelhyflex.problems.frequencyassignment.constraint.FrequencyAssignmentWriteableEnforceableConstraint1;
import parallelhyflex.problems.frequencyassignment.constraint.FrequencyAssignmentWriteableEnforceableConstraintGenerator1;
import parallelhyflex.problems.frequencyassignment.experience.FrequencyAssignmentNormalExperience;
import parallelhyflex.problems.frequencyassignment.problem.FrequencyAssignmentProblem;
import parallelhyflex.problems.frequencyassignment.problem.FrequencyAssignmentProblemGenerator;
import parallelhyflex.problems.frequencyassignment.solution.FrequencyAssignmentSolution;
import parallelhyflex.problems.frequencyassignment.solution.FrequencyAssignmentSolutionGenerator;

/**
 *
 * @author kommusoft
 */
public class FAPMain {

    public static void main(String[] args) throws IOException {
        Communication.initializeCommunication(args);
        try {
            FrequencyAssignmentProblemGenerator tspg = new FrequencyAssignmentProblemGenerator();
            ParAdapHH dummy;
            long timespan = 360_000;//six minutes
            FrequencyAssignmentProblem tsp;
            if (args.length > 4 && args[4] != null && !args[4].isEmpty()) {
                ConfigReader.getInstance().readFromFile(args[4]);
            }
            if (Communication.getCommunication().getRank() == 0) {
                if (args.length <= 3 || args[3] == null || args[3].isEmpty()) {
                    tsp = tspg.generateProblem();
                } else {
                    tsp = tspg.readFromFile(args[3]);
                }
                dummy = new ParAdapHH(tsp, timespan, new CloningGenerator<>(new FrequencyAssignmentNormalExperience(null)), negoGenerator(), 1_000, 1_000, new FrequencyAssignmentSolutionGenerator(null));
            } else {
                dummy = new ParAdapHH(tspg, timespan, new CloningGenerator<>(new FrequencyAssignmentNormalExperience(null)), negoGenerator(), 1_000, 1_000, new FrequencyAssignmentSolutionGenerator(null));
            }
            dummy.startExecute();
        } catch (ProtocolException | IOException e) {
            Communication.log(e);
            e.printStackTrace();
        }
        Communication.finalizeCommunication();
    }

    private static Generator<FrequencyAssignmentProblem, TwoSetWriteableSearchSpaceNegotiator<FrequencyAssignmentSolution, FrequencyAssignmentProblem, FrequencyAssignmentWriteableEnforceableConstraint1, FrequencyAssignmentWriteableEnforceableConstraintGenerator1>> negoGenerator() {
        return new CloningGenerator(new TwoSetWriteableSearchSpaceNegotiator<FrequencyAssignmentSolution, FrequencyAssignmentProblem, FrequencyAssignmentWriteableEnforceableConstraint1, FrequencyAssignmentWriteableEnforceableConstraintGenerator1>(new FrequencyAssignmentWriteableEnforceableConstraintGenerator1(null)));
    }
}
