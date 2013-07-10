package parallelhyflex.experiencestorage;

import java.util.Comparator;
import java.util.logging.Logger;
import parallelhyflex.experiencestorage.evaluators.SetHypothesisItem;
import parallelhyflex.experiencestorage.evaluators.SetHypothesisItemComparator1;
import parallelhyflex.problemdependent.constraints.WriteableEnforceableConstraint;
import parallelhyflex.problemdependent.experience.WriteableExperience;
import parallelhyflex.problemdependent.problem.Problem;
import parallelhyflex.problemdependent.solution.Solution;

/**
 *
 * @param <TSolution>
 * @param <TProblem>
 * @param <THypothesis>
 * @author kommusoft
 */
public class WriteableSetExperienceStore<TSolution extends Solution<TSolution>, TProblem extends Problem<TSolution>, THypothesis extends WriteableEnforceableConstraint<TSolution>> extends SetExperienceStore<TSolution, TProblem, THypothesis> implements WriteableExperience<TSolution, THypothesis> {

    private static final Logger LOG = Logger.getLogger(WriteableSetExperienceStore.class.getName());

    /**
     *
     * @param problem
     * @param hypothesisGenerator
     * @param comparator
     * @param historySize
     * @param hypothesisSize
     * @param generationSize
     */
    public WriteableSetExperienceStore(TProblem problem, InstanceHypothesisGenerator<TSolution, THypothesis> hypothesisGenerator, Comparator<SetHypothesisItem> comparator, int historySize, int hypothesisSize, int generationSize) {
        super(problem, hypothesisGenerator, comparator, historySize, hypothesisSize, generationSize);
    }

    /**
     *
     * @param problem
     * @param hypothesisGenerator
     * @param historySize
     * @param hypothesisSize
     * @param generationSize
     */
    public WriteableSetExperienceStore(TProblem problem, InstanceHypothesisGenerator<TSolution, THypothesis> hypothesisGenerator, int historySize, int hypothesisSize, int generationSize) {
        super(problem, hypothesisGenerator, historySize, hypothesisSize, generationSize);
    }

    /**
     *
     * @param problem
     * @param hypothesisGenerator
     * @param comparator
     */
    public WriteableSetExperienceStore(TProblem problem, InstanceHypothesisGenerator<TSolution, THypothesis> hypothesisGenerator, Comparator<SetHypothesisItem> comparator) {
        super(problem, hypothesisGenerator, comparator);
    }

    /**
     *
     * @param problem
     * @param hypothesisGenerator
     */
    public WriteableSetExperienceStore(TProblem problem, InstanceHypothesisGenerator<TSolution, THypothesis> hypothesisGenerator) {
        super(problem, hypothesisGenerator, SetHypothesisItemComparator1.getInstance());
    }
}
