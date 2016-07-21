import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Iterator;
import java.util.Vector;
import java.text.DecimalFormat;

/**
 * Performs various deterministic operations
 * to gather data to determine the nature
 * of a sorting algorithm.
 *
 * @author Austin Minor and Ryan Santa Ana
 */

public class SortingAlg {
   
   public static final int ARRAY_SIZE = 10000;
   /**
   * Bound for how many times array should
   * be doubled and timed.
   */
   public static final int BOUND = 3;
   public static final double NANO_SEC_TO_SEC = 1000000000d;
   
   public static void sortingTest() {
      SortingLab<RelativeInt> sortingLab =
         new SortingLab<RelativeInt>(40);
      String output = "";
      
      output += stableTest(sortingLab);
      output += timeValues(sortingLab);
   }
   /**
    * Tests the stability of the algorithm
    * by using a relative position type
    * in a random sorted array to test
    * if the relative positions of equivalent
    * items change and are thus non-stable
    */
   private static String stableTest(SortingLab<RelativeInt> sortingLab) {
      String output = "";
      
      output += "Sort 1 is stable: ";
      RelativeInt[] sortedArray = rngArray(1); 
      sortingLab.sort1(sortedArray);
      output += isStable(sortedArray) + "\n";
      
      output += "Sort 2 is stable: ";
      sortedArray = rngArray(1); 
      sortingLab.sort2(sortedArray);
      output += isStable(sortedArray) + "\n";
      
      output += "Sort 3 is stable: ";
      sortedArray = rngArray(1); 
      sortingLab.sort3(sortedArray);
      output += isStable(sortedArray) + "\n";
      
      output += "Sort 4 is stable: ";
      sortedArray = rngArray(1); 
      sortingLab.sort4(sortedArray);
      output += isStable(sortedArray) + "\n";
      
      output += "Sort 5 is stable: ";
      sortedArray = rngArray(1); 
      sortingLab.sort5(sortedArray);
      output += isStable(sortedArray) + "\n";
      System.out.print(output);
      
      return output; 
   }
   
   private static boolean isStable(RelativeInt[] sortedArray) {
      boolean stable = true; 
      if (sortedArray.length % 2 != 0) {
         throw new IllegalArgumentException();
      }
   
      int count = 0;
      while (count < sortedArray.length && stable) {
         //guarenteed two iterators becuase array has an even number of elements
         if (sortedArray[count].getRelativePos() > sortedArray[count + 1].getRelativePos()) {
            stable = false;
         }
         
         count += 2;
      }
   
      return stable;
   }
   
   private static RelativeInt[] rngArray(int ratio) {
      RelativeInt[] relativeInts = new RelativeInt[ratio * ARRAY_SIZE];
      
      //Create an array of array indices
      Vector<Integer> ether = new Vector<Integer>();
      for (int i = 0; i < ratio * ARRAY_SIZE; i++) {
         ether.add(i);
      }
   
      Random rng = new Random();
      int i0 = 0;
      int i1 = 0;
      int count = 0;
      while (ether.size() > 0) {
         i0 = ether.remove(rng.nextInt((ether.size())));
         i1 = ether.remove(rng.nextInt((ether.size())));
         if (i0 < i1) {
            relativeInts[i0] = new RelativeInt(count,0); 
            relativeInts[i1] = new RelativeInt(count,1);
         }
         else {
            relativeInts[i0] = new RelativeInt(count,1); 
            relativeInts[i1] = new RelativeInt(count,0);
         }
         count++;
      }
      return relativeInts;
   }
   
   private static RelativeInt[] sortedArray(int ratio) {
      RelativeInt[] relativeInts = new RelativeInt[ratio * ARRAY_SIZE];
      
      for (int i = 0; i < (ratio * ARRAY_SIZE); i++) {
         //Relative position does not matter
         relativeInts[i] = new RelativeInt(i,0);
      }
      
      return relativeInts;
   }
   
   private static RelativeInt[] reverseSortedArray(int ratio) {
      RelativeInt[] relativeInts = new RelativeInt[ratio * ARRAY_SIZE];
      
      int i0 = 0;
      for (int i1 = ((ratio * ARRAY_SIZE) - 1); i1 >= 0; i1--) {
         //Relative position does not matter
         relativeInts[i0] = new RelativeInt(i1,0);
         i0++;
      }
      
      return relativeInts;
   }
   
