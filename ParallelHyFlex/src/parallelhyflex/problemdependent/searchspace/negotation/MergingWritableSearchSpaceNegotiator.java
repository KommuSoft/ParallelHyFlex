package parallelhyflex.problemdependent.searchspace.negotation;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
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

    protected MergingWritableSearchSpaceNegotiator(TRG generator) {
        this.generator = generator;
    }

    @Override
    public SearchSpace<TSolution> negotiate(Collection<TEC> enforceableConstraints) {
        try {
            int s = Communication.getCommunication().getSize();
            int r = Communication.getCommunication().getRank();
            byte[][] total = new byte[s][];
            total[r] = this.generatePacket(enforceableConstraints);
            Communication.AG(total, r, 1, MPI.OBJECT, total, 0, 1, MPI.OBJECT);
            return innerNegotiate(enforceableConstraints, readPacket(total));
        } catch (Exception e) {
            return null;
        }
    }

    public byte[] generatePacket(Collection<TEC> enforceableConstraints) throws IOException {
        byte[] data;
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            DataOutputStream dos = new DataOutputStream(baos);
            for (TEC c : enforceableConstraints) {
                c.write(dos);
            }
            dos.close();
            data = baos.toByteArray();
        }
        return data;
    }

    public HashSet<TEC> readPacket(byte[][] datamatrix) {
        HashSet<TEC> tecs = new HashSet<>();
        int s = Communication.getCommunication().getSize();
        int r = Communication.getCommunication().getRank();
        for (int i = 0; i < r; i++) {
            try {
                try (ByteArrayInputStream bais = new ByteArrayInputStream(datamatrix[i])) {
                    readEntry(bais, tecs);
                }
            } catch (Exception e) {
            }
        }
        for (int i = r + 1; i < s; i++) {
            try {
                try (ByteArrayInputStream bais = new ByteArrayInputStream(datamatrix[i])) {
                    readEntry(bais, tecs);
                }
            } catch (Exception e) {
            }
        }
        return tecs;
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
}