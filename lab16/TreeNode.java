
public class TreeNode implements  Comparable<TreeNode> 
{
   private int character, frequency;
   private TreeNode left, right;
   private String code;

   //------------------------------------------------
   //   Creat the original nodes with an (ascii code of) a character
   // and a frequency
   public TreeNode(int ch, int freq)
   {
      character = ch;
      frequency = freq;
   } 
   
   
   //------------------------------------------------
   //   Create the new nodes with two subtrees
   //   and a calculated frequency
   public TreeNode(TreeNode node1, TreeNode node2) 
   {
      left = node1;
      right = node2;
      frequency = (left.getFrequency() + right.getFrequency());
   }

   //------------------------------------------------
   public void setCode(String cd) {
      code = cd;
   }
  
   //------------------------------------------------
   public int getFrequency() {
      return frequency;
   }
   
   //------------------------------------------------
   public int getChar() {
      return character;
   }
   
   //------------------------------------------------
   public String getCode() {
      return code;
   }
   
    //------------------------------------------------
   public TreeNode getLeftTreeNode() {
      return left;
   }
   
   //------------------------------------------------
   public TreeNode getRightTreeNode() {
      return right;
   }
   
   //------------------------------------------------
   public boolean isLeaf() {
      return (left == null && right == null);
   }
   
   //------------------------------------------------
   public int compareTo( TreeNode e )
   {
      return 0;
   }
   
   //------------------------------------------------
   public String toString()
   {
      return "";       
   } 
} // class TreeNode
