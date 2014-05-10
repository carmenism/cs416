/** 
 * ControlPanel.java:
 * A panel of sliders and other controls for the application
 *
 * @author Matthew Plumlee
 * modified by rdb 
 * 
 * CS416 Spring 2008
 */

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class ControlPanel extends JPanel 
{
   //---------------- instance variables ---------------------------
   private Component  _parent;
   private JLabel     _regexLabel;
   private String     _labelPrefix;
   private RegexApp   _app;
   //------------------- constructor -------------------------------
   /**
    * Container parent of the control panel is passed as an argument
    * and a reference to the application class.
    */
   public ControlPanel( Component parent, RegexApp app ) 
   {
      super( new GridLayout(0, 1) );
      _parent = parent;
      _app    = app;
      _labelPrefix = "Regex: ";
 
      //create misc check boxes and quit button
      JPanel buttonRow = new JPanel();
      
      buttonRow.add( new SplitCountSpinner() );
      
      buttonRow.add( new JLabel( _labelPrefix ));
      _regexLabel = new JLabel( "      " );
      _regexLabel.setBorder( new LineBorder( Color.BLACK ));
      buttonRow.add( _regexLabel );
            
      add( buttonRow );
   }
   //--------------------- setRegexLabel( String ) ----------------------
   /**
    * update the label field showing the current regular expression
    */
   public void setRegexLabel( String newText )
   {
      _regexLabel.setText( newText );
   }
   //+++++++++++++++++++++++ SeedSpinner class ++++++++++++++++++++
   /**
    * private inner class to update the spinner for recursive depth parameter.
    */
   private class SplitCountSpinner extends ParameterSpinner 
   {
      //---------------- constructor --------------------------------------
      public SplitCountSpinner () 
      {
         super( "Split count", 
               -1, _app.maxSplitCount, _app.curSplitCount );
      }
      //------------------------- valueChanged( int ) -----------------------
      public void valueChanged( int newValue ) 
      {
         _app.curSplitCount = newValue;
      }
   }   
}
