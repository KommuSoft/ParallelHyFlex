package parallelhyflex.communication;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 *
 * @author kommusoft
 */
public class SerialisationUtils {

    private SerialisationUtils() {
    }

    public static void writeLongArray(DataOutputStream dos, long... array) throws IOException {
        dos.writeInt(array.length);
        for (long v : array) {
            dos.writeLong(v);
        }
    }

    public static void writeIntArray(DataOutputStream dos, int... array) throws IOException {
        dos.writeInt(array.length);
        for (int v : array) {
            dos.writeInt(v);
        }
    }

    public static void writeDoubleArray(DataOutputStream dos, double... array) throws IOException {
        dos.writeInt(array.length);
        for (double v : array) {
            dos.writeDouble(v);
        }
    }

    public static void writeIntArray2d(DataOutputStream dos, int[][] array) throws IOException {
        dos.writeInt(array.length);
        for (int[] v : array) {
            writeIntArray(dos, v);
        }
    }

    public static long[] readLongArray(DataInputStream dis) throws IOException {
        long[] res = new long[dis.readInt()];
        for (int i = 0; i < res.length; i++) {
            res[i] = dis.readLong();
        }
        return res;
    }

    public static int[] readIntArray(DataInputStream dis) throws IOException {
        int[] res = new int[dis.readInt()];
        for (int i = 0; i < res.length; i++) {
            res[i] = dis.readInt();
        }
        return res;
    }

    public static double[] readDoubleArray(DataInputStream dis) throws IOException {
        double[] res = new double[dis.readInt()];
        for (int i = 0; i < res.length; i++) {
            res[i] = dis.readDouble();
        }
        return res;
    }

    public static int[][] readIntArray2d(DataInputStream dis) throws IOException {
        int[][] res = new int[dis.readInt()][];
        for (int i = 0; i < res.length; i++) {
            res[i] = readIntArray(dis);
        }
        return res;
    }
}
