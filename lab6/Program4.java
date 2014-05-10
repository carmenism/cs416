/**
 * Lab 5 -- Layout exercise -- show some examples of different layouts.
 * 
 * rdb
 * 02/05/08
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Program4 extends JFrame
{
   //---------------------- instance variables ----------------------
   private LayoutPanel _appPanel;
   
   //--------------------------- constructor -----------------------
   public Program4( String title )     
   {
      super( title );
      
      String[] buttonLabels = {"Start", "Stop", "Faster", "Slower"};
      this.setSize( 600, 450 );
      this.setBackground( Color.LIGHT_GRAY );
      this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
            
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
      
      JSlider slider = new JSlider(JSlider.VERTICAL);
      slider.setMinimum(0);
      slider.setMaximum(450);
      slider.setValue(225);
      slider.addChangeListener(_appPanel);
      
      this.add( slider, BorderLayout.EAST );

      this.setVisible( true );
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
         switch (_buttonId)
         {
            case 0: _appPanel.startMove(); break;
            case 1: _appPanel.stopMove(); break;
            case 2: _appPanel.faster(); break;
            case 3: _appPanel.slower(); break;
         }
         System.out.println( "Button " + _buttonId + "  event." );
      }
   }
         
   //------------------ main ------------------------------------------   
   public static void main( String [ ] args ) 
   {
      new Program4( "Layout Demo" );
   }
}