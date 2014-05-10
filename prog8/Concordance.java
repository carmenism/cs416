/**
 * Concordance.java
 *     For Program8
 * 
 * @author Carmen St. Jean
 *         CS 416 - Fall 2008, October 28, 2008
 */

//----------------------- imports ----------------------------------------------
import java.util.*;

public class Concordance
{  
   //------------------------- instance variables ------------------------------
   private int hashSize;
   private HashTable hashTable;
   
   //---------------------------------------------------------------------------
   //------------------------- constructor -------------------------------------
   public Concordance(int _hashSize)
   {
      hashSize = _hashSize;
      hashTable = new HashTable(hashSize);
   }
   
   //---------------------------------------------------------------------------
   //------------------------- methods -----------------------------------------
   
   /**
    * public String findWord(String word)
    *    Finds if word is in the concordance or not.  If it is, then it returns
    *    a string containing the word and the line numbers.  If it is not, then
    *    it returns a message to indicate so.
    */
   public String findWord(String word)
   {
      String s;
      try
      {
         s = hashTable.getEntry(word).printWord();
      }
      catch (Exception e)
      {
         s = "Did not find " + word;
      }
      return s;
   }

   //---------------------------------------------------------------------------
   
   /**
    * public void addWord(String word, int line)
    *    Adds a word if it is not already in the concordance (hash table).  If
    *    it is, then it simply adds the line number.
    */
   public void addWord(String word, int line)
   {
      String capsWord = word.toUpperCase();
      WordEntry temp = (WordEntry) hashTable.getEntry(capsWord);
      if (temp != null)
      {
         temp.addLine(line);
      }
      else
      {
         hashTable.add(new WordEntry(capsWord, line));
      }
   }
   
   //---------------------------------------------------------------------------
   
   /**
    * public void printContents()
    *    Prints the contents of the entire concordance.
    */
   public void printContents()
   {
      for (int i = 0; i < hashSize; i++)
      {
         System.out.print(i + ": ");
         Iterator<WordEntry> iter = hashTable._table[i].iterator();
         while (iter.hasNext())
         {
            System.out.print(iter.next().printWord());
         }
         System.out.print("\n");
      }
   }
}