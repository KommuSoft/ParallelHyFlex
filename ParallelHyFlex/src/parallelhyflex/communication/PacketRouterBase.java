package parallelhyflex.communication;

import java.util.HashMap;
import java.util.LinkedList;


public class PacketRouterBase implements PacketRouter {
    
    private final HashMap<Integer,LinkedList<PacketReceiver>> tagMapper = new HashMap<>();

    @Override
    public void registerPacketReceiver(PacketReceiver receiver) {
        for(Integer i : receiver.getPacketTags()) {
            LinkedList<PacketReceiver> ll;
            if(tagMapper.containsKey(i)) {
                ll = tagMapper.get(i);
            }
            else {
                ll = new LinkedList<>();
                tagMapper.put(i, ll);
            }
            ll.add(receiver);
        }
    }

    @Override
    public void unregisterPacketReceiver(PacketReceiver receiver) {
        for(Integer i : receiver.getPacketTags()) {
            LinkedList<PacketReceiver> ll;
            if(tagMapper.containsKey(i)) {
                tagMapper.get(i).remove(receiver);
            }
        }
    }

    @Override
    public void routePacket(int sender, int tag, Object data) {
        for(PacketReceiver pr : tagMapper.get(tag)) {
            try {
            pr.receivePacket(sender, tag, data);
            }
            catch(Exception e) {
                Communication.Log(e);
            }
        }
    }
    
}
