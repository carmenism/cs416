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
import java.awt.event.*;
import javax.swing.*;

abstract public class GShape extends JComponent 
{
   //---------------- instance variables ------------------------
   private   Color     _lineColor;
   private   int       _lineWidth = 1;
   private Point       _lastMouse;

   protected Container _parent;
   protected int       _borderSize = 5;  // extra JComponent size > object size

   //--------------------  constructors ---------------------------
   /**
    * Constructor from GShape
    */
   public GShape( Container parent, Color aColor )
   { 
      _lineColor = aColor;
      _parent    = parent;
      _parent.add( this );
   }
   /**
    * Another wheels-like constructor
    */
   public GShape( Container parent, int x, int y )
   {
      this( parent, Color.RED );
      this.setLocation( x, y );
   }

   //++++++++++++++++++ wheels-like convenience methods +++++++++++++++++++
   //----------------------- setBorderColor( Color ) --------------------
   /**
    * setFrameColor -- a wheels method
    */
   public void setFrameColor( Color aColor ) 
   {
      _lineColor = aColor;
   }
   /**
    * setBorderColor -- a wheels -like method
    */
   public void setBorderColor( Color aColor ) 
   {
      _lineColor = aColor;
   }
   /**
    * setLineColor -- a wheels -like method
    */
   public void setLineColor( Color aColor ) 
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
   //------------------- setLocation( Point ) -----------------------
   /**
    * setLocation( Point ) -- a wheels method
    */
   public void setLocation( Point p ) 
   {
      setLocation( p.x, p.y );
   }
   //------------------- setSize( int, int ) -----------------------
   /**
    * setSize( int, int ) -- a wheels method
    */
   public void setSize( int x, int y ) 
   {
      // make box a bit bigger to allow for the boundary being drawn with
      //   lines upto 3 pixel wide
      super.setSize( x + _borderSize, y + _borderSize );
   }
   //++++++++++++++++++++++++ accessor methods +++++++++++++++++++++++++++++
   //------------------- getSize() -----------------------
   /**
    * getSize() -- a JCompoent method
    */
   public Dimension getSize( ) 
   {
      // Since we made the JComponent box bigger than the shape
      //   we need to override the JComponent.getSize() method
      //   to return the size of the shape to deduct the extra
      return new Dimension( this.getWidth(), this.getHeight() );
   }
   //------------------- getWidth() -----------------------
   /**
    * getWidth() -- a JCompoent method
    */
   public int getWidth( ) 
   {
      // Since we made the JComponent box bigger than the shape
      //   we need to override the JComponent.getSize() method
      //   to return the size of the shape to deduct the extra
      return super.getWidth() - _borderSize;
   }
   //------------------- getHeight() -----------------------
   /**
    * getHeight() -- a JCompoent method
    */
   public int getHeight( ) 
   {
      // Since we made the JComponent box bigger than the shape
      //   we need to override the JComponent.getSize() method
      //   to return the size of the shape to deduct the extra
      return super.getHeight() - _borderSize;
   }
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
   //----------------------- moveBy( int, int ) -------------------------------
   /**
    * moveBy( dx, dy ) -- move the location by delta
    */
   public void moveBy( int deltaX, int deltaY ) 
   {
      this.repaint( this.getBounds() );
      setLocation( getX() + deltaX, getY() + deltaY );
      this.repaint( this.getBounds() );
   }
}
