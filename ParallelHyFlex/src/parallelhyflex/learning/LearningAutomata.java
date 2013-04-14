package parallelhyflex.learning;

import com.google.common.collect.HashBiMap;
import java.util.Collection;
import parallelhyflex.algebra.Generator;
import parallelhyflex.algebra.Procedure;
import parallelhyflex.algebra.tuples.Tuple3;
import parallelhyflex.learning.learningschemes.LinearLearningScheme;
import parallelhyflex.learning.selectors.RouletteWheelSelector;
import parallelhyflex.utils.Utils;

/**
 *
 * @author kommusoft
 */
public class LearningAutomata<TAction> {

    private final HashBiMap<TAction, Integer> actions;
    private final double[] probabilities;
    private final Generator<double[],Integer> selector;
    private final Procedure<Tuple3<double[], Integer, Double>> learningScheme;

    public LearningAutomata(Collection<TAction> actions, Generator<double[],Integer> selector, Procedure<Tuple3<double[], Integer, Double>> learningScheme) {
        this.actions = Utils.generateIndexHashBiMapper(actions);
        this.probabilities = new double[this.actions.size()];
        double p = 1.0d/this.probabilities.length;
        for(int i = 0; i < this.probabilities.length; i++) {
            this.probabilities[i] = p;
        }
        this.selector = selector;
        this.learningScheme = learningScheme;
    }
    
    public LearningAutomata(Collection<TAction> actions, Procedure<Tuple3<double[], Integer, Double>> learningScheme) {
        this(actions,new RouletteWheelSelector(),learningScheme);
    }
    
    public LearningAutomata(Collection<TAction> actions, double lambda1, double lambda2) {
        this(actions,new LinearLearningScheme(lambda1,lambda2));
    }

    public TAction getAction() {
        return null;
    }

    public void update(TAction action, double reward) {
        if (this.actions.containsKey(action)) {
            int index = this.actions.get(action);
            this.learningScheme.execute(new Tuple3<>(this.probabilities,index,reward));
        }
    }
}
