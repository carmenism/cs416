/**
 * Paddles.java
 *     For Program4.java
 * 
 * @author Carmen St. Jean
 *         CS 416 - Fall 2008, September 29, 2008
 */

//----------------------- imports ----------------------------------------------
import java.awt.*;
import javax.swing.*;
import java.util.*;

public class Paddle extends GRectangle
{
   //------------------------- instance variables ------------------------------
   private int xLocation, yLocation, score = 0;
   private Point lastMousePosition;
   
   //---------------------------------------------------------------------------
   //------------------------- constructor -------------------------------------

   /**
    * public Paddle(Container parent, int x, int y)
    *    Another wheels-like constructor which creates a paddle at the given
    *    location.
    */
   public Paddle(Container parent, int x, int y)
   {
      super(parent, x, y);
      xLocation = x;
      yLocation = y;
      setSize(15, 50);
      setColor(Color.BLACK);
      parent.repaint();
   }
}