package parallelhyflex.problemdependent.searchspace.negotation;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import parallelhyflex.communication.AsynchronousGatherAll;
import parallelhyflex.communication.Communication;
import parallelhyflex.communication.serialisation.ReadableGenerator;
import parallelhyflex.problemdependent.constraints.WriteableEnforceableConstraint;
import parallelhyflex.problemdependent.searchspace.SearchSpace;
import parallelhyflex.problemdependent.solution.Solution;

/**
 *
 * @param <TSolution>
 * @param <TEC>
 * @param <TRG>
 * @author kommusoft
 */
public abstract class MergingWriteableSearchSpaceNegotiator<TSolution extends Solution<TSolution>, TEC extends WriteableEnforceableConstraint<TSolution>, TRG extends ReadableGenerator<TEC>> extends AsynchronousGatherAll<byte[]> implements WriteableSearchSpaceNegotiator<TSolution, TEC> {

    public static final int SendTag = 2;
    private final TRG generator;
    private boolean active;
    private boolean ready;
    private final ArrayList<TEC> own = new ArrayList<>();
    private SearchSpace<TSolution> ss;

    protected MergingWriteableSearchSpaceNegotiator(TRG generator) {
        super(SendTag);
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
        active = true;
        ready = false;
        own.addAll(enforceableConstraints);
        try {
            super.send(this.generatePacket(enforceableConstraints));
        } catch (IOException ex) {
            Logger.getLogger(MergingWriteableSearchSpaceNegotiator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

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

    public void readPacket(ArrayList<TEC> tecs, byte[] datamatrix) {
        try (ByteArrayInputStream bais = new ByteArrayInputStream(datamatrix)) {
            readEntry(bais, tecs);
        } catch (IOException ex) {
            Logger.getLogger(MergingWriteableSearchSpaceNegotiator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void readEntry(ByteArrayInputStream bais, Collection<TEC> tecs) throws IOException {
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
    public void receivePacket(int from, int tag, Object data) throws Exception {
        super.receivePacket(from, tag, data);
        if (super.isReady()) {
            ArrayList<TEC> other = new ArrayList<>();
            int i = 0;
            int r = Communication.getCommunication().getRank();
            for (byte[] seg : this) {
                if(i != r) {
                    this.readPacket(other, seg);
                }
                i++;
            }
            this.reset();
            this.ss = this.innerNegotiate(own, other);
            own.clear();
            this.active = false;
            this.ready = true;
        }
    }
}