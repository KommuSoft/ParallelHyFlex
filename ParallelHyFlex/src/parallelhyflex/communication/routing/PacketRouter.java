package parallelhyflex.communication.routing;

/**
 *
 * @author kommusoft
 */
public interface PacketRouter extends PacketReceiver {
    
    public void registerPacketReceiver (PacketReceiver receiver);
    public void unregisterPacketReceiver (PacketReceiver receiver);
    public void routePacket (int sender, int tag, Object data);
    
}