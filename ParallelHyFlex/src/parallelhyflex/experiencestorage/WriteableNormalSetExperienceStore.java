package parallelhyflex.experiencestorage;

import java.util.Comparator;
import java.util.logging.Logger;
import parallelhyflex.experiencestorage.evaluators.NormalEvaluatedHypothesis;
import parallelhyflex.experiencestorage.evaluators.NormalEvaluatedHypothesisComparator1;
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
public class WriteableNormalSetExperienceStore<TSolution extends Solution<TSolution>, TProblem extends Problem<TSolution>, THypothesis extends WriteableEnforceableConstraint<TSolution>> extends NormalSetExperienceStore<TSolution, TProblem, THypothesis> implements WriteableExperience<TSolution, THypothesis> {

    private static final Logger LOG = Logger.getLogger(WriteableNormalSetExperienceStore.class.getName());

    /**
     *
     * @param problem
     * @param hypothesisGenerator
     * @param comparator
     * @param hypothesisSize
     * @param generationSize
     */
    public WriteableNormalSetExperienceStore(TProblem problem, InstanceHypothesisGenerator<TSolution, THypothesis> hypothesisGenerator, Comparator<NormalEvaluatedHypothesis> comparator, int hypothesisSize, int generationSize) {
        super(problem, hypothesisGenerator, comparator, hypothesisSize, generationSize);
    }

    /**
     *
     * @param problem
     * @param hypothesisGenerator
     * @param hypothesisSize
     * @param generationSize
     */
    public WriteableNormalSetExperienceStore(TProblem problem, InstanceHypothesisGenerator<TSolution, THypothesis> hypothesisGenerator, int hypothesisSize, int generationSize) {
        super(problem, hypothesisGenerator, hypothesisSize, generationSize);
    }

    /**
     *
     * @param problem
     * @param hypothesisGenerator
     * @param comparator
     */
    public WriteableNormalSetExperienceStore(TProblem problem, InstanceHypothesisGenerator<TSolution, THypothesis> hypothesisGenerator, Comparator<NormalEvaluatedHypothesis> comparator) {
        super(problem, hypothesisGenerator, comparator);
    }

    /**
     *
     * @param problem
     * @param hypothesisGenerator
     */
    public WriteableNormalSetExperienceStore(TProblem problem, InstanceHypothesisGenerator<TSolution, THypothesis> hypothesisGenerator) {
        super(problem, hypothesisGenerator, NormalEvaluatedHypothesisComparator1.getInstance());
    }
}
