/**
 * LayoutPanel.java
 *     For Program4.java
 * 
 * @author Carmen St. Jean
 *         CS 416 - Fall 2008, September 29, 2008
 */

//----------------------- imports ----------------------------------------------
import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.util.*;

public class LayoutPanel extends JPanel implements Mover,
                                               javax.swing.event.ChangeListener
{
   //------------------------- instance variables ------------------------------
   private MoveTimer _timer;
   private final int INTERVAL = 100;
   private Puck _puck;
   private Paddle _paddle;
   private Program4 _frame;
   private int left = 0, right = 0;
   private GRectangle leftGoal, box1, box2, box3, box4;
   private Vector<GEllipse> bumpers; 
   //---------------------------------------------------------------------------
   //------------------------- constructor -------------------------------------

   public LayoutPanel( Program4 frame ) 
   {
      super();
      _frame = frame;
      setLayout( null );
      this.setSize(600,450);
      
      buildLeftGoal();
      _paddle = new Paddle( frame, getWidth() - 35, 100 );
      
      Random generator = new Random();
      int x = 3 + generator.nextInt(6);
      int y = 3 + generator.nextInt(6);
      _puck = new Puck( frame, this, _paddle, getWidth()/2, getHeight()/2);
      _puck.setMove(x, y);
         
      _timer = new MoveTimer( INTERVAL, this );
      this.setBackground( Color.WHITE );
      repaint();
   }
   
   //---------------------------------------------------------------------------
   //------------------------- methods -----------------------------------------

   /**
    * private void buildLeftGoal()
    *    This builds the goal on the left side.
    */
   private void buildLeftGoal()
   {
      double y = 2 * this.getHeight() / 5;
      double h = this.getHeight() / 5;
      leftGoal = new GRectangle(_frame, 0, (int)y);
      leftGoal.setSize(5, (int)h);
      this.add(leftGoal);
   }
   
   //---------------------------------------------------------------------------
      
   /**
    * public void buildBoxes()
    *    This builds the boxes in the middle to complicate the game.
    */
   public void buildBumpers()
   {
      bumpers = new  Vector<GEllipse>(); 
      Vector<Point> points = new Vector<Point>();
      int x = this.getWidth() / 3;
      int y = this.getHeight() / 3;
      bumpers.add(new GEllipse(_frame, x - 12, y - 12));
      bumpers.add(new GEllipse(_frame, 2 * x - 8, y - 12));
      bumpers.add(new GEllipse(_frame, x - 12, 2 * y - 12));
      bumpers.add(new GEllipse(_frame, 2 * x - 12, 2 * y - 12));
      for (int i = 0; i < 4; i++)
      {
         bumpers.get(i).setSize(24, 24);
         this.add(bumpers.get(i));
         points.add(new Point(bumpers.get(i).getX()+12,
                              bumpers.get(i).getY()+12));
      }
      _puck.tellAboutBumpers(points);
   }
   
   //---------------------------------------------------------------------------

   /**
    * public void pointScored(int player)
    *    Updates the score.  0 is for the human player, 1 is for the computer.
    */
   public void pointScored(int player)
   {
      if (player == 0)
      {
         right++;
      }
      else if (player ==1)
      {
         left++;
      }
      stopMove();
      System.out.println("Left: " + left + " Right: " + right);
      _frame.updateScore(left, right);
      _puck.setMove(-_puck.getMoveX(), _puck.getMoveX());
   }
   //---------------------------------------------------------------------------
   //------------------------- move interface methods --------------------------
  
   /**
    * public void move() 
    *    Implements Mover interface. At each clock tick, move each component.
    */
   public void move() 
   {
      _paddle.repaint();
      _puck.move();
      this.getParent().repaint();
   }
   
   //---------------------------------------------------------------------------
   //------------------------- button methods ----------------------------------

   /**
    * public void startMove()
    *    Stars the timer and thus the puck.
    */
   public void startMove()
   {
      _timer.start();
   }
   
   //---------------------------------------------------------------------------
   
   /**
    * public void stopMove()
    *    Stops the timer and thus the puck.
    */
   public void stopMove()
   {
      _timer.stop();
      System.out.println("STOP!");
   }
   
   //---------------------------------------------------------------------------

   /**
    * public void slower()
    *    Slows down the timer.
    */
   public void slower()
   {
      int delay = _timer.getDelay();
      _timer.setDelay(delay + (delay / 5));
   }
   
   //---------------------------------------------------------------------------

   /**
    * public void faster()
    *    Speeds up the timer.
    */
   public void faster()
   {
      int delay = _timer.getDelay();
      _timer.setDelay(delay - (delay / 5));
   }
   
   //---------------------------------------------------------------------------
   //------------------------- slider methods ----------------------------------

   /**
    * public void stateChanged(javax.swing.event.ChangeEvent e)
    *    When the slider is moved, the paddle will move with it.
    */
   public void stateChanged(javax.swing.event.ChangeEvent e)
   {
      Object source = e.getSource();
      JSlider theJSlider = (JSlider) source;
      _paddle.setLocation(getWidth() - 35,
                          getHeight() - theJSlider.getValue() - 25);
      _paddle.repaint();
   }
}   