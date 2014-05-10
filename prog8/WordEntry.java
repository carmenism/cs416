/**
 * WordEntry.java
 *     For Program8
 * 
 * @author Carmen St. Jean
 *         CS 416 - Fall 2008, October 28, 2008
 */

//----------------------- imports ----------------------------------------------
import java.util.*;

public class WordEntry
{
   //------------------------- instance variables ------------------------------
   private Vector<Integer> lines;
   public String word;
   
   //---------------------------------------------------------------------------
   //------------------------- constructor -------------------------------------
   public WordEntry(String w, int n)
   {
      word = w.toUpperCase();
      lines = new Vector<Integer>();
      lines.add(new Integer(n));
   }
   
   //---------------------------------------------------------------------------
   //------------------------- methods -----------------------------------------
   
   /**
    * public void addLine(int n)
    *    Adds a line number to the word entry.
    */
   public void addLine(int n)
   {
      lines.add(new Integer(n));
   }
   
   //---------------------------------------------------------------------------
   
   /**
    * public String getWord()
    *    Gets the associated word from the word entry.
    */
   public String getWord()
   {
      return word;
   }
   
   //---------------------------------------------------------------------------
   
   /**
    * public String printWord()
    *    Returns the word and the line(s) it occurs on as a string.
    */
   public String printWord()
   {
      String s = "";
      s += "[" + word;
      for (int i = 0; i < lines.size(); i++)
         s += " " + lines.get(i).intValue();
      s += "]";
      return s;
   }
}