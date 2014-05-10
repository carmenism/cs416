
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
 * modified mlb Nov 2008
 */

import java.util.*;
import java.io.*;

public class BinarySearchTree implements Iterable<Data>
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
   //------------------ iterator() ---------------------------------
   public Iterator<Data> iterator()
   {
      return new InfixIterator();
   }
   
   
   
   
   //-------------------------------------------------------------
   //
   //*************   Problem Item #5
   //
   //
   //-------------------- findMin() --------------------------------
   /**
    * find the node in the tree that has the  maximum value field.
    * Remember the tree is organized by "key", not "value", so you need
    * to traverse the entire tree. It doesn't really matter what traversal
    * you do. However, it make sense to define a  method that finds the max
    * value in a subtree and recursively call that for each child of a node.
    */
   public Data findMin()
   {
      return findMin(_root);
   }
   
   public Data findMin(Node n)
   {
      if (n == null)
         return new Data("", "", 200);
      else
      {
         Data l = findMin(n.left);
         Data r = findMin(n.right);
         if (l.value < r.value && l.value < n.data.value)
            return l;
         else if (r.value < l.value && r.value < n.data.value)
            return r;
         return n.data;
      }
   }
   
   //-------------------------------------------------------------
   //
   //*************   Problem Item #6
   //
   //
   //-------------------- countLeaves() --------------------------------
   /**
    * Count the leaves in the tree
    *    call a helper to do recursion
    */
   public int countLeaves()
   {
      return countLeaves(_root);
   }
   
   public int countLeaves(Node n)
   {
      if (n == null)
         return 0;
      else if (n.left == null && n.right == null)
         return 1;
      else
         return (countLeaves(n.left) + countLeaves(n.right));
   }
   
   
   //-------------------------------------------------------------
   //
   //*************   Problem Item #7
   //
   //
   //-------------------- postOrder( ) --------------------------------
   /**
    * Return a String containing the data in post-order
    *  call a helper to do recursion
    */
   public String postOrder(  )
   {
      return postOrder(_root);
   }
   
   public String postOrder(Node n)
   {
      if (n == null)
      {
         return "";
      }
      else
      {
         String l = postOrder(n.left);
         String r = postOrder(n.right);
         return l + r + n.data.toString() + "\n";
      }
   }
   
   
   //-------------------------------------------------------------
   //
   //*************   Problem Item #8
   //
   //
   //-------------------- height() ------------------------------------
   /**
    * return the height of the tree
    * This method calls a helper method that does the recursion.
    */
   public int height()
   {
      return height(_root);
   }
   
   public int height(Node n)
   {
      if (n == null)
         return -1;
      else
         return Math.max(height(n.left), height(n.right)) + 1;
   }
   
   
   //-------------------------------------------------------------
   //
   //*************   Problem Item #9
   //
   //
   //------------------- findParent( String ) -------------------------
   /**
    * find the parent node of a node whose key matches the argument.
    * 
    */
   public Data findParent( String key )
   {
      return null;
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
            //  newOne.parent = parent;
         }
      }
      else
      {
         if ( parent.right != null )
            addNode( parent.right, newOne );
         else 
         {
            parent.right = newOne;
            //  newOne.parent = parent;
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
      
      //--------------- constructor --------------------------------
      public Node( Data d )
      {
         data = d;
         left = null;
         right = null;       
      }
   }
   //++++++++++++++++++++++++ inner class InfixIterator ++++++++++++++++++
   /**
    * An iterator that walks the tree in prefix order
    */
   public class InfixIterator implements Iterator<Data>
   {
      //--------------------- instance variables ---------------------------
      private Stack<NodeVisit> stack;  // top of stack defines the next node
      private Node             nextNode;
      private Node             lastNode;
      
      //+++++++++++ private inner class Step +++++++++++++++++++++++++++++
      private class NodeVisit
      {
         public Node node;
         public int  visit;  // 0, 1, 2, or 3; but only 0, 1 and 2 in stack
         
         public NodeVisit( Node n, int v )
         {
            node = n; visit = v;
         }
      }
      
      //----------------------- constructor ------------------------------
      public InfixIterator()
      {
         nextNode = null;
         if ( _root == null )
            stack = null;
         else
         {
            stack = new Stack<NodeVisit>();
            stack.push( new NodeVisit( _root, 0 ) );
            nextNode = getNext();
         }
         lastNode = null;
      }
      //---------------------- hasNext() -----------------------------------
      public boolean hasNext()
      {
         return  nextNode != null;
      }
      //----------------------- Data next() ----------------------------------
      public Data next()
      {
         lastNode = nextNode;
         Data ret;
         if ( nextNode == null )
            throw new NoSuchElementException( "BinarySearchTree.iterator" );
         ret = nextNode.data;
         nextNode = getNext();
         return ret;
      }
      
      //----------------------- Node getNext() --------------------------------
      private Node getNext() 
      {
         Node ret = null;
         if ( stack.isEmpty() )
            return null;
         NodeVisit top = stack.peek();
         {
            switch ( top.visit++ )  
            {
               case 0:       // about to make first visit; push left onto stack
                  
                  if ( top.node.left != null )
               {
                  stack.push( new NodeVisit( top.node.left, 0 )); 
                  ret = getNext();    // we'll invoke next recursively
               }
                  else
                  {
                     ret = top.node;
                     top.visit++;
                  }
                  break;
               case 1:       // we've been down the left branch, return the node
                  // and set up to go down the right branch
                  ret = top.node;              
                  break;
               case 2:       // done the node, go down the right branch
                  
                  if ( top.node.right != null )
               {
                  stack.push( new NodeVisit( top.node.right, 0 ));
                  ret = getNext();
               }
                  else  // can pop this one off the stack and continue
                  {
                     stack.pop();
                     ret = getNext();
                  }
                  break;
               case 3:       // finished right branch, pop from stack and recurse
                  if ( !stack.isEmpty() )
                  stack.pop();
                  ret = getNext();
                  break;
               default:
                  if ( !stack.isEmpty() )
                  stack.pop();
                  return null;
            }
         }
         //System.out.println( stack.size() + " " + stack.peek().visit + " " + ret );  
         return ret;
      }
      //------------------ remove() ----------------------------------
      public void remove()
      {        
      }
   }     
}


