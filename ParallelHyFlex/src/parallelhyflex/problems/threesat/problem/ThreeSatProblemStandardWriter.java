package parallelhyflex.problems.threesat.problem;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.logging.Logger;
import parallelhyflex.problems.threesat.ClauseUtils;

/**
 *
 * @author kommusoft
 */
public class ThreeSatProblemStandardWriter {

    private static final ThreeSatProblemStandardWriter instance = new ThreeSatProblemStandardWriter();
    private static final Logger LOG = Logger.getLogger(ThreeSatProblemStandardWriter.class.getName());

    public static ThreeSatProblemStandardWriter getInstance() {
        return instance;
    }

    private ThreeSatProblemStandardWriter() {
    }

    /**
     *
     * @param os
     * @param v
     * @param c
     * @param clauses
     */
    public void write(OutputStream os, int v, int c, long[] clauses) {
        try (PrintWriter tw = new PrintWriter(os)) {
            tw.println(String.format("p cnf %s %s", v, c));
            for (int i = 0; i < clauses.length; i++) {
                tw.println(this.printStandardClause(clauses[i]));
            }
        }
    }

    private String printStandardClause(long l) {
        int i0 = ClauseUtils.getSigned1Index0(l);
        int i1 = ClauseUtils.getSigned1Index1(l);
        int i2 = ClauseUtils.getSigned1Index2(l);
        return String.format("%s\t%s\t%s\t0", i0, i1, i2);
    }
}
