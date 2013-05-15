package parallelhyflex.problemdependent.searchspace.negotation;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import mpi.MPI;
import parallelhyflex.communication.Communication;
import parallelhyflex.communication.ReadableGenerator;
import parallelhyflex.problemdependent.constraints.WritableEnforceableConstraint;
import parallelhyflex.problemdependent.searchspace.SearchSpace;
import parallelhyflex.problemdependent.solution.Solution;

/**
 *
 * @author kommusoft
 */
public abstract class MergingWritableSearchSpaceNegotiator<TSolution extends Solution<TSolution>, TEC extends WritableEnforceableConstraint<TSolution>, TRG extends ReadableGenerator<TEC>> implements WritableSearchSpaceNegotiator<TSolution, TEC> {

    private final TRG generator;
    public static final int SendTag = 2;
    private boolean active;
    private boolean ready;
    private SearchSpace<TSolution> ss;
    private final HashSet<TEC> own, others;
    private int todo = 0;

    protected MergingWritableSearchSpaceNegotiator(TRG generator) {
        this.own = new HashSet<>();
        this.others = new HashSet<>();
        this.generator = generator;
    }

    @Override
    public boolean isReady() {
        return this.ready;
    }

    @Override
    public boolean isActive() {
        return this.active;
    }

    @Override
    public SearchSpace<TSolution> getSearchSpace() {
        this.ready = false;
        return this.ss;
    }

    @Override
    public void sendEnforceableConstraints(Collection<TEC> enforceableConstraints) {
        try {
            own.clear();
            active = true;
            ready = false;
            own.addAll(enforceableConstraints);
            Communication.nbB(this.generatePacket(enforceableConstraints), 0, 1, MPI.OBJECT, SendTag);
            todo += Communication.getCommunication().getSize() - 1;
        } catch (IOException ex) {
            Logger.getLogger(MergingWritableSearchSpaceNegotiator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Object[] generatePacket(Collection<TEC> enforceableConstraints) throws IOException {
        byte[] data;
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            try (DataOutputStream dos = new DataOutputStream(baos)) {
                for (TEC c : enforceableConstraints) {
                    c.write(dos);
                }
            }
            data = baos.toByteArray();
        }
        return new Object[]{data};
    }

    public void readPacket(HashSet<TEC> tecs, byte[] datamatrix) {
        try (ByteArrayInputStream bais = new ByteArrayInputStream(datamatrix)) {
            readEntry(bais, tecs);
        } catch (IOException ex) {
            Logger.getLogger(MergingWritableSearchSpaceNegotiator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void readEntry(ByteArrayInputStream bais, HashSet<TEC> tecs) throws IOException {
        try (DataInputStream dis = new DataInputStream(bais)) {
            while (bais.available() > 0) {
                tecs.add(this.getGenerator().readAndGenerate(dis));
            }
        }
    }

    protected abstract SearchSpace<TSolution> innerNegotiate(Collection<TEC> own, Collection<TEC> others);

    /**
     * @return the generator
     */
    protected TRG getGenerator() {
        return generator;
    }

    @Override
    public int[] getPacketTags() {
        return new int[]{SendTag};
    }

    @Override
    public void receivePacket(int from, int tag, Object data) throws Exception {
        this.readPacket(others, (byte[]) data);
        this.todo--;
        if (todo <= 0) {
            this.ss = this.innerNegotiate(own, others);
            others.clear();
            this.ready = true;
            this.active = false;
        }
    }
}