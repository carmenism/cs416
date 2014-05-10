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

public class LayoutPanel extends JPanel implements Mover, javax.swing.event.ChangeListener
{
   //------------------------- instance variables -----------------------
   private MoveTimer _timer;
   private final int INTERVAL = 100;
   private Puck _puck;
   private GRectangle _mallet;
   
   private JFrame _frame;
   //------------------------- constructor ----------------------------
   public LayoutPanel( JFrame frame ) 
   {
      super();
      _frame = frame;
      setLayout( null );    // Important line. We want to do all our own
                            // positioning inside this panel.
      this.setBackground( Color.WHITE );
      
      _puck = new Puck( frame, this, 100, 100 );
      _puck.setMove( 4, 6);
       
      _mallet = new GRectangle( frame, 550, 100 );
      _mallet.setSize( 15, 50 );
      _mallet.setColor( Color.BLACK );
      
      _timer = new MoveTimer( INTERVAL, this );
      //_timer.start();
   }
  //-------------------------- move() --------------------------------
   /**
    * move() implements Mover interface. At each clock tick, move each component.
    */
   public void move() 
   {
      _puck.move();
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
      int x = _puck.getMoveX();
      int y = _puck.getMoveY();
      _puck.setMove(-x, y);
   }
   
   public void invertY()
   {
      int x = _puck.getMoveX();
      int y = _puck.getMoveY();
      _puck.setMove(x, -y);
   }
   public void slower()
   {
      int delay = _timer.getDelay();
      _timer.setDelay(delay + (delay / 5));
      System.out.println("Delay slowed down from " + delay + " to " + (delay + (delay / 5)));
   }
   public void faster()
   {
      int delay = _timer.getDelay();
      _timer.setDelay(delay - (delay / 5));
      System.out.println("Delay sped up from " + delay + " to " + (delay - (delay / 5)));
   }
   
   public void stateChanged(javax.swing.event.ChangeEvent e)
   {
      _mallet.setColor(Color.BLUE);
      Object source = e.getSource();
      JSlider theJSlider = (JSlider) source;
      _mallet.setLocation(550, 425 - theJSlider.getValue());
//
//      if (source instanceof BoundedRangeModel)
//      {
//         BoundedRangeModel aModel = (BoundedRangeModel) source;
//         if (!aModel.getValueIsAdjusting())
//         {
//            System.out.println("Changed: " + aModel.getValue());
//         }
//      }
//      else if (source instanceof JSlider)
//      {
//      JSlider theJSlider = (JSlider) source;
//         if (!theJSlider.getValueIsAdjusting())
//         {
//           System.out.println("Slider changed: " + theJSlider.getValue());
//         }
//      }
//      else if (source instanceof JProgressBar)
//      {
//         JProgressBar theJProgressBar = (JProgressBar) source;
//         System.out.println("ProgressBar changed: "
//             + theJProgressBar.getValue());
//      }
//      else
//      {
//         System.out.println("Something changed: " + source);
//      }
      System.out.println("Slider was moved.");
      _mallet.repaint();
   }
}   