/**
 * Lab14.java -- Anagram
 * This program gets a string from the user (of length 3-8), generates all
 * permutations of the string and looks up each permutation in a Dictionary.
 * The Dictionary is the OPTED public domain English word list dictionary. 
 * This dictionary is based on the public domain portion of "The Project 
 * Gutenberg Etext of Webster's Unabridged Dictionary" which is in turn based
 * on the 1913 US Webster's Unabridged Dictionary. (See Project Gutenburg)
 *             http://msowww.anu.edu.au/~ralph/OPTED/
 * 
 * Words of length 3 to 8 were extracted from this dictionary into a file 
 * named opted3to8.txt. There are no plurals in the dictionary.
 * 
 * The entire dictionary is read into a Java Collection object and then
 * the user is allowed to enter a list of 3-8 characters, from which all
 * valid anagrams are extracted. 
 * 
 */
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import java.net.*;

public class Lab14 
{
   //---------------------- instance variables ----------------------
   Collection<String> _dictionary;
    private final String DICT_URL = "http://cs.unh.edu/~cs416/public/opted3to8.txt";
   private char      _option;

   //--------------------------- constructor -----------------------
   public Lab14( String title )     
   {      
      String optionMessage = chooseAlgorithm();
      while ( optionMessage != null )
      {         
         readWebDictionary( _dictionary );
         
         String inMessage = "Enter a string of letters";
         String input = JOptionPane.showInputDialog( null,  inMessage );
         
         String outMessage;
         Collection<String> validWords = new Vector<String>();
         
         while ( input != null && input.length() > 0 )
         {
            if ( input.length() < 3 || input.length() > 8 )
               outMessage = "Program limited to 3-8 letter words. Try another";
            else
            {   
               long start = System.currentTimeMillis();
               
               lookup( validWords, "", input );
               if ( validWords.size() == 0 )
                  outMessage = input + ": leads to no words";
               else 
                  outMessage = makeMessage( input, validWords );
               
               validWords.clear();
               
               long time = System.currentTimeMillis() - start;
               
               System.out.println( optionMessage + "   Len, time: " + input.length() + " " + time );
            }         
            JOptionPane.showMessageDialog( null, outMessage );
            input = JOptionPane.showInputDialog( null,  inMessage ); 
         }
         optionMessage = chooseAlgorithm();
      }
   }
   //--------------------- search( String, int, int ) ------------------------
   /**
    * Do a binary search looking for the word.
    */
   private boolean search( String word, Vector<String> dict, int lo, int hi )
   {
      if ( lo > hi ) 
         return false;
      int mid = ( hi + lo ) / 2;
      
      int comp = word.compareTo( dict.get( mid )  );
      
      if ( comp < 0 )   // word belongs between lo and mid
      {
         return search( word, dict, lo, mid - 1 );
      }
      else if ( comp > 0 )      // word is between mid and hi  
      {
         return search(  word, dict, mid + 1, hi );
      }
      else
      {
         return true;
      }
   }
   //---------------------- chooseAlgorithm -----------------------------
   /**
    * Open a dialog box to let user choose among the 3 algorithm options.
    * Only the 1st character of the user's input is used to determine
    * the chosen option. The returned string is just a term referring to the
    * chosen algorithm.
    */
   private String chooseAlgorithm()
   {    
      String outMessage;
      String inMessage = "Which algorithm: H (HashTable), "
                       + "J (Java's Hashset), or B (Binary search)?";
      String input = JOptionPane.showInputDialog( null,  inMessage ); 
      
      if ( input == null )
         outMessage = null;
      else
      {
         _option = input.toLowerCase().charAt( 0 );
         switch ( _option )
         {
            default:
               System.err.println( "Bad input: you're getting HashTable!" );
               _option = 'h';
            case 'j': 
               _dictionary = new HashSet<String>();
               outMessage = "Hashset ";
               break;
            case 'h': 
               outMessage = "HashTable ";
               int size = getTableSize();
               _dictionary = new HashTable<String>( size );
               break;
            case 'b': 
               outMessage = "Binary Tree ";
               _dictionary = new Vector<String>();
               break;
         }  
      }
      return outMessage;
   }
   //----------------------- getTableSize() -----------------------------
   /**
    * open a dialog box to get the hash table size
    */
   private int getTableSize()
   {
      String inMessage = "Enter size for hash table: ";
      String input = JOptionPane.showInputDialog( null,  inMessage ); 
      while ( true )  // not really; we return from loop once valid int entered
      {
         try 
         {
            return Integer.parseInt( input );
         }
         catch ( NumberFormatException nfe )
         {
            input = JOptionPane.showInputDialog( null, "Invalid integer. "
                                                   + inMessage );
         }
      }
   }
      
   //---------------------- lookup( String, String ----------------------
   /**
    * This is the key recursive call 
    *   The "head" parameter is the fixed portion of the current permutation;
    *   the "tail" parameter is set of characters for which all permutations
    *   must still be generated for this "head". 
    *  
    *   Permutations that represent valid words are added to wordsFound.
    */
   private void lookup( Collection<String> wordsFound, String head, String tail )
   {
      String newHead, newTail;
      
      // the "Base case" is when there is nothing left in the tail.
 
      if ( tail.length() == 0 ) // no tail means we've got a possible word
      {
         if ( ifWord( head )  && !wordsFound.contains( head ))
            wordsFound.add( head );
      }
      else
      {  // for each character in "tail" add it to the head and
         //    generate permutations for the remainder of the tail.
         for ( int i = 0; i < tail.length(); i++ )
         {
            // add next character of the tail to the head 
            newHead = head + tail.substring( i, i + 1 );
            
            // remove that character from the tail
            newTail = tail.substring( 0, i ) + tail.substring( i + 1 );
            
            // and recurse
            lookup( wordsFound, newHead, newTail );
         }
      }
   }
   //--------------------- ifWord( String ) ------------------------------
   /**
    * check if the argument string is in the English word dictionary.
    */
   private boolean ifWord( String word )
   {
      if ( _option == 'b' )
         return search( word, (Vector<String>)_dictionary, 
                         0, _dictionary.size() - 1 );
      else
         return _dictionary.contains( word );
   }
   
//------------------- readWebDictionary -------------------------------
   private void readWebDictionary( Collection<String> dictionary )
   {
      URLConnection connection = null;
      
      try 
      {
         connection =  new URL(DICT_URL).openConnection();
      } catch (IOException e) 
      {
         System.err.println( "***Error -- can't open url: " + DICT_URL );
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
      
      while ( scanner.hasNext() )
      {
         dictionary.add( scanner.next() );        
      }
      if ( dictionary instanceof HashTable )
         ((HashTable) dictionary).report();
  }



   //------------------- readDictionary -------------------------------
   private void readDictionary( Collection<String> dictionary )
   {
      Scanner scanner = null;
      try
      {
            scanner = new Scanner( new File( "opted3to8.txt" ));
      }
      catch ( IOException ioe )
      {
         System.err.println( "***Error -- can't open 'opted.txt'" );
         System.exit( -1 );
      }
 
      while ( scanner.hasNext() )
      {
         dictionary.add( scanner.next() );
      }
      if ( dictionary instanceof HashTable )
         ((HashTable) dictionary).report();
   }
   //--------------------- makeMessage( String, Collection ) ------------------------
   private String makeMessage( String start, Collection<String> validWords ) 
   {
      String message = start + ": makes " + validWords.size() + " words.";
      int i = 0;
      
      for ( Iterator iter = validWords.iterator(); iter.hasNext(); )
      {
         if ( i++ % 4 == 0 )
            message = message + "\n" + iter.next();
         else
            message = message + "    " + iter.next();
      }
      return message;
   }
   //----------------------- main ----------------------------------------
   public static void main( String[] args )
   {
      new Lab14( "Lab14" );
   }
}