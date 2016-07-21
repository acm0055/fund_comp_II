import java.util.ArrayList;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;

/**
 * Calculates the time complexity of an
 * unknown funtction f(N). The time
 * complexity is guarenteed to be of
 * polynomial nature. Thus R = T(f(2N)/T(f(N))
 * is proportional to 2^k thus log_2(R) = k.
 *
 * @author Austin Minor and Ryan Santa Ana
 */

public class TimeComplexity {
   private static final double NANO_SEC_TO_SEC = 1000000000d;
   private static final int bound = 6;

   public static void timeComplexityTest() throws IOException {
      double ratio = 0.0;
      int N = 0;
      ArrayList<Double> runningTimes
         = new ArrayList<Double>();
      String output = "";
      TimingLab t0 = new TimingLab(40);
      FileWriter fileOut = new FileWriter(new File("results.txt"));
   
      int count = 0;
      while (count < bound) {
         //returns the relative double of N
         //ie 2N - 4N - 8N
         N = (int) Math.pow(2.0, count);
      
         runningTimes.add(timeMethod(N, t0));
      
         if (runningTimes.size() > 1) {
            ratio = (Math.log10(runningTimes.get(runningTimes.size() - 1)
               / runningTimes.get(runningTimes.size() - 2))
               / Math.log10(2));
         }
      
         count++;
         if (runningTimes.size() > 1) {
            output = N + "," + runningTimes.get(runningTimes.size() - 1)
                + "," + ratio;
         }
         else
            output = N + "," + runningTimes.get(runningTimes.size() - 1)
                     + "," + "-";
      
         fileOut.append(output);
         System.out.println(output); 
         output = "";
      }
      fileOut.close();
   }

   /**
    * Runs timing method with 2^t (exponential)
    * N sizes. Ex) 2^0 = 1N; 2^1 = 2N; 2^2 = 4N ...
    */
   private static double timeMethod(int N, TimingLab t0) {
      //to-do
      long time = System.nanoTime();
      t0.timeTrial(N);
      return (System.nanoTime() - time) / (double) NANO_SEC_TO_SEC;
   }
}
