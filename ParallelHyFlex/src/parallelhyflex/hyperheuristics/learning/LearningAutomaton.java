package parallelhyflex.hyperheuristics.learning;

import com.google.common.collect.HashBiMap;
import parallelhyflex.algebra.Generator;
import parallelhyflex.algebra.Procedure;
import parallelhyflex.algebra.tuples.Tuple3;
import parallelhyflex.hyperheuristics.learning.learningschemes.LinearLearningScheme;
import parallelhyflex.hyperheuristics.learning.selectors.RouletteWheelSelector;
import parallelhyflex.utils.Utils;

/**
 *
 * @author kommusoft
 */
public class LearningAutomaton<TAction> {

    private final HashBiMap<TAction, Integer> actions;
    private double[] probabilities;
    private final Generator<double[], Integer> selector;
    private final Procedure<Tuple3<double[], Integer, Double>> learningScheme;

    /**
     *
     * @param selector
     * @param learningScheme
     */
    public LearningAutomaton(Generator<double[], Integer> selector, Procedure<Tuple3<double[], Integer, Double>> learningScheme) {
        this.probabilities = new double[0];
        this.actions = HashBiMap.create();
        this.selector = selector;
        this.learningScheme = learningScheme;
        this.reset();
    }

    /**
     *
     * @param selector
     * @param learningScheme
     * @param actions
     */
    public LearningAutomaton(Generator<double[], Integer> selector, Procedure<Tuple3<double[], Integer, Double>> learningScheme, Iterable<TAction> actions) {
        this(selector, learningScheme);
        this.reset(actions);
    }

    /**
     *
     * @param learningScheme
     */
    public LearningAutomaton(Procedure<Tuple3<double[], Integer, Double>> learningScheme) {
        this(new RouletteWheelSelector(), learningScheme);
    }

    /**
     *
     * @param learningScheme
     * @param actions
     */
    public LearningAutomaton(Procedure<Tuple3<double[], Integer, Double>> learningScheme, Iterable<TAction> actions) {
        this(new RouletteWheelSelector(), learningScheme);
        this.reset(actions);
    }

    /**
     *
     * @param lambda1
     * @param lambda2
     */
    public LearningAutomaton(double lambda1, double lambda2) {
        this(new LinearLearningScheme(lambda1, lambda2));
    }

    /**
     *
     * @param lambda1
     * @param lambda2
     * @param actions
     */
    public LearningAutomaton(double lambda1, double lambda2, Iterable<TAction> actions) {
        this(new LinearLearningScheme(lambda1, lambda2));
        this.reset(actions);
    }
    
    /**
     *
     * @param lambda
     */
    public LearningAutomaton(double lambda) {
        this(lambda,lambda);
    }

    /**
     *
     * @param lambda
     * @param actions
     */
    public LearningAutomaton(double lambda, Iterable<TAction> actions) {
        this(lambda);
        this.reset(actions);
    }

    /**
     *
     */
    public LearningAutomaton() {
        this(1.0d);
    }
    
    /**
     *
     * @param actions
     */
    public LearningAutomaton(Iterable<TAction> actions) {
        this();
        this.reset(actions);
    }

    /**
     *
     */
    public void reset() {
        double p = 1.0d / this.probabilities.length;
        for (int i = 0; i < this.probabilities.length; i++) {
            this.probabilities[i] = p;
        }
    }

    /**
     *
     * @param actions
     */
    public void reset(Iterable<TAction> actions) {
        this.actions.clear();
        Utils.generateIndexMapper(actions, this.actions);
        this.probabilities = new double[this.actions.size()];
        this.reset();
    }

    /**
     *
     * @return
     */
    public TAction getAction() {
        int index = this.selector.generate(probabilities);
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
            this.learningScheme.execute(new Tuple3<>(this.probabilities, index, reward));
        }
    }
}
