/**
 * Lab 10 -- CS416 Fall 2008
 * A generic Queue implementation using Vector 
 *   This implementation associates the back of the queue with
 *   index 0 of the Vector. I.e., it puts new objects at index 0.
 *   and takes objects off the queue from the end of the array.
 **************************************************************************
 * Record below three execution times from the original and modified code.
 * (Later you'll add times for your implementation of the QueueList class. 
 * 
 * Original  average times: 
 * 
 * Add Time : 0.161
 * Remove Time : 4.42
 * 
 * Add Time : 0.2
 * Remove Time : 4.565
 * 
 * Add Time : 0.22
 * Remove Time : 4.438
 * 
 * Modified  average times:
 * 
 * Add Time : 4.436
 * Remove Time : 0.063
 * 
 * Add Time : 4.499
 * Remove Time : 0.07
 * 
 * Add Time : 4.423
 * Remove Time : 0.064
 * 
 ***************************************************************************
 * @author rdb
 * February 2008
 minor additions mlb Fall 2008
 */

import java.util.Vector;

public class Queue <Elem>
{
   //----------------- instance variables ------------------------------
   private Vector<Elem> _queue;
   
   //--------------------- constructor ---------------------------------
   public Queue()
   {
      _queue = new Vector<Elem>();
   }  
   //------------------------- add( Elem ) ------------------------------
   public void add( Elem item )
   {
      _queue.add( 0, item );
   } 
   //----------------------- remove() ------------------------------------
   public Elem remove()
   {
      Elem retVal = null;
      if ( _queue.size() > 0 )
      {
         retVal = _queue.get( _queue.size() - 1 );
         _queue.remove( _queue.size() - 1 );
      }
      return retVal;
   }
   //---------------------- isEmpty() -----------------------------------
   public boolean isEmpty()
   {
      return _queue.size() == 0;
   }
   //------------------ size() ---------------------------------
   public int size()
   {
      return _queue.size();
   }
   
   //----------------- main ---------------------------------------
   public static void main( String[] args )
   {
      // first some very simple tests 
      Queue<String> s = new Queue<String>();
      
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