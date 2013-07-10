package parallelhyflex.problems.frequencyassignment.problem;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import parallelhyflex.algebra.DoubleUpperMatrix;
import parallelhyflex.algebra.generators.IntegerArrayIndexGenerator;
import parallelhyflex.communication.serialisation.SerialisationUtils;
import parallelhyflex.genetic.observer.ManipulationGuiderObserver;
import parallelhyflex.interference.FunctionInterferenceStructure;
import parallelhyflex.interference.InterferenceStructure;
import parallelhyflex.problemdependent.problem.ProblemBase;
import parallelhyflex.problems.frequencyassignment.heuristic.FrequencyAssignmentGuiderManipulator;
import parallelhyflex.problems.frequencyassignment.heuristic.FrequencyAssignmentHeuristicC1;
import parallelhyflex.problems.frequencyassignment.heuristic.FrequencyAssignmentHeuristicC2;
import parallelhyflex.problems.frequencyassignment.heuristic.FrequencyAssignmentHeuristicC3;
import parallelhyflex.problems.frequencyassignment.heuristic.FrequencyAssignmentHeuristicL1;
import parallelhyflex.problems.frequencyassignment.heuristic.FrequencyAssignmentHeuristicL2;
import parallelhyflex.problems.frequencyassignment.heuristic.FrequencyAssignmentHeuristicM1;
import parallelhyflex.problems.frequencyassignment.heuristic.FrequencyAssignmentHeuristicM2;
import parallelhyflex.problems.frequencyassignment.solution.FrequencyAssignmentSolution;
import parallelhyflex.problems.frequencyassignment.solution.FrequencyAssignmentSolutionGenerator;
import parallelhyflex.utils.Utils;

/**
 *
 * @author kommusoft
 */
public class FrequencyAssignmentProblem extends ProblemBase<FrequencyAssignmentSolution, FrequencyAssignmentSolutionGenerator> {

    public static FrequencyAssignmentProblem readText(Scanner sc) {
        int n = sc.nextInt();//nTranceivers
        int m = sc.nextInt();//sectors
        double k = sc.nextDouble();//k
        double csh = sc.nextDouble();//csh
        double cacr = sc.nextDouble();//cacr
        int[][] frequencies = new int[n][];
        for (int i = 0x00; i < n; i++) {
            int l = sc.nextInt();
            int[] freq = new int[l];
            for (int j = 0x00; j < l; j++) {
                freq[j] = sc.nextInt();
            }
            frequencies[i] = freq;
        }
        int[] placement = new int[n];
        for (int i = 0x00; i < n; i++) {
            placement[i] = sc.nextInt();
        }
        DoubleUpperMatrix means = new DoubleUpperMatrix(m);
        DoubleUpperMatrix stdevs = new DoubleUpperMatrix(m);
        for (int i = 0x00; i < m; i++) {
            for (int j = i + 0x01; j < m; j++) {
                means.set(i, j, sc.nextDouble());
                stdevs.set(i, j, sc.nextDouble());
            }
        }
        return new FrequencyAssignmentProblem(n, m, frequencies, means, stdevs, placement, k, csh, cacr);
    }
    private final int nTransceivers;//TODO remove counters?
    private final int nSectors;
    private final int[][] frequencies;
    private final DoubleUpperMatrix means;
    private final DoubleUpperMatrix stdevs;
    private final int[] placement;
    private FunctionInterferenceStructure<Integer, Integer> interference;
    private final double k;
    private final double csh;
    private final double cacr;

