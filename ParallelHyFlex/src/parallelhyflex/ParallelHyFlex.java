package parallelhyflex;

import parallelhyflex.problems.dummy.DummyProblem;

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
        HyperHeuristic dummy = new HyperHeuristic(new DummyProblem());
        //System.out.println("Hi from <"+Communication.getCommunication().getRank()+">");
        Communication.finalizeCommunication();
    }
}
