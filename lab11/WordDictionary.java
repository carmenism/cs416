/**
 * WordDictionary -- shows a primitive mechanism for speeding up sorting
 *  operations. The first letter of a word is used to index into an array
 *  of lists. Each of these lists contains all the words that start with the
 *  same letter. If you are looking for a particular word, you only have to
 *  search for it among words that start with the same letter.
 * 
 * CS 416 -- Lab13 Spring 2008
 *
 * Complete the constructor and the methods 
 *          add, find, remove(), remove( String ), size
 *
 */
@SuppressWarnings("unchecked")

public class WordDictionary
{
   //---------------- class variables -----------------------------------
   private final int NLISTS  = 27;  // 1 list for each letter and 1 for all else
   
   //---------------- instance variables --------------------------------
   private LinkedList<String> _list[];
   private int                _curList;
   
   //--------------------- constructor ----------------------------------
   public WordDictionary()
   {
      // one list for each letter and one for everything else
      _list = (LinkedList<String>[]) new LinkedList[ NLISTS ]; 
      
      for (int i = 0; i < NLISTS; i++)
      {
         _list[i] = new LinkedList<String>();
      }
      
      //********************************************************
      // create a LinkedList<String> object for each _list entry
      //********************************************************
      
      
   }
   //--------------------- add( String ) --------------------------------------
   /**
    * insert an object in order
    */
   public void add( String word )
   { 
      //*************************************************************
      // figure out which list must have the String (if it's there).
      // The method, getListFromString( String ), will do that.
      //
      //   Once you know the list, add this word t oit..
      //*************************************************************
      int index = this.getListFromString(word);
      _list[index].add(word);
   }
   //--------------------- find( String ) --------------------------------------
   /**
    * return the word if it is found in dictionary
    */
   public String find( String word )
   {  
      //*************************************************************
      // figure out which list must have the String (if it's there)
      //   and ask that list to find it.
      //*************************************************************
      int index = this.getListFromString(word);
      return _list[index].find(word);
   }
   //--------------------- remove( String ) --------------------------------------
   /**
    * return Word if it is found in dictionary and remove it from Dictionary
    */
   public String remove( String word )
   {  
      //*************************************************************
      // figure out which list must contain String (if it's there)
      //   ask that list to remove (and return) it.
      //*************************************************************
      int index = this.getListFromString(word);
      return _list[index].remove(word);
   }
   //--------------------- remove() --------------------------------------
   /**
    * remove first word in dictionary
    */
   public String remove( )
   {  
      //*************************************************************
      // loop through lists until find one with something to remove
      //*************************************************************
      String r = null;
      for (int i = 0; i < NLISTS && r == null; i++)
      {
            r = _list[i].remove();
      }
      return r;
   }
   //--------------------- size() --------------------------------------
   /**
    * return true if WordDictionary is empty
    */
   public int size()
   { 
      //*******************************************************************
      // sum up the sizes of all the arrays
      //*******************************************************************
      int sum = 0;
      for (int i = 0; i < NLISTS; i++)
         sum += _list[i].size();
      return sum;
   }
   //--------------------- isEmpty() --------------------------------------
   /**
    * return true if WordDictionary is empty
    */
   public boolean isEmpty()
   {      
      return size() == 0;
   }
   
   //--------------------- first() --------------------------------------
   /**
    * return first element on list
    */
   public String first( )
   {    
      String found = null;
      
      // special code: need to retain the list value for the integer
      //   that generates the found object; increment it otherwise.
      //   by testing found first and incrementing later, we can get
      //   it to work.
      int list = -1;
      while ( found == null && ++list < NLISTS )
      {
         found = _list[ list ].first();
      }
      _curList = list;
      return found;
   }
   //---------------------  next() --------------------------------------
   /**
    * return next element on list
    */
   public String next( )
   {  
      if ( _curList >= NLISTS )
         return null;
      else
      {
         String nextItem = _list[ _curList ].next();
         if ( nextItem == null )
         {      
            // This is "special" code. We need to get the first of the
            //   next non-empty list; the tricky part is the increment of
            //   curList. If we do it AFTER we test for null, then we won't
            //   increment after we've found the one we want -- and we 
            //   don't want that increment to happen.
            while ( nextItem == null && ++_curList < NLISTS  )
               nextItem = _list[ _curList ].first();
         }
         return nextItem;
      }
   }
   //------------------ getListFromString( String ) ------------------------
   /**
    * return the index of a list based on the key
    */
   int getListFromString( String key )
   {
      int whichList = key.toString().toLowerCase().charAt( 0 ) - 'a';  
      if ( whichList < 0 || whichList >= NLISTS )
         whichList = NLISTS - 1;
      return whichList;
   }
   
