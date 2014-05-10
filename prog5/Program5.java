/**
 * Program5.java
 *     Koch Snowflake Fractal
 * 
 * @author Carmen St. Jean
 *         CS 416 - Fall 2008, October 7, 2008
 */

//----------------------- imports ----------------------------------------------
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

public class Program5 extends JFrame implements ActionListener 
{
   //---------------------- instance variables ---------------------------------
   private final int WIDTH = 600;
   private final int HEIGHT = 600;
   private LayoutPanel drawPanel;
   private JPanel panel;
   private JLabel topLabel;
   private int order = 1;
   
   //---------------------------------------------------------------------------
   //------------------------- constructor -------------------------------------
  
   public Program5( String title, String[] args )    
   {
      super( title );
      drawPanel = new LayoutPanel(  );
      panel = new JPanel();
      panel.add(drawPanel);
      
      String [] buttonLabels = {"Increase", "Decrease"};
      JPanel bMenu = new JPanel();
      int n = 2;
      for (int i = 0; i < n; i++)
      {
         JButton button = new JButton( buttonLabels[i]);
         button.addActionListener(new ButtonListener(i));
         bMenu.add(button);
      }
      this.add(bMenu, BorderLayout.SOUTH);
      topLabel = new JLabel("", JLabel.CENTER);
      this.updateTop(order);
      this.add(topLabel, BorderLayout.NORTH);
      
      getContentPane().add (panel);
      setSize (WIDTH, HEIGHT);
      this.setVisible( true );
   }
   
   //---------------------------------------------------------------------------
   //------------------------- methods -----------------------------------------
   
   /**
    * public void updateTop(int o)
    *    Updates the text label at the north-end of the window to display the
    *    current order.
    */
   public void updateTop(int o)
   {
      topLabel.setText("Order: " + o);
   }
   
   //---------------------------------------------------------------------------
   
   /**
    * public void actionPerformed (ActionEvent event)
    *    The compiler screamed at me when this method wasn't there.
    */
   public void actionPerformed (ActionEvent event)
   {
      repaint();
   }
   
   //---------------------------------------------------------------------------
   //------------------------- ButtonListener ----------------------------------
   
   /**
    * public class ButtonListener implements ActionListener
    *    This is for the two buttons Increase and Decrease.
    */
   public class ButtonListener implements ActionListener
   {
      int _buttonId;
      public ButtonListener(int buttonId)
      {
         _buttonId = buttonId;
      }
      public void actionPerformed(ActionEvent ev)
      {
         JButton button = (JButton) ev.getSource();
         switch (_buttonId)
         {
            case 0:
            {
               if (order < 9)
               {
                  order++;
                  updateTop(order);
                  drawPanel.setOrder(order);
               }
               break;
            }
            case 1:
            {
               if (order > 1)
               {
                  order--;
                  updateTop(order);
                  drawPanel.setOrder(order);
               }
               break;
            }
         }
      }
   }
   
   //----------------------- main ----------------------------------------------
   
   public static void main( String [ ] args )
   {
      new Program5( "Koch Snowflake", args );
   }
}