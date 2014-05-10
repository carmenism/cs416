/**
 * Buggy.java
 *    For Program2.
 * 
 * @author Carmen St. Jean
 *         CS 416 - Fall 2008, September 16, 2008
 */

//----------------------- imports ----------------------------------------------
import java.awt.*;
import javax.swing.*;

public class Buggy extends JComponent
{
   //------------------------- instance variables ------------------------------
   private GRectangle rollbar;
   private GRectangle body, cab, leftSeat, rightSeat;
   private GRectangle wheel1, wheel2, wheel3, wheel4;
   private GEllipse steeringwheel, leftLight, rightLight;
   private GLine bumper1, bumper2;
   private GStar star;
   private JFrame frame;
   
   //---------------------------------------------------------------------------
   //------------------------- constructor -------------------------------------
   
   /**
    * public Buggy(JFrame f, int x, int y, Color bodyColor)
    *    This constructor creates a buggy at the given location with the given
    *    color.
    */
   public Buggy(JFrame f, int x, int y, Color bodyColor)
   {
      
      frame = f;
      
      star = new GStar(frame, Color.WHITE, x + 20, y + 40);
      
      leftLight = new GEllipse( frame, x + 16, y );
      leftLight.setFillColor( Color.YELLOW );
      leftLight.setBorderColor( Color.BLACK );
      leftLight.setSize( 8, 5 );
      
      rightLight = new GEllipse( frame, x + 40, y );
      rightLight.setFillColor( Color.YELLOW );
      rightLight.setBorderColor( Color.BLACK );
      rightLight.setSize( 8, 5 );
       
      bumper1 = new GLine( frame, Color.GRAY );
      bumper1.setLineWidth( 5 );
      bumper1.setPoints( x + 18, y + 1,
                         x + 46, y + 1 );
      
      bumper2 = new GLine( frame, Color.GRAY );
      bumper2.setLineWidth( 5 );
      bumper2.setPoints( x + 15, y + 80,
                         x + 48, y + 80 );
            
      rollbar = new GRectangle( frame, x + 11, y + 59 );
      rollbar.setFillColor( bodyColor );
      rollbar.setBorderColor( Color.BLACK );
      rollbar.setSize( 42, 4 );
      
      leftSeat = new GRectangle( frame, x + 15, y + 39 );
      leftSeat.setFillColor( Color.YELLOW );
      leftSeat.setBorderColor( Color.BLACK );
      leftSeat.setSize( 15, 14 );
       
      rightSeat = new GRectangle( frame, x + 33, y + 39 );
      rightSeat.setFillColor( Color.YELLOW );
      rightSeat.setBorderColor( Color.BLACK );
      rightSeat.setSize( 15, 14 );
      
      steeringwheel = new GEllipse( frame, x + 17, y + 30 );
      steeringwheel.setFillColor( Color.CYAN );
      steeringwheel.setBorderColor( Color.BLACK );
      steeringwheel.setSize( 10, 5 );
      
      cab = new GRectangle( frame, x + 13, y + 24 );
      cab.setFillColor( Color.CYAN );
      cab.setBorderColor( Color.BLACK );
      cab.setSize( 37, 44 );
      
      body = new GRectangle( frame, x + 7, y + 2 );
      body.setFillColor( bodyColor );
      body.setBorderColor( Color.BLACK );
      body.setSize( 50, 79 );
      
      wheel1 = new GRectangle( frame, x, y + 10 );
      wheel1.setColor( Color.BLACK );
      wheel1.setSize( 10, 20 );
       
      wheel2 = new GRectangle( frame, x + 54, y + 10 );
      wheel2.setColor( Color.BLACK );
      wheel2.setSize( 10, 20 );
       
      wheel3 = new GRectangle( frame, x, y + 55 );
      wheel3.setColor( Color.BLACK );
      wheel3.setSize( 10, 20 );
       
      wheel4 = new GRectangle( frame, x + 54, y + 55 );
      wheel4.setColor( Color.BLACK );
      wheel4.setSize( 10, 20 );
   }
}
