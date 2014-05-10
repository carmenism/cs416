
import java.awt.*;
import java.awt.Color;
import java.awt.Point;

public class RecordPlayer
{ 
   //---------------------- instance variables ------------------------------
   private int xLoc, yLoc;     // the location of the buggy
   
   // RecordPlayer display variables
   private Rectangle body;
   private int bodyWidth = 60, bodyLength = 60;
   private int bodyX = 0, bodyY = 0;
   private Color bodyColor;  
   
   private Rectangle line1;
   private int lineWidth = 4, lineLength = bodyLength - 10;
   private int line1X = 0, line1Y = 4;
   
   private Ellipse circle;
   private int circleWidth = 40, circleLength = 40;
   private int circleX = 10, circleY = 5;
   
   private Ellipse circle2;
   private int circle2Width = 15, circle2Length = 15;
   private int circle2X = 24, circle2Y = 18;
      
   //------------------------- constructor -----------------------------------
   public RecordPlayer ( Color theBodyColor ) 
   {
      bodyColor = theBodyColor;
      
      body = new Rectangle( bodyColor);
      body.setSize( bodyWidth, bodyLength);
      
      line1 = new Rectangle( Color.BLACK );
      line1.setSize( lineWidth, lineLength );
      
      circle = new Ellipse( Color.BLACK );
      circle.setSize( circleWidth, circleLength );
      
      circle2 = new Ellipse( Color.RED );
      circle2.setSize( circle2Width, circle2Length );
      
   }

   //-------------------- setLocation( Point ) --------------------------
   /**
    * move the rover to the specified Point location.
    */
   public void setLocation( Point p ) 
   {
      setLocation( p.x, p.y );
   }
   
     
   //------------------ setLocation( int, int ) --------------------------
   /**
    * move the rover to the specified x,y location.
    */
   public void setLocation( int x, int y ) 
   {
      xLoc = x; yLoc = y;
      body.setLocation( x + bodyX, y + bodyY );
      line1.setLocation( x + line1X, y + line1Y );
      circle.setLocation( x + circleX, y + circleY );
      circle2.setLocation( x + circle2X, y + circle2Y );
   }
   
   //----------------------- display( Graphics2D ) -------------------------
   /**
    * display - calls draw and fill awt methods (this is an rdb method).
    */
   public void display( java.awt.Graphics2D aBetterBrush )
   {
//     fill( aBetterBrush );
//     draw( aBetterBrush );
     
     body.display( aBetterBrush );
     line1.display( aBetterBrush );
     circle.display( aBetterBrush );
     circle2.display( aBetterBrush );

   }
}
