/**
 * Program8.java
 *     Hash Tabel Concordance
 * 
 * @author Carmen St. Jean
 *         CS 416 - Fall 2008, October 28, 2008
 */

//----------------------- imports ----------------------------------------------
import javax.swing.*;
import java.util.*;
import java.io.*;
import java.net.*;

public class Program8 
{
   //------------------------- instance variables ------------------------------
   private Concordance c;
   
   //---------------------------------------------------------------------------
   //------------------------- constructor -------------------------------------
   public Program8( String[] args )     
   {
      int hashSize = 100;
      String url = "http://www.cs.unh.edu/~cs416/public/gettysburg.txt";
      if (args.length > 0)
      {
         try
         {
            hashSize = Integer.parseInt(args[0]);
         }
         catch (NumberFormatException nfe)
         {
            System.err.println("Argument must be integer. " + 
                               "Using default hash size.");
            hashSize = 100;
         }
         try
         {
            url = args[1];
         }
         catch (ArrayIndexOutOfBoundsException e)
         {
            System.err.println("Using default URL.");
            url = "http://www.cs.unh.edu/~cs416/public/gettysburg.txt";
         }
      }
      else
      {
         System.err.println("Using default hash table size and URL.");
      }
      
      c = new Concordance(hashSize);
      scanIn(url);
      c.printContents();
      promptForInput();
   }
      
   //---------------------------------------------------------------------------
   //------------------------- methods -----------------------------------------
   
   /**
    * private void scanIn(String url)
    *    Scans in the words from the file at specified URL, or reports an error.
    */
   private void scanIn(String url)
   {
      URLConnection connection = null;
      try 
      {
         connection =  new URL(url).openConnection();
      }
      catch (IOException e) 
      {
         System.err.println( "***Error -- can't open url: " + url );
         System.exit( - 1 );
      }
      Scanner scanner = null;
      try
      {
         scanner = new Scanner( connection.getInputStream( ) );        
      }
      catch ( IOException e )
      {
         System.err.println( "***Error -- can't create scanner" );
         System.exit( - 1 );
      }
      scanner.useDelimiter("\n");
      while (scanner.hasNext())
      {
         if (scanner.next().equals("***START***"))
         {
            break;
         }  
      }
      int lineCount = 1;
      while (scanner.hasNext())
      {
         Scanner scanner2 = new Scanner(scanner.next());
         scanner2.useDelimiter("\\W+");
         while (scanner2.hasNext())
         {
            String word = scanner2.next();
            c.addWord(word, lineCount);
         }
         lineCount++;
      }
   }
      
   //---------------------------------------------------------------------------
   
   /**
    * private void promptForInput()
    *    Prompts the user for input, searches for the input in the concordance,
    *    and reports the results.  Continues to prompt until the user types
    *    or clicks 'cancel.'
    */
   private void promptForInput()
   {
      try
      {
         JOptionPane j = new JOptionPane();
         String input = j.showInputDialog(null, "Enter a word", "Concordance",
                                          JOptionPane.QUESTION_MESSAGE);
         while (!input.equalsIgnoreCase("cancel"))
         {
            String result = c.findWord(input.toUpperCase());
            j.showMessageDialog(null, result, "Concordance Results",
                                JOptionPane.PLAIN_MESSAGE);
            input = j.showInputDialog(null, "Enter a word", "Concordance",
                                      JOptionPane.QUESTION_MESSAGE);
         }
      }
      catch(Exception e)
      { }
   }

   //---------------------------------------------------------------------------
   //------------------------- main --------------------------------------------
   public static void main( String[] args )
   {
      new Program8( args );
   }
}