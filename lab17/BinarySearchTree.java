
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
  
    //------------------ preOrderPrint( PrintStream ) ---------------
   /**
    * Traverse the tree in in-order fashion to print the nodes of the tree
    * to the PrintStream parameter.
    * This method uses the private utility method to print a subtree rooted
    * at a particular node.
    */
   public void inOrderPrint( )
   {
      inOrderPrint(  _root );
      System.out.println();
   }
   //------------------ preOrderPrint( PrintStream, node ) ---------------
   /**
    * Print the subtree rooted at "node" in in-order fashion.
    */
   private void inOrderPrint(  Node n )
   {
      if ( n != null )
      {
        
         inOrderPrint(  n.left );
          System.out.print( " " + n.data + " ");
         inOrderPrint( n.right );
      }
   }

   
   
   

   //-------------------- height() ------------------------------------
   /**
    * return the depth of the tree ( just a stub for now)
    */
   public int height()
   {
      return 0;
   }
  
   //-------------------- find( String ) -------------------------
   /**
    * Given a key value, search the tree to find the node that has
    * that key value, if it exists.
    * 
    * Return the Data object from the node or null
    */
   public Data find( String key )
   {
      Data found = null;
      Node cur = _root;
      while ( cur != null && found == null )
      {
         int cmp = key.compareTo( cur.data.key );
         if ( cmp == 0 )
            found = cur.data;
         else if ( cmp < 0 )
            cur = cur.left;
         else 
            cur = cur.right;
      }
      return found;
   }
   //-------------------- findNode( String ) -------------------------
   /**
    * Given a key value, search the tree to find the node that has
    * that key value, if it exists.
    * 
    * Return the Data object from the node or null
    */
   public Node findNode( String key )
   {
      Data found = null;
      Node cur = _root;
      while ( cur != null && found == null )
      {
         int cmp = key.compareTo( cur.data.key );
         if ( cmp == 0 )
            found = cur.data;
         else if ( cmp < 0 )
            cur = cur.left;
         else 
            cur = cur.right;
      }
      return cur;
   }
   
   
   
    //-------------------- delete() --------------------------------
   /**
    * find the node in the tree that has the  maximum value field and
    * delete it from the tree, and return it.
    */
   public Data delete(Data d)
   {
      Data maxData = null;
      Node found = findNode( d.key );
      if ( found != null )
      {
         maxData = found.data;
         removeNode( found );
      }
      return maxData;
   }

 

      
   //-------------------- removeNode( Node ) ------------------------------
   /**
    * Remove a specific node from the tree
    */
   private void removeNode( Node n )
   {
      if (_root == n)  // n is the root
      {
         if (_root.left == null)
         {
            _root = _root.right;
         }
         else if (_root.right == null)
         {
            _root = _root.left;
         }
         else
         {
            addToFarRight(_root.left, _root.right);
            _root = _root.left;
         }           
      }
      else  // n is not the root
      {
         Node parent = n.parent;
         if ( parent.left == n )  // n is left child of parent
         {
        	 if ( n.left == null && n.right == null )
        	 {
        		 parent.left = null;
        	 }
        	 else if (n.left == null)
            {
               parent.left = n.right;
               n.right.parent = parent;
            }
        	 else if (n.right == null)
        	 {
        		 parent.left = n.left;
        		 n.left.parent = parent;
        	 }
            else
            {
               parent.left = n.left;
               n.left.parent = parent;
               addNode( n.left, n.right );
            }
         }
         else   // n is right child of parent
         {
        	 if ( n.left == null && n.right == null )
        	 {
        		 parent.right = null;
        	 }
        	 else if (n.left == null)
            {
               parent.right = n.right;
               n.right.parent = parent;
            }
        	 else if (n.right == null)
        	 {
        		 parent.right = n.left;
        		 n.left.parent = parent;
        	 }
            else
            {
               parent.right = n.left;
               n.left.parent = parent;
               addNode( n.left, n.right );
            }
         }
      }
  
   }
   //---------------------- addToFarRight( node, node ) ----------------
   /**
    * add the subtree Node as the right most descendant of the 1st argument
    */
   private void addToFarRight( Node n, Node subtree )
   {
      while ( n.right != null )
         n = n.right;
      n.right = subtree;
   }
   //----------------------- addToFarLeft( Node, Node ) -----------------------
   /**
    * add the subtree Node as the left most descendant of the 1st argument
    */
   private void addToFarLeft( Node n, Node subtree )
   {
      while ( n.left != null )
         n = n.left;
      n.left = subtree;
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
        
        
