/**
 * A generic LinkedList implementation using a protected inner Node class. 
 * 
 * public interface:
 *   LinkedList()           -- create an empty list
 *   T first()              -- return the data from the first element on list
 *                             and set "current" item to the first one.
 *                             if list is empty, returns null;
 *   T next()               -- updates "current" entry to the next on the list
 *                             and returns that entry's data object. If there 
 *                             is no next entry, null is returned and "current"
 *                             becomes null.
 * 
 *   void add( T )          -- adds T object somewhere in the list
 *   void addHead( T )      -- adds T object to the head of the list
 *   void addTail( T )      -- adds T object to the tail of the list
 * 
 *   T    remove()          -- removes the head of the list and returns its
 *                             data object, which will be null if list is empty
 *   T    remove( String )  -- looks for an object whose key value matches
 *                             the passed string. if found, the object is 
 *                             removed from the list and its data returned.
 *   T    find( String )    -- same as remove( String ), except no removal 
 *                             takes place
 *   boolean ifEmpty()      -- returns true if the list is empty.
 *   int     size()         -- returns the size of the list
 * @author rdb
 * February 2008
 */

public class LinkedList<T extends Comparable<String>>
{
   //----------------- instance variables --------------------------
   protected Node<T> _head;
   protected Node<T> _tail;
   protected Node<T> _cur;
   protected int     _size;
   
   //---------------- constructor ----------------------------------
   /**
    * create an empty linked list
    */
   public LinkedList() 
   {
      _head = null;
      _tail = null;
      _cur  = null;
      _size = 0;
   }
   //----------------- T first() --------------------------------
   /**
    * return the object that is at the start of the list and set the
    * "current" node to the head of the list. Returns null if there are 
    * no elements on the list.
    */
   public T first()
   {
      if ( _head == null )
         return null;
      else
      {
         _cur = _head;
         return _cur.data;
      }
   }
   //----------------- T next() --------------------------------
   /**
    * Move the "current" node position ot the next node on the list
    * and return the  object that is the data for this node. 
    * Returns null if there is no next node and the "current" node
    * position is set to null.
    */
   public T next()
   {
      if ( _cur == null || _cur.next == null )
         return null;
      else
      {
         _cur = _cur.next;
         return _cur.data;
      }
   }
   
   //----------------- isEmpty( ) -------------------------------
   /**
    * returns true if the list is empty
    */
   public boolean isEmpty()
   {
      return _head == null;
   }
   //----------------- toString( ) -------------------------------
   /**
    * creates a string representation of the entire list. Only works for
    * very short lists; but it can be a useful debugging tool.
    */
   public String toString()
   {
      Node walk = _head;
      String str = "";
      while ( walk != null )
      {
         str += walk.data + ", ";
         walk = walk.next;
      }
      return str;
   }
   //----------------- size( ) -------------------------------
   /**
    * returns the size of the list
    */
   public int size()
   {
      return _size;
   }
   //----------------- add( T ) -------------------------------
   /**
    * add a node to the list with the data defined by the T object
    * It is not specified where the node is added; this implementation
    * adds it "after" the head if there is one, else it becomes the head.
    */
   public void add( T newOne )
   {
      Node<T> newNode = new Node<T>( newOne, null );
      if ( _head == null )
         _head = _tail = newNode;
      else          
      {
         newNode.next = _head.next;
         _head.next = newNode;  
         if ( _head == _tail )
            _tail = newNode;
      }
      _size++;
   }
   //----------------- addHead( T ) -------------------------------
   /**
    * adds the data to a node that becomes the new list head.
    */
   public void addHead( T newOne )
   {
      _head = new Node<T>( newOne, _head );
      if ( _tail == null )
         _tail = _head;
      _size++;
   }
   //----------------- addTail( T ) -------------------------------
   /**
    * adds the data to a node that becomes the new list tail.
    */
   public void addTail( T newOne )
   {
      if ( _tail == null )
         addHead( newOne );
      else
      {
         Node<T> temp = new Node<T>( newOne, null );
         _tail.next = temp;
         _tail = temp;
         _size++;
      }
   }
   //----------------- remove() ----------------------------------
   /**
    * remove and return the head of the list
    */
   public T remove()
   {
      T  retObj = null;
      if ( _head != null )
      {
         retObj = (T) _head.data;
         _size--;
         _head = _head.next;
      }
      if ( _head == null )
         _tail = null;
      return retObj;
   }
   //----------------- remove( String ) -----------------------------
   /**
    * remove and return the data from the first entry on the list whose
    * key matches the argument.
    */
   public T remove( String key )
   {
      T    retObj = null;
      Node<T> prev = null;
      Node<T> next = _head;
      while ( next != null && next.data.compareTo( key ) != 0 )
      {
         prev = next;
         next = next.next;
      }
      if ( next != null )  // remove node from the list and return the object
      {
         if ( prev == null )    // found node is head of list
            _head = next.next;
         else                      // make previous node now reference 
            prev.next = next.next; // the node referenced by the found node
         retObj = next.data;
         _size--;
      }
      if ( _head == null )
         _tail = null;
      return retObj;
   }
   //----------------- find( String ) -----------------------------
   /**
    * find an entry whose key matches the argument String. If found, return
    * the data for the entry. If not found, return null.
    */
   public T find( String key )
   {
      Node<T> next = _head;
      while ( next != null && next.data.compareTo( key ) != 0 )
         next = next.next;
      if ( next == null )
         return null;     // not found
      else
         return next.data;
   }
   
   //++++++++++++++++++ Node inner class ++++++++++++++++++++++++++++++++
   /**
    * The Node class is hidden from the application, but is accessible
    * to child classes.
    * 
    * Nodes and take data objects (T) as long as they implement the 
    * StringComparable interface. (It's a bit awkward, but it is necessary
    * to say that T "extends" StringComparable, even though it really
    * just "implements" it.)
    * 
    * The instance data members are public since this class is only
    * available inside the List and its children.
    */
   protected class Node<T> 
   {
      public Node<T> next = null;
      public T data = null;
      
      public Node( T newData, Node<T> n )
      {
         data = newData;  next = n;
      }
   }
   //--------------------- main ----------------------------------
   public static void main( String[] args )
   {
      LinkedList<String> l = new LinkedList<String>();
      
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
         