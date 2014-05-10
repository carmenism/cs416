/**
 * ListPanel - Data structure manipulation 
 * 
 * This class allows the user to add and remove object to a data collection
 * organized as a Stack, Queue or alphabetically sorted list.
 * 
 * The state of the collection is shown in a simple graphical representation.
 * 
 * This is not a complete implementation. It needs the following
 * methods completed:
 *     removeItem
 *     removeItemByKey
 * and the links in the graphical displa should be replaced with something 
 * that shows which end of the link as some kind of arrow head.
 * 
 * In addition the QueueList and StackList and SortedList classes are
 * not complete.
 * 
 */
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;
import java.io.*;
import javax.swing.border.*;

public class ListPanel extends JPanel
{
   
   //---------------------- instance variables ----------------------
   WordDictionary    _wd;   // can be a queue, stack or alpha list
   
   private int       _deltaX = 30; // space between nodes
   private int       _deltaY = 40;
   private int       _firstX;    // x loc for first node on a row
   private int       _firstY;    // y loc for first row
   private int       _nextX;     // location for next node to be added
   private int       _nextY;
   private int       _nodeWidth = 60;  // size of node
   private int       _nodeHeight = 40;

   private boolean   _print = true;   // if set, print after each command
   //--------------------------- constructor -----------------------
   public ListPanel( Container parent )     
   {   
      this.setLayout( null );
      _wd = new WordDictionary();
     
      this.setSize( new Dimension( 800, 300 ));
      _firstX = _nextX = _deltaX / 2;
      
      _firstY = _nextY = 40;      
   }

   //---------------------- togglePrint -----------------------------
   /**
    * Print individual arrays -- only if non-empty
    */
   public void togglePrint()
   { 
      _print = !_print;
   }
   //---------------------- addItem -----------------------------
   /**
    * execute dialog to add an item 
    */
   public void addItem( )
   {  
      String inMessage = "Enter words to add to Dictionary";
      String input = JOptionPane.showInputDialog( null,  inMessage ); 
      String key;
      String rest = "empty";
      
      if ( input != null && input.trim().length() > 0 )
      {
         String[] keys = input.split( " " );
         for ( int i = 0; i < keys.length; i++ )
         {
            _wd.add( keys[ i ] );
         }
      }
      if  ( _print )
         _wd.printDS();
   }
   //---------------------- removeItem -----------------------------
   /**
    * execute dialog to remove an item at a "default" position
    */
   public void removeItem( )
   {  
      String d = _wd.remove();
      if  ( _print )
         _wd.printDS();
   }
   //---------------------- removeItemByKey ------------------------
   /**
    * execute dialog to remove an item identified by a key
    */
   public void removeItemByKey()
   {  
      String inMessage = "Enter keys of items to be deleted; each token is another key";
      String input = JOptionPane.showInputDialog( null,  inMessage ); 
      String key;
      String rest;
      
      if ( input != null && input.trim().length() > 0 )
      {
         String[] keys = input.split( " " );
         for ( int i = 0; i < keys.length; i++ )
         {
            String d = _wd.remove( keys[ i ] );
         }
      }
      if  ( _print )
         _wd.printDS();
   }
}
