/**
 * A generic HashTable implementation that implements Java's Collection
 * interface -- or at least part of it.
 * 
 * implemented methods of Collection:
 *   void add( T )          -- adds T object somewhere in the list
 * 
 *                             removed from the list and its data returned.
 *   boolean ifEmpty()      -- returns true if the list is empty.
 *   int     size()         -- returns the size of the list
 *   int     hashCode()     -- returns the hashCode for the HashTable
 *   
 * 
 * unimplemented methods of Collection
 *   void  clear()
 *   boolean containsAll( Collection<?> c )
 *   boolean equals( Object o )
 *   Iterator<T>  iterator()
 *   boolean remove( Object )
 *   boolean removeAll( Collection<?> )
 *   boolean retainAll( Collection<?> )
 *   Object[] toArray()
 *   <T> T[] toArray( T[] a )
 *  
 * ************************************************************************
 * Use the space below to report performance statistics:
 * ************************************************************************
 * 1. Execution times for the sample input
 * 
 *                        relapsed             traipsed
 *   Java's Hashset       417                  60
 * 
 *   Binary Search        125                  72
 * 
 * 2. distribution statistics for the initial badHash method
 * 
 * ---------- HashTable bucket info ---------
 * Max  Mean   Min   Standard deviation
 * 378  53.0  0   12.437935519620384
 * 
 * HashTable    Len, time: 8 605
 * HashTable    Len, time: 8 407
 *
 * false
 * List size (5) = 5
 * Searching for A: true
 * Searching for C: true
 * Searching for d: false
 * Searching for E: true
 * Searching for F: false
 * Searching for G: false
 * List size (5) = 5
 * 
 * 3. distribution statistics for your revised hash method (named hash)
 * 
 * Size: 53
 * ---------- HashTable bucket info ---------
 * Max  Mean   Min   Standard deviation
 * 1081  1024.0  952   1.0234803634869403
 * HashTable    Len, time: 8 3222
 * HashTable    Len, time: 8 3323
 * 
 * Size: 211
 * ---------- HashTable bucket info ---------
 * Max  Mean   Min   Standard deviation
 * 299  257.0  216   1.032250410398151
 * HashTable    Len, time: 8 1124
 * HashTable    Len, time: 8 1073
 * 
 * Size: 511
 * ---------- HashTable bucket info ---------
 * Max  Mean   Min   Standard deviation
 * 135  106.0  74   0.9810738994730541
 * HashTable    Len, time: 8 767
 * HashTable    Len, time: 8 541
 * 
 * Size: 1023
 * ---------- HashTable bucket info ---------
 * Max  Mean   Min   Standard deviation
 * 82  53.0  32   0.9915576647842869
 * HashTable    Len, time: 8 548
 * HashTable    Len, time: 8 329
 * 
 * 4. Execution times for the sample input
 *                          relapsed             traipsed
 *   HashTable/badHash      605                  407
 * 
 *   HashTable/goodHash     3222                 3323
 *                          1124                 1073
 *                          767                  541
 *                          548                  329
 * 
 */

import java.util.*;

public class HashTable<T> implements Collection<T>
{
   //----------------- instance variables --------------------------
   private int             _size;
   private LinkedList<T>[] _table;   
   
   //---------------- constructor ----------------------------------
   /**
    * create an empty hash table
    */
   public HashTable( int tableSize ) 
   {
      //++++++++++++++++++
      // Create an array of LinkedList objects; because of esoteric
      // Java semantic complications, you cannot specify a generic
      // type in the "new", so you must cast the result to 
      // LinkedList<T>[]
      // before assigning it to _table.
      //-----------------
      _table = (LinkedList<T>[]) new LinkedList[tableSize];
      
      //++++++++++++++++++++
      // Now initialize the _table entries (for loop) to objects of type
      // LinkedList<T>
      //--------------------
      
      for (int i = 0; i < tableSize; i++)
      {
         _table[i] = new LinkedList<T>();
      }
   
   }
   //----------------- tableIndex( String ) ------------------------
   /**
    * map an String based key to a table index
    */
   private int tableIndex( String s )
   {
      //return badHash( s ) % _table.length;
      return hash( s ) % _table.length;
   }
   //----------------- badHash( String ) ------------------------
   /**
    * This is not a very good hash function.
    */
   private int badHash( String s )
   {
      int sum = 0;
      for ( int i = 0; i < s.length(); i++ )
         sum += s.charAt( i );
      return sum;
   }
   //----------------- hash( String ) ------------------------
   /**
    * Write a better one. Instead of just using the character value
    * in the sum, multiply it by 10 raised to the power i: Math.pow( 10, i )
    */
   private int hash( String s )
   {
      int sum = 0;
      for ( int i = 0; i < s.length(); i++ )
         //sum += s.charAt( i );   //<------------- edit this line
         sum += Math.pow(10, i) * s.charAt( i );   //<------------- edit this line
      return sum;
   }
   //----------------- add( T ) -------------------------------
   /**
    * add a T object to the hash table -- as long as it is not already there.
    */
   public boolean add( T newOne )
   {
      if ( contains( newOne ))
         return false;
      else
      {
         //++++++++++++++++++++++++++++++++++++
         // -convert "newOne" t a String (use the toString() method ).
         // -get its corresponding table index (tableIndex method)
         // -add "newOne" to the LinkedList at that index.
         // -increment the _size instance variable.
         //-------------------------------------
         String s = newOne.toString();
         int i = tableIndex(s);
         _table[i].add(newOne);
         _size++;
         return true;
      }
   }
   //----------------- contains( Object ) -------------------------------
   /**
    * returns true if the object passed as an argument is contained
    * in the hash table
    */
   public boolean contains( Object search )
   {
      //++++++++++++++++++++++++++++++++++
      // -convert "search" to a String (use the toString() method ).
      // -get its corresponding table index (tableIndex method)
      // -invoke the "contains" method of the list at that index
      // -return the result
      //------------------------------------
      String s = search.toString();
      int i = tableIndex(s);
      return _table[i].contains(search);
      //return false;
   }
   //----------------- isEmpty( ) -------------------------------
   /**
    * returns true if the list is empty
    */
   public boolean isEmpty()
   {
      return _size == 0;
   }
   //----------------- size( ) -------------------------------
   /**
    * returns the size of the list
    */
   public int size()
   {
      return _size;
   }
 
   //-------------------- report() ----------------------------
   /**
    * printout out lengths of each of bucket lists
    */
   public void report()
   {
      float avg = _size / _table.length;
      int   max = 0, min = _size + 1;
      float sdSum = 0;
      System.out.println( "---------- HashTable bucket info ---------" );
      for ( int i = 0; i < _table.length; i++ )
      {
         int bucketSize = _table[ i ].size();
         if ( bucketSize < min )
            min = bucketSize;
         if ( bucketSize > max )
            max = bucketSize;
         sdSum += Math.pow( avg - bucketSize, 2 );
      }
      double stdDev = Math.sqrt( sdSum / _size );
      System.out.println( "Max  Mean   Min   Standard deviation" );
      System.out.println( max + "  " + avg + "  " + min + "   " + stdDev );
   }
            
   //+++++++++++++++++ unimplemented methods of Collection
  
   public boolean addAll( Collection< ? extends T > c ) { return false; }
   public void  clear(){};
   public boolean containsAll( Collection<?> c ){ return false; }
   public boolean equals( Object o ) { return false; }
   public Iterator<T>  iterator() { return null; }
   public boolean remove( Object o ) { return false; }
   public boolean removeAll( Collection<?> c ) { return false; }
   public boolean retainAll( Collection<?> c ) { return false; }
   public Object[] toArray() { return null; }
   public <T> T[] toArray( T[] a )  { return null; }

   //--------------------- main ----------------------------------
   public static void main( String[] args )
   {
      HashTable<String> l = new HashTable<String>( 53 );
      
      System.out.println( l.contains( "bogus" ));
      l.add( "A" );
      l.add( "B" );
      l.add( "C" );
      l.add( "D" );
      l.add( "E" );
      
      System.out.println( "List size (5) = " + l.size() );
      
      System.out.println( "Searching for A: " + l.contains( "A" ));
      System.out.println( "Searching for C: " + l.contains( "C" ));
      System.out.println( "Searching for d: " + l.contains( "d" ));
      System.out.println( "Searching for E: " + l.contains( "E" ));
      System.out.println( "Searching for F: " + l.contains( "F" ));
      System.out.println( "Searching for G: " + l.contains( "G" ));   
      
      System.out.println( "List size (5) = " + l.size() );   
    }
}
         