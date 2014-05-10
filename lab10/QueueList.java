/**
 * Lab 10 -- CS416 Fall 2008
 * A generic QueueList implementation using Vector 
 *   This implementation associates the back of the queue with
 *   index 0 of the Vector. I.e., it puts new objects at index 0.
 *   and takes objects off the queue from the end of the array.
 **************************************************************************
 * Record below three execution times from the original and modified code.
 * (Later you'll add times for your implementation of the QueueListList class. 
 * 
  * Original  average times: 
 * 
 * Modified  average times:
 * 
 * Execution 1:
 * Add Time : 0.273
 * Remove Time : 0.031
 * 
 * Execution 2:
 * Add Time : 0.206
 * Remove Time : 0.031
 * 
 * Execution 3:
 * Add Time : 0.24
 * Remove Time : 0.033
 *
 ***************************************************************************
 * @author rdb
 * February 2008
 * minor additions mlb Fall 2008
 */

import java.util.Vector;

public class QueueList <Elem>
{
   //----------------- instance variables ------------------------------
   private LinkedList<Elem> _queue;
   
   //--------------------- constructor ---------------------------------
   public QueueList()
   {
      //<******************* edit code here *************
      _queue = new LinkedList<Elem>();
   }  
   //------------------------- add( Elem ) ------------------------------
   public void add( Elem item )
   {
      //<******************* edit code here *************
      _queue.append( item );
   } 
   //----------------------- remove() ------------------------------------
   public Elem remove()
   {
      return _queue.remove(); //<******************* edit code here *************
   }
   //------------------ isEmpty() ---------------------------------   
   public boolean isEmpty()
   {
      return _queue.isEmpty(); //<******************* edit code here *************
   }    
   //------------------ size() ---------------------------------
   public int size()
   {
      return _queue.size(); //<******************* edit code here *************
   }
    
   //----------------- main ---------------------------------------
   public static void main( String[] args )
   {
      // first some very simple tests 
      QueueList<String> s = new QueueList<String>();
      
      s.add( "A" );
      s.add( "B" );
      s.add( "C" );
      
      System.out.println( s.remove() );
      
      s.add( "D" );
      s.add( "E" );
      
      System.out.println( s.remove() + " " + s.isEmpty() );
      System.out.println( s.remove() + " " + s.isEmpty());
      System.out.println( s.remove() + " " + s.isEmpty());
      System.out.println( s.remove() + " " + s.isEmpty());
      System.out.println( s.remove() + " " + s.isEmpty());
      //------------------- timing tests --------------------------
      // Now we'll do LOTS of pushes and pops and output the elapsed
      // time it all takes.
      //
      int numAdd = 100000;    // number pushes
      
      System.out.println( "------------------ Timing test --------------" );
      System.out.println( numAdd + "    adds and removes" );
      System.out.println( "----------------------------------------------" );
                            
      
      long start = System.currentTimeMillis();
      
      //------------- add lots of values onto stack ----------------
      for ( int i = 0; i < numAdd; i++ )
      {
          s.add( "A" + i  );
      }
          System.out.println(  s.size() + " adds done." );
      long time1 =  System.currentTimeMillis();   
      System.out.println( "Time : " + ( (time1 - start) /  1000.0f ) );     
      System.out.println( "Now remove them." );
      //------------- now remove them all off ----------------
      while ( ! s.isEmpty() )
      {
         s.remove();
      }
   long time2 = System.currentTimeMillis();
      System.out.println( "Time : " + ( (time2 - time1) /  1000.0f ) );    
   }
}