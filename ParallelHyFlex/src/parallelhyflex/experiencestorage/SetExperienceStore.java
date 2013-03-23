/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parallelhyflex.experiencestorage;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import parallelhyflex.problemdependent.Constraint;
import parallelhyflex.problemdependent.ExperienceBase;
import parallelhyflex.problemdependent.Problem;
import parallelhyflex.problemdependent.Solution;
import parallelhyflex.utils.ProbabilityUtils;

/**
 *
 * @author kommusoft
 */
public class SetExperienceStore<TSolution extends Solution<TSolution>,TProblem extends Problem<TSolution>, THypothesis extends Constraint<TSolution>> extends ExperienceBase<TSolution,TProblem> {
    
    private final HashSet<SetHypothesisItem<TSolution,THypothesis>> hypothesis = new HashSet<>();
    private final int historySize;
    private final int hypothesisSize;
    private final InstanceHypothesisGenerator<TSolution,THypothesis> hypothesisGenerator;
    private final Comparator<SetHypothesisItem> comparator;
    
    public SetExperienceStore (TProblem problem, int historySize, int hypothesisSize, InstanceHypothesisGenerator<TSolution,THypothesis> hypothesisGenerator, Comparator<SetHypothesisItem> comparator) {
        super(problem);
        this.historySize = historySize;
        this.hypothesisSize = hypothesisSize;
        this.hypothesisGenerator = hypothesisGenerator;
        this.comparator = comparator;
    }

    @Override
    public void join(TSolution solution) {
        double eval = this.getProblem().getObjectiveFunction(0).evaluateSolution(solution);
        for(SetHypothesisItem<TSolution,THypothesis> shi : hypothesis) {
            shi.checkInstance(solution,eval);
        }
        if(hypothesis.size() < this.hypothesisSize) {
            THypothesis hyp = this.hypothesisGenerator.generate(solution);
            SetHypothesisItem<TSolution,THypothesis> shi = new SetHypothesisItem<>(hyp,this.historySize);
            shi.checkInstance(solution, eval);
            this.hypothesis.add(shi);
        }
    }

    @Override
    public void amnesia() {
        if(hypothesis.size() > 0) {
            SetHypothesisItem[] items = new SetHypothesisItem[hypothesis.size()];
            int i = 0;
            for(SetHypothesisItem shi : this.hypothesis) {
                items[i++] = shi;
            }
            Arrays.sort(items, this.comparator);
            int k = ProbabilityUtils.fromBenfordDistribution(items.length)-1;
            hypothesis.remove(items[k]);
        }
    }
    
}
