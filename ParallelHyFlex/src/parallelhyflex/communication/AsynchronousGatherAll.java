package parallelhyflex.communication;

import mpi.MPI;

/**
 *
 * @author kommusoft
 */
public class AsynchronousGatherAll<T> implements PacketReceiver {

    private final int[] packetTags;
    private int currentDimension = 0;
    private final Object[] cache;
    private int receiveCache;

    public AsynchronousGatherAll(int packetTag) {
        packetTags = new int[]{packetTag};
        this.cache = new Object[Communication.getCommunication().getSize()];
        this.reset();
    }

    public void reset() {
        this.receiveCache = Communication.getCommunication().getNeighbor(currentDimension) << 1;
        this.currentDimension = 0;
    }

    public void send(T value) {
        this.receiveCache = 1;
        this.cache[Communication.getCommunication().getRank()] = value;
        this.checkSend();
    }

    public boolean ready() {
        return this.currentDimension >= Communication.getCommunication().getDimensions() && (this.receiveCache&(1<<Communication.getCommunication().getDimensions())) != 0x00;
    }

    @Override
    public int[] getPacketTags() {
        return this.packetTags;
    }

    @Override
    public void receivePacket(int from, int tag, Object data) throws Exception {
        Object[] unpack = (Object[]) data;
        int diff = Communication.getCommunication().getRank() ^ from;
        int s = Communication.getCommunication().getSize();
        this.receiveCache |= diff << 1;
        int base = from & (~(diff - 1));
        int red = Math.min(diff,s-base);
        System.arraycopy(unpack, 0, cache, base, red);
        this.checkSend();
    }

    private void checkSend() {
        int d = Communication.getCommunication().getDimensions();
        int r = Communication.getCommunication().getRank();
        int s = Communication.getCommunication().getSize();
        int cd = this.currentDimension;
        int l = 1 << cd;
        while (cd < d && (this.receiveCache & l) != 0x00) {
            int other = r^l;
            if (other < s) {
                int base = r & (~(l - 1));
                int red = Math.min(l,s-base);
                Object[][] packet = new Object[1][red];
                System.arraycopy(cache, base, packet[0], 0, red);
                Communication.nbS(packet, 0, 1, MPI.OBJECT, other, this.packetTags[0x00]);
            }
            cd++;
                l <<= 1;
        }
        this.currentDimension = cd;
    }
}
