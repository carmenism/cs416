/**
 * Event.java
 *     Bank Simulation
 * 
 * @author Carmen St. Jean
 *         CS 416 - Fall 2008, October 22, 2008
 */

public class Event implements Comparable<Event>
{
   //------------------------- instance variables ------------------------------
   private int number, time;
   
   //---------------------------------------------------------------------------
   //------------------------- constructor -------------------------------------
   public Event(int n, int t)     
   {
      number = n;
      time = t;
   }
   
   //---------------------------------------------------------------------------
   //------------------------- methods -----------------------------------------
   
   /**
    * public int getTime()
    *    Returns the time.
    */
   public int getTime()
   {
      return time;
   }
   
   //---------------------------------------------------------------------------
   
   /**
    * public int getCustomer()
    *    Returns the customer number.
    */
   public int getCustomer()
   {
      return number;
   }
   
   //---------------------------------------------------------------------------
   
   /**
    * public int compareTo(Event e)
    *    For the Comparable interface.
    */
   public int compareTo(Event e)
   {
      if (time < e.getTime())
         return -1;
      else if (time > e.getTime())
         return 1;
      else
         return 0;
   }
}