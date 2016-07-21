import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class SelectorTest {
   
   /** Fixture initialization (common initialization
    *  for all tests). **/
   private int[] a1 = {1, 2, 7, 4, 5, 9}; 
   private int[] a2 = {9, 5, 4, 7, 2, 1};
   private int[] a3 = {5, 4, 1, 9, 7, 2};
   private int[] a4 = {};
   private int[] a5 = {1, 1, 1, 7, 7};
   private int[] a6 = {7, 7, 2, 4, 1, 1};
   
   private int min = 1;
   private int max = 9;
   private int minFirstA1 = 1;
   private int minLastA2 = 9;
   private int minThirdA6 = 4;
   private int maxFirstA1 = 9;
   private int maxLastA2 = 1;
   private int maxThirdA6 = 2;
   private int[] threeA2MinRange = {1, 2, 4};
   private int[] threeA2MidRange = {2, 4, 5};
   private int[] threeA2MaxRange = {5, 7, 9};
   private int ceilingFirstA1 = 9;
   private int ceilingLastA2 = 9;
   private int ceilingMidA3 = 5;
   
   @Before public void setUp() {
      
   }
   
   @Test (expected = IllegalArgumentException.class)
   public void minNullTest() {
      Selector.min(null);
   }
   
   @Test (expected = IllegalArgumentException.class)
   public void minEmptyTest() {
      Selector.min(a4);
   }
   
   /**
   Min is first item of array.
   */
   @Test
   public void minFirstTest() {
      Assert.assertEquals("Min in first item of array failed",
                           Selector.min(a1), min);
   }
   
   /**
   Min is last item of array.
   */
   @Test
   public void minLastTest() {
      Assert.assertEquals("Min in last item of array failed",
                           Selector.min(a2), min);
   }
   
   /**
   Min is middle item of array.
   */
   @Test
   public void minMidTest() {
      Assert.assertEquals("Min in middle item of array failed",
                           Selector.min(a3), min);
   }
   
   @Test (expected = IllegalArgumentException.class)
   public void maxNullTest() {
      Selector.max(null);
   }
   
   @Test (expected = IllegalArgumentException.class)
   public void maxEmptyTest() {
      Selector.max(a4);
   }
   
   /**
   Max is first item of array
   */
   @Test
   public void maxFirstTest() {
      Assert.assertEquals("Max in first item of array failed",
                           Selector.max(a2), max);
   }
   
   /**
   Max is last item of array
   */
   @Test
   public void maxLastTest() {
      Assert.assertEquals("Max in last item of array failed",
                           Selector.max(a1), max);
   }
   
   /**
   * Max is middle item of array
   */
   @Test
   public void maxMidTest() {
      Assert.assertEquals("Max in middle item of array failed",
                           Selector.max(a3), max);
   }
   
   /**
   * Test for null case.
   */
   @Test (expected = IllegalArgumentException.class)
   public void kthMinNullTest() {
      Selector.kmin(null, 2);
   }
   
   /**
   * Test for empty array.
   */
   @Test (expected = IllegalArgumentException.class)
   public void kthMinEmptyTest() {
      Selector.kmin(a4, 1);
   }
   
   /**
   * Test for kth min out of bounds check.
   */
   @Test (expected = IllegalArgumentException.class)
   public void kthOutOfBoundsMinTest() {
      Selector.kmin(a3, 10);
   }
   
   /**
   * Test for zero k zero.
   */
   @Test (expected = IllegalArgumentException.class)
   public void kthMinZeroTest() {
      Selector.kmin(a3, 0);
   }
   
   
   /**
   * Test for the special case where
   * a unique kth min does not exist.
   */
   @Test (expected = IllegalArgumentException.class)
   public void kthMinNotReachedTest() {
      Selector.kmin(a5, 3);
   }
   
   /**
   * Test for boundary first element.
   */
   @Test
   public void kminFirstTest() {
      Assert.assertEquals("Failed boundary condition first element",
             Selector.kmin(a1, 1), minFirstA1);
   }

    /**
    * Test for boundary last element.
    */
   @Test
   public void kminLastTest() {
      Assert.assertEquals("Failed boundary condition last element",
             Selector.kmin(a2, 6), minLastA2);
   }
    
    /**
    * Test for typical middle example.
    */
   @Test
   public void kminMidTest() {
      Assert.assertEquals("Failed for generic middle element",
            Selector.kmin(a6, 3), minThirdA6);
   }
   
   /**
   * Test for single element array
   */
   @Test
   public void kminSingleTest() {
      int[] single = {2};
      Assert.assertEquals("Failed single element boundary case",
             Selector.kmin(single, 1), 2);
   }
   
   /**
   * Test for two element array (first min)
   */
   @Test
   public void kminTwoFirstTest() {
      int[] two = {2,1};
      Assert.assertEquals("Failed two element 1st min case",
             Selector.kmin(two, 1), 1);
   }
   
   /**
   * Test for two element array (second min)
   */
   @Test
   public void kminTwoSecondTest() {
      int[] two = {2,1};
      Assert.assertEquals("Failed two element 2nd min case",
             Selector.kmin(two, 2), 2);
   }
   
   /**
   * Test for null case.
   */
   @Test (expected = IllegalArgumentException.class)
   public void kthMaxNullTest() {
      Selector.kmax(null, 2);
   }
   
   /**
   * Test for empty array.
   */
   @Test (expected = IllegalArgumentException.class)
   public void kthMaxEmptyTest() {
      Selector.kmax(a4, 1);
   }
   
   /**
   * Test for kth max out of bounds check.
   */
   @Test (expected = IllegalArgumentException.class)
   public void kthOutOfBoundsMaxTest() {
      Selector.kmax(a3, 10);
   }
   
   /**
   * Test for zero k zero.
   */
   @Test (expected = IllegalArgumentException.class)
   public void kthMaxZeroTest() {
      Selector.kmax(a3, 0);
   }
   
   
   /**
   * Test for the special case where
   * a unique kth max does not exist.
   */
   @Test (expected = IllegalArgumentException.class)
   public void kthMaxNotReachedTest() {
      Selector.kmax(a5, 3);
   }
   
   /**
   * Test for boundary first element.
   */
   @Test
   public void kmaxFirstTest() {
      Assert.assertEquals("Failed boundary condition first element",
             Selector.kmax(a1, 1), maxFirstA1);
   }

    /**
    * Test for boundary last element.
    */
   @Test
   public void kmaxLastTest() {
      Assert.assertEquals("Failed boundary condition last element",
             Selector.kmax(a2, 6), maxLastA2);
   }
    
    /**
    * Test for typical middle example.
    */
   @Test
   public void kmaxMidTest() {
      Assert.assertEquals("Failed for generic middle element",
            Selector.kmax(a6, 3), maxThirdA6);
   }
   
   /**
   * Test for single element array
   */
   @Test
   public void kmaxSingleTest() {
      int[] single = {2};
      Assert.assertEquals("Failed single element boundary case",
             Selector.kmax(single, 1), 2);
   }
   
   /**
   * Test for two element array (first max)
   */
   @Test
   public void kmaxTwoFirstTest() {
      int[] two = {2,1};
      Assert.assertEquals("Failed two element 1st max case",
             Selector.kmax(two, 1), 2);
   }
   
   /**
   * Test for two element array (second max)
   */
   @Test
   public void kmaxTwoSecondTest() {
      int[] two = {2,1};
      Assert.assertEquals("Failed two element 2nd max case",
             Selector.kmax(two, 2), 1);
   }
   
   /**
   * Test for null case.
   */
   @Test (expected = IllegalArgumentException.class)
   public void rangeNullTest() {
      Selector.range(null, 2, 4);
   }
   
   /**
   * Test for array zero in arrange
   */
   @Test (expected = IllegalArgumentException.class)
   public void rangeZeroTest() {
      Selector.range(a4, 0, 2);
   }
   
   /**
   * Test for no items in range.
   */
   @Test
   public void noItemsInRangeTest() {
      //tests length of the returned array for elements
      Assert.assertEquals("Items were found when no" +
             "items were in range", 
             Selector.range(a3, 10, 100).length,
             0);
   }
   
   /**
   * Whole array in range.
   */
   public void wholeArrayInRangeTest() {
      //unnessarly large range to get whole array
      Assert.assertArrayEquals("Whole array was not in range.",
             Selector.range(a2, -100, 100), a2);
   }
   
   /**
   * Typical array, middle range.
   */
   public void middleInRangeTest() {
      Assert.assertArrayEquals("Middle three values of array not in range.",
             Selector.range(a2, 2, 6), threeA2MidRange);
   }
   
   /**
   * Boundary condition, min range.
   */
   public void minInRangeTest() {
      Assert.assertArrayEquals("Boundary condition of min three values" +
             " in array failed",
             Selector.range(a2, -2, 4), threeA2MinRange);
   }
   
   /**
   * Boundary condition, max range.
   */
   public void maxInRangeTest() {
      Assert.assertArrayEquals("Boundary condition of max three values" +
             " in array failed.",
             Selector.range(a2, 5, 100), threeA2MaxRange);
   }
   
   /**
   * Test for single element array
   */
   @Test
   public void rangeSingleTest() {
      int[] single = {2};
      Assert.assertArrayEquals("Failed single element boundary case",
             Selector.range(single, 1, 2), single);
   }
   
   /**
   * Test for two element array (first range)
   */
   @Test
   public void rangeTwoFirstTest() {
      int[] two = {2,1};
      int[] expected = {1};
      Assert.assertArrayEquals("Failed two element 1st range case",
             Selector.range(two, 0, 1), expected);
   }
   
   /**
   * Test for two element array (second range)
   */
   @Test
   public void rangeTwoSecondTest() {
      int[] two = {2,1};
      int[] expected = {2};
      Assert.assertArrayEquals("Failed two element 2nd range case",
             Selector.range(two, 2, 5), expected);
   }
   
   /**
   * Test for two element array (second range)
   */
   @Test
   public void rangeTwoTest() {
      int[] two = {2,1};
      int[] expected = {2, 1};
      Assert.assertArrayEquals("Failed two element both elements case",
             Selector.range(two, 1, 2), expected);
   }
   
   /**
   * Test for the special condition that
   * that high and low are the same value.
   */
   @Test
   public void rangeDupTest() {
      int[] expected = {7};
      Assert.assertArrayEquals("Same high low test.",
             Selector.range(a2, 7, 7), expected);
   }
   
   /**
   * Test for null case.
   */
   @Test (expected = IllegalArgumentException.class)
   public void ceilingNullTest() {
      Selector.ceiling(null, 2);
   }
   
   /**
   * Test for array zero in range
   */
   @Test (expected = IllegalArgumentException.class)
   public void ceilingZeroTest() {
      Selector.ceiling(a4, 2);
   }
}
