/**
 * RecursionApp.java -- 
 * A driver for program 5
 * 
 */
import java.util.*;
import java.io.*;

public class RecursionApp 
{
   //---------------------- instance variables ----------------------
   private static Scanner terminal;
   
 
   //---------------------------------------------------------------
   public static void runMax()
   {
      String input;
      System.out.println( "Enter a list of integers on one line");
      
      input = terminal.nextLine();
      input = terminal.nextLine();
      Scanner list = new Scanner( input);
      int  temp [] = new int[1000];
      int i = 0;
      while( list.hasNextInt() )
         temp[i++] = list.nextInt();
      
      
      System.out.println( Recursion.findMax( temp, i) ) ;   
   }
   
   //---------------------------------------------------------------
   public static void runSearch()
   {
      String input;
      System.out.println( "Enter a list of integers on one line,\n the last number will be the search key");
      
      input = terminal.nextLine();
      input = terminal.nextLine();
      Scanner list = new Scanner( input);
      int  temp [] = new int[1000];
      int i = 0;
      while( list.hasNextInt() )
         temp[i++] = list.nextInt();
      
      
      System.out.println( Recursion.search( temp, i -1 , temp[i-1] ) ) ;   
   }
   
   
   //---------------------------------------------------------------
   public static void runprint1()
   {
      String input;
      int n = 0;
      System.out.println( "Enter an integer"); 
      input = terminal.next();
      Scanner list = new Scanner( input);
      
      if( list.hasNextInt() )
         n= list.nextInt();
      
      Recursion.insideOut( n ) ;
      System.out.println();    
   }
   
   
   //---------------------------------------------------------------
   public static void runPalindrome()
   {
      System.out.println( "Enter a string to check for palindrome");
      String input = terminal.next();
      
      if( Recursion.palindrome( input) ) 
         System.out.println( "Palindrome");  
      else
         System.out.println( "Not a Palindrome");   
   }
   
   //---------------------------------------------------------------
   public static void runCountA()
   {
      System.out.println( "Enter a string to count");
      String input = terminal.next();
      
      System.out.println( "Enter the letter to count ");
      String letter = terminal.next();
      
      System.out.println( "There are " + 
                         Recursion.countA( input,letter.substring(0,1) ) +" " + letter + " 's" );     
   }
   
  
   
   //---------------------------------------------------------------
   public static void runFlip()
   {
      System.out.println( "Enter a string to flip");
      String input = terminal.next();
      String f = Recursion.flip(input);    
      System.out.println( f );       
   }
   
   //---------------------------------------------------------------
   public static void runSub()
   {
      System.out.println( "Enter a string and a substring candidate");
      String input = terminal.next();
      String sub = terminal.next();
      if( Recursion.isSubstring( input, sub ))
         System.out.println( sub + " is a substring of " + input )  ;    
      else     
         System.out.println( sub + " is NOT a substring of " + input )  ;
   }
   
   //----------------------- main ----------------------------------------
   public static void main( String[] args )
   { 
      terminal = new Scanner( System.in );
      String choice = "";
      String outMessage;
      String inMessage = "Q(quit) " +
         "\nR(palindrome) "+
         "\nC(countA) "+
         "\nO(insideOut) "+
         "\nS(isSubstring) "+
         "\nM(findMax) "+
         "\nF(flip) "+
         "\nT(search) "
       
  ;
      
      System.out.println( inMessage );
      choice = terminal.next().trim().substring(0,1);
      System.out.println( choice);
      while( !choice.equalsIgnoreCase(  "Q" ) ) 
      {
         if( choice.equalsIgnoreCase(  "M" ) )
            runMax();
         else if ( choice.equalsIgnoreCase(  "R" ) )
            runPalindrome();
         else if ( choice.equalsIgnoreCase(  "C" ) )
            runCountA();
         else if ( choice.equalsIgnoreCase(  "O" ) )
            runprint1();
         else if ( choice.equalsIgnoreCase(  "F" ) )
            runFlip();
         else if ( choice.equalsIgnoreCase(  "S" ) )
            runSub();
         else if ( choice.equalsIgnoreCase(  "T" ) )
            runSearch();
         else
            System.err.println( "Invalid choice" );
         System.out.println( "\n**************" );
         System.out.println( inMessage );
         choice = terminal.next().trim().substring(0,1);     
      }
      
      System.out.println( "So long!");
   }
}