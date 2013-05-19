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
import java.net.UnknownHostException;
import mpi.Datatype;

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

    public UdpWorld(int rank) throws SocketException, UnknownHostException {
        address = InetAddress.getLocalHost();
        this.socket = new DatagramSocket(PORT_OFFSET + rank);
        this.receivePacket = new DatagramPacket(this.buffer, MAXIMUM_PACKET_SIZE);
    }

    public boolean recv(Object buf, int offset, int count, Datatype datatype, int source, int tag) throws IOException {
        int n = Array.getLength(buf);
        int end = Math.min(n, count + offset);
        int subcount = end - offset;
        this.socket.receive(this.receivePacket);
        ByteArrayInputStream bais = new ByteArrayInputStream(this.receivePacket.getData());
        ObjectInputStream ois = new ObjectInputStream(bais);
        ois.close();
        bais.close();
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
}
