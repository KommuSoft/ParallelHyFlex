/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parallelhyflex.problems.threesat.constraints;

import java.io.DataInputStream;
import java.io.IOException;
import parallelhyflex.problemdependent.EnforceableConstraint;
import parallelhyflex.problemdependent.EnforceableConstraintGeneratorBase;
import parallelhyflex.problems.threesat.ThreeSatProblem;
import parallelhyflex.problems.threesat.ThreeSatSolution;

/**
 *
 * @author kommusoft
 */
public class ThreeSatWritableEnforceableConstraintGenerator extends EnforceableConstraintGeneratorBase<ThreeSatSolution,ThreeSatProblem,EnforceableConstraint<ThreeSatSolution>> {

    public ThreeSatWritableEnforceableConstraintGenerator (ThreeSatProblem problem) {
        super(problem);
    }
    
    @Override
    public EnforceableConstraint<ThreeSatSolution> readAndGenerate(DataInputStream dis) throws IOException {
        long vala = dis.readLong();
        if((vala&ThreeSatWritableEnforceableConstraint1.MASK_BIT) != 0x00) {
            return new ThreeSatWritableEnforceableConstraint1(this.getProblem(),ThreeSatWritableEnforceableConstraint1.MASK&vala);
        }
        else {
            int distance = (int) vala;
            ThreeSatSolution tss = this.getProblem().getGenerator().readAndGenerate(dis);
            return new ThreeSatWritableEnforceableConstraint2(this.getProblem(),tss,distance);
        }
    }
    
}
