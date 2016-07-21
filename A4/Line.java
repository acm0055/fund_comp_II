import java.util.Collection;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Line.java
 * Models a line segment as a Set of Points.
 *
 * @author   Dean Hendrix (dh@auburn.edu) && Austin Minor (acm0055@auburn.edu)
 * @version  2015-10-8
 *
 */
public class Line implements Comparable<Line>, Iterable<Point> {
 
   SortedSet<Point> line;
   
   /** Creates a new line with no points. */
   public Line() {
      line = new TreeSet<Point>();
   }
   
   /** Creates a new line with all distinct collinear points in c. */
   public Line(Collection<Point> c) {
      line = new TreeSet<Point>(c);
   }
 
   /** Adds p to this line if distinct and collinear with current points. */
   public boolean add(Point p) {
      if (p == null) {
         return false;
      }
      
      if (this.length() > 1) {
      //check for collinearity
         if (p.slopeTo(this.first()) == p.slopeTo(this.last())) {
            return line.add(p);
         }
         else {
            return false;
         }
      }
      //checks for duplicates based on the nature
      //of a set
      return line.add(p);
   }
   
   /** Returns the first (minimum) point on this line. */
   public Point first() {
      return line.first();
   }
   
   /** Returns the last (maximum) point on this line. */
   public Point last() {
      return line.last();
   }
   
   /** Returns the number of points on this line. */
   public int length() {
      return line.size();
   }
    
  // compare this point to that point
   @Override
   public int compareTo(Line that) {
      if (this.first().compareTo(that.first()) < 0) {
         return -1;
      }
      if (this.first().compareTo(that.first()) > 0) {
         return 1;
      }
      if (this.first().compareTo(that.first()) == 0) {
         if (this.last().compareTo(that.last()) < 0) {
            return -1;
         }
         if (this.last().compareTo(that.last()) > 0) {
            return 1;
         }
      }
      return 0;
   }
   
  // provide an iterator over all the points on this line
   @Override
   public Iterator<Point> iterator() {
      return line.iterator();
   }
   
   /** 
    * Return true if this point's x and y coordinates are
    * the same as that point's x and y coordinates.
    * Return false otherwise.
    */
   @Override 
   public boolean equals(Object obj) {
      if (obj == this) {
         return true;
      }
      if (obj == null) {
         return false;
      }
      if (!(obj instanceof Line)) {
         return false;
      }
      Line that = (Line) obj;
      return (this.first().equals(that.first())) && (this.last().equals(that.last()));
   }
 
  // return this line as a String
   @Override
   // DO NOT MODIFY
   public String toString() {
      StringBuilder s = new StringBuilder();
      for (Point p : line) {
         s.append(p + " -> ");
      }
      s = s.delete(s.length() - 4, s.length());
      return s.toString();
   }
 
}
