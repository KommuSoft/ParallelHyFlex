package parallelhyflex.memory.stateexchange;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 *
 * @author kommusoft
 * TODO: more efficient serialisation (only modified items)
 */
public class ExchangeState {
    
    private int length = 0;
    private Serializable[] serializables = new Serializable[1];
    
    public ExchangeState (int n) {
        this.length = n;
        this.serializables = new Serializable[n];
    }
    
    public byte[] writePacket () throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeInt(length);
        for(Serializable s : serializables) {
            oos.writeObject(s);
        }
        oos.close();
        byte[] data = baos.toByteArray();
        baos.close();
        return data;
    }
    
    public <T extends Serializable> T readObject (int index) {
        return (T) this.serializables[index];
    }
    
    public void reduce () {
        int l = this.length;
        int L = this.serializables.length;
        if(l < L/2) {
            Serializable[] ser = new Serializable[l];
            System.arraycopy(this.serializables, 0, ser, 0, l);
            this.serializables = ser;
        }
        else {
            for(int i = l; i < L; i++) {
                this.serializables[i] = null;
            }
        }
    }
    
    public void readPacket (byte[] data) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        ObjectInputStream ois = new ObjectInputStream(bais);
        int l = ois.readInt();
        Serializable[] array = this.serializables;
        if(l > serializables.length) {
            array = new Serializable[l];
        }
        for(int i = 0; i < l; i++) {
            this.serializables[i] = (Serializable) ois.readObject();
        }
        this.serializables = array;
        this.length = l;
        ois.close();
        bais.close();
    }
    
}
