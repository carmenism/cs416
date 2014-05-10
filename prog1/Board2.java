/**
 * Board.java
 *    For Program1.
 * 
 * @author Carmen St. Jean
 *         CS 416 - Fall 2008, September 9, 2008
 */

//----------------------- imports ----------------------------------------------
import java.awt.Color;
import java.awt.event.*;
import wheelsunh.users.*;
import java.util.Stack;
import java.util.ArrayList;

public class Board
{
   //------------------------- instance variables ------------------------------
   private int myX, myY, size, status, currentPlayer = 2;
   private int blackScore = 2, whiteScore = 2;
   private Tile [][] theTiles;
   private TextBox text;
   
   //---------------------------------------------------------------------------
   //------------------------- constructor -------------------------------------
   
   /**
    * public Board(int i, int j, int s, int status)
    *    This constructor creates a tile at the specified position with the
    *    specificed size.  A status of zero indicates it is a wall; a status of
    *    1 indicates it is an open passage-way.
    */
   public Board(int x, int y, int tileSize)
   {
      size = tileSize;
      myX = x; //x location
      myY = y; //y location
      theTiles = new Tile[8][8];
      for (int i = 0; i < 8; i++)
      {
         for (int j = 0; j < 8; j++)
         {
             theTiles[i][j] = new Tile(this, myX + j*size, myY + i*size, size);
             theTiles[i][j].setRow(i);
             theTiles[i][j].setCol(j);
         }
      }
      this.setUpCenter( );
      currentPlayer = 2; // first player is black
      text = new TextBox("OTHELLO \n\nBlack: 2\n\nWhite: 2\n\nCurrent Player: Black");
      text.setLocation(500, 120);
   }
   
   /**
    * 
    */
   public void setUpCenter( )
   {
      theTiles[3][3].setStatus(1); // white
      theTiles[3][4].setStatus(2); // black
      theTiles[4][3].setStatus(2); // black
      theTiles[4][4].setStatus(1); // white
   }
   

   /**
    * 
    */
   public void notify(Tile tile)
   {
      System.out.println(currentPlayer);
      int col = tile.getCol();
      int row = tile.getRow();
      boolean nextPlayerCanGo = false;
      if (tile.getStatus() == 0) // if tile is empty
      {
         for (int i = -1; i <= 1; i++) // check surrounding tiles for opponent
         {
            for (int j = -1; j <= 1; j++)
            {
               if (row+i >= 0 && row+i < 8 && col+j >=0 && col+j < 8) // if surrounding point checked is really a tile
               { 
                  if (theTiles[row+i][col+j].getStatus() != 0 &&
                      theTiles[row+i][col+j].getStatus() != currentPlayer) // ...and if tile is not the current player's color
                  {
                     Stack<Tile> stack = new Stack<Tile>();
                     stack.push(theTiles[row+i][col+j]);
                     int n = 2;
                     if (row+n*i >= 0 && row+n*i < 8 && col+n*j >= 0 && col+n*j < 8) // check the tile beyond that
                     {
                        Tile current = theTiles[row+n*i][col+n*j];
                        current.setRow(row+n*i);
                        current.setCol(col+n*j);
                        while (current.getStatus() != 0)
                        {
                           if (row+n*i >= 0 && row+n*i < 8 && col+n*j >= 0 &&
                               col+n*j < 8 && current.getStatus() != currentPlayer) // flanking piece not found YET
                           {
                              stack.push(theTiles[row+n*i][col+n*j]);
                           }
                           else if (row+n*i >= 0 && row+n*i < 8 && col+n*j >= 0 &&
                                    col+n*j < 8 && current.getStatus() == currentPlayer) // flanking piece found
                           {
                              stack.push(theTiles[row+n*i][col+n*j]);
                              break;
                           }
                           else // no flanking piece found
                           {
                              stack.clear();
                              break;
                           }
                           n++;
                           if (row+n*i >= 0 && row+n*i < 8 && col+n*j >= 0 && col+n*j < 8)
                              current = theTiles[row+n*i][col+n*j];
                        }
                     }
                     if (!stack.empty() && stack.peek().getStatus() == currentPlayer) // nonempty stack indicates flanking piece found
                     {
                        tile.setStatus(currentPlayer);
                        while (!stack.empty())
                        { 
                           (stack.pop()).setStatus(currentPlayer);
                        }
                        nextPlayerCanGo = true;
                     }
                  }
               }
            }
         }
      }
      if (nextPlayerCanGo)
         this.changeCurrentPlayer( );
   }
   
   
   private void changeCurrentPlayer( )
   {
      this.scoreUpdater();
      if (currentPlayer == 1)
      {
         currentPlayer = 2;
         text.setText("OTHELLO \n\nBlack: " + blackScore +
                      "\n\nWhite: " + whiteScore +
                      "\n\nCurrent Player: Black");
      }
      else if (currentPlayer == 2)
      {
         currentPlayer = 1;
         text.setText("OTHELLO \n\nBlack: " + blackScore +
                      "\n\nWhite: " + whiteScore +
                      "\n\nCurrent Player: White");
      }
      ArrayList<Tile> arr = checkForValidMove();
      if (arr.isEmpty())
      {
         // NO VALID MOVES, PLAYER MUST PASS
         if (currentPlayer == 1)
         {
            currentPlayer = 2;
            text.setText("OTHELLO \n\nBlack: " + blackScore +
                         "\n\nWhite: " + whiteScore +
                         "\n\nCurrent Player: Black");
         }
         else if (currentPlayer == 2)
         {
            currentPlayer = 1;
            text.setText("OTHELLO \n\nBlack: " + blackScore +
                         "\n\nWhite: " + whiteScore +
                         "\n\nCurrent Player: White");
         }
      }
      else // NEXT PLAYER CAN GO !!!!!!!!!!!! LOOK AT ME!
      {
         Tile compMove = arr.get(0);
         if (currentPlayer == 2)
            notify(compMove);
      }
   }
   
   private ArrayList<Tile> checkForValidMove()
   {
      ArrayList<Tile> visitableTiles = new ArrayList<Tile>();
      for (int r = 0; r < 8; r++)
      {
         for (int c = 0; c < 8; c++)
         {
             if (theTiles[r][c].getStatus() != 0)
             {
                for (int i = -1; i <= 1; i++)
                {
                   for (int j = -1; j <= 1; j++)
                   {
                      if (r+i >= 0 && r+i < 8 && c+j >=0 && c+j < 8 &&
                          theTiles[r+i][c+j].getStatus() != 0 &&
                          theTiles[r+i][c+j].getStatus() != currentPlayer)
                      {
                         int n = 2;
                         if (r+i >= 0 && r+i < 8 && c+j >=0 && c+j < 8)
                         {
                            Tile current = theTiles[r+n*i][c+n*j];
                            while (current.getStatus() != 0)
                            {
                               if (r+n*i >= 0 && r+n*i < 8 && c+n*j >= 0 &&
                                   c+n*j < 8 && current.getStatus() != currentPlayer)
                               {
                                  if (r+n*i >= 0 && r+n*i < 8 && c+n*j >= 0 &&
                                      c+n*j < 8 && current.getStatus() == currentPlayer)
                                  {
                                     visitableTiles.add(current);
                                     break;
                                  }
                               }
                               else
                                  break;
                               n++;
                               if (r+i >= 0 && r+i < 8 && c+j >=0 && c+j < 8)
                                  current = theTiles[r+n*i][c+n*j];
                            }
                         }
                     }
                  }
               }
            }
         }
      }
      return visitableTiles;
   }
   
   private void scoreUpdater( )
   {
      whiteScore = 0;
      blackScore = 0;
      for (int i = 0; i < 8; i++)
      {
         for (int j = 0; j < 8; j++)
         {
             if (theTiles[i][j].getStatus() == 1)
                whiteScore++;
             if (theTiles[i][j].getStatus() == 2)
                blackScore++;
         }
      }
   }
}
