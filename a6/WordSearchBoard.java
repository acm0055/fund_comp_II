import java.util.LinkedList;
import java.util.List;

/**
* Defines a word search board. Position is defined
* as row/column left justified. Board is guarenteed
* to be square in nature (IE NxN). Provides the data
* storage and methods for manipulating that data storage
* for a word search board. All locations are defined
* as numbers 0 - N-1 (where N is the number of elements)
* such that the numbers start in the left upper corner and
* progess to the lower right corner by traversing the rows
* of the board.
* @author Austin Minor
* @version 11-10-2015
*/

public class WordSearchBoard implements GameBoard<String, Boolean> {
   
   private boolean[][] markBoard = null;
   private String[][] wordBoard = null;
   private int squareDimension;
   
   /**
   * Creates a valid word search board by parsing
   * a string array into a two dimension word board.
   * 
   * @param wordBoardIn string array to parse into word
   *     board
   */
   public WordSearchBoard(String[] wordBoardIn) {
      setBound(wordBoardIn);
      markBoard = new boolean[squareDimension][squareDimension];
      wordBoard = new String[squareDimension][squareDimension];
      int pos = 0;
      for (String s: wordBoardIn) {
         wordBoard[this.rowPos(pos)][this.colPos(pos)] = s.toUpperCase();
         pos++;
      }
   }
   
   /**
   * Returns the dimensions of the board.
   * 
   * @return dimensions of the board
   */
   public int[] getDimensions() {
      int[] dimensions = new int[2];
      dimensions[0] = squareDimension;
      dimensions[1] = squareDimension;
      return dimensions;
   }
   
   /**
   * Toggles the mark position on the word board
   * using a boolean board.
   * @return old mark value
   * @param pos board position defined by implementation
   */
   public Boolean togglePosMark(int pos) {
      boolean temp = markBoard[pos / squareDimension][pos % squareDimension];
      markBoard[this.rowPos(pos)][this.colPos(pos)] = !temp;
      return temp;
   }
   
   /**
   * Returns true if the position has
   * a non-empty mark value.
   * 
   * @param pos position on marking board as
   *     defined by implenting class
   * @return true if positions has a non-empty
   *     mark value
   */
   public boolean isMarked(int pos) {
      return markBoard[this.rowPos(pos)][this.colPos(pos)];
   }
   /**
   * Return the character at the given position.
   * @return new mark value
   * @param pos board position defined by implementation
   */
   public String elementAtPos(int pos) {
      return wordBoard[this.rowPos(pos)][this.colPos(pos)];
   }
   /**
   * Sets all marks on word board
   * back to their zero value.
   */
   public void clearMarks() {
      markBoard = new boolean[squareDimension][squareDimension];
   }
   /**
   * Change element at the position specified.
   * @return old element value
   * @param pos board position defined by implementation
   * @param element element to replace the element
   *     at the specified position
   */
   public String changeElementAtPos(int pos, String element) {
      String temp = wordBoard[this.rowPos(pos)][this.colPos(pos)];
      wordBoard[this.rowPos(pos)][this.colPos(pos)] = element;
      return temp;
   }
   
   /**
   * Conducts a depth first search to determine
   * if the board contains the given sequence.
   *
   * @param sequence string to search for on board
   * @return true if the specified string is found
   *     on the board
   */
   public boolean contains(String sequence) {
      return (depthFirstSearch(sequence).size() != 0);
   }
   
   /**
     * Conducts a depth first search to determine
   * the sequence of positions on the board for
   * the given string. Returns only the first
   * occurance on the string if it is found.
   * If the string is not found, returns an
   * empty list. This is the publicly
   * accessable method for depth first search.
   *
   * @param s string to search for on board
   * @return empty list if the string is not found
   *     on the board, list of positions if the
   *     string is found on the board
   */
   public List<Integer> locate(String s) {
      return depthFirstSearch(s);
   }
   
