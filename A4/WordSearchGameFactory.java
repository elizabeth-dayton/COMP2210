
/**
 * Provides a factory method for creating word search games. 
 *
 * @author YOUR NAME (YOU@auburn.edu)
 * @author Dean Hendrix (dh@auburn.edu)
 * @version TODAY
 */
public class WordSearchGameFactory {

   /**
    * Returns an instance of a class that implements the WordSearchGame
    * interface.
    */
   public static WordSearchGame createGame() {
      
      return new WordSearch();
   }

}