    public FrequencyAssignmentProblem(int nTransceivers, int nSectors, int[][] frequencies, DoubleUpperMatrix means, DoubleUpperMatrix stdevs, int[] placement, double k, double csh, double cacr) {
        this.nTransceivers = nTransceivers;
        this.nSectors = nSectors;
        this.frequencies = frequencies;
        this.means = means;
        this.stdevs = stdevs;
        this.placement = placement;
        this.k = k;
        this.csh = csh;
        this.cacr = cacr;
        this.interference = new FunctionInterferenceStructure<>(new IntegerArrayIndexGenerator(placement));
        this.setObjectives(new FrequencyAssignmentObjectiveFunction1());
        this.setSolutionGenerator(new FrequencyAssignmentSolutionGenerator(this));
        this.setHeuristics(new FrequencyAssignmentHeuristicC1(this), new FrequencyAssignmentHeuristicC2(this), new FrequencyAssignmentHeuristicC3(this), new FrequencyAssignmentHeuristicM1(this), new FrequencyAssignmentHeuristicM2(this), new FrequencyAssignmentHeuristicL1(this), new FrequencyAssignmentHeuristicL2(this));
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.nTransceivers;
        hash = 37 * hash + this.nSectors;
        hash = 37 * hash + Arrays.deepHashCode(this.frequencies);
        hash = 37 * hash + Objects.hashCode(this.means);
        hash = 37 * hash + Objects.hashCode(this.stdevs);
        hash = 37 * hash + Arrays.hashCode(this.placement);
        hash = 37 * hash + Objects.hashCode(this.interference);
        hash = 37 * hash + Objects.hashCode(this.getK());
        hash = 37 * hash + Objects.hashCode(this.getCsh());
        hash = 37 * hash + Objects.hashCode(this.getCacr());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof FrequencyAssignmentProblem)) {
            return false;
        }
        FrequencyAssignmentProblem fap = (FrequencyAssignmentProblem) obj;
        return (this.nSectors == fap.nSectors && this.nTransceivers == fap.nTransceivers && this.means.equals(fap.means) && this.stdevs.equals(fap.stdevs) && Utils.arrayEquality(placement, fap.placement) && Utils.arrayEquality(this.frequencies, fap.frequencies));
    }

    /**
     * @return the nTransceivers
     */
    public int getnTransceivers() {
        return nTransceivers;
    }

    /**
     * @return the nSectors
     */
    public int getnSectors() {
        return nSectors;
    }

    /**
     * @return the frequencies
     */
    public int[][] getFrequencies() {
        return frequencies;
    }

    /**
     * @return the means
     */
    public DoubleUpperMatrix getMeans() {
        return means;
    }

    /**
     * @return the stdevs
     */
    public DoubleUpperMatrix getStdevs() {
        return stdevs;
    }

    /**
     * @return the placement
     */
    public int[] getPlacement() {
        return placement;
    }

    /**
     *
     * @param dos
     * @throws IOException
     */
    @Override
    public void write(DataOutputStream dos) throws IOException {
        dos.writeInt(nTransceivers);
        dos.writeInt(nSectors);
        dos.writeDouble(getK());
        dos.writeDouble(getCsh());
        dos.writeDouble(getCacr());
        SerialisationUtils.writeIntArray2d(dos, frequencies);
        means.write(dos);
        stdevs.write(dos);
        SerialisationUtils.writeIntArray(dos, placement);
    }

    /**
     *
     * @return
     */
    public InterferenceStructure<Integer> getInterferenceStructure() {
        return this.interference;
    }

    @Override
    public String toString() {
        return "FrequencyAssignmentProblem{" + "nTransceivers=" + nTransceivers + ", nSectors=" + nSectors + ", frequencies=" + frequencies + ", means=\n" + means + ", stdevs=\n" + stdevs + ", placement=" + placement + ", interference=" + interference + '}';
    }

    public void writeText(PrintStream osw) throws IOException {
        int n = this.getnTransceivers();
        int m = this.getnSectors();
        osw.println(String.format("%s %s", n, m));
        osw.println(String.format("%s %s %s", this.getK(), this.getCsh(), this.getCacr()));
        int[][] frequencies = this.getFrequencies();
        for (int i = 0x00; i < n; i++) {
            int[] freq = frequencies[i];
            int l = freq.length;
            osw.println(l);
            for (int j = 0x00; j < l; j++) {
                osw.println(freq[j]);
            }
        }
        int[] placement = this.placement;
        for (int i = 0x00; i < n; i++) {
            osw.println(placement[i]);
        }
        DoubleUpperMatrix means = this.means;
        DoubleUpperMatrix stdevs = this.stdevs;
        for (int i = 0x00; i < m; i++) {
            for (int j = i + 0x01; j < m; j++) {
                osw.println(means.get(i, j));
                osw.println(stdevs.get(i, j));
            }
        }
    }

    /**
     * @return the k
     */
    public double getK() {
        return k;
    }

    /**
     * @return the csh
     */
    public double getCsh() {
        return csh;
    }

    /**
     * @return the cacr
     */
    public double getCacr() {
        return cacr;
    }

    public ManipulationGuiderObserver getObserver(FrequencyAssignmentSolution from) {
        return new FrequencyAssignmentGuiderManipulator(this, from);
    }
}
