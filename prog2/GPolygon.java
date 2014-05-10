/**
 * GPolygon.java
 *    For Program2.
 * 
 * @author Carmen St. Jean
 *         CS 416 - Fall 2008, September 16, 2008
 */

//----------------------- imports ----------------------------------------------
import java.awt.*;
import javax.swing.*;

public class GPolygon extends GAreaShape
{
   //------------------------- instance variables ------------------------------
   private Polygon polygon;
   private int [] xPoints;
   private int [] yPoints;
   private int nPoints;
   
   //---------------------------------------------------------------------------
   //------------------------- constructor -------------------------------------
   
   /**
    * public GPolygon(JFrame f, int [] xP, int [] yP, int n)
    *    This constructor creates a polygon with n points using the two arrays
    *    of integers as the point coordinates.
    */
   public GPolygon(JFrame f, int [] xP, int [] yP, int n)
   {
      super( f, Color.BLUE );
      setBorderColor( Color.BLACK );
      xPoints = xP;
      yPoints = yP;
      nPoints = n;
      polygon = new Polygon(xPoints, yPoints, nPoints);
      updateComponent();
   }
   
   //---------------------------------------------------------------------------
   
   /**
    * public GPolygon(JFrame f, Color fillColor)
    *    This constructor creates an empty polygon with the given color.  Points
    *    must be added later with the addPoint(int x, int y) method.
    */
   public GPolygon(JFrame f, Color fillColor)
   {
      super( f, fillColor );  
      setBorderColor( Color.BLACK );
      polygon = new Polygon();
      updateComponent();
   }
   
   //---------------------------------------------------------------------------
   //------------------------- methods -----------------------------------------

   /**
    * public void addPoint(int x, int y)
    *    This method adds a vertex to the polygon at the given location.
    */
   public void addPoint(int x, int y)
   {
      polygon.addPoint(x, y);
      updateComponent();
   }
   
   //---------------------------------------------------------------------------

   /**
    * private void updateComponent()
    *    This method uses the bounding box of the polygon to set the location
    *    and size for display.
    */
   private void updateComponent()
   {
      Rectangle b = polygon.getBounds();
      super.setLocation(0, 0);
      //super.setLocation(b.x, b.y);
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
      brush.fillPolygon(polygon);
      brush.setColor( getBorderColor() );
      brush.drawPolygon(polygon);
   }
}
