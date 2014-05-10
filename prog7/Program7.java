/**
 * Program7.java
 *     Bank Simulation
 * 
 * @author Carmen St. Jean
 *         CS 416 - Fall 2008, October 22, 2008
 */

//----------------------- imports ----------------------------------------------
import java.util.PriorityQueue;
import java.util.Random;
import java.util.*;

public class Program7
{
   //------------------------- instance variables ------------------------------
   private PriorityQueue<Event> myEvents;
   private Random myRandom;
   private int customers = 0, totalWaits = 0, totalTrans = 0;

   //---------------------------------------------------------------------------
   //------------------------- constructor -------------------------------------
   public Program7(int seed, int simTime, int arrMin, int arrMax, int tranMin,
                   int tranMax)     
   {
      myEvents = new PriorityQueue<Event>();
      myRandom = new Random(seed);
      createArrivals(arrMin, arrMax, simTime);
      Teller teller = new Teller();
      int finalTime = bankSimulation(teller, tranMin, tranMax);
      printEnd(finalTime);
   }
   
   //---------------------------------------------------------------------------
   //------------------------- methods -----------------------------------------
   
   /**
    * private void createArrivals(int arrMin, int arrMax, int simTime)
    *    Creates arrivals with random times and adds them to the event priority
    *    queue.
    */
   private void createArrivals(int arrMin, int arrMax, int simTime)
   {
      int i = 1;
      int arrTime = myRandom.nextInt(arrMax - arrMin + 1) + arrMin;
      while (arrTime < simTime)
      {
         myEvents.add(new ArrivalEvent(i, arrTime));
         arrTime += myRandom.nextInt(arrMax - arrMin + 1) + arrMin;
         i++;
      }
      customers = i - 1;
   }
   
   //---------------------------------------------------------------------------
   
   /**
    * private int bankSimulation(Teller teller, int tranMin, int tranMax)
    *    Starts the actual bank simulation.
    */
   private int bankSimulation(Teller teller, int tranMin, int tranMax)
   {
      int tranTime, finalTime = 1;
      while (!myEvents.isEmpty())
      {
         Event current = myEvents.remove();
         int customerNum = current.getCustomer();
         int time = current.getTime();
         if (current instanceof ArrivalEvent)
         {
            int startTime = Math.max(teller.getNextAvailable(), time);
            int waitTime = teller.getNextAvailable() - time;
            if (waitTime < 0)
               waitTime = 0;
            totalWaits += waitTime;
            tranTime = myRandom.nextInt(tranMax - tranMin + 1) + tranMin;
            totalTrans += tranTime;
            System.out.println("Customer " + customerNum +
                               ":   Arriving at time " + time + " (Waiting: "
                               + waitTime + ")"); 
            myEvents.add(new ServiceEvent(customerNum, startTime, tranTime));
            teller.setNextAvailable(startTime + tranTime);
         }
         else if (current instanceof ServiceEvent)
         {
            ServiceEvent service = (ServiceEvent) current;
            int d = service.getDuration();
            System.out.println("Customer " + customerNum + 
                               ":   Servicing at time " + time +
                               " (Service Time: " + d + ")");
            myEvents.add(new DepartureEvent(customerNum, time+d));
         }
         else if (current instanceof DepartureEvent)
         {
            System.out.println("Customer " + customerNum + 
                               ":   Departing at time " + time);
            finalTime = time;
         }
      }
      return finalTime;
   }

   //---------------------------------------------------------------------------
   
   /**
    * private void printEnd(int finalTime)
    *    Prints the total customers, average weight time, and teller busy time.
    */
   private void printEnd(int finalTime)
   {
      float averageWait = (float)totalWaits / customers;
      float tellerBusy = (float)totalTrans / finalTime * 100;
      System.out.println("Summary\n  Number of customers " + customers);
      System.out.println("  Average waiting time  " + averageWait + " minutes");
      System.out.println("    Teller Busy " + tellerBusy);
   }

   //---------------------------------------------------------------------------
   //------------------------- TELLER CLASS ------------------------------------

   public class Teller
   {
      private int nextAvailable;
      public Teller()
      {
         nextAvailable = 0;
      }
      public void setNextAvailable(int n)
      {
         nextAvailable = n;
      }
      public int getNextAvailable()
      {
         return nextAvailable;
      }
   }
   
   //---------------------------------------------------------------------------
   //------------------------- ARRIVAL EVENT CLASS -----------------------------
   
   public class ArrivalEvent extends Event
   {
      public ArrivalEvent(int n, int t)
      {
         super(n, t);
      }
   }
 
   //---------------------------------------------------------------------------
   //------------------------- DEPARTURE EVENT CLASS ---------------------------
   
   public class DepartureEvent extends Event
   {
      public DepartureEvent(int n, int t)
      {
         super(n, t);
      }
   } 
   
   //---------------------------------------------------------------------------
   //------------------------- SERVICE EVENT CLASS -----------------------------
   
   public class ServiceEvent extends Event
   {
      private int duration;
      public ServiceEvent(int n, int t, int d)
      {
         super(n, t);
         duration = d;
      }
      public int getDuration()
      {
         return duration;
      }
   }
   
   //---------------------------------------------------------------------------
   //------------------------- main --------------------------------------------
   
   public static void main( String [ ] args ) 
   {
      int seed = 1, simTime = 1, arrMin = 1, arrMax = 1,
          tranMin = 1, tranMax = 1;
      System.out.println("Enter random number seed:");
      Scanner scanIn = new Scanner(System.in);
      if (scanIn.hasNextInt())
      {
         seed = scanIn.nextInt();
      }
      System.out.println("Enter simulation time:");
      scanIn = new Scanner(System.in);
      if (scanIn.hasNextInt())
      {
         simTime = scanIn.nextInt();
      }  
      System.out.println("Enter minimum and maximum arrival times:");
      scanIn = new Scanner(System.in);  
      if (scanIn.hasNextInt())
      {
         arrMin = scanIn.nextInt();
      }
      if (scanIn.hasNextInt())
      {
         arrMax = scanIn.nextInt();
      }  
      System.out.println("Enter minimum and maximum transaction times:");
      scanIn = new Scanner(System.in);  
      if (scanIn.hasNextInt())
      {
         tranMin = scanIn.nextInt();
      }
      if (scanIn.hasNextInt())
      {
         tranMax = scanIn.nextInt();
      }  
      new Program7(seed, simTime, arrMin, arrMax, tranMin, tranMax);
   }
}