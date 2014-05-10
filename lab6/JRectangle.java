/**
 * JRectangle.java-- a convenience class for simplifying access to awt graphics
 *    objects supported by the Graphics AWT class. 
 *    The class extends JComponent, but the interface is patterned
 *    after that of wheels. 
 * 
 * @author (of the modifications) rdb
 * January 2008
 */

import java.awt.geom.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class JRectangle extends GRectangle implements MouseListener, MouseMotionListener
{
   //---------------- instance variables ------------------------
   private int _prevX, _prevY;
   private Point _lastMouse;
   private int dx, dy;
   private Color myColor;
   //--------------------  constructors ---------------------------
   /**
    * Constructor from JRectangle
    */
   public JRectangle( Container parent, Color aColor )
   { 
      super( parent, aColor );
      this.addMouseListener( this );
      this.addMouseMotionListener( this );
      myColor = aColor;
   }
   /**
    * Another wheels-like constructor
    */
   public JRectangle( Container parent, int x, int y )
   {
      super( parent, x, y );
      this.addMouseListener( this );
      this.addMouseMotionListener( this );
   }
   
   //------------------- mouse handling methods ---------------------
   public void mousePressed( MouseEvent me )
   {
     // get the offset from location
      // me is relative to location
     dx =   me.getPoint().x;
     dy =   me.getPoint().y;
  }
   
   public void mouseDragged( MouseEvent me )
   {
      Point cur = me.getPoint();
      
      // add the change from the original offset
      setLocation(  getX() + cur.x - dx , getY() + cur.y -dy) ;
      _parent.repaint(  );
  
      
   }
   
   //------------- unused interface methods -----------------
   public void mouseClicked( MouseEvent me ) {}
   public void mouseEntered( MouseEvent me )
   {
      this.setFillColor( Color.ORANGE );
      this.repaint();
   }
   public void mouseExited( MouseEvent me )
   {
      this.setFillColor( myColor );
      this.repaint();
   }
   public void mouseMoved( MouseEvent me ) {}
   public void mouseReleased( MouseEvent me ){}
}
