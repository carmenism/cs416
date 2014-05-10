
//********************************************************************

import java.awt.*;
import javax.swing.JPanel;

public class DrawPanel extends JPanel
{
   private final int PANEL_WIDTH = 600;
   private final int PANEL_HEIGHT = 600;

  
   private double height = 120;
   private double width = 250;
   private  double x1 = 150, x2 = x1 ;
   private  double y1 = 300, y2 =  y1 - width;
   private double angle = 0;



   //-----------------------------------------------------------------
   public DrawPanel ( )
   {   
      setBackground (Color.white);
      setPreferredSize (new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
   }
   
   /*-----------------------------------------------------------------
   
       |x2,y2
       |
       | x3, y3
       |__________________  x4,y4
       |
       |
       |
       |x1,y1
    
   //-----------------------------------------------------------------*/
   public void paintComponent (Graphics brush)
   {
      super.paintComponent (brush);
      brush.setColor (Color.black);
       
      double midX, midY, x3, y3, x4, y4, x5, y5, base, height; 
      
      midX = x2 + (x1 - x2) / 2;  //apex of T
      midY = y2 + (y1 - y2) / 2;
      
      x3 = x2 + (x1 - x2) / 3;
      y3 = y2 + (y1 - y2) / 3;
      
      x5 = x2 + 2 * (x1 - x2) / 3;
      y5 = y2 + 2 * (y1 - y2) / 3;
      
      base = Math.sqrt( (y5 - y3)*(y5 - y3) + (x5 - x3)*(x5 - x3) );
      height = Math.sqrt(3) * base / 2;
      
      x4 = midX + height/base * (y1-y2) / 3;  // bottom of T
      y4 = midY + height/base * (x2-x1) / 3;

      brush.drawLine ((int)x1, (int)y1, (int)x5, (int)y5);

      brush.drawLine ((int)x5, (int)y5, (int)x4, (int)y4); 
      
      brush.drawLine ((int)x4, (int)y4, (int)x3, (int)y3);
      
      brush.drawLine ((int)x3, (int)y3, (int)x2, (int)y2);
             
   }

   //-----------------------------------------------------------------
   //  
   //-----------------------------------------------------------------
   public void tilt ()
   {
       angle = angle + .01;
      x2  = x1 + Math.sin( angle ) * width;
      y2 = y1 - Math.cos( angle ) * width;
   }

}
