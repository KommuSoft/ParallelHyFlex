package parallelhyflex;

import parallelhyflex.problems.threesat.ThreeSatProblem;

/**
 *
 * @author kommusoft
 */
public class ParallelHyFlex {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Communication.initializeCommunication(args);
        HyperHeuristic dummy = new HyperHeuristic(new ThreeSatProblem(128));
        //System.out.println("Hi from <"+Communication.getCommunication().getRank()+">");
        Communication.finalizeCommunication();
    }
}
