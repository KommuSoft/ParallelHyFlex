package parallelhyflex.communication;

/**
 *
 * @author kommusoft
 */
public class AsynchronousGatherAll<T> implements PacketReceiver {
    
    private final int[] packetTags;
    private int currentDimension = 0;
    private final Object[] cache;
    private int receiveCache;
    
    public AsynchronousGatherAll (int packetTag) {
        packetTags = new int[] {packetTag};
        this.cache = new Object[Communication.getCommunication().getSize()];
        this.receiveCache = 0;
    }
    
    public void reset () {
        this.receiveCache = 0;
        this.currentDimension = 0;
    }
    
    public void send (T value) {
        this.receiveCache = 1;
        this.cache[Communication.getCommunication().getRank()] = value;
    }
    
    public boolean ready () {
        return this.currentDimension < Communication.getCommunication().getDimensions();
    }

    @Override
    public int[] getPacketTags() {
        return this.packetTags;
    }

    @Override
    public void receivePacket(int from, int tag, Object data) throws Exception {
        Object[] unpack = (Object[]) data;
        int diff = Communication.getCommunication().getRank()^from;
        this.receiveCache |= diff;
        int base = from&(~(diff-1));
        System.arraycopy(unpack, 0, cache, base, diff);
        this.checkSend();
    }

    private void checkSend() {
        int d = Communication.getCommunication().getDimensions();
        int id = Communication.getCommunication().getRank();
        while(this.currentDimension < d) {
            int l = 1<<this.currentDimension;
            int base = id&(~(l-1));
            Object[][] packet = new Object[1][l];
            for(int i = 0; i < l; i++) {
                packet[1][i] = this.cache[i+base];
            }
            Communication.nbS(base, base, base, null, base, base)
            this.currentDimension++;
        }
    }
    
}
