package parallelhyflex.memory.stateexchange;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author kommusoft TODO: more efficient serialisation (only modified items)
 */
public class ExchangeState extends ArrayList<Serializable> {

    /**
     *
     * @return
     * @throws IOException
     */
    public byte[] writePacket() throws IOException {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream(); ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeInt(this.size());
            for (Serializable s : this) {
                oos.writeObject(s);
            }
            return baos.toByteArray();
        }
    }

    /**
     *
     * @param <T>
     * @param index
     * @return
     */
    public <T extends Serializable> T readObject(int index) {
        if (index < this.size() && index >= 0x00) {
            try {
                return (T) this.get(index);
            } catch (Exception e) {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     *
     * @param data
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void readPacket(byte[] data) throws IOException, ClassNotFoundException {
        try (ByteArrayInputStream bais = new ByteArrayInputStream(data); ObjectInputStream ois = new ObjectInputStream(bais)) {
            int l = ois.readInt();
            int s = this.size();
            if (l < s) {
                for (int i = s - 1; i >= l; i--) {
                    this.remove(i);
                }
                s = l;
            }
            for (int i = 0; i < s; i++) {
                this.set(i, (Serializable) ois.readObject());
            }
            for (int i = s; i < l; i++) {
                this.add((Serializable) ois.readObject());
            }
        }
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ExchangeState[");
        for (Serializable s : this) {
            sb.append(sb);
        }
        sb.append(" ]");
        return sb.toString();
    }
}
