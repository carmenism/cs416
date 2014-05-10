/**
 * GShape.java -- a convenience class for simplifying access to awt graphics
 *    objects supported by the Graphics AWT class.
 *    The class extends JComponent, but the interface is patterned
 *    after that of wheels. This is a frame class for
 *       GAreaShape
 *         GEllipse 
 *         GRectangle
 *       GLine
 *    Child classes must override paintComponent.
 * 
 * @author (of the modifications) rdb
 * January 2008
 */

import java.awt.geom.*;
import java.awt.*;
import javax.swing.*;

abstract public class GShape extends JComponent 
{
   //---------------- instance variables ------------------------
   private Color  _lineColor;
   private int    _lineWidth = 1;
   private JFrame _frame;

   //--------------------  constructors ---------------------------
   /**
    * Constructor from GShape
    */
   public GShape( JFrame frame, Color aColor )
   { 
      _lineColor = aColor;
      _frame       = frame;
      //_frame.getLayeredPane().add( this, new Integer( 1 ) );
      _frame.add( this );
   }
   /**
    * Another wheels-like constructor
    */
   public GShape( JFrame frame, int x, int y )
   {
      this( frame, Color.RED );
      this.setLocation( x, y );
   }

   //++++++++++++++++++ wheels-like convenience methods +++++++++++++++++++
   //----------------------- setBorderColor( Color ) --------------------
   /**
    * setBorderColor -- a wheels method
    */
   public void setBorderColor( Color aColor ) 
   {
      _lineColor = aColor;
   }
   //----------------------- setColor( Color ) -----------------------
   /**
    * setColor -- a wheels method
    */
   public void setColor( Color aColor ) 
   {
      _lineColor = aColor;
   }
   //----------------------- setThickness( int ) -------------------------
   /**
    * setThickness -- a wheels method
    */
   public void setThickness( int w ) 
   {
      _lineWidth = w;
   }
   //----------------------- setLineWidth( int ) --------------------------
   /**
    * setLineWidth -- same as setThickness, but I like the name better
    */
   public void setLineWidth( int w ) 
   {
      _lineWidth = w;
   }
   //++++++++++++++++++++++++ accessor methods +++++++++++++++++++++++++++++
    //----------------------- getColor() -----------------------
   /**
    * getColor -- a wheels method
    */
   public Color getColor() 
   {
      return _lineColor;
   }
   //----------------------- getThickness() -------------------------
   /**
    * getThickness -- a wheels method
    */
   public int getThickness() 
   {
      return _lineWidth;
   }
   //----------------------- getLineWidth( ) --------------------------
   /**
    * getLineWidth -- same as getThickness, but I like the name better
    */
   public int getLineWidth() 
   {
      return _lineWidth;
   }
   //----------------------- getXLocation() -------------------------------
   /**
    * getXLocation() - a wheels method
    *                  return int value of x location
    */
   public int getXLocation()
   {
      return this.getX();
   }
   //----------------------- getYLocation() -------------------------------
   /**
    * getYLocation() - a wheels method
    *                  return int value of y location
    */
   public int getYLocation()
   {
      return this.getY();
   }
   //------------------- setLocation( Point ) -----------------------
   /**
    * setLocation( Point ) -- a wheels method
    */
   public void setLocation( Point p ) 
   {
      setLocation( p.x, p.y );
   }
   //----------------------- moveBy( int, int ) -------------------------------
   /**
    * moveBy( dx, dy ) -- move the location by delta
    */
   public void moveBy( int deltaX, int deltaY ) 
   {
      setLocation( this.getX() + deltaX, this.getY() + deltaY );
   }
}
