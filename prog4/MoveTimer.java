/**
 * Chapter 7: MoveTimer.java
 * A subclass of Timer that can be used for animation.
 * It also serves as an example of the code for an "event source" object.
 * Version 2 of 2
 */
import javax.swing.*;
import java.awt.event.*;

public class MoveTimer extends javax.swing.Timer 
{
   private Mover _mover; // peer object
   
   public MoveTimer( int anInterval, Mover aMover ) 
   {
      super( anInterval, null );
      _mover = aMover;
      this.addActionListener( new MoveListener() );
   }
   
   private class MoveListener implements ActionListener 
   {
      public void actionPerformed( ActionEvent e )
      {
         _mover.move();
      }
   }
}