   private static String timeValues(SortingLab<RelativeInt> sortingLab) {
      String output = "";
      RelativeInt[] relativeInts = null;
      DecimalFormat decFmt = new DecimalFormat(",##0.00000");
      long startTime = 0L;
      double time = 0.0;
     
      int i = 0;
      int ratio = 1;
      output += "Sort 1 random arrays ";
      while(i < BOUND) {
         ratio = (int) Math.pow(2, i);
         output += "size " + (ratio * ARRAY_SIZE);
         relativeInts = rngArray(ratio);
         startTime = System.nanoTime();
         sortingLab.sort1(relativeInts);
         time = (System.nanoTime() - startTime)/NANO_SEC_TO_SEC;
         output += " time " + decFmt.format(time) + " ";
         i++;
      }
      output += "\n";
      
      i = 0;
      ratio = 1;
      output += "Sort 2 random arrays ";
      while(i < BOUND) {
         ratio = (int) Math.pow(2, i);
         output += "size " + (ratio * ARRAY_SIZE);
         relativeInts = rngArray(ratio);
         startTime = System.nanoTime();
         sortingLab.sort2(relativeInts);
         time = (System.nanoTime() - startTime)/NANO_SEC_TO_SEC;
         output += " time " + decFmt.format(time) + " ";
         i++;
      }
      output += "\n";
      
      i = 0;
      ratio = 1;
      output += "Sort 3 random arrays ";
      while(i < BOUND) {
         ratio = (int) Math.pow(2, i);
         output += "size " + (ratio * ARRAY_SIZE);
         relativeInts = rngArray(ratio);
         startTime = System.nanoTime();
         sortingLab.sort3(relativeInts);
         time = (System.nanoTime() - startTime)/NANO_SEC_TO_SEC;
         output += " time " + decFmt.format(time) + " ";
         i++;
      }
      output += "\n";
      
      i = 0;
      ratio = 1;
      output += "Sort 4 random arrays ";
      while(i < BOUND) {
         ratio = (int) Math.pow(2, i);
         output += "size " + (ratio * ARRAY_SIZE);
         relativeInts = rngArray(ratio);
         startTime = System.nanoTime();
         sortingLab.sort4(relativeInts);
         time = System.nanoTime();
         time -= startTime;
         time /= NANO_SEC_TO_SEC;
         output += " time " + decFmt.format(time) + " ";
         i++;
      }
      output += "\n";
      
      i = 0;
      ratio = 1;
      output += "Sort 5 random arrays ";
      while(i < BOUND) {
         ratio = (int) Math.pow(2, i);
         output += "size " + (ratio * ARRAY_SIZE);
         relativeInts = rngArray(ratio);
         startTime = System.nanoTime();
         sortingLab.sort5(relativeInts);
         time = (System.nanoTime() - startTime)/NANO_SEC_TO_SEC;
         output += " time " + decFmt.format(time) + " ";
         i++;
      }
      output += "\n";
               
      i = 0;
      ratio = 1;
      output += "Sort 1 sorted arrays ";
      while(i < BOUND) {
         ratio = (int) Math.pow(2, i);
         output += "size " + (ratio * ARRAY_SIZE);
         relativeInts = sortedArray(ratio);
         startTime = System.nanoTime();
         sortingLab.sort1(relativeInts);
         time = (System.nanoTime() - startTime)/NANO_SEC_TO_SEC;
         output += " time " + decFmt.format(time) + " ";
         i++;
      }
      output += "\n";
      
      i = 0;
      ratio = 1;
      output += "Sort 2 sorted arrays ";
      while(i < BOUND) {
         ratio = (int) Math.pow(2, i);
         output += "size " + (ratio * ARRAY_SIZE);
         relativeInts = sortedArray(ratio);
         startTime = System.nanoTime();
         sortingLab.sort2(relativeInts);
         time = (System.nanoTime() - startTime)/NANO_SEC_TO_SEC;
         output += " time " + decFmt.format(time) + " ";
         i++;
      }
      output += "\n";
      
      i = 0;
      ratio = 1;
      output += "Sort 3 sorted arrays ";
      while(i < BOUND) {
         ratio = (int) Math.pow(2, i);
         output += "size " + (ratio * ARRAY_SIZE);
         relativeInts = sortedArray(ratio);
         startTime = System.nanoTime();
         sortingLab.sort3(relativeInts);
         time = (System.nanoTime() - startTime)/NANO_SEC_TO_SEC;
         output += " time " + decFmt.format(time) + " ";
         i++;
      }
      output += "\n";
      
      i = 0;
      ratio = 1;
      output += "Sort 4 sorted arrays ";
      while(i < BOUND) {
         ratio = (int) Math.pow(2, i);
         output += "size " + (ratio * ARRAY_SIZE);
         relativeInts = sortedArray(ratio);
         startTime = System.nanoTime();
         sortingLab.sort4(relativeInts);
         time = (System.nanoTime() - startTime)/NANO_SEC_TO_SEC;
         output += " time " + decFmt.format(time) + " ";
         i++;
      }
      output += "\n";
      
      i = 0;
      ratio = 1;
      output += "Sort 5 sorted arrays ";
      while(i < BOUND) {
         ratio = (int) Math.pow(2, i);
         output += "size " + (ratio * ARRAY_SIZE);
         relativeInts = sortedArray(ratio);
         startTime = System.nanoTime();
         sortingLab.sort5(relativeInts);
         time = (System.nanoTime() - startTime)/NANO_SEC_TO_SEC;
         output += " time " + decFmt.format(time) + " ";
         i++;
      }
      output += "\n";
      
      System.out.print(output);
     
      return "";
   }
}

class RelativeInt extends RelativePosType<Integer> {
   public RelativeInt(Integer objIn, int relativePosIn) {
      super(objIn, relativePosIn);
   }
}

class RelativePosType<T extends Comparable<T>> 
         implements Comparable<RelativePosType<T>> {

   //relative position to its neighbor if no neighbor -1
   private int relativePos = -1;
   private T object;

   public RelativePosType(T objIn, int relativePosIn) {
      this.relativePos = relativePosIn;
      this.object = objIn;
   }

   public int getRelativePos() {
      return this.relativePos;
   }

   public int compareTo(RelativePosType<T> objectIn) {
      return object.<T>compareTo(objectIn.object);
   }
}
