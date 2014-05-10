/**
 * GAreaShape.java -- a convenience class for simplifying access to awt graphics
 *    objects supported by the Graphics AWT class.
 *    The class extends GShape by adding ability to have a border color and
 *      a fill color.
 * 
 *    This is a frame class for 
 *       GEllipse 
 *       GRectangle
 *    Child classes must override paintComponent.
 * 
 * @author (of the modifications) rdb
 * January 2008
 */

import java.awt.geom.*;
import java.awt.*;
import javax.swing.*;

abstract public class GAreaShape extends GShape 
{
   //---------------- instance variables ------------------------
   private Color _fillColor;
   
   //--------------------  constructors ---------------------------
   /**
    * Constructor from GAreaShape
    */
   public GAreaShape( Container parent, Color aColor )
   { 
      super( parent, aColor );
      setFillColor( aColor );
   }
   /**
    * Another wheels-like constructor
    */
   public GAreaShape( Container parent, int x, int y )
   {
      super( parent, x, y );
      this.setSize( 50, 50 );  // areas have default size
      setColor( Color.RED );   // use wheels default color
   }

   //++++++++++++++++++ wheels-like convenience methods +++++++++++++++++++
   //----------------------- setBorderColor( Color ) --------------------
   /**
    * setBorderColor -- a wheels method
    */
   public void setBorderColor( Color aColor ) 
   {
      super.setColor( aColor );   // this sets the line color
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
      setBorderColor( aColor );
      setFillColor( aColor );
   }
   //++++++++++++++++++++++++ accessor methods +++++++++++++++++++++++++++++
   //----------------------- getBorderColor() --------------------
   /**
    * getBorderColor -- a wheels method
    */
   public Color getBorderColor() 
   {
      return super.getColor();
   }
   //----------------------- getFillColor() --------------------
   /**
    * getFillColor -- a wheels method
    */
   public Color getFillColor() 
   {
      return _fillColor;
   }
   //----------------------- getColor() -----------------------
   /**
    * getColor -- a wheels method
    */
   public Color getColor() 
   {
      return _fillColor;
   }
}
