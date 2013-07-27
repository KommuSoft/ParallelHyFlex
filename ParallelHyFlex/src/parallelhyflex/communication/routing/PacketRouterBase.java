package parallelhyflex.communication.routing;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.logging.Logger;
import mpi.MPI;
import parallelhyflex.communication.Communication;

/**
 *
 * @author kommusoft
 */
public class PacketRouterBase implements PacketRouter {

    private final HashMap<Integer, LinkedList<PacketReceiver>> tagMapper = new HashMap<>();

    /**
     *
     * @param receiver
     */
    @Override
    public void registerPacketReceiver(PacketReceiver receiver) {
        for (Integer i : receiver.getPacketTags()) {
            LinkedList<PacketReceiver> ll;
            if (tagMapper.containsKey(i)) {
                ll = tagMapper.get(i);
            } else {
                ll = new LinkedList<>();
                tagMapper.put(i, ll);
            }
            ll.add(receiver);
        }
    }

    /**
     *
     * @param receiver
     */
    @Override
    public void unregisterPacketReceiver(PacketReceiver receiver) {
        for (Integer i : receiver.getPacketTags()) {
            LinkedList<PacketReceiver> ll;
            if (tagMapper.containsKey(i)) {
                tagMapper.get(i).remove(receiver);
            }
        }
    }

    /**
     *
     * @param sender
     * @param tag
     * @param data
     */
    @Override
    public void routePacket(int sender, int tag, Object data) {
        Iterable<PacketReceiver> prs = tagMapper.get(tag);
        if (prs != null) {
            for (PacketReceiver pr : prs) {
                try {
                    pr.receivePacket(sender, tag, data);
                } catch (Exception e) {
                    Communication.log(e);
                }
            }
        }
        else {
            Communication.log("Received packet with tag %s but could not address it!",tag);
        }
    }

    /**
     *
     * @return
     */
    @Override
    public int[] getPacketTags() {
        return new int[]{MPI.ANY_TAG};
    }

    /**
     *
     * @param from
     * @param tag
     * @param data
     * @throws Exception
     */
    @Override
    public void receivePacket(int from, int tag, Object data) throws Exception {
        this.routePacket(tag, tag, data);
    }
    private static final Logger LOG = Logger.getLogger(PacketRouterBase.class.getName());
}
