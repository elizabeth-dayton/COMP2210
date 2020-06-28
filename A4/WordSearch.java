import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.Math;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
/**
*Implements the WordSearchGame abstract class.
*
*Elizabeth Dayton.
*3-27-18.
*/

public class WordSearch implements WordSearchGame {

   String[][] defaultBoard = {{"E", "E", "C", "A"},
      {"A", "L", "E", "P"},
      {"H", "N", "B", "O"},
      {"Q", "T", "T", "Y"}}; 
   String[][] userCreatedBoard = null;
   TreeSet<String> lexicon = new TreeSet<String>();
   boolean lexiconLoaded = false;
   int boardDimensions = 4;
   boolean[][] visited = new boolean[boardDimensions][boardDimensions];
   List<Integer> path = new LinkedList<Integer>();
   String word = null;
   StringBuilder wordSoFar = new StringBuilder();
   boolean isOnBoard = false;
   int z = 0;

/**
*Loads a lexicon of valid words.
*
*@param fileName used.
*/
   public void loadLexicon(String fileName) {
   
      if (fileName == null) {
      
         throw new IllegalArgumentException();
      
      }
   
      try {
      
         Scanner s = 
            new Scanner(new BufferedReader(new FileReader(new File(fileName))));
         
         while (s.hasNext()) {
            String str = s.next();
            lexicon.add(str);
            s.nextLine();
         }
         
         lexiconLoaded = true;
         
      }
      
      catch (FileNotFoundException e) {
      
         throw new IllegalArgumentException();
      
      }
      
      
   }

/**
*Sets the game board.
*
*@param letterArray used.
*/
   public void setBoard(String[] letterArray) {
   
      if (letterArray == null || Math.sqrt(letterArray.length) % 2 != 0) {
      
         throw new IllegalArgumentException();
      
      }
   
      int length = letterArray.length;
      boardDimensions = (int) Math.sqrt(length);
      int x = 0;
      
      userCreatedBoard = new String[boardDimensions][boardDimensions];
      
      for (int rows = 0 ; rows < userCreatedBoard.length; rows++) {
         
         for (int cols = 0 ; cols < userCreatedBoard[0].length; cols++) {
            
            userCreatedBoard[rows][cols] = letterArray[x];
            x++;
               
         }     
      }
      
      visited = new boolean[boardDimensions][boardDimensions];
   }
   
/**
*Returns the current game board in string form.
*/
   public String getBoard() {
   
      String output = "The letters on the board are: ";
   
      if (userCreatedBoard == null) {
      
         for (String[] letter : defaultBoard) {
         
            output += Arrays.toString(letter);
         
         }
      }
      
      else {
      
         for (String[] letter : userCreatedBoard) {
         
            output += Arrays.toString(letter);
         
         }
      }
      
      System.out.print(output + "\n");
      return output;
   }

/**
*Gets all valid words on the game board.
*(words of the minimum length and found in the loaded lexicon).
*
*@param minimumWordLength used.
*/   
   public SortedSet<String> getAllValidWords(int minimumWordLength) {
   
      if (lexiconLoaded == false) {
      
         throw new IllegalStateException();
      
      }
      
      if (minimumWordLength < 1) {
      
         throw new IllegalArgumentException();
      
      }
      
      SortedSet<String> validWords = new TreeSet<String>();
      
      for (String word : lexicon) {
      
         if (word.length() < minimumWordLength) {
         
            continue;
         
         }
         isOnBoard(word);
      
         if (isOnBoard) {
         
            validWords.add(word);
         
         }
      
      }
   
      return validWords;
   
   }

/**
*Computes the score for all valid words found.
*
*@param words used.
*@param minimumWordLength used.
*/
   public int getScoreForWords(SortedSet<String> words, int minimumWordLength) {
   
      if (lexiconLoaded == false) {
      
         throw new IllegalStateException();
      
      }
      
      if (minimumWordLength < 1) {
      
         throw new IllegalArgumentException();
      
      }
   
      int score = 0;
      
      for (String word : words) {
      
         if (word.length() >= minimumWordLength && isValidWord(word) && isOnBoard(word) == null) {
         
            score += (1 + (word.length() - minimumWordLength));
         
         }
      
      } 
   
      return score;
   
   }

/**
*Determines if a given word is found in the loaded lexicon.
*
*@param wordToCheck used.
*/
   public boolean isValidWord(String wordToCheck) {
   
      if (lexiconLoaded == false) {
      
         throw new IllegalStateException();
      
      }
      
      if (wordToCheck == null) {
      
         throw new IllegalArgumentException();
      
      }
      
      word = wordToCheck;
      
      if (lexicon.contains(wordToCheck)) {
         
         return true;
         
      }
   
      return false;
   
   }

/**
*Checks the loaded lexicon to see if any word begins with the specified string.
*
*@param prefixToCheck used.
*/
   public boolean isValidPrefix(String prefixToCheck) {
   
      if (lexiconLoaded == false) {
      
         throw new IllegalStateException();
      
      }
      
      if (prefixToCheck == null) {
      
         throw new IllegalArgumentException();
      
      }
      
      if (lexicon.ceiling(prefixToCheck) == null) {
         
         return false;
         
      }
   
      return true;
   
   }

/**
*Determines if a given word is on the board regardless of whether it is
*in the loaded lexicon or its length.
* 
*@param wordToCheck used.
*/
   public List<Integer> isOnBoard(String wordToCheck) {
   
      if (lexiconLoaded == false) {
      
         throw new IllegalStateException();
      
      }
      
      if (wordToCheck == null) {
      
         throw new IllegalArgumentException();
      
      }
      
      word = wordToCheck;
      
      depthFirst(0, 0);
      
      return path;
   
   }

/**
*depth first search.
*/
   public void depthFirst(int x, int y) {
      for (boolean[] row : visited) {
         Arrays.fill(row, false);
      }
   
      Position start = new Position(x, y);
      if (isValid(start)) {
      
         dfsRecursive(start);
      }
   }
   
      
/**
*Depth-first search (recursively) for isOnBoard method.
*
*@param position used.
*/   
   public void dfsRecursive(Position position) {
   
   
      if (isVisited(position)) {
         return;
      }
      visit(position);
      if (wordSoFar != null && wordSoFar.toString() == word) {
      
         isOnBoard = true;
         return;
      
      }
      process(position);
      for (Position neighbor : position.neighbors()) {
         dfsRecursive(neighbor);
      }
   }
   
   
   private boolean isValid(Position p) {
      return (p.x >= 0) && (p.x < boardDimensions) && (p.y >= 0) && (p.y < boardDimensions);
   }

