/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hyflextests;

import AbstractClasses.HyperHeuristic;
import AbstractClasses.ProblemDomain;
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

/**
 *
 * @author kommusoft
 */
public abstract class TestHyperHeuristic extends HyperHeuristic {

    private int runid;
    private int time = 0;
    private PrintStream ps;

    public TestHyperHeuristic(long seed, int runid) {
        super(seed);
        this.runid = runid;
    }

    /**
     * @return the runid
     */
    public int getRunid() {
        return runid;
    }

    /**
     * @param runid the runid to set
     */
    public void setRunid(int runid) {
        this.runid = runid;
    }

    public void incTime() {
        this.time++;
    }

    public int getTime() {
        return this.time;
    }

    @Override
    public void solve(ProblemDomain problem) {
        try {
            File f = new File("data/" + this.getClass().getSimpleName() + new Date().getTime() + ".dat");
            try (final OutputStream os = new FileOutputStream(f); final PrintStream ps = new PrintStream(os)) {
                this.ps = ps;
                int number_of_heuristics = problem.getNumberOfHeuristics();
                ps.println(String.format("#Number of Heuristics: %s", number_of_heuristics));
                for (ProblemDomain.HeuristicType ht : ProblemDomain.HeuristicType.values()) {
                    ps.println(String.format("#Heuristics of type %s: %s", ht.toString(), Arrays.toString(problem.getHeuristicsOfType(ht))));
                }
                HashSet<Integer> crossovers = new HashSet<>();
                for (Integer i : problem.getHeuristicsOfType(ProblemDomain.HeuristicType.CROSSOVER)) {
                    crossovers.add(i);
                }
                solveInner(problem, number_of_heuristics, crossovers, ps);
            }
        } catch (IOException ex) {
            Logger.getLogger(ProbabilityVectorTests.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected abstract void solveInner(ProblemDomain problem, int number_of_heuristics, HashSet<Integer> crossovers, PrintStream ps);

    protected double applyHeuristic(ProblemDomain pd, int heuristic_to_apply, int from, int to) {
        double current_obj_function_value = pd.getFunctionValue(from);
        double new_obj_function_value = pd.applyHeuristic(heuristic_to_apply, from, to);
        ps.println(String.format("#Apply(%s): %s -> %s -> %s,%s,%s", time, from, heuristic_to_apply, to, current_obj_function_value, new_obj_function_value));
        return new_obj_function_value;
    }

    protected double applyHeuristic(ProblemDomain pd, int heuristic_to_apply, int from1, int from2, int to) {
        double current_obj_function_value1 = pd.getFunctionValue(from1);
        double current_obj_function_value2 = pd.getFunctionValue(from2);
        double new_obj_function_value = pd.applyHeuristic(heuristic_to_apply, from1, from2, to);
        ps.println(String.format("#Apply(%s): [%s,%s] -> %s -> %s,[%s,%s],%s", time, from1, from2, heuristic_to_apply, to, current_obj_function_value1, current_obj_function_value2, new_obj_function_value));
        return new_obj_function_value;
    }

    protected void printField(String name, Object object) {
        ps.println(String.format("#%s(%s): %s", name, time, object));
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
