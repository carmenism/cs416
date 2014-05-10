/**
 * Program2.java
 * 
 * @author Carmen St. Jean
 *         CS 416 - Fall 2008, September 16, 2008
 */

//----------------------- imports ----------------------------------------------
import java.awt.*;
import javax.swing.*;
import java.util.*;

public class Program3 extends JFrame
{
   //------------------------- instance variables ------------------------------
   
   public Program3(int speed, int [] x, int [] y)
   {
      super("Program 3");
      this.setSize(700, 500);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.add(new MovePanel(this));
      this.setVisible(true); 
   } 
   
   //------------------------- main --------------------------------------------
   public static void main(String[] args)
   {
      int speed = 0;
      int [] xPoints = new xPoints[args.length];
      int [] yPoints = new yPoints[args.length];
      try
      {
         speed = Integer.parseInt(args[0]);
         int i = 1, n = 0;
         while (i < args.length)
         {
            xPoints[n] = Integer.parseInt(args[i]);
            yPoints[n] = Integer.parseInt(args[i+1]);
            i += 2;
            n++;
         }
      }
      catch(ArrayIndexOutOfBoundsException oob)
      {
         System.out.println("Error with command line arguments. Goodbye.");
         System.exit(0);
      }
      catch(NumberFormatException nfe)
      {
         System.out.println("Error with command line arguments. Goodbye.");
         System.exit(0);
      }
      catch(NoSuchElementException nse)
      {
         System.out.println("Error with command line arguments. Goodbye.");
         System.exit(0);
      }
      Program3 app = new Program3(speed, xPoints, yPoints);
   }
}