   /**
     * Conducts a depth first search to determine
   * the sequence of positions on the board for
   * the given string. Returns only the first
   * occurance on the string if it is found.
   * If the string is not found, returns an
   * empty list. Searchs the whole board for
   * answer.
   *
   * @param s string to search for on board
   * @return empty list if the string is not found
   *     on the board, list of positions if the
   *     string is found on the board
   */
   private List<Integer> depthFirstSearch(String sequence) {
      LinkedList<Integer> stack = new LinkedList<Integer>();
      LinkedList<Integer> wordPos = new LinkedList<Integer>();
      List<Integer> neighbors = null;
     //string pos counter
      int c = 0;
      int index = -1;
     //find starting pos
      for (int i = 0; i < squareDimension * squareDimension; i++) {
         if (sequence.toUpperCase().startsWith(this.elementAtPos(i))) {
            this.clearMarks();
            wordPos.clear();
            stack.clear();
            //depth-first search
            stack.push(i);
            togglePosMark(i);
            wordPos = (LinkedList<Integer>) 
                      dfsRecursive(stack, sequence.substring(
                      elementAtPos(i).length(), sequence.length())
                      .toUpperCase(), elementAtPos(i).length(), 
                      i, sequence.length());
            if (wordPos.size() != 0) {
               return wordPos;
            }
         }
      }
      //no result so return empty list
      return new LinkedList<Integer>();
   }
   
   /**
     * The main algorithm behind the depth first search.
     * Recurssively computes the solution and then returns
     * the list of positions or an empty list depeneding
     * on if the sequence is found.
   *
   * @param s string to search for on board
   * @return empty list if the string is not found
   *     on the board, list of positions if the
   *     string is found on the board
   */
   private List<Integer> dfsRecursive(LinkedList<Integer> list, 
      String seq, int length, int point, int requiredSize) {
      
      List<Integer> neighbors = generateAdjacentCells(point, seq);
      if (length == requiredSize) {
         return list;
      }
      if (neighbors.size() == 0) {
         return new LinkedList<Integer>();
      }
      for (int n: neighbors) {
         this.togglePosMark(n);
         length += elementAtPos(n).length();
         list.addLast(n);
         
         List<Integer> result = dfsRecursive(list, seq.substring(
            elementAtPos(n).length(), seq.length()), length, n, requiredSize);
            
         if (result.size() == 0) {
            length -= elementAtPos(n).length();
            this.togglePosMark(list.removeLast());
         }
         else {
            return result;
         }
      }
      return new LinkedList<Integer>();
   }
   
   /**
     * Generates the valid neighbors of an index
     * on the board. Checks for validity by
     * guarenting that the neighboring element(s)
     * contains the first part of the given sequence.
   *
   * @param s string to validate neighbors
   * @return empty list if no valid neighbors
   *     exist, list of the positions of valid
   *     neighbors if valid neighbors exist
   */
   private List<Integer> generateAdjacentCells(int i, String s) {
      LinkedList<Integer> validNeighbors = new LinkedList<Integer>();
      int iX = this.rowPos(i);
      int iY = this.colPos(i);
      int nX = iX - 1;
      int nY = iY - 1;
     //counter for validNeighbors array
      int c = 0;
      int t = 0;
      for (int k = 0; k < 9; k++) {
         if (nX >= 0 && nY >= 0 && nX < squareDimension && nY < squareDimension 
             && !markBoard[nX][nY] && (nX != iX || nY != iY) 
             && s.toUpperCase().startsWith(wordBoard[nX][nY])) {
             
            validNeighbors.add((nX * squareDimension + nY));
         }
         
         t++;
         nY++;
         
         if (t == 3) {
            nX++;
            nY -= 3;
            t = 0;
         }
      }
      //no valid neighbors so return null
      if (validNeighbors.size() == 0) {
         return new LinkedList<Integer>();
      }
      return validNeighbors;
   }
      
    /**
     * Sets the square dimension bound of the board.
     * Does not check to see if board is actually
     * square. Sets the squareDimension variable
     * of the board itself.
     *
     * @param wordBoardIn board to compute square
     *     dimensions of
     */
   private void setBound(String[] wordBoardIn) {
      squareDimension = ((int) Math.sqrt(wordBoardIn.length));
   }
   
   /**
   * Returns the valid row index of
   * a given position.
   *
   * @param pos position on the board
   * @return row index of the given position
   */
   private int rowPos(int pos) {
      return pos / squareDimension;
   }
   
   /**
   * Returns the valid column index of
   * a given position.
   *
   * @param pos position on the board
   * @return column index of the given position
   */
   private int colPos(int pos) {
      return pos % squareDimension;
   }
}