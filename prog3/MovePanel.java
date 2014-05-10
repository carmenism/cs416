/**
 * MovePanel.java
 *    For Program3.
 * 
 * @author Carmen St. Jean
 *         CS 416 - Fall 2008, September 23, 2008
 */

//----------------------- imports ----------------------------------------------
import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;

public class MovePanel extends JPanel implements Mover, MouseListener
{
   //------------------------- instance variables ------------------------------
   private Seeker seeker;
   private final int DIAMETER = 19;
   private int speed;
   private double goalX, goalY, currentX, currentY;
   private ArrayList<WayPoint> wayPoints1;
   private LinkedList<WayPoint> wayPoints;
   private WayPoint wp;
   private MoveTimer _timer;
   private JFrame _frame;
   
   //---------------------------------------------------------------------------
   //------------------------- constructor -------------------------------------
   
   /**
    * public MovePanel(JFrame frame, String [] args)
    *    This constructor creates a panel and requires an array of strings to
    *    find the speed and the points for the seeker to visit.
    */
   public MovePanel(JFrame frame, String [] args) 
   {
      super();
      _frame = frame;
      this.setBackground( Color.WHITE );
      this.addMouseListener(this);
      
      wayPoints1 = new ArrayList<WayPoint>();
      wayPoints = new LinkedList<WayPoint>();
      this.argumentReader(args);
      
      if (wayPoints != null)
      {
         wp = wayPoints.remove();
         currentX = wp.getX();
         currentY = wp.getY(); System.out.println( currentX + " " + currentY);
         wp = wayPoints.remove();
         goalX = wp.getX();
         goalY = wp.getY();
      
         seeker = new Seeker( Color.red, frame );  
         seeker.setLocation((int)currentX, (int)currentY);
         seeker.setSize(DIAMETER, DIAMETER);
    
         _timer = new MoveTimer( speed, this);
         _timer.start();
         move();
      }
   }
   
   //---------------------------------------------------------------------------
   //------------------------- methods -----------------------------------------

   /**
    * private void argumentReader(String [] args)
    *    This reads the arguments to find the speed and the coordinates for the
    *    way points.
    */
   private void argumentReader(String [] args)
   {
      try
      {
         speed = Integer.parseInt(args[0]);
         for (int i = 1; i < args.length - 1; i += 2)
         {
            int x = Integer.parseInt(args[i]);
            int y = Integer.parseInt(args[i+1]);
            wayPoints.add(new WayPoint(_frame, x, y));
            wayPoints1.add(new WayPoint(_frame, x, y));
         }
      }
      catch(ArrayIndexOutOfBoundsException e)
      {
         System.out.println("Error with command line arguments.");
         seeker = new Seeker(Color.red, _frame);  
         seeker.setLocation(100, 100);
         seeker.setSize(DIAMETER, DIAMETER);
         seeker.repaint();
         this.repaint();
      }
   }
   
   //---------------------------------------------------------------------------
   
   /**
    * public void move()
    *    This method uses the current location and the location of the goal to
    *    calculate the movement of the seeker in regards to the speed.  It also
    *    draws lines between the way points.
    */
   public void move() 
   {
      for (int i = 0; i < wayPoints1.size() - 1; i++)
      {
         GLine line = new GLine(_frame,
                       wayPoints1.get(i+1).getX(), wayPoints1.get(i+1).getY(),
                         wayPoints1.get(i).getX(), wayPoints1.get(i).getY());
      }
      double distX = goalX - currentX;
      double distY = goalY - currentY;
      double vectorLength = Math.sqrt(distX*distX + distY*distY);
      int numSteps = (int)(vectorLength / speed);
      double stepX = distX/numSteps;
      double stepY = distY/numSteps;
      numSteps--;
      if (numSteps > 0)
      {
         currentX += stepX;
         currentY += stepY;
         seeker.setLocation((int) currentX, (int) currentY);
      }
      else if (numSteps == 0)
      {
         currentX = goalX;
         currentY = goalY;
         seeker.setLocation((int) currentX, (int) currentY); 
         if (!wayPoints.isEmpty())
         {
            wp = wayPoints.remove();
            goalX = wp.getX();
            goalY = wp.getY();
         }
         else
            _timer.stop();
      }
      seeker.repaint();
      this.repaint();
   }
   
   //---------------------------------------------------------------------------
   //------------------------- MouseListener events ----------------------------

  /**
   * public void mousePressed(MouseEvent e)
   *    At the press of the mouse, this method adds a new way point where the
   *    mouse was clicked.
   */
   public void mousePressed(MouseEvent e)
   {
      Point p = e.getPoint();
      WayPoint wp = new WayPoint(_frame, p.x, p.y);
      wayPoints.add(wp);
      wayPoints1.add(wp);
      wp = wayPoints.remove();
      goalX = wp.getX();
      goalY = wp.getY();
      GLine line = new GLine(_frame, (int)goalX, (int)goalY,
                             (int)currentX, (int)currentY);
      this.add(line);
      line.repaint();
      wp.repaint();
      this.repaint();
      _timer.start();
      move();
   }
   
   //---------------------------------------------------------------------------
   
   public void mouseClicked(MouseEvent e) { }   
   public void mouseReleased(MouseEvent e) { }
   public void mouseEntered(MouseEvent e) { }
   public void mouseExited(MouseEvent e) {}
}