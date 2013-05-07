package parallelhyflex.problems.fdcsp.problem;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import parallelhyflex.algebra.PinnedFlightWeight;
import parallelhyflex.algebra.collections.ArgumentIterable;
import parallelhyflex.algebra.collections.ArrayIterator;
import parallelhyflex.algebra.collections.ListMapperBase;
import parallelhyflex.problemdependent.problem.ObjectiveFunction;
import parallelhyflex.problemdependent.problem.ProblemBase;
import parallelhyflex.problems.fdcsp.problem.expressions.Expression;
import parallelhyflex.problems.fdcsp.problem.solution.FDCOPSolution;
import parallelhyflex.problems.fdcsp.problem.solution.FDCOPSolutionGenerator;
import parallelhyflex.utils.UniqueRandomGenerator;
import parallelhyflex.utils.Utils;

/**
 *
 * @author kommusoft
 */
public class FDCOPProblem extends ProblemBase<FDCOPSolution, FDCOPSolutionGenerator> implements Iterable<Variable>, ArgumentIterable<Variable, FDCOPConstraint> {

    private final Variable[] variables;
    private final int[] domainSizes;
    private final FDCOPConstraint[] constraints;
    private final ListMapperBase<Variable, FDCOPConstraint> constraintMapping = new ListMapperBase<>();
    private final Expression[] minimalisations;
    private final UniqueRandomGenerator<Integer> variableSelector;
    private static final PinnedFlightWeight<Integer, FDCOPObjectiveFunctionI> objectivesFw = new PinnedFlightWeight<>(FDCOPObjectiveFunctionIGenerator.getInstance());

    public FDCOPProblem(Variable[] variables, FDCOPConstraint[] constraints, Expression[] minimalisations) {
        this.setSolutionGenerator(new FDCOPSolutionGenerator(this));
        System.out.println(String.format("FDCOPP %s %s %s", Arrays.toString(variables), Arrays.toString(constraints), Arrays.toString(minimalisations)));
        variableSelector = new UniqueRandomGenerator(Utils.sequence(0, variables.length));
        this.variables = variables;
        this.constraints = constraints;
        this.minimalisations = minimalisations;
        for (FDCOPConstraint c : constraints) {
            for (Variable v : c) {
                constraintMapping.put(v, c);
            }
        }
        reduceDomains(constraints);
        this.domainSizes = new int[variables.length];
        int index = 0;
        for (Variable var : variables) {
            this.domainSizes[index] = var.getDomain().size();
            var.setIndex(index++);
            System.out.println(String.format("%s in %s", var, var.getDomain()));
        }
    }

    @Override
    public ObjectiveFunction<FDCOPSolution> getObjectiveFunction(int index) {
        return objectivesFw.generate(index);
    }

    @Override
    public int getNumberOfObjectiveFunctions() {
        return this.minimalisations.length;
    }

    public int getDomainSize(int variableIndex) {
        return this.domainSizes[variableIndex];
    }

    public int getDomainSize(Variable var) {
        return this.domainSizes[var.getIndex()];
    }

    public Variable getVariable(int variableIndex) {
        return this.variables[variableIndex];
    }

    public FiniteIntegerDomain getDomain(int variableIndex) {
        return this.variables[variableIndex].getDomain();
    }

    public int getNumberOfVariables() {
        return this.variables.length;
    }

    public int getNumberOfMinimalisations() {
        return this.minimalisations.length;
    }

    public int getNumberOfConstraints() {
        return this.constraints.length;
    }

    private void reduceDomains(FDCOPConstraint[] constraints) {
        HashSet<FDCOPConstraint> toReduce = new HashSet<>();
        for (FDCOPConstraint c : constraints) {
            toReduce.add(c);
        }
        HashSet<FDCOPConstraint> toAdd = new HashSet<>();
        do {
            toAdd.clear();
            for (Iterator<FDCOPConstraint> it = toReduce.iterator(); it.hasNext();) {
                FDCOPConstraint c = it.next();
                it.remove();
                if (c.relaxDomains()) {
                    for (Variable v : c) {
                        for (Iterator<FDCOPConstraint> cai = constraintMapping.iterator(v); cai.hasNext();) {
                            FDCOPConstraint ca = cai.next();
                            if (!toReduce.contains(ca)) {
                                toAdd.add(c);
                            }
                        }
                    }
                }
            }
            toReduce.addAll(toAdd);
        } while (!toAdd.isEmpty());
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {
        //TODO
    }

    @Override
    public Iterator<Variable> iterator() {
        return new ArrayIterator<>(this.variables);
    }

    public Iterator<Variable> variableIterator() {
        return this.iterator();
    }

    public Iterator<FDCOPConstraint> constraintIterator() {
        return new ArrayIterator<>(this.constraints);
    }

    public Iterator<Expression> minimalisationIterator() {
        return new ArrayIterator<>(this.minimalisations);
    }

    @Override
    public Iterator<FDCOPConstraint> iterator(Variable argument) {
        return this.constraintMapping.iterator(argument);
    }
}