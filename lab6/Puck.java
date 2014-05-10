/** 
 * Puck.java:
 * 
 * Displays a simple snow man using multiple components.
 * The entire snowman is built in the constructor.
 * 
 * @author rdb
 * Created 9/11/07; derived from cs415 demo program, Start.java 
 * 01/08:   modified to add new components and to work with G classes
 */
import java.awt.*;
import javax.swing.*;
import java.util.*;

public class Puck extends JComponent implements Mover
{
   //---------------- instance variables ------------------------------
   private Container _panel;     // region in which motion can occur
   
   private int  _dX;           // steps sizes for each move
   private int  _dY;
   
   //  xmin,ymin of all components (also the composite location)
   private int  _xLoc = Integer.MAX_VALUE;  
   private int  _yLoc = Integer.MAX_VALUE;
   
   //  save xmax, ymax of all components (used to get composite size)
   private int  _xMax = Integer.MIN_VALUE;
   private int  _yMax = Integer.MIN_VALUE;
          
   private Vector<GShape>  _components; // components of this composite
     
   // -----------------------------------------------------------------
   /** 
    * Constructor for the Puck class. 
    *    JFrame argument is used by the G-classes
    *    JPanel argument is needed by this Composite class: it defines
    *        the Container in which the snow man is allowed to move
    *    x,y are the position of the Puck.
    */
   public Puck( JFrame frame, JPanel panel, int x, int y )
   {
      _panel  = panel;      
      _components = new Vector<GShape>();
      
      buildPuck( frame, x, y );
     
      super.setSize( _xMax - _xLoc + 2, _yMax - _yLoc + 2 );
      setLocation( _xLoc, _yLoc );
   }
   //------------------ component building methods ----------------------

   private void buildPuck(Container parent, int x, int y)
   {
      int    size  = 50; 
      
      GEllipse puck = new GEllipse(parent, x, y);
      puck.setSize(size, size);
      puck.setFillColor(Color.BLACK);
      updateComposite(puck);
      _components.add(puck);      
   }
   
   //------------------------ setLocation( Point ) ----------------------
   /**
    * display the snowman at the specified Point
    */
   public void setLocation(Point p) 
   {
      setLocation( p.x, p.y );
   }
   //------------------------ setLocation( int, int ) ----------------------
   /**
    * display the snowman at the specified x,y location
    *    we achieve this by re-positioning all the components.
    */   
   public void setLocation( int x, int y )
   {
      int dx = x - _xLoc;
      int dy = y - _yLoc;
      
      // if this is null, we're being called at super constructor time,
      //   before we've created the components object. Ignore that.
      if ( _components != null )
      {
         for ( int i = 0; i < _components.size(); i++ )
            _components.get( i ).moveBy( dx, dy );
         
         super.setLocation( x, y );
         _xLoc = x; _yLoc = y;
      }
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
   //----------------------- setMove( int, int ) ------------------------
   /**
    * set the move increments
    */
   public void setMove( int dx, int dy )
   {
      _dX = dx;
      _dY = dy;
   }
   //----------------------- getMoveX() -----------------------------------
   /**
    * return the x component of move step
    */
   public int getMoveX()
   {
       return _dX;
   }
   //----------------------- getMoveY() -----------------------------------
   /**
    * return the y component of move step
    */
   public int getMoveY()
   {
       return _dY;
   }

   //---------------------- move() -----------------------------------------
   /**
    * move the Puck by the specified incremental steps.
    */
   public void move() 
   {
      // get the expected next location
      int nextX = this.getX() + _dX;
      int nextY = this.getY() + _dY;
      
      // but check if we have hit a boundary of the panel we're in
      if ( nextX <= this.getMinBoundX())    // are we at the left edge
      {                                     
         _dX = - _dX;                         // if so, reverse x increment
         nextX = this.getMinBoundX();       // and set us at the edge
      }
      else if ( nextX >= this.getMaxBoundX()) // have we hit the right edge
      {
         _dX = - _dX; 
         nextX = this.getMaxBoundX();
      }
      if ( nextY <= this.getMinBoundY())      // at the top?
      {
         _dY = - _dY; 
         nextY  = this.getMinBoundY();
      }
      else if ( nextY > this.getMaxBoundY())  // at the bottom?
      {
         _dY = - _dY;
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
      return _panel.getX();
   /**
    * get the min valid y location value
    */
   }
   public int getMinBoundY() 
   {
      return _panel.getY();
   }
   /**
    * get the max valid x location value
    */
   public int getMaxBoundX() 
   {
     return _panel.getX() + _panel.getWidth() - this.getWidth();
   }
   /**
    * get the max valid y location value
    */
   public int getMaxBoundY() 
   {
      return _panel.getY() + _panel.getHeight() - this.getHeight();
   }
}
