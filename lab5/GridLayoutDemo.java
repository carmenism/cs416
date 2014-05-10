/**
 * Layout demo -- show some examples of different layouts.
 * 
 * rdb
 * 02/05/08
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GridLayoutDemo extends JFrame
{
   public GridLayoutDemo( String title )     
   {
      super( title );
      this.setSize( 100, 150 );
      this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
      
      JPanel gridPanel = new JPanel( new GridLayout( 2, 3 ));
      this.add( gridPanel );
      
      int n = 6;
      for ( int i = 0; i < n; i++ )
      {
         JButton button = new JButton( String.valueOf( i ));
         button.addActionListener( new ButtonListener( i ));
         button.setBackground( Color.BLUE );
         button.setIcon( new MyIcon( ));
         gridPanel.add( button );
      }
              
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
         if ( button.getBackground().equals( Color.BLUE ) )         
            button.setBackground( Color.RED );
         else
            button.setBackground( Color.BLUE );
         System.out.println( "Button " + _buttonId + "  event." );
      }
   }
         
   public class MyIcon implements Icon
   {
      public int getIconWidth() 
      {
         return 8;     // No idea!!!
      }
      public int getIconHeight()
      {
         return 6;     // ???
      }
      public void paintIcon( Component c, Graphics g, int x, int y )
      {
         Graphics2D g2 = (Graphics2D) g;
         Color saveColor = g2.getColor();
         g2.setColor( Color.CYAN );
         g2.fillOval( 6, 6, c.getWidth() - 12, c.getHeight() - 12 );
         g2.setColor( Color.BLACK );
         
         g2.drawString( "+", x, y );
         g2.setColor( saveColor );
      }
   }
   //------------------ main ------------------------------------------   
   public static void main( String [ ] args ) 
   {
      new GridLayoutDemo( "GridLayoutDemo Demo" );
   }
}