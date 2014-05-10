/*
 * MyEvent for Lab12 - Carmen St. Jean - October 16, 2008
 */

public class MyEvent implements Comparable<MyEvent>
{
   //---------------------- instance variables ----------------------
   private int time;
   
   //--------------------------- constructor -----------------------
   public MyEvent(int t)     
   {
      time = t;
   }

   public int getTime()
   {
      return time;
   }
   
   public int compareTo(MyEvent e)
   {
      if (time < e.getTime())
         return -1;
      else
         return 1;
   }
}