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

public class RecordPlayer extends JComponent
{
   //---------------- instance variables ------------------------------
   // Components need to be accessed when displaying
   //
   private GEllipse    record;
   private GEllipse    recordMiddle;
   private GRectangle  box;
   private GLine       arm;
    
   private JFrame      frame;
   
   // -----------------------------------------------------------------
   /** 
    * Constructor for the SnowMan class. Arguments are the position.
    */
   public RecordPlayer( JFrame f, int x, int y )
   {
      // local "constant" variables are used to define the locations of each of the
      // components
      int    boxX      = 0,   boxY      = 0;
      int    boxSize   = 100;
      int    recordX   = 10,  recordY   = 10;
      int    recordSize = 80;
      int    recordMiddleX = 35, recordMiddleY = 35;
      int    recordMiddleSize = 30;      
      int    armX1 = 30, armY1 = 40, armX2 = 15, armY2 = 90;
      
      frame = f;
  
      arm = new GLine( frame, Color.WHITE );
      arm.setPoints( x + armX1, y + armY1, 
                         x + armX2, y + armY2 );
      arm.setLineWidth( 4 );
      
      recordMiddle = new GEllipse( frame, x + recordMiddleX, y + recordMiddleY );
      recordMiddle.setColor( Color.GREEN );
      recordMiddle.setSize( recordMiddleSize, recordMiddleSize );
      
      record = new GEllipse( frame, x + recordX, y + recordY );
      record.setColor( Color.BLACK );
      record.setSize( recordSize, recordSize );
       
      box = new GRectangle( frame, x + boxX, y + boxY );
      box.setSize( boxSize, boxSize );
      box.setColor( Color.BLUE );

   }
 
   /**/
}//End of Class RecordPlayer
