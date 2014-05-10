/**
 * Lab 10 -- CS416 Fall 2008
 * A generic StackList implementation using LinkedList. This is just a 
 * skeleton; you need to add the implementation.
 * 
 * Original  average times: 
 * 
 * Modified  average times:
 * 
 * Execution 1:
 * Push Time : 0.219
 * Pop Time : 82.533
 * 
 * Execution 2:
 * Push Time : 0.194
 * Pop Time : 82.301
 * 
 * Execution 3:
 * Push Time : 0.217
 * Pop Time : 81.441
 * 
 * @author rdb
 * February 2008
 * minor additions mlb Fall 2008
 */

public class StackList<Type>
{
   //----------------- instance variables ----------------------------
   private LinkedList<Type> _stack;
  
   //------------------ constructor ---------------------------------
   public StackList()
   {
      //<******************* edit code here *************
      _stack = new LinkedList<Type>();
   }
   
   //------------------ push ---------------------------------
   public void push( Type item )
   {
      //<******************* edit code here *************
      _stack.add( item );
   }
   
   //------------------ pop ---------------------------------
   public Type pop()
   {
      Type retVal = null;
    //  if ( _stack.size() > 0 )
   //   {
         retVal = _stack.remove( );
    //  }
      return retVal;
      //<******************* edit code here *************
   }
   //------------------ isEmpty() ---------------------------------   
   public boolean isEmpty()
   {
      return _stack.size() == 0;
      //<******************* edit code here *************
   }    
   //------------------ size() ---------------------------------
   public int size()
   {
      return _stack.size();
      //<******************* edit code here *************
   }
   
   //---------------------  main -----------------------------------
   /**
    * main tests the time to do lots of pushes and pops using this 
    * implementation.
    */
   public static void main( String[] args )
   {
      StackList<String> s = new StackList<String>();
      
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