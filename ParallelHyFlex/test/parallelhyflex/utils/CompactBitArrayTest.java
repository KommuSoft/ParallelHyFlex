/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parallelhyflex.utils;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import parallelhyflex.TestParameters;

/**
 *
 * @author kommusoft
 */
public class CompactBitArrayTest {

    public CompactBitArrayTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getBit method, of class CompactBitArray.
     */
    @Test
    public void testGetBit() {
        CompactBitArray instance = CompactBitArray.randomInstance(997);
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            int index = Utils.StaticRandom.nextInt(997);
            long exp = 0;
            if (instance.get(index)) {
                exp = 1L;
            }
            long result = instance.getBit(index);
            assertEquals(exp, result);
        }
    }

    /**
     * Test of getBit method, of class CompactBitArray.
     */
    @Test
    public void testGetBit_long() {
        CompactBitArray instance = CompactBitArray.randomInstance(997);
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            int index = Utils.StaticRandom.nextInt(997);
            long indexl = (long) index;
            assertEquals(instance.getBit(index), instance.getBit(indexl));
        }
    }

    /**
     * Test of satisfiesClause method, of class CompactBitArray.
     */
    @Test
    public void testSatisfiesClause() {
        System.out.println("satisfiesClause");
        long constraint = 0L;
        CompactBitArray instance = null;
        boolean expResult = false;
        boolean result = instance.satisfiesClause(constraint);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of SatisfiesClauseWithoutBlock method, of class CompactBitArray.
     */
    @Test
    public void testSatisfiesClauseWithoutBlock() {
        System.out.println("SatisfiesClauseWithoutBlock");
        long constraint = 0L;
        int blockindex = 0;
        CompactBitArray instance = null;
        boolean expResult = false;
        boolean result = instance.SatisfiesClauseWithoutBlock(constraint, blockindex);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNumberOfFailingClauses method, of class CompactBitArray.
     */
    @Test
    public void testGetNumberOfFailingClauses() {
        System.out.println("getNumberOfFailingClauses");
        long[] constraints = null;
        CompactBitArray instance = null;
        int expResult = 0;
        int result = instance.getNumberOfFailingClauses(constraints);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of swap method, of class CompactBitArray.
     */
    @Test
    public void testSwap() {
        CompactBitArray instance = CompactBitArray.randomInstance(997);
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            int index = Utils.StaticRandom.nextInt(997);
            CompactBitArray instance2 = instance.clone();
            instance2.swap(index);
            for (int j = 0; j < 997; j++) {
                if (j != index) {
                    Assert.assertEquals(instance.getBit(j), instance2.getBit(j));
                } else {
                    Assert.assertTrue(instance.getBit(j) != instance2.getBit(j));
                }
            }
        }
    }

    /**
     * Test of swapRange method, of class CompactBitArray.
     */
    @Test
    public void testSwapRange() {
        CompactBitArray instance = CompactBitArray.randomInstance(997);
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            int indexa = Utils.StaticRandom.nextInt(997);
            int indexb = Utils.StaticRandom.nextInt(997);
            int index1 = Math.min(indexa,indexb);
            int index2 = Math.max(indexa,indexb);
            CompactBitArray instance2 = instance.clone();
            instance2.swapRange(index1, index2);
            for (int j = 0; j < 997; j++) {
                if (j < index1 || j > index2) {
                    Assert.assertEquals(String.format("With indices %s %s and %s",index1,index2,j),instance.getBit(j), instance2.getBit(j));
                } else {
                    Assert.assertTrue(String.format("With indices %s %s and %s",index1,index2,j),instance.getBit(j) != instance2.getBit(j));
                }
            }
        }
    }

    /**
     * Test of setRange method, of class CompactBitArray.
     */
    @Test
    public void testSetRange() {
        CompactBitArray instance = CompactBitArray.randomInstance(997);
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            int indexa = Utils.StaticRandom.nextInt(997);
            int indexb = Utils.StaticRandom.nextInt(997);
            int index1 = Math.min(indexa,indexb);
            int index2 = Math.max(indexa,indexb);
            CompactBitArray instance2 = instance.clone();
            instance2.setRange(index1, index2);
            for (int j = 0; j < 997; j++) {
                if (j < index1 || j > index2) {
                    Assert.assertEquals(String.format("With indices %s %s and %s",index1,index2,j),instance.getBit(j), instance2.getBit(j));
                } else {
                    Assert.assertEquals(String.format("With indices %s %s and %s",index1,index2,j),1L,instance2.getBit(j));
                }
            }
        }
    }

    /**
     * Test of resetRange method, of class CompactBitArray.
     */
    @Test
    public void testResetRange() {
        CompactBitArray instance = CompactBitArray.randomInstance(997);
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            int indexa = Utils.StaticRandom.nextInt(997);
            int indexb = Utils.StaticRandom.nextInt(997);
            int index1 = Math.min(indexa,indexb);
            int index2 = Math.max(indexa,indexb);
            CompactBitArray instance2 = instance.clone();
            instance2.resetRange(index1, index2);
            for (int j = 0; j < 997; j++) {
                if (j < index1 || j > index2) {
                    Assert.assertEquals(String.format("With indices %s %s and %s",index1,index2,j),instance.getBit(j), instance2.getBit(j));
                } else {
                    Assert.assertEquals(String.format("With indices %s %s and %s",index1,index2,j),0L,instance2.getBit(j));
                }
            }
        }
    }

    /**
     * Test of set method, of class CompactBitArray.
     */
    @Test
    public void testSet1() {
        CompactBitArray instance = CompactBitArray.randomInstance(997);
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            int index = Utils.StaticRandom.nextInt(997);
            CompactBitArray instance2 = instance.clone();
            instance2.set(index, true);
            for (int j = 0; j < 997; j++) {
                if (j != index) {
                    Assert.assertEquals(instance.getBit(j), instance2.getBit(j));
                } else {
                    Assert.assertEquals(1L,instance2.getBit(j));
                }
            }
        }
    }
    
    /**
     * Test of set method, of class CompactBitArray.
     */
    @Test
    public void testSet2() {
        CompactBitArray instance = CompactBitArray.randomInstance(997);
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            int index = Utils.StaticRandom.nextInt(997);
            CompactBitArray instance2 = instance.clone();
            instance2.set(index, false);
            for (int j = 0; j < 997; j++) {
                if (j != index) {
                    Assert.assertEquals(instance.getBit(j), instance2.getBit(j));
                } else {
                    Assert.assertEquals(0L,instance2.getBit(j));
                }
            }
        }
    }

    /**
     * Test of equals method, of class CompactBitArray.
     */
    @Test
    public void testEquals1() {
        CompactBitArray instance = CompactBitArray.randomInstance(997);
        CompactBitArray instance2 = instance.clone();
        for(int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            Assert.assertEquals(instance,instance2);
            int index = Utils.StaticRandom.nextInt(997);
            instance2.swap(index);
            Assert.assertNotSame(instance,instance2);
            instance2.swap(index);
        }
    }

    /**
     * Test of getLength method, of class CompactBitArray.
     */
    @Test
    public void testGetLength() {
        for(int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            int length = 1+Utils.StaticRandom.nextInt(997);
            CompactBitArray instance = CompactBitArray.randomInstance(length);
            Assert.assertEquals(length,instance.getLength());
        }
    }

    /**
     * Test of getBlockLength method, of class CompactBitArray.
     */
    @Test
    public void testGetBlockLength() {
        for(int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            int length = 1+Utils.StaticRandom.nextInt(997);
            int block = (int) Math.ceil(length/64.0d);
            CompactBitArray instance = CompactBitArray.randomInstance(length);
            Assert.assertEquals(block,instance.getBlockLength());
        }
    }

    /**
     * Test of willSwap method, of class CompactBitArray.
     */
    @Test
    public void testWillSwap() {
        System.out.println("willSwap");
        long constraint = 0L;
        int index = 0;
        CompactBitArray instance = null;
        boolean expResult = false;
        boolean result = instance.willSwap(constraint, index);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of swapGetBit method, of class CompactBitArray.
     */
    @Test
    public void testSwapGetBit() {
        CompactBitArray instance = CompactBitArray.randomInstance(997);
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            int index = Utils.StaticRandom.nextInt(997);
            CompactBitArray instance2 = instance.clone();
            int bit = instance2.swapGetBit(index);
            Assert.assertEquals(instance2.getBit(index), bit);
            for (int j = 0; j < 997; j++) {
                if (j != index) {
                    Assert.assertEquals(instance.getBit(j), instance2.getBit(j));
                } else {
                    Assert.assertTrue(instance.getBit(j) != instance2.getBit(j));
                }
            }
        }
    }
}