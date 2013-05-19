package parallelhyflex.communication.abstraction;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;
import mpi.Datatype;
import mpi.MPI;
import parallelhyflex.algebra.tuples.Tuple2;

/**
 *
 * @author kommusoft
 */
public class UdpWorld {

    public static final int PORT_OFFSET = 0x5048;
    public static final int MAXIMUM_PACKET_SIZE = 65_507;
    private final byte[] buffer = new byte[MAXIMUM_PACKET_SIZE];
    private final DatagramPacket receivePacket;
    private final DatagramSocket socket;
    private final InetAddress address;
    private final HashMap<Tuple2<Integer, Integer>, LinkedList<Object>> receiveCache = new HashMap<>();

    public UdpWorld(int rank) throws SocketException, UnknownHostException {
        address = InetAddress.getLocalHost();
        this.socket = new DatagramSocket(PORT_OFFSET + rank);
        this.receivePacket = new DatagramPacket(this.buffer, MAXIMUM_PACKET_SIZE);
    }

    public boolean recv(Object buf, int offset, int count, Datatype datatype, int source, int tag) throws IOException, ClassNotFoundException {
        int n = Array.getLength(buf);
        int subcount = Math.min(n - offset, count);
        boolean found = this.searchDatabase(buf, offset, subcount, datatype, source, tag);
        this.socket.setSoTimeout(0);
        while (!found) {
            this.socket.receive(this.receivePacket);
            found = readPacket(source, tag, buf, offset, subcount);
        }
        return true;
    }

    public boolean irecv(Object buf, int offset, int count, Datatype datatype, int source, int tag) throws IOException, ClassNotFoundException {
        int n = Array.getLength(buf);
        int subcount = Math.min(n - offset, count);
        boolean found = this.searchDatabase(buf, offset, subcount, datatype, source, tag);
        this.socket.setSoTimeout(1);
        while (!found) {
            try {
                this.socket.receive(this.receivePacket);
            } catch (SocketTimeoutException e) {
                return false;
            }
            found = readPacket(source, tag, buf, offset, subcount);
        };
        return true;
    }

    public boolean isend(Object buf, int offset, int count, Datatype datatype, int dest, int tag) throws IOException {
        int n = Array.getLength(buf);
        int end = Math.min(n, count + offset);
        int subcount = end - offset;
        DatagramPacket packet = null;
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream(); ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeInt(tag);
            oos.writeInt(subcount);
            for (int i = offset; i < end; i++) {
                oos.writeObject(Array.get(buf, i));
            }
            byte[] data = baos.toByteArray();
            packet = new DatagramPacket(data, 0, data.length, address, PORT_OFFSET + dest);
        }
        if (packet != null) {
            this.socket.send(packet);
            return true;
        } else {
            return false;
        }
    }

    private boolean readPacket(int source, int tag, Object buf, int offset, int count) throws IOException, ClassNotFoundException {
        try (ByteArrayInputStream bais = new ByteArrayInputStream(this.receivePacket.getData()); ObjectInputStream ois = new ObjectInputStream(bais)) {
            int srcP = this.receivePacket.getPort() - PORT_OFFSET;
            int tagP = ois.readInt();
            int lenP = ois.readInt();
            if (sourceTagMatch(srcP, source, tagP, tag)) {
                readObjects(buf, offset, Math.min(lenP, count), ois);
                return true;
            } else {
                Object dat = new Object[lenP];
                readObjects(dat, offset, Math.min(lenP, count), ois);
                Tuple2<Integer, Integer> key = new Tuple2<>(srcP, tagP);
                LinkedList<Object> val;
                if (this.receiveCache.containsKey(key)) {
                    val = this.receiveCache.get(dat);
                } else {
                    val = new LinkedList<>();
                    this.receiveCache.put(key, val);
                }
                val.add(dat);
                return false;
            }
        }
    }

    private void readObjects(Object buf, int offset, int count, ObjectInputStream ois) throws IOException, ClassNotFoundException {
        int end = offset + count;
        for (int i = offset; i < end; i++) {
            Array.set(buf, i, ois.readObject());
        }

    }

    private boolean searchDatabase(Object buf, int offset, int subcount, Datatype datatype, int source, int tag) {
        LinkedList<Object> val;
        for (Entry<Tuple2<Integer, Integer>, LinkedList<Object>> entry : this.receiveCache.entrySet()) {
            if (sourceTagMatch(entry.getKey().getItem1(), source, entry.getKey().getItem2(), tag)) {
                val = entry.getValue();
                if (val.size() > 0) {
                    System.arraycopy(val.pollFirst(), 0, buf, offset, subcount);
                    return true;
                }
            }
        }
        return false;
    }

    private boolean sourceTagMatch(int srcP, int source, int tagP, int tag) {
        return (srcP == source || source == MPI.ANY_SOURCE) && (tagP == tag || tag == MPI.ANY_TAG);
    }
}
