/**
 * RegexApp - the main class for controlling the application code
 *             for P12.
 * CS416
 * Fall 2008 416
 * mlb
 */
import java.util.*;
import java.util.regex.*;
import java.io.*;

public class RegexApp
{
    //--------------------- instance variables ----------------------------
    private String dictionary;
    
    private Vector<String>   patterns;       
    private Vector<String>   labels;       
    private int   nxtPattern = 0; 
    private Pattern pattern;   
    
    private static final String DICTIONARY = "WORD.LST";
    private static final String PATTERNS = "patterns.txt";
    
    //---------------------- constructor ----------------------------------
    /**
     */
    public RegexApp()
    {
        patterns  = new Vector<String>();
        labels  = new Vector<String>();
        
        readDicFile();
        readPatternsFile();
        
        for( int i = 0; i < labels.size() ; i++)
        {
            System.out.println("\n*********************");
            System.out.println( "Problem" + labels.get( i ) );
            pattern = Pattern.compile( patterns.get( i ), Pattern.MULTILINE );
            findAll( );              
        }
    }
    
    //---------------------- readDicFile() -----------------------------
    /**
     * read dictionary
     */
    public void readDicFile( )
    {    
        File newIn = new File( DICTIONARY );
        StringBuffer contents = new StringBuffer();
        try
        {
            Scanner in = new Scanner( newIn );
            while ( in.hasNextLine() )
            {
                String line = in.nextLine();
                contents.append( line + "\n" );
            }           
            dictionary= new String( contents );
            System.out.println( dictionary.length() );
        }
        catch ( IOException ioe )
        {
            System.out.println( "Dictionary File::IOException " 
                                   + ioe.getMessage() );
            System.exit( 0 );
        }    
    }
    
    //---------------------- readPatternsFile() -----------------------------
    /**
     * read patterns
     */
    public void readPatternsFile( )
    {
        File newIn = new File( PATTERNS );
        
        try
        {
            Scanner in = new Scanner( newIn );
            while ( in.hasNextLine() )
            {
                String n = in.nextLine().trim();
                if( n.startsWith("//") )
                    labels.add( n.substring(2) );
                else if( n.length() > 0  )
                    patterns.add( n );      
            }                  
        }
        catch ( IOException ioe )
        {
            System.out.println( "Pattern File::IOException " 
                                   + ioe.getMessage() );
            System.exit( 0);
        }    
    }
    
    
    // ------------------ findAll() ------------------------
    /**
     * Given the current r.e. pattern, try to find that pattern in the
     * input data; if successful, try again and again until it fails.
     */
    public void findAll()
    {         
        if ( pattern != null )
        {
            System.out.println( "findAll pattern: " + pattern );
            StringBuffer results = new StringBuffer();
            Matcher m = pattern.matcher( dictionary );
            
            while ( m.find() )
            {      
                results.append(m.group() +"\n" );           
            }
            
            System.out.println( new String( results ) );
        }
    }
    
    //--------------------- main -----------------------------------------
    public static void main( String[] args )
    {
        new RegexApp(  );
    }
}