   /**
    * make a string from dictionary
    */
   public String toString()
   {
      String s = "";
      for ( int i = 0; i < NLISTS; i++ )
      {
         s += _list[ i ].toString();
      }
      return s;
   }
   //---------------------- printDS -----------------------------
   /**
    * Print individual arrays -- only if non-empty
    */
   public void printDS()
   { 
      // print arrays
      System.out.println( "-----------------------------------------" );
      for ( int i = 0; i < NLISTS; i++ )
      {
         if ( ! _list[ i ].isEmpty() )
            printList( _list[ i ] );
      }
      System.out.println( "-----------------------------------------" );
   }
   private void printList( LinkedList l )
   {
      System.out.print( "[ " + l.first() );
      
      String n = (String) l.next();
      while ( n != null )
      {
         System.out.print ( ", " + n );
         n = (String) l.next();
      }
      System.out.println( " ]" );
   }
     
   //--------------------- main ----------------------------------
   public static void main( String[] args )
   {
      WordDictionary l = new WordDictionary();
      
      System.out.println( l.find( "bogus" ));
      l.add( "A" );
      l.add( "B" );
      l.add( "C" );
      l.add( "D" );
      l.add( "E" );
      
      System.out.println( l );
      
      System.out.println( "List size (5) = " + l.size() );
      
      System.out.println( "Searching for A: " + l.find( "A" ));
      System.out.println( "Searching for C: " + l.find( "C" ));
      System.out.println( "Searching for d: " + l.find( "d" ));
      System.out.println( "Searching for E: " + l.find( "E" ));
      
      System.out.println( l );
     
      System.out.println( "Searching for F: " + l.find( "F" ));
      System.out.println( "Searching for G: " + l.find( "G" ));   
      
      System.out.println( "List size (7) = " + l.size() );
      
      // now remove some
      
      l.remove();  // removes A
      System.out.println( "List size (6) = " + l.size() );
      
      l.remove( "G" ); // remove one on the end
      System.out.println( "List size (5) = " + l.size() );
      
      System.out.println( l );
      l.remove( "E" ); // remove one in the middle
      System.out.println( "List size (4) = " + l.size() );
      System.out.println( l );
      
      l.remove( "Not There" );
      System.out.println( "List size (4) = " + l.size() );
      
      while ( l.size() > 0 )
         l.remove();
      System.out.println( "List size (0) = " + l.size() );
      
      //---------------------Timing ----------------------------------------
      int numAdd = 50000;    // number pushes
      
      System.out.println( "------------------ Timing test --------------" );
      System.out.println( numAdd + "    adds and removes" );
      System.out.println( "----------------------------------------------" );
                            
      
      System.out.println(  " Remove them using size()." );
      long start = System.currentTimeMillis();
      
      //------------- add lots of values onto stack ----------------
      for ( int i = 0; i < numAdd; i++ )
      {
          l.add(  "A" + i );
      }
      //------------- now remove them all ----------------
      while ( l.size() > 0 )
      {
         l.remove();
      }
      float time = ( System.currentTimeMillis() - start ) / 1000.0f;
      System.out.println( "Time : " + time );     

      System.out.println(  " Remove them using isEmpty (tests head)" );
      start = System.currentTimeMillis();
      
      //------------- add lots of values onto stack ----------------
      for ( int i = 0; i < numAdd; i++ )
      {
          l.add( "A" + i );
      }
      //------------- now remove them all off ----------------
      while ( ! l.isEmpty() )
      {
         l.remove();
      }
      time = ( System.currentTimeMillis() - start ) / 1000.0f;
      System.out.println( "Time : " + time );     
   }
}