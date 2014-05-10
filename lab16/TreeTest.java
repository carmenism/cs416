
import java.util.Scanner;
import java.text.DecimalFormat;
import java.util.*;
import java.io.*;
import java.net.*;

public class TreeTest                                         
{
   private Vector<TreeNode> vect;
   private TreeNode tree;
   
   public TreeTest(  )
   {
      makeTree();
      inOrderPrint();
   }
   
   //-------------------------------------------------------------
   // makes a simple valid huffman tree
   // codes are:
   // Character Frequency Code
   //-------------------------------
   //e          1        000
   //a          2        001
   //b          4        01
   //c          5        10
   //d          6        11
   
   public void makeTree(  )
   {
      vect = new Vector<TreeNode>();
      
      vect.add( new TreeNode( (int) 'e',  1));
      vect.add( new TreeNode( (int) 'a',  2));
      vect.add( new TreeNode( (int) 'b',  4));
      vect.add( new TreeNode( (int) 'c',  5));
      vect.add( new TreeNode( (int) 'd',  6));
      
      TreeNode temp1 = new TreeNode( vect.get(0), vect.get(1) );
      temp1 = new TreeNode( temp1 , vect.get(2));
      TreeNode temp2 = new TreeNode( vect.get(3), vect.get(4) );
      temp1 = new TreeNode( temp1, temp2 );
      tree = temp1;
   }
   
   //------------------ inOrderPrint( PrintStream ) ---------------
   public void inOrderPrint(  )
   {
      inOrderPrint( tree );
      System.out.println();
   }
   //------------------ inOrderPrint( PrintStream, node ) ---------------
   /**
    * Print the subtree rooted at "node" in in-order fashion.
    */
   private void inOrderPrint(  TreeNode n )
   {
      if ( n != null )
      {
         System.out.println("L");
         inOrderPrint(  n.getLeftTreeNode() );
         if( n.isLeaf() )
            System.out.print( "    leaf "+ (char)n.getChar() );
         else
            System.out.print( "Non-leaf  " );  
         System.out.println( "  :  " + n.getFrequency() );
         System.out.println("R");
         inOrderPrint(  n.getRightTreeNode() );
      }
      else
         System.out.println("null");
   }
   
   public static void main(String[] args)
   {          
      new TreeTest(  );
   }    
}




