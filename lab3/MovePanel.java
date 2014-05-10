/**
 * Chapter 7: MovePanel.java
 * Creates the panel to be placed inside the Lag3 window.
 * Used( with modifications ) in all programs later in this book.
 * Version 3 of 3
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
   private BouncingBall _ball; // components
   private BouncingBall _ball2, _ball3;
   private final int INIT_X = 75; // attributes
   private final int INIT_Y = 75;
   private final int DIAMETER = 60;
   private final int INTERVAL = 100;
   
   private GPlayer _man, _man2;
   private final   int MAN_X  = 200;
   private final   int MAN_Y  = 200;
   
   private MoveTimer _timer;
   private JFrame    _frame;
   
   public MovePanel( JFrame frame ) 
   {
      super();
      _frame = frame;
      this.setBackground( Color.white );
      
      _ball = new BouncingBall( frame, Color.red );  
      _ball.setLocation( INIT_X, INIT_Y );
      _ball.setSize( DIAMETER, DIAMETER );
      
      _ball2 = new BouncingBall( frame, Color.blue );
      _ball2.setMove( 3, 6 );
      _ball2.setLocation( INIT_X, INIT_Y );
      _ball2.setSize( DIAMETER, DIAMETER );
      
      _ball3 = new BouncingBall( frame, Color.orange );
      _ball3.setMove( 5, -4 );
      _ball3.setLocation( INIT_X, INIT_Y );
      _ball3.setSize( DIAMETER/2, DIAMETER/2 );
      
      _timer = new MoveTimer( INTERVAL, this );
      _timer.start();
      
      _man = new GPlayer( frame, Color.black, MAN_X, MAN_Y );
      _man.setLocation( INIT_X, INIT_Y );
      _man.setMove( -3, 5 );
      
      _man2 = new GPlayer( frame, Color.green, MAN_X, MAN_Y );
      _man2.setLocation( INIT_X, INIT_Y );
      _man2.setMove( 7, -5 );
   }
   
   public void move() 
   {
      _ball.move();
      _ball2.move();
      _ball3.move();
      _man.move();
      _man2.move();
      this.repaint();
   }
   
   public void paintComponent( Graphics aBrush ) 
   {
      super.paintComponent( aBrush );
      Graphics2D betterBrush = 
        ( Graphics2D ) aBrush;
      //_ball.fill( betterBrush );
   }
}
