package parallelhyflex.problems.fdcsp.problem;

import java.util.Arrays;
import java.util.Iterator;
import java.util.TreeSet;
import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Test;
import parallelhyflex.TestParameters;
import parallelhyflex.utils.Utils;

/**
 *
 * @author kommusoft
 */
public class IntegerIntervalTest extends FiniteIntegerDomainTestBase {

    /**
     *
     */
    public IntegerIntervalTest() {
    }

    /**
     * Test of size method, of class IntegerInterval.
     */
    @Test
    public void testSize() {
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            TreeSet<Integer> set = new TreeSet<>();
            IntegerInterval ii = fillRandom(set);
            Assert.assertEquals(set.size(), ii.size());
        }
    }

    /**
     * Test of contains method, of class IntegerInterval.
     */
    @Test
    public void testContains_Integer() {
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            TreeSet<Integer> set = new TreeSet<>();
            IntegerInterval ii = fillRandom(set);
            containsSame(set, ii);
        }
    }

    /**
     * Test of clear method, of class IntegerInterval.
     */
    @Test
    public void testClear() {
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            TreeSet<Integer> set = new TreeSet<>();
            IntegerInterval ii = fillRandom(set);
            set.clear();
            ii.clear();
            Assert.assertEquals(0, ii.size());
            Assert.assertEquals(set.size(), ii.size());
            containsSame(set, ii);
        }
    }

    /**
     * Test of contains method, of class IntegerInterval.
     */
    @Test
    public void testContains_int_int() {
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            TreeSet<Integer> set = new TreeSet<>();
            IntegerInterval ii = fillRandom(set);
            int val1 = this.randomDomainValue();
            int val2 = this.randomDomainValue();
            Assert.assertEquals(String.format("(%s,%s) in? %s", val1, val2, ii), containsTreeInterval(set, val1, val2), ii.contains(val1, val2));
        }
    }

    /**
     * Test of contains method, of class IntegerInterval.
     */
    @Test
    public void testContains_IntegerInterval() {
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            TreeSet<Integer> set = new TreeSet<>();
            IntegerInterval ii = fillRandom(set);
            int val1 = this.randomDomainValue();
            int val2 = this.randomDomainValue();
            IntegerInterval ii2 = new IntegerInterval(val1, val2);
            Assert.assertEquals(String.format("%s in? %s", ii2, ii), containsTreeInterval(set, val1, val2), ii.contains(ii2));
        }
    }

    /**
     * Test of overlap method, of class IntegerInterval.
     */
    @Test
    public void testOverlap() {
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            TreeSet<Integer> set1 = new TreeSet<>(), set2 = new TreeSet<>();
            IntegerInterval ii1 = fillRandom(set1);
            IntegerInterval ii2 = fillRandom(set2);
            Assert.assertEquals(this.withOverlap(set1, set2), ii1.overlap(ii2));
        }
    }

    /**
     * Test of canUnion method, of class IntegerInterval.
     */
    @Test
    public void testCanUnion() {
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            TreeSet<Integer> set1 = new TreeSet<>(), set2 = new TreeSet<>();
            IntegerInterval ii1 = fillRandom(set1);
            IntegerInterval ii2 = fillRandom(set2);
            set1.addAll(set2);
            Assert.assertEquals(String.format("%s u? %s", ii1, ii2), this.representable(set1), ii1.canUnion(ii2));
        }
    }

    /**
     * Test of empty method, of class IntegerInterval.
     */
    @Test
    public void testEmpty() {
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            TreeSet<Integer> set = new TreeSet<>();
            IntegerInterval ii = fillRandom(set);
            this.containsSame(set, ii);
            Assert.assertEquals(set.isEmpty(), ii.empty());
        }
    }

    /**
     * Test of notEmpty method, of class IntegerInterval.
     */
    @Test
    public void testNotEmpty() {
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            TreeSet<Integer> set = new TreeSet<>();
            IntegerInterval ii = fillRandom(set);
            this.containsSame(set, ii);
            Assert.assertEquals(!set.isEmpty(), ii.notEmpty());
        }
    }

    /**
     * Test of low method, of class IntegerInterval.
     */
    @Test
    public void testLow() {
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            TreeSet<Integer> set = new TreeSet<>();
            IntegerInterval ii = fillRandom(set);
            this.containsSame(set, ii);
            if (set.size() > 0) {
                Assert.assertEquals(set.first(), ii.low());
            }
        }
    }

    /**
     * Test of setLow method, of class IntegerInterval.
     */
    @Test
    public void testSetLow() {
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            IntegerInterval ii = fillRandom();
            int low = this.randomDomainValue();
            ii.setLow(low);
            Assert.assertEquals(low, (int) ii.low());
        }
    }

    /**
     * Test of high method, of class IntegerInterval.
     */
    @Test
    public void testHigh() {
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            TreeSet<Integer> set = new TreeSet<>();
            IntegerInterval ii = fillRandom(set);
            this.containsSame(set, ii);
            if (set.size() > 0) {
                Assert.assertEquals(set.last(), ii.high());
            }
        }
    }

    /**
     * Test of setHigh method, of class IntegerInterval.
     */
    @Test
    public void testSetHigh() {
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            IntegerInterval ii = fillRandom();
            int high = this.randomDomainValue();
            ii.setHigh(high);
            Assert.assertEquals(high, (int) ii.high());
        }
    }

    /**
     * Test of compareTo method, of class IntegerInterval.
     */
    @Test
    public void testCompareTo() {
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            IntegerInterval ii1 = fillRandom();
            IntegerInterval ii2 = fillRandom();
            if(ii1.low() < ii2.low()) {
                Assert.assertTrue(ii1.compareTo(ii2) < 0);
            }
            else if(ii1.low() > ii2.low()) {
                Assert.assertTrue(ii1.compareTo(ii2) > 0);
            }
            else {
                Assert.assertEquals(0,ii1.compareTo(ii2));
            }
        }
    }

    /**
     * Test of hashCode method, of class IntegerInterval.
     */
    @Test
    public void testHashCode() {
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            IntegerInterval ii = fillRandom();
            IntegerInterval ii2 = fillRandom();
            if (ii.equals(ii2)) {
                Assert.assertEquals(String.format("h %s=? h %s",ii,ii2),ii2.hashCode(), ii.hashCode());
            }
        }
    }

    /**
     * Test of equals method, of class IntegerInterval.
     */
    @Test
    public void testEquals() {
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            TreeSet<Integer> set1 = new TreeSet<>(), set2 = new TreeSet<>();
            IntegerInterval ii1 = fillRandom(set1);
            IntegerInterval ii2 = fillRandom(set2);
            Assert.assertEquals(String.format("%s ==? %s", ii1, ii2), ii1.equals(ii2), Utils.arrayEquality(set1.iterator(), set2.iterator()));
        }
    }

    /**
     * Test of clone method, of class IntegerInterval.
     */
    @Test
    public void testClone() {
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            IntegerInterval ii = fillRandom();
            IntegerInterval ii2 = ii.clone();
            Assert.assertEquals(ii, ii2);
        }
    }

    /**
     * Test of toString method, of class IntegerInterval.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        IntegerInterval instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of union method, of class IntegerInterval.
     */
    @Test
    public void testUnion() throws Exception {
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            TreeSet<Integer> set1 = new TreeSet<>(), set2 = new TreeSet<>();
            IntegerInterval ii1 = fillRandom(set1);
            IntegerInterval ii2 = fillRandom(set2);
            if (ii1.canUnion(ii2)) {
                IntegerInterval ii3 = ii1.union(ii2);
                areEqualUnion(set1, set2, ii3);
            }
        }
    }

    /**
     * Test of unionWith method, of class IntegerInterval.
     */
    @Test
    public void testUnionWith() throws Exception {
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            TreeSet<Integer> set1 = new TreeSet<>(), set2 = new TreeSet<>();
            IntegerInterval ii1 = fillRandom(set1);
            IntegerInterval ii2 = fillRandom(set2);
            if (ii1.canUnion(ii2)) {
                ii1.unionWith(ii2);
                areEqualUnion(set1, set2, ii1);
            }
        }
    }

    /**
     * Test of intersection method, of class IntegerInterval.
     */
    @Test
    public void testIntersection() {
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            TreeSet<Integer> set1 = new TreeSet<>(), set2 = new TreeSet<>();
            IntegerInterval ii1 = fillRandom(set1);
            IntegerInterval ii2 = fillRandom(set2);
            if (ii1.canIntersect(ii2)) {
                IntegerInterval ii3 = ii1.intersection(ii2);
                areEqualIntersect(set1, set2, ii3);
            }
        }
    }

    /**
     * Test of intersectWith method, of class IntegerInterval.
     */
    @Test
    public void testIntersectWith() {
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            TreeSet<Integer> set1 = new TreeSet<>(), set2 = new TreeSet<>();
            IntegerInterval ii1 = fillRandom(set1);
            IntegerInterval ii2 = fillRandom(set2);
            if (ii1.canIntersect(ii2)) {
                ii1.intersectWith(ii2);
                areEqualIntersect(set1, set2, ii1);
            }
        }
    }

    /**
     * Test of minus method, of class IntegerInterval.
     */
    @Test
    public void testMinus() throws Exception {
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            TreeSet<Integer> set1 = new TreeSet<>(), set2 = new TreeSet<>();
            IntegerInterval ii1 = fillRandom(set1);
            IntegerInterval ii2 = fillRandom(set2);
            if (ii1.canMinus(ii2)) {
                IntegerInterval ii3 = ii1.minus(ii2);
                areEqualMinus(set1, set2, ii3);
            }
        }
    }

    /**
     * Test of minusWith method, of class IntegerInterval.
     */
    @Test
    public void testMinusWith() throws Exception {
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            TreeSet<Integer> set1 = new TreeSet<>(), set2 = new TreeSet<>();
            IntegerInterval ii1 = fillRandom(set1);
            IntegerInterval ii2 = fillRandom(set2);
            if (ii1.canMinus(ii2)) {
                ii1.minusWith(ii2);
                areEqualMinus(set1, set2, ii1);
            }
        }
    }

    /**
     * Test of canIntersect method, of class IntegerInterval.
     */
    @Test
    public void testCanIntersect() {
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            TreeSet<Integer> set1 = new TreeSet<>(), set2 = new TreeSet<>();
            IntegerInterval ii1 = fillRandom(set1);
            IntegerInterval ii2 = fillRandom(set2);
            set1.retainAll(set2);
            Assert.assertEquals(this.representable(set1), ii1.canIntersect(ii2));
        }
    }

    /**
     * Test of canMinus method, of class IntegerInterval.
     */
    @Test
    public void testCanMinus() {
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            TreeSet<Integer> set1 = new TreeSet<>(), set2 = new TreeSet<>();
            IntegerInterval ii1 = fillRandom(set1);
            IntegerInterval ii2 = fillRandom(set2);
            set1.removeAll(set2);
            Assert.assertEquals(this.representable(set1), ii1.canIntersect(ii2));
        }
    }

    /**
     * Test of add method, of class IntegerInterval.
     */
    @Test
    public void testAdd() {
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            IntegerInterval ii1 = fillRandom();
            IntegerInterval ii2 = fillRandom();
            TreeSet<Integer> set = generateAddSet(ii1, ii2);
            this.containsSame(set, ii1.add(ii2));
        }
    }

    /**
     * Test of addWith method, of class IntegerInterval.
     */
    @Test
    public void testAddWith() {
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            IntegerInterval ii1 = fillRandom();
            IntegerInterval ii2 = fillRandom();
            TreeSet<Integer> set = generateAddSet(ii1, ii2);
            ii1.addWith(ii2);
            this.containsSame(set, ii1);
        }
    }

    /**
     * Test of sub method, of class IntegerInterval.
     */
    @Test
    public void testSub() {
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            IntegerInterval ii1 = fillRandom();
            IntegerInterval ii2 = fillRandom();
            TreeSet<Integer> set = generateSubSet(ii1, ii2);
            this.containsSame(set, ii1.sub(ii2));
        }
    }

    /**
     * Test of subWith method, of class IntegerInterval.
     */
    @Test
    public void testSubWith() {
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            IntegerInterval ii1 = fillRandom();
            IntegerInterval ii2 = fillRandom();
            TreeSet<Integer> set = generateSubSet(ii1, ii2);
            ii1.subWith(ii2);
            this.containsSame(set, ii1);
        }
    }

    /**
     * Test of canAdd method, of class IntegerInterval.
     */
    @Test
    public void testCanAdd() {
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            IntegerInterval ii1 = fillRandom();
            IntegerInterval ii2 = fillRandom();
            TreeSet<Integer> set = generateAddSet(ii1, ii2);
            ii1.addWith(ii2);
            Assert.assertEquals(this.representable(set), ii1.canAdd(ii2));
        }
    }

    /**
     * Test of canSub method, of class IntegerInterval.
     */
    @Test
    public void testCanSub() {
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            IntegerInterval ii1 = fillRandom();
            IntegerInterval ii2 = fillRandom();
            TreeSet<Integer> set = generateSubSet(ii1, ii2);
            ii1.addWith(ii2);
            Assert.assertEquals(this.representable(set), ii1.canSub(ii2));
        }
    }

    /**
     * Test of neg method, of class IntegerInterval.
     */
    @Test
    public void testNeg() {
        System.out.println("neg");
        IntegerInterval instance = null;
        IntegerInterval expResult = null;
        IntegerInterval result = instance.neg();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of negWith method, of class IntegerInterval.
     */
    @Test
    public void testNegWith() {
        System.out.println("negWith");
        IntegerInterval instance = null;
        instance.negWith();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of canNeg method, of class IntegerInterval.
     */
    @Test
    public void testCanNeg() {
        System.out.println("canNeg");
        IntegerInterval instance = null;
        boolean expResult = false;
        boolean result = instance.canNeg();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getIth method, of class IntegerInterval.
     */
    @Test
    public void testGetIth() {
        System.out.println("getIth");
        int index = 0;
        IntegerInterval instance = null;
        Integer expResult = null;
        Integer result = instance.getIth(index);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of iterator method, of class IntegerInterval.
     */
    @Test
    public void testIterator() {
        System.out.println("iterator");
        IntegerInterval instance = null;
        Iterator expResult = null;
        Iterator result = instance.iterator();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    private IntegerInterval fillRandom(TreeSet<Integer> treeset) {
        int v1 = randomDomainValue();
        int v2 = randomDomainValue();
        for (int k = v1; k <= v2; k++) {
            treeset.add(k);
        }
        return new IntegerInterval(v1, v2);
    }

    private void containsSame(TreeSet<Integer> set, IntegerInterval ii) {
        String sa = Arrays.toString(set.toArray());
        String sb = ii.toString();
        for (int k = TestParameters.DOMAIN_LOW; k <= TestParameters.DOMAIN_HIGH; k++) {
            Assert.assertEquals(String.format("%s =? %s (%s)", sa, sb, k), set.contains(k), ii.contains(k));
        }
    }

    private IntegerInterval fillRandom() {
        int v1 = randomDomainValue();
        int v2 = randomDomainValue();
        return new IntegerInterval(v1, v2);

    }

    private boolean containsTreeInterval(TreeSet<Integer> set, int low, int high) {
        for (int k = low; k <= high; k++) {
            if (!set.contains(k)) {
                return false;
            }
        }
        return true;
    }

    private void areEqualUnion(TreeSet<Integer> set1, TreeSet<Integer> set2, IntegerInterval ii3) {
        for (int k = TestParameters.DOMAIN_LOW; k <= TestParameters.DOMAIN_HIGH; k++) {
            Assert.assertEquals(set1.contains(k) || set2.contains(k), ii3.contains(k));
        }
    }

    private void areEqualIntersect(TreeSet<Integer> set1, TreeSet<Integer> set2, IntegerInterval ii3) {
        for (int k = TestParameters.DOMAIN_LOW; k <= TestParameters.DOMAIN_HIGH; k++) {
            Assert.assertEquals(set1.contains(k) && set2.contains(k), ii3.contains(k));
        }
    }

    private void areEqualMinus(TreeSet<Integer> set1, TreeSet<Integer> set2, IntegerInterval ii3) {
        for (int k = TestParameters.DOMAIN_LOW; k <= TestParameters.DOMAIN_HIGH; k++) {
            Assert.assertEquals(set1.contains(k) && !set2.contains(k), ii3.contains(k));
        }
    }

    private boolean withOverlap(TreeSet<Integer> set1, TreeSet<Integer> set2) {
        for (int k = TestParameters.DOMAIN_LOW; k <= TestParameters.DOMAIN_HIGH; k++) {
            if (set1.contains(k) && set2.contains(k)) {
                return true;
            }
        }
        return false;
    }

    private boolean representable(TreeSet<Integer> set) {
        int num = this.numberOfSequences(set);
        return (num == 0 || num == 2);
    }

    private int numberOfSequences(TreeSet<Integer> set) {
        boolean old = false;
        int i = 0;
        for (int k = TestParameters.DOMAIN_LOW; k <= TestParameters.DOMAIN_HIGH; k++) {
            if (set.contains(k) != old) {
                i++;
                old = !old;
            }
        }
        if (old) {
            i++;
        }
        return i;
    }

    private TreeSet<Integer> generateAddSet(IntegerInterval ii1, IntegerInterval ii2) {
        TreeSet<Integer> set = new TreeSet<>();
        for (Integer i1 : ii1) {
            for (Integer i2 : ii2) {
                set.add(i1 + i2);
            }
        }
        return set;
    }

    private TreeSet<Integer> generateSubSet(IntegerInterval ii1, IntegerInterval ii2) {
        TreeSet<Integer> set = new TreeSet<>();
        for (Integer i1 : ii1) {
            for (Integer i2 : ii2) {
                set.add(i1 - i2);
            }
        }
        return set;
    }
}