/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parallelhyflex.problems.fdcsp.problem;

import parallelhyflex.TestParameters;
import parallelhyflex.utils.Utils;

/**
 *
 * @author kommusoft
 */
public abstract class FiniteIntegerDomainTestBase {

    public int randomDomainValue() {
        return Utils.StaticRandom.nextInt(TestParameters.DOMAIN_HIGH + 1 - TestParameters.DOMAIN_LOW) + TestParameters.DOMAIN_LOW;
    }
    
}
