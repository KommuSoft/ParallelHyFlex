package parallelhyflex.hyperheuristics.learning;

import com.google.common.collect.HashBiMap;
import java.util.logging.Logger;
import parallelhyflex.algebra.Generator;
import parallelhyflex.algebra.Procedure;
import parallelhyflex.algebra.probability.NormalizedProbabilityVector;
import parallelhyflex.algebra.probability.SingleNormalizedProbabilityVector;
import parallelhyflex.algebra.tuples.Tuple3;
import parallelhyflex.hyperheuristics.learning.learningschemes.LinearLearningScheme;
import parallelhyflex.hyperheuristics.learning.selectors.RouletteWheelSelector;
import parallelhyflex.utils.Utils;

/**
 *
 * @param <TAction>
 * @author kommusoft
 */
public class LearningAutomaton<TAction> {

    private static final Logger LOG = Logger.getLogger(LearningAutomaton.class.getName());
    private final HashBiMap<TAction, Integer> actions;
    private final Generator<NormalizedProbabilityVector, Integer> selector;
    private final Procedure<Tuple3<NormalizedProbabilityVector, Integer, Double>> learningScheme;
    private final NormalizedProbabilityVector vector;

    /**
     *
     * @param vector
     * @param selector
     * @param learningScheme
     */
    public LearningAutomaton(NormalizedProbabilityVector vector, Generator<NormalizedProbabilityVector, Integer> selector, Procedure<Tuple3<NormalizedProbabilityVector, Integer, Double>> learningScheme) {
        this.actions = HashBiMap.create();
        this.vector = vector;
        this.selector = selector;
        this.learningScheme = learningScheme;
        this.reset();
    }

    /**
     *
     * @param vector
     * @param selector
     * @param learningScheme
     * @param actions
     */
    public LearningAutomaton(NormalizedProbabilityVector vector, Generator<NormalizedProbabilityVector, Integer> selector, Procedure<Tuple3<NormalizedProbabilityVector, Integer, Double>> learningScheme, Iterable<TAction> actions) {
        this(vector, selector, learningScheme);
        this.reset(actions);
    }

    /**
     *
     * @param vector
     * @param learningScheme
     */
    public LearningAutomaton(NormalizedProbabilityVector vector, Procedure<Tuple3<NormalizedProbabilityVector, Integer, Double>> learningScheme) {
        this(vector, new RouletteWheelSelector(), learningScheme);
    }

    /**
     *
     * @param vector
     * @param learningScheme
     * @param actions
     */
    public LearningAutomaton(NormalizedProbabilityVector vector, Procedure<Tuple3<NormalizedProbabilityVector, Integer, Double>> learningScheme, Iterable<TAction> actions) {
        this(vector, new RouletteWheelSelector(), learningScheme);
        this.reset(actions);
    }

    /**
     *
     * @param vector
     * @param lambda1
     * @param lambda2
     */
    public LearningAutomaton(NormalizedProbabilityVector vector, double lambda1, double lambda2) {
        this(vector, new LinearLearningScheme(lambda1, lambda2));
    }

    /**
     *
     * @param vector
     * @param lambda1
     * @param lambda2
     * @param actions
     */
    public LearningAutomaton(NormalizedProbabilityVector vector, double lambda1, double lambda2, Iterable<TAction> actions) {
        this(vector, new LinearLearningScheme(lambda1, lambda2));
        this.reset(actions);
    }

    /**
     *
     * @param vector
     * @param lambda
     */
    public LearningAutomaton(NormalizedProbabilityVector vector, double lambda) {
        this(vector, lambda, lambda);
    }

    /**
     *
     * @param vector
     * @param lambda
     * @param actions
     */
    public LearningAutomaton(NormalizedProbabilityVector vector, double lambda, Iterable<TAction> actions) {
        this(vector, lambda);
        this.reset(actions);
    }

    /**
     *
     * @param vector
     */
    public LearningAutomaton(NormalizedProbabilityVector vector) {
        this(vector, 1.0d);
    }

    /**
     *
     * @param vector
     * @param actions
     */
    public LearningAutomaton(NormalizedProbabilityVector vector, Iterable<TAction> actions) {
        this(vector);
        this.reset(actions);
    }

    /**
     *
     * @param selector
     * @param learningScheme
     */
    public LearningAutomaton(Generator<NormalizedProbabilityVector, Integer> selector, Procedure<Tuple3<NormalizedProbabilityVector, Integer, Double>> learningScheme) {
        this(new SingleNormalizedProbabilityVector(), selector, learningScheme);
    }

    /**
     *
     * @param selector
     * @param learningScheme
     * @param actions
     */
    public LearningAutomaton(Generator<NormalizedProbabilityVector, Integer> selector, Procedure<Tuple3<NormalizedProbabilityVector, Integer, Double>> learningScheme, Iterable<TAction> actions) {
        this(new SingleNormalizedProbabilityVector(), selector, learningScheme, actions);
    }

    /**
     *
     * @param learningScheme
     */
    public LearningAutomaton(Procedure<Tuple3<NormalizedProbabilityVector, Integer, Double>> learningScheme) {
        this(new SingleNormalizedProbabilityVector(), learningScheme);
    }

    /**
     *
     * @param learningScheme
     * @param actions
     */
    public LearningAutomaton(Procedure<Tuple3<NormalizedProbabilityVector, Integer, Double>> learningScheme, Iterable<TAction> actions) {
        this(new SingleNormalizedProbabilityVector(), learningScheme, actions);
    }

    /**
     *
     * @param lambda1
     * @param lambda2
     */
    public LearningAutomaton(double lambda1, double lambda2) {
        this(new SingleNormalizedProbabilityVector(), lambda1, lambda2);
    }

    /**
     *
     * @param lambda1
     * @param lambda2
     * @param actions
     */
    public LearningAutomaton(double lambda1, double lambda2, Iterable<TAction> actions) {
        this(new SingleNormalizedProbabilityVector(), lambda1, lambda2, actions);
    }

    /**
     *
     * @param lambda
     */
    public LearningAutomaton(double lambda) {
        this(new SingleNormalizedProbabilityVector(), lambda, lambda);
    }

    /**
     *
     * @param lambda
     * @param actions
     */
    public LearningAutomaton(double lambda, Iterable<TAction> actions) {
        this(new SingleNormalizedProbabilityVector(), lambda, actions);
    }

    /**
     *
     */
    public LearningAutomaton() {
        this(new SingleNormalizedProbabilityVector());
    }

    /**
     *
     * @param actions
     */
    public LearningAutomaton(Iterable<TAction> actions) {
        this(new SingleNormalizedProbabilityVector(), actions);
    }

    /**
     *
     */
    public void reset() {
        this.vector.reset();
    }

    /**
     *
     * @param actions
     */
    public void reset(Iterable<TAction> actions) {
        this.actions.clear();
        Utils.generateIndexMapper(actions, this.actions);
        this.vector.setSize(this.actions.size());
    }

    /**
     *
     * @return
     */
    public TAction getAction() {
        int index = this.selector.generate(this.vector);
        return this.actions.inverse().get(index);
    }

    /**
     *
     * @param action
     * @param reward
     */
    public void update(TAction action, double reward) {
        if (this.actions.containsKey(action)) {
            int index = this.actions.get(action);
            this.learningScheme.execute(new Tuple3<>(this.vector, index, reward));
        }
    }

    @Override
    public String toString() {
        return String.format("LA{%s}",this.vector);
    }
}
