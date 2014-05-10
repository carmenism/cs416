/**
 * WayPoint.java
 *    For Program3.
 * 
 * @author Carmen St. Jean
 *         CS 416 - Fall 2008, September 23, 2008
 */

//----------------------- imports ----------------------------------------------
import java.awt.*;
import javax.swing.*;

public class WayPoint extends JComponent
{
   //------------------------- instance variables ------------------------------
   private GLine horizontal, vertical;
   private JFrame frame;
   private int x, y;
   
   //---------------------------------------------------------------------------
   //------------------------- constructor -------------------------------------
 
   /**
    * public WayPoint(JFrame f, int xLoc, int yLoc)
    *    Creates a way point at the given location on the given frame.
    */
   public WayPoint(JFrame f, int xLoc, int yLoc)
   {
      frame = f;
      x = xLoc;
      y = yLoc;
      horizontal = new GLine( frame, Color.BLUE );
      horizontal.setLineWidth( 3 );
      horizontal.setPoints( x - 6, y,
                            x + 6, y );
      vertical = new GLine( frame, Color.BLUE );
      vertical.setLineWidth( 3 );
      vertical.setPoints( x, y - 6,
                          x, y + 6 );
      System.out.println("new point at " + x + " " + y);
   }
   
   //---------------------------------------------------------------------------
   //------------------------- methods -----------------------------------------

   /**
    * public int getX()
    *    Returns the x-location of this way point.
    */
   public int getX()
   {
      return x;
   }
   
   //---------------------------------------------------------------------------
  
   /**
    * public int getY()
    *    Returns the y-location of this way point.
    */
   public int getY()
   {
      return y;
   }
}