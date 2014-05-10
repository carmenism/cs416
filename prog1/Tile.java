/**
 * Tile.java
 *    For Program1, these tiles construct the board and contain the playing
 *    stones.
 * 
 * @author Carmen St. Jean
 *         CS 416 - Fall 2008, September 9, 2008
 */

//----------------------- imports ----------------------------------------------
import java.awt.Color;
import java.awt.event.*;
import wheelsunh.users.*;

public class Tile extends Rectangle
{
   //------------------------- instance variables ------------------------------
   private int myX, myY, col, row, size, status;
   private Board theBoard;
   private Color brown = new Color(160, 100, 25);
   private Ellipse stone;
   
   //---------------------------------------------------------------------------
   //------------------------- constructor -------------------------------------
   
   /**
    * public Tile(Board board, int x, int y, int tileSize)
    *    This constructor creates a tile at the specified position with the
    *    specificed size.  The tile must remember the board later to notify it
    *    if the tile was clicked.
    */
   public Tile(Board board, int x, int y, int tileSize)
   {
      super(  );
      size = tileSize;
      myX = x;
      myY = y;
      setLocation(myX, myY);      
      setSize(size, size);
      setFrameColor(Color.BLACK);
      setFillColor(brown);
      theBoard = board;
      status = 0; // initially empty
      
      stone = new Ellipse(myX + 5, myY + 5);
      stone.setSize(size - 10, size - 10);
      stone.setColor(Color.WHITE);
      stone.hide( );
   }
   
   //---------------------------------------------------------------------------
   //------------------------- methods -----------------------------------------

   /**
    * void setStatus(int type)
    *    This method sets the "status" of the tile.  If it is '0,' then there is
    *    no stone placed on that tile.  If it is '1,' then the tile is occupied
    *    by a white tile.  '2' is a black tile.
    */
   public void setStatus(int type)
   {
      if (type == 0) // set empty
      {
         status = type;
         stone.hide();
      }
      else if (type == 1) // set to white
      {
         status = type;
         stone.setColor(Color.WHITE);
         stone.show();
      }
      else if (type == 2) // set to black
      {
         status = type;
         stone.setColor(Color.BLACK);
         stone.show();
      }
   }
   
   //---------------------------------------------------------------------------

   /**
    * int getStatus()
    *    Returns the status of the tile.  (See 'setStatus()' above for an
    *    explanation concerning status numbers.)
    */
   public int getStatus()
   {
      return status;
   }
   
   //---------------------------------------------------------------------------

   /**
    * void setRow(int r)
    *    Tells the tile its row so the tile can remember for future reference.
    */
   public void setRow(int r)
   {
      row = r;
   }
   
   //---------------------------------------------------------------------------
   
   /**
    * void setCol(int c)
    *    Tells the tile its column so the tile can remember for future
    *    reference.
    */
   public void setCol(int c)
   {
      col = c;
   }
   
   //---------------------------------------------------------------------------

   /**
    * int getRow()
    *    Returns the row of the tile.
    */
   public int getRow()
   {
      return row;
   }
   
   //---------------------------------------------------------------------------

   /**
    * int getCol()
    *    Returns the column of the tile.
    */
   public int getCol()
   {
      return col;
   }

   //---------------------------------------------------------------------------

   /**
    * void mousePressed(MouseEvent me)
    *    This method notifies the board that this specific tile has been clicked
    *    by the user.
    */
   public void mousePressed(MouseEvent me)
   {
      theBoard.notify(this);
   }
} 
