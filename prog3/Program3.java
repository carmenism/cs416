/**
 * Program3.java
 *    This program accepted values to create points on the panel for a seeker
 *    to visit.
 * 
 * @author Carmen St. Jean
 *         CS 416 - Fall 2008, September 23, 2008
 */

//----------------------- imports ----------------------------------------------
import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.util.*;

public class Program3 extends JFrame 
{
   //---------------------------------------------------------------------------
   //------------------------- constructor -------------------------------------
   
   /**
    * public Program3(String title, String [] args)
    *    Requires an array of strings for the speed and point coordinates.
    */
   public Program3(String title, String [] args) 
   {
      super(title);
      this.setSize(600, 450);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.add(new MovePanel(this, args));
      this.setVisible(true);
   }

   //---------------------------------------------------------------------------
   //------------------------- main method -------------------------------------
   
   public static void main(String [] args) 
   {
      Program3 app = new Program3("Program 3", args);
   }
}