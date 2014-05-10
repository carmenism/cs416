/**
 * LayoutPanel.java
 *     For Program5.java
 * 
 * @author Carmen St. Jean
 *         CS 416 - Fall 2008, October 7, 2008
 */

//----------------------- imports ----------------------------------------------
import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.Color;

public class LayoutPanel extends JPanel
{
   //------------------------- instance variables ------------------------------
   private final int PANEL_WIDTH = 600;
   private final int PANEL_HEIGHT = 500;
   private JFrame _frame;
   private Graphics brush; 
   private int x = 20, y = 300, count = 1;
   private JTextField _infix, _postfix;
   private LinkedList<String> list, postfix, operatorStack, temp;
   private LinkedList<JLabel> postfixLabels, infixLabels;
   private String postfixUpdater;
   
   //---------------------------------------------------------------------------
   //------------------------- constructor -------------------------------------

   public LayoutPanel(JFrame f) 
   {
      _frame = f;
      setBackground (Color.white);
      setPreferredSize (new Dimension(PANEL_WIDTH, PANEL_HEIGHT));  
      list = new LinkedList<String>();
      postfixLabels = new LinkedList<JLabel>();
      infixLabels = new LinkedList<JLabel>();
      operatorStack = new LinkedList<String>();
   }
   
   //---------------------------------------------------------------------------
   
   /**
    * public void paintComponent (Graphics brush)
    *    This makes the initial call to the drawSnowflake method.  The initial
    *    points can be set here.
    */
   public void paintComponent (Graphics brush)
   {
      super.paintComponent (brush);
      brush.setColor (Color.black);
      this.brush = brush;
      if (postfixLabels != null && postfixLabels.size() != 0)
         if (postfixLabels.getLast() != null)
            postfixLabels.getLast().setBounds(20*count, y, 100, 24);
      if (infixLabels != null && infixLabels.size() != 0)
         if (infixLabels.getLast() != null)
            infixLabels.getLast().setBounds(350, y, 100, 24);
   }
   
   //---------------------------------------------------------------------------

   /**
    * public void infixConverter(JTextField i, JTextField p)
    *    Textfields sent to the layout to begin postfix evalution.
    */
   public void enterPostfixEvaluation(JTextField i, JTextField p)
   {
      count++;
      y = 300;
      _infix = i; _postfix = p;
      String postfixText = _postfix.getText();
      list.clear();
      Scanner s = new Scanner(postfixText);
      while (s.hasNext())
      {
         list.add(s.next());
      }
      postfix = new LinkedList<String>();
   }

   //---------------------------------------------------------------------------

   public void postStep()
   {
      String labelText = "";
      if (!list.isEmpty())
      {
         String post = list.removeFirst();
         if (isParsableToInt(post))
         {
            postfix.add(post);
            labelText = labelText + post;
         }
         else if (post.equals("+") || post.equals("-") ||
                  post.equals("*") || post.equals("/"))
         {
            int result = 0;
            String l = postfix.removeLast();
            int left = Integer.parseInt(l);
            JLabel test1 = postfixLabels.removeLast();
            test1.setVisible(false);
            String r = postfix.removeLast();
            int right = Integer.parseInt(r);
            JLabel test2 = postfixLabels.removeLast();
            test2.setVisible(false);
            y = y + 40;
         
            if (post.equals("+"))
            {
               result = right + left;
            }
            else if (post.equals("-"))
            {
               result = right - left;
            }
            else if (post.equals("*"))
            {
               result = right * left;
            }
            else if (post.equals("/"))
            {
               result = right / left;
            }
         
            postfix.add(Integer.toString(result));
            labelText = labelText + result;
         }
         postfixLabels.add(new JLabel(labelText));
         this.add(postfixLabels.getLast());
         this.repaint();
         y = y - 20;
         System.out.println(list.size());
      }
   }

   //---------------------------------------------------------------------------

   /**
    * public boolean isParsableToInt(String i) 
    *    Determines if a string contains an integer.
    */
   public boolean isParsableToInt(String i) 
   {
      try
      {
         Integer.parseInt(i);
         return true;
      }
      catch(NumberFormatException nfe)
      {
         return false;
      }
   }
   
   //---------------------------------------------------------------------------

   /**
    * public void infixConverter(JTextField i, JTextField p)
    *    Textfields sent to the layout to begin an infix to postfix conversion.
    */
   public void infixConverter(JTextField i, JTextField p)
   {
      count++;
      y = 300;
      if (!list.isEmpty())
         list.clear();
      operatorStack.clear();
      postfixUpdater = "";
      temp = new LinkedList<String>();
      _infix = i; _postfix = p;
      String infixText = _infix.getText();
      Scanner s = new Scanner(infixText);
      while (s.hasNext())
      {
         temp.add(s.next());
      }
   }

   //---------------------------------------------------------------------------

   /**
    * public void inStep()
    *    Takes the infix to postfix conversion one step further.
    */
   public void inStep()
   {
      if (!temp.isEmpty())
      {
         String t = temp.removeFirst();
         
         if (isParsableToInt(t))
         {
            list.add(t);
            postfixUpdater = postfixUpdater + " " + t;
         }
         else if (t.equals("("))
         {
            operatorStack.add(t);
         }
         else if (t.equals(")"))
         {
            while (!operatorStack.getLast().equals("("))
            {
               String ss = operatorStack.removeLast();
               list.add(ss);
               postfixUpdater = postfixUpdater + " " + ss;
            }
            operatorStack.removeLast();
         }
         else
         {
            while (!operatorStack.isEmpty() &&
                   getPriority(operatorStack.getLast()) >= getPriority(t))
            {
               String sss = operatorStack.removeLast();
               list.add(sss);
               postfixUpdater = postfixUpdater + " " + sss;
            }
            operatorStack.add(t);
         }
      }
      if (temp.isEmpty())
      {
         while (!operatorStack.isEmpty())
         {
            String ssss = operatorStack.removeLast();
            list.add(ssss);
            postfixUpdater = postfixUpdater + " " + ssss;
         }
      }
      _postfix.setText(postfixUpdater);
   }

   //---------------------------------------------------------------------------

   /**
    * private int getPriority(String op)
    *    Returns a higher integer for multiplication or division operators than
    *    subtraction or addition.
    */
   private int getPriority(String op)
   {
      if (op.equals("+") || op.equals("-"))
         return 1;
      else if (op.equals("*") || op.equals("/"))
         return 2;
      return 0;
   }
}