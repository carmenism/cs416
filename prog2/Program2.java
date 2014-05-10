/**
 * Program2.java
 * 
 * @author Carmen St. Jean
 *         CS 416 - Fall 2008, September 16, 2008
 */

//----------------------- imports ----------------------------------------------
import java.awt.*;
import javax.swing.*;

public class Program2 extends JFrame
{
   //------------------------- instance variables ------------------------------
   public Program2(String title)
   {
      super(title);
      this.setSize(700, 500);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.add(new DrawPanel(this));
      this.setVisible(true); 
   } 
   
   //------------------------- main --------------------------------------------
   public static void main(String[] args)
   {
      Program2 app = new Program2("Program 2");
   }
}