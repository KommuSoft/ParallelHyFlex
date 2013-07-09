package parallelhyflex.problems.circlepositioning;

import org.junit.Assert;
import org.junit.Test;
import parallelhyflex.TestParameters;

/**
 *
 * @author kommusoft
 */
public class CirclePositioningUtilsTest {

    /**
     *
     */
    public CirclePositioningUtilsTest() {
    }

    /**
     *
     */
    @Test
    public void testCalculateCircleOverlapArea1() {
        double x1, x2, y1, y2, r1, r2, expResult;
        
        x1 = -10.0;
        y1 = -10.0;
        r1 = 5.0;
        x2 = 10.0;
        y2 = 10.0;
        r2 = 5.0;
        expResult = 0.0;
        testCCOA(x1, y1, r1, x2, y2, r2, expResult);
        
        x1 = -5.0;
        y1 = -0.0;
        r1 = 5.0;
        x2 = 5.0;
        y2 = 0.0;
        r2 = 5.0;
        expResult = 0.0;
        testCCOA(x1, y1, r1, x2, y2, r2, expResult);
        
        x1 = -5.0;
        y1 = -0.0;
        r1 = 200.0;
        x2 = 5.0;
        y2 = 0.0;
        r2 = 5.0;
        expResult = 25.0d*Math.PI;
        testCCOA(x1, y1, r1, x2, y2, r2, expResult);
        
        x1 = -5.0;
        y1 = -0.0;
        r1 = 15.0;
        x2 = 5.0;
        y2 = 0.0;
        r2 = 5.0;
        expResult = 25.0d*Math.PI;
        testCCOA(x1, y1, r1, x2, y2, r2, expResult);
        
        x1 = 0.0;
        y1 = 0.0;
        r1 = 1.0;
        x2 = 0.80794550659903441863d;
        y2 = 0.0;
        r2 = 1.0;
        expResult = 0.5d*Math.PI;
        testCCOA(x1, y1, r1, x2, y2, r2, expResult);
        
    }

    private void testCCOA(double x1, double y1, double r1, double x2, double y2, double r2, double expResult) {
        double result = CirclePositioningUtils.calculateCircleOverlapArea(x1, y1, r1, x2, y2, r2);
        Assert.assertTrue(String.format("%s versus %s",expResult,result),Math.abs(expResult-result) <= TestParameters.TOLERANCE);
        result = CirclePositioningUtils.calculateCircleOverlapArea(x2, y2, r2, x1, y1, r1);
        Assert.assertTrue(String.format("%s versus %s",expResult,result),Math.abs(expResult-result) <= TestParameters.TOLERANCE);
        result = CirclePositioningUtils.calculateCircleOverlapArea(y1, x1, r1, y2, x2, r2);
        Assert.assertTrue(String.format("%s versus %s",expResult,result),Math.abs(expResult-result) <= TestParameters.TOLERANCE);
        result = CirclePositioningUtils.calculateCircleOverlapArea(y2, x2, r2, y1, x1, r1);
        Assert.assertTrue(String.format("%s versus %s",expResult,result),Math.abs(expResult-result) <= TestParameters.TOLERANCE);
    }
}