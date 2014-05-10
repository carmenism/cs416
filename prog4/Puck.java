/**
 * Puck.java
 *     For Program4.java
 * 
 * @author Carmen St. Jean
 *         CS 416 - Fall 2008, September 29, 2008
 */

//----------------------- imports ----------------------------------------------
import java.awt.*;
import javax.swing.*;
import java.util.*;

public class Puck extends JComponent implements Mover
{
   //------------------------- instance variables ------------------------------
   private LayoutPanel _panel;
   private Paddle _paddle;
   private int  _dX, _dY;
   private int  size  = 30, _xLoc = 100, _yLoc = 100;
   private int  _xMax = Integer.MIN_VALUE;
   private int  _yMax = Integer.MIN_VALUE;
   private Vector<GShape>  _components;
   private Vector<GEllipse> boxes;
   private Vector<Point> points;
     
   //---------------------------------------------------------------------------
   //------------------------- constructor -------------------------------------

   /**
    * public Puck( JFrame frame, LayoutPanel panel, Paddle p, int x, int y )
    *    JFrame is necessary for G-classes.
    *    JPanel is neede for the composite parts of this.
    *    Paddle so the puck can know its location and bounce off of it.
    *    x, y are the position of the puck.
    */
   public Puck( JFrame frame, LayoutPanel panel, Paddle p, int x, int y )
   {
      _panel  = panel;      
      _components = new Vector<GShape>();
      _paddle = p;
      _xLoc = x;
      _yLoc = y;
      buildPuck( frame, x, y );
      super.setSize( _xMax - _xLoc + 2, _yMax - _yLoc + 2 );
      setLocation( _xLoc, _yLoc );
   }
   
   //---------------------------------------------------------------------------
   //------------------------- bumper methods ----------------------------------

   /**
    * public void tellAboutBumpers(Vector<Point> p)
    *    This method is so the bumpers can be 'delivered' to the puck.  This
    *    actually only tells their center coordinates.
    */
   public void tellAboutBumpers(Vector<Point> p)
   {
      points = p;     
   }
   
   //---------------------------------------------------------------------------
   
   /**
    * private Point getCenterPuck()
    *    Calculates the current center of the puck.
    */
   private Point getCenterPuck()
   {
      return new Point(getX()+size/2, getY()+size/2);
   }
   
   //---------------------------------------------------------------------------
   
   /**
    * private void bumperCheck(int nextX, int nextY)
    *    This method checks to see if the puck will hit any bumpers as it moves,
    *    and will make the puck bounce accordingly.
    */
   private void bumperCheck(int nextX, int nextY)
   {
      for (int i = 0; i < 4; i++)
      {
         int x = nextX + size/2;
         int y = nextY + size/2;
         double dist = Math.sqrt( (x - points.get(i).x)*(x - points.get(i).x)
                                + (y - points.get(i).y)*(y - points.get(i).y) );
         if (dist <= 24)
         {
            _dX = (x - points.get(i).x)/2;
            _dY = (y - points.get(i).y)/2;
         }
      }
   }

   //---------------------------------------------------------------------------
   //------------------------- component methods -------------------------------

   /**
    * private void buildPuck(Container parent, int x, int y)
    *    Builds the actual puck...  Simplifies the constructor.
    */
   private void buildPuck(Container parent, int x, int y)
   {
      GEllipse puck = new GEllipse(parent, x, y);
      puck.setSize(size, size);
      puck.setFillColor(Color.BLACK);
      updateComposite(puck);
      _components.add(puck);      
   }
   
   //---------------------------------------------------------------------------
   
   /**
    * public void updateComposite(GShape comp)
    *    This updates the bounds of the composite based on a new component.
    */
   public void updateComposite(GShape comp)
   {
      if (comp.getX() < _xLoc)
         _xLoc = comp.getX();
      if (comp.getY() < _yLoc)
         _yLoc = comp.getY();
      
      int cmaxX = comp.getX() + comp.getWidth();
      int cmaxY = comp.getY() + comp.getHeight();
   
      if (cmaxX > _xMax)
         _xMax = cmaxX;
      if (cmaxY > _yMax)
         _yMax = cmaxY;
    }
   
   //---------------------------------------------------------------------------
   //------------------------- move methods ------------------------------------
   
   /**
    * public void setLocation(Point p)
    *    Displays the puck at the given location.
    */
   public void setLocation(Point p) 
   {
      setLocation( p.x, p.y );
   }
   
   //---------------------------------------------------------------------------

   /**
    * public void setLocation(int x, int y)
    *    Displays the puck at the given location.
    */
   public void setLocation(int x, int y)
   {
      int dx = x - _xLoc;
      int dy = y - _yLoc;
      if ( _components != null )
      {
         for ( int i = 0; i < _components.size(); i++ )
            _components.get( i ).moveBy( dx, dy );
         super.setLocation( x, y );
         _xLoc = x; _yLoc = y;
      }
   }
   
   //---------------------------------------------------------------------------
   
   /**
    * public void setMove(int dx, int dy)
    *    This sets the move increments.
    */
   public void setMove(int dx, int dy)
   {
      _dX = dx;
      _dY = dy;
   }
   
   //---------------------------------------------------------------------------

   /**
    * public int getMoveX()
    *    This returns the x component of move step.
    */
   public int getMoveX()
   {
       return _dX;
   }
   
   //---------------------------------------------------------------------------

   /**
    * public int getMoveY()
    *    This returns the y component of move step.
    */
   public int getMoveY()
   {
       return _dY;
   }

   //---------------------------------------------------------------------------

   /**
    * public void move() 
    *    This moves the puck by the specified incremental steps.
    */
   public void move() 
   {
      // get the expected next location
      int nextX = this.getX() + _dX;
      int nextY = this.getY() + _dY;
      
      if (points != null)
      {
         this.bumperCheck(nextX, nextY);
      }
      
      // but check if we have hit a boundary of the panel we're in
      if ( nextX <= 5 && 
           nextY >= 2*_panel.getHeight()/5 - size/2 &&
           nextY < 2*_panel.getHeight()/5 + size/2 + _panel.getHeight()/5)
      {
         _panel.pointScored(0);
      }
      else if ((nextX + size) >= _paddle.getXLocation() &&
                nextY >= _paddle.getYLocation() - size/2 &&
                nextY < _paddle.getYLocation() + size/2 + 50)
      {
         _dX = - _dX;
         nextX = this.getMaxBoundX() - size;
      }
      else if ( nextX <= this.getMinBoundX())    // are we at the left edge
      {                                     
         _dX = - _dX;                         // if so, reverse x increment
         nextX = this.getMinBoundX();       // and set us at the edge
      }
      else if (nextX >= this.getMaxBoundX()) // have we hit the right edge
      {
         _panel.pointScored(1);
      }
      
      if (nextY <= this.getMinBoundY())      // at the top?
      {
         _dY = - _dY; 
         nextY  = this.getMinBoundY();
      }
      else if (nextY > this.getMaxBoundY())  // at the bottom?
      {
         _dY = - _dY;
         nextY = this.getMaxBoundY();
      }
      this.setLocation( nextX, nextY );       // update location
   }
   
   //---------------------------------------------------------------------------
   
   /**
    * public int getMinBoundX() 
    *    This gets the min valid x location value.
    */
   public int getMinBoundX() 
   {
      return _panel.getX();
   }
   
   //---------------------------------------------------------------------------
   
   /**
    * public int getMinBoundY() 
    *    This gets the min valid y location value.
    */
   public int getMinBoundY() 
   {
      return _panel.getY();
   }
   
   //---------------------------------------------------------------------------
   
   /**
    * public int getMaxBoundX() 
    *    This gets the max valid x location value.
    */
   public int getMaxBoundX() 
   {
     return _panel.getX() + _panel.getWidth() - this.getWidth();
   }
   
   //---------------------------------------------------------------------------
   
   /**
    * public int getMaxBoundY() 
    *    This gets the max valid y location value.
    */
   public int getMaxBoundY() 
   {
      return _panel.getY() + _panel.getHeight() - this.getHeight();
   }
}
