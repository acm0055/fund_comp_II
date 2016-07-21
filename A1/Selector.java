import java.util.Arrays;

/**
* Defines a library of selection methods
* on arrays of ints.
*
* @author   Austin Minor (acm0055@auburn.edu)
* @author   Dean Hendrix (dh@auburn.edu)
* @version  2015-08-22
*
*/
public final class Selector {

/**
 * Can't instantiate this class.
 *
 * D O   N O T   C H A N G E   T H I S   C O N S T R U C T O R
 *
 */
   private Selector() { }


/**
 * Selects the minimum value from the array a. This method
 * throws IllegalArgumentException if a is null or has zero
 * length. The array a is not changed by this method.
 */
   public static int min(int[] a) {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
      
      int min = a[0]; //guarenteeded to have one item
      
      for (int i = 0; i < a.length; i++) {
         min = (a[i] < min) ? a[i] : min;
      }
      
      return min;
   }


/**
 * Selects the maximum value from the array a. This method
 * throws IllegalArgumentException if a is null or has zero
 * length. The array a is not changed by this method.
 */
   public static int max(int[] a) {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
      
      int max = a[0]; //guarenteeded to have one item
      
      for (int i = 0; i < a.length; i++) {
         max = (a[i] > max) ? a[i] : max;
      }
      
      return max;
   }


/**
 * Selects the kth minimum value from the array a. This method
 * throws IllegalArgumentException if a is null, has zero length,
 * or if there is no kth minimum value. Note that there is no kth
 * minimum value if k < 1, k > a.length, or if k is larger than
 * the number of distinct values in the array. The array a is not
 * changed by this method.
 */
   public static int kmin(int[] a, int k) {
     
     //Check for base cases that are
     //invalid by nature of the problem.
      if (a == null) {
         throw new IllegalArgumentException();
      }
      if (a.length == 0) {
         throw new IllegalArgumentException();
      }
      if (k <= 0) {
         throw new IllegalArgumentException();
      }
      if (k > a.length) {
         throw new IllegalArgumentException();
      }
      
      int countK = 1; //kth item counter
      int[] b = Arrays.copyOf(a, a.length);
      Arrays.sort(b); //min to max sort
      int kmin = b[0]; //guarenteed an item by above checks
      
      int i = 1; //counter
      while (i < b.length && countK != k) {
         if (b[i] != kmin) {
            kmin = b[i];
            countK++;
         }
         i++;
      }
      
      
      //never found kth min
      if (countK < k) {
         throw new IllegalArgumentException();
      }
      
      //Undo above shift to prevent out of bounds error
      kmin = b[i - 1];
      
      return kmin;
   }


/**
 * Selects the kth maximum value from the array a. This method
 * throws IllegalArgumentException if a is null, has zero length,
 * or if there is no kth maximum value. Note that there is no kth
 * maximum value if k < 1, k > a.length, or if k is larger than
 * the number of distinct values in the array. The array a is not
 * changed by this method.
 */
   public static int kmax(int[] a, int k) {
   
     //Check for base cases that are
     //invalid by nature of the problem.
      if (a == null) {
         throw new IllegalArgumentException();
      }
      if (a.length == 0) {
         throw new IllegalArgumentException();
      }
      if (k <= 0) {
         throw new IllegalArgumentException();
      }
      if (k > a.length) {
         throw new IllegalArgumentException();
      }
      
      int countK = 1; //kth item counter
      int[] b = Arrays.copyOf(a, a.length);
      Arrays.sort(b); //min to max sort
      int kmax = b[b.length - 1]; //guarenteed an item by above checks
      
      //Had to shift one to get right kth element
      //because of trival solution
      int i = b.length - 2; //counter
      while (i >= 0 && countK != k) {
         if (b[i] != kmax) {
            kmax = b[i];
            countK++;
         }
         i--;
      }
      
      //never found kth min
      if (countK < k) {
         throw new IllegalArgumentException();
      }
      
      //Undo above shift to prevent out of bounds error
      kmax = b[i + 1];
      
      return kmax;
   }


/**
 * Returns an array containing all the values in a in the
 * range [low..high]; that is, all the values that are greater
 * than or equal to low and less than or equal to high,
 * including duplicate values. The length of the returned array
 * is the same as the number of values in the range [low..high].
 * If there are no qualifying values, this method returns a
 * zero-length array. Note that low and high do not have
 * to be actual values in a. This method throws an
 * IllegalArgumentException if a is null or has zero length.
 * The array a is not changed by this method.
 */
   public static int[] range(int[] a, int low, int high) {
      if (a == null) {
         throw new IllegalArgumentException();
      }
      if (a.length == 0) {
         throw new IllegalArgumentException();
      }
      
      int[] b = new int[a.length];
      int elementCount = 0;
      
      for (int i = 0; i < a.length; i++) {
         if (a[i] >= low && a[i] <= high) {
            b[elementCount] = a[i];
            elementCount++; 
         }
      }
      
      int[] c = new int[elementCount];
      for (int i = 0; i < elementCount; i++) {
         c[i] = b[i];
      }
      
      return c;
   }


/**
 * Returns the smallest value in a that is greater than or equal to
 * the given key. This method throws an IllegalArgumentException if
 * a is null or has zero length, or if there is no qualifying
 * value. Note that key does not have to be an actual value in a.
 * The array a is not changed by this method.
 */
   public static int ceiling(int[] a, int key) {
      if (a == null) {
         throw new IllegalArgumentException();
      }
      if (a.length == 0) {
         throw new IllegalArgumentException();
      }
      
      //possible ceilings may be all of a
      int[] possCeilings = new int[a.length];
      int ceilingCount = 0; //zero initial ceilings
      int ceiling;
      
      
      for (int i = 0; i < a.length; i++) {
         if (a[i] >= key) {
            //store possible ceiling
            possCeilings[ceilingCount] = a[i];
            ceilingCount++;
         }
      }
      
      if (ceilingCount > 0) {
         //set trival solution
         ceiling = possCeilings[0];
         //test for non-trival solutions
         for (int i = 1; i < ceilingCount; i++) {
            ceiling = ceiling < possCeilings[i]
                   ? ceiling : possCeilings[i];
         }
      }
      //if no ceilings were found
      else {
         throw new IllegalArgumentException();
      }
      
      return ceiling;
   }


/**
 * Returns the largest value in a that is less than or equal to
 * the given key. This method throws an IllegalArgumentException if
 * a is null or has zero length, or if there is no qualifying
 * value. Note that key does not have to be an actual value in a.
 * The array a is not changed by this method.
 */
   public static int floor(int[] a, int key) {
      if (a == null) {
         throw new IllegalArgumentException();
      }
      if (a.length == 0) {
         throw new IllegalArgumentException();
      }
      
      //possible floors may be all of a
      int[] possFloors = new int[a.length];
      int floorCount = 0; //zero inital floors
      int floor;
      
      
      for (int i = 0; i < a.length; i++) {
         if (a[i] <= key) {
            //store possible floor
            possFloors[floorCount] = a[i];
            floorCount++;
         }
      }
      
      if (floorCount > 0) {
         //set trival solution
         floor = possFloors[0];
         //test for non-trival solutions
         for (int i = 1; i < floorCount; i++) {
            floor = floor > possFloors[i]
                   ? floor : possFloors[i];
         }
      }
      //if no floors were found
      else {
         throw new IllegalArgumentException();
      }
      
      return floor;
   }

}
