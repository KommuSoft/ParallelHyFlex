package parallelhyflex.memory.senders;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import parallelhyflex.communication.Communication;
import parallelhyflex.communication.SendTagged;
import parallelhyflex.problemdependent.solution.Solution;

/**
 *
 * @author kommusoft
 */
public abstract class PushSenderBase<TSolution extends Solution<TSolution>> implements PushSender<TSolution>, SendTagged {
    
    public static final int SendTag = 0x00;

    public Object[] generatePacket(int index, TSolution solution) {
        Object[] data = new Object[3];
        data[0] = Communication.getCommunication().getRank();
        data[1] = index;
        try {
            try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
                DataOutputStream dos = new DataOutputStream(baos);
                solution.write(dos);
                dos.close();
                data[2] = baos.toByteArray();
            }
        } catch (Exception e) {
            Communication.log(e);
        }
        return new Object[] {data};
    }

    @Override
    public int getSendTag() {
        return SendTag;
    }
}
