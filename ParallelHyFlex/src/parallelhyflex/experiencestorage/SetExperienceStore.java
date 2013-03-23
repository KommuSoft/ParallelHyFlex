/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parallelhyflex.experiencestorage;

import java.util.HashSet;
import parallelhyflex.problemdependent.Constraint;
import parallelhyflex.problemdependent.ExperienceBase;
import parallelhyflex.problemdependent.Problem;
import parallelhyflex.problemdependent.Solution;

/**
 *
 * @author kommusoft
 */
public class SetExperienceStore<TSolution extends Solution<TSolution>,TProblem extends Problem<TSolution>, THypothesis extends Constraint<TSolution>> extends ExperienceBase<TSolution,TProblem> {
    
    private final HashSet<SetHypothesisItem<TSolution,THypothesis>> hypothesis = new HashSet<>();
    private final int historySize;
    private final int hypothesisSize;
    
    public SetExperienceStore (TProblem problem, int historySize, int hypothesisSize) {
        super(problem);
        this.historySize = historySize;
        this.hypothesisSize = hypothesisSize;
    }

    @Override
    public void join(TSolution solution) {
        double eval = this.getProblem().getObjectiveFunction(0).evaluateSolution(solution);
        for(SetHypothesisItem<TSolution,THypothesis> shi : hypothesis) {
            shi.checkInstance(solution,eval);
        }
        if(hypothesis.size() < this.hypothesisSize) {
            
        }
    }

    @Override
    public void amnesia() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
