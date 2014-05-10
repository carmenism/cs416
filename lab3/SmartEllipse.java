/**
 * Chapter 7: SmartEllipse.java
 * Extends Java's Ellipse2D.Double class, adding the capabilities to
 * set color, rotation, location, and size, to move to a specified
 * location, and to display itself on a panel.
 */

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class SmartEllipse extends Ellipse2D.Double 
{
   private Color _borderColor, _fillColor; // attributes
   private int _rotation;
   private final int STROKE_WIDTH = 2;
   
   public SmartEllipse( Color aColor )
   { 
      _borderColor = aColor;
      _fillColor = aColor; // solid color to start
      _rotation = 0; // no rotation for now
   }
   
   // methods not provided by Java
   public void setBorderColor( Color aColor ) 
   {
      _borderColor = aColor;
   }
   public void setFillColor( Color aColor ) 
   {
      _fillColor = aColor;
   }
   public void setRotation( int aRotation ) 
   {
      _rotation = aRotation;
   }
   
   // more readable versions of methods provided by Java
   public void setLocation( double x, double y ) 
   {
      this.setFrame( x, y, this.getWidth(), 
                     this.getHeight() );
   }
   public void setSize( int aWidth, int aHeight ) 
   {
      this.setFrame( this.getX(), this.getY(), 
                    aWidth, aHeight );
   }
   public void move( int aChangeInX, int aChangeInY ) 
   {
      this.setFrame( (int)this.getX()+aChangeInX,
                   (int)this.getY()+aChangeInY,
                    this.getWidth(),
                    this.getHeight() );
   }
   
   public void fill( Graphics2D aBetterBrush )
   {
      Color savedColor = aBetterBrush.getColor();
      aBetterBrush.setColor( _fillColor );
      aBetterBrush.fill( this ); // paint a solid ellipse
      aBetterBrush.setColor( savedColor );
   }
   public void draw( Graphics2D aBrush ) 
   {
      Color savedColor = aBrush.getColor();
      aBrush.setColor( _borderColor );
      Stroke savedStroke = aBrush.getStroke();
      aBrush.setStroke( new BasicStroke( STROKE_WIDTH ) );
      aBrush.draw( this );
      aBrush.setStroke( savedStroke );
      aBrush.setColor( savedColor );
   }
}