   /**.
    * Has this valid position been visited?
    */
   private boolean isVisited(Position p) {
      return visited[p.x][p.y];
   }

   /**
    * Mark this valid position as having been visited.
    */
   private void visit(Position p) {
      visited[p.x][p.y] = true;
   }

   /**
    * Process this valid position.
    */
   private void process(Position p) {
   
      if (userCreatedBoard == null) {
      
         if (isValidPrefix(defaultBoard[p.x][p.y]) 
            && word.charAt(z) == (defaultBoard[p.x][p.y]).toString().charAt(0)) {
         
            wordSoFar.insert(wordSoFar.length(), defaultBoard[p.x][p.y]);
         
            if (p.x == 0) {
            
               path.add(p.y);
            
            }
            
            else {
            
               path.add(p.x * boardDimensions + p.y);
            
            }
         }
      
      }
      
      else {
      
         if (isValidPrefix(userCreatedBoard[p.x][p.y]) 
            && word.charAt(z) 
            == (userCreatedBoard[p.x][p.y]).toString().charAt(0)) {
         
            z++;
            wordSoFar.insert(wordSoFar.length(), userCreatedBoard[p.x][p.y]);
         
            if (p.x == 0) {
            
               path.add(p.y);
            
            }
            
            else {
            
               path.add(p.x * boardDimensions + p.y);
            
            }
         }
      
      
      }
   }

   class Position {
   
      int maxNeighbors = 8;
      int x;
      int y;
   
      /** Constructs a Position with coordinates (x,y). */
      public Position(int x, int y) {
         this.x = x;
         this.y = y;
      }
   
      /** Returns a string representation of this Position. */
      @Override
      public String toString() {
         return "(" + x + ", " + y + ")";
      }
   
      /** Returns all the neighbors of this Position. */
      public Position[] neighbors() {
         Position[] nbrs = new Position[maxNeighbors];
         int count = 0;
         Position p;
         // generate all eight neighbor positions
         // add to return value if valid
         for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
               if (!((i == 0) && (j == 0))) {
                  p = new Position(x + i, y + j);
                  if (isValid(p)) {
                     nbrs[count++] = p;
                  }
               }
            }
         }
         return Arrays.copyOf(nbrs, count);
      }
   }
}


