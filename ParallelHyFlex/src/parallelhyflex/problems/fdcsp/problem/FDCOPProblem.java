package parallelhyflex.problems.fdcsp.problem;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import parallelhyflex.algebra.collections.ListMapperBase;
import parallelhyflex.problems.fdcsp.problem.expressions.Expression;

/**
 *
 * @author kommusoft
 */
public class FDCOPProblem {

    private Variable[] variables;
    private FDCOPConstraint[] constraints;
    private final ListMapperBase<Variable,FDCOPConstraint> constraintMapping = new ListMapperBase<>();
    private Expression[] minimalisation;

    public FDCOPProblem(Variable[] variables, FDCOPConstraint[] constraints, Expression[] minimalisations) {
        System.out.println(String.format("FDCOPP %s %s %s", Arrays.toString(variables), Arrays.toString(constraints), Arrays.toString(minimalisations)));
        for (FDCOPConstraint c : constraints) {
            for (Variable v : c) {
                constraintMapping.put(v, c);
            }
        }
        reduceDomains(constraints);
    }

    public void reduceDomains(FDCOPConstraint[] constraints) {
        //TODO: reduce variable domains further
        HashSet<FDCOPConstraint> toReduce = new HashSet<>();
        for (FDCOPConstraint c : constraints) {
            toReduce.add(c);
        }
        HashSet<FDCOPConstraint> toAdd = new HashSet<>();
        do {
            toAdd.clear();
            for(FDCOPConstraint c : toReduce) {
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