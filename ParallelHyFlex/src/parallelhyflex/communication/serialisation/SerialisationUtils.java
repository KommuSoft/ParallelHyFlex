package parallelhyflex.communication.serialisation;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.logging.Logger;

/**
 *
 * @author kommusoft
 */
public final class SerialisationUtils {

    /**
     *
     * @param dos
     * @param array
     * @throws IOException
     */
    public static void writeLongArray(DataOutputStream dos, long... array) throws IOException {
        dos.writeInt(array.length);
        for (long v : array) {
            dos.writeLong(v);
        }
    }

    /**
     *
     * @param dos
     * @param array
     * @throws IOException
     */
    public static void writeIntArray(DataOutputStream dos, int... array) throws IOException {
        dos.writeInt(array.length);
        for (int v : array) {
            dos.writeInt(v);
        }
    }

    /**
     *
     * @param dos
     * @param array
     * @throws IOException
     */
    public static void writeDoubleArray(DataOutputStream dos, double... array) throws IOException {
        dos.writeInt(array.length);
        for (double v : array) {
            dos.writeDouble(v);
        }
    }

    /**
     *
     * @param dos
     * @param array
     * @throws IOException
     */
    public static void writeIntArray2d(DataOutputStream dos, int[][] array) throws IOException {
        dos.writeInt(array.length);
        for (int[] v : array) {
            writeIntArray(dos, v);
        }
    }

    /**
     *
     * @param dis
     * @return
     * @throws IOException
     */
    public static long[] readLongArray(DataInputStream dis) throws IOException {
        long[] res = new long[dis.readInt()];
        for (int i = 0; i < res.length; i++) {
            res[i] = dis.readLong();
        }
        return res;
    }

    /**
     *
     * @param dis
     * @return
     * @throws IOException
     */
    public static int[] readIntArray(DataInputStream dis) throws IOException {
        int[] res = new int[dis.readInt()];
        for (int i = 0; i < res.length; i++) {
            res[i] = dis.readInt();
        }
        return res;
    }

    /**
     *
     * @param dis
     * @param values
     * @return
     * @throws IOException
     */
    public static int[] readIntArray(DataInputStream dis, int[] values) throws IOException {
        int n = dis.readInt();
        int k = Math.min(n, values.length);
        for (int i = 0; i < k; i++) {
            values[i] = dis.readInt();
        }
        for (int i = k; i < n; i++) {
            dis.readInt();
        }
        return values;
    }

    /**
     *
     * @param dis
     * @return
     * @throws IOException
     */
    public static double[] readDoubleArray(DataInputStream dis) throws IOException {
        double[] res = new double[dis.readInt()];
        for (int i = 0; i < res.length; i++) {
            res[i] = dis.readDouble();
        }
        return res;
    }

    /**
     *
     * @param dis
     * @return
     * @throws IOException
     */
    public static int[][] readIntArray2d(DataInputStream dis) throws IOException {
        int[][] res = new int[dis.readInt()][];
        for (int i = 0; i < res.length; i++) {
            res[i] = readIntArray(dis);
        }
        return res;
    }

    /**
     *
     * @param dis
     * @param values
     * @return
     * @throws IOException
     */
    public static double[] readDoubleArray(DataInputStream dis, double[] values) throws IOException {
        int n = dis.readInt();
        int k = Math.min(n, values.length);
        for (int i = 0; i < k; i++) {
            values[i] = dis.readDouble();
        }
        for (int i = k; i < n; i++) {
            dis.readDouble();
        }
        return values;
    }

    private SerialisationUtils() {
    }
    private static final Logger LOG = Logger.getLogger(SerialisationUtils.class.getName());
}
