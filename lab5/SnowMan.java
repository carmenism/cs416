/** 
 * SnowMan.java:
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

public class SnowMan extends JComponent implements Mover
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
    * Constructor for the SnowMan class. 
    *    JFrame argument is used by the G-classes
    *    JPanel argument is needed by this Composite class: it defines
    *        the Container in which the snow man is allowed to move
    *    x,y are the position of the SnowMan.
    */
   public SnowMan( JFrame frame, JPanel panel, int x, int y )
   {
      _panel  = panel;      
      _components = new Vector<GShape>();
      
      buildFace( frame, x, y );   // create the face      
      buildHat(  frame, x, y );   // create the hat and its brim  
      buildBody( frame, x, y );   // create the body
      buildArms( frame, x, y );   // create some arms
      buildHead( frame, x, y );   // create the head
     
      super.setSize( _xMax - _xLoc + 2, _yMax - _yLoc + 2 );
      setLocation( _xLoc, _yLoc );
   }
   //------------------ component building methods ----------------------
   private void buildFace( Container parent, int x, int y )
   {
      int[]  smileX     = { 40, 45, 55, 60 };
      int[]  smileY     = { 28, 30, 30, 28 };
      
      int    leftEyeX  = 40,  leftEyeY  = 15;
      int    rightEyeX = 55,  rightEyeY = 15;
      int    eyeSize   = 4;
      
       // create the two eyes
      GEllipse leftEye = new GEllipse( parent, x + leftEyeX, y + leftEyeY );
      leftEye.setColor( Color.BLACK );
      leftEye.setSize( eyeSize, eyeSize );
      updateComposite( leftEye );
      _components.add( leftEye );
      
      GEllipse rightEye = new GEllipse( parent, x + rightEyeX, y + rightEyeY );
      rightEye.setColor( Color.BLACK );
      rightEye.setSize( eyeSize, eyeSize );
      updateComposite( rightEye );
      _components.add( rightEye );
      
      // create a smile
      GLine[] smile = new GLine[ smileX.length - 1 ];
      for ( int i = 0; i < smileX.length - 1; i++ )
      {
         smile[ i ] = new GLine( parent, Color.BLACK );
         smile[ i ].setPoints(  x + smileX[ i ], y + smileY[ i ], 
                              x + smileX[ i + 1 ], y + smileY[ i + 1 ] );
         smile[ i ].setLineWidth( 2 );
         updateComposite( smile[ i ] );
         _components.add( smile[ i ] );
      }
   }
   private void buildHat( Container parent, int x, int y )
   {
      int    brimX  = 30,  brimY  = 0;
      int    brimW  = 40,  brimH = 4;
      int    hatX   = 38,  hatY   = -13;
      int    hatW   = 25,  hatH   = 13;
      
      GRectangle hatBody = new GRectangle( parent, x + hatX, y + hatY );
      hatBody.setSize( hatW, hatH );
      hatBody.setColor( Color.BLACK );
      updateComposite( hatBody );
      _components.add( hatBody );
      
      GRectangle hatBrim = new GRectangle( parent, x + brimX, y + brimY );
      hatBrim.setSize( brimW, brimH );
      hatBrim.setColor( Color.BLACK );
      updateComposite( hatBrim );
      _components.add( hatBrim );
   }
   private void buildBody( Container parent, int x, int y )
   {
      int    bodyX     = 10,  bodyY     = 40;
      int    bodySize  = 80; 
      
      GEllipse body = new GEllipse( parent, x + bodyX, y + bodyY );
      body.setSize( bodySize, bodySize );
      body.setFillColor( Color.WHITE );
      updateComposite( body );
      _components.add( body );      
   }
   private void buildArms( Container parent, int x, int y )
   {
      int    lArmX1 = 0, lArmY1 = 20, lArmX2 = 15, lArmY2 = 60;
      int    rArmX1 = 85, rArmY1 = 60, rArmX2 = 120, rArmY2 = 40;
      
      GLine leftArm = new GLine( parent, Color.BLACK );
      leftArm.setPoints( x + lArmX1, y + lArmY1, 
                        x + lArmX2, y + lArmY2 );
      leftArm.setLineWidth( 3 );
      updateComposite( leftArm );
      _components.add( leftArm );
      
      GLine rightArm = new GLine( parent, Color.BLACK  );
      rightArm.setPoints( x + rArmX1, y + rArmY1, 
                         x + rArmX2, y + rArmY2 );
      rightArm.setLineWidth( 3 );
      updateComposite( rightArm );
      _components.add( rightArm );
   }
   private void buildHead( Container parent, int x, int y )
   {
      int    headX     = 25,  headY     = 0;
      int    headSize  = 50;
      
      GEllipse head = new GEllipse( parent, x + headX, y + headY );
      head.setSize( headSize, headSize );
      head.setFillColor( java.awt.Color.WHITE );
      updateComposite( head );
      _components.add( head );
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
    * move the SnowMan by the specified incremental steps.
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
