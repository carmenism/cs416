/**
 * TreeApp - the main class for controlling the application code. 
 * 
 * 
 * The application semantics including the graphics generated 
 * by the app are controlled from this class.
 * 
 * Modified by Carmen St. Jean for Program11
 * CS 416, Fall 2008, November 25, 2008
 */
import javax.swing.*;
import java.util.*;
   
public class TreeApp
{
   //--------------------- instance variables ----------------------------
   private DataList         _list;
   private Data             _nextData;
   private Iterator<Data>   _iter;
   private BinarySearchTree _bst = null;
   private DisplayPanel     _display;
   private ControlPanel     _controls;
   
   //------ "package" state variables; set by GUI ----------
   boolean printMode     = true;  // if set, print after each command
   
   //--- data generation constants with package access so GUI can change
   int     dataSize        = 8;
   int     minDataSize     = 0;
   int     maxDataSize     = 100;
   int     defaultDataSize = 8;
   int     minNodeWidth    = 1;
   int     maxNodeWidth    = 90;
   int     defaultNodeWidth = 90;
   
   int     rngSeed          = 0;   // random seed
   int     maxSeed          = 16;   // arbitrary number
   
   //--- subset constants
   private int     subsetMin       = 30;   // arbitrary; all values are 0, 100
   private int     subsetMax       = 70;
   
   //---------------------- constructor ----------------------------------
   /**
    * The app needs the display reference only so it can update the 
    * DisplayListPanel with new Lists when needed and tell it to redraw
    * when things change.
    */
   public TreeApp( DisplayPanel display )
   {
      _display = display;
      _controls = null;
      newData();
   }
   /**
    * setControlPanel
    */
   public void setControlPanel( ControlPanel controls )
   {
      _controls = controls;
      updateNextData();
   }
   //---------------------- update() -----------------------------
   /**
    * Something in gui has change; update whatever needs to be updated
    * The GUI calls the app when a GUI update occurs; the app (in this
    * case) only needs to pass this along to the DisplayListPanel.
    */
   public void update()
   { 
       _display.setTree( _bst );     
   }
   //----------------------  setNodeWidth( int ) -------------------
   public void setNodeWidth( int newW )
   {
      if ( newW > 0 )
         _display.setNodeWidth( newW );
   }
   //+++++++++++++++++++++ methods invoked by GUI buttons +++++++++++++++++++
   //---------------------- newData() -----------------------------
   /**
    * generate a new DataList 
    */
   public void newData( )
   {  
      _list = generateData( this.dataSize, this.rngSeed );
      _iter = _list.iterator();
      _nextData = null;
      if ( _controls != null )
         updateNextData();
      
      _bst = new BinarySearchTree();
      update();
   }
   //------------------ updateNextData() ------------------------
   /**
    * update the instance variable for the nextData item and the
    * controls label for it.
    */
   private void updateNextData()
   {
      /////////////////////////////////////////
      // do your version of "peek", update the JLabel
      /////////////////////////////////////////////
      if (_iter.hasNext())
      {
         _nextData = _iter.next();
         String str = _nextData.toString();
         if (_controls != null)
            _controls.setNextDataLabel(str);
      }
      else
      {
         if (_controls != null)
            _controls.setNextDataLabel("none");
         _nextData = null;
      }
   }
   //---------------------- addAll() -----------------------------
   /**
    * generate a new DataList 
    */
   public void addAll( )
   { 
      /////////////////////////////////////////
      // have it add everything that is left.
      /////////////////////////////////////////////
      while (_nextData != null)
      {
         _bst.add(_nextData);
         updateNextData();
         update();
      }
      update();
   }
   //---------------------- addOne() -----------------------------
   /**
    * generate a new DataList 
    */
   public void addOne( )
   {  
      /////////////////////////////////////////
      // have it add the next data item to the tree
      // be sure to update the nextData object and label
      /////////////////////////////////////////////
      if (_nextData != null)
      {
         _bst.add(_nextData);
         updateNextData();
         update();
      }
   }
   
