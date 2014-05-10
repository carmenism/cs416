/**
 * Triangle.java
 *    For Program2.
 * 
 * @author Carmen St. Jean
 *         CS 416 - Fall 2008, September 16, 2008
 */

//----------------------- imports ----------------------------------------------
import java.awt.*;
import javax.swing.*;

public class GTriangle extends GAreaShape
{
   //------------------------- instance variables ------------------------------
   private JFrame frame;
   private Polygon triangle;
   private int [] xPoints;
   private int [] yPoints;
   private int nPoints = 0;
   
   //---------------------------------------------------------------------------
   //------------------------- constructor -------------------------------------
   
   /**
    * public GTriangle(JFrame f, int [] xP, int [] yP)
    *    This constructor creates a triangle with 3 points using the two arrays
    *    of integers as the point coordinates.
    */
   public GTriangle(JFrame f, int [] xP, int [] yP)
   {
      super( f, Color.RED );
      setBorderColor( Color.BLACK );
      xPoints = xP;
      yPoints = yP;
      triangle = new Polygon(xPoints, yPoints, 3);
      updateComponent();
   }
   
   //---------------------------------------------------------------------------
   
   /**
    * public GTriangle(JFrame f, Color fillColor)
    *    This constructor creates an empty triangle with the given color.
    *    Points must be added later with the addPoint(int x, int y) method.
    */
   public GTriangle(JFrame f, Color fillColor)
   {
      super( f, fillColor );  
      setBorderColor( Color.BLACK );
      triangle = new Polygon();
      updateComponent();
   }
    
   //---------------------------------------------------------------------------
   //------------------------- methods -----------------------------------------

   /**
    * public void addPoint(int x, int y)
    *    This method adds a vertex to the triangle at the given location.  This
    *    method will not allow more than three points to be added to the
    *    triangle.
    */
   public void addPoint(int x, int y)
   {
      if (nPoints < 3)
      {
         triangle.addPoint(x, y);
         updateComponent();
      }
      nPoints++;
   }
      
   //---------------------------------------------------------------------------

   /**
    * private void updateComponent()
    *    This method uses the bounding box of the triangle to set the location
    *    and size for display.
    */
   private void updateComponent( )
   {
      Rectangle b = triangle.getBounds();
      super.setLocation(0, 0);
      super.setLocation(b.x, b.y);
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
      brush.fillPolygon(triangle);
      brush.setColor( getBorderColor() );
      brush.drawPolygon(triangle);
   }
}
