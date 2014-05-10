/**
 * Seeker.java
 *    For Program3.
 * 
 * @author Carmen St. Jean
 *         CS 416 - Fall 2008, September 23, 2008
 */

//----------------------- imports ----------------------------------------------
import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class Seeker extends GEllipse
{
   //------------------------- instance variables ------------------------------
   private Container _panel; // peer object( and container )
   private JFrame    _frame; 
   private Timer     _timer;
   
   //---------------------------------------------------------------------------
   //------------------------- constructor -------------------------------------

   /**
    * public Seeker(Color aColor, JFrame aFrame)
    *    Creates a seeker with the given color on the given frame.
    */
   public Seeker(Color aColor, JFrame aFrame)
   {
      super( aFrame, aColor );
      super.setFillColor(new Color(255, 255, 255, 100));
      super.setBorderColor(aColor);
      _frame   = aFrame;
      _panel   = aFrame.getContentPane();
   }
   
   //---------------------------------------------------------------------------
   //------------------------- methods -----------------------------------------

   /**
    * public void setLocation(int x, int y)
    *    Overrides and utilizes the super.setLocation(x, y) method so that the
    *    seeker will be placed centered on (x, y).
    */
   public void setLocation(int x, int y)
   {
      super.setLocation(x - 9, y - 9);
   }
}

