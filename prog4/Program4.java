/**
 * Program4.java
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

public class Program4 extends JFrame
{
   //------------------------- instance variables ------------------------------
   private LayoutPanel _appPanel;
   private JLabel scores;
   private JSlider slider;
   
   //---------------------------------------------------------------------------
   //------------------------- constructor -------------------------------------
   
   public Program4(String title)     
   {
      super(title);
      
      String [] buttonLabels = {"Start", "Stop", "Faster", "Slower",
                                "Add Bumpers"};
      this.setSize(600, 450);
      this.setBackground(Color.LIGHT_GRAY);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
      _appPanel = new LayoutPanel(this);
      this.add(_appPanel);
      
      JPanel bMenu = new JPanel();
      int n = 5;
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
      
      this.makeSlider();

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

   /**
    * public void makeSlider()
    *    Makes a slider on the east-side of the window.
    */
   public void makeSlider()
   {
      slider = new JSlider(JSlider.VERTICAL);
      slider.setMinimum(0);
      slider.setMaximum(_appPanel.getHeight());
      slider.setValue(225);
      slider.addChangeListener(_appPanel);
      this.add(slider, BorderLayout.EAST);
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
            case 0: _appPanel.startMove(); break;
            case 1: _appPanel.stopMove(); break;
            case 2: _appPanel.faster(); break;
            case 3: _appPanel.slower(); break;
            case 4: _appPanel.buildBumpers();
         }
      }
   }
         
   //------------------------- main method -------------------------------------
   public static void main(String [] args) 
   {
      new Program4("Program 4");
   }
}