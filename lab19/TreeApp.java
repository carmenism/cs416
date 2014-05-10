/**
 * TreeApp - the main class for controlling the application code. 
 * 
 * 
 * The application semantics including the graphics generated 
 * by the app are controlled from this class.
 */
import javax.swing.*;
import java.util.*;
   
public class TreeApp
{
   //--------------------- instance variables ----------------------------
   private DataList _list;
   private BinarySearchTree _bst = null;
   private DisplayPanel     _display;
   
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
   
   //----- duplicates flag
   private boolean _allowDuplicates = false;
   
   //---------------------- constructor ----------------------------------
   /**
    * The app needs the display reference only so it can update the 
    * DisplayPanel with new data when needed and tell it to redraw
    * when things change.
    */
   public TreeApp( DisplayPanel display )
   {
      _display = display;
      makeData( _allowDuplicates );
   }
   
   
   /////////////////////////////////////////////////////////////////////
   
   //---------------------- checkAVL() -----------------------------
   /** 

    */
   public void checkAVL( )
   {    
      
     checkAVL( _bst.root() );                        
     update();
   }
   //-----------checkAVL( BinarySearchTree.Node ) -------------
  /** 
     Sets the AVL status of each node
    */
   public void checkAVL( BinarySearchTree.Node node )
   {
      if (node !=null)
      {
         checkAVL(node.left);
         checkAVL(node.right);
         
         int bal = _bst.height(node.right) - _bst.height(node.left);
         node.data.value = bal;
         
         if (bal == 0 || bal == 1 || bal == -1)
            node.data.name = "AVL";
         else if (bal < -1 && node.left.data.value < 0)
            node.data.name = "LL";
         else if (bal < -1 && node.left.data.value > 0)
            node.data.name = "LR";
         else if (bal > 1 && node.right.data.value > 0)
            node.data.name = "RR";
         else if (bal > 1 && node.right.data.value < 0)
            node.data.name = "RL";
      }
   }
  
   /////////////////////////////////////////////////////////////////////
   
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
   //---------------------- setDuplicates( boolean allow ) ---------------
   /**
    * if argument is true, duplicate keys are allowed in tree
    */
   public void setDuplicates( boolean allow )
   {
      _allowDuplicates = allow;
   }
   //+++++++++++++++++++++ methods invoked by GUI buttons +++++++++++++++++++
   //---------------------- makeData( boolean ) -----------------------------
   /**
    * generate a new DataList 
    */
   public void makeData( boolean allowDuplicates )
   {  
      _allowDuplicates = allowDuplicates;
      _list = generateData( this.dataSize, this.rngSeed );
      
      _bst = new BinarySearchTree();
      
      Iterator<Data> iter = _list.iterator();
      while ( iter.hasNext() )
         _bst.add( iter.next() );
      
      print();
      update();
   }
   //---------------------- toArray() -----------------------------
   /**
    * Generate an ordered list from tree
    */
   public void toArray( )
   { 
      Vector<Data> sorted = toVector();
      
      _bst = new BinarySearchTree();
      Iterator<Data> iter = sorted.iterator();
      while ( iter.hasNext() )
         _bst.add( iter.next() );
     
      _display.setTree( _bst );
      _display.update();
   }
   //----------------------- toVector() ----------------------------
   private Vector<Data> toVector()
   {
      Vector<Data> sorted = new Vector<Data>();
      
      Iterator<Data> iter = _bst.iterator();
      while ( iter.hasNext() )
         sorted.add( iter.next() );
      return sorted;
   }
   //---------------------- balanceTree() -----------------------------
   /**
    * balance the tree 
    */
   public void balanceTree( )
   {  
      Vector<Data> sorted = new Vector<Data>();
      
      Iterator<Data> iter = _bst.iterator();
      while ( iter.hasNext() )
         sorted.add( iter.next() );
      
      BinarySearchTree newTree  = new BinarySearchTree();
      
      binaryAdd( sorted, 0, sorted.size() - 1, newTree );
      _bst = newTree;
      print();
      update();
   }
   //------------------------ binaryAdd ------------------------
   private void binaryAdd( Vector<Data> order, int low, int high, 
                          BinarySearchTree bst )
   {
      if ( high >= low )
      {
         int mid = ( high + low ) / 2;
         bst.add( order.get( mid ) );
         binaryAdd( order, low, mid - 1, bst );
         binaryAdd( order, mid + 1, high, bst );
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
      if ( _allowDuplicates )
         letters = "abc";
      StringBuffer  keybuf  = new StringBuffer( "12" );
      
      while ( dl.size() < numItems )
      {
         // generate a key
         char letter1 = letters.charAt( rng.nextInt( letters.length() ));
         char letter2 = letters.charAt( rng.nextInt( letters.length() ));
         
         keybuf.setCharAt( 0, letter1 );
         keybuf.setCharAt( 1, letter2 );         
         String key = keybuf.toString();

         if ( _allowDuplicates || dl.find( key ) == null )
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
