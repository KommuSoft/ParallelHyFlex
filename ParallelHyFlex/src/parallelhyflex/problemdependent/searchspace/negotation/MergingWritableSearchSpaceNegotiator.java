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
import parallelhyflex.communication.AsynchronousGatherAll;
import parallelhyflex.communication.serialisation.ReadableGenerator;
import parallelhyflex.problemdependent.constraints.WritableEnforceableConstraint;
import parallelhyflex.problemdependent.searchspace.SearchSpace;
import parallelhyflex.problemdependent.solution.Solution;

/**
 *
 * @author kommusoft
 */
public abstract class MergingWritableSearchSpaceNegotiator<TSolution extends Solution<TSolution>, TEC extends WritableEnforceableConstraint<TSolution>, TRG extends ReadableGenerator<TEC>> extends AsynchronousGatherAll<byte[]> implements WritableSearchSpaceNegotiator<TSolution, TEC> {
    /**
     *
     */
    public static final int SendTag = 2;

    private final TRG generator;
    private boolean active;
    private boolean ready;
    private final HashSet<TEC> own = new HashSet<>();
    private SearchSpace<TSolution> ss;

    /**
     *
     * @param generator
     */
    protected MergingWritableSearchSpaceNegotiator(TRG generator) {
        super(SendTag);
        this.generator = generator;
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isReady() {
        return this.ready;
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isActive() {
        return this.active;
    }

    /**
     *
     * @return
     */
    @Override
    public SearchSpace<TSolution> getSearchSpace() {
        this.ready = false;
        return this.ss;
    }

    /**
     *
     * @param enforceableConstraints
     */
    @Override
    public void sendEnforceableConstraints(Collection<TEC> enforceableConstraints) {
        active = true;
        ready = false;
        own.addAll(enforceableConstraints);
        try {
            super.send(this.generatePacket(enforceableConstraints));
        } catch (IOException ex) {
            Logger.getLogger(MergingWritableSearchSpaceNegotiator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param enforceableConstraints
     * @return
     * @throws IOException
     */
    public byte[] generatePacket(Collection<TEC> enforceableConstraints) throws IOException {
        byte[] data;
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            try (DataOutputStream dos = new DataOutputStream(baos)) {
                for (TEC c : enforceableConstraints) {
                    c.write(dos);
                }
            }
            data = baos.toByteArray();
        }
        return data;
    }

    /**
     *
     * @param tecs
     * @param datamatrix
     */
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

    /**
     *
     * @param own
     * @param others
     * @return
     */
    protected abstract SearchSpace<TSolution> innerNegotiate(Collection<TEC> own, Collection<TEC> others);

    /**
     * @return the generator
     */
    protected TRG getGenerator() {
        return generator;
    }

    /**
     *
     * @param from
     * @param tag
     * @param data
     * @throws Exception
     */
    @Override
    public void receivePacket(int from, int tag, Object data) throws Exception {
        super.receivePacket(from, tag, data);
        if (super.isReady()) {
            HashSet<TEC> other = new HashSet<>();
            for (byte[] seg : this) {
                this.readPacket(other, seg);
            }
            this.reset();
            this.ss = this.innerNegotiate(own, other);
            this.active = false;
            this.ready = true;
        }
    }
}