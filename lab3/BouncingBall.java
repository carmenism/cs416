/** 
 * Chapter 7: BouncingBall.java
 * Extends SmartEllipse, adding the ability to "bounce."
 * 
 * 01/30/08 rdb
 *    Changed 2nd argument of constructor from JPanel to JFrame
 *    Recovered the JPanel, by calling getContentPane() method of 
 *      the JFrame. 
 */
import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class BouncingBall extends GEllipse implements Mover 
{
   private int _changeX, _changeY; // attributes
   private final int MOVE_LEN = 5;
   private Container _panel; // peer object( and container )
   private JFrame    _frame; 
   private Timer     _timer;
   
   public BouncingBall( JFrame aFrame, Color aColor )
   {
      super( aFrame, aColor );
      _changeX = MOVE_LEN;
      _changeY = MOVE_LEN;
      _frame   = aFrame;
      _panel   = aFrame.getContentPane();
   }
   
   public void setMove( int x, int y )
   {
     _changeX = x;
     _changeY = y;
   }
   
   public void move() 
   {
      int nextX = (int)this.getX() + _changeX;
      int nextY = (int)this.getY() +  _changeY;
      if ( nextX <= this.getMinBoundX() ) 
      {
         _changeX *= -1;
         nextX = this.getMinBoundX();
      }
      else if ( nextX >= this.getMaxBoundX() ) 
      {
         _changeX *= -1;
         nextX = this.getMaxBoundX();
      }
      if ( nextY <= this.getMinBoundY() ) 
      {
         _changeY *= -1;
         nextY  = this.getMinBoundY();
      }
      else if ( nextY > this.getMaxBoundY() )
      {
         _changeY *= -1;
         nextY = this.getMaxBoundY();
      }
      this.setLocation( nextX, nextY );
   }
   public int getMinBoundX() 
   {
      return(int) _panel.getX();
   }
   public int getMinBoundY() 
   {
      return(int) _panel.getY();
   }
   public int getMaxBoundX() 
   {
      return(int)( _panel.getX() + _panel.getWidth()
                       - this.getWidth() );
   }
   public int getMaxBoundY() 
   {
      return(int)( _panel.getY() + _panel.getHeight()
                       - this.getHeight() );
   }
}

