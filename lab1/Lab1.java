/**
 * Lab1.java Fall 2008
 * 
 * This is a Swing application based on the pattern described in Chapter 7 of
 * Sanders and van Dam, Object-Oriented Programming in Java.
 * 
 * All the application-specific code is in the DrawPanel class
 * 
 * @author rdb
 * 
 */

import javax.swing.*;

public class Lab1 extends JFrame
{
   //------------------ constructor ---------------------------------
   /**
    * Construct a JFrame for the application and put a JPanel in it.
    */
   public Lab1( String title, String[] args )
   {
      super( title );            // specify window title
      this.setSize( 700, 500 );  // define window size
      
      this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
 
      // All the application specific code is in the DrawPanel constructor.
      // DrawPanel extends JPanel. 

      DrawPanel panel = new DrawPanel( args );   // create graphics in JPanel
      this.add( panel );                         // add it to the frame
      
      this.setVisible( true );  // Initially, JFrame is not visible, make it so.
   }
   
   //------------------------ main -----------------------------------
   public static void main( String[] args )
   {
      Lab1 app = new Lab1( "Drawing in AWT/Swing", args );
   }
}