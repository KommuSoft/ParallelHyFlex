package parallelhyflex;

import parallelhyflex.problems.threesat.ThreeSatProblemGenerator;

/**
 *
 * @author kommusoft
 */
public class ParallelHyFlex {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        ThreeSatProblemGenerator tspg = new ThreeSatProblemGenerator(128,500);
        tspg.generateProblem();
        
        
        Communication.initializeCommunication(args);
        HyperHeuristic dummy = new HyperHeuristic(tspg.generateProblem());
        Communication.finalizeCommunication();
    }
}
