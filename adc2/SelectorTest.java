import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.Comparator;
import java.util.Collections;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class SelectorTest {
   
   /** Fixture initialization (common initialization
    *  for all tests). **/
   private ArrayList<Integer> a1 = new ArrayList<Integer>(); 
   private ArrayList<Integer> a2 = new ArrayList<Integer>();
   private ArrayList<Integer> a3 = new ArrayList<Integer>();
   private ArrayList<Integer> a4 = new ArrayList<Integer>();
   private ArrayList<Integer> a5 = new ArrayList<Integer>();
   private ArrayList<Integer> a6 = new ArrayList<Integer>();
   
   private Integer min = 1;
   private Integer max = 9;
   private Integer minFirstA1 = 1;
   private Integer minLastA2 = 9;
   private Integer minThirdA6 = 4;
   private Integer maxFirstA1 = 9;
   private Integer maxLastA2 = 1;
   private Integer maxThirdA6 = 2;
   private Integer[] threeA2MinRange = {1, 2, 4};
   private Integer[] threeA2MidRange = {2, 4, 5};
   private Integer[] threeA2MaxRange = {5, 7, 9};
   private Integer ceilingFirstA1 = 9;
   private Integer ceilingLastA2 = 9;
   private Integer ceilingMidA3 = 5;
   
   @Before public void setUp() {
      a1.add(1); a5.add(1);
      a1.add(2); a5.add(1);
      a1.add(7); a5.add(1);
      a1.add(4); a5.add(7);
      a1.add(5); a5.add(7);
      a1.add(9); 
      a6.add(7);
      a2.add(9); a6.add(7);
      a2.add(5); a6.add(2);
      a2.add(4); a6.add(4);
      a2.add(7); a6.add(1);
      a2.add(2); a6.add(1);
      a2.add(1);
      
      a3.add(5);
      a3.add(4);
      a3.add(1);
      a3.add(9);
      a3.add(7);
      a3.add(2);
   }
   
   @Test (expected = IllegalArgumentException.class)
   public void minNullComparatorTest() {
      Selector.<Integer>min(a1, null);
   }
   
   @Test (expected = IllegalArgumentException.class)
   public void minNullTypeTest() {
      Selector.<Integer>min(null, new CompareIntByStdOrder());
   }
   
   @Test (expected = NoSuchElementException.class)
   public void minEmptyTest() {
      Selector.<Integer>min(a4, new CompareIntByStdOrder());
   }
   
   /**
   Min is first item of array.
   */
   @Test
   public void minFirstTest() {
      Assert.assertEquals("Min in first item of array failed",
                           Selector.<Integer>min(a1, new CompareIntByStdOrder()), min);
   }
   
   /**
   Min is last item of array.
   */
   @Test
   public void minLastTest() {
      Assert.assertEquals("Min in last item of array failed",
                           Selector.<Integer>min(a2, new CompareIntByStdOrder()), min);
   }
   
   /**
   Min is middle item of array.
   */
   @Test
   public void minMidTest() {
      Assert.assertEquals("Min in middle item of array failed",
                           Selector.<Integer>min(a3, new CompareIntByStdOrder()), min);
   }
   
   @Test (expected = IllegalArgumentException.class)
   public void maxNullTypeTest() {
      Selector.<Integer>max(null, new CompareIntByStdOrder());
   }
   
   @Test (expected = IllegalArgumentException.class)
   public void maxNullComparatorTest() {
      Selector.<Integer>max(a1, null);
   }
   
   @Test (expected = NoSuchElementException.class)
   public void maxEmptyTest() {
      Selector.<Integer>max(a4, new CompareIntByStdOrder());
   }
   
   /**
   Max is first item of array
   */
   @Test
   public void maxFirstTest() {
      Assert.assertEquals("Max in first item of array failed",
                           Selector.<Integer>max(a2, new CompareIntByStdOrder()), max);
   }
   
   /**
   Max is last item of array
   */
   @Test
   public void maxLastTest() {
      Assert.assertEquals("Max in last item of array failed",
                           Selector.<Integer>max(a1, new CompareIntByStdOrder()), max);
   }
   
   /**
   * Max is middle item of array
   */
   @Test
   public void maxMidTest() {
      Assert.assertEquals("Max in middle item of array failed",
                           Selector.<Integer>max(a3, new CompareIntByStdOrder()), max);
   }
   
   /**
   * Test for null type.
   */
   @Test (expected = IllegalArgumentException.class)
   public void kthMinNullTypeTest() {
      Selector.<Integer>kmin(null, 2, new CompareIntByStdOrder());
   }
   
   /**
   * Test for null comparator.
   */
   @Test (expected = IllegalArgumentException.class)
   public void kthMinNullComparatorTest() {
      Selector.<Integer>kmin(a1, 2, null);
   }
   
   /**
   * Test for empty array.
   */
   @Test (expected = NoSuchElementException.class)
   public void kthMinEmptyTest() {
      Selector.<Integer>kmin(a4, 1, new CompareIntByStdOrder());
   }
   
   /**
   * Test for kth min out of bounds check.
   */
   @Test (expected = NoSuchElementException.class)
   public void kthOutOfBoundsMinTest() {
      Selector.<Integer>kmin(a3, 10, new CompareIntByStdOrder());
   }
   
   /**
   * Test for k less than one.
   */
   @Test (expected = NoSuchElementException.class)
   public void kthMinLessThanOneTest() {
      Selector.<Integer>kmin(a3, -1, new CompareIntByStdOrder());
   }
   
   
   /**
   * Test for the special case where
   * a unique kth min does not exist.
   */
   @Test (expected = NoSuchElementException.class)
   public void kthMinNotReachedTest() {
      Selector.<Integer>kmin(a5, 3, new CompareIntByStdOrder());
   }
   
   /**
   * Test for boundary first element.
   */
   @Test
   public void kminFirstTest() {
      Assert.assertEquals("Failed boundary condition first element",
             Selector.<Integer>kmin(a1, 1, new CompareIntByStdOrder()), minFirstA1);
   }

    /**
    * Test for boundary last element.
    */
   @Test
   public void kminLastTest() {
      Assert.assertEquals("Failed boundary condition last element",
             Selector.<Integer>kmin(a2, 6, new CompareIntByStdOrder()), minLastA2);
   }
    
    /**
    * Test for typical middle example.
    */
   @Test
   public void kminMidTest() {
      Assert.assertEquals("Failed for generic middle element",
            Selector.<Integer>kmin(a6, 3, new CompareIntByStdOrder()), minThirdA6);
   }
   
   /**
   * Test for single element array
   */
   @Test
   public void kminSingleTest() {
      ArrayList<Integer> single = new ArrayList<Integer>();
      single.add(2);
      Integer expected = 2;
      Assert.assertEquals("Failed single element boundary case",
             Selector.<Integer>kmin(single, 1, new CompareIntByStdOrder()), expected);
   }
   
   /**
   * Test for two element array (first min)
   */
   @Test
   public void kminTwoFirstTest() {
      ArrayList<Integer> two = new ArrayList<Integer>();
      two.add(2);
      two.add(1);
      Integer expected = 1;
      Assert.assertEquals("Failed two element 1st min case",
             Selector.<Integer>kmin(two, 1, new CompareIntByStdOrder()), expected);
   }
   
   /**
   * Test for two element array (second min)
   */
   @Test
   public void kminTwoSecondTest() {
      ArrayList<Integer> two = new ArrayList<Integer>();
      two.add(2);
      two.add(1);
      Integer expected = 2;
      Assert.assertEquals("Failed two element 2nd min case",
             Selector.<Integer>kmin(two, 2, new CompareIntByStdOrder()), expected);
   }
   
   /**
   * Test for null type.
   */
   @Test (expected = IllegalArgumentException.class)
   public void kthMaxNullTypeTest() {
      Selector.<Integer>kmax(null, 2, new CompareIntByStdOrder());
   }
   
   /**
   * Test for null comparator
   */
   @Test (expected = IllegalArgumentException.class)
   public void kthMaxNullComparatorTest() {
      Selector.<Integer>kmax(a1, 1, null);
   }
   
   /**
   * Test for empty array.
   */
   @Test (expected = NoSuchElementException.class)
   public void kthMaxEmptyTest() {
      Selector.<Integer>kmax(a4, 1, new CompareIntByStdOrder());
   }
   
   /**
   * Test for kth max out of bounds check.
   */
   @Test (expected = NoSuchElementException.class)
   public void kthOutOfBoundsMaxTest() {
      Selector.<Integer>kmax(a3, 10, new CompareIntByStdOrder());
   }
   
   /**
   * Test for k < 1.
   */
   @Test (expected = NoSuchElementException.class)
   public void kthMaxLessThanOneTest() {
      Selector.<Integer>kmax(a3, -1, new CompareIntByStdOrder());
   }
   
   
   /**
   * Test for the special case where
   * a unique kth max does not exist.
   */
   @Test (expected = NoSuchElementException.class)
   public void kthMaxNotReachedTest() {
      Selector.<Integer>kmax(a5, 3, new CompareIntByStdOrder());
   }
   
   /**
   * Test for boundary first element.
   */
   @Test
   public void kmaxFirstTest() {
      Assert.assertEquals("Failed boundary condition first element",
             Selector.<Integer>kmax(a1, 1, new CompareIntByStdOrder()), maxFirstA1);
   }

    /**
    * Test for boundary last element.
    */
   @Test
   public void kmaxLastTest() {
      Assert.assertEquals("Failed boundary condition last element",
             Selector.<Integer>kmax(a2, 6, new CompareIntByStdOrder()), maxLastA2);
   }
    
    /**
    * Test for typical middle example.
    */
   @Test
   public void kmaxMidTest() {
      Assert.assertEquals("Failed for generic middle element",
            Selector.<Integer>kmax(a6, 3, new CompareIntByStdOrder()), maxThirdA6);
   }
   
   /**
   * Test for single element array
   */
   @Test
   public void kmaxSingleTest() {
      ArrayList<Integer> single = new ArrayList<Integer>();
      single.add(2);
      Integer expected = 2;
      Assert.assertEquals("Failed single element boundary case",
             Selector.<Integer>kmax(single, 1, new CompareIntByStdOrder()), expected);
   }
   
   /**
   * Test for two element array (first max)
   */
   @Test
   public void kmaxTwoFirstTest() {
      ArrayList<Integer> two = new ArrayList<Integer>();
      two.add(2);
      two.add(1);
      Integer expected = 2;
      Assert.assertEquals("Failed two element 1st max case",
             Selector.<Integer>kmax(two, 1, new CompareIntByStdOrder()), expected);
   }
   
   /**
   * Test for two element array (second max)
   */
   @Test
   public void kmaxTwoSecondTest() {
      ArrayList<Integer> two = new ArrayList<Integer>();
      two.add(2);
      two.add(1);
      Integer expected = 1;
      Assert.assertEquals("Failed two element 2nd max case",
             Selector.<Integer>kmax(two, 2, new CompareIntByStdOrder()), expected);
   }
   
   /**
   * Test for null type.
   */
   @Test (expected = IllegalArgumentException.class)
   public void rangeNullTypeTest() {
      Selector.<Integer>range(null, 2, 4, new CompareIntByStdOrder());
   }
   
   /**
   * Test for null comparator.
   */
   @Test (expected = IllegalArgumentException.class)
   public void rangeNullComparatorTest() {
      Selector.<Integer>range(a1, 2, 4, null);
   }
   
   /**
   * Test for array zero in arrange
   */
   @Test (expected = NoSuchElementException.class)
   public void rangeZeroTest() {
      Selector.<Integer>range(a4, 0, 2, new CompareIntByStdOrder());
   }
   
   /**
   * Test for no items in range.
   */
   @Test (expected = NoSuchElementException.class)
   public void noItemsInRangeTest() {
      Selector.<Integer>range(a3, 10, 100, new CompareIntByStdOrder());
   }
   
   /**
   * Whole array in range.
   */
   public void wholeArrayInRangeTest() {
      //unnessarly large range to get whole array
      Assert.assertArrayEquals("Whole array was not in range.",
             Selector.<Integer>range(a2, -100, 100, new CompareIntByStdOrder()).toArray(new Integer[0]), a2.toArray(new Integer[0]));
   }
   
   /**
   * Typical array, middle range.
   */
   public void middleInRangeTest() {
      Assert.assertArrayEquals("Middle three values of array not in range.",
             Selector.<Integer>range(a2, 2, 6, new CompareIntByStdOrder()).toArray(new Integer[0]), threeA2MidRange);
   }
   
   /**
   * Boundary condition, min range.
   */
   public void minInRangeTest() {
      Assert.assertArrayEquals("Boundary condition of min three values" +
             " in array failed",
             Selector.<Integer>range(a2, -2, 4, new CompareIntByStdOrder()).toArray(new Integer[0]), threeA2MinRange);
   }
   
   /**
   * Boundary condition, max range.
   */
   public void maxInRangeTest() {
      Assert.assertArrayEquals("Boundary condition of max three values" +
             " in array failed.",
             Selector.<Integer>range(a2, 5, 100, new CompareIntByStdOrder()).toArray(new Integer[0]), threeA2MaxRange);
   }
   
   /**
   * Test for single element array
   */
   @Test
   public void rangeSingleTest() {
      ArrayList<Integer> single = new ArrayList<Integer>();
      single.add(2);
      Assert.assertArrayEquals("Failed single element boundary case",
             Selector.<Integer>range(single, 1, 2, new CompareIntByStdOrder()).toArray(new Integer[0]), single.toArray(new Integer[0]));
   }
   
   /**
   * Test for two element array (first range)
   */
   @Test
   public void rangeTwoFirstTest() {
      ArrayList<Integer> two = new ArrayList<Integer>();
      two.add(2);
      two.add(1);
      Integer[] expected = {1};
      Assert.assertArrayEquals("Failed two element 1st range case",
             Selector.<Integer>range(two, 0, 1, new CompareIntByStdOrder()).toArray(new Integer[0]), expected);
   }
   
   /**
   * Test for two element array (second range)
   */
   @Test
   public void rangeTwoSecondTest() {
      ArrayList<Integer> two = new ArrayList<Integer>();
      two.add(2);
      two.add(1);
      Integer[] expected = {2};
      Assert.assertArrayEquals("Failed two element 2nd range case",
             Selector.<Integer>range(two, 2, 5, new CompareIntByStdOrder()).toArray(new Integer[0]), expected);
   }
   
   /**
   * Test for two element array (second range)
   */
   @Test
   public void rangeTwoTest() {
      ArrayList<Integer> two = new ArrayList<Integer>();
      two.add(2);
      two.add(1);
      Integer[] expected = {2, 1};
      Assert.assertArrayEquals("Failed two element both elements case",
             Selector.<Integer>range(two, 1, 2, new CompareIntByStdOrder()).toArray(new Integer[0]), expected);
   }
   
   /**
   * Test for the special condition that
   * that high and low are the same value.
   */
   @Test
   public void rangeDupTest() {
      Integer[] expected = {7};
      Assert.assertArrayEquals("Same high low test.",
             Selector.<Integer>range(a2, 7, 7, new CompareIntByStdOrder()).toArray(new Integer[0]), expected);
   }
   
   /**
   * Test for null type.
   */
   @Test (expected = IllegalArgumentException.class)
   public void ceilingNullTypeTest() {
      Selector.ceiling(null, 2, new CompareIntByStdOrder());
   }
   
   /**
   * Test for null comparator.
   */
   @Test (expected = IllegalArgumentException.class)
   public void ceilingNullComparatorTest() {
      Selector.<Integer>ceiling(a1, 2, null);
   }
   
   /**
   * Test for array zero in range
   */
   @Test (expected = NoSuchElementException.class)
   public void ceilingZeroTest() {
      Selector.ceiling(a4, 2, new CompareIntByStdOrder());
   }
   
   /**
   * Test for no ceiling
   */
   @Test (expected = NoSuchElementException.class)
   public void noCeilingTest() {
      Selector.<Integer>range(a3, 100, new CompareIntByStdOrder());
   }
   
   /**
   * Typical ceiling, middle range.
   */
   public void middleCeilingTest() {
      Assert.assertEquals("Middle average ceiling failed.",
             Selector.<Integer>range(a2, 3, new CompareIntByStdOrder()), ceilingMidA3);
   }
   
   /**
   * Boundary condition, min range.
   */
   public void minInRangeTest() {
      Assert.assertArrayEquals("Boundary condition of min three values" +
             " in array failed",
             Selector.<Integer>range(a2, -2, 4, new CompareIntByStdOrder()).toArray(new Integer[0]), threeA2MinRange);
   }
   
   /**
   * Boundary condition, max range.
   */
   public void maxInRangeTest() {
      Assert.assertArrayEquals("Boundary condition of max three values" +
             " in array failed.",
             Selector.<Integer>range(a2, 5, 100, new CompareIntByStdOrder()).toArray(new Integer[0]), threeA2MaxRange);
   }
   
   /**
   * Test for single element array
   */
   @Test
   public void rangeSingleTest() {
      ArrayList<Integer> single = new ArrayList<Integer>();
      single.add(2);
      Assert.assertArrayEquals("Failed single element boundary case",
             Selector.<Integer>range(single, 1, 2, new CompareIntByStdOrder()).toArray(new Integer[0]), single.toArray(new Integer[0]));
   }
   
   /**
   * Test for two element array (first range)
   */
   @Test
   public void rangeTwoFirstTest() {
      ArrayList<Integer> two = new ArrayList<Integer>();
      two.add(2);
      two.add(1);
      Integer[] expected = {1};
      Assert.assertArrayEquals("Failed two element 1st range case",
             Selector.<Integer>range(two, 0, 1, new CompareIntByStdOrder()).toArray(new Integer[0]), expected);
   }
   
   /**
   * Test for two element array (second range)
   */
   @Test
   public void rangeTwoSecondTest() {
      ArrayList<Integer> two = new ArrayList<Integer>();
      two.add(2);
      two.add(1);
      Integer[] expected = {2};
      Assert.assertArrayEquals("Failed two element 2nd range case",
             Selector.<Integer>range(two, 2, 5, new CompareIntByStdOrder()).toArray(new Integer[0]), expected);
   }
   
   /**
   * Test for two element array (second range)
   */
   @Test
   public void rangeTwoTest() {
      ArrayList<Integer> two = new ArrayList<Integer>();
      two.add(2);
      two.add(1);
      Integer[] expected = {2, 1};
      Assert.assertArrayEquals("Failed two element both elements case",
             Selector.<Integer>range(two, 1, 2, new CompareIntByStdOrder()).toArray(new Integer[0]), expected);
   }
   
   /**
   * Test for the special condition that
   * that high and low are the same value.
   */
   @Test
   public void rangeDupTest() {
      Integer[] expected = {7};
      Assert.assertArrayEquals("Same high low test.",
             Selector.<Integer>range(a2, 7, 7, new CompareIntByStdOrder()).toArray(new Integer[0]), expected);
   }
}


class CompareIntByStdOrder implements Comparator<Integer> {
   public int compare(Integer i1, Integer i2) {
      return i1.compareTo(i2);
   }
}