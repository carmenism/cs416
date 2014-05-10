/**
 * GRectangle.java-- a convenience class for simplifying access to awt graphics
 *    objects supported by the Graphics AWT class. 
 *    The class extends JComponent, but the interface is patterned
 *    after that of wheels. 
 * 
 * @author (of the modifications) rdb
 * January 2008
 */

import java.awt.geom.*;
import java.awt.*;
import javax.swing.*;

public class GRectangle extends GAreaShape 
{
   //---------------- instance variables ------------------------
   //--------------------  constructors ---------------------------
   /**
    * Constructor from GRectangle
    */
   public GRectangle( Container parent, Color aColor )
   { 
      super( parent, aColor );
   }
   /**
    * Another wheels-like constructor
    */
   public GRectangle( Container parent, int x, int y )
   {
      super( parent, x, y );
   }

   //----------------------- paintComponent( Graphics2D ) -------------------------
   /**
    * paintComponent - calls draw and fill awt methods (this is an rdb method).
    */
   public void paintComponent( java.awt.Graphics brush )
   {
      super.paintComponent( brush );
      
      Graphics2D brush2 = (Graphics2D) brush;
      int w = getWidth();
      int h = getHeight();
      int lw = getLineWidth();
      
      // The Graphics2 object has a "clipping" window that will ignore any
      // attempt to draw outside of it. The default window is the size of the
      // component. Unfortunately, the border of a Rectangle is 1 pixel longer
      // and 1 pixel wider than the interior AND the size of the JComponent is 
      // determined by the interior. So, create a clipping window that is 
      // big enough to hold the boundary and the interior -- nothing gets 
      // clipped away! Actually, need enough extra to match the line width.
      //   
      brush2.setClip( 0, 0, w + _borderSize, h + _borderSize );
      
      // note that the location of the Rectangle is the location of the
      // JComponent. All drawing is relative to this location. Hence, we
      // draw all ellipses at ( 0, 0 ) -- their location in their JComponent.

      brush2.setColor( getFillColor() );
      brush2.fillRect( 0, 0, w, h );
      brush2.setStroke( new BasicStroke( lw ));
      brush2.setColor( getBorderColor() );
      brush2.drawRect( 0, 0, w, h );
   }   
}
