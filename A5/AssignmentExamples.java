/**
 * AssignmentExamples.java
 * Generates the DnaStrand examples in the assignment handout.
 *
 * @author   Dean Hendrix <dh@auburn.edu>
 * @version  2015-10-12
 *
 */
public class AssignmentExamples {
   public static void main(String[] args) {
      DnaStrand dna = new ArrayStrand("AGTCGAATTCAAGTCGAATTCAGTCA");
      String enzyme = "GAATTC";
      String splice = "XXYY";
      System.out.println("DnaStrand method cutWith()");
      System.out.println("======================");
      System.out.println("Before: " + dna);
      DnaStrand ret = dna.cutWith(enzyme);
      System.out.println("After:  " + dna);
      System.out.println("Return: " + ret);
   	
      System.out.println();
   
      dna = new ArrayStrand("AGTCGAATTCAAGTCGAATTCAGTCA");
      enzyme = "GAATTC";
      splice = "XXYY";
      System.out.println("DnaStrand method cutAndSplice()");
      System.out.println("======================");
      System.out.println("Before: " + dna);
      ret = dna.cutAndSplice(enzyme, splice);
      System.out.println("After:  " + dna);
      System.out.println("Return: " + ret);
      System.out.println();
   }
}
