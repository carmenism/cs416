
import java.awt.*;
import java.awt.Color;
import java.awt.Point;

public class Buggy
{ 
   //---------------------- instance variables ------------------------------
   private int xLoc, yLoc;     // the location of the buggy
   
   // Buggy display variables
   private Rectangle body;
   private int bodyWidth = 40, bodyLength = 60;
   private int bodyX = 4, bodyY = 0;
   private Color bodyColor;  
   
   private Rectangle tracksLeft, tracksRight;
   private int trackWidth = 4, trackLength = bodyLength - 8;
   private int lTrackX = 0, lTrackY = 4;
   private int rTrackX = bodyX + bodyWidth, rTrackY = 4;
   
   private Ellipse radar;
   private int radarWidth = 20, radarLength = 8;
   private int radarX = 14, radarY = 4;
   private Line radarAntenna;
   private int antennaX = 24, antennaY = 10, antennaLength = 5;
   
   private Rectangle battery;
   private int batteryWidth = 15, batteryLength = 30;   
   private int batteryX = 9, batteryY = 20;
   private Color batteryColor;  
      
   //------------------------- constructor -----------------------------------
   public Buggy ( Color theBodyColor ) 
   {
      bodyColor = theBodyColor;
      batteryColor = Color.orange;
      
      body = new Rectangle( bodyColor);
      body.setSize( bodyWidth, bodyLength);
      
      tracksLeft = new Rectangle( Color.BLACK );
      tracksLeft.setSize( trackWidth, trackLength );
      tracksRight = new Rectangle( Color.BLACK );
      tracksRight.setSize( trackWidth, trackLength );
      
      radar = new Ellipse( Color.GRAY );
      radar.setSize( radarWidth, radarLength );
      radarAntenna = new Line();
      radarAntenna.setColor( Color.BLACK );
      radarAntenna.setThickness( 2 );
      
      battery = new Rectangle( batteryColor );
      battery.setSize( batteryWidth, batteryLength );
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
      tracksLeft.setLocation( x + lTrackX, y + lTrackY );
      tracksRight.setLocation( x + rTrackX, y + rTrackY );
      radar.setLocation( x + radarX, y + radarY );
      radarAntenna.setPoints( x + antennaX, y + antennaY, 
                             x + antennaX, y + antennaY + antennaLength);
      battery.setLocation( x + batteryX, y + batteryY );
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
     tracksLeft.display( aBetterBrush );
     tracksRight.display( aBetterBrush );
     radar.display( aBetterBrush );
     battery.display( aBetterBrush );
     radarAntenna.display( aBetterBrush );
   }
}
