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
   int     minDataSize     = 1;
   int     maxDataSize     = 30;
   int     defaultDataSize = 8;
   int     minNodeWidth    = 20;
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
      makeData();
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
   //---------------------- makeData() -----------------------------
   /**
    * generate a new DataList 
    */
   public void makeData( )
   {  
      _list = generateData( this.dataSize, this.rngSeed );
      
      _bst = new BinarySearchTree();
      
      Iterator<Data> iter = _list.iterator();
      while ( iter.hasNext() )
         _bst.add( iter.next() );
      
      print();
      update();
   }
   
  
   
   //-------------------------------------------------------------
   //
   //*************   Problem Item #3
   //
   //
   //---------------------- toArray() -----------------------------
   /**
    * Generate an ordered list from tree
    */
   public void toArray( )
   { 
      Vector<Data> vect = new Vector<Data>();
      Iterator<Data> iter = _bst.iterator();
      while (iter.hasNext())
         vect.add(iter.next());
      
      BinarySearchTree bst = new BinarySearchTree();
      Iterator<Data> iter2 = vect.iterator();
      while (iter2.hasNext())
         bst.add(iter2.next());
      
      _bst = bst;
      _display.setTree( _bst );
      _display.update();
   }

     
   //-------------------------------------------------------------
   //
   //*************   Problem Item #4
   //
   //
   //---------------------- balanceTree() -----------------------------
   /**
    * balance the tree 
    */
   public void balanceTree( )
   {  
      Vector<Data> vect = new Vector<Data>();
      Iterator<Data> iter = _bst.iterator();
      while (iter.hasNext())
         vect.add(iter.next());
      
      BinarySearchTree bst = rebuild(vect);
      
      _bst = bst;
      _display.setTree( _bst );
      _display.update();
      print();
      update();
   }

   public BinarySearchTree rebuild(Vector<Data> v)
   {
      if (v == null || v.size() == 0)
         return null;
      else
      {
         int s = v.size();
         int mid = s/2;
         BinarySearchTree b = new BinarySearchTree(v.remove(mid));
                  
         s = v.size();
         
         if (s - mid == 1)
         {
            b.add(v.remove(s - 1 - mid));
         }
         else if (s - mid > 1)
         {
            Vector<Data> v2 = new Vector<Data>();
            for (int i = mid; i < s - 1; i++)
               v2.add(v.get(i));
            BinarySearchTree r = rebuild(v2);
            if (r != null)
               b.add(r.root().data);
         }
         
         s = v.size();
         System.out.println(s);
         if (s == 1)
         {
            b.add(v.remove(0));
         }
         else if (s > 1)
         {
            Vector<Data> v1 = new Vector<Data>();
            for (int i = 0; i < s - 1; i++)
            {
               v1.add(v.get(i));
            }
            BinarySearchTree l = rebuild(v1);
            if (l != null)
               b.add(l.root().data);
         }
        
         return b;
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
   
    //---------------------- postOrder() -----------------------------
   /**
    * Generate a post-order String of value fields from the BST
    */
   public void postOrder( )
   { 
      
      String str = _bst.postOrder();
   
      JOptionPane.showMessageDialog( null, "Values in post-order:\n" + str );
   }
   
   
   //---------------------- countLeaves() -----------------------------
   /**
    * find and report the number of leaves in the tree
    */
   public void countLeaves( )
   {    
      int count = _bst.countLeaves(); 
      JOptionPane.showMessageDialog( null, "There are " + count + " leaves in the tree" );
   }
   
   
    //---------------------- height() -----------------------------
   /**
    * find and report the height of the tree
    */
   public void height( )
   {    
      int height = _bst.height(); 
      JOptionPane.showMessageDialog( null, "The tree has height " + height );
   }
   
     //---------------------- findMin() -----------------------------
   /**
    * find and report the min value Data item in the list
    */
   public void findMin( )
   { 
      String msg;
      Data min = _bst.findMin();
      if ( min == null )
         msg = "Tree is empty";
      else
         msg = "Min entry in tree is " + min;
      JOptionPane.showMessageDialog( null, msg );
   }
   
   

   //---------------------- keepSubset() -----------------------------
   /**
    * build a new tree that contains only the nodes of the old tree
    * whose value fields are within the range (inclusive) entered into
    * a dialog box.
    */
   public void keepSubset( )
   { 
      getSubsetBounds();
      BinarySearchTree newTree = new BinarySearchTree();
      
      Iterator<Data> iter = _bst.iterator();
      while ( iter.hasNext() )
      {
         Data d = iter.next();
         if ( d.value >= subsetMin && d.value <= subsetMax )
            newTree.add( d );
      }  
      _bst = newTree;
      update();
   }
   //---------------------- delSubset() -----------------------------
   /**
    * build a new tree that contains only the nodes of the old tree
    * whose value fields are outside the range entered into
    * a dialog box.
    */
   public void delSubset( )
   { 
      getSubsetBounds();
      BinarySearchTree newTree = new BinarySearchTree();
      
      Iterator<Data> iter = _bst.iterator();
      while ( iter.hasNext() )
      {
         Data d = iter.next();
         if ( d.value < subsetMin || d.value > subsetMax )
            newTree.add( d );
      }  
      _bst = newTree;
      _display.setTree( newTree );
      _display.update();
   }

   //---------------------- findParent() -----------------------------
   /**
    * find and report the information associated with the parent of the
    * node with a specified key.
    */
   public void findParent( )
   { 
      String outMsg;
      String key;
      key = JOptionPane.showInputDialog( null, "Enter key to search for" );
      if ( key != null && key.length() > 0 )
      {         
         Data found = _bst.findParent( key );
         if ( found != null )
            outMsg = "Found: " + found;
         else
            outMsg = key + " is not in the tree or is the root.";
         JOptionPane.showMessageDialog( null, outMsg );
      }
   }
   //++++++++++++++++++++++ utility methods +++++++++++++++++++++++++++++++++++++
   // You should think about more of these
   //
   //----------------------- getSubsetBounds() ---------------------------
   /**
    * use dialog box to get new bounds for subset values
    */
   private void getSubsetBounds()
   {
      String inMessage = "Enter min/max for subset bounds: \n "
                       + "1 value changes min; no values changes nothing.";
      String input = JOptionPane.showInputDialog( null,  inMessage ); 
      if ( input != null && input.length() > 0 )
      {
         System.out.println( "input:|" + input + "| " + input.length() );
         String[] values = input.split( " " );
         
         if ( values.length > 1 )
            this.subsetMax = stringToInt( values[ 1 ], this.subsetMax );
         if ( values.length > 0 )
            this.subsetMin = stringToInt( values[ 0 ], this.subsetMin );
      }
      System.out.println( "Subset range: " + subsetMin + " --> " + subsetMax );
   }
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
