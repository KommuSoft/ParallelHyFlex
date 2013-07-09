package parallelhyflex.experiencestorage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import parallelhyflex.problemdependent.constraints.EnforceableConstraint;
import parallelhyflex.problemdependent.experience.ExperienceBase;
import parallelhyflex.problemdependent.problem.Problem;
import parallelhyflex.problemdependent.solution.Solution;
import parallelhyflex.utils.ProbabilityUtils;

/**
 *
 * @author kommusoft
 */
public class SetExperienceStore<TSolution extends Solution<TSolution>, TProblem extends Problem<TSolution>, THypothesis extends EnforceableConstraint<TSolution>> extends ExperienceBase<TSolution, TProblem, THypothesis> {

    private final HashSet<SetHypothesisItem<TSolution, THypothesis>> hypothesis = new HashSet<>();
    //private final ReentrantReadWriteLock setLock;
    private final int historySize;
    private final int hypothesisSize;
    private final int generationSize;
    private final InstanceHypothesisGenerator<TSolution, THypothesis> hypothesisGenerator;
    private final Comparator<SetHypothesisItem> comparator;
    private double mineval = Double.POSITIVE_INFINITY;

    /**
     *
     * @param problem
     * @param hypothesisGenerator
     * @param comparator
     * @param historySize
     * @param hypothesisSize
     * @param generationSize
     */
    public SetExperienceStore(TProblem problem, InstanceHypothesisGenerator<TSolution, THypothesis> hypothesisGenerator, Comparator<SetHypothesisItem> comparator, int historySize, int hypothesisSize, int generationSize) {
        super(problem);
        //this.setLock = new ReentrantReadWriteLock();
        this.historySize = historySize;
        this.hypothesisSize = hypothesisSize;
        this.hypothesisGenerator = hypothesisGenerator;
        this.generationSize = generationSize;
        this.comparator = comparator;
    }

    /**
     *
     * @param problem
     * @param hypothesisGenerator
     * @param historySize
     * @param hypothesisSize
     * @param generationSize
     */
    public SetExperienceStore(TProblem problem, InstanceHypothesisGenerator<TSolution, THypothesis> hypothesisGenerator, int historySize, int hypothesisSize, int generationSize) {
        this(problem, hypothesisGenerator, SetHypothesisItemComparator1.getInstance(), historySize, hypothesisSize, generationSize);
    }

    /**
     *
     * @param problem
     * @param hypothesisGenerator
     * @param comparator
     */
    public SetExperienceStore(TProblem problem, InstanceHypothesisGenerator<TSolution, THypothesis> hypothesisGenerator, Comparator<SetHypothesisItem> comparator) {
        this(problem, hypothesisGenerator, comparator, 5, 20, 5);
    }

    /**
     *
     * @param problem
     * @param hypothesisGenerator
     */
    public SetExperienceStore(TProblem problem, InstanceHypothesisGenerator<TSolution, THypothesis> hypothesisGenerator) {
        this(problem, hypothesisGenerator, SetHypothesisItemComparator1.getInstance(), 5, 20, 5);
    }

    /**
     *
     * @param solution
     */
    @Override
    public void join(TSolution solution) {
        double eval = this.getProblem().getObjectiveFunction(0).evaluateSolution(solution);
        mineval = Math.min(mineval, eval);
        for (SetHypothesisItem<TSolution, THypothesis> shi : getHypothesis()) {
            shi.checkInstance(solution, eval);
        }
        if (getHypothesis().size() < this.getHypothesisSize()) {
            THypothesis hyp = this.getHypothesisGenerator().generate(solution);
            SetHypothesisItem<TSolution, THypothesis> shi = new SetHypothesisItem<>(hyp, this.getHistorySize());
            shi.checkInstance(solution, eval);
            this.getHypothesis().add(shi);
        }
    }

    /**
     *
     */
    @Override
    public void amnesia() {
        ArrayList<SetHypothesisItem<TSolution, THypothesis>> items = getSortedHypothesisList();
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
        ArrayList<SetHypothesisItem<TSolution, THypothesis>> items = getSortedHypothesisList();
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
    public Set<SetHypothesisItem<TSolution, THypothesis>> getHypothesis() {
        return hypothesis;
    }

    /**
     * @return the historySize
     */
    public int getHistorySize() {
        return historySize;
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

    private ArrayList<SetHypothesisItem<TSolution, THypothesis>> getUnsortedHypothesisList() {
        ArrayList<SetHypothesisItem<TSolution, THypothesis>> items = new ArrayList<>(this.getHypothesis().size());
        for (SetHypothesisItem<TSolution, THypothesis> shi : this.getHypothesis()) {
            items.add(shi);
        }
        return items;
    }

    private ArrayList<SetHypothesisItem<TSolution, THypothesis>> getSortedHypothesisList() {
        ArrayList<SetHypothesisItem<TSolution, THypothesis>> items = this.getUnsortedHypothesisList();
        Collections.sort(items, this.getComparator());
        return items;
    }

    /**
     * @return the comparator
     */
    public Comparator<SetHypothesisItem> getComparator() {
        return comparator;
    }
}
