package parallelhyflex.problems.fdcsp.problem.solution;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import parallelhyflex.communication.SerialisationUtils;
import parallelhyflex.problemdependent.solution.SolutionGeneratorBase;
import parallelhyflex.problems.fdcsp.problem.FDCOPConstraint;
import parallelhyflex.problems.fdcsp.problem.FDCOPProblem;
import parallelhyflex.problems.fdcsp.problem.FiniteIntegerDomain;
import parallelhyflex.problems.fdcsp.problem.MutableFiniteIntegerDomain;
import parallelhyflex.problems.fdcsp.problem.Variable;
import parallelhyflex.problems.fdcsp.problem.expressions.Expression;
import parallelhyflex.utils.CompactBitArray;
import parallelhyflex.utils.Utils;

/**
 *
 * @author kommusoft
 */
public class FDCOPSolutionGenerator extends SolutionGeneratorBase<FDCOPSolution, FDCOPProblem> {
    
    public FDCOPSolutionGenerator(FDCOPProblem problem) {
        super(problem);
    }
    
    @Override
    public FDCOPSolution generateSolution() {
        FDCOPProblem prob = this.getProblem();
        MutableFiniteIntegerDomain[] domains = new MutableFiniteIntegerDomain[prob.getNumberOfVariables()];
        for (int i = 0; i < prob.getNumberOfVariables(); i++) {
            domains[i] = prob.getDomain(i).clone();
        }
        int n = prob.getNumberOfVariables();
        int k = prob.getNumberOfMinimalisations();
        int[] values = new int[n];
        CompactBitArray assigned = new CompactBitArray(n);
        double[] evals = new double[k];
        System.out.println(String.format("Initial domains are %s",Arrays.toString(domains)));
        for (int i = 0; i < n; i++) {
            if (!assigned.get(i)) {
                int val = domains[i].getIth(Utils.StaticRandom.nextInt(domains[i].size()));
                values[i] = val;
                System.out.println(String.format("%s=%s",prob.getVariable(i),val));
                assigned.set(i, true);
                domains[i].setToSingle(val);
                reduceDomains(prob.getVariable(i), assigned, domains, values);
                System.out.println(String.format("Assigned %s (%s) domains are now %s",i,prob.getVariable(i),Arrays.toString(domains)));
            }
        }
        FDCOPSolution sol = new FDCOPSolution(values, evals);
        int i = 0;
        for (Iterator<Expression> it = prob.minimalisationIterator(); it.hasNext(); i++) {
            sol.setEvaluationValue(i, it.next().getExpressionValue(sol));
        }
        return sol;
    }
    
    @Override
    public FDCOPSolution readAndGenerate(DataInputStream dis) throws IOException {
        int[] values = SerialisationUtils.readIntArray(dis);
        double[] evals = SerialisationUtils.readDoubleArray(dis);
        return new FDCOPSolution(values, evals);
    }
    
    private void reduceDomains(Variable v, CompactBitArray assigned, MutableFiniteIntegerDomain[] domains, int[] values) {
        HashSet<FDCOPConstraint> toCheck = new HashSet<>();
        HashSet<FDCOPConstraint> toAdd = new HashSet<>();
        for (FDCOPConstraint c : v) {
            if (c.relaxDomains(domains)) {
                relaxConstraint(domains, assigned, c, values, toAdd, toAdd);
            }
        }
        
        while (toAdd.size() > 0) {
            toCheck.addAll(toAdd);
            toAdd.clear();
            for (Iterator<FDCOPConstraint> it = toCheck.iterator(); it.hasNext();) {
                FDCOPConstraint c = it.next();
                it.remove();
                if (c.relaxDomains(domains)) {
                    relaxConstraint(domains, assigned, c, values, toAdd, toCheck);
                }
            }
        }
    }
    
    private void relaxConstraint(FiniteIntegerDomain[] domains, CompactBitArray assigned, FDCOPConstraint c, int[] values, HashSet<FDCOPConstraint> toAdd, HashSet<FDCOPConstraint> toCheck) {
        for (Variable v3 : c) {
            int index = v3.getIndex();
            FiniteIntegerDomain fid = domains[index];
            if (fid.size() == 1) {
                values[index] = fid.low();
                assigned.set(index, true);
            }
            for (FDCOPConstraint c2 : v3) {
                if (!toCheck.contains(c2)) {
                    toAdd.add(c2);
                }
            }
        }
    }
}
