/**
 * Chapter 7: MovePanel.java
 * Creates the panel to be placed inside the Lab4 window.
 *
 * 1/30/08: rdb
 *    Renamed (old name was BallApp) and added JFrame parameter to constructor
 *    Pass JFrame to BouncingBall
 */
import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class MovePanel extends JPanel implements Mover 
{
   //------------------------- instance variables -----------------------
   private SnowMan _snowman;
   private final int SNOWMAN_X = 300;
   private final int SNOWMAN_Y = 300;
   private GBuggy _buggy;
   private MoveTimer _timer;
   private final int INTERVAL = 100;
   private JFrame    _frame;

   //------------------------- constructor ----------------------------
   public MovePanel( JFrame frame ) 
   {
      super();
      _frame = frame; 
      
      this.setBackground( Color.white );
 
      _snowman = new SnowMan( this, _frame, SNOWMAN_X, SNOWMAN_Y );
      _snowman.setMove( -4, -4 );
      
      _buggy = new GBuggy( this, _frame, SNOWMAN_X, SNOWMAN_Y );
      _buggy.setMove( -4, 4 );
      
      _timer = new MoveTimer( INTERVAL, this );
      _timer.start();
   }
 
   //-------------------------- move() --------------------------------
   /**
    * move() implements Mover interface. At each clock tick, move each component.
    */
   public void move() 
   {
      _snowman.move();
      _buggy.move();
      _frame.repaint();
   }   
}
