/**
 * Program9.java
 *     Huffman Code
 * 
 * @author Carmen St. Jean
 *         CS 416 - Fall 2008, November 5, 2008
 */

//----------------------- imports ----------------------------------------------
import java.util.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

public class Program9                                         
{ 
   //------------------------- instance variables ------------------------------
   private  String url;
   private int [] charFreq;
   private int charCount = 0, encodeSize = 0;
   private PriorityQueue<TreeNode> theNodes;
   private final static String DICT_URL =
      "http://www.cs.unh.edu/~cs416/public/gettysburg.txt";
  
   //---------------------------------------------------------------------------
   //------------------------- constructor -------------------------------------
   public Program9(String u)
   {
      url = u;       
      charFreq = new int[128];
      for (int i = 0; i < 128; i++)
         charFreq[i] = 0;
      readFreq();
      theNodes = new PriorityQueue<TreeNode>();
      printCode(createTree());
   }
   
   //---------------------------------------------------------------------------
   //------------------------- methods -----------------------------------------
   
   /**
    * private void readFreq()
    *    Reads in the frequency of characters from a file or prints appropriate
    *    error messages.
    */
   private void readFreq()     
   {
      URLConnection connection = null;  
      BufferedReader infile = null;
      try
      {
         connection =  new URL(url).openConnection();
      }
      catch (IOException e)
      {
         System.err.println("***Error -- can't open url: " + url);
         System.err.println("***Opening default: " + DICT_URL);     
         try
         {
            connection =  new URL(DICT_URL).openConnection();
         }
         catch (IOException fe)
         {
            System.err.println("***Error -- can't open url: " + DICT_URL);        
            System.exit(-1);
         }
      }     
      try
      {
         infile =  new BufferedReader(
                   new InputStreamReader( connection.getInputStream()));
      }
      catch (IOException e)
      {
         System.err.println("***Error -- can't create buffered reader");
         System.exit(-1);
      }
      try
      {
         int i = infile.read();        
         while (i != -1)
         {
            charFreq[i]++;
            i = infile.read();
            charCount++;
         }
      }
      catch (Exception e)
      {
         System.out.println("Read error " + e);  
      }        
   }
   
   //---------------------------------------------------------------------------
   
   /**
    * private TreeNode createTree()
    *    Takes the array of character frequencies and generates a tree.
    */
   private TreeNode createTree()
   {
      for (int i = 0; i < charFreq.length; i++)
      {
         if (charFreq[i] != 0)
         {
            theNodes.add(new TreeNode(i, charFreq[i]));
         }
      }
      TreeNode smallest, nextSmallest;
      while (theNodes.size() >= 2)
      {
         smallest = theNodes.remove();
         nextSmallest = theNodes.remove();
         theNodes.add(new TreeNode(smallest, nextSmallest));
      }
      return theNodes.remove();
   }
   
   //---------------------------------------------------------------------------
   
   /**
    * private void printCode(TreeNode tree)
    *    Uses the Huffman code to determine the codes of each character and then
    *    prints the resulting code.
    */
   private void printCode(TreeNode tree)
   {
      tree.setCode("");
      huffmanChar(tree);
      System.out.println("Character    Frequency    Code");
      System.out.println("------------------------------");
      traverseTree(tree);
      System.out.println("\n*****Text file length " + charCount*8 + " bits," + 
                         " can be encoded with " + encodeSize + " bits.");
   }
   
   //---------------------------------------------------------------------------
   
   /**
    * private void huffmanChar(TreeNode n)
    *    Recursively determines the code of each leaf node of the tree.
    */
   private void huffmanChar(TreeNode n)
   {
      String code = n.getCode();
      if (n.getLeftTreeNode() != null)
      {
         n.getLeftTreeNode().setCode(code + "0");
         huffmanChar(n.getLeftTreeNode());
      }
      if (n.getRightTreeNode() != null)
      {
         n.getRightTreeNode().setCode(code + "1");
         huffmanChar(n.getRightTreeNode());
      }
   }
   
   //---------------------------------------------------------------------------
   
   /**
    * private void traverseTree(TreeNode n)
    *    Recursively traverses to print out each leaf node (character,
    *    frequency, and code.
    */
   private void traverseTree(TreeNode n)
   {
      if (n.isLeaf())
      {
         int c = n.getChar();
         String f = formatFreq(n.getFrequency());
         encodeSize += n.getFrequency()*n.getCode().length();
         if (c > 32)
         {
            System.out.println("   " + (char)c + "           " + f +
                               "      " + n.getCode());
         }
         else if (c == 32)
         {
            System.out.println("   " + "sp" + "          " + f +
                               "      " + n.getCode());
         }
         else if (c == 10)
         {
            System.out.println("   " + "nl" + "          " + f +
                               "      " + n.getCode());
         }
         else
            System.out.println(c);
      }
      else
      {
         if (n.getLeftTreeNode() != null)
            traverseTree(n.getLeftTreeNode());
         if (n.getRightTreeNode() != null)
            traverseTree(n.getRightTreeNode());
      }
   }
   
   //---------------------------------------------------------------------------
   
   /**
    * private String formatFreq(int n)
    *    Formats an integer so that it will have proper spacing for printing.
    */
   private String formatFreq(int n)
   {
      if (n >= 10000)
      {
         return "" + n + "";
      }
      else if (n >= 1000)
      {
         return "" + n + " ";
      }
      else if (n >= 100)
      {
         return "" + n + "  ";
      }
      else if (n >= 10)
      {
         return "" + n + "   ";
      }
      else
         return "" + n + "    ";
   }
   
   //---------------------------------------------------------------------------
   //------------------------- main --------------------------------------------
   public static void main(String[] args)
   {   
      String file = DICT_URL;
      
      if ( args.length > 0 )
      {
         file = args[0];
      }            
      new Program9(file);
   }    
}
