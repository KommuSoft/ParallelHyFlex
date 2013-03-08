/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parallelhyflex.problems.threesat;

import java.util.Random;
import parallelhyflex.SolutionGeneratorBase;

/**
 *
 * @author kommusoft
 */
public class ThreeSatSolutionGenerator extends SolutionGeneratorBase<ThreeSatSolution> {
    
    private final int n64;
    
    public ThreeSatSolutionGenerator (int n64) {
        this.n64 = n64;
    }
    
    @Override
    public ThreeSatSolution generateSolution() {
        long[] data = new long[n64];
        Random rand = this.getRandom();
        for(int i = 0; i < n64; i++) {
            data[i] = rand.nextLong();
        }
        return new ThreeSatSolution(data);
    }
    
}
