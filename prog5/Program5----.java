/**
 * Program5.java
 * 
 * To make this game more exciting/complicated, I've added an "Add Bumpers"
 * button, which will place four bumpers in the screen which the puck can
 * bounce off of.
 * 
 * @author Carmen St. Jean
 *         CS 416 - Fall 2008, September 29, 2008
 */

//----------------------- imports ----------------------------------------------
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Program5 extends JFrame
{
   //------------------------- instance variables ------------------------------
   private LayoutPanel _appPanel;
   private JLabel scores;
   
   //---------------------------------------------------------------------------
   //------------------------- constructor -------------------------------------
   
   public Program5(String title)     
   {
      super(title);
      
      String [] buttonLabels = {"Increase", "Decrease"};
      this.setSize(600, 450);
      this.setBackground(Color.LIGHT_GRAY);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
      _appPanel = new LayoutPanel(this);
      this.add(_appPanel);
      
      JPanel bMenu = new JPanel();
      int n = 2;
      for (int i = 0; i < n; i++)
      {
         JButton button = new JButton( buttonLabels[i]);
         button.addActionListener(new ButtonListener(i));
         bMenu.add(button);
      }
      this.add(bMenu, BorderLayout.SOUTH);   
      
      scores = new JLabel("", JLabel.CENTER);
      this.updateScore(0, 0);
      this.add(scores, BorderLayout.NORTH);

      this.setVisible(true);
      _appPanel.setPreferredSize(new Dimension(600,450));
      this.pack();
   }
   
   //---------------------------------------------------------------------------
   //------------------------- methods -----------------------------------------
   
   /**
    * public void updateScore(int left, int right)
    *    Updates the text label at the north-end of the window to display the
    *    current score.
    */
   public void updateScore(int left, int right)
   {
      scores.setText("SCORE:  computer: " + left + ",  you: " + right);
   }
   
   //---------------------------------------------------------------------------
   //------------------------- ButtonListener ----------------------------------
   
   /**
    * public class ButtonListener implements ActionListener
    *    This is for the four buttons in the bMenu: Start, Stop, Faster, and
    *    Slower.
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
            case 0: _appPanel.increase(); break;
            case 1: _appPanel.decrease(); break;
         }
      }
   }
         
   //------------------------- main method -------------------------------------
   public static void main(String [] args) 
   {
      new Program5("Program 5");
   }
}