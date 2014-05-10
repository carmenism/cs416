/**
 * DrawPanel -- template for JPanel class for a Swing based application.
 */

import java.awt.*;
import javax.swing.*;

public class DrawPanel extends JPanel
{
   //------ instance variables for contents of panel -------
   // declare instance variables for graphical objects
   private SnowMan man1;
   private SnowMan man2;
   private SnowMan man3;
   private JFrame frame;
   private RecordPlayer rp1;
   private RecordPlayer rp2;
   
   //------ Constructor ------------
   public DrawPanel(JFrame f)
   {
      super();
      //this.setBackground( Color.GRAY );
      frame = f;
      // add creation of graphical objects, such as an ellipse
      man1 = new SnowMan( frame, 50, 50 );
      man2 = new SnowMan( frame, 300, 50 );
      man3 = new SnowMan( frame, 50, 300 );
      rp1 = new RecordPlayer( frame, 300, 200 );
      rp2 = new RecordPlayer( frame, 450, 50 );
   }

}