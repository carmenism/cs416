/**
 * HashTable.java
 *     For Program8
 * 
 * @author Carmen St. Jean
 *         CS 416 - Fall 2008, October 28, 2008
 */

//----------------------- imports ----------------------------------------------
import java.util.*;

public class HashTable
{
   //------------------------- instance variables ------------------------------
   private int _size;
   public LinkedList<WordEntry> [] _table;   
   
   //---------------------------------------------------------------------------
   //------------------------- constructor -------------------------------------
   public HashTable(int tableSize) 
   {
      _table = (LinkedList<WordEntry>[]) new LinkedList[tableSize];
      for (int i = 0; i < tableSize; i++)
      {
         _table[i] = new LinkedList<WordEntry>();
      }
   }
   
   //---------------------------------------------------------------------------
   //------------------------- methods -----------------------------------------
   
   /**
    * private int tableIndex(String s)
    *    Finds the table the specified string should be sorted under.
    */
   private int tableIndex(String s)
   {
      return hash(s) % _table.length;
   }

   //---------------------------------------------------------------------------
   
   /**
    * private int hash(String s)
    *    Hashes the specified string using the "better hash" function from the
    *    lab.
    */
   private int hash(String s)
   {
      int sum = 0;
      for (int i = 0; i < s.length(); i++)
         sum += Math.pow(10, i) * s.charAt(i); 
      return sum;
   }
   
   //---------------------------------------------------------------------------
   
   /**
    * public boolean add(WordEntry newOne)
    *    Adds a new WordEntry object.
    */
   public boolean add(WordEntry newOne)
   {
      if (contains(newOne))
         return false;
      else
      {
         String s = newOne.word;
         int i = tableIndex(s);
         _table[i].add(newOne);
         _size++;
         return true;
      }
   }
   
   //---------------------------------------------------------------------------
   
   /**
    * public WordEntry getEntry(String search)
    *    Looks to see if a word exists in the hash table as a WordEntry and
    *    returns corresponding WordEntry, or null if the word is not in the
    *    hash table.
    */
   public WordEntry getEntry(String search)
   {
      int i = tableIndex(search);
      for (int j = 0; j < _table[i].size(); j++)
      {
         WordEntry w = (WordEntry) _table[i].get(j);
         if (search.equals(w.word))
            return w;
      }
      return null;
   }
   
   //---------------------------------------------------------------------------
   
   /**
    * public boolean contains(WordEntry search)
    *    Looks to see if a word exists in the hash table as a WordEntry or not.
    */
   public boolean contains(WordEntry search)
   {
      String s = search.getWord();
      int i = tableIndex(s);
      return _table[i].contains(search);
   }
   
   //---------------------------------------------------------------------------
   
   /**
    * public boolean isEmpty()
    *    Returns true if the list is empty.
    */
   public boolean isEmpty()
   {
      return _size == 0;
   }
   
   //---------------------------------------------------------------------------
   
   /**
    * public int size()
    *    Returns the size of the hash table.
    */
   public int size()
   {
      return _size;
   }
}
         