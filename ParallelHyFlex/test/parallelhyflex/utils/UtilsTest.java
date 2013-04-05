/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parallelhyflex.utils;

import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author kommusoft
 */
public class UtilsTest {
    
    public UtilsTest() {
    }

    /**
     * Test of getLengthIndex method, of class Utils.
     */
    @Test
    public void testGetLengthIndex() {
    }

    /**
     * Test of countOnes method, of class Utils.
     */
    @Test
    public void testCountOnes() {
    }

    /**
     * Test of arrayEquality method, of class Utils.
     */
    @Test
    public void testArrayEquality_longArr_longArr() {
    }

    /**
     * Test of stringReverse method, of class Utils.
     */
    @Test
    public void testStringReverse() {
    }

    /**
     * Test of arrayEquality method, of class Utils.
     */
    @Test
    public void testArrayEquality_GenericType_GenericType() {
    }

    /**
     * Test of getLimitedModuloEnumerable method, of class Utils.
     */
    @Test
    public void testGetLimitedModuloEnumerable_3args() {
        ArrayList<Integer> a = Utils.toArrayList(Utils.getLimitedModuloEnumerable(4,3,10));
        Assert.assertArrayEquals(new Integer[] {4,7,0,3},a.toArray(new Integer[] {}));
    }

    /**
     * Test of getLimitedModuloEnumerable method, of class Utils.
     */
    @Test
    public void testGetLimitedModuloEnumerable_int_int() {
        ArrayList<Integer> a = Utils.toArrayList(Utils.getLimitedModuloEnumerable(4,10));
        Assert.assertArrayEquals(new Integer[] {4,5,6,7,8,9,0,1,2,3},a.toArray(new Integer[] {}));
    }
}