/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parallelhyflex;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import mpi.MPI;

/**
 *
 * @author kommusoft
 */
public class MemorySlots<TSolution extends Solution<TSolution>> {
    
    private final boolean local;
    private final MemoryExchangePolicy policy;
    private final Object[] storage;
    
    public MemorySlots (boolean isLocal, int memorySize, MemoryExchangePolicy policy) {
        this.local = isLocal;
        this.policy = policy;
        this.storage = new Object[memorySize];
        //TODO: memorySize
    }

    /**
     * @return the isLocal
     */
    public boolean isLocal() {
        return local;
    }

    /**
     * @return the policy
     */
    public MemoryExchangePolicy getPolicy() {
        return policy;
    }

    public void pushSolution(int index) {
        Communication.Log("pushing "+index);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        try {
            dos.writeInt(Communication.getCommunication().getRank());
            dos.writeInt(index);
            TSolution tsol = (TSolution) this.storage[index];
            tsol.writeSolution(dos);
            dos.close();
            baos.close();
            byte[] ba = baos.toByteArray();
            Communication.BC(ba,0,ba.length,MPI.BYTE,Communication.getCommunication().getRank());
        } catch (IOException ex) {
            Logger.getLogger(MemorySlots.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void setSolution (int index, TSolution sol) {
        this.storage[index] = sol;
        this.pushSolution(index);
    }
    public TSolution getSolution (int index) {
        return (TSolution) this.storage[index];
        //TODO: update queues
    }
    
    @Override
    public String toString () {
        return String.format("MemorySlots[%s,%s,%s]",this.isLocal(),this.getPolicy(),this.storage.length);
    }

    public int getSize() {
        return this.storage.length;
    }
    
}
