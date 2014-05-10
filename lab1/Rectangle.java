/**
 * Rectangle.java
 * 
 * Extends Java's Rectangle2D.Double class, adding the capabilities to
 * set color, rotation, location, and size, to move to a specified
 * location, and to display itself on a panel.
 * 
 * This class is a modification of the SmartRectangle class from
 * Sanders and van Dam, Object-Oriented Programming in Java, Chapter 7.
 * 
 * The modifications include:
 * 1. reformatting to match my style
 * 2. more wheels-like functions--simplifies conversion of wheels programs: 
 *       getXLocation(), getYLocation(), setColor, setLineWidth, setThickness
 * 3. added display method that then calls the awt fill and draw methods.
 * 
 * @author (of the modifications) rdb
 * January 2008
 */

import java.awt.geom.*;
import java.awt.*;

public class Rectangle extends java.awt.geom.Rectangle2D.Double 
{
   //---------------- instance variables ------------------------
   private Color _borderColor;
   private Color _fillColor;
   private int   _rotation;
   private int   _lineWidth = 2;
   
   //--------------------  constructor ---------------------------
   /**
    * Constructor from SmartRectangle
    */
   public Rectangle( Color aColor )
   { 
      _borderColor = aColor;
      _fillColor   = aColor;     // solid color to start
      _rotation    = 0;           // no rotation for now
   }
   /**
    * Another wheels-like constructor -- uses double rather than int
    */
   public Rectangle( double x, double y )
   {
      this( Color.RED );
      this.setLocation( x, y );
   }   
   
   //--------------------- wheels-like convenience methods -----------------
   //++++++++++++++++++ wheels-like convenience methods +++++++++++++++++++
   //----------------------- setBorderColor( Color ) --------------------
   /**
    * setBorderColor -- a wheels method
    */
   public void setBorderColor( Color aColor ) 
   {
      _borderColor = aColor;
   }
   //----------------------- setFillColor( Color ) --------------------
   /**
    * setFillColor -- a wheels method
    */
   public void setFillColor( Color aColor ) 
   {
      _fillColor = aColor;
   }
   //----------------------- setColor( Color ) -----------------------
   /**
    * setColor -- a wheels method
    */
   public void setColor( Color aColor ) 
   {
      _fillColor   = aColor;
      _borderColor = aColor;
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
   //----------------------- setRotation( int ) --------------------------
    /**
     * setRotation -- a wheels method
     */
   public void setRotation( int aRotation ) 
   {
      _rotation = aRotation;
   }
   //----------------------- getXLocation() -------------------------------
   /**
    * getXLocation() - a wheels method
    *                  return int value of x location
    */
   public int getXLocation()
   {
      return (int) this.getX();
   }
   //----------------------- getYLocation() -------------------------------
   /**
    * getYLocation() - a wheels method
    *                  return int value of y location
    */
   public int getYLocation()
   {
      return (int) this.getY();
   }
   //------------------- setLocation( Point ) -----------------------
   /**
    * setLocation( Point ) -- a wheels method
    */
   public void setLocation( Point p ) 
   {
      setLocation( p.x, p.y );
   }
   //------------------- setLocation( double, double ) -----------------------
   /**
    * setLocation( double, double ) -- wheels-like
    *                                  uses double rather than int
    */
   public void setLocation( double x, double y ) 
   {
      this.setFrame( x, y, this.getWidth(), this.getHeight() );
   }
   //----------------------- setSize( int, int ) ---------------------------
   /**
    * setSize( int, int )  - a wheels method
    */
   public void setSize (int aWidth, int aHeight) 
   {
      this.setFrame( this.getX(), this.getY(), aWidth, aHeight );
   }
   //----------------------- move( int, int ) -------------------------------
   /**
    * move( dx, dy ) -- move the location by delta
    */
   public void move( int aChangeInX, int aChangeInY ) 
   {
      this.setFrame( (int)this.getX() + aChangeInX,
                    (int)this.getY() + aChangeInY,
                    this.getWidth(),
                    this.getHeight());
   }
   //----------------------- display( Graphics2D ) -------------------------
   /**
    * display - calls draw and fill awt methods (this is an rdb method).
    */
   public void display( java.awt.Graphics2D aBetterBrush )
   {
      fill( aBetterBrush );
      draw( aBetterBrush );
   }
   //-------------------------- fill( Graphics2D ) ------------------------
   /**
    * fill - overrides parent method
    */
   public void fill( java.awt.Graphics2D aBetterBrush )
   {
      Color savedColor = aBetterBrush.getColor();
      aBetterBrush.setColor( _fillColor );
      aBetterBrush.fill( this );
      aBetterBrush.setColor( savedColor );
   }
   //--------------------------- draw( Graphics2D ) ------------------------
   /**
    * draw - overrides parent method
    */
   public void draw( java.awt.Graphics2D aBrush ) 
   {
      Color savedColor = aBrush.getColor();
      aBrush.setColor( _borderColor );
      java.awt.Stroke savedStroke = aBrush.getStroke();
      aBrush.setStroke( new java.awt.BasicStroke( _lineWidth ));
      aBrush.draw( this );
      aBrush.setStroke( savedStroke );
      aBrush.setColor( savedColor );
   }
}
