package hyflextests;

import AbstractClasses.ProblemDomain;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Random;

public class UniformTests extends TestHyperHeuristic {

    public UniformTests(long seed, int runid) {
        super(seed, runid);
    }

    @Override
    protected void solveInner(ProblemDomain problem, int number_of_heuristics, HashSet<Integer> crossovers, PrintStream ps) {
        problem.setMemorySize(2);
        problem.initialiseSolution(0);
        problem.initialiseSolution(1);
        int active = 0;
        double current_obj_function_value = problem.getFunctionValue(0);
        Random rand = new Random();
        while (!hasTimeExpired()) {

            this.printField("Eval", current_obj_function_value);

            int heuristic_to_apply = rand.nextInt(number_of_heuristics);
            if (!crossovers.contains(heuristic_to_apply)) {
                current_obj_function_value = this.applyHeuristic(problem, heuristic_to_apply, active, active);
            } else {
                current_obj_function_value = this.applyHeuristic(problem, heuristic_to_apply, active, 1 - active, 1 - active);
                active = 1 - active;
            }
            this.incTime();
        }
    }
}