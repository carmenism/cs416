/**
 * Lab4.java
 * Derived from BouncingBall code from Ch 7 of Sanders and van Dam
 * 
 * This is a bouncing SnowMan
 *
 * 1/08: rdb
 *     renamed this class and BallPanel (to MovePanel)  and changed 
 *        MovePanel constructor to accept a JFrame as a parameter
 */
import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class Lab4 extends JFrame 
{
  public Lab4( String title ) 
  {
    super( title );
    this.setSize( 600, 450 );
    this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    
    this.add( new MovePanel( this ) );
    this.add( new JButton( "East" ), BorderLayout.EAST );
    this.add( new JButton( "West" ), BorderLayout.WEST );
    this.add( new JButton( "South" ), BorderLayout.SOUTH );
    this.add( new JButton( "North" ), BorderLayout.NORTH );
    this.setVisible( true );
  }

  public static void main( String [ ] args ) 
  {
    Lab4 app = new Lab4( "Lab4" );
  }
}
