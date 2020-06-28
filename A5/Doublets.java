import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.stream.Collectors;
   
/**
 * Provides an implementation of the WordLadderGame interface. The lexicon
 * is stored as a HashSet of Strings.
 *
 * @author Your Name (you@auburn.edu)
 * @author Dean Hendrix (dh@auburn.edu)
 * @version 2018-04-06
 */
public class Doublets implements WordLadderGame {

   // The word list used to validate words.
   // Must be instantiated and populated in the constructor.
   private TreeSet<String> lexicon;


   /**
    * Instantiates a new instance of Doublets with the lexicon populated with
    * the strings in the provided InputStream. The InputStream can be formatted
    * in different ways as long as the first string on each line is a word to be
    * stored in the lexicon.
    */
   public Doublets(InputStream in) {
      try {
         lexicon = new TreeSet<String>();
         Scanner s =
            new Scanner(new BufferedReader(new InputStreamReader(in)));
         while (s.hasNext()) {
            String str = s.next().toLowerCase();
            lexicon.add(str);
            s.nextLine();
         }
         in.close();
      }
      catch (java.io.IOException e) {
         System.err.println("Error reading from InputStream.");
         System.exit(1);
      }
   }


   //////////////////////////////////////////////////////////////
   // ADD IMPLEMENTATIONS FOR ALL WordLadderGame METHODS HERE  //
   //////////////////////////////////////////////////////////////
   
   /**
   *returns the hamming distance between two strings.
   *
   *@param str1 used.
   *@param str2 used.
   */
   public int getHammingDistance(String str1, String str2) {
   
      int hammingDistance = 0;
   
      if (str1.length() != str2.length()) {
      
         return -1;
      }
      
      int x = 0;
      
      while (x < str1.length()) {
      
         if (str1.charAt(x) != str2.charAt(x)) {
         
            hammingDistance++;
         
         }
      
         x++;
      }
      
      return hammingDistance;
   }
   
  /**
  *returns the minimum length word ladder.
  *
  *@param start used.
  *@param end used.
  */ 
   public List<String> getMinLadder(String start, String end) {
         
      List<String> minWordLadder = new LinkedList<String>();
      List<String> emptyLadder = new LinkedList<String>();
      
      if (isWord(start) == false || isWord(end) == false) {
      
         return emptyLadder;
      
      }
      
      minWordLadder.add(start);
      int length = 1;
      String current = start;
      int hammingDistance = getHammingDistance(start, end);
      
      if (getHammingDistance(current, end) == 1) {
      
         minWordLadder.add(end);
         return minWordLadder;
      
      }
      
      int counter = 1;
      
      for (int x = (hammingDistance - 1); x >= 0; x--) {
      
         List<String> neighbors = getNeighbors(current);
      
         for (String str : neighbors) {
         
            if (getHammingDistance(str, end) == x) {
            
               minWordLadder.add(str);
               length++;
               current = str;
               break;
            
            }
            
         }
         
         counter++;
          
         if (length != counter) {
         
            for (String str : neighbors) {
            
               if (getHammingDistance(current, str) == 1) {
                 
                  minWordLadder.add(str);
                  current = str;
                  length++;
                  x++;
                  break; 
               
               } 
            
            }
            continue;
         }
      }
      
      
      if (minWordLadder.contains(start) && minWordLadder.contains(end)) {
      
         return minWordLadder;
      
      }
      
      return emptyLadder;
   
   }
 
 /**
 *returns all words that have a hamming distance of one relative to the given word.
 *
 *@param word used.
 */
   public List<String> getNeighbors(String word) {
   
      List<String> neighbors = new LinkedList<String>();
   
      for (String str : lexicon) {
      
         if (getHammingDistance(word, str) == 1) {
         
            neighbors.add(str);
         
         }
      }
   
      return neighbors;
   
   } 
 
 /**
 *returns the total number of words in the current lexicon.
 */
   public int getWordCount() {
   
      return lexicon.size();
   
   }
 
 /**
 *checks to see if the given string is a word.
 *
 *@param str used.
 */
   public boolean isWord(String str) {
   
      if (lexicon.contains(str)) {
      
         return true;
      
      }
   
      return false;
   
   } 

/**
*checks to see if the given sequence of strings is a word ladder.
*
*@param sequence used.
*/ 
   public boolean isWordLadder(List<String> sequence) {
   
      if (sequence.isEmpty()) {
      
         return false;
      
      }
   
      for (String str : sequence) {
      
         if (isWord(str) == false) {
         
            return false;
         
         }
      
      }
      
      for (int x = 0; x < sequence.size() - 1; x++ ) {
      
         if (getHammingDistance(sequence.get(x), sequence.get(x + 1)) != 1) {
         
            return false;
         
         }
      
      }
   
      return true;
   
   }
 
}

