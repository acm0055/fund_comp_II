
/**
 * Provides a facotry method for creating word search games. 
 */
public class WordSearchGameFactory {
   private static String[] defaultBoard = 
      {"E", "E", "C", "A", "A", "L", "E", "P", "H", "N", "B", "O", "Q", "T", "T", "Y"};
   public static WordSearchGame createGame() {
   // change this to return an instance of your game engine
      Tnetennba wordGame = new Tnetennba();
      wordGame.setBoard(defaultBoard);
      
      return wordGame; 
   }
}