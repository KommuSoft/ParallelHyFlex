package hyflextests;

import AbstractClasses.HyperHeuristic;
import AbstractClasses.ProblemDomain;
import AbstractClasses.ProblemDomain.HeuristicType;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProbabilityVectorTests extends HyperHeuristic {

    private int factor;
    
    public ProbabilityVectorTests(long seed, int factor) {
        super(seed);
        this.factor = factor;
    }

    @Override
    public void solve(ProblemDomain problem) {
        try {
            File f = new File("probabilityvector" + factor + "-" + new Date().getTime() + ".dat");
            try (OutputStream os = new FileOutputStream(f); PrintStream ps = new PrintStream(os)) {
                int number_of_heuristics = problem.getNumberOfHeuristics();
                ps.println(String.format("#Number of Heuristics: %s", number_of_heuristics));
                for (HeuristicType ht : HeuristicType.values()) {
                    ps.println(String.format("#Heuristics of type %s: %s", ht.toString(), Arrays.toString(problem.getHeuristicsOfType(ht))));
                }
                HashSet<Integer> cos = new HashSet<>();
                for(Integer i : problem.getHeuristicsOfType(HeuristicType.CROSSOVER)) {
                    cos.add(i);
                }
                problem.setMemorySize(2);
                problem.initialiseSolution(0);
                problem.initialiseSolution(1);
                int active = 0;
                double current_obj_function_value = problem.getFunctionValue(0);
                //double new_obj_function_value = 0.0d;
                ps.println(String.format("#Eval(0): %s", current_obj_function_value));
                double[] pv = new double[number_of_heuristics];
                for (int i = 0; i < pv.length; i++) {
                    pv[i] = current_obj_function_value/factor;
                }
                int t = 0;
                while (!hasTimeExpired()) {
                    ps.println(String.format("#Vector(%s): %s", t, Arrays.toString(pv)));
                    ps.print(t);
                    for (int i = 0; i < pv.length; i++) {
                        ps.print("\t" + pv[i]);
                    }
                    ps.println();
                    int heuristic_to_apply = Utils.rouletteWheel(pv);
                    double new_obj_function_value;
                    if (!cos.contains(heuristic_to_apply)) {
                        new_obj_function_value = problem.applyHeuristic(heuristic_to_apply, active, active);
                    }
                    else {
                        new_obj_function_value = problem.applyHeuristic(heuristic_to_apply, active, 1-active, 1-active);
                        active = 1-active;
                    }
                    double delta = current_obj_function_value - new_obj_function_value;
                    ps.println(String.format("#Apply(%s): %s,%s,%s", t, heuristic_to_apply, current_obj_function_value, new_obj_function_value));
                    current_obj_function_value = new_obj_function_value;
                    pv[heuristic_to_apply] += delta;
                    t++;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ProbabilityVectorTests.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String toString() {
        return "ProbabilityVectorTester";
    }
}