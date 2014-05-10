/**
 * Program6.java
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

public class Program6 extends JFrame implements ActionListener 
{
   //---------------------- instance variables ---------------------------------
   private final int WIDTH = 600;
   private final int HEIGHT = 600;
   private LayoutPanel drawPanel;
   private JPanel panel, bottomGrid;
   private int order = 1;
   private JTextField infix, postfix;
   private JButton enterInfix, stepInfix, enterPostfix, stepPostfix, clear;
   
   //---------------------------------------------------------------------------
   //------------------------- constructor -------------------------------------
  
   public Program6( String title, String[] args )    
   {
      super( title );
      drawPanel = new LayoutPanel(this);
      panel = new JPanel();
      panel.add(drawPanel);
      
      createBottomGrid();
      
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
   private void createBottomGrid()
   {
      bottomGrid = new JPanel(new GridLayout(3, 3));
      
      postfix = new JTextField("Enter postfix expression");
      bottomGrid.add(postfix);
      enterPostfix = new JButton("Enter Postfix Evaluation");
      enterPostfix.addActionListener(new ButtonListener(0));
      bottomGrid.add(enterPostfix);
      stepPostfix = new JButton("Step");
      stepPostfix.addActionListener(new ButtonListener(1));
      bottomGrid.add(stepPostfix);
      
      infix = new JTextField("Enter infix expression");
      bottomGrid.add(infix);
      enterInfix = new JButton("Enter Conversion to Postfix");
      enterInfix.addActionListener(new ButtonListener(2));
      bottomGrid.add(enterInfix);
      stepInfix = new JButton("Step");
      stepInfix.addActionListener(new ButtonListener(3));
      bottomGrid.add(stepInfix);
      
      clear = new JButton("Clear");
      clear.addActionListener(new ButtonListener(4));
      bottomGrid.add(clear);
      
      this.add(bottomGrid, BorderLayout.SOUTH);
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
            case 0:    // enter postfix evaluation
            {
               drawPanel.enterPostfixEvaluation(infix, postfix);
               break;
            }
            case 1:    // step postfix
            {
               drawPanel.postStep();
               break;
            }
            case 2:    // enter infix to postfix conversion
            {
               drawPanel.infixConverter(infix, postfix);
               break;
            }
            case 3:    // step infix
            {
               break;
            }
            case 4:    // clear
            {
               break;
            }
         }
      }
   }
   
   //----------------------- main ----------------------------------------------
   
   public static void main( String [ ] args )
   {
      new Program6( "Infix/Postfix Evaluator", args );
   }
}