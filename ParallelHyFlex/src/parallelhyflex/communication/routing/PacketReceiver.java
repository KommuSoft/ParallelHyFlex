package parallelhyflex.communication.routing;

/**
 *
 * @author kommusoft
 */
public interface PacketReceiver {
    
    public int[] getPacketTags ();
    
    public void receivePacket (int from, int tag, Object data) throws Exception;
    
}
