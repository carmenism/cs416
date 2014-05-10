/**
 * DrawPanel.java
 *    For Program2.
 * 
 * @author Carmen St. Jean
 *         CS 416 - Fall 2008, September 16, 2008
 */

//----------------------- imports ----------------------------------------------
import java.awt.*;
import javax.swing.*;

public class DrawPanel extends JPanel
{
   //------------------------- instance variables ------------------------------
   private JFrame frame;
   private Buggy buggy1, buggy2;
   private GPolygon polygon1, polygon2;
   private GTriangle triangle;
   private GStar star;
   
   //---------------------------------------------------------------------------
   //------------------------- constructor -------------------------------------
   
   /**
    * public DrawPanel(JFrame f)
    *    Creates a panel in the given frame to display GWheels objects on.
    */
   public DrawPanel(JFrame f)
   {
      super();
      frame = f;
      this.setBackground(Color.WHITE);
      star = new GStar(frame, Color.GREEN, 10, 10);
      buggy1 = new Buggy(frame, 450, 250, Color.BLUE);
      buggy2 = new Buggy(frame, 550, 250, Color.GREEN);
      
      int[]  xPoints     = { 20, 310, 310 };
      int[]  yPoints     = { 10, 40, 310 };
      polygon1 = new GPolygon(frame, Color.ORANGE);
      polygon1.addPoint(5, 130);
      polygon1.addPoint(190, 40);
      polygon1.addPoint(180, 240);
      polygon1.addPoint(80, 200);
      polygon2 = new GPolygon(frame, xPoints, yPoints, 3);


      triangle = new GTriangle(frame, Color.RED);
      triangle.addPoint(5, 5);
      triangle.addPoint(150, 5);
      triangle.addPoint(5, 360);
   }
}