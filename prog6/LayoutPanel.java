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
   private final int PANEL_HEIGHT = 600;
   private JFrame _frame;
   private Graphics brush; 
   private int y = 400;
   private JTextField _infix, _postfix;
   private LinkedList<String> list, postfix;
   private LinkedList<JLabel> postfixLabels;
   //---------------------------------------------------------------------------
   //------------------------- constructor -------------------------------------
   
   public LayoutPanel(JFrame f) 
   {
      _frame = f;
      setBackground (Color.white);
      setPreferredSize (new Dimension(PANEL_WIDTH, PANEL_HEIGHT));  
      list = new LinkedList<String>();
      postfixLabels = new LinkedList<JLabel>();
      JLabel test = new JLabel( "yooo");

      test.setBounds(20, 400, 100, 24); 
     // test.setBorder(Color.BLUE);
      _frame.add(test);
      //this.repaint();
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
      
   }
   
   //---------------------------------------------------------------------------

   /**
    * public void setOrder(int newOrder)
    *    This method sets the order for the fractal, then repaints.
    */
   public void enterPostfixEvaluation(JTextField i, JTextField p)
   {
      _infix = i; _postfix = p;
      String postfixText = _postfix.getText();
//      if (!list.isEmpty())
//         list.clear();
      Scanner s = new Scanner(postfixText);
      while (s.hasNext())
      {
         list.add(s.next());
      }
      postfix = new LinkedList<String>();
   }
   
   public void postStep( )
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
            String r = postfix.removeLast();
            int right = Integer.parseInt(r);
         
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
         postfixLabels.getLast().setBounds(20, y, 100, 24); 
         _frame.add(postfixLabels.getLast());

         this.repaint();
         y = y - 20;
      }
      else
      {
         list.clear();
      }
      
      //list
   }
   
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
   
   public void infixConverter(JTextField i, JTextField p)
   {
      if (!list.isEmpty())
         list.clear();
      
      String postfixUpdater = "";
      
      LinkedList<String> temp = new LinkedList<String>();
      _infix = i; _postfix = p;
      String infixText = _infix.getText();
      Scanner s = new Scanner(infixText);
      while (s.hasNext())
      {
         temp.add(s.next());
      }
      
      LinkedList<String> operatorStack = new LinkedList<String>();
      while (!temp.isEmpty())
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
                   (t.equals("-") || t.equals("+")) &&
                   (operatorStack.getLast().equals("*") || 
                    operatorStack.getLast().equals("/")))
            {
               String sss = operatorStack.removeLast();
               list.add(sss);
               postfixUpdater = postfixUpdater + " " + sss;
            }
            operatorStack.add(t);
         }
      }
      while (!operatorStack.isEmpty())
      {
         String ssss = operatorStack.removeLast();
         list.add(ssss);
         postfixUpdater = postfixUpdater + " " + ssss;
      }
      _postfix.setText(postfixUpdater);
   }
}   

//            while (!operatorStack.isEmpty() &&
//                   (((t.equals("-") || t.equals("+")) &&
//                   (operatorStack.getLast().equals("*") || 
//                    operatorStack.getLast().equals("/"))) || 
//                   ((t.equals("*") || t.equals("+")) &&
//                   (operatorStack.getLast().equals("*") || 
//                    operatorStack.getLast().equals("/")))))