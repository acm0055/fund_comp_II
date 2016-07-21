/**
* Provides the interface for a generic
* game board for checkers, chess,
* word search, etc. Position
* is defined by the implementation.
* Parsing of a game board is defined
* by the implementation.
* @author Austin Minor
* @version 11-10-2015
*/
public interface GameBoard<T, U> {
   
   /**
   * Toggles the position mark.
   * Typed so different concepts of
   * marking maybe applied by inheriting classes.
   * 
   * @param pos position on marking board as
   *     defined by implenting class
   * @return previous mark value
   */
   U togglePosMark(int pos);
   
   /**
   * Returns true if the position has
   * a non-empty mark value.
   * 
   * @param pos position on marking board as
   *     defined by implenting class
   * @return true if positions has a non-empty
   *     mark value
   */
   boolean isMarked(int pos);
   
   /**
   * Returns the element at the
   * specified position.
   * 
   * @param pos position on marking board as
   *     defined by implenting class
   * @return element at specified position
   */
   T elementAtPos(int pos);
   
   /**
   * Clears the mark board
   * of all marks.
   */
   void clearMarks();
   
   /**
   * Changes the element at the
   * specified position with the
   * specified element.
   *
   * @param pos position on marking board as
   *     defined by implenting class
   * @param element element to swap the
   *     element at the specified position with
   * @return previous element
   */
   T changeElementAtPos(int pos, T element);
   
   /**
   * Returns true if the board
   * contains the specified value.
   * 
   * @param element element to search
   *     the board for
   * @return true if the board contains the
   *     specified value
   */
   boolean contains(T element);
}