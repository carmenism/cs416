/**
 * A generic LinkedList implementation using a private inner Node class. 
 * 
 * The only add/remove methods are
 *
 *   void add( T )          -- adds object to the head of the list
 *   T    remove()          -- removes the first object on the list
 *                
 * @author rdb
 * February 2008
 */

public class LinkedList<T>
{
   //----------------- instance variables --------------------------
   private Node _head;
   private Node _tail;
   
   //---------------- constructor ----------------------------------
   public LinkedList() 
   {
      _head = null;
      _tail = null;
   }
   //----------------- isEmpty( ) -------------------------------
   public boolean isEmpty()
   {
      return _head == null;
   }
   //----------------- size( ) -------------------------------
   public int size()
   {
      Node walk = _head;
      int  size = 0;
      while ( walk != null )
      {
         size++;
         walk = walk.next;
      }
      return size;
   }
   //----------------- add( Object ) -------------------------------
   public void add( T newOne )
   {
      _head = new Node( newOne, _head );
      _tail = _head;
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
         retObj = _head.object;
         _head = _head.next;
      }
      
      if ( _head == null )
      {
         _tail = null;
      }
      
      return retObj;
   }
   //----------------- find( key ) -----------------------------
   public T find( T key )
   {
      Node next = _head;
      while ( next != null && !next.compare( key ) )
         next = next.next;
      if ( next == null )
         return null;     // not found
      else
         return next.object;
   }
   
   public void append( T object )
   {
      if ( _tail != null )
      {
         _tail.next = new Node( object, null );
         _tail = _tail.next;
      }
      else
      {
         add( object );
      }
   }
   
   //++++++++++++++++++ Node inner class ++++++++++++++++++++++++++++++++
   private class Node
   {
      public Node next = null;
      public T object = null;
      
      public Node( T adder, Node n )
      {
         object = adder;  next = n;
      }
      public boolean compare( T key ) 
      { /*.???..*/ 
         return object.equals( key ); 
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
      
      System.out.println( "Searching for A: " + l.find( "A" ));
      System.out.println( "Searching for C: " + l.find( "C" ));
      System.out.println( "Searching for b: " + l.find( "b" ));
      System.out.println( "Searching for E: " + l.find( "E" ));
      
   }
}
         
