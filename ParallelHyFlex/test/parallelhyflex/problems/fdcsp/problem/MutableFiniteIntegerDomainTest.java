package parallelhyflex.problems.fdcsp.problem;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.TreeSet;
import org.junit.Assert;
import org.junit.Test;
import parallelhyflex.TestParameters;
import parallelhyflex.algebra.InductiveBiasException;
import parallelhyflex.utils.Utils;

/**
 *
 * @author kommusoft
 */
public class MutableFiniteIntegerDomainTest extends FiniteIntegerDomainTestBase {

    /**
     * Test of low method, of class MutableFiniteIntegerDomain.
     */
    @Test
    public void testLow() {
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            MutableFiniteIntegerDomain fid = new MutableFiniteIntegerDomain();
            TreeSet<Integer> set = new TreeSet<>();
            fillRandom(fid, set);
            if (set.size() > 0) {
                Assert.assertEquals(set.first(), fid.low());
            } else {
                Assert.assertEquals(null, fid.low());
            }
        }
    }

    /**
     * Test of high method, of class MutableFiniteIntegerDomain.
     */
    @Test
    public void testHigh() {
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            MutableFiniteIntegerDomain fid = new MutableFiniteIntegerDomain();
            TreeSet<Integer> set = new TreeSet<>();
            fillRandom(fid, set);
            if (set.size() > 0) {
                Assert.assertEquals(set.last(), fid.high());
            } else {
                Assert.assertEquals(null, fid.high());
            }
        }
    }

    /**
     * Test of getIth method, of class MutableFiniteIntegerDomain.
     */
    @Test
    public void testGetIth() {
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            MutableFiniteIntegerDomain fid = new MutableFiniteIntegerDomain();
            TreeSet<Integer> set = new TreeSet<>();
            fillRandom(fid, set);
            int j = 0;
            for (Iterator<Integer> ii = fid.integerIterator(); ii.hasNext(); j++) {
                Assert.assertEquals(ii.next(), fid.getIth(j));
            }
        }
    }

    /**
     * Test of size method, of class MutableFiniteIntegerDomain.
     */
    @Test
    public void testSize() {
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            MutableFiniteIntegerDomain fid = new MutableFiniteIntegerDomain();
            TreeSet<Integer> set = new TreeSet<>();
            fillRandom(fid, set);
            Assert.assertEquals(set.size(), fid.size());
        }
    }

    /**
     * Test of clear method, of class MutableFiniteIntegerDomain.
     */
    @Test
    public void testClear() {
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            MutableFiniteIntegerDomain fid = new MutableFiniteIntegerDomain();
            fillRandom(fid);
            fid.clear();
            Assert.assertEquals(0, fid.size());
        }
    }

    /**
     * Test of add method, of class MutableFiniteIntegerDomain.
     */
    @Test
    public void testAdd_int() {
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            MutableFiniteIntegerDomain fid = new MutableFiniteIntegerDomain();
            TreeSet<Integer> set = new TreeSet<>();
            for (int j = 0; j < TestParameters.LOOP2_PARAMETER; j++) {
                int v = randomDomainValue();
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
            MutableFiniteIntegerDomain fid = new MutableFiniteIntegerDomain();
            TreeSet<Integer> set = new TreeSet<>();
            for (int j = 0; j < TestParameters.LOOP2_PARAMETER; j++) {
                int v1 = randomDomainValue();
                int v2 = randomDomainValue();
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
            MutableFiniteIntegerDomain fid = new MutableFiniteIntegerDomain();
            TreeSet<Integer> set = new TreeSet<>();
            for (int j = 0; j < TestParameters.LOOP2_PARAMETER; j++) {
                int v1 = randomDomainValue();
                int v2 = randomDomainValue();
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
            MutableFiniteIntegerDomain fid = new MutableFiniteIntegerDomain(), fid2;
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
            MutableFiniteIntegerDomain fid1 = new MutableFiniteIntegerDomain(), fid2 = new MutableFiniteIntegerDomain(), fid3;
            fillRandom(fid1);
            fillRandom(fid2);
            fid3 = fid1.union(fid2);
            for (int k = TestParameters.DOMAIN_LOW; k <= TestParameters.DOMAIN_HIGH; k++) {
                Assert.assertEquals(fid1.contains(k) || fid2.contains(k), fid3.contains(k));
            }
        }
    }

    /**
     * Test of unionWith method, of class MutableFiniteIntegerDomain.
     */
    @Test
    public void testUnionWith_Iterable() {
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            MutableFiniteIntegerDomain fid1 = new MutableFiniteIntegerDomain(), fid2 = new MutableFiniteIntegerDomain(), fid3;
            ArrayList<IntegerInterval> arii = new ArrayList<>();
            fillRandom(fid1);
            fillRandom(fid2, arii);
            fid3 = fid1.clone();
            fid3.unionWith(arii);
            for (int k = TestParameters.DOMAIN_LOW; k <= TestParameters.DOMAIN_HIGH; k++) {
                Assert.assertEquals(fid1.contains(k) || fid2.contains(k), fid3.contains(k));
            }
        }
    }

    /**
     * Test of unionWith method, of class MutableFiniteIntegerDomain.
     */
    @Test
    public void testUnionWith_FiniteIntegerDomain() throws InductiveBiasException {
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            MutableFiniteIntegerDomain fid1 = new MutableFiniteIntegerDomain(), fid2 = new MutableFiniteIntegerDomain(), fid3;
            fillRandom(fid1);
            fillRandom(fid2);
            fid3 = fid1.clone();
            fid3.unionWith(fid2);
            for (int k = TestParameters.DOMAIN_LOW; k <= TestParameters.DOMAIN_HIGH; k++) {
                Assert.assertEquals(fid1.contains(k) || fid2.contains(k), fid3.contains(k));
            }
        }
    }

    /**
     * Test of unionWith method, of class MutableFiniteIntegerDomain.
     */
    @Test
    public void testUnionWith_IntegerInterval() {
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            MutableFiniteIntegerDomain fid1 = new MutableFiniteIntegerDomain(), fid2;
            fillRandom(fid1);
            fid2 = fid1.clone();
            int val1 = randomDomainValue();
            int val2 = randomDomainValue();
            fid2.unionWith(new IntegerInterval(val1, val2));
            for (int k = TestParameters.DOMAIN_LOW; k <= TestParameters.DOMAIN_HIGH; k++) {
                Assert.assertEquals(fid1.contains(k) || (val1 <= k && k <= val2), fid2.contains(k));
            }
        }
    }

    /**
     * Test of unionWith method, of class MutableFiniteIntegerDomain.
     */
    @Test
    public void testUnionWith_int_int() {
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            MutableFiniteIntegerDomain fid1 = new MutableFiniteIntegerDomain(), fid2;
            fillRandom(fid1);
            fid2 = fid1.clone();
            int val1 = randomDomainValue();
            int val2 = randomDomainValue();
            fid2.unionWith(val1, val2);
            for (int k = TestParameters.DOMAIN_LOW; k <= TestParameters.DOMAIN_HIGH; k++) {
                Assert.assertEquals(fid1.contains(k) || (val1 <= k && k <= val2), fid2.contains(k));
            }
        }
    }

    /**
     * Test of unionWith method, of class MutableFiniteIntegerDomain.
     */
    @Test
    public void testUnionWith_int() {
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            MutableFiniteIntegerDomain fid1 = new MutableFiniteIntegerDomain(), fid2;
            fillRandom(fid1);
            fid2 = fid1.clone();
            int val = randomDomainValue();
            fid2.unionWith(val);
            for (int k = TestParameters.DOMAIN_LOW; k <= TestParameters.DOMAIN_HIGH; k++) {
                Assert.assertEquals(fid1.contains(k) || k == val, fid2.contains(k));
            }
        }
    }

    /**
     * Test of intersection method, of class MutableFiniteIntegerDomain.
     */
    @Test
    public void testIntersection() throws InductiveBiasException {
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            MutableFiniteIntegerDomain fid1 = new MutableFiniteIntegerDomain(), fid2 = new MutableFiniteIntegerDomain(), fid3;
            fillRandom(fid1);
            fillRandom(fid2);
            fid3 = fid1.intersection(fid2);
            for (int k = TestParameters.DOMAIN_LOW; k <= TestParameters.DOMAIN_HIGH; k++) {
                Assert.assertEquals(fid1.contains(k) && fid2.contains(k), fid3.contains(k));
            }
        }
    }

    /**
     * Test of intersectWith method, of class MutableFiniteIntegerDomain.
     */
    @Test
    public void testIntersectWith_FiniteIntegerDomain() throws InductiveBiasException {
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            MutableFiniteIntegerDomain fid1 = new MutableFiniteIntegerDomain(), fid2 = new MutableFiniteIntegerDomain(), fid3;
            fillRandom(fid1);
            fillRandom(fid2);
            fid3 = fid1.clone();
            fid3.intersectWith(fid2);
            for (int k = TestParameters.DOMAIN_LOW; k <= TestParameters.DOMAIN_HIGH; k++) {
                Assert.assertEquals(fid1.contains(k) && fid2.contains(k), fid3.contains(k));
            }
        }
    }

    /**
     * Test of intersectWith method, of class MutableFiniteIntegerDomain.
     */
    @Test
    public void testIntersectWith_IntegerInterval() {
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            MutableFiniteIntegerDomain fid1 = new MutableFiniteIntegerDomain(), fid2;
            fillRandom(fid1);
            fid2 = fid1.clone();
            int val1 = randomDomainValue();
            int val2 = randomDomainValue();
            fid2.intersectWith(new IntegerInterval(val1, val2));
            for (int k = TestParameters.DOMAIN_LOW; k <= TestParameters.DOMAIN_HIGH; k++) {
                Assert.assertEquals(fid1.contains(k) && (val1 <= k && k <= val2), fid2.contains(k));
            }
        }
    }

    /**
     * Test of intersectWith method, of class MutableFiniteIntegerDomain.
     */
    @Test
    public void testIntersectWith_int_int() {
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            MutableFiniteIntegerDomain fid1 = new MutableFiniteIntegerDomain(), fid2;
            fillRandom(fid1);
            fid2 = fid1.clone();
            int val1 = randomDomainValue();
            int val2 = randomDomainValue();
            fid2.intersectWith(val1, val2);
            for (int k = TestParameters.DOMAIN_LOW; k <= TestParameters.DOMAIN_HIGH; k++) {
                Assert.assertEquals(fid1.contains(k) && (val1 <= k && k <= val2), fid2.contains(k));
            }
        }
    }

    /**
     * Test of intersectWith method, of class MutableFiniteIntegerDomain.
     */
    @Test
    public void testIntersectWith_int() {
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            MutableFiniteIntegerDomain fid1 = new MutableFiniteIntegerDomain(), fid2;
            fillRandom(fid1);
            fid2 = fid1.clone();
            int val = randomDomainValue();
            fid2.intersectWith(val);
            for (int k = TestParameters.DOMAIN_LOW; k <= TestParameters.DOMAIN_HIGH; k++) {
                Assert.assertEquals(fid1.contains(k) && k == val, fid2.contains(k));
            }
        }
    }

    /**
     * Test of minus method, of class MutableFiniteIntegerDomain.
     */
    @Test
    public void testMinus() throws InductiveBiasException {
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            MutableFiniteIntegerDomain fid1 = new MutableFiniteIntegerDomain(), fid2 = new MutableFiniteIntegerDomain(), fid3;
            fillRandom(fid1);
            fillRandom(fid2);
            fid3 = fid1.minus(fid2);
            for (int k = TestParameters.DOMAIN_LOW; k <= TestParameters.DOMAIN_HIGH; k++) {
                Assert.assertEquals(String.format("%s\\%s=%s",fid1,fid2,fid3),fid1.contains(k) && !fid2.contains(k), fid3.contains(k));
            }
        }
    }

    /**
     * Test of minusWith method, of class MutableFiniteIntegerDomain.
     */
    @Test
    public void testMinusWith_FiniteIntegerDomain() throws InductiveBiasException {
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            MutableFiniteIntegerDomain fid1 = new MutableFiniteIntegerDomain(), fid2 = new MutableFiniteIntegerDomain(), fid3;
            fillRandom(fid1);
            fillRandom(fid2);
            fid3 = fid1.clone();
            fid3.minusWith(fid2);
            for (int k = TestParameters.DOMAIN_LOW; k <= TestParameters.DOMAIN_HIGH; k++) {
                Assert.assertEquals(fid1.contains(k) && !fid2.contains(k), fid3.contains(k));
            }
        }
    }

    /**
     * Test of minusWith method, of class MutableFiniteIntegerDomain.
     */
    @Test
    public void testMinusWith_IntegerInterval() {
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            MutableFiniteIntegerDomain fid1 = new MutableFiniteIntegerDomain(), fid2;
            fillRandom(fid1);
            fid2 = fid1.clone();
            int val1 = randomDomainValue();
            int val2 = randomDomainValue();
            fid2.minusWith(new IntegerInterval(val1, val2));
            for (int k = TestParameters.DOMAIN_LOW; k <= TestParameters.DOMAIN_HIGH; k++) {
                Assert.assertEquals(fid1.contains(k) && !(val1 <= k && k <= val2), fid2.contains(k));
            }
        }
    }

    /**
     * Test of minusWith method, of class MutableFiniteIntegerDomain.
     */
    @Test
    public void testMinusWith_int_int() {
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            MutableFiniteIntegerDomain fid1 = new MutableFiniteIntegerDomain(), fid2;
            fillRandom(fid1);
            fid2 = fid1.clone();
            int val1 = randomDomainValue();
            int val2 = randomDomainValue();
            fid2.minusWith(val1, val2);
            for (int k = TestParameters.DOMAIN_LOW; k <= TestParameters.DOMAIN_HIGH; k++) {
                Assert.assertEquals(fid1.contains(k) && !(val1 <= k && k <= val2), fid2.contains(k));
            }
        }
    }

    /**
     * Test of minusWith method, of class MutableFiniteIntegerDomain.
     */
    @Test
    public void testMinusWith_int() {
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            MutableFiniteIntegerDomain fid1 = new MutableFiniteIntegerDomain(), fid2;
            fillRandom(fid1);
            fid2 = fid1.clone();
            int val = randomDomainValue();
            fid2.minusWith(val);
            for (int k = TestParameters.DOMAIN_LOW; k <= TestParameters.DOMAIN_HIGH; k++) {
                Assert.assertEquals(fid1.contains(k) && k != val, fid2.contains(k));
            }
        }
    }

    /**
     * Test of minusWith method, of class MutableFiniteIntegerDomain.
     */
    @Test
    public void testMinusWith_Iterable() {
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            MutableFiniteIntegerDomain fid1 = new MutableFiniteIntegerDomain(), fid2 = new MutableFiniteIntegerDomain(), fid3;
            ArrayList<IntegerInterval> arii = new ArrayList<>();
            fillRandom(fid1);
            fillRandom(fid2, arii);
            fid3 = fid1.clone();
            fid3.minusWith(arii);
            for (int k = TestParameters.DOMAIN_LOW; k <= TestParameters.DOMAIN_HIGH; k++) {
                Assert.assertEquals(fid1.contains(k) && !fid2.contains(k), fid3.contains(k));
            }
        }
    }

    /**
     * Test of integerIterator method, of class MutableFiniteIntegerDomain.
     */
    @Test
    public void testIntegerIterator() {
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            MutableFiniteIntegerDomain fid1 = new MutableFiniteIntegerDomain();
            TreeSet<Integer> set = new TreeSet<>();
            fillRandom(fid1, set);
            Iterator<Integer> fidi = fid1.integerIterator();
            Iterator<Integer> seti = set.iterator();
            Assert.assertEquals(seti.hasNext(), fidi.hasNext());
            while (seti.hasNext() && fidi.hasNext()) {
                Assert.assertEquals(seti.next(), fidi.next());
                Assert.assertEquals(seti.hasNext(), seti.hasNext());
            }
        }
    }

    /**
     * Test of read method, of class MutableFiniteIntegerDomain.
     */
    @Test
    public void testSerialize() throws Exception {
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataOutputStream dos = new DataOutputStream(baos);
            MutableFiniteIntegerDomain fid1 = new MutableFiniteIntegerDomain(), fid2 = new MutableFiniteIntegerDomain();
            fillRandom(fid1);
            fid1.write(dos);
            dos.close();
            baos.close();
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            DataInputStream dis = new DataInputStream(bais);
            fid2.read(dis);
            dis.close();
            bais.close();
            Assert.assertEquals(fid1, fid2);
        }
    }

    /**
     * Test of hashCode method, of class MutableFiniteIntegerDomain.
     */
    @Test
    public void testHashCode() {
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            MutableFiniteIntegerDomain fid1 = new MutableFiniteIntegerDomain(), fid2 = new MutableFiniteIntegerDomain();
            fillRandom(fid1);
            fillRandom(fid2);
            if (fid1.equals(fid2)) {
                Assert.assertEquals(fid1.hashCode(), fid2.hashCode());
            }
        }
    }

    /**
     * Test of equals method, of class MutableFiniteIntegerDomain.
     */
    @Test
    public void testEquals() {
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            MutableFiniteIntegerDomain fid1 = new MutableFiniteIntegerDomain(), fid2 = new MutableFiniteIntegerDomain();
            fillRandom(fid1);
            fillRandom(fid2);
            Assert.assertEquals(Utils.arrayEquality(fid1.integerIterator(), fid2.integerIterator()), fid1.equals(fid2));
        }
    }

    /**
     * Test of readAndGenerate method, of class MutableFiniteIntegerDomain.
     */
    @Test
    public void testReadAndGenerate() throws Exception {
        MutableFiniteIntegerDomain fid3 = new MutableFiniteIntegerDomain();
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataOutputStream dos = new DataOutputStream(baos);
            MutableFiniteIntegerDomain fid1 = new MutableFiniteIntegerDomain(), fid2;
            fillRandom(fid1);
            fid1.write(dos);
            dos.close();
            baos.close();
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            DataInputStream dis = new DataInputStream(bais);
            fid2 = fid3.readAndGenerate(dis);
            dis.close();
            bais.close();
            Assert.assertEquals(fid1, fid2);
        }
    }

    /**
     * Test of intersectWith method, of class MutableFiniteIntegerDomain.
     */
    @Test
    public void testIntersectWith_Iterable() {
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            MutableFiniteIntegerDomain fid1 = new MutableFiniteIntegerDomain(), fid2 = new MutableFiniteIntegerDomain(), fid3;
            ArrayList<IntegerInterval> arii = new ArrayList<>();
            fillRandom(fid1);
            fillRandom(fid2, arii);
            fid3 = fid1.clone();
            fid3.intersectWith(arii);
            for (int k = TestParameters.DOMAIN_LOW; k <= TestParameters.DOMAIN_HIGH; k++) {
                Assert.assertEquals(fid1.contains(k) && fid2.contains(k), fid3.contains(k));
            }
        }
    }

    /**
     * Test of setToSingle method, of class MutableFiniteIntegerDomain.
     */
    @Test
    public void testSetToSingle_IntegerInterval() {
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            MutableFiniteIntegerDomain fid1 = new MutableFiniteIntegerDomain(), fid2 = new MutableFiniteIntegerDomain();
            fillRandom(fid1);
            int val1 = randomDomainValue();
            int val2 = randomDomainValue();
            fid1.setToSingle(new IntegerInterval(val1, val2));
            fid2.add(new IntegerInterval(val1, val2));
            Assert.assertEquals(fid2, fid1);
        }
    }

    /**
     * Test of setToSingle method, of class MutableFiniteIntegerDomain.
     */
    @Test
    public void testSetToSingle_int_int() {
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            MutableFiniteIntegerDomain fid1 = new MutableFiniteIntegerDomain(), fid2 = new MutableFiniteIntegerDomain();
            fillRandom(fid1);
            int val1 = randomDomainValue();
            int val2 = randomDomainValue();
            fid1.setToSingle(val1, val2);
            fid2.add(val1, val2);
            Assert.assertEquals(fid2, fid1);
        }
    }

    /**
     * Test of setToSingle method, of class MutableFiniteIntegerDomain.
     */
    @Test
    public void testSetToSingle_int() {
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            MutableFiniteIntegerDomain fid1 = new MutableFiniteIntegerDomain(), fid2 = new MutableFiniteIntegerDomain();
            fillRandom(fid1);
            int val = randomDomainValue();
            fid1.setToSingle(val);
            fid2.add(val);
            Assert.assertEquals(fid2, fid1);
        }
    }

    /**
     * Test of generate method, of class MutableFiniteIntegerDomain.
     */
    @Test
    public void testGenerate() {
        MutableFiniteIntegerDomain fid3 = new MutableFiniteIntegerDomain();
        for (int i = 0; i < TestParameters.LOOP_PARAMETER; i++) {
            MutableFiniteIntegerDomain fid1 = new MutableFiniteIntegerDomain(), fid2;
            fillRandom(fid1);
            if(fid1.size() > 0) {
                fid2 = fid3.generate(fid1.toString());
                Assert.assertEquals(fid1, fid2);
            }
        }
    }

    private void fillRandom(MutableFiniteIntegerDomain fid) {
        for (int j = Utils.nextInt(TestParameters.LOOP2_PARAMETER); j > 0; j--) {
            int v1 = randomDomainValue();
            int v2 = randomDomainValue();
            fid.add(new IntegerInterval(v1, v2));
        }
    }

    private void fillRandom(MutableFiniteIntegerDomain fid, TreeSet<Integer> treeset) {
        for (int j = Utils.nextInt(TestParameters.LOOP2_PARAMETER); j > 0; j--) {
            int v1 = randomDomainValue();
            int v2 = randomDomainValue();
            fid.add(new IntegerInterval(v1, v2));
            for (int k = v1; k <= v2; k++) {
                treeset.add(k);
            }
        }
    }

    private void fillRandom(MutableFiniteIntegerDomain fid, Collection<IntegerInterval> intervals) {
        for (int j = Utils.nextInt(TestParameters.LOOP2_PARAMETER); j > 0; j--) {
            int v1 = randomDomainValue();
            int v2 = randomDomainValue();
            fid.add(new IntegerInterval(v1, v2));
            intervals.add(new IntegerInterval(v1, v2));
        }
    }
}