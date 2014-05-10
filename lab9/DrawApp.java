/**
 *  Tilt a T
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

public class DrawApp extends JFrame implements ActionListener 
{
   //---------------------- instance variables ----------------------
   private final int WIDTH = 500;
   private final int HEIGHT = 500;
   
   private JButton increase;
   private DrawPanel drawPanel;
   private JPanel panel, tools;
   
   //--------------------------- constructor -----------------------
   public DrawApp( String title, String[] args )    
   {
      super( title );
      
      tools = new JPanel ();
      tools.setLayout (new BoxLayout(tools, BoxLayout.X_AXIS));
      tools.setPreferredSize (new Dimension (WIDTH, 40));
      
      increase = new JButton ("Tilt");
      
      increase.setMargin (new Insets (0, 0, 0, 0));
      increase.addActionListener (this);
      tools.add (increase);
      
      drawPanel = new DrawPanel ( );
      
      panel = new JPanel();
      panel.add (tools);
      panel.add (drawPanel);
      
      getContentPane().add (panel);
      setSize (WIDTH, HEIGHT);
      
      this.setVisible( true );
   }
   
   //-----------------------------------------------------------------
   //-----------------------------------------------------------------
   public void actionPerformed (ActionEvent event)
   {
      drawPanel.tilt ();
      repaint();
   }
   
   
   //------------------ main ------------------------------------------   
   public static void main( String [ ] args ) 
   {
      new DrawApp( "Tilt a T", args );
   }
}