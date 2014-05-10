/*
 * Lab12 - Carmen St. Jean - October 16, 2008
 */

import java.util.PriorityQueue;
import java.util.Random;

public class Lab12
{
   //---------------------- instance variables ----------------------
   private PriorityQueue<MyEvent> myEvents;
   private Random myRandom;
   
   //--------------------------- constructor -----------------------
   public Lab12( )     
   {
      myEvents = new PriorityQueue<MyEvent>();
      myRandom = new Random(1);
      
      System.out.println("Add MyEvent objects");
      
      for (int i = 0; i < 20; i++)
      {
         int time = myRandom.nextInt(50) + 50;
         myEvents.add(new MyEvent(time));
         System.out.println("MyEvent " + i + " time: " + time);
      }
      
      System.out.println("\n---------\n\nRemove MyEvent objects");
      
      for (int j = 0; j < 20; j++)
      {
         int time = myEvents.remove().getTime();
         System.out.println("MyEvent " + j + " time: " + time);
      }
   }
   
   //------------------ main ------------------------------------------   
   public static void main( String [ ] args ) 
   {
      new Lab12( );
   }
}