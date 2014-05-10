/* 
 * Buggy.java -- a wheels program
 *   
 * Displays a Martian Buggy based on colors given
 * at construction time.  The Buggy has a rectangular body, two wheel
 * tracks on either side, a radar with antenna in the middle near the
 * back, and a battery pack off-center toward the front.
 * 
 * Derived from a program originally written by Matt Plumlee, then modified
 * by Mark Bochert.
 * 
 * This version modified by Dan Bergeron to make it closer to what is
 * needed for Program 2 in Spring 2008 CS416. In the original version, the
 * traversal responsibility was in the main application which called 
 * Buggy.moveTo for each new position of the Buggy. In this version, the Buggy
 * class is given the entire path to traverse and does it's own updating.
 * 
 * @author Matt Plumlee, Mark Bochert, Dan Bergeron
 * 
 */

import wheelsunh.users.*;
import java.awt.Color;
import java.awt.Point;
import java.awt.*;
import javax.swing.*;
import java.util.*;

public class GBuggy extends JComponent implements Mover
{ 
   //--------------------- instance variables ----------------------------
   private int xLoc, yLoc;     // the location of the Buggy

   private Container _parent;     // region in which motion can occur
   
   private int  _dX;           // steps sizes for each move
   private int  _dY;
   
   //  xmin,ymin of all components (also the composite location)
   private int  _xLoc = Integer.MAX_VALUE;  
   private int  _yLoc = Integer.MAX_VALUE;
   
   //  save xmax, ymax of all components (used to get composite size)
   private int  _xMax = Integer.MIN_VALUE;
   private int  _yMax = Integer.MIN_VALUE;
   
   private Vector<GShape>  _components; // components of this composite
   

   
   // Buggy display variables
   private GRectangle body;
   private int bodyWidth = 40, bodyLength = 60;
   private int bodyX = 4, bodyY = 0;
   private Color bodyColor;  
   
   private GRectangle tracksLeft, tracksRight;
   private int trackWidth = 4, trackLength = bodyLength - 8;
   private int lTrackX = 0, lTrackY = 4;
   private int rTrackX = bodyX + bodyWidth, rTrackY = 4;
   
   private GEllipse radar;
   private int radarWidth = 20, radarLength = 8;
   private int radarX = 14, radarY = 4;
   private GLine radarAntenna;
   private int antennaX = 24, antennaY = 10, antennaLength = 5;
   
   private GRectangle battery;
   private int batteryWidth = 15, batteryLength = 30;   
   private int batteryX = 9, batteryY = 20;
   private Color batteryColor;  
         
   //------------------------- Buggy constructor ------------------------
   /**
    * build a Buggy object 
    */
   public GBuggy ( JPanel jp, JFrame frame, int x, int y )
   {
      _parent = jp;
      _components = new Vector<GShape>();
      
      super.setLocation( x, y );
      
      bodyColor = Color.BLUE;
      batteryColor = Color.RED;
            
      battery = new GRectangle( frame, batteryColor);
      battery.setSize( batteryWidth, batteryLength );
      battery.setLocation( x + batteryX, y + batteryY );
      updateComposite( battery );
      _components.add( battery );
      
      radarAntenna = new GLine( frame );
      radarAntenna.setPoints( x + antennaX, y + antennaY, 
                              x + antennaX, y + antennaY + antennaLength );
      radarAntenna.setColor( Color.BLACK );
      radarAntenna.setThickness( 2 );
      updateComposite( radarAntenna );
      _components.add( radarAntenna );
      
      radar = new GEllipse( frame, Color.GRAY );
      radar.setSize( radarWidth, radarLength );
      radar.setLocation( x + radarX, y + radarY );
      updateComposite( radar );
      _components.add( radar );
      
      tracksLeft = new GRectangle( frame, Color.BLACK );
      tracksLeft.setSize( trackWidth, trackLength );
      tracksLeft.setLocation( x + lTrackX, y + lTrackY );
      updateComposite( tracksLeft );
      _components.add( tracksLeft );
      tracksRight = new GRectangle( frame, Color.BLACK );
      tracksRight.setSize( trackWidth, trackLength );
      tracksRight.setLocation( x + rTrackX, y + rTrackY );
      updateComposite( tracksRight );
      _components.add( tracksRight );
      
      body = new GRectangle( frame, bodyColor);
      body.setSize( bodyWidth, bodyLength );
      body.setLocation( x + bodyX, y + bodyY );
      updateComposite( body );
      _components.add( body );
   }
   
   //---------------------- updateComposite -------------------------------  
   /**
    * update the bounds of the composite based on a new component.
    */
   public void updateComposite( GShape comp )
   {
      if ( comp.getX() < _xLoc )
         _xLoc = comp.getX();
      if ( comp.getY() < _yLoc )
         _yLoc = comp.getY();
      
      int cmaxX = comp.getX() + comp.getWidth();
      int cmaxY = comp.getY() + comp.getHeight();
   
      if ( cmaxX > _xMax )
         _xMax = cmaxX;
      if ( cmaxY > _yMax )
         _yMax = cmaxY;
    }
   
   //------------------------ setLocation( Point ) ----------------------
   /**
    * display the Buggy at the specified Point
    */
   public void setLocation(Point p) 
   {
      setLocation( p.x, p.y );
   }
   //------------------------ setLocation( int, int ) ----------------------
   /**
    * display the Buggy at the specified x,y location
    */   
   public void setLocation( int x, int y ) 
   {
      xLoc = x;
      yLoc = y;
      super.setLocation( x, y );
      body.setLocation( x + bodyX, y + bodyY );
      tracksLeft.setLocation( x + lTrackX, y + lTrackY );
      tracksRight.setLocation( x + rTrackX, y + rTrackY );
      radar.setLocation( x + radarX, y + radarY );
      radarAntenna.setPoints( x + antennaX, y + antennaY, 
                             x + antennaX, y + antennaY + antennaLength );
      battery.setLocation( x + batteryX, y + batteryY );
   }
   
   //--------------------------- recharge( boolean ) --------------------------
   /**
    * put the Buggy in recharge mode (true) or take it out of recharge (false)
    */
   public void rechargeMode( boolean recharge ) 
   {
      if ( recharge )
         battery.setColor( Color.RED );
      else
         battery.setColor( batteryColor );
   }
   
      //----------------------- setMove( int, int ) ------------------------
   /**
    * set the move increments
    */
   public void setMove( int dx, int dy )
   {
      _dX = dx;
      _dY = dy;
   }

   //---------------------- move() -----------------------------------------
   /**
    * move the SnowMan by the specified incremental steps.
    */
   public void move() 
   {
      // get the expected next location
      int nextX = this.getX() + _dX;
      int nextY = this.getY() + _dY;
      
      // but check if we have hit a boundary of the panel we're in
      if ( nextX <= this.getMinBoundX())    // are we at the left edge
      {                                     
         _dX *= -1;                         // if so, reverse x increment
         nextX = this.getMinBoundX();       // and set us at the edge
      }
      else if ( nextX >= this.getMaxBoundX()) // have we hit the right edge
      {
         _dX *= -1;
         nextX = this.getMaxBoundX();
      }
      if ( nextY <= this.getMinBoundY())      // at the top?
      {
         _dY *= -1;
         nextY  = this.getMinBoundY();
      }
      else if ( nextY > this.getMaxBoundY())  // at the bottom?
      {
         _dY *= -1;
         nextY = this.getMaxBoundY();
      }
      this.setLocation( nextX, nextY );       // update location
   }
   //------------------ boundary specifications ------------------------------
   /**
    * get the min valid x location value
    */
   public int getMinBoundX() 
   {
      return _parent.getX();
   /**
    * get the min valid y location value
    */
   }
   public int getMinBoundY() 
   {
      return _parent.getY();
   }
   /**
    * get the max valid x location value
    */
   public int getMaxBoundX() 
   {
     return _parent.getX() + _parent.getWidth() - bodyWidth - 2*trackWidth;
   }
   /**
    * get the max valid y location value
    */
   public int getMaxBoundY() 
   {
      return _parent.getY() + _parent.getHeight() - bodyLength;
   }
}