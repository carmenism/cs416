/**
 * LayoutPanel.java
 * Creates the panel to be placed inside the Lab5 window.
 *
 * 02/05/08: rdb
 * 02/06/08: Modified for Lab5
 */
import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.util.*;


public class LayoutPanel extends JPanel implements Mover
{
   //------------------------- instance variables -----------------------
   private MoveTimer _timer;
   private final int INTERVAL = 100;
   private SnowMan _snowman;
   
   private JFrame _frame;
   //------------------------- constructor ----------------------------
   public LayoutPanel( JFrame frame ) 
   {
      super();
      _frame = frame;
      setLayout( null );    // Important line. We want to do all our own
                            // positioning inside this panel.
      this.setBackground( Color.WHITE );
      
      _snowman = new SnowMan( frame, this, 100, 100 );
      _snowman.setMove( 4, 6);
      
      JRectangle paddle = new JRectangle( frame, 200, 100 );
      paddle.setSize( 40, 10 );
      paddle.setColor( Color.BLACK );
      
      _timer = new MoveTimer( INTERVAL, this );
      //_timer.start();
   }
  //-------------------------- move() --------------------------------
   /**
    * move() implements Mover interface. At each clock tick, move each component.
    */
   public void move() 
   {
      _snowman.move();
      this.getParent().repaint();
   }
   
   public void startMove()
   {
      _timer.start();
   }
   
   public void stopMove()
   {
      _timer.stop();
   }
   
   public void invertX()
   {
      int x = _snowman.getMoveX();
      int y = _snowman.getMoveY();
      _snowman.setMove(-x, y);
   }
   
   public void invertY()
   {
      int x = _snowman.getMoveX();
      int y = _snowman.getMoveY();
      _snowman.setMove(x, -y);
   }
}   