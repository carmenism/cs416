/**
* Lab11 - Data structure manipulation application
 *        Choose between Q - queue
 *                       S - stack
 *                       A - alphabetic linked listP
 * 
 * This class creates a ListPanel and builds a menu of 4 buttons.
 * Each button results in a call to a method of the ListPanel.
 * 
 * rdb
 * 03/03/08
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

public class Lab11 extends JFrame
{
   //---------------------- instance variables ----------------------
   private ListPanel _appPanel;
   
   //--------------------------- constructor -----------------------
   public Lab11( String title, String[] args )     
   {
      super( title );
 
      String[] buttonLabels = { "TogglePrint", "Add", "Remove", "Remove( x )" };
      this.setBackground( Color.WHITE );
      this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
      
      _appPanel = new ListPanel( this );
      this.add( _appPanel );
      
      Component buttonMenu = makeButtonMenu( buttonLabels );
      this.add( buttonMenu, BorderLayout.NORTH );
      
      this.setPreferredSize( new Dimension( _appPanel.getWidth(), 100 ));
      this.pack();
      this.setVisible( true );
   }
   //------------------- makeButtonMenu --------------------------------
   private Component makeButtonMenu( String[] labels )
   {
      // JPanel defaults to FlowLayout
      
      JPanel bMenu = new JPanel( new GridLayout( 1, 0 )); 
      JButton button;
      for ( int i = 0; i < labels.length; i++ )
      {
         button = new JButton( labels[ i ] );
         bMenu.add( button );
         button.addActionListener( new ButtonListener( i ));
      }      
      return bMenu;
   }
   //+++++++++++++++++ ButtonListener inner class ++++++++++++++++++++++++
   /**
    * ButtonListener handles all button events and passes them along
    * to methods of the ListPanel class.
    */
   public class ButtonListener implements ActionListener
   {
      int _buttonId;
      public ButtonListener( int buttonId )
      {
         _buttonId = buttonId;
      }
      public void actionPerformed( ActionEvent ev )
      {
          switch ( _buttonId )
          {
             case 0:
                _appPanel.togglePrint();
                break;
             case 1:
                _appPanel.addItem();
                break;
             case 2:
                _appPanel.removeItem();
                break;
             case 3:
                _appPanel.removeItemByKey();
                break;
           }               
      }
   }         
   //------------------ main ------------------------------------------   
   public static void main( String [ ] args ) 
   {
      new Lab11( "List Demo", args );
   }
}