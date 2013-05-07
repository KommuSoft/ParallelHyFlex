package parallelhyflex.problems.fdcsp.problem;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import parallelhyflex.algebra.collections.ListMapperBase;
import parallelhyflex.problems.fdcsp.problem.expressions.Expression;
import parallelhyflex.utils.UniqueRandomGenerator;
import parallelhyflex.utils.Utils;

/**
 *
 * @author kommusoft
 */
public class FDCOPProblem {

    private final Variable[] variables;
    private final FDCOPConstraint[] constraints;
    private final ListMapperBase<Variable,FDCOPConstraint> constraintMapping = new ListMapperBase<>();
    private final Expression[] minimalisations;
    private final UniqueRandomGenerator<Integer> variableSelector;

    public FDCOPProblem(Variable[] variables, FDCOPConstraint[] constraints, Expression[] minimalisations) {
        System.out.println(String.format("FDCOPP %s %s %s", Arrays.toString(variables), Arrays.toString(constraints), Arrays.toString(minimalisations)));
        variableSelector = new UniqueRandomGenerator(Utils.sequence(0,variables.length));
        this.variables = variables;
        this.constraints = constraints;
        this.minimalisations = minimalisations;
        for (FDCOPConstraint c : constraints) {
            for (Variable v : c) {
                constraintMapping.put(v, c);
            }
        }
        reduceDomains(constraints);
        for(Variable var : variables) {
            System.out.println(String.format("%s in %s",var,var.getDomain()));
        }
    }

    public void reduceDomains(FDCOPConstraint[] constraints) {
        HashSet<FDCOPConstraint> toReduce = new HashSet<>();
        for (FDCOPConstraint c : constraints) {
            toReduce.add(c);
        }
        HashSet<FDCOPConstraint> toAdd = new HashSet<>();
        do {
            toAdd.clear();
            for (Iterator<FDCOPConstraint> it = toReduce.iterator(); it.hasNext();) {
                FDCOPConstraint c = it.next();it.remove();
                if(c.relaxDomains()) {
                    for(Variable v : c) {
                        for(Iterator<FDCOPConstraint> cai = constraintMapping.iterator(v); cai.hasNext();) {
                            FDCOPConstraint ca = cai.next();
                            if(!toReduce.contains(ca)) {
                                toAdd.add(c);
                            }
                        }
                    }
                }
            }
            toReduce.addAll(toAdd);
        }
        while(!toAdd.isEmpty());
    }
}