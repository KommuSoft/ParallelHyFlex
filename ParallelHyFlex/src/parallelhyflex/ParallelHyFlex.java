package parallelhyflex;

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
        System.out.println("Hi from <"+Communication.getCommunication().getRank()+">");
        Communication.finalizeCommunication();
    }
}
