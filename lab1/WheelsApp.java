/**
 * WheelsApp.java -- CS416 WheelsApp, Fall 2008
 * 
 * This is a wheels application that has organized to make it a bit easier
 * to translate into an AWT/Swing application. The wheels hides the 
 * interface to the AWT JPanel, but 
 * 
 * @author rdb
 * 
 */

import wheelsunh.users.*;
import java.awt.Color;

public class WheelsApp extends Frame
{
   //-------------------- instance variables ----------------------
   // Note: these variables do NOT need to be instance variables in wheels,
   //       but they will need to be when converting this program to awt.
   private Rectangle rect1,  rect2;
   private Ellipse   ell1,   ell2;
   private Line      line1,  line2;
   private Buggy     buggy1, buggy2;
   
   //------------------ constructor ---------------------------------
   /**
    * Create some wheels objects
    */
   public WheelsApp( String[] args )
   {           
      rect1 = new Rectangle( Color.BLUE );
      rect1.setLocation( 100, 100 );
      rect1.setSize( 40, 40 );
      
      rect2 = new Rectangle( Color.RED );
      rect2.setLocation( 200, 200 );
      rect2.setSize( 20, 60 );
      
      line1 = new Line();
      line1.setColor( Color.BLACK );
      line1.setPoints( 120, 120, 210, 230 );
      
      ell1 = new Ellipse( Color.CYAN );
      ell1.setLocation( 10, 400  );
      ell1.setSize( 40, 10 );
      
      ell2 = new Ellipse( Color.MAGENTA );
      ell2.setLocation( 400, 400 );
      ell2.setSize( 30, 30 );
      
      line2 = new Line();
      line2.setColor( Color.BLACK );
      line2.setPoints( 25, 405, 415, 415 );  
      
      buggy1 = new Buggy( Color.RED );
      buggy1.setLocation( 450, 300 );
      buggy2 = new Buggy( Color.CYAN);
      buggy2.setLocation( 400, 20 );
   }
   
   //------------------------ main -----------------------------------
   public static void main( String[] args )
   {
      WheelsApp app = new WheelsApp( args );
   }
}