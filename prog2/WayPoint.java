/**
 * Buggy.java
 *    For Program2.
 * 
 * @author Carmen St. Jean
 *         CS 416 - Fall 2008, September 16, 2008
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
   }
   
   public int getX()
   {
      return x;
   }
   
   public int getY()
   {
      return y;
   }
}