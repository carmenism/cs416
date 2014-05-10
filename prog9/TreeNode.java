/**
 * TreeNode.java
 *     For Huffman Code
 * 
 * @author Carmen St. Jean
 *         CS 416 - Fall 2008, November 5, 2008
 */

public class TreeNode implements Comparable<TreeNode> 
{
   //------------------------- instance variables ------------------------------
   private int character, frequency;
   private TreeNode left, right;
   private String code;

   //---------------------------------------------------------------------------
   //------------------------- constructor -------------------------------------
   
   /**
    * public TreeNode(int ch, int freq)
    *    Creates an original node with the ASCII code of a character and
    *    a frequency.
    */
   public TreeNode(int ch, int freq)
   {
      character = ch;
      frequency = freq;
   } 
   
   //---------------------------------------------------------------------------
   
   /**
    * public TreeNode(TreeNode node1, TreeNode node2)
    *    Creates a new node from two subtrees and a calculated frequency.
    */
   public TreeNode(TreeNode node1, TreeNode node2) 
   {
      left = node1;
      right = node2;
      frequency = (left.getFrequency() + right.getFrequency());
   }

   //---------------------------------------------------------------------------
   //------------------------- methods -----------------------------------------
   
   /**
    * public void setCode(String cd)
    *    Sets the code of the tree node.
    */
   public void setCode(String cd)
   {
      code = cd;
   }
  
   //---------------------------------------------------------------------------
   
   /**
    * public int getFrequency()
    *    Returns the frequency for this character.
    */
   public int getFrequency()
   {
      return frequency;
   }
   
   //---------------------------------------------------------------------------
   
   /**
    * public int getChar()
    *    Returns the ASCII value for the character.
    */
   public int getChar()
   {
      return character;
   }
   
   //---------------------------------------------------------------------------
   
   /**
    * public String getCode()
    *    Returns the Huffman code for this character.
    */
   public String getCode()
   {
      return code;
   }
   
   //---------------------------------------------------------------------------
   
   /**
    * public TreeNode getLeftTreeNode()
    *    Returns the left tree node.
    */
   public TreeNode getLeftTreeNode()
   {
      return left;
   }
   
   //---------------------------------------------------------------------------
      
   /**
    * public TreeNode getRightTreeNode()
    *    Returns the right tree node.
    */
   public TreeNode getRightTreeNode()
   {
      return right;
   }
   
   //---------------------------------------------------------------------------
   
   /**
    * public boolean isLeaf()
    *    Returns true if the node is a leaf.
    */
   public boolean isLeaf()
   {
      return (left == null && right == null);
   }
   
   //---------------------------------------------------------------------------
   
   /**
    * public int compareTo(TreeNode e)
    *    Compares one node to another; returns zero when they are equal.
    */
   public int compareTo(TreeNode e)
   {
      if (frequency < e.getFrequency())
         return -1;
      else if (frequency > e.getFrequency())
         return 1;
      else
         return 0;
   }
}
