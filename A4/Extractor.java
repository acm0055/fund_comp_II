import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

/**
 * Extractor.java. Implements feature extraction for collinear points in
 * two dimensional data.
 *
 * @author  Dean Hendrix (dh@auburn.edu) && Austin Minor (acm0055@auburn.edu)
 * @version 2015-10-8
 *
 */
public class Extractor {
   
  /** resolution settings for stdlib drawing. */
   private static int HI_RES = 32768;
   private static int LO_RES = 32;
   
   private Point[] points;
     
   /**
    * Builds an extractor based on the points in the
    * file named by filename. 
    */
   public Extractor(String filename) {
      Scanner fileIn = null;
      try {
         fileIn = new Scanner(new File(filename));
      }
      catch (IOException e) {
         System.exit(1);
      }
      
      int length = Integer.parseInt(fileIn.nextLine());
      points = new Point[length];
      
      int i = 0;
      Scanner strParse = null;
      while (i < length) {
         strParse = new Scanner(fileIn.nextLine());
         points[i] = (new Point(Integer.parseInt(strParse.next()),
                      Integer.parseInt(strParse.next())));
         i++;
      }
   }
  
   /**
    * Builds an extractor based on the points in the
    * Collection named by pcoll. 
    */
   public Extractor(Collection<Point> pcoll) {
      points = pcoll.<Point>toArray(new Point[0]);
   }
  
   /**
    * Returns a sorted set of all line segments of exactly four
    * collinear points. Uses a brute-force combinatorial
    * strategy. Returns an empty set if there are no qualifying
    * line segments.
    */
   public SortedSet<Line> getLinesBrute() {
      if (points.length < 4) {
         return null;
      }
      
      SortedSet<Line> lines = new TreeSet<Line>();
      Line line = null;
      int count = 0;
      for (int i = 0; i < points.length; i++) {
         for (int k = count; k < points.length; k++) {
            for (int j = count + 1; j < points.length; j++) {
               for (int l = count + 2; l < points.length; l++) {
                  line = new Line();
                  line.add(points[i]);
                  line.add(points[k]);
                  line.add(points[j]);
                  line.add(points[l]);
                  if (line.length() > 3) {
                     lines.add(line);
                  }
               }
            }
         }
         count++;
      }
      return lines;
   }
  
   /**
    * Returns a sorted set of all line segments of at least four
    * collinear points. The line segments are maximal; that is,
    * no sub-segments are identified separately. A sort-and-scan
    * strategy is used. Returns an empty set if there are no qualifying
    * line segments.
    */
   public SortedSet<Line> getLinesFast() {
      SortedSet<Line> lines = new TreeSet<Line>();
      Point[] sortedTemp = Arrays.<Point>copyOf(points, points.length);
      Line l = new Line();
      int prevLen = 1;
               
      for (Point p1: points) {
         if (l.length() > 3) {
            //wrap around error due to itrs
            lines.add(l);
         }
         Arrays.sort(sortedTemp, p1.SLOPE_ORDER);
         double slopeCurrent = 0.0;
         double slopePrevious = 1.0;
         l = new Line();
         for (Point p2: sortedTemp) {
            slopeCurrent = p1.slopeTo(p2);
            if (slopeCurrent == slopePrevious) {
               l.add(p2);
            }
            else {
               if (l.length() > 3) {
                  lines.add(l); 
               }
               l = new Line();
               l.add(p1);
               l.add(p2);
            }
            slopePrevious = slopeCurrent;
         }
      }
      
      return lines;
   }
  
  // Draw all points to a graphics window. 
   public void drawPoints() {
   // optional
      for(Point p: points) {
         p.draw();
      }
   }
  
  // Draw all identified lines, if any, to a graphics window. 
   public void drawLines() {
   // optional
      SortedSet<Line> ls = getLinesFast();
      for(Line l: ls) {
         l.first().drawTo(l.last());
      }
   }
}
