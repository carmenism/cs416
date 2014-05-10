/**
 * Lab 10 -- CS416 Fall 2008
 * A generic Stack implementation using Vector 
 *   This implementation associates the "top" of the stack with
 *   index 0 of the Vector. I.e., it puts pushed objects at index 0.
 * You need to revise the implementation so that it adds pushed 
 *    objects to the end of the array.
 **************************************************************************
 * Record below three execution times from the original and modified code.
 * (Later you'll add times for your implementation of the StackList class. 
 * 
 * Original  average times:
 * 
 * Execution 1:
 * Push Time : 4.442
 * Pop Time : 4.938
 * 
 * Execution 2:
 * Push Time : 4.46
 * Pop Time : 5.115
 * 
 * Execution 3:
 * Push Time : 4.481
 * Pop Time : 4.573
 * 
 * Average Push Time: 4.461
 * Average Pop Time: 4.8753
 * 
 * Modified  average times:
 * 
 * Execution 1:
 * Push Time : 0.17
 * Pop Time : 4.456
 * 
 * Execution 2:
 * Push Time : 0.167
 * Pop Time : 4.944
 * 
 * Execution 3:
 * Push Time : 0.186
 * Pop Time : 4.551
 * 
 * Average Push Time: 0.1743
 * Average Pop Time: 4.6503
 * 
 ***************************************************************************
 * @author rdb
 * February 2008
 *   minor additions mlb Fall 2008
 */

import java.util.Vector;

public class Stack<Type> 
{
   //----------------- instance variables ----------------------------
   private Vector<Type> _stack;
  
   //------------------ constructor ---------------------------------
   public Stack()
   {
      _stack = new Vector<Type>();
   }
   
   //------------------ push ---------------------------------
   public void push( Type item )
   {
      _stack.add( item );
   }
   
   //------------------ pop ---------------------------------
   public Type pop()
   {
      Type retVal = null;
      if ( _stack.size() > 0 )
      {
         retVal = _stack.get( 0 );
         _stack.remove( 0 );
      }
      return retVal;
   }
   //------------------ isEmpt() ---------------------------------   
   public boolean isEmpty()
   {
      return _stack.size() == 0;
   }    
   //------------------ size() ---------------------------------
   public int size()
   {
      return _stack.size();
   }
   
   //---------------------  main -----------------------------------
   /**
    * main tests the time to do lots of pushes and pops using this 
    * implementation.
    */
   public static void main( String[] args )
   {
      Stack<String> s = new Stack<String>();
      
      // First some very basic tests to make sure we got it right.
      s.push( "A" );
      s.push( "B" );
      s.push( "C" );

      System.out.println( s.pop() );

      s.push( "D" );
      s.push( "E" );

      System.out.println( s.pop() + " " + s.isEmpty() );
      System.out.println( s.pop() + " " + s.isEmpty());
      System.out.println( s.pop() + " " + s.isEmpty());
      System.out.println( s.pop() + " " + s.isEmpty());
      System.out.println( s.pop() + " " + s.isEmpty());
            
      //------------------- timing tests --------------------------
      // Now we'll do LOTS of pushes and pops and output the elapsed
      // time it all takes.
      //
      int numPush = 100000;    // number pushes
      
      System.out.println( "------------------ Timing test --------------" );
      System.out.println( numPush + "    pushes and pops" );
      System.out.println( "----------------------------------------------" );
                            
      
      long start = System.currentTimeMillis();
      
      //------------- push lots of values onto stack ----------------
      for ( int i = 0; i < numPush; i++ )
      {
          s.push( "A" + i  );
      }
     System.out.println(  s.size() + " pushes done." );
      long time1 =  System.currentTimeMillis();   
      System.out.println( "Time : " + ( (time1 - start) /  1000.0f ) );     
      System.out.println( "Now pop them." );
      //------------- now pop them all off ----------------
      while ( ! s.isEmpty() )
      {
         s.pop();
      }
      long time2 = System.currentTimeMillis();
      System.out.println( "Time : " + ( (time2 - time1) /  1000.0f ) );
   }
}