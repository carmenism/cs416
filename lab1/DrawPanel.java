/** 
 * DrawPanel.java: Swing panel for AWT draw assignment.
 * 
 * This is a skeleton for the main display window for an awt application. 
 *      
 * @author rdb
 *
 */

import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;

public class DrawPanel extends JPanel
{ 
   //-------------------- instance variables ----------------------
   // declare here (among other things) the variables that reference
   //   AWT display objects (ellipses, rectangles, lines, etc.)
    
   //-------------------- instance variables ----------------------
   // Note: these variables do NOT need to be instance variables in wheels,
   //       but they will need to be when converting this program to awt.
   private Rectangle rect1,  rect2;
   private Ellipse   ell1,   ell2;
   private Line      line1,  line2;
   private Buggy     buggy1, buggy2;
   private RecordPlayer recordplayer1, recordplayer2;
  
   //------------- Constructor ---------------------------------
   /**
    * Create awt objects to be displayed.
    */
   public DrawPanel( String [ ] args ) 
   {
      super();
      // Create the objects you want to display using awt graphical
      // objects. For now, use the wheels-like "wrapper" classes,
      // Rectangle, Ellipse, and Line. The Rectangle and Ellipse wrapper
      // classes are minor variations of SmartRectangle and SmartEllipse
      // from the Sanders and van Dam text.
      //
      // References to the objects you create need to be saved in instance
      // variables
      
      rect1 = new Rectangle( Color.BLUE );
      rect1.setLocation( 100, 100 );
      rect1.setSize( 40, 40 );
      
      rect2 = new Rectangle( Color.RED );
      rect2.setLocation( 200, 200 );
      rect2.setSize( 20, 60 );
      
      line1 = new Line();
      line1.setColor( Color.BLACK );
      line1.setPoints( 120, 120, 210, 230 );
      
      ell1 = new Ellipse( Color.CYAN );
      ell1.setLocation( 10, 400  );
      ell1.setSize( 40, 10 );
      
      ell2 = new Ellipse( Color.MAGENTA );
      ell2.setLocation( 400, 400 );
      ell2.setSize( 30, 30 );
      
      line2 = new Line();
      line2.setColor( Color.BLACK );
      line2.setPoints( 25, 405, 415, 415 );  
      
      buggy1 = new Buggy( Color.RED );
      buggy1.setLocation( 450, 300 );
      buggy2 = new Buggy( Color.CYAN);
      buggy2.setLocation( 400, 20 );
      
      recordplayer1 = new RecordPlayer( Color.BLUE );
      recordplayer1.setLocation( 300, 300 );
      
      recordplayer2 = new RecordPlayer( Color.GREEN );
      recordplayer2.setLocation( 200, 300 );
          
   }  
   //------------- paintComponent ---------------------------------------
   /**
    * This method is called from the Java environment when it determines
    * that the JPanel display should be updated; you need to 
    * make appropriate calls here to re-draw the graphical images on
    * the display. Each object created in the constructor above should 
    * have its "fill" and/or "draw" methods invoked with a Graphics2D 
    * object. The Graphics object passed to paintComponent will be a 
    * a Graphics2D object, so it can be coerced to that type and
    * passed along to the "display" method of the objects you created.
    * 
    * Note that the "display" method is not an awt method, but a convenience
    * method defined by the "wrapper" classes. The display method usually
    * passes the graphical objects to both the Graphics2D.fill and
    * Graphics2D.draw methods, except in the case of Line graphical objects 
    * which cannot be "filled".
    */
   public void paintComponent( Graphics aBrush )
   {
      super.paintComponent( aBrush );
      Graphics2D newBrush = (Graphics2D) aBrush;  // coerce arg to Graphics2D
      
      // foreach instance variable, var, that references an awt graphics object:
      //    var.display( newBrush );
      
      rect1.display( newBrush );
      rect2.display( newBrush );
      ell1.display( newBrush );
      ell2.display( newBrush );
      line1.display( newBrush );
      line2.display( newBrush );
      buggy1.display( newBrush );
      buggy2.display( newBrush );
      recordplayer1.display( newBrush );
      recordplayer2.display( newBrush );
   }
}