package hyflextests;

import AbstractClasses.ProblemDomain;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.HashSet;

public class ProbabilityVectorTests extends TestHyperHeuristic {

    private int factor = 1;

    public ProbabilityVectorTests(long seed, int run) {
        super(seed, run);
    }

    @Override
    protected void solveInner(ProblemDomain problem, int number_of_heuristics, HashSet<Integer> crossovers, PrintStream ps) {
        problem.setMemorySize(2);
        problem.initialiseSolution(0);
        problem.initialiseSolution(1);
        int active = 0;
        double current_obj_function_value = problem.getFunctionValue(0);
        double[] pv = new double[number_of_heuristics];
        for (int i = 0; i < pv.length; i++) {
            pv[i] = current_obj_function_value / factor;
        }
        while (!hasTimeExpired()) {
            this.printField("Eval", current_obj_function_value);

            ps.print(this.getTime());
            for (int i = 0; i < pv.length; i++) {
                ps.print("\t" + Math.round(pv[i]));
            }
            ps.println();

            int heuristic_to_apply = Utils.rouletteWheel(pv);
            double new_obj_function_value;
            if (!crossovers.contains(heuristic_to_apply)) {
                new_obj_function_value = this.applyHeuristic(problem, heuristic_to_apply, active, active);
            } else {
                new_obj_function_value = this.applyHeuristic(problem, heuristic_to_apply, active, 1 - active, 1 - active);
                active = 1 - active;
            }
            double delta = current_obj_function_value - new_obj_function_value;
            current_obj_function_value = new_obj_function_value;
            pv[heuristic_to_apply] += delta;
            this.incTime();
        }
    }
}