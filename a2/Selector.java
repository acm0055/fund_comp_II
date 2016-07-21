import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
* Defines a library of selection methods
* on Collections.
*
* @author   Dean Hendrix (dh@auburn.edu)
* @author   Chase Minor (acm0055@auburn.edu)
* @version  2015-09-07
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
 * Selects the minimum value from the Collection c, as defined by
 * the supplied Comparator comp. This method throws an IllegalArgumentException
 * if either c or comp is null, and it throws a NoSuchElementException
 * if c is empty. The Collection c is not changed by this method.
 *
 * @param c     the Collection to be searched through
 * @param comp  the Comparator that defines the ordering of the elements in c
 * @return      minimum value in c
 *
 */
   public static <T> T min(Collection<T> c, Comparator<T> comp) {
      if (c == null || comp == null) {
         throw new IllegalArgumentException();
      }
      if (c.isEmpty()) {
         throw new NoSuchElementException();
      }
      
      Iterator<T> itr = c.iterator();
      //Guarenteed at least one element
      T min = itr.next();
      T currentElement = null;
      while (itr.hasNext()) {
         currentElement = itr.next();
         //in case of equivalence keeps current min
         min = (comp.compare(min, currentElement) <= 0) ? min : currentElement;
      }
      return min;
   }


/**
 * Selects the maximum value from the Collection c, as defined by
 * the supplied Comparator comp. This method throws an IllegalArgumentException
 * if either c or comp is null, and it throws a NoSuchElementException
 * if c is empty. The Collection c is not changed by this method.
 *
 * @param c     the Collection to be searched through
 * @param comp  the Comparator that defines the ordering of the elements in c
 * @return      maximum value in c
 *
 */
   public static <T> T max(Collection<T> c, Comparator<T> comp) {
      if (c == null || comp == null) {
         throw new IllegalArgumentException();
      }
      if (c.isEmpty()) {
         throw new NoSuchElementException();
      }
     
      Iterator<T> itr = c.iterator();
      //Guarenteed one element by above checks
      T max = itr.next();
      T currentElement = null;
      while (itr.hasNext()) {
         currentElement = itr.next();
         //in case of equivalence keeps current max
         max = (comp.compare(max, currentElement) >= 0) ? max : currentElement;
      }
      return max;
   }


/**
 * Selects the kth minimum value from the Collection c, as defined by
 * the supplied Comparator comp. This method throws an IllegalArgumentException
 * if either c or comp is null, and it throws a NoSuchElementException
 * if either c is empty or if there is no kth minimum value. Note that there
 * is no kth minimum value if k < 1, k > c.size(), or if k is larger than
 * the number of distinct values in c. The Collection c is not changed by
 * this method.
 *
 * @param c     the Collection to be searched through
 * @param k     the k-selection value
 * @param comp  the Comparator that defines the ordering of the elements in c
 * @return      kth minimum value in c
 *
 */
   public static <T> T kmin(Collection<T> c, int k, Comparator<T> comp) {
      if (c == null || comp == null) {
         throw new IllegalArgumentException();
      }
      //Less obviously checks for empty collection
      //because if the
      //collection is empty its size is zero,
      //and k must be greater than the size
      //and cannot be less than one.
      if (k > c.size() || k < 1) {
         throw new NoSuchElementException();
      }
      
      //List to be operated on
      ArrayList<T> op = new ArrayList<T>(c);
      
      java.util.Collections.<T>sort(op, comp);
      
      Iterator<T> itr = op.iterator();
      //Guarenteed one element by above checks
      T kmin = itr.next();
      T currentElement = null;
      int kth = 1; //Start at one since first min known
      while (itr.hasNext() && kth < k) {
         currentElement = itr.next();
         //sorted so guarenteed less than relationship
         if (comp.compare(kmin, currentElement) < 0) {
            kth++;
            kmin = currentElement;
         }
      }
      
      if (kth < k) {
         throw new NoSuchElementException();
      }
      
      return kmin;
   }


/**
 * Selects the kth minimum value from the Collection c, as defined by
 * the supplied Comparator comp. This method throws an IllegalArgumentException
 * if either c or comp is null, and it throws a NoSuchElementException
 * if either c is empty or if there is no kth minimum value. Note that there
 * is no kth minimum value if k < 1, k > c.size(), or if k is larger than
 * the number of distinct values in c. The Collection c is not changed by
 * this method.
 *
 * @param c     the Collection to be searched through
 * @param k     the k-selection value
 * @param comp  the Comparator that defines the ordering of the elements in c
 * @return      kth minimum value in c
 *
 */
   public static <T> T kmax(Collection<T> c, int k, Comparator<T> comp) {
      if (c == null || comp == null) {
         throw new IllegalArgumentException();
      }
      //Less obviously checks for empty collection
      //because if the
      //collection is empty its size is zero,
      //and k must be greater than the size
      //and cannot be less than one.
      if (k > c.size() || k < 1) {
         throw new NoSuchElementException();
      }
      
      //List to be operated on
      ArrayList<T> op = new ArrayList<T>(c);
      
      //Sort ArrayList in reverse order for iterating through
      //IE max -> min, 1st max = element 1 : 2nd max iterate
      //through till the next unique element, etc.
      Comparator<T> reverseComp = java.util.Collections.reverseOrder(comp);
      java.util.Collections.<T>sort(op, reverseComp);
      
      Iterator<T> itr = op.iterator();
      //Guarenteed one element by above checks
      T kmax = itr.next();
      T currentElement = null;
      int kth = 1; //Start at one since first max known
      while (itr.hasNext() && kth < k) {
         currentElement = itr.next();
         //sorted so guarenteed more than relationship
         //max -> min
         if (comp.compare(kmax, currentElement) > 0) {
            kth++;
            kmax = currentElement;
         }
      }
      
      if (kth < k) {
         throw new NoSuchElementException();
      }
      
      return kmax;
   }


/**
 * Returns a Collection containing all the values in the supplied
 * Collection c that are in the range [low..high]; that is, all the
 * values that are greater than or equal to low and less than or
 * equal to high, including duplicate values, as determined by the
 * supplied Comparator comp. The returned Collection contains only
 * values in the range [low..high], and no others. Note that low and
 * high do not have to be actual values in c. If there are no
 * qualifying values, including the case where c is empty, this method
 * throws a NoSuchElementException. This method throws an
 * IllegalArgumentException if either c or comp is null. The Collection c
 * is not changed by this method.
 *
 * @param c     the Collection to be searched through
 * @param low   the lower bound value of the range
 * @param high  the upper bound value of the range
 * @param comp  the Comparator that defines the ordering of the elements in c
 * @return      a Collection of elements in the range [low..high]
 *
 */
   public static <T> Collection<T> range(Collection<T> c, T low, T high,
                                         Comparator<T> comp) {
      if (c == null || comp == null) {
         throw new IllegalArgumentException();
      }
      if (c.isEmpty()) {
         throw new NoSuchElementException();
      }
      
      Iterator<T> itr = c.iterator();
      T currentElement = null;
      ArrayList<T> range = new ArrayList<T>();
      while (itr.hasNext()) {
         currentElement = itr.next();
         if (comp.compare(currentElement, low) >= 0 
             && comp.compare(currentElement, high) <= 0) {
            range.add(currentElement);
         }
      }
      
      if (range.isEmpty()) {
         throw new NoSuchElementException();
      }
      
      return range;
   }


/**
 * Returns the smallest value in the Collection c that is greater than
 * or equal to the given key, as defined by the supplied Comparator
 * comp. This method throws an IllegalArgumentException if either c
 * or comp is null, and throws a NoSuchElementException if c is empty
 * or if there is no qualifying value. Note that key does not have to
 * be an actual value in c.
 *
 * @param c     the Collection to be searched through
 * @param key   the reference value
 * @param comp  the Comparator that defines the ordering of the elements in c
 * @return      the next greater value in c
 *
 */
   public static <T> T ceiling(Collection<T> c, T key, Comparator<T> comp) {
      if (c == null || comp == null) {
         throw new IllegalArgumentException();
      }
      if (c.isEmpty()) {
         throw new NoSuchElementException();
      }
      
      T ceiling = null;
      T currentValue = null;
      Iterator<T> itr = c.iterator();
      
      while (itr.hasNext()) {
         currentValue = itr.next();
         if (comp.compare(currentValue, key) >= 0) {
            if (ceiling == null) {
               ceiling = currentValue;
            }
            else if (comp.compare(currentValue, ceiling) < 0) {
               ceiling = currentValue;
            }
         }
      }
      
      if (ceiling == null) {
         throw new NoSuchElementException();
      }
      
      return ceiling;
   }


/**
 * Returns the largest value in the Collection c that is less than
 * or equal to the given key, as defined by the supplied Comparator
 * comp. This method throws an IllegalArgumentException if either c
 * or comp is null, and throws a NoSuchElementException if c is empty
 * or if there is no qualifying value. Note that key does not have to
 * be an actual value in c.
 *
 * @param c     the Collection to be searched through
 * @param key   the reference value
 * @param comp  the Comparator that defines the ordering of the elements in c
 * @return      the next smaller value in c
 *
 */
   public static <T> T floor(Collection<T> c, T key, Comparator<T> comp) {
      if (c == null || comp == null) {
         throw new IllegalArgumentException();
      }
      if (c.isEmpty()) {
         throw new NoSuchElementException();
      }
      
      T floor = null;
      T currentValue = null;
      Iterator<T> itr = c.iterator();
      
      while (itr.hasNext()) {
         currentValue = itr.next();
         if (comp.compare(currentValue, key) <= 0) {
            if (floor == null) {
               floor = currentValue;
            }
            else if (comp.compare(currentValue, floor) > 0) {
               floor = currentValue;
            }
         }
      }
      
      if (floor == null) {
         throw new NoSuchElementException();
      }
      
      return floor;
   }

}
