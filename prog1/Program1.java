/**
 * Program1.java
 *    Othello AKA Reversi, white player is the computer.
 * 
 * @author Carmen St. Jean
 *         CS 416 - Fall 2008, September 9, 2008
 */

//----------------------- imports ----------------------------------------------
import wheelsunh.users.*;
import java.awt.Color;
import java.util.*;
import java.io.*;
import java.awt.Dimension;

public class Program1 extends Frame
{
   //------------------------- instance variables ------------------------------
   private Board board;
   
   //---------------------------------------------------------------------------
   //------------------------- constructor -------------------------------------
   
   /**
    * Program1()
    *    Creates at instance of the board.
    */
   public Program1() throws Exception
   {
      board = new Board(10, 10, 60);
   }

   //---------------------------------------------------------------------------
   //------------------------- main method -------------------------------------

   public static void main(String [] args) throws Exception
   {
      Program1 app = new Program1();
   }
}
