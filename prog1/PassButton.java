/**
 * PassButton.java
 *    For Program1, a button which can be clicked to pass a turn.
 * 
 * @author Carmen St. Jean
 *         CS 416 - Fall 2008, September 9, 2008
 */

//----------------------- imports ----------------------------------------------
import java.awt.Color;
import java.awt.event.*;
import wheelsunh.users.*;
import java.awt.Dimension;

public class PassButton extends ShapeGroup
{
   //------------------------- instance variables ------------------------------
   private int myX, myY;
   private Board theBoard;
   private TextBox text;
   private Ellipse button;
   
   //---------------------------------------------------------------------------
   //------------------------- constructor -------------------------------------
   
   /**
    * public PassButton(Board board, int x, int y)
    *    This constructor creates a button, which can be clicked to skip a turn.
    *    The board must be passed in so the button can call public methods in it
    *    later.
    */
   public PassButton(Board board, int x, int y)
   {
      text = new TextBox("Pass turn -->");
      myX = x;
      myY = y;
      text.setLocation(myX, myY);
      text.setSize(new Dimension(90, 40));
      theBoard = board;
      button = new Ellipse(Color.BLUE);
      button.setLocation(x + 95, y);
      button.setSize(40, 40);
      add(text);
      add(button);
   }
   
   //---------------------------------------------------------------------------
   //------------------------- methods -----------------------------------------

   /**
    * void mousePressed(MouseEvent me)
    *    When the button is clicked, it lets the board know that a move was
    *    skipped and tells the computer (white) to make the next move.
    */
   public void mousePressed(MouseEvent me)
   {
      theBoard.passedMove();
      theBoard.changeCurrentPlayer();
   }
} 
