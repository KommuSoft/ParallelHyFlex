package parallelhyflex.communication;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Logger;
import mpi.MPI;
import parallelhyflex.algebra.collections.iterables.ArrayIterator;
import parallelhyflex.algebra.collections.iterables.CastingIterator;
import parallelhyflex.communication.abstraction.CommMode;
import parallelhyflex.communication.routing.PacketReceiver;

/**
 *
 * @param <T>
 * @author kommusoft
 */
public class AsynchronousGatherAll<T> implements PacketReceiver, Iterable<T> {

    private static final Logger LOG = Logger.getLogger(AsynchronousGatherAll.class.getName());
    private final int[] packetTags;
    private int currentDimension = 0;
    private final Object[] cache;
    private int receiveCache;

    /**
     *
     * @param packetTag
     */
    public AsynchronousGatherAll(int packetTag) {
        packetTags = new int[]{packetTag};
        this.cache = new Object[getCom().getSize()];
        this.reset();
    }

    /**
     *
     */
    public void reset() {
        Communication com = this.getCom();
        int d = com.getDimensions();
        this.receiveCache = com.getNonNeighborCache() << 1;
        this.currentDimension = d - 1;
        //Communication.log("AsymWal %s WalNgb %s WalFul %s", com.areAssymetricWalls(), com.hasWallNeighbor(),com.isFullWall());
        if (com.areAssymetricWalls()) {
            if (!com.hasWallNeighbor()) {
                this.receiveCache |= 1;
            } else {
                this.currentDimension--;
            }
        } else {
            this.receiveCache |= 1;
        }
        //Communication.log("WRD: %s DOM: %s", Integer.toBinaryString(this.receiveCache), this.currentDimension);
    }

    /**
     *
     * @param rank
     * @return
     */
    public boolean available(int rank) {
        return this.isReady();
    }

    /**
     *
     * @param value
     */
    public void send(T value) {
        //Communication.log("Sending with word %s\tDIM=%s", Integer.toBinaryString(this.receiveCache), this.currentDimension);
        Communication com = this.getCom();
        int d = com.getDimensions();
        int r = com.getRank();
        this.receiveCache |= 2 << d;
        this.cache[getCom().getRank()] = value;
        if (com.isFullWall()) {
            this.checkSend();
        } else {//we are the half wall
            this.sendPacket(1, r, 1, r ^ (1 << (d - 1)));
        }
    }

    /**
     *
     * @param rank
     * @return
     */
    public T getData(int rank) {
        return (T) cache[rank];
    }

    /**
     *
     * @param type
     * @return
     */
    public T[] toArray(T[] type) {
        return toArrayList().toArray(type);
    }

    /**
     *
     * @return
     */
    public boolean isReady() {
        return this.currentDimension < 0x00 && (this.receiveCache & 0x03) == 0x03;
    }

    /**
     *
     * @return
     */
    @Override
    public int[] getPacketTags() {
        return this.packetTags;
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
        Object[] unpack = (Object[]) data;
        //Communication.log("Receiving %s with word %s\tDIM=%s", Arrays.toString(unpack), Integer.toBinaryString(this.receiveCache), this.currentDimension);
        Communication com = this.getCom();
        int diff = (com.getRank() ^ from) << 1;
        if (com.isFullWall()) {
            this.receiveCache |= diff;
            int base = from & (diff - 1);
            for (int i = 0; base < cache.length; base += diff) {
                cache[base] = unpack[i++];
            }
            this.checkSend();
        } else {
            //try {
            System.arraycopy(unpack, 0, cache, 0, cache.length);
            //} catch (Exception e) {
            //    Communication.log("ERROR");
            //    throw e;
            //}
            this.receiveCache = 0x03;
            this.currentDimension = -0x01;
        }
    }

    /**
     *
     */
    public void logState() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.cache.length; i++) {
            if (this.cache[i] != null) {
                sb.append('x');
            } else {
                sb.append(' ');
            }
        }
        sb.append('|');
        int rcv = this.receiveCache;
        while (rcv != 0x00) {
            if ((rcv & 0x01) != 0x00) {
                sb.append('x');
            } else {
                sb.append(' ');
            }
            rcv >>= 0x01;
        }
        Communication.log(sb.toString());
    }

    private void checkSend() {
        Communication com = this.getCom();
        int d = com.getDimensions();
        int r = com.getRank();
        int s = com.getSize();
        //Communication.log("now has state word %s\tDIM=%s", Integer.toBinaryString(this.receiveCache), this.currentDimension);
        int cd = this.currentDimension;
        int size = 1 << (cd + 2);
        while (cd >= 0 && (this.receiveCache & size) != 0x00 && (this.receiveCache & (2 << d)) != 0x00) {
            this.sendToNeighbor(r, s, size >> 0x02);
            cd--;
            size >>= 1;
        }
        if (cd == -1 && this.receiveCache == ((1 << (d + 2))) - 2) {
            //Communication.log("Sending callback!");
            this.sendPacket(this.cache.length, 0, 1, com.getWallNeighbor());
            this.receiveCache |= 0x01;
        }
        this.currentDimension = cd;
    }

    private void sendToNeighbor(int rank, int size, int diff) {
        if ((rank ^ diff) < size) {
            int diff2 = diff << 1;
            int base = rank & ((diff2) - 1);
            int l = 1 + (this.cache.length - 1 - base) / diff2;
            sendPacket(l, base, diff2, rank ^ diff);
        }
    }

    /**
     *
     * @return
     */
    public ArrayList<T> toArrayList() {
        ArrayList<T> al = new ArrayList<>(cache.length);
        for (T val : this) {
            al.add(val);
        }
        return al;
    }

    /**
     *
     * @return
     */
    @Override
    public Iterator<T> iterator() {
        return new CastingIterator<>(new ArrayIterator<>(this.cache));
    }

    private Communication getCom() {
        return Communication.getCommunication();
    }

    private void sendPacket(int length, int offset, int delta, int other) {
        Object[] packet = new Object[length];
        for (int i = 0, j = offset; i < length; i++, j += delta) {
            packet[i] = cache[j];
        }
        Communication.Send(CommMode.MpiNonBlocking, new Object[]{packet}, 0, 1, MPI.OBJECT, other, this.packetTags[0x00]);
        //Communication.log("Send %s to %s\tDIM=%s", Arrays.toString(packet), other, this.currentDimension);
    }
}
