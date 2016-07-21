import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class LinkedStrandTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }
   
   /** A test that always fails. **/
   @Test public void emptyConstructorTest() {
      LinkedStrand test = new LinkedStrand();
      Assert.assertEquals("Tests the empty constructor "
         + "for validity using the toString().", "", test.toString());
   }
   
   /** A test that always fails. **/
   @Test public void stringConstructorTest() {
      LinkedStrand test = new LinkedStrand("Hello");
      Assert.assertEquals("Tests the string constructor "
         + "for validity using the toString().", "Hello", test.toString());
   }
   
   /** A test that always fails. **/
   @Test public void stringInitializerTest() {
      LinkedStrand test = new LinkedStrand();
      test.initializeFrom("Hello");
      Assert.assertEquals("Tests the string initializer "
         + "for validity using the toString().", "Hello", test.toString());
   }
   
   /** A test that always fails. **/
   @Test public void cutWithEmptyDNATest() {
      LinkedStrand test = new LinkedStrand();
      try {
         DnaStrand result = test.cutWith("test");
      }
      catch (IllegalStateException e) {
         Assert.assertEquals("Tests the cutWith() with empty DNA strand "
            + "for validity using exception.", 1, 1);
         return;
      }
      Assert.assertEquals("Tests the cutWith() with empty DNA strand "
         + "for validity using exception.", 0, 1);
   }
   
   /** A test that always fails. **/
   @Test public void cutWithEmptyEnzymeTest() {
      LinkedStrand test = new LinkedStrand("Hello");
      DnaStrand result = test.cutWith("");
      Assert.assertEquals("Tests the cutWith() with empty string parameter "
         + "for validity using the toString().", "Hello", test.toString());
      Assert.assertEquals("Tests the cutWith() with empty DNA strand "
         + "for validity using the toString().", "", result.toString());
   }
   
   /** A test that always fails. **/
   @Test public void cutWithNullEnzymeTest() {
      LinkedStrand test = new LinkedStrand("Hello");
      DnaStrand result = test.cutWith(null);
      Assert.assertEquals("Tests the cutWith() with null string parameter "
         + "for validity using the toString().", "Hello", test.toString());
      Assert.assertEquals("Tests the cutWith() with empty DNA strand "
         + "for validity using the toString().", "", result.toString());
   }
   
   /** A test that always fails. **/
   @Test public void cutWithEnzymeNotInDNATest() {
      LinkedStrand test = new LinkedStrand("Hello");
      DnaStrand result = test.cutWith("test");
      Assert.assertEquals("Tests the cutWith() with enzyme not in DNA strand "
         + "for validity using the toString().", "Hello", test.toString());
      Assert.assertEquals("Tests the cutWith() with empty DNA strand "
         + "for validity using the toString().", "", result.toString());
   }
   
   /** A test that always fails. **/
   @Test public void cutWithEnzymeFirstInDNATest() {
      LinkedStrand test = new LinkedStrand("testHello");
      DnaStrand result = test.cutWith("test");
      Assert.assertEquals("Tests the cutWith() with enzyme first in DNA strand "
         + "for validity using the toString().", "", test.toString());
      Assert.assertEquals("Tests the cutWith() with empty DNA strand "
         + "for validity using the toString().", "Hello", result.toString());
   }
   
   /** A test that always fails. **/
   @Test public void cutWithEnzymeLastInDNATest() {
      LinkedStrand test = new LinkedStrand("Hellotest");
      DnaStrand result = test.cutWith("test");
      Assert.assertEquals("Tests the cutWith() with enzyme last in DNA strand "
         + "for validity using the toString().", "Hello", test.toString());
      Assert.assertEquals("Tests the cutWith() with empty DNA strand "
         + "for validity using the toString().", "", result.toString());
   }
   
   /** A test that always fails. **/
   @Test public void cutWithEnzymeWholeDNATest() {
      LinkedStrand test = new LinkedStrand("test");
      DnaStrand result = test.cutWith("test");
      Assert.assertEquals("Tests the cutWith() with enzyme the whole DNA strand "
         + "for validity using the toString().", "", test.toString());
      Assert.assertEquals("Tests the cutWith() with empty DNA strand "
         + "for validity using the toString().", "", result.toString());
   }
   
   /** A test that always fails. **/
   @Test public void cutWithEnzymeNormalTest() {
      LinkedStrand test = new LinkedStrand("Hetestllo");
      DnaStrand result = test.cutWith("test");
      Assert.assertEquals("Tests the cutWith() with enzyme in the middle of the DNA strand "
         + "for validity using the toString().", "He", test.toString());
      Assert.assertEquals("Tests the cutWith() with empty DNA strand "
         + "for validity using the toString().", "llo", result.toString());
   }
   
   /** A test that always fails. **/
   @Test public void cutWithSplitDnaTest() {
      LinkedStrand test = new LinkedStrand("Htestello");
      DnaStrand result = test.cutAndSplice("test", "tset");
      
      try {
         result.cutWith("test");
      }
      catch (IllegalStateException e) {
         Assert.assertEquals("Tests the cutAndSplice() with empty DNA strand "
            + "for validity using exception.", 1, 1);
         return;
      }
      Assert.assertEquals("Tests the cutAndSplice() with empty DNA strand "
         + "for validity using exception.", 0, 1);
   }
   
   /** A test that always fails. **/
   @Test public void spliceEmptyDNATest() {
      LinkedStrand test = new LinkedStrand();
      try {
         DnaStrand result = test.cutAndSplice("test", "tset");
      }
      catch (IllegalStateException e) {
         Assert.assertEquals("Tests the cutAndSplice() with empty DNA strand "
            + "for validity using exception.", 1, 1);
         return;
      }
      Assert.assertEquals("Tests the cutAndSplice() with empty DNA strand "
         + "for validity using exception.", 0, 1);
   }
   
   /** A test that always fails. **/
   @Test public void spliceEmptyEnzymeTest() {
      LinkedStrand test = new LinkedStrand("Hello");
      DnaStrand result = test.cutAndSplice("", "");
      Assert.assertEquals("Tests the cutAndSplice() with empty string parameter "
         + "for validity using the toString().", "Hello", test.toString());
      Assert.assertEquals("Tests the cutAndSplice() with empty DNA strand "
         + "for validity using the toString().", "", result.toString());
   }
   
   /** A test that always fails. **/
   @Test public void spliceEmptySpliceTest() {
      LinkedStrand test = new LinkedStrand("Hetestllo");
      DnaStrand result = test.cutAndSplice("test", "");
      Assert.assertEquals("Tests the cutAndSplice() with empty string parameter "
         + "for validity using the toString().", "Hetestllo", test.toString());
      Assert.assertEquals("Tests the cutAndSplice() with empty DNA strand "
         + "for validity using the toString().", "Hello", result.toString());
   }
   
   /** A test that always fails. **/
   @Test public void spliceNullEnzymeTest() {
      LinkedStrand test = new LinkedStrand("Hello");
      DnaStrand result = test.cutAndSplice(null, "tset");
      Assert.assertEquals("Tests the cutAndSplice() with null string parameter "
         + "for validity using the toString().", "Hello", test.toString());
      Assert.assertEquals("Tests the cutAndSplice() with empty DNA strand "
         + "for validity using the toString().", "", result.toString());
   }
   
   /** A test that always fails. **/
   @Test public void spliceNullSpliceTest() {
      LinkedStrand test = new LinkedStrand("Hello");
      DnaStrand result = test.cutAndSplice("test", null);
      Assert.assertEquals("Tests the cutAndSplice() with null string parameter "
         + "for validity using the toString().", "Hello", test.toString());
      Assert.assertEquals("Tests the cutAndSplice() with empty DNA strand "
         + "for validity using the toString().", "", result.toString());
   }
      
   /** A test that always fails. **/
   @Test public void spliceEnzymeNotInDNATest() {
      LinkedStrand test = new LinkedStrand("Hello");
      DnaStrand result = test.cutAndSplice("test", "tset");
      Assert.assertEquals("Tests the cutAndSplice() with enzyme not in DNA strand "
         + "for validity using the toString().", "Hello", test.toString());
      Assert.assertEquals("Tests the cutAndSplice() with empty DNA strand "
         + "for validity using the toString().", "", result.toString());
   }
   
   /** A test that always fails. **/
   @Test public void spliceEnzymeFirstInDNATest() {
      LinkedStrand test = new LinkedStrand("testHello");
      DnaStrand result = test.cutAndSplice("test", "tset");
      Assert.assertEquals("Tests the cutAndSplice() with enzyme first in DNA strand "
         + "for validity using the toString().", "testHello", test.toString());
      Assert.assertEquals("Tests the cutAndSplice() with empty DNA strand "
         + "for validity using the toString().", "tsetHello", result.toString());
   }
   
   /** A test that always fails. **/
   @Test public void spliceEnzymeLastInDNATest() {
      LinkedStrand test = new LinkedStrand("Hellotest");
      DnaStrand result = test.cutAndSplice("test", "tset");
      Assert.assertEquals("Tests the cutAndSplice() with enzyme last in DNA strand "
         + "for validity using the toString().", "Hellotest", test.toString());
      Assert.assertEquals("Tests the cutAndSplice() with empty DNA strand "
         + "for validity using the toString().", "Hellotset", result.toString());
   }
   
   /** A test that always fails. **/
   @Test public void spliceEnzymeWholeDNATest() {
      LinkedStrand test = new LinkedStrand("test");
      DnaStrand result = test.cutAndSplice("test", "tset");
      Assert.assertEquals("Tests the cutAndSplice() with enzyme the whole DNA strand "
         + "for validity using the toString().", "test", test.toString());
      Assert.assertEquals("Tests the cutAndSplice() with empty DNA strand "
         + "for validity using the toString().", "tset", result.toString());
   }
   
   /** A test that always fails. **/
   @Test public void spliceEnzymeNormalTest() {
      LinkedStrand test = new LinkedStrand("Hetestllo");
      DnaStrand result = test.cutAndSplice("test", "tset");
      Assert.assertEquals("Tests the cutAndSplice() with enzyme in the middle of the DNA strand "
         + "for validity using the toString().", "Hetestllo", test.toString());
      Assert.assertEquals("Tests the cutAndSplice() with empty DNA strand "
         + "for validity using the toString().", "Hetsetllo", result.toString());
   }
   
   /** A test that always fails. **/
   @Test public void spliceSplitDnaTest() {
      LinkedStrand test = new LinkedStrand("Htestello");
      DnaStrand result = test.cutAndSplice("test", "tset");
      
      try {
         result.cutAndSplice("tset", "test");
      }
      catch (IllegalStateException e) {
         Assert.assertEquals("Tests the cutWith() with empty DNA strand "
            + "for validity using exception.", 1, 1);
         return;
      }
      Assert.assertEquals("Tests the cutWith() with empty DNA strand "
         + "for validity using exception.", 0, 1);
   }
   
   /** A test that always fails. **/
   @Test public void spliceMultiFirstDnaTest() {
      LinkedStrand test = new LinkedStrand("testtesttesttestHello");
      DnaStrand result = test.cutAndSplice("test", "tset");
      Assert.assertEquals("Tests the cutAndSplice() with enzyme in the middle of the DNA strand "
         + "for validity using the toString().", "testtesttesttestHello", test.toString());
      Assert.assertEquals("Tests the cutAndSplice() with empty DNA strand "
         + "for validity using the toString().", "tsettsettsettsetHello", result.toString());
   }
   
   /** A test that always fails. **/
   @Test public void spliceMultiNormalDnaTest() {
      LinkedStrand test = new LinkedStrand("HellotesttesttesttestHello");
      DnaStrand result = test.cutAndSplice("test", "tset");
      Assert.assertEquals("Tests the cutAndSplice() with enzyme in the middle of the DNA strand "
         + "for validity using the toString().", "HellotesttesttesttestHello", test.toString());
      Assert.assertEquals("Tests the cutAndSplice() with empty DNA strand "
         + "for validity using the toString().", "HellotsettsettsettsetHello", result.toString());
   }
}
