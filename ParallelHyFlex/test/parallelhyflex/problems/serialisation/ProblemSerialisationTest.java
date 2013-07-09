package parallelhyflex.problems.serialisation;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import junit.framework.Assert;
import parallelhyflex.TestParameters;
import parallelhyflex.problemdependent.problem.Problem;
import parallelhyflex.problemdependent.problem.ProblemReader;
import parallelhyflex.problemdependent.solution.Solution;
import parallelhyflex.problemdependent.solution.SolutionGenerator;
import parallelhyflex.problems.ProblemTestBase;

/**
 *
 * @author kommusoft
 */
public abstract class ProblemSerialisationTest<TSG extends SolutionGenerator<TS>, TP extends Problem<TS>, TPG extends ProblemReader<TS, TP>, TS extends Solution<TS>> extends ProblemTestBase<TSG, TP, TPG, TS> {
    
    /**
     *
     * @throws IOException
     */
    public void testSerializationDeserialization() throws IOException {
        for(int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            this.renewProblemGenerator();
            this.renewProblem();
            ByteArrayInputStream bais;
            try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
                DataOutputStream dos = new DataOutputStream(baos);
                this.getTsp().write(dos);
                dos.close();
                bais = new ByteArrayInputStream(baos.toByteArray());
            }
            DataInputStream dis = new DataInputStream(bais);
            TP tsp2 = this.getTspg().readAndGenerate(dis);
            Assert.assertEquals(this.getTsp(), tsp2);
            dis.close();
            bais.close();
        }
    }
    
}
