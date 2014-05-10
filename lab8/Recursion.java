import java.util.*;
import java.io.*;


public class Recursion
{
  
   //-----------------------------------------------------
   //
   // find the max value in array list[0,...,n-1]
   //  n is number of elements in the array
   public static int findMax( int [] list , int n)
   {
      if (n == 0)
      {
         return Integer.MIN_VALUE;
      }
      else if (n == 1)
      {
         return list[0];
      }
      else
      {
         return Math.max(list[n-1], findMax(list, n-1));
      }
   }
   
   //-------------------------------------------------------
   //  search for "key" in array list[0,...,n-1]
   //   n is number of elements in the array
   //   return the index of a found element ( or -1 if not found)
   
   public static int search( int [] list , int n, int key)
   {
      if (n == 0)
      {
         return -1;
      }
      else if (list[n-1] == key)
      {
         return (n-1);
      }
      else
      {
         return (search(list, n-1, key));
      }
   }
   
   
   //------------------------------------------------------------
   //  returns true if this String is a palindrome
   public static boolean palindrome( String input)
   {
      int length = input.length();
      String first = input.substring(0, 1);
      String last = input.substring(length-1, length);
      
      if (length == 1)
      {
         return true;
      }
      else if (!first.equals(last))
      {
         return false;
      }
      else
      {
         return palindrome(input.substring(1, length-1));
      }
   }
   
   
   //-------------------------------------------------------------
   // returns the number of occurances of the letter "letter"
   // in the string  
   public static int countA( String input, String letter)
   {
      if (input.substring(0, 1).equals(letter))
      {
         if (input.length() == 1)
         {
            return 1;
         }
         else
         {
            return (1 + countA( input.substring(1), letter));
         }
      }
      else
      {
         if (input.length() == 1)
         {
            return 0;
         }
         else
         {
            return (countA( input.substring(1), letter));
         }
      }
   }

   
   //------------------------------------------------------------------------
   //  flips each pair of letters
   //  eg. input:  abcdefg
   //     output:  badcfeg
   public static String flip( String s )
   {
      if (s.equals(""))
      {
         return "";
      }
      else if (s.length() == 1)
      {
         return s;
      }
      else
      {
         String first = s.substring(0, 1);
         String second = s.substring(1, 2);
         return second + first + flip(s.substring(2));
      }  
   }
   
   
   //------------------------------------------------------------------------  
   // returns true is t is a substring of s
   //
   public static boolean isSubstring( String s , String t)
   {
      if (s.length() < t.length())
      {
         return false;
      }
      else if (s.substring(0, t.length()).equals(t))
      {
         return true;
      }
      else
      {
         return isSubstring(s.substring(1), t);
      }
   }
   
   
   //------------------------------------------------------------------------ 
   //  prints out the integer argument "inside out" eg.
   //   n = 123    output:   32123     
   //   you can assume that n is positive
   public static void insideOut( int n )
   {
      if (n < 10)
      {
         System.out.print(n);
      }
      else
      {
         System.out.print(n%10);
         insideOut(n/10);
       //  System.out.print(n);
      }
//       System.out.println("*****  insideOut has not been implemented yet!***");
   }
   
  

}