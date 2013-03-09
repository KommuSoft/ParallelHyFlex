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
        
        /*CompactBitArray cba = new CompactBitArray(188);
        System.out.println(cba);
        cba.swapRange(17,191);
        System.out.println(cba);
        cba.clearTail();
        System.out.println(cba);*/
        
        ThreeSatProblemGenerator tspg = new ThreeSatProblemGenerator(1000000,90000000);
        tspg.generateProblem();
        
        
        /*Communication.initializeCommunication(args);
        ThreeSatProblemGenerator tspg = new ThreeSatProblemGenerator(1024,20000);
        HyperHeuristic dummy = new HyperHeuristic(tspg.generateProblem());
        //System.out.println("Hi from <"+Communication.getCommunication().getRank()+">");
        Communication.finalizeCommunication();*/
    }
}
