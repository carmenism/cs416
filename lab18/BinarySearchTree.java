
/**
 * BinarySearchTree -- this class uses a private inner class for
 *      tree nodes. (Although in this version it is public so I can 
 *      do a prefix walk in DisplayPanel.)
 *
 * @author rdb
 * April 2, 2008
 * 
 * Modified: April 6
 *      added iterator and node deletion code.
 * 
 * modified mlb Nov 2008 for Lab17
 */

import java.util.*;
import java.io.*;

public class BinarySearchTree 
{
   //-------------------- instance variables ---------------------
   private Node   _root;
   private int    _size;
   
   //-------------------- constructors --------------------------
   /**
    * Construct an empty tree with no nodes
    */
   public BinarySearchTree()
   {
      _root = null;
   }
   /**
    * Construct a tree with a root 
    */
   public BinarySearchTree( Data rootData )
   {
      _root = new Node( rootData );
   }
   //--------------------- root() ----------------------------------
   /**
    * return the root of the tree; this is package access so that DisplayPanel
    * can do a prefix walk of the tree. Would be better to have multiple
    * iterators.
    */
   BinarySearchTree.Node root()
   {
      return _root;
   }
  
   
     //------------------ inOrderPrint( PrintStream ) ---------------
   /**
    * Traverse the tree in in-order fashion to print the nodes of the tree
    * to the PrintStream parameter.
    * This method uses the private utility method to print a subtree rooted
    * at a particular node.
    */
   public void inOrderPrint( )
   {
      inOrderPrint( System.out, _root );
      System.out.println();
   }

   
   //------------------ inOrderPrint( PrintStream, node ) ---------------
   /**
    * Print the subtree rooted at "node" in in-order fashion.
    */
   private void inOrderPrint( PrintStream out, Node n )
   {
      if (n == null)
      {
         
      }
      else
      {
         inOrderPrint(out, n.left);
         out.println(n.data.toString());
         inOrderPrint(out, n.right);
      }
   }
      
   //------------------ preOrderPrint( PrintStream ) ---------------
   /**
    * Traverse the tree in in-order fashion to print the nodes of the tree
    * to the PrintStream parameter.
    * This method uses the private utility method to print a subtree rooted
    * at a particular node.
    */
   public void preOrderPrint(  )
   {
      preOrderPrint( System.out, _root );
      System.out.println();
   }
   //------------------ preOrderPrint( PrintStream, node ) ---------------
   /**
    * Print the subtree rooted at "node" in in-order fashion.
    */
   private void preOrderPrint( PrintStream out, Node n )
   {
      if (n == null)
      {
         
      }
      else
      {  
         out.println(n.data.toString());
         preOrderPrint(out, n.left);
         preOrderPrint(out, n.right);
      }
   }

   
 

   //-------------------- height() ------------------------------------
   /**
    * return the depth of the tree ( just a stub for now)
    */
   public int height()
   {
      return height(_root);
   }

   //-------------------- height() ------------------------------------
   /**
    * return the depth of the tree ( just a stub for now)
    */
   public int height(Node n)
   {
      if (n == null)
         return -1;
      else
         return Math.max(height(n.left), height(n.right)) + 1;
   }
   
   //-------------------- findDepth( String ) -------------------------
   /**
    * Given a key value, search the tree to find the node that has
    * that key value, if it exists.
    * 
    * Return the Data object from the node or null
    */
   public int findDepth( String key )
   {
  
        return  findDepth( _root,key, 0);
   
   }
   
   //-------------------- findDepth( Node, String ) -------------------------
   /**
    * Given a key value, search the tree to find the node that has
    * that key value, if it exists.
    * 
    * Return the Data object from the node or null
    */
   public int findDepth( Node n, String key, int d )
   {
      if (n == null)
         return -1;
      else if (n.data.key.equals(key))
         return d;    
      else
      {
         int lDepth = findDepth(n.left, key, d + 1);
         int rDepth = findDepth(n.right, key, d + 1);
         return Math.max(lDepth, rDepth);
      }
   }
   
   
  
   //-------------------- sumNode(  ) -------------------------
   /**
    * Given a key value, search the tree to find the node that has
    * that key value, if it exists.
    * 
    * Return the Data object from the node or null
    */
   public int sumNode(  )
   {
      return sumNode( _root );
   }
   
   
    //-------------------- sumNode(  ) -------------------------
   /**
    * Given a key value, search the tree to find the node that has
    * that key value, if it exists.
    * 
    * Return the Data object from the node or null
    */
   public int sumNode( Node n )
   {
      if (n == null)
         return 0;
      else
         return (n.data.value + sumNode(n.left) + sumNode(n.right));
   }
      
   //--------------------- add -----------------------------------
   /**
    * add a node to the tree in its proper position determined by the
    * "key" field of the Data object. This method uses the addNode 
    * recursive utility method.
    */
   public void add( Data data )
   {
      Node newNode = new Node( data );
      if ( _root == null )
         _root = newNode;
      else
         addNode( _root, newNode );
      _size++;
   }
 
   //------------------ addNode( Node, Node ) -----------------------
   /**
    * a recursive method to add a new Node (2nd argument) to the subtree
    * rooted at the first argument.
    */
   private void addNode( Node parent, Node newOne )
   {
      if ( newOne.data.compareTo( parent.data ) < 0 )
      {
         if ( parent.left != null )
            addNode( parent.left, newOne );
         else 
         {
            parent.left = newOne;
            newOne.parent = parent;
         }
      }
      else
      {
         if ( parent.right != null )
            addNode( parent.right, newOne );
         else 
         {
            parent.right = newOne;
            newOne.parent = parent;
         }
      }
   }
   
   //-------------------------- size() -------------------------
   /**
    * return tree size
    */
   public int size()
   {
      return _size;
   }
   //-------------------------- toString() -------------------------
   /**
    * Generate a string representation of the tree.
    */
   public String toString()
   {
      return toString( _root, "  ", "  " ) ;        
   }
   
   /**
    * recursively generate a string representation for a Node of a tree.
    * indent is increased for increasing depth.
    * branch is a short character string that prefixes each node indicating
    *        whether this node is a left (L) or right (R) child of its parent.
    */
   private String toString( Node n, String indent, String branch )
   {
      String s = "";
      if ( n != null )
      {
         String prefix = indent.substring( 0, indent.length() - 2 ) + branch;
         s += prefix + n.data.toString() + "\n";
         if ( n.left != null )
            s += toString( n.left, indent + "  ", "L " );
         if ( n.right != null )
            s += toString( n.right, indent + "  ", "R " );
      }
      return s;
   }
   //+++++++++++++++++++++++ inner class Node ++++++++++++++++++++++
   /**
    * The Node class does not have to be seen outside this class, so
    * it is private.
    */
   public class Node
   {
      //-------------- instance variables ---------------------------
      public Data data;
      public Node left;
      public Node right;
      public Node parent;
      
      //--------------- constructor --------------------------------
      public Node( Data d )
      {
         data = d;
         left = null;
         right = null;
         parent = null;
      }
   }
     
}
        
        
