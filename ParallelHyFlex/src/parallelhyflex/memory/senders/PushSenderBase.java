/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parallelhyflex.memory.senders;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import parallelhyflex.communication.Communication;
import parallelhyflex.problemdependent.solution.Solution;

/**
 *
 * @author kommusoft
 */
public abstract class PushSenderBase<TSolution extends Solution<TSolution>> implements PushSender<TSolution> {

    public Object[] generatePacket(int index, TSolution solution) {
        Object[] data = new Object[3];
        data[0] = Communication.getCommunication().getRank();
        data[1] = index;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataOutputStream dos = new DataOutputStream(baos);
            solution.write(dos);
            dos.close();
            data[2] = baos.toByteArray();
            baos.close();
        } catch (Exception e) {
            Communication.Log(e);
        }
        return data;
    }
}
