/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parallelhyflex.problems.fdcsp.problem;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.TreeSet;
import java.util.Iterator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import parallelhyflex.TestParameters;
import parallelhyflex.algebra.InductiveBiasException;
import parallelhyflex.utils.Utils;

/**
 *
 * @author kommusoft
 */
public class MutableFiniteIntegerDomainTest {

    public MutableFiniteIntegerDomainTest() {
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
     * Test of all method, of class MutableFiniteIntegerDomain.
     */
    @Test
    public void testAll() {
        System.out.println("all");
        MutableFiniteIntegerDomain expResult = null;
        MutableFiniteIntegerDomain result = MutableFiniteIntegerDomain.all();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of low method, of class MutableFiniteIntegerDomain.
     */
    @Test
    public void testLow() {
        System.out.println("low");
        MutableFiniteIntegerDomain instance = new MutableFiniteIntegerDomain();
        Integer expResult = null;
        Integer result = instance.low();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of high method, of class MutableFiniteIntegerDomain.
     */
    @Test
    public void testHigh() {
        System.out.println("high");
        MutableFiniteIntegerDomain instance = new MutableFiniteIntegerDomain();
        Integer expResult = null;
        Integer result = instance.high();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getIth method, of class MutableFiniteIntegerDomain.
     */
    @Test
    public void testGetIth() {
        System.out.println("getIth");
        int index = 0;
        MutableFiniteIntegerDomain instance = new MutableFiniteIntegerDomain();
        Integer expResult = null;
        Integer result = instance.getIth(index);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of size method, of class MutableFiniteIntegerDomain.
     */
    @Test
    public void testSize() {
        System.out.println("size");
        MutableFiniteIntegerDomain instance = new MutableFiniteIntegerDomain();
        int expResult = 0;
        int result = instance.size();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clear method, of class MutableFiniteIntegerDomain.
     */
    @Test
    public void testClear() {
        System.out.println("clear");
        MutableFiniteIntegerDomain instance = new MutableFiniteIntegerDomain();
        boolean expResult = false;
        boolean result = instance.clear();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class MutableFiniteIntegerDomain.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        MutableFiniteIntegerDomain instance = new MutableFiniteIntegerDomain();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of contains method, of class MutableFiniteIntegerDomain.
     */
    @Test
    public void testContains() {
        System.out.println("contains");
        Integer value = null;
        MutableFiniteIntegerDomain instance = new MutableFiniteIntegerDomain();
        boolean expResult = false;
        boolean result = instance.contains(value);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of add method, of class MutableFiniteIntegerDomain.
     */
    @Test
    public void testAdd_int() {
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            FiniteIntegerDomain fid = new MutableFiniteIntegerDomain();
            TreeSet<Integer> set = new TreeSet<>();
            for (int j = 0; j < TestParameters.LOOP2_PARAMETER; j++) {
                int v = Utils.StaticRandom.nextInt(TestParameters.DOMAIN_HIGH + 1 - TestParameters.DOMAIN_LOW) + TestParameters.DOMAIN_LOW;
                fid.add(v);
                set.add(v);
                for (int k = TestParameters.DOMAIN_LOW; k <= TestParameters.DOMAIN_HIGH; k++) {
                    Assert.assertEquals(set.contains(k), fid.contains(k));
                }
            }
        }
    }

    /**
     * Test of add method, of class MutableFiniteIntegerDomain.
     */
    @Test
    public void testAdd_int_int() {
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            FiniteIntegerDomain fid = new MutableFiniteIntegerDomain();
            TreeSet<Integer> set = new TreeSet<>();
            for (int j = 0; j < TestParameters.LOOP2_PARAMETER; j++) {
                int v1 = Utils.StaticRandom.nextInt(TestParameters.DOMAIN_HIGH + 1 - TestParameters.DOMAIN_LOW) + TestParameters.DOMAIN_LOW;
                int v2 = Utils.StaticRandom.nextInt(TestParameters.DOMAIN_HIGH + 1 - TestParameters.DOMAIN_LOW) + TestParameters.DOMAIN_LOW;
                fid.add(v1, v2);
                for (int k = v1; k <= v2; k++) {
                    set.add(k);
                }
                for (int k = TestParameters.DOMAIN_LOW; k <= TestParameters.DOMAIN_HIGH; k++) {
                    Assert.assertEquals(set.contains(k), fid.contains(k));
                }
            }
        }
    }

    /**
     * Test of add method, of class MutableFiniteIntegerDomain.
     */
    @Test
    public void testAdd_IntegerInterval() {
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            FiniteIntegerDomain fid = new MutableFiniteIntegerDomain();
            TreeSet<Integer> set = new TreeSet<>();
            for (int j = 0; j < TestParameters.LOOP2_PARAMETER; j++) {
                int v1 = Utils.StaticRandom.nextInt(TestParameters.DOMAIN_HIGH + 1 - TestParameters.DOMAIN_LOW) + TestParameters.DOMAIN_LOW;
                int v2 = Utils.StaticRandom.nextInt(TestParameters.DOMAIN_HIGH + 1 - TestParameters.DOMAIN_LOW) + TestParameters.DOMAIN_LOW;
                fid.add(new IntegerInterval(v1, v2));
                for (int k = v1; k <= v2; k++) {
                    set.add(k);
                }
                for (int k = TestParameters.DOMAIN_LOW; k <= TestParameters.DOMAIN_HIGH; k++) {
                    Assert.assertEquals(set.contains(k), fid.contains(k));
                }
            }
        }
    }

    /**
     * Test of clone method, of class MutableFiniteIntegerDomain.
     */
    @Test
    public void testClone() {
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            FiniteIntegerDomain fid = new MutableFiniteIntegerDomain(), fid2;
            fillRandom(fid);
            fid2 = fid.clone();
            for (int k = TestParameters.DOMAIN_LOW; k <= TestParameters.DOMAIN_HIGH; k++) {
                Assert.assertEquals(fid.contains(k), fid2.contains(k));
            }
        }
    }

    /**
     * Test of union method, of class MutableFiniteIntegerDomain.
     */
    @Test
    public void testUnion() throws InductiveBiasException {
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            FiniteIntegerDomain fid1 = new MutableFiniteIntegerDomain(), fid2 = new MutableFiniteIntegerDomain(), fid3;
            fillRandom(fid1);
            fillRandom(fid2);
            fid3 = fid1.union(fid2);
            for (int k = TestParameters.DOMAIN_LOW; k <= TestParameters.DOMAIN_HIGH; k++) {
                Assert.assertEquals(fid1.contains(k)||fid2.contains(k), fid3.contains(k));
            }
        }
    }

    /**
     * Test of unionWith method, of class MutableFiniteIntegerDomain.
     */
    @Test
    public void testUnionWith_Iterable() {
        System.out.println("unionWith");
        Iterable<IntegerInterval> other = null;
        MutableFiniteIntegerDomain instance = new MutableFiniteIntegerDomain();
        boolean expResult = false;
        boolean result = instance.unionWith(other);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of unionWith method, of class MutableFiniteIntegerDomain.
     */
    @Test
    public void testUnionWith_FiniteIntegerDomain() {
        System.out.println("unionWith");
        FiniteIntegerDomain other = null;
        MutableFiniteIntegerDomain instance = new MutableFiniteIntegerDomain();
        boolean expResult = false;
        boolean result = instance.unionWith(other);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of unionWith method, of class MutableFiniteIntegerDomain.
     */
    @Test
    public void testUnionWith_IntegerInterval() {
        System.out.println("unionWith");
        IntegerInterval interval = null;
        MutableFiniteIntegerDomain instance = new MutableFiniteIntegerDomain();
        boolean expResult = false;
        boolean result = instance.unionWith(interval);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of unionWith method, of class MutableFiniteIntegerDomain.
     */
    @Test
    public void testUnionWith_int_int() {
        System.out.println("unionWith");
        int low = 0;
        int high = 0;
        MutableFiniteIntegerDomain instance = new MutableFiniteIntegerDomain();
        boolean expResult = false;
        boolean result = instance.unionWith(low, high);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of unionWith method, of class MutableFiniteIntegerDomain.
     */
    @Test
    public void testUnionWith_int() {
        System.out.println("unionWith");
        int value = 0;
        MutableFiniteIntegerDomain instance = new MutableFiniteIntegerDomain();
        boolean expResult = false;
        boolean result = instance.unionWith(value);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of intersection method, of class MutableFiniteIntegerDomain.
     */
    @Test
    public void testIntersection() throws InductiveBiasException {
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            FiniteIntegerDomain fid1 = new MutableFiniteIntegerDomain(), fid2 = new MutableFiniteIntegerDomain(), fid3;
            fillRandom(fid1);
            fillRandom(fid2);
            fid3 = fid1.intersection(fid2);
            for (int k = TestParameters.DOMAIN_LOW; k <= TestParameters.DOMAIN_HIGH; k++) {
                Assert.assertEquals(fid1.contains(k)&&fid2.contains(k), fid3.contains(k));
            }
        }
    }

    /**
     * Test of intersectWith method, of class MutableFiniteIntegerDomain.
     */
    @Test
    public void testIntersectWith_FiniteIntegerDomain() {
        System.out.println("intersectWith");
        FiniteIntegerDomain other = null;
        MutableFiniteIntegerDomain instance = new MutableFiniteIntegerDomain();
        boolean expResult = false;
        boolean result = instance.intersectWith(other);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of intersectWith method, of class MutableFiniteIntegerDomain.
     */
    @Test
    public void testIntersectWith_IntegerInterval() {
        System.out.println("intersectWith");
        IntegerInterval interval = null;
        MutableFiniteIntegerDomain instance = new MutableFiniteIntegerDomain();
        boolean expResult = false;
        boolean result = instance.intersectWith(interval);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of intersectWith method, of class MutableFiniteIntegerDomain.
     */
    @Test
    public void testIntersectWith_int_int() {
        System.out.println("intersectWith");
        int low = 0;
        int high = 0;
        MutableFiniteIntegerDomain instance = new MutableFiniteIntegerDomain();
        boolean expResult = false;
        boolean result = instance.intersectWith(low, high);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of intersectWith method, of class MutableFiniteIntegerDomain.
     */
    @Test
    public void testIntersectWith_int() {
        System.out.println("intersectWith");
        int value = 0;
        MutableFiniteIntegerDomain instance = new MutableFiniteIntegerDomain();
        boolean expResult = false;
        boolean result = instance.intersectWith(value);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of minus method, of class MutableFiniteIntegerDomain.
     */
    @Test
    public void testMinus() throws InductiveBiasException {
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            FiniteIntegerDomain fid1 = new MutableFiniteIntegerDomain(), fid2 = new MutableFiniteIntegerDomain(), fid3;
            fillRandom(fid1);
            fillRandom(fid2);
            fid3 = fid1.minus(fid2);
            for (int k = TestParameters.DOMAIN_LOW; k <= TestParameters.DOMAIN_HIGH; k++) {
                Assert.assertEquals(fid1.contains(k)&&!fid2.contains(k), fid3.contains(k));
            }
        }
    }

    /**
     * Test of minusWith method, of class MutableFiniteIntegerDomain.
     */
    @Test
    public void testMinusWith_FiniteIntegerDomain() {
        System.out.println("minusWith");
        FiniteIntegerDomain fid = null;
        MutableFiniteIntegerDomain instance = new MutableFiniteIntegerDomain();
        boolean expResult = false;
        boolean result = instance.minusWith(fid);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of minusWith method, of class MutableFiniteIntegerDomain.
     */
    @Test
    public void testMinusWith_IntegerInterval() {
        System.out.println("minusWith");
        IntegerInterval interval = null;
        MutableFiniteIntegerDomain instance = new MutableFiniteIntegerDomain();
        boolean expResult = false;
        boolean result = instance.minusWith(interval);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of minusWith method, of class MutableFiniteIntegerDomain.
     */
    @Test
    public void testMinusWith_int_int() {
        System.out.println("minusWith");
        int low = 0;
        int high = 0;
        MutableFiniteIntegerDomain instance = new MutableFiniteIntegerDomain();
        boolean expResult = false;
        boolean result = instance.minusWith(low, high);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of minusWith method, of class MutableFiniteIntegerDomain.
     */
    @Test
    public void testMinusWith_int() {
        System.out.println("minusWith");
        int value = 0;
        MutableFiniteIntegerDomain instance = new MutableFiniteIntegerDomain();
        boolean expResult = false;
        boolean result = instance.minusWith(value);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of minusWith method, of class MutableFiniteIntegerDomain.
     */
    @Test
    public void testMinusWith_Iterable() {
        System.out.println("minusWith");
        Iterable<IntegerInterval> other = null;
        MutableFiniteIntegerDomain instance = new MutableFiniteIntegerDomain();
        boolean expResult = false;
        boolean result = instance.minusWith(other);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of iterator method, of class MutableFiniteIntegerDomain.
     */
    @Test
    public void testIterator() {
        System.out.println("iterator");
        MutableFiniteIntegerDomain instance = new MutableFiniteIntegerDomain();
        Iterator expResult = null;
        Iterator result = instance.iterator();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of integerIterator method, of class MutableFiniteIntegerDomain.
     */
    @Test
    public void testIntegerIterator() {
        System.out.println("integerIterator");
        MutableFiniteIntegerDomain instance = new MutableFiniteIntegerDomain();
        Iterator expResult = null;
        Iterator result = instance.integerIterator();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of read method, of class MutableFiniteIntegerDomain.
     */
    @Test
    public void testRead() throws Exception {
        System.out.println("read");
        DataInputStream dis = null;
        MutableFiniteIntegerDomain instance = new MutableFiniteIntegerDomain();
        instance.read(dis);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of write method, of class MutableFiniteIntegerDomain.
     */
    @Test
    public void testWrite() throws Exception {
        System.out.println("write");
        DataOutputStream dos = null;
        MutableFiniteIntegerDomain instance = new MutableFiniteIntegerDomain();
        instance.write(dos);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class MutableFiniteIntegerDomain.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        MutableFiniteIntegerDomain instance = new MutableFiniteIntegerDomain();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class MutableFiniteIntegerDomain.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        MutableFiniteIntegerDomain instance = new MutableFiniteIntegerDomain();
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of readAndGenerate method, of class MutableFiniteIntegerDomain.
     */
    @Test
    public void testReadAndGenerate() throws Exception {
        System.out.println("readAndGenerate");
        DataInputStream dis = null;
        MutableFiniteIntegerDomain instance = new MutableFiniteIntegerDomain();
        MutableFiniteIntegerDomain expResult = null;
        MutableFiniteIntegerDomain result = instance.readAndGenerate(dis);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of intersectWith method, of class MutableFiniteIntegerDomain.
     */
    @Test
    public void testIntersectWith_Iterable() {
        System.out.println("intersectWith");
        Iterable<IntegerInterval> other = null;
        MutableFiniteIntegerDomain instance = new MutableFiniteIntegerDomain();
        boolean expResult = false;
        boolean result = instance.intersectWith(other);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setToSingle method, of class MutableFiniteIntegerDomain.
     */
    @Test
    public void testSetToSingle_IntegerInterval() {
        System.out.println("setToSingle");
        IntegerInterval interval = null;
        MutableFiniteIntegerDomain instance = new MutableFiniteIntegerDomain();
        instance.setToSingle(interval);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setToSingle method, of class MutableFiniteIntegerDomain.
     */
    @Test
    public void testSetToSingle_int_int() {
        System.out.println("setToSingle");
        int low = 0;
        int high = 0;
        MutableFiniteIntegerDomain instance = new MutableFiniteIntegerDomain();
        instance.setToSingle(low, high);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setToSingle method, of class MutableFiniteIntegerDomain.
     */
    @Test
    public void testSetToSingle_int() {
        System.out.println("setToSingle");
        int value = 0;
        MutableFiniteIntegerDomain instance = new MutableFiniteIntegerDomain();
        instance.setToSingle(value);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of generate method, of class MutableFiniteIntegerDomain.
     */
    @Test
    public void testGenerate() {
        System.out.println("generate");
        String text = "";
        MutableFiniteIntegerDomain instance = new MutableFiniteIntegerDomain();
        MutableFiniteIntegerDomain expResult = null;
        MutableFiniteIntegerDomain result = instance.generate(text);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    private void fillRandom(FiniteIntegerDomain fid) {
        for (int j = 0; j < TestParameters.LOOP2_PARAMETER; j++) {
            int v1 = Utils.StaticRandom.nextInt(TestParameters.DOMAIN_HIGH + 1 - TestParameters.DOMAIN_LOW) + TestParameters.DOMAIN_LOW;
            int v2 = Utils.StaticRandom.nextInt(TestParameters.DOMAIN_HIGH + 1 - TestParameters.DOMAIN_LOW) + TestParameters.DOMAIN_LOW;
            fid.add(new IntegerInterval(v1, v2));
        }
    }
}