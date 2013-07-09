package parallelhyflex.utils;

import java.util.Collection;
import junit.framework.Assert;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author kommusoft
 */
public class StatisticsUtilsTest {
    
    /**
     *
     */
    public StatisticsUtilsTest() {
    }

    /**
     *
     */
    @Test
    public void testMean_doubleArr() {
        System.out.println("mean");
        double[] vals = null;
        double expResult = 0.0;
        double result = StatisticsUtils.mean(vals);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     *
     */
    @Test
    public void testErf() {
        System.out.println("erf");
        double z = 0.0;
        double expResult = 0.0;
        double result = StatisticsUtils.erf(z);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     *
     */
    @Test
    public void testNormalCdf() {
        Assert.assertEquals(0.5d,StatisticsUtils.normalCdf(0.0d,1.0d,0.0d));
        Assert.assertTrue(0.5d<StatisticsUtils.normalCdf(-1.0d,1.0d,0.0d));
        Assert.assertTrue(0.5d>StatisticsUtils.normalCdf(1.0d,1.0d,0.0d));
        Assert.assertTrue(StatisticsUtils.normalCdf(-1.0d,1.0d,0.0d)>StatisticsUtils.normalCdf(-1.0d,2.0d,0.0d));
        Assert.assertTrue(StatisticsUtils.normalCdf(1.0d,1.0d,0.0d)<StatisticsUtils.normalCdf(1.0d,2.0d,0.0d));
        Assert.assertEquals(1.0d,StatisticsUtils.normalCdf(Double.NEGATIVE_INFINITY,1.0d,0.0d));
        Assert.assertEquals(0.0d,StatisticsUtils.normalCdf(Double.POSITIVE_INFINITY,1.0d,0.0d));
    }

    /**
     *
     */
    @Test
    public void testMean_Collection() {
        System.out.println("mean");
        Collection<Double> vals = null;
        double expResult = 0.0;
        double result = StatisticsUtils.mean(vals);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     *
     */
    @Test
    public void testMean_intArr() {
        System.out.println("mean");
        int[] vals = null;
        double expResult = 0.0;
        double result = StatisticsUtils.mean(vals);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     *
     */
    @Test
    public void testVariation_doubleArr() {
        System.out.println("variation");
        double[] vals = null;
        double expResult = 0.0;
        double result = StatisticsUtils.variation(vals);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     *
     */
    @Test
    public void testVariation_Collection() {
        System.out.println("variation");
        Collection<Double> vals = null;
        double expResult = 0.0;
        double result = StatisticsUtils.variation(vals);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     *
     */
    @Test
    public void testVariation_intArr() {
        System.out.println("variation");
        int[] vals = null;
        double expResult = 0.0;
        double result = StatisticsUtils.variation(vals);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     *
     */
    @Test
    public void testVariation_doubleArr_double() {
        System.out.println("variation");
        double[] vals = null;
        double mean = 0.0;
        double expResult = 0.0;
        double result = StatisticsUtils.variation(vals, mean);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     *
     */
    @Test
    public void testVariation_Collection_double() {
        System.out.println("variation");
        Collection<Double> vals = null;
        double mean = 0.0;
        double expResult = 0.0;
        double result = StatisticsUtils.variation(vals, mean);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     *
     */
    @Test
    public void testVariation_intArr_double() {
        System.out.println("variation");
        int[] vals = null;
        double mean = 0.0;
        double expResult = 0.0;
        double result = StatisticsUtils.variation(vals, mean);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     *
     */
    @Test
    public void testMin_doubleArr() {
        System.out.println("min");
        double[] vals = null;
        double expResult = 0.0;
        double result = StatisticsUtils.min(vals);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     *
     */
    @Test
    public void testMin_Collection() {
        System.out.println("min");
        Collection<Double> vals = null;
        double expResult = 0.0;
        double result = StatisticsUtils.min(vals);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     *
     */
    @Test
    public void testMin_intArr() {
        System.out.println("min");
        int[] vals = null;
        double expResult = 0.0;
        double result = StatisticsUtils.min(vals);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     *
     */
    @Test
    public void testMax_doubleArr() {
        System.out.println("max");
        double[] vals = null;
        double expResult = 0.0;
        double result = StatisticsUtils.max(vals);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     *
     */
    @Test
    public void testMax_Collection() {
        System.out.println("max");
        Collection<Double> vals = null;
        double expResult = 0.0;
        double result = StatisticsUtils.max(vals);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     *
     */
    @Test
    public void testMax_intArr() {
        System.out.println("max");
        int[] vals = null;
        double expResult = 0.0;
        double result = StatisticsUtils.max(vals);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     *
     */
    @Test
    public void testEntropy() {
        System.out.println("entropy");
        int[] vals = null;
        double expResult = 0.0;
        double result = StatisticsUtils.entropy(vals);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     *
     */
    @Test
    public void testPqEntropy() {
        System.out.println("pqEntropy");
        double p = 0.0;
        double expResult = 0.0;
        double result = StatisticsUtils.pqEntropy(p);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}