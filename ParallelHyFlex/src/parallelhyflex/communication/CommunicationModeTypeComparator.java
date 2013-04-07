package parallelhyflex.communication;

import java.util.Comparator;

/**
 *
 * @author kommusoft
 */
public class CommunicationModeTypeComparator implements Comparator<CommunicationMode> {
    
    private static CommunicationModeTypeComparator instance = new CommunicationModeTypeComparator();
    
    public static CommunicationModeTypeComparator getInstance () {
        return instance;
    }
    
    private CommunicationModeTypeComparator () {}

    @Override
    public int compare(CommunicationMode o1, CommunicationMode o2) {
        return ((Integer) o1.getType()).compareTo(o2.getType());
    }
    
}
