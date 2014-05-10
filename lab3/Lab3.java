/**
 * Chapter 7: Lab3.java
 * Displays a window with a red circle on a white background. 
 * Similar to FirstApp.java( in Chapter 1 ), but written without Wheels.
 * Used( with modifications ) in all programs later in this book.
 *
 * 1/08: rdb
 *     renamed this class and BallPanel (to MovePanel)  and changed 
 *        MovePanel constructor to accept a JFrame as a parameter
 */
import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class Lab3 extends JFrame 
{
  public Lab3( String title ) 
  {
    super( title );
    this.setSize( 600, 450 );
    this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    this.add( new MovePanel( this ) );
    this.setVisible( true );
  }

  public static void main( String [ ] args ) 
  {
    Lab3 app = new Lab3( "Chapter 7: Lab3" );
  }
}
