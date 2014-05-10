/**
 * Lab 5 -- Layout exercise -- show some examples of different layouts.
 * 
 * rdb
 * 02/05/08
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Lab5 extends JFrame
{
   //---------------------- instance variables ----------------------
   private LayoutPanel _appPanel;
   
   //--------------------------- constructor -----------------------
   public Lab5( String title )     
   {
      super( title );
      
      String[] buttonLabels = {"Start", "Stop", "Reverse x", "Reverse y"};
      this.setSize( 600, 450 );
      this.setBackground( Color.LIGHT_GRAY );
      this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
      

  //    this.add( new JButton( "West" ), BorderLayout.WEST );
  //    this.add( new JButton( "East" ), BorderLayout.EAST );
  //    this.add( new JButton( "North" ), BorderLayout.NORTH );
      
      _appPanel = new LayoutPanel( this );
      this.add( _appPanel );
      
      JPanel bMenu = new JPanel();
      int n = 4;
      for ( int i = 0; i < n; i++ )
      {
         JButton button = new JButton( buttonLabels[i]);
         button.addActionListener( new ButtonListener( i ));
         bMenu.add( button );
      }
      this.add( bMenu, BorderLayout.SOUTH );
       
      
      Component buttonMenu = makeButtonMenu( buttonLabels );
      
   //   this.add( buttonMenu, BorderLayout.SOUTH );
      this.setVisible( true );
   }
   //------------------- makeButtonMenu --------------------------------
   private Component makeButtonMenu( String[] labels )
   {
      // JPanel defaults to FlowLayout
      
      JPanel bMenu = new JPanel(); 
      JButton button;
      for ( int i = 0; i < labels.length; i++ )
      {
         button = new JButton( labels[ i ] );
         bMenu.add( button );
      }
      
      return bMenu;
   }
   
   public class ButtonListener implements ActionListener
   {
      int _buttonId;
      public ButtonListener( int buttonId )
      {
         _buttonId = buttonId;
      }
      public void actionPerformed( ActionEvent ev )
      {
         JButton button = (JButton) ev.getSource();
         if ( button.getBackground().equals( Color.BLUE ) )
         {
            button.setBackground( Color.RED );
            System.out.println("Change from blue to red");
         }
         else
         {
            button.setBackground( Color.BLUE );
            System.out.println("Change from red to blue");
         }
         switch (_buttonId)
         {
            case 0: _appPanel.startMove(); break;
            case 1: _appPanel.stopMove(); break;
            case 2: _appPanel.invertX(); break;
            case 3: _appPanel.invertY(); break;
         }
         System.out.println( "Button " + _buttonId + "  event." );
      }
   }
         
   //------------------ main ------------------------------------------   
   public static void main( String [ ] args ) 
   {
      new Lab5( "Layout Demo" );
   }
}