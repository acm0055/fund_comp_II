/**
 * SortingLabClient.java. 
 * Provides a simple example client of SortingLab.java.
 *
 * NOTE: The generic type of SortingLab must be bound
 *       to a Comparable type. The sorting methods in
 *       SortingLab use the natural ordering of the
 *       elements in the parameter array.
 *
 * @author    Dean Hendrix (dh@auburn.edu)
 * @version   2015-09-11
 *
 */
public final class SortingLabClient {

  /** Drives execution. */
   public static void main(String[] args) {
      int key = 99;
      // run one sort on an array of Strings
      String[] as = {"D", "B", "E", "C", "A"};
      SortingLab<String> sls = new SortingLab<String>(key);
      sls.sort1(as);
   
      // run a sort on multiple Integer arrays of increasing length
      SortingLab<Integer> sli = new SortingLab<Integer>(key);
      int M = 2000000; // max capacity for array
      int N = 10000;   // initial size of array
      double start;
      double elapsedTime;
      for (; N < M; N *= 2) {
         Integer[] ai = getIntegerArray(N, Integer.MAX_VALUE);
         start = System.nanoTime();
         sli.sort2(ai);
         elapsedTime = (System.nanoTime() - start) / 1000000000d;
         System.out.print(N + "\t");
         System.out.printf("%4.3f\n", elapsedTime);
      }
   }

  /** 
   * Returns an array of size N filled with Integer values
   * in the range 0 .. max - 1.
   */
   private static Integer[] getIntegerArray(int N, int max) {
      Integer[] a = new Integer[N];
      java.util.Random rng = new java.util.Random();
      for (int i = 0; i < N; i++) {
         a[i] = rng.nextInt(max);
      }
      return a;
   }

}
