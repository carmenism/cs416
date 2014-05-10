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
import javax.swing.JOptionPane;

public class Board
{
   //------------------------- instance variables ------------------------------
   private int myX, myY, size, status, curPlayer = 2, passCount = 0;
   private int blackScore = 2, whiteScore = 2;
   private Tile [][] theTiles;
   private TextBox text;
   private PassButton passButton;
   private boolean compHasMadeMove = false;
   
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
      curPlayer = 2; // first player is black
      text = new TextBox("OTHELLO \n\nBlack: 2\n\nWhite: 2\n\n" +
                         "Currently black's turn");
      text.setLocation(500, 120);
      passButton = new PassButton(this, 500, 300);
   }
   
   //---------------------------------------------------------------------------

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
   
   //---------------------------------------------------------------------------

   /**
    * 
    */
   public void notify(Tile tile)
   {
      int c = tile.getCol();
      int r = tile.getRow();
      boolean nextPlayerCanGo = false;
      if (tile.getStatus() == 0) 
      {
         for (int i = -1; i <= 1; i++) 
         {
            for (int j = -1; j <= 1; j++)
            {
               if (r+i >= 0 && r+i < 8 && c+j >=0 && c+j < 8)
               { 
                  if (theTiles[r+i][c+j].getStatus() != 0 &&
                      theTiles[r+i][c+j].getStatus() != curPlayer)
                  {
                     Stack<Tile> stack = new Stack<Tile>();
                     stack.push(theTiles[r+i][c+j]);
                     int n = 2;
                     if (r+n*i >= 0 && r+n*i < 8 && c+n*j >= 0 && c+n*j < 8) 
                     {
                        Tile current = theTiles[r+n*i][c+n*j];
                        current.setRow(r+n*i);
                        current.setCol(c+n*j);
                        while (current.getStatus() != 0)
                        {
                           if (r+n*i >= 0 && r+n*i < 8 && c+n*j >= 0 &&
                               c+n*j < 8 && current.getStatus() != curPlayer) 
                           {
                              stack.push(theTiles[r+n*i][c+n*j]);
                           }
                           else if (r+n*i >= 0 && r+n*i < 8 && c+n*j >= 0 &&
                                    c+n*j < 8 &&
                                    current.getStatus() == curPlayer)
                           {
                              stack.push(theTiles[r+n*i][c+n*j]);
                              break;
                           }
                           else
                           {
                              stack.clear();
                              break;
                           }
                           n++;
                           if (r+n*i >= 0 && r+n*i < 8 && c+n*j >= 0 &&
                               c+n*j < 8)
                              current = theTiles[r+n*i][c+n*j];
                        }
                     }
                     if (!stack.empty() &&
                         stack.peek().getStatus() == curPlayer)
                     {
                        tile.setStatus(curPlayer);
                        while (!stack.empty())
                           (stack.pop()).setStatus(curPlayer);
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
   
   //---------------------------------------------------------------------------

   public void changeCurrentPlayer( )
   {
      this.scoreUpdater();
      if (curPlayer == 1)
      {
         passCount = 0;
         curPlayer = 2;
         text.setText("OTHELLO \n\nBlack: " + blackScore +
                      "\n\nWhite: " + whiteScore +
                      "\n\nCurrently black's turn");
      }
      else if (curPlayer == 2)
      {
         curPlayer = 1;
         text.setText("OTHELLO \n\nBlack: " + blackScore +
                      "\n\nWhite: " + whiteScore +
                      "\n\nCurrently white's turn");
         int count = 0;
         Tile compMove = findCompMove();
         if (compMove != null)
            notify(compMove);
         else
         {
            passedMove();
            text.setText("OTHELLO \n\nBlack: " + blackScore +
                         "\n\nWhite: " + whiteScore +
                         "\n\nComputer passes..." +
                         "Currently black's turn again");
            curPlayer = 2;
         }
      }
   }
   
   //---------------------------------------------------------------------------
   
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
      if (whiteScore + blackScore == 64)
      {
         this.endGame();
      }
   }

   public Tile findCompMove( )
   {
      System.out.println("Comp searching for move.");
      Stack<Tile> maxFlanks = new Stack<Tile>();
      for (int r = 0; r < 8; r++)
      {
         for (int c = 0; c < 8; c++)
         {
            System.out.println(r+":"+c);
            if (theTiles[r][c].getStatus() == 0)
            {
               Stack<Tile> stack = new Stack<Tile>();
               stack.push(theTiles[r][c]);
               for (int i = -1; i <= 1; i++)
               {
                  for (int j = -1; j <= 1; j++)
                  {
                     if (r+i >= 0 && r+i < 8 && c+j >=0 && c+j < 8)
                     { 
                        if (theTiles[r+i][c+j].getStatus() != 0 &&
                            theTiles[r+i][c+j].getStatus() != curPlayer)
                        {
                           
                           stack.push(theTiles[r+i][c+j]);
                           int n = 2;
                           if (r+n*i >= 0 && r+n*i < 8 && c+n*j >= 0 && c+n*j < 8) 
                           {
                              Tile current = theTiles[r+n*i][c+n*j];
                              current.setRow(r+n*i);
                              current.setCol(c+n*j);
                              while (current.getStatus() != 0)
                              {
                                 if (r+n*i >= 0 && r+n*i < 8 && c+n*j >= 0 &&
                                     c+n*j < 8 && current.getStatus() != curPlayer) 
                                 {
                                    stack.push(theTiles[r+n*i][c+n*j]);
                                 }
                                 else if (r+n*i >= 0 && r+n*i < 8 && c+n*j >= 0 &&
                                          c+n*j < 8 &&
                                          current.getStatus() == curPlayer)
                                 {
                                    stack.push(theTiles[r+n*i][c+n*j]);
                                    System.out.println("Found a flanking move. " + r + ":" + c);
                                    break;
                                 }
                                 else
                                 {
                                    stack.clear();
                                    break;
                                 }
                                 n++;
                                 if (r+n*i >= 0 && r+n*i < 8 && c+n*j >= 0 &&
                                     c+n*j < 8)
                                    current = theTiles[r+n*i][c+n*j];
                              }
                           }
                           
                           if (!stack.empty() &&
                                stack.peek().getStatus() == curPlayer &&
                                stack.size() >= maxFlanks.size() )
                           {
                              maxFlanks = stack;
                           }
                        }
                     }
                  }
               }
            }
         }
      }
      if (maxFlanks != null)
      {
         for (int i = maxFlanks.size() - 1; i > 0; i--)
         {
            maxFlanks.pop();
         }
         Tile returnTile = maxFlanks.pop();
         return theTiles[returnTile.getRow()][returnTile.getCol()];   
      }
      return null;
   }

   /**
    * static void sleep(int milliseconds)
    *    This will cause the program to "sleep" for the provided number of
    *    milliseconds.
    */
   public static void sleep(int milliseconds)
   {
      try
      {
      Thread.sleep(milliseconds);
      }
      catch(InterruptedException e)
      { }
   }
   
   public void passedMove()
   {
      passCount++;
      if (passCount == 2)
         endGame();
   }
   
   private void endGame()
   {
      System.out.println("GAME OVER");
      if (whiteScore > blackScore)
         JOptionPane.showMessageDialog( null, "Sorry, the computer has beat you!" );
      else if (blackScore < whiteScore)
         JOptionPane.showMessageDialog( null, "Congrats!  You beat the computer!" );
      else if (blackScore == whiteScore)
         JOptionPane.showMessageDialog( null, "It's a tie!  Game over." );
   }
}
