package parallelhyflex;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Arrays;
import parallelhyflex.communication.abstraction.UdpWorld;

/**
 *
 * @author kommusoft
 */
public class UdpTester {
    
    public static void main (String[] args) throws SocketException, UnknownHostException, IOException, ClassNotFoundException {
        UdpWorld world1 = new UdpWorld(1);
        UdpWorld world2 = new UdpWorld(2);
        int[] vals = new int[] {2,3,5,8,13};
        world1.isend(new Object[]{vals}, 0, 1, null, 2, 4);
        vals = new int[] {12,13,15,18,23};
        world1.isend(new Object[]{vals}, 0, 1, null, 2, 13);
        Object[] buffer = new Object[1];
        world2.recv(buffer, 0, 1, null, 1, 13);
        System.out.println(Arrays.deepToString(buffer));
        world2.recv(buffer, 0, 1, null, 1, 4);
        System.out.println(Arrays.deepToString(buffer));
    }
    
}
