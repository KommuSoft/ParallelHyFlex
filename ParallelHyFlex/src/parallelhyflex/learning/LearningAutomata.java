package parallelhyflex.learning;

import com.google.common.collect.HashBiMap;
import java.util.Collection;
import parallelhyflex.algebra.Procedure;
import parallelhyflex.algebra.tuples.Tuple3;
import parallelhyflex.utils.Utils;

/**
 *
 * @author kommusoft
 */
public class LearningAutomata<TAction> {

    private final HashBiMap<TAction, Integer> actions;
    private final double[] probabilities;
    private final Procedure<Tuple3<double[], Integer, Double>> learningScheme;

    public LearningAutomata(Collection<TAction> actions, Procedure<Tuple3<double[], Integer, Double>> learningScheme) {
        this.actions = Utils.generateIndexHashBiMapper(actions);
        this.probabilities = new double[this.actions.size()];
        this.learningScheme = learningScheme;
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
