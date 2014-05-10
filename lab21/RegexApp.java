/**
 * RegexApp - the main class for controlling the application code. 
 * 
 * 
 * The application semantics including the graphics generated 
 * by the app are controlled from this class.
 */
import javax.swing.*;
import java.util.*;
import java.util.regex.*;
import java.io.*;
import java.text.*;
   
public class RegexApp
{
   //--------------------- instance variables ----------------------------
   private DisplayPanel     _display;
   private ControlPanel     _controls;
   private JFileChooser     _chooser;
  
   private Pattern          _pattern;
   
   private String           _regex;    // the currently active reg expr
   private String           _testString;
   
   private PrintWriter      _log;
   
   //------ "package" state variables; set by GUI ----------
   boolean printMode     = true;  // if set, print after each command
   
   int     maxSplitCount = 100;
   int     curSplitCount = 4;
      
   //---------------------- constructor ----------------------------------
   /**
    * The app needs the display reference only so it can update the 
    * DisplayListPanel with new Lists when needed and tell it to redraw
    * when things change.
    */
   public RegexApp( DisplayPanel display )
   {
      _display = display;
      _controls = null;
      File f = new File( "." );
      System.out.println( "File( . ): " + f.getAbsolutePath() );
      
      _chooser = new JFileChooser( new File( "." ));
      _chooser.setCurrentDirectory( new File( "." ));
      try 
      {
         File logFile = new File( "Lab21.log" );
         if ( !logFile.exists() )
            logFile.createNewFile();
         
         FileWriter fw = new FileWriter( logFile, true );  // append
         _log = new PrintWriter( fw, true );
         
         log( "-------------------------------", "" );
      }
      catch ( IOException ioe ) 
      {
         System.out.println( "RegexApp catch: " + ioe.getMessage() );
      }
   }
   /**
    * log information in the log file
    */
   private void log( String separator, String data )
   {
      _log.println( separator + dateString() + separator );
      _log.println( data );
      _log.println( separator + separator + separator );
   }
   /**
    * return the current date/time as a string
    */
   private String dateString()
   {
         Date d = new Date();
         DateFormat fmt = new SimpleDateFormat();
         return fmt.format( d );
   }
      
   /**
    * setControlPanel
    */
   public void setControlPanel( ControlPanel controls )
   {
      _controls = controls;
   }
   //---------------------- update() -----------------------------
   /**
    * Something in gui has change; update whatever needs to be updated
    * The GUI calls the app when a GUI update occurs; the app (in this
    * case) only needs to pass this along to the DisplayListPanel.
    */
   public void update()
   { 
   }
   //+++++++++++++++++++++ methods invoked by GUI buttons +++++++++++++++++++
   //---------------------- newRegex() -----------------------------
   /**
    * generate a new DataList 
    */
   public void newRegex( )
   {  
      _regex = JOptionPane.showInputDialog( null, 
                                       "Enter regular expression" ); 
      if ( _regex != null )
      {
         _pattern = Pattern.compile( _regex, Pattern.MULTILINE );//| Pattern.DOTALL );
         _controls.setRegexLabel( _regex );
         log( "++++++++++++++++++", _regex );
         _display.setMatch( " " );
      }
   }
   //------------------ newInputString() ------------------------
   /**
    * Enter an input string in a 
    */
   public void newInputString()
   {
      _testString = JOptionPane.showInputDialog( null, 
                                       "Enter input string" );
      if ( _testString != null )
      {
         _display.setInput( _testString );   
         _display.setMatch( " " );
      }
   }
   //------------------ findAll() ------------------------
   /**
    * 
    */
   public void findAll()
   {
      if ( _pattern != null )
      {
         String results = new String();
         boolean found;
         Matcher m = _pattern.matcher( _testString );
         
         found = m.find();
         while ( found )
         {
            results += m.group() + "<eol>\n";
            found = m.find();
         }
         _display.setMatch( results );
         log( "@@@@@@@@@@@@@@@@@@@", results );
      }
   }
   //------------------ split() ------------------------
   /**
    * split the input string according to the r.e.
    */
   public void split()
   {
      if ( _pattern != null )
      {
         String[] parts = _pattern.split( _testString );
         String splitMatch = "";
         for ( int i = 0; i < parts.length; i++ )
            splitMatch += parts[ i ] + "\n";
         _display.setMatch( splitMatch );
         log( "====================", splitMatch );
      }
   }
   //---------------------- newInputFile() -----------------------------
   /**
    * read input strings from a file 
    */
   public void newInputFile( )
   {
      int returnval = _chooser.showOpenDialog( null );
      if ( returnval == JFileChooser.APPROVE_OPTION )
      {
         File newIn = _chooser.getSelectedFile();
         _log.println( "---------\n" + newIn.getName() + "\n---------" );
         StringBuffer contents = new StringBuffer();
         try
         {
            Scanner in = new Scanner( newIn );
            while ( in.hasNextLine() )
               contents.append( in.nextLine() + "\n" );
            _testString = new String( contents );
            _display.setInput( _testString );
            _display.setMatch( " " );
         }
         catch ( IOException ioe )
         {
            System.out.println( "newInputFile::IOException " + ioe.getMessage() );
         }
      }
   }
   //---------------------- reset() -----------------------------
   /**
    * reset the regular expression procession on the current input string.
    */
   public void reset( )
   {  
   }
   
   //++++++++++++++++++++++ utility methods +++++++++++++++++++++++++++++++++++++
   // You should think about more of these
   //
   //----------------------- stringToInt( String ) -----------------------------
   /**
    * Convert string to integer
    */
   private int stringToInt( String str, int defaultVal )
   {
      try 
      {
         return Integer.parseInt( str );
      }
      catch ( NumberFormatException nfe )
      {
         String input = JOptionPane.showInputDialog( null, 
                                     "Invalid integer input: " + str + ". No change." );
         return defaultVal;
      }
   }
}