   //---------------------- print() -----------------------------
   /**
    * print the list for debugging; maybe more 
    */
   public void print( )
   {  
      printList( _list, "DebugList" );
      if ( _list != null )
         _list.checkList();
      printTree( _bst, "DebugTree" );
   }
   //++++++++++++++++++++++ utility methods +++++++++++++++++++++++++++++++++++++
   // You should think about more of these
   //
   //----------------------- stringToInt( String ) -----------------------------
   /**
    * Convert string to integer
    */
   private int stringToInt( String str, int defaultVal )
   {
      try 
      {
         return Integer.parseInt( str );
      }
      catch ( NumberFormatException nfe )
      {
         String input = JOptionPane.showInputDialog( null, 
                                     "Invalid integer input: " + str + ". No change." );
         return defaultVal;
      }
   }
   //------------------- loopAverage( _list ) ----------------------------
   /**
    * compute and return the average value of all the value fields of
    * the Data objects in the _list. Traverse the _list using the 
    * first() and next() methods of DataList. This is an iterative
    * solution.
    */
   private double loopAverage( DataList _list )
   {
      Iterator<Data> iter = _list.iterator();
      double         sum = 0.0;
      
      while ( iter.hasNext() )
      {
         Data item = iter.next();
         sum += item.value;
      }
      return sum / _list.size();
   }
        
   //------------------- printTree( _bst, String ) ---------------------------
   private void printTree( BinarySearchTree _bst, String title )
   {
      String dashes = " ------------------------ ";
      System.out.println( "\n" + dashes + title + dashes );
      if ( _bst == null || _bst.size() == 0 )
         System.out.println( "---Empty---" );
      else
      {
         System.out.println( _bst );
         System.out.println( "Tree has " + _bst.size() + " nodes." );
      }
   }
   //------------------- printList( _list, String ) ---------------------------
   private void printList( DataList _list, String title )
   {
      String dashes = " ------------------------ ";
      System.out.println( "\n" + dashes + title + dashes );
      if ( _list == null || _list.size() == 0 )
         System.out.println( "---Empty---" );
      else
      {
         System.out.println( _list );
         double loopAve = loopAverage( _list );
         int total = (int) ( loopAve * _list.size() );
         System.out. println( "List average (loop) of " + _list.size() 
                                + " elements: " + loopAve + " Total: " + total ); 
      }
   }
   //------------------- generateData -----------------------------------
   /**
    * arguments are 
    *    numItems -- number items to generate
    *    seed     -- random number seed; -1 means let system pick it.
    */
   private DataList generateData( int numItems, int seed )
   {
      String[] names = { "box", "cat", "dog", "ham", "bike", "bath", "beer", 
         "rose", "seal", "eggs", "iron", "brass" };
      DataList dl = new DataList();
      Random rng;
      if ( seed < 0 )
         rng = new Random();
      else 
         rng = new Random( seed );
      
      String        letters = "abcdefghijklmnopqrstuvwxyz";
      StringBuffer  keybuf  = new StringBuffer( "12" );
      
      while ( dl.size() < numItems )
      {
         // generate a key
         char letter1 = letters.charAt( rng.nextInt( letters.length() ));
         char letter2 = letters.charAt( rng.nextInt( letters.length() ));
         
         keybuf.setCharAt( 0, letter1 );
         keybuf.setCharAt( 1, letter2 );         
         String key = keybuf.toString();

         Data check = dl.find( key );
         if ( check == null )
         {            
            // generate a name field; these are allowed to duplicate
            int    nameIndex = rng.nextInt( names.length );
            String name = names[ nameIndex ];
                       
            // generate a value from 0 to 99
            int    val = rng.nextInt( 100 );
            dl.add( new Data( key, name, val ));
         }
      }
      return dl;
   }
}