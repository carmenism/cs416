/**
 * Star.java
 *    For Program2.
 * 
 * @author Carmen St. Jean
 *         CS 416 - Fall 2008, September 16, 2008
 */

//----------------------- imports ----------------------------------------------
import java.awt.*;
import javax.swing.*;

public class GStar extends GAreaShape
{
   //------------------------- instance variables ------------------------------
   private JFrame frame;
   private Polygon star;
   private int nPoints = 0, xLoc, yLoc;
   
   //---------------------------------------------------------------------------
   //------------------------- constructor -------------------------------------
   
   /**
    * public GStar(JFrame f, Color fillColor, int x, int y)
    *    This constructor creates a star at the given location.
    */
   public GStar(JFrame f, Color fillColor, int x, int y)
   {
      super( f, fillColor );
      xLoc = x;
      yLoc = y;
      int[] xPoints = { 20, 25, 40, 28, 35, 20,  5, 12,  0, 15 };
      int[] yPoints = {  0, 15, 15, 22, 38, 28, 38, 22, 15, 15 };
      setBorderColor( Color.BLACK );
      star = new Polygon(xPoints, yPoints, 10);
      updateComponent();
   }
   
   //---------------------------------------------------------------------------

   /**
    * private void updateComponent()
    *    This method uses the bounding box of the star to set the location
    *    and size for display.
    */
   private void updateComponent( )
   {
      Rectangle b = star.getBounds();
      super.setLocation(xLoc, yLoc);
      super.setSize( b.width * 2, b.height * 2);
   }   
   
   //---------------------------------------------------------------------------

   /**
    * public void paintComponent(java.awt.Graphics brush)
    *    Casts the brush into a 2D brush and sets the clip.
    */

   public void paintComponent(java.awt.Graphics brush)
   {
      super.paintComponent(brush);
      Graphics2D brush2 = (Graphics2D) brush;
      Rectangle b = this.getBounds();
      int w = (int) b.getWidth();
      int h = (int) b.getHeight();
      brush2.setClip( 0, 0, w + 5, h + 5 );
      brush.setColor( getFillColor() );
      brush.fillPolygon(star);
      brush.setColor( getBorderColor() );
      brush.drawPolygon(star);
   }
}
