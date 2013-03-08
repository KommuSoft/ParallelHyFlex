package parallelhyflex;

import parallelhyflex.problems.threesat.ThreeSatProblem;
import parallelhyflex.utils.CompactBitArray;

/**
 *
 * @author kommusoft
 */
public class ParallelHyFlex {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        CompactBitArray cba = new CompactBitArray(192);
        System.out.println(cba);
        cba.swapRange(17,189);
        System.out.println(cba);
        cba.resetRange(18,188);
        System.out.println(cba);
        cba.setRange(19,187);
        System.out.println(cba);
        
        Communication.initializeCommunication(args);
        HyperHeuristic dummy = new HyperHeuristic(new ThreeSatProblem(128));
        //System.out.println("Hi from <"+Communication.getCommunication().getRank()+">");
        Communication.finalizeCommunication();
    }
}
