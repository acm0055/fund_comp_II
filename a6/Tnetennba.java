import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.NoSuchElementException;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.TreeSet;

/**
* Engine for a word search game.
*
* @author Austin Minor
* @version 11/10/15
*/
public class Tnetennba implements WordSearchGame {

   private SortedSet<String> lexicon = new TreeSet<String>();
   private WordSearchBoard board;
   
   /**
   * Loads a lexicon for the game.
   * Parses a file with the assumption
   * that the first token of each line
   * is the word to parse.
   *
   * @param fileName name of lexicon file to parse.
   */
   public void loadLexicon(String fileName) {
      Scanner fileIn = null;
      try {
         fileIn = new Scanner(new File(fileName));
      }
      catch (IOException ioe) {
         throw new IllegalArgumentException();
      }
      catch (NullPointerException npe) {
         throw new IllegalArgumentException();
      }
      Scanner lineScan = null;
      while (fileIn.hasNext()) {
         lineScan = new Scanner(fileIn.nextLine());
         lexicon.add(lineScan.next().toUpperCase());
      }
   }
   
   /**
   * Sets the board with the given
   * board array.
   *
   * @param letterArray string array
   *     to parse into a game board
   */
   public void setBoard(String[] letterArray) {
      if (letterArray == null) {
         throw new IllegalArgumentException();
      }
      if (!isSquare(letterArray.length)) {
         throw new IllegalArgumentException();
      }
      board = new WordSearchBoard(letterArray);
   }
   
   /**
   * Returns a string representation
   * of the game board.
   *
   * @return string representation of
   *     game board
   */
   public String getBoard() {
      if (board == null) {
         throw new IllegalStateException();
      }
      StringBuilder s = new StringBuilder();
      int[] dims = board.getDimensions();
      int numOfElements = dims[0] * dims[1];
      for (int i = 0; i < numOfElements; i++) {
         if (i % dims[1] == 0) {
            s.append("[");
         }
         s.append(board.elementAtPos(i) + " ");
         if (i % dims[1] == (dims[1] - 1)) {
            s.deleteCharAt(s.length() - 1);
            s.append("]\n");
         }
      }
      return s.toString();
   }
   
   /**
   * Returns all words in the lexicon and game board
   * greater than or equal to the specified length.
   * 
   * @param minWordLength minimum valid word length
   * @return all valid words at least minWordLength long
   */
   public SortedSet<String> getAllValidWords(int minWordLength) {
      if (board == null) {
         throw new IllegalStateException();
      }
      if (lexicon.size() == 0) {
         throw new IllegalStateException();
      }
      if (minWordLength < 1) {
         throw new IllegalArgumentException();
      }
      SortedSet<String> wordsOnBoard = new TreeSet<String>();
      for (String s: lexicon) {
         if (s.length() >= minWordLength) {
            if (board.contains(s)) {
               wordsOnBoard.add(s);
            }
         }
      }
      return wordsOnBoard;
   }
   
   /**
   * Calculates the score on the basis of
   * a minimum word length. Any word the minimum
   * length receives one point. Any word with length
   * past the minimum length receives one additional
   * point per length past. Any words below the
   * minimum word length recieve zero points.
   *
   * @param words word list to calculate the score of
   * @param minWordLength minimum word length
   * @return score of the given words
   */
   public int getScoreForWords(SortedSet<String> words, int minWordLength) {
      if (words == null || minWordLength < 1) {
         throw new IllegalArgumentException();
      }
      int score = 0;
      for (String s: words) {
         if (s.length() >= minWordLength) {
           //give score of each word of min length = 1
           //and 1 more for each letter past the min length
            score += (s.length() - minWordLength) + 1;
         }
      }
      return score;
   }
   
   /**
   * Tests if the word exists in the
   * lexicon.
   *
   * @param wordToCheck word to check for
   *     existance in lexicon
   * @return true if the given word exists
   *     in the lexicon
   */
   public boolean isValidWord(String wordToCheck) {
      if (lexicon.size() == 0) {
         throw new IllegalStateException();
      }
      if (wordToCheck == null) {
         throw new IllegalArgumentException();
      }
      if (lexicon.contains(wordToCheck)) {
         return true;
      }
      return false;
   }
   
   
   /**
   * Tests if words with the given
   * prefix exist in the lexicon.
   *
   * @param prefixToCheck prefix to check for
   *     existance of words in lexicon
   * @return true if the given prefix prefixes
   *     words in the lexicon
   */
   public boolean isValidPrefix(String prefixToCheck) {
      if (lexicon.size() == 0) {
         throw new IllegalStateException();
      }
      if (prefixToCheck == null) {
         throw new IllegalArgumentException();
      }
      try {
         if (lexicon.tailSet(prefixToCheck).first().contains(prefixToCheck)) {
            return true;
         }
      }
      catch (NoSuchElementException e) {
      }
      return false;
   }
   
   /**
   * Determines if the word exists on the
   * game board. If so, returns its position.
   *
   * @param wordToCheck word to check for
   *     existance on game board
   * @return position of word on game board if it exists,
   *     empty list if the word is not on the game board
   */
   public List<Integer> isOnBoard(String wordToCheck) {
      if (board == null) {
         throw new IllegalStateException();
      }
      if (lexicon.size() == 0) {
         throw new IllegalStateException();
      }
      if (wordToCheck == null) {
         throw new IllegalArgumentException();
      }
      return board.locate(wordToCheck);
   }
   
   /**
   * Determines if the game board is square.
   * By using tolerances and the floor and
   * ceiling functions of the square root
   * of the number of elements on the board.
   * 
   * @param boardSize number of elements on
   *     the board
   * @return true if the board size is square
   */
   private boolean isSquare(int boardSize) {
      double tolerance = 0.001;
      double sqVal = Math.sqrt(boardSize);
      if (Math.abs(sqVal - Math.ceil(sqVal)) < tolerance
          || Math.abs(sqVal - Math.floor(sqVal)) < tolerance) {
         return true;
      }
      return false;
   }
}