import java.util.*;
import java.io.*;
import java.net.*;

public class ReadWebChar                                         
{ 
   private final static String DICT_URL = "http://www.cs.unh.edu/~cs416/public/test1.txt";
   private  String url;
   private int [] charFreq;
  
   //--------------------------------------------------------------------------------------
   public ReadWebChar( String u )
   {
      url = u;       
      charFreq = new int[128];
      for (int i = 0; i < 128; i++)
         charFreq[i] = 0;
      readFreq ( );
      printFreq ( );
   }
   
   //--------------------------------------------------------------------------------------
   private void readFreq(  )     
   {
      URLConnection connection = null;  
      BufferedReader infile = null;
      try {
         connection =  new URL(url).openConnection();
      } catch (IOException e){
         System.err.println( "***Error -- can't open url: " + url );
         System.err.println( "***Opening default: " + DICT_URL );     
         try {
            connection =  new URL(DICT_URL).openConnection();
         } catch (IOException fe) {
            System.err.println( "***Error -- can't open url: " + DICT_URL );        
            System.exit( - 1 );
         }
      }     
      try{
         infile =  new BufferedReader(
                   new InputStreamReader( connection.getInputStream()));
      }
      catch ( IOException e ){
         System.err.println( "***Error -- can't create buffered reader" );
         System.exit( - 1 );
      }
      try{
         
         // ********************************
         // Finally we can read characters!!
         int i = infile.read( );        
         while(  i != -1 ){                       
           // System.out.println( (char) i );
            charFreq[i]++;
            i = infile.read(  );
         }  
         
         
         
      }
      catch( Exception e){
         System.out.println( "Read error " + e);  
      }        
   }

   public void printFreq( )
   {
      for (int i = 0; i < charFreq.length; i++)
      {
         if (charFreq[i] != 0)
         {
            if (i > 32)
            {
               System.out.println("ASCII: " + i +
                                  "   Char: " + (char)i +
                                  "   Freq: " + charFreq[i]);
            }
            else if (i == 32)
            {
               System.out.println("ASCII: " + i +
                                  "   Char: space" +
                                  "   Freq: " + charFreq[i]);
            }
            else if (i == 10)
            {
               System.out.println("ASCII: " + i +
                                  "   Char: line feed" +
                                  "   Freq: " + charFreq[i]);
            }
            else
               System.out.println(i);
         }
      }
   }
   
   //--------------------------------------------------------------------------------------
   public static void main(String[] args)
   {   
      String file = DICT_URL;
      
      if ( args.length > 0 )
      {
         file = args[0];
      }            
      new ReadWebChar( file );
      
   }    
}
