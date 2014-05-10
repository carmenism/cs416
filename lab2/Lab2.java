/**
 * Lab2 -- template for Swing based application main class.
 */

import java.awt.*;
import javax.swing.*;

public class Lab2 extends JFrame
{
   //------ Constructor ---------------
   public Lab2( String title )
   {
      super( title );
      this.setSize( 700, 500 );
      this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
      
      // The JPanel class referenced here might be the only 
      //   application specific line in this class:
      this.add( new DrawPanel(this) );
      
      this.setVisible( true ); 
   } 
   
   //-------- main ---------------------
   public static void main(String[] args)
   {
      Lab2 app = new Lab2( "A Swing Application" );
   }
}