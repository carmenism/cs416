/**
 * GPlayer.java-- a composite G object 
 * 
 * @author rdb
 * January 2008
 */

import java.awt.geom.*;
import java.awt.*;
import javax.swing.*;

public class GPlayer extends GAreaShape implements Mover
{
   //---------------- instance variables ------------------------
   GEllipse   _head;
   GRectangle _body;
   Container  _panel;
   int _changeX = 0, _changeY = 0;
   //--------------------  constructors ---------------------------
   /**
    * Constructor for GPlayer
    */
   public GPlayer( JFrame frame, Color aColor, int x, int y )
   { 
      super( frame, aColor );
 
      int    headX = 5,  headY = 0,  headW = 20, headH = 20;
      int    bodyX = 0,  bodyY = 20, bodyW = 30, bodyH = 50;
      
      _panel = frame.getContentPane();
      
      _head = new GEllipse( frame, x + headX, y + headY );
      _head.setSize( headW, headH );
      _head.setColor( aColor );
      
      _body = new GRectangle( frame, x + bodyX, y + bodyY );
      _body.setSize( bodyW, bodyH );
      _body.setColor( aColor );
      
      super.setLocation( x, y );
      this.setSize( bodyW, headH + bodyH );
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
   
   public void setMove( int x, int y )
   {
     _changeX = x;
     _changeY = y;
   }
      
   //------------------- setLocation -----------------------------
   public void setLocation( int x, int y )
   {
      int dx = x - this.getX();
      int dy = y - this.getY();
      _body.moveBy( dx, dy );
      _head.moveBy( dx, dy );
      super.setLocation( x, y );
   }
}