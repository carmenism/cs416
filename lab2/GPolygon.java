/** 
 * RecordPlayer.java:
 * 
 * Displays a simple snow man using multiple Wheels Shapes.
 * The entire snowman is built in the constructor.
 * 
 * @author rdb
 * Created 9/11/07; derived from cs415 demo program, Start.java 
 */
import java.awt.*;
import javax.swing.*;

public class GPolygon extends GAreaShape
{
   //---------------- instance variables ------------------------------
   // Components need to be accessed when displaying

   private JFrame      frame;
   private Polygon polygon;
   
   
   // -----------------------------------------------------------------
   /** 
    * Constructor for the SnowMan class. Arguments are the position.
    */
   public GPolygon( JFrame f, Color c )
   {
     super( f, c );
     int[]  xPoints     = { 0, 90, 45 };
     int[]  yPoints     = { 0, 0,  60 };
     polygon = new Polygon(xPoints, yPoints, 3);
   }
   

   public void paintComponent( java.awt.Graphics brush )
   {
      super.paintComponent( brush );
      
      Graphics2D brush2 = (Graphics2D) brush;
      int w = getWidth();
      int h = getHeight();
      
      // The Graphics2 object has a "clipping" window that will ignore any
      // attempt to draw outside of it. The default window is the size of the
      // component. Unfortunately, the border of a Rectangle is 1 pixel longer
      // and 1 pixel wider than the interior AND the size of the JComponent is 
      // determined by the interior. So, create a clipping window that is 
      // big enough to hold the boundary and the interior -- nothing gets 
      // clipped away!
      //   
      brush2.setClip( 0, 0, w + 1, h + 1 );
      
      // note that the location of the Rectangle is the location of the
      // JComponent. All drawing is relative to this location. Hence, we
      // draw all ellipses at ( 0, 0 ) -- their location in their JComponent.

      brush2.setColor( getFillColor() );
  //    brush2.fillPolygon( 0, 0, w, h );
      brush2.setColor( getBorderColor() );
  //    brush2.drawPolygon( 0, 0, w, h );
   }
 
   /**/
}//End of Class GAreaShape
