/**
 * MoveTimer.java
 * A subclass of javax.swing.Timer that can be used for animation.
 * It also serves as an example of the code for an "event source" object.
 * Version 2 of 2
 * 
 * From. Sanders and van Dam, Object-Oriented Programming in Java
 * 
 * This class creates a timer object that will generate an event when
 * the specified time interval elapses. The java environment passes the
 * event to the "actionPerformed" method of the MoveListener object 
 * created by the MoveTimer constructor. 
 * 
 * The MoveTimer constructor is passed a Mover object that must have
 * a "move()" method that gets invoked when the timer event occurs.
 * 
 * MoveListener is as private "inner" class of MoveTimer. 
 */

public class MoveTimer extends javax.swing.Timer
{
   //-------------- instance variables ----------------------------------
   private Mover _mover; // peer object

   //-------------- MoveTimer constructor -------------------------------
   /**
    * anInterval is number of milliseconds
    * aMover is object to be moved and repainted.
    */
   public MoveTimer( int anInterval, Mover aMover) 
   {
      super( anInterval, null );
      _mover = aMover;
      this.addActionListener( new MoveListener() );
   }

   //++++++++++++++++++ MoveListener inner class ++++++++++++++++++++++++
   /**
    * private inner class to handle the event that signals the end of
    * the time interval. An iinner class has access to all the instance
    * variables of its enclosing class. Hence it can access the _mover
    * variable that contains a reference to the application object that
    * wants to get informed when the time interval elapses.
    */
   private class MoveListener implements java.awt.event.ActionListener 
   {
     //------------ actionPerformed ------------------------------------
     /**
      * gets invoked when the time interval elapses. This, in turn, 
      * calls the "move()" method of the Mover object passed to the
      * MoveTimer constructor.
      */
     public void actionPerformed( java.awt.event.ActionEvent e )
     {
        _mover.move();
     }
   }
}

