package parallelhyflex.problems.constraint;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import junit.framework.Assert;
import parallelhyflex.TestParameters;
import parallelhyflex.problemdependent.constraints.EnforceableConstraint;
import parallelhyflex.problemdependent.constraints.EnforceableConstraintGenerator;
import parallelhyflex.problemdependent.constraints.WritableEnforceableConstraintBase;
import parallelhyflex.problemdependent.problem.Problem;
import parallelhyflex.problemdependent.problem.ProblemReader;
import parallelhyflex.problemdependent.solution.Solution;
import parallelhyflex.problemdependent.solution.SolutionGenerator;
import parallelhyflex.problems.ProblemTestBase;

/**
 *
 * @param <TSG> 
 * @param <TP> 
 * @param <TPG> 
 * @param <TS> 
 * @author kommusoft
 */
public abstract class WritableEnforceableConstraintTest<TSG extends SolutionGenerator<TS>, TP extends Problem<TS>, TPG extends ProblemReader<TS, TP>, TS extends Solution<TS>> extends ProblemTestBase<TSG, TP, TPG, TS> {

    private WritableEnforceableConstraintBase<TS, TP> tswec;
    private EnforceableConstraintGenerator<TS, ? extends EnforceableConstraint<TS>> ecg;
    private TS tss2;

    public abstract WritableEnforceableConstraintBase<TS, TP> renewWritableEnforceableConstraint();
    
    public abstract EnforceableConstraintGenerator<TS, ? extends EnforceableConstraint<TS>> renewEnforceableConstraintGenerator ();

    public void testEnforceTrue1() {
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            this.renewProblemGenerator();
            this.renewProblem();
            this.renewSolutionGenerator();
            this.renewSolution();
            setTswec(this.renewWritableEnforceableConstraint());
            getTswec().enforceTrue(getTss());
            Assert.assertTrue(getTswec().isSatisfied(getTss()));
            Assert.assertFalse(getTswec().isNotSatisfied(getTss()));
        }
    }

    public void testEnforceTrue2() {
        this.renewProblemGenerator();
        this.renewProblem();
        this.renewSolutionGenerator();
        this.renewSolution();
        setTswec(this.renewWritableEnforceableConstraint());
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            this.renewSolution2();
            getTswec().enforceTrue(getTss2());
            Assert.assertTrue(getTswec().isSatisfied(getTss2()));
            Assert.assertFalse(getTswec().isNotSatisfied(getTss2()));
        }
    }

    protected void renewSolution2() {
        this.setTss2(getTsg().generateSolution());
    }

    public void testEnforceFalse1() {
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            this.renewProblemGenerator();
            this.renewProblem();
            this.renewSolutionGenerator();
            this.renewSolution();
            setTswec(this.renewWritableEnforceableConstraint());
            getTswec().enforceFalse(getTss());
            Assert.assertFalse(getTswec().isSatisfied(getTss()));
            Assert.assertTrue(getTswec().isNotSatisfied(getTss()));
        }
    }

    public void testEnforceFalse2() {
        this.renewProblemGenerator();
        this.renewProblem();
        this.renewSolutionGenerator();
        this.renewSolution();
        setTswec(this.renewWritableEnforceableConstraint());
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            this.renewSolution2();
            getTswec().enforceFalse(getTss2());
            Assert.assertFalse(getTswec().isSatisfied(getTss2()));
            Assert.assertTrue(getTswec().isNotSatisfied(getTss2()));
        }
    }

    public void testSerializeDeserialize() throws Exception {
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            this.setEcg(this.renewEnforceableConstraintGenerator());
            this.renewProblemGenerator();
            this.renewProblem();
            this.renewSolutionGenerator();
            this.renewSolution();
            setTswec(this.renewWritableEnforceableConstraint());
            ByteArrayInputStream bais;
            try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
                try (DataOutputStream dos = new DataOutputStream(baos)) {
                    this.getTswec().write(dos);
                }
                bais = new ByteArrayInputStream(baos.toByteArray());
            }
            try (DataInputStream dis = new DataInputStream(bais)) {
                EnforceableConstraint<TS> tswec2 = getEcg().readAndGenerate(dis);
                Assert.assertEquals(this.getTswec(), tswec2);
            }
            bais.close();
        }
    }

    /**
     * @return the tss2
     */
    public TS getTss2() {
        return tss2;
    }

    /**
     * @param tss2 the tss2 to set
     */
    public void setTss2(TS tss2) {
        this.tss2 = tss2;
    }

    /**
     * @return the tswec
     */
    public WritableEnforceableConstraintBase<TS, TP> getTswec() {
        return tswec;
    }

    /**
     * @param tswec the tswec to set
     */
    public void setTswec(WritableEnforceableConstraintBase<TS, TP> tswec) {
        this.tswec = tswec;
    }

    /**
     * @return the ecg
     */
    public EnforceableConstraintGenerator<TS, ? extends EnforceableConstraint<TS>> getEcg() {
        return ecg;
    }

    /**
     * @param ecg the ecg to set
     */
    public void setEcg(EnforceableConstraintGenerator<TS, ? extends EnforceableConstraint<TS>> ecg) {
        this.ecg = ecg;
    }
    
}
