package parallelhyflex.experiencestorage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;
import parallelhyflex.experiencestorage.evaluators.NormalEvaluatedHypothesis;
import parallelhyflex.experiencestorage.evaluators.NormalEvaluatedHypothesisComparator1;
import parallelhyflex.problemdependent.constraints.EnforceableConstraint;
import parallelhyflex.problemdependent.experience.ExperienceBase;
import parallelhyflex.problemdependent.problem.Problem;
import parallelhyflex.problemdependent.solution.Solution;
import parallelhyflex.utils.ProbabilityUtils;

/**
 *
 * @param <TSolution>
 * @param <TProblem>
 * @param <THypothesis>
 * @author kommusoft
 */
public class NormalSetExperienceStore<TSolution extends Solution<TSolution>, TProblem extends Problem<TSolution>, THypothesis extends EnforceableConstraint<TSolution>> extends ExperienceBase<TSolution, TProblem, THypothesis> {

    private static final Logger LOG = Logger.getLogger(SetExperienceStore.class.getName());
    private final HashSet<NormalEvaluatedHypothesis<TSolution, THypothesis>> hypothesis = new HashSet<>();
    private final int hypothesisSize;
    private final int generationSize;
    private final InstanceHypothesisGenerator<TSolution, THypothesis> hypothesisGenerator;
    private final Comparator<NormalEvaluatedHypothesis> comparator;
    private double mineval = Double.POSITIVE_INFINITY;

    /**
     *
     * @param problem
     * @param hypothesisGenerator
     * @param comparator
     * @param hypothesisSize
     * @param generationSize
     */
    public NormalSetExperienceStore(TProblem problem, InstanceHypothesisGenerator<TSolution, THypothesis> hypothesisGenerator, Comparator<NormalEvaluatedHypothesis> comparator, int hypothesisSize, int generationSize) {
        super(problem);
        //this.setLock = new ReentrantReadWriteLock();
        this.hypothesisSize = hypothesisSize;
        this.hypothesisGenerator = hypothesisGenerator;
        this.generationSize = generationSize;
        this.comparator = comparator;
    }

    /**
     *
     * @param problem
     * @param hypothesisGenerator
     * @param hypothesisSize
     * @param generationSize
     */
    public NormalSetExperienceStore(TProblem problem, InstanceHypothesisGenerator<TSolution, THypothesis> hypothesisGenerator, int hypothesisSize, int generationSize) {
        this(problem, hypothesisGenerator, NormalEvaluatedHypothesisComparator1.getInstance(), hypothesisSize, generationSize);
    }

    /**
     *
     * @param problem
     * @param hypothesisGenerator
     * @param comparator
     */
    public NormalSetExperienceStore(TProblem problem, InstanceHypothesisGenerator<TSolution, THypothesis> hypothesisGenerator, Comparator<NormalEvaluatedHypothesis> comparator) {
        this(problem, hypothesisGenerator, comparator, 20, 5);
    }

    /**
     *
     * @param problem
     * @param hypothesisGenerator
     */
    public NormalSetExperienceStore(TProblem problem, InstanceHypothesisGenerator<TSolution, THypothesis> hypothesisGenerator) {
        this(problem, hypothesisGenerator, NormalEvaluatedHypothesisComparator1.getInstance(), 20, 5);
    }

    /**
     *
     * @param solution
     */
    @Override
    public void join(TSolution solution) {
        double eval = this.getProblem().getObjectiveFunction(0).evaluateSolution(solution);
        mineval = Math.min(mineval, eval);
        for (NormalEvaluatedHypothesis<TSolution, THypothesis> shi : getHypothesis()) {
            shi.evaluate(solution, eval);
        }
        if (getHypothesis().size() < this.getHypothesisSize()) {
            THypothesis hyp = this.getHypothesisGenerator().generate(solution);
            NormalEvaluatedHypothesis<TSolution, THypothesis> shi = new NormalEvaluatedHypothesis<>(hyp);
            shi.evaluate(solution, eval);
            this.getHypothesis().add(shi);
        }
    }

    /**
     *
     */
    @Override
    public void amnesia() {
        ArrayList<NormalEvaluatedHypothesis<TSolution, THypothesis>> items = getSortedHypothesisList();
        if (items.size() > 0) {
            int k = ProbabilityUtils.integerFromBenfordDistribution(items.size());
            getHypothesis().remove(items.get(k));
        }
    }

    /**
     *
     * @return
     */
    @Override
    public Collection<THypothesis> generateEnforceableConstraints() {
        ArrayList<THypothesis> generatedEcs = new ArrayList<>();
        ArrayList<NormalEvaluatedHypothesis<TSolution, THypothesis>> items = getSortedHypothesisList();
        int K = Math.min(getHypothesis().size(), this.getGenerationSize());
        if (K > 0) {
            for (int i = 0; i < K; i++) {
                int k = ProbabilityUtils.integerFromUniformDistribution(items.size() - i);
                generatedEcs.add(items.get(k).getHypothesis());
                getHypothesis().remove(items.get(k));
                items.set(k, items.get(items.size() - 1));
            }
        }
        return generatedEcs;
    }

    /**
     * @return the hypothesis
     */
    public Set<NormalEvaluatedHypothesis<TSolution, THypothesis>> getHypothesis() {
        return hypothesis;
    }

    /**
     * @return the hypothesisSize
     */
    public int getHypothesisSize() {
        return hypothesisSize;
    }

    /**
     * @return the exhaustSize
     */
    public int getGenerationSize() {
        return generationSize;
    }

    /**
     * @return the hypothesisGenerator
     */
    public InstanceHypothesisGenerator<TSolution, THypothesis> getHypothesisGenerator() {
        return hypothesisGenerator;
    }

    private ArrayList<NormalEvaluatedHypothesis<TSolution, THypothesis>> getUnsortedHypothesisList() {
        ArrayList<NormalEvaluatedHypothesis<TSolution, THypothesis>> items = new ArrayList<>(this.getHypothesis().size());
        for (NormalEvaluatedHypothesis<TSolution, THypothesis> shi : this.getHypothesis()) {
            items.add(shi);
        }
        return items;
    }

    private ArrayList<NormalEvaluatedHypothesis<TSolution, THypothesis>> getSortedHypothesisList() {
        ArrayList<NormalEvaluatedHypothesis<TSolution, THypothesis>> items = this.getUnsortedHypothesisList();
        Collections.sort(items, this.getComparator());
        return items;
    }

    /**
     * @return the comparator
     */
    public Comparator<NormalEvaluatedHypothesis> getComparator() {
        return comparator;
    }
}