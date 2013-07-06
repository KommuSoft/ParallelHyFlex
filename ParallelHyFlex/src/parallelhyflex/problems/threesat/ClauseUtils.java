package parallelhyflex.problems.threesat;

import parallelhyflex.problems.threesat.solution.ThreeSatSolution;
import parallelhyflex.utils.CompactBitArray;
import parallelhyflex.utils.ProbabilityUtils;
import parallelhyflex.utils.Utils;

/**
 *
 * @author kommusoft
 */
public class ClauseUtils {

    public static int calculateLoss(Integer i, CompactBitArray cba, long[] constraints, int[] tocheck) {
        int delta, np, nn, j;
        delta = 0;
        np = tocheck[0];
        nn = tocheck.length;
        for (j = 1; j <= np; j++) {
            if (cba.willSwap(constraints[tocheck[j]], i)) {
                delta++;
            }
        }
        for (; j < nn; j++) {
            if (cba.willSwap(constraints[tocheck[j]], i)) {
                delta--;
            }
        }
        delta *= (cba.getBit(i) << 1) - 1;
        return delta;
    }

    public static long generate0Clause(long index0, long index1, long index2) {
        return (index0 << 40) | (index1 << 20) | (index2);
    }

    public static long generateClause(long index0, long index1, long index2, boolean value0, boolean value1, boolean value2) {
        long val = generate0Clause(index0, index1, index2);
        if (value0) {
            val |= 1L << 62;
        }
        if (value1) {
            val |= 1L << 61;
        }
        if (value2) {
            val |= 1L << 60;
        }
        return val;
    }

    public static long generateTrueClause(CompactBitArray cba) {
        int n = cba.getLength();
        long i0 = Utils.nextInt(n);
        long i1 = Utils.nextInt(n);
        long i2 = Utils.nextInt(n);
        long ia = Math.min(i0, Math.min(i1, i2));
        long ic = Math.max(i0, Math.max(i1, i2));
        long ib = i0 + i1 + i2 - ia - ic;
        long fill = (((long) Utils.nextInt(8)) << 60) | generate0Clause(ia, ib, ic);
        int ci = Utils.nextInt(3);
        long index = ClauseUtils.getIndexI(fill, ci);
        fill = ClauseUtils.setValue(fill, ci, cba.getBit(index));
        return fill;
    }

    public static long generateCompletelyTrueClause(CompactBitArray cba, long index0, long index1, long index2) {
        long fill = generate0Clause(index0, index1, index2);
        for (int i = 0; i < 3; i++) {
            fill = ClauseUtils.setValue(fill, i, cba.getBit(ClauseUtils.getIndexI(fill, i)));
        }
        return fill;
    }

    public static long generateCompletelyTrueClause(CompactBitArray cba) {
        int n = cba.getLength();
        long i0 = Utils.nextInt(n);
        long i1 = Utils.nextInt(n);
        long i2 = Utils.nextInt(n);
        long ia = Math.min(i0, Math.min(i1, i2));
        long ic = Math.max(i0, Math.max(i1, i2));
        long ib = i0 + i1 + i2 - ia - ic;
        return generateCompletelyTrueClause(cba, ia, ib, ic);
    }

    public static long generateCompletelyTrueClause(CompactBitArray cba, double[] cdf) {
        long i0 = ProbabilityUtils.randomIndexFromCDF(cdf);
        long i1 = ProbabilityUtils.randomIndexFromCDF(cdf);
        long i2 = ProbabilityUtils.randomIndexFromCDF(cdf);
        long ia = Math.min(i0, Math.min(i1, i2));
        long ic = Math.max(i0, Math.max(i1, i2));
        long ib = i0 + i1 + i2 - ia - ic;
        return generateCompletelyTrueClause(cba, ia, ib, ic);
    }

    public static int degree(long clause) {
        long inda = clause & 0x0F_FFFF;
        long indb = (clause >> 20) & 0x0F_FFFF;
        long indc = (clause >> 40) & 0x0F_FFFF;
        int deg = 1;
        if (inda != indb) {
            deg++;
        }
        if (indb != indc) {
            deg++;
        }
        return deg;
    }

    public static int[] getUniqueIndices(long clause) {
        int[] ind = new int[degree(clause)];
        long inda = clause & 0x0F_FFFF;
        long indb = (clause >> 20) & 0x0F_FFFF;
        long indc = (clause >> 40) & 0x0F_FFFF;
        ind[0] = (int) inda;
        int deg = 1;
        if (inda != indb) {
            ind[deg++] = (int) indb;
        }
        if (indb != indc) {
            ind[deg++] = (int) indc;
        }
        return ind;
    }
    public static String clausesToString (long[] constraints) {
        StringBuilder sb = new StringBuilder();
        for (long clause : constraints) {
            sb.append(String.format("(%s)", clauseToString(clause)));
        }
        return sb.toString();
    }

    public static boolean isHornClause(long clause) {
        long vals = (clause >> 60) & 7;
        return Utils.countOnes(vals) < 2;
    }

    public static boolean isValidClause(long clause) {
        long inda = getIndex0(clause);
        long indb = getIndex1(clause);
        long indc = getIndex2(clause);
        if (inda > indb || indb > indc) {
            return false;
        }
        if (indb == indc && inda != indb) {
            return false;
        }
        long vala = getValue0(clause);
        long valb = getValue1(clause);
        long valc = getValue2(clause);
        return (inda != indb || vala == valb) && (indb != indc || valb == valc) && (inda != indc || vala == valc);//TODO: optional third part? (indices are sorted)
    }

    public static void swapRandomBit(int n, int[][] influences, CompactBitArray cba, long[] constraints, ThreeSatSolution from) {
        int i = Utils.nextInt(n);
        swapBit(i, influences[i], cba, constraints, from);
    }

    public static void swapBit(int i, int[] tocheck, CompactBitArray cba, long[] constraints, ThreeSatSolution from) {
        int j, np = tocheck[0], nn = tocheck.length, delta = 0;
        for (j = 1; j <= np; j++) {
            if (cba.willSwap(constraints[tocheck[j]], i)) {
                delta++;
            }
        }
        for (; j < nn; j++) {
            if (cba.willSwap(constraints[tocheck[j]], i)) {
                delta--;
            }
        }
        delta *= 1 - (cba.swapGetBit(i) << 1);
        from.addConfictingClauses(delta);
    }

    public static int getIndexI(long clause, int i) {
        return (int) ((clause >> (40 - 20 * i)) & 0x0F_FFFF);
    }

    public static int getIndex0(long clause) {
        return (int) ((clause >> 40) & 0x0F_FFFF);
    }

    public static int getIndex1(long clause) {
        return (int) ((clause >> 20) & 0x0F_FFFF);
    }

    public static int getIndex2(long clause) {
        return (int) (clause & 0x0F_FFFF);
    }

    public static int getValueI(long clause, int i) {
        return (int) ((clause >> (62 - i)) & 0x01);
    }

    public static long setValue(long fill, int i, long bit) {
        return (fill & (~(0x01L << (62 - i)))) | (bit << (62 - i));
    }

    public static int getValue0(long clause) {
        return (int) ((clause >> 62) & 0x01);
    }

    public static int getValue1(long clause) {
        return (int) ((clause >> 61) & 0x01);
    }

    public static int getValue2(long clause) {
        return (int) ((clause >> 60) & 0x01);
    }

    public static void setIndices(long clause, int[] indices) {
        indices[2] = (int) (clause & 0x0F_FFFF);
        indices[1] = (int) ((clause >> 20) & 0x0F_FFFF);
        indices[0] = (int) ((clause >> 40) & 0x0F_FFFF);
    }

    /**
     * Retrieves the influences of a certain clause towards certain variables
     *
     * @param clause the clause to analyze
     * @param positive A 4-array where the first element is the number of
     * variables and the next three elements the indices of the variables.
     * @param negative A 4-array where the first element is the number of
     * variables and the next three elements the indices of the variables.
     */
    public static void setInfluences(long clause, int[] positive, int[] negative) {
        int ip = 1;
        int in = 1;
        int ia = (int) (clause & 0x0F_FFFF);
        int ib = (int) ((clause >> 20) & 0x0F_FFFF);
        int ic = (int) ((clause >> 40) & 0x0F_FFFF);
        if ((clause & 0x1000_0000_0000_0000L) != 0x00) {
            positive[ip++] = ia;
        } else {
            negative[in++] = ia;
        }
        if (ia != ib) {
            if ((clause & 0x2000_0000_0000_0000L) != 0x00) {
                positive[ip++] = ib;
            } else {
                negative[in++] = ib;
            }
        }
        if (ib != ic) {
            if ((clause & 0x4000_0000_0000_0000L) != 0x00) {
                positive[ip++] = ic;
            } else {
                negative[in++] = ic;
            }
        }
        positive[0] = ip;
        negative[0] = in;
    }

    public static int getLargestIndex(Iterable<Long> clauses) {
        int index = 0;
        for (long clause : clauses) {
            index = Math.max(index, (int) (clause & 0x0F_FFFF));
        }
        return index;
    }

    public static int getLargestIndex(long[] clauses) {
        int index = 0;
        for (long clause : clauses) {
            index = Math.max(index, (int) (clause & 0x0F_FFFF));
        }
        return index;
    }

    public static int getNumberOfFailedClauses(CompactBitArray cba, long[] clauses) {
        int number = 0;
        for (long clause : clauses) {
            if (!cba.satisfiesClause(clause)) {
                number++;
            }
        }
        return number;
    }

    public static int getFalseClauseIndex(ThreeSatSolution from, long[] clauses) {
        int c = clauses.length;
        int c0 = Utils.nextInt(c);
        for(int i = c0; i < c; i++) {
            if (!from.satisfiesClause(clauses[i])) {
                return i;
            }
        }
        for(int i = 0; i < c0; i++) {
            if (!from.satisfiesClause(clauses[i])) {
                return i;
            }
        }
        return -1;
    }
    
    public static int getNonEqualVariableIndex (CompactBitArray cba1, CompactBitArray cba2) {
        int v = cba1.getLength();
        int v0 = Utils.nextInt(v);//TODO: speedup with block level
        for(int i = v0; i < v; i++) {
            if (cba1.getBit(i) != cba2.getBit(i)) {
                return i;
            }
        }
        for(int i = 0; i < v0; i++) {
            if (cba1.getBit(i) != cba2.getBit(i)) {
                return i;
            }
        }
        return -1;
    }
    public static int getEqualVariableIndex (CompactBitArray cba1, CompactBitArray cba2) {
        int v = cba1.getLength();
        int v0 = Utils.nextInt(v);//TODO: speedup with block level
        for(int i = v0; i < v; i++) {
            if (cba1.getBit(i) == cba2.getBit(i)) {
                return i;
            }
        }
        for(int i = 0; i < v0; i++) {
            if (cba1.getBit(i) == cba2.getBit(i)) {
                return i;
            }
        }
        return -1;
    }
    public static String clauseToString(long clause) {
        long inda = getIndex0(clause);
        long indb = getIndex1(clause);
        long indc = getIndex2(clause);
        long vala = getValue0(clause);
        long valb = getValue1(clause);
        long valc = getValue2(clause);
        return String.format("[%s]=%s or [%s]=%s or [%s]=%s", inda, vala, indb, valb, indc, valc);
    }

    private ClauseUtils() {
    }
}
