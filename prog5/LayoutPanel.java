/**
 * LayoutPanel.java
 *     For Program5.java
 * 
 * @author Carmen St. Jean
 *         CS 416 - Fall 2008, October 7, 2008
 */

//----------------------- imports ----------------------------------------------
import java.awt.*;
import javax.swing.*;

public class LayoutPanel extends JPanel
{
   //------------------------- instance variables ------------------------------
   private final int PANEL_WIDTH = 600;
   private final int PANEL_HEIGHT = 600;
   private JFrame _frame;
   private Graphics brush; 
   private int _order = 1;
   
   //---------------------------------------------------------------------------
   //------------------------- constructor -------------------------------------
   
   public LayoutPanel( ) 
   {
      setBackground (Color.white);
      setPreferredSize (new Dimension(PANEL_WIDTH, PANEL_HEIGHT));      
   }
   
   //---------------------------------------------------------------------------
   //------------------------- methods -----------------------------------------

   /**
    * public void drawSnowflake(int order, double x1, double y1,
    *                                      double x2, double y2)
    *    This is the actual recursive function, which determines what lines
    *    should be drawn and draws them.  The base case is an order of one.
    */
   public void drawSnowflake(int order, double x1, double y1,
                                        double x2, double y2)
   {     
      if (order == 1)
      {
         brush.drawLine ((int)x1, (int)y1, (int)x2, (int)y2);
      }
      else
      {
         double midX, midY, x3, y3, x4, y4, x5, y5, base, height;
         
         midX = x2 + (x1 - x2) / 2;
         midY = y2 + (y1 - y2) / 2;
         x3 = x2 + (x1 - x2) / 3;
         y3 = y2 + (y1 - y2) / 3;
         x5 = x2 + 2 * (x1 - x2) / 3;
         y5 = y2 + 2 * (y1 - y2) / 3;
         
         base = Math.sqrt( (y5 - y3)*(y5 - y3) + (x5 - x3)*(x5 - x3) );
         height = Math.sqrt(3) * base / 2;
         
         x4 = midX - height/base * (y1-y2) / 3;  // bottom of T
         y4 = midY - height/base * (x2-x1) / 3;
         
         drawSnowflake(order - 1, x1, y1, x5, y5);
         drawSnowflake(order - 1, x5, y5, x4, y4); 
         drawSnowflake(order - 1, x4, y4, x3, y3);
         drawSnowflake(order - 1, x3, y3, x2, y2);
      }
   }
   
   //---------------------------------------------------------------------------
   
   /**
    * public void paintComponent (Graphics brush)
    *    This makes the initial call to the drawSnowflake method.  The initial
    *    points can be set here.
    */
   public void paintComponent (Graphics brush)
   {
      super.paintComponent (brush);
      brush.setColor (Color.black);
      this.brush = brush;
      
      double x1 = this.getWidth()/2;
      double y1 = 365 - 3*Math.sqrt(3)*this.getWidth()/10;
      double x2 = 4*this.getWidth()/5;
      double y2 = 365;
      double x3 = this.getWidth()/5;
      double y3 = 365;
      
      drawSnowflake(_order, x1, y1, x2, y2);
      drawSnowflake(_order, x2, y2, x3, y3);
      drawSnowflake(_order, x3, y3, x1, y1);
   }
   
   //---------------------------------------------------------------------------

   /**
    * public void setOrder(int newOrder)
    *    This method sets the order for the fractal, then repaints.
    */
   public void setOrder(int newOrder)
   {
      _order = newOrder;
      this.repaint();
   }
}   