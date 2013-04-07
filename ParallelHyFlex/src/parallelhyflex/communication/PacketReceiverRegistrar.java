/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parallelhyflex.communication;

/**
 *
 * @author kommusoft
 */
public interface PacketReceiverRegistrar {
    
    public void registerPacketReceiver (PacketReceiver receiver);
    public void unregisterPacketReceiver (PacketReceiver receiver);
    public void routePacket (int sender, int tag, Object data);
    
}