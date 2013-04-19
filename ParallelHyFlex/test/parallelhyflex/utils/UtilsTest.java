package parallelhyflex.utils;

import java.util.ArrayList;
import java.util.Iterator;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author kommusoft
 */
public class UtilsTest {

    /**
     * Test of countOnes method, of class Utils.
     */
    @Test
    public void testCountOnes() {
        Assert.assertEquals(0,Utils.countOnes(0x00));
        Assert.assertEquals(2,Utils.countOnes(0x03));
        Assert.assertEquals(3,Utils.countOnes(0x92));
        Assert.assertEquals(4,Utils.countOnes(0xf0));
        Assert.assertEquals(64,Utils.countOnes(0xFFFF_FFFF_FFFF_FFFFL));
        Assert.assertEquals(32,Utils.countOnes(0xF0F0_F0F0_F0F0_F0F0L));
        Assert.assertEquals(32,Utils.countOnes(0x0F0F_0F0F_0F0F_0F0FL));
        Assert.assertEquals(32,Utils.countOnes(0x0123_4567_89AB_CDEFL));
    }

    /**
     * Test of arrayEquality method, of class Utils.
     */
    @Test
    public void testArrayEquality_longArr_longArr() {
        Assert.assertTrue(Utils.arrayEquality(new long[] {},new long[] {}));
        Assert.assertTrue(Utils.arrayEquality(new long[] {1},new long[] {1}));
        Assert.assertTrue(Utils.arrayEquality(new long[] {1,1},new long[] {1,1}));
        Assert.assertTrue(Utils.arrayEquality(new long[] {1,1,2},new long[] {1,1,2}));
        Assert.assertTrue(Utils.arrayEquality(new long[] {1,1,2,3,5},new long[] {1,1,2,3,5}));
        Assert.assertTrue(Utils.arrayEquality(new long[] {1,1,2,3,5,8,13},new long[] {1,1,2,3,5,8,13}));
        Assert.assertFalse(Utils.arrayEquality(new long[] {},new long[] {1}));
        Assert.assertFalse(Utils.arrayEquality(new long[] {1},new long[] {2}));
        Assert.assertFalse(Utils.arrayEquality(new long[] {1,1},new long[] {1,2}));
        Assert.assertFalse(Utils.arrayEquality(new long[] {1,1,2},new long[] {1,2,3}));
        Assert.assertFalse(Utils.arrayEquality(new long[] {1,1,2,3,5},new long[] {1,2,3,5,8}));
        Assert.assertFalse(Utils.arrayEquality(new long[] {1,1,2,3,5,8,13},new long[] {1,2,3,5,8,13,21}));
    }

    /**
     * Test of stringReverse method, of class Utils.
     */
    @Test
    public void testStringReverse() {
        Assert.assertEquals("abc",Utils.stringReverse("cba"));
        Assert.assertEquals("a",Utils.stringReverse("a"));
        Assert.assertEquals("abcba",Utils.stringReverse("abcba"));
    }

    /**
     * Test of arrayEquality method, of class Utils.
     */
    @Test
    public void testArrayEquality_GenericType_GenericType() {
        Assert.assertTrue(Utils.arrayEquality(new String[] {},new String[] {}));
        Assert.assertTrue(Utils.arrayEquality(new String[] {"1"},new String[] {"1"}));
        Assert.assertTrue(Utils.arrayEquality(new String[] {"1","1"},new String[] {"1","1"}));
        Assert.assertTrue(Utils.arrayEquality(new String[] {"1","1","2"},new String[] {"1","1","2"}));
        Assert.assertTrue(Utils.arrayEquality(new String[] {"1","1","2","3","5"},new String[] {"1","1","2","3","5"}));
        Assert.assertTrue(Utils.arrayEquality(new String[] {"1","1","2","3","5","8","13"},new String[] {"1","1","2","3","5","8","13"}));
        Assert.assertFalse(Utils.arrayEquality(new String[] {},new String[] {"1"}));
        Assert.assertFalse(Utils.arrayEquality(new String[] {"1"},new String[] {"2"}));
        Assert.assertFalse(Utils.arrayEquality(new String[] {"1,1"},new String[] {"1","2"}));
        Assert.assertFalse(Utils.arrayEquality(new String[] {"1","1","2"},new String[] {"1","2","3"}));
        Assert.assertFalse(Utils.arrayEquality(new String[] {"1","1","2","3","5"},new String[] {"1","2","3","5","8"}));
        Assert.assertFalse(Utils.arrayEquality(new String[] {"1","1","2","3","5","8","13"},new String[] {"1","2","3","5","8","13","21"}));
    }

    /**
     * Test of getLimitedModuloEnumerable method, of class Utils.
     */
    @Test
    public void testGetLimitedModuloEnumerable_3args() {
        ArrayList<Integer> a = Utils.toArrayList(Utils.sequenceModulo(4,3,10));
        Assert.assertArrayEquals(new Integer[] {4,7,0,3},a.toArray(new Integer[] {}));
    }

    /**
     * Test of getLimitedModuloEnumerable method, of class Utils.
     */
    @Test
    public void testGetLimitedModuloEnumerable_int_int() {
        ArrayList<Integer> a = Utils.toArrayList(Utils.sequenceModulo(4,10));
        Assert.assertArrayEquals(new Integer[] {4,5,6,7,8,9,0,1,2,3},a.toArray(new Integer[] {}));
    }
    
    @Test
    public void testSequence1 () {
        Iterable<Integer> iterable = Utils.sequence(5);
        Iterator<Integer> iterator = iterable.iterator();
        Assert.assertTrue(iterator.hasNext()); Assert.assertEquals(0,(int) iterator.next());
        Assert.assertTrue(iterator.hasNext()); Assert.assertEquals(1,(int) iterator.next());
        Assert.assertTrue(iterator.hasNext()); Assert.assertEquals(2,(int) iterator.next());
        Assert.assertTrue(iterator.hasNext()); Assert.assertEquals(3,(int) iterator.next());
        Assert.assertTrue(iterator.hasNext()); Assert.assertEquals(4,(int) iterator.next());
        Assert.assertFalse(iterator.hasNext());
        iterator = iterable.iterator();
        Assert.assertTrue(iterator.hasNext()); Assert.assertEquals(0,(int) iterator.next());
        Assert.assertTrue(iterator.hasNext()); Assert.assertEquals(1,(int) iterator.next());
        Assert.assertTrue(iterator.hasNext()); Assert.assertEquals(2,(int) iterator.next());
        Assert.assertTrue(iterator.hasNext()); Assert.assertEquals(3,(int) iterator.next());
        Assert.assertTrue(iterator.hasNext()); Assert.assertEquals(4,(int) iterator.next());
        Assert.assertFalse(iterator.hasNext());
        iterable = Utils.sequence(7);
        iterator = iterable.iterator();
        Assert.assertTrue(iterator.hasNext()); Assert.assertEquals(0,(int) iterator.next());
        Assert.assertTrue(iterator.hasNext()); Assert.assertEquals(1,(int) iterator.next());
        Assert.assertTrue(iterator.hasNext()); Assert.assertEquals(2,(int) iterator.next());
        Assert.assertTrue(iterator.hasNext()); Assert.assertEquals(3,(int) iterator.next());
        Assert.assertTrue(iterator.hasNext()); Assert.assertEquals(4,(int) iterator.next());
        Assert.assertTrue(iterator.hasNext()); Assert.assertEquals(5,(int) iterator.next());
        Assert.assertTrue(iterator.hasNext()); Assert.assertEquals(6,(int) iterator.next());
        Assert.assertFalse(iterator.hasNext());
        iterator = iterable.iterator();
        Assert.assertTrue(iterator.hasNext()); Assert.assertEquals(0,(int) iterator.next());
        Assert.assertTrue(iterator.hasNext()); Assert.assertEquals(1,(int) iterator.next());
        Assert.assertTrue(iterator.hasNext()); Assert.assertEquals(2,(int) iterator.next());
        Assert.assertTrue(iterator.hasNext()); Assert.assertEquals(3,(int) iterator.next());
        Assert.assertTrue(iterator.hasNext()); Assert.assertEquals(4,(int) iterator.next());
        Assert.assertTrue(iterator.hasNext()); Assert.assertEquals(5,(int) iterator.next());
        Assert.assertTrue(iterator.hasNext()); Assert.assertEquals(6,(int) iterator.next());
        Assert.assertFalse(iterator.hasNext());
        iterable = Utils.sequence(0);
        iterator = iterable.iterator();
        Assert.assertFalse(iterator.hasNext());
        iterator = iterable.iterator();
        Assert.assertFalse(iterator.hasNext());
    }
    
    @Test
    public void testSequence2 () {
        Iterable<Integer> iterable = Utils.sequence(1,5);
        Iterator<Integer> iterator = iterable.iterator();
        Assert.assertTrue(iterator.hasNext()); Assert.assertEquals(1,(int) iterator.next());
        Assert.assertTrue(iterator.hasNext()); Assert.assertEquals(2,(int) iterator.next());
        Assert.assertTrue(iterator.hasNext()); Assert.assertEquals(3,(int) iterator.next());
        Assert.assertTrue(iterator.hasNext()); Assert.assertEquals(4,(int) iterator.next());
        Assert.assertFalse(iterator.hasNext());
        iterator = iterable.iterator();
        Assert.assertTrue(iterator.hasNext()); Assert.assertEquals(1,(int) iterator.next());
        Assert.assertTrue(iterator.hasNext()); Assert.assertEquals(2,(int) iterator.next());
        Assert.assertTrue(iterator.hasNext()); Assert.assertEquals(3,(int) iterator.next());
        Assert.assertTrue(iterator.hasNext()); Assert.assertEquals(4,(int) iterator.next());
        Assert.assertFalse(iterator.hasNext());
        iterable = Utils.sequence(3,7);
        iterator = iterable.iterator();
        Assert.assertTrue(iterator.hasNext()); Assert.assertEquals(3,(int) iterator.next());
        Assert.assertTrue(iterator.hasNext()); Assert.assertEquals(4,(int) iterator.next());
        Assert.assertTrue(iterator.hasNext()); Assert.assertEquals(5,(int) iterator.next());
        Assert.assertTrue(iterator.hasNext()); Assert.assertEquals(6,(int) iterator.next());
        Assert.assertFalse(iterator.hasNext());
        iterator = iterable.iterator();
        Assert.assertTrue(iterator.hasNext()); Assert.assertEquals(3,(int) iterator.next());
        Assert.assertTrue(iterator.hasNext()); Assert.assertEquals(4,(int) iterator.next());
        Assert.assertTrue(iterator.hasNext()); Assert.assertEquals(5,(int) iterator.next());
        Assert.assertTrue(iterator.hasNext()); Assert.assertEquals(6,(int) iterator.next());
        Assert.assertFalse(iterator.hasNext());
        iterable = Utils.sequence(5,5);
        iterator = iterable.iterator();
        Assert.assertFalse(iterator.hasNext());
        iterator = iterable.iterator();
        Assert.assertFalse(iterator.hasNext());
        iterable = Utils.sequence(8,3);
        iterator = iterable.iterator();
        Assert.assertFalse(iterator.hasNext());
        iterator = iterable.iterator();
        Assert.assertFalse(iterator.hasNext());
    }
    
    @Test
    public void testSequence3 () {
        Iterable<Integer> iterable = Utils.sequence(1,2,5);
        Iterator<Integer> iterator = iterable.iterator();
        Assert.assertTrue(iterator.hasNext()); Assert.assertEquals(1,(int) iterator.next());
        Assert.assertTrue(iterator.hasNext()); Assert.assertEquals(3,(int) iterator.next());
        Assert.assertFalse(iterator.hasNext());
        iterator = iterable.iterator();
        Assert.assertTrue(iterator.hasNext()); Assert.assertEquals(1,(int) iterator.next());
        Assert.assertTrue(iterator.hasNext()); Assert.assertEquals(3,(int) iterator.next());
        Assert.assertFalse(iterator.hasNext());
        iterable = Utils.sequence(3,3,7);
        iterator = iterable.iterator();
        Assert.assertTrue(iterator.hasNext()); Assert.assertEquals(3,(int) iterator.next());
        Assert.assertTrue(iterator.hasNext()); Assert.assertEquals(6,(int) iterator.next());
        Assert.assertFalse(iterator.hasNext());
        iterator = iterable.iterator();
        Assert.assertTrue(iterator.hasNext()); Assert.assertEquals(3,(int) iterator.next());
        Assert.assertTrue(iterator.hasNext()); Assert.assertEquals(6,(int) iterator.next());
        Assert.assertFalse(iterator.hasNext());
        iterable = Utils.sequence(5,1,5);
        iterator = iterable.iterator();
        Assert.assertFalse(iterator.hasNext());
        iterator = iterable.iterator();
        Assert.assertFalse(iterator.hasNext());
        iterable = Utils.sequence(8,3,3);
        iterator = iterable.iterator();
        Assert.assertFalse(iterator.hasNext());
        iterator = iterable.iterator();
        Assert.assertFalse(iterator.hasNext());
    }
    
}