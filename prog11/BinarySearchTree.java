/**
 * BinarySearchTree -- this class uses a private inner class for
 *      tree nodes. (Although in this version it is public so I can 
 *      do a prefix walk in DisplayPanel.)
 *
 * @author rdb
 * April 2, 2008
 * 
 * Modified: Nov 15 2008  mb
 * 
 * Modified by Carmen St. Jean for Program11
 * CS 416, Fall 2008, November 25, 2008
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
    */
   public Node findNode( String key )
   {
      Node found = null;
      Node cur = _root;
      while ( cur != null && found == null )
      {
         int cmp = key.compareTo( cur.data.key );
         if ( cmp == 0 )
            found = cur;
         else if ( cmp < 0 )
            cur = cur.left;
         else 
            cur = cur.right;
      }
      return found;
   }
   //-------------------- height() ------------------------------------
   /**
    * return the depth of the tree
    */
   public int height()
   {
      return height( _root );
   }
   
   //-------------------- height( Node ) --------------------------------
   /**
    * return the depth of the subtree rooted at node
    */
   private int height( Node node )
   {
      if ( node == null ) 
         return 0;
      else 
      {
         int leftHeight = height( node.left );
         int rightHeight = height( node.right );
         if ( leftHeight > rightHeight )
            return leftHeight + 1;
         else
            return rightHeight + 1;
      }         
   }
      
   //-------------------- removeNode( Node ) ------------------------------
   /**
    * Remove a specific node from the tree
    */
   public void removeNode( Node n )
   {
      if ( _root == n )
      {
         if ( _root.left != null )
         {
            addToFarRight( _root.left, _root.right );
            _root = _root.left;
            _root.parent = null;
         }
         else if ( _root.right != null )
         {
            addToFarLeft( _root.right, _root.left );
            _root = _root.right;
            _root.parent = null;
         }
         else
            _root = null;
      }
      else
      {
         Node parent = n.parent;
         if ( parent.left == n )  // this is left child of parent
         {
            if ( n.left == null )
               parent.left = n.right;
            else
            {
               n.left.parent = parent;
               parent.left = n.left;
               addToFarRight( parent.left, n.right );
            }
            if ( n.right != null )    // update parent reference if needed
               n.right.parent = parent;
         }
         else   // this is right child of parent
         {
            if ( n.right == null )
               parent.right = n.left;
            else
            {
               parent.right = n.right;
               n.right.parent = parent;
               addToFarLeft( parent.right, n.left );
            }
            if ( n.left != null )    // update parent reference if needed
               n.left.parent = parent;
         }
      }
      _size--;
      
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
      {
         addNode( _root, newNode );
         checkAVL(_root);
      }
      _size++;
   }
   
   //-------------- checkAVL(BinarySearchTree.Node n) ------------------
   /**
    * Checks a given Node to see if it is balanced.  If not, it
    * determines the appropriate rotations required.
    */
   public int checkAVL(Node n)
   {
      int bal = 0;
      if (n != null)
      {
         int leftBal = checkAVL(n.left);
         int rightBal = checkAVL(n.right);
         bal = height(n.right) - height(n.left);
         
         if (height(n.left) > height(n.right) + 1 &&
             height(n.left.left) > height(n.left.right))
         {
            rotateRight(n);
         }
         else if (height(n.right) > height(n.left) + 1 &&
                  height(n.right.right) > height(n.right.left))
         {
            rotateLeft(n);
         }
         else if (height(n.right) > height(n.left) + 1 &&
                  height(n.right.left) > height(n.right.right))
         {
            rotateRight(n.right);
            rotateLeft(n);
         }
         else if (height(n.left) > height(n.right) + 1 &&
                  height(n.left.right) > height(n.left.left))
         {
            rotateLeft(n.left);
            rotateRight(n);
         }
      }
      return bal;
   }

   //------------------ rotateRight(Node P) ------------------------------
   /**
    * rotates the AVL subtree to the right around given pivot Node P.
    */
   public void rotateRight(Node P)
   {
      Node L = P.left;
      P.left = L.right;
      L.right = P;
      fixSubtreeParent(P, L);
      if (P.left != null)
         P.left.parent = P;
      P.parent = L;
   }
   
   //------------------ rotateLeft(Node P) ------------------------------
   /**
    * rotates the AVL subtree to the left around given pivot Node P.
    */
   public void rotateLeft(Node P)
   {
      Node R = P.right;
      P.right = R.left;
      R.left = P;
      fixSubtreeParent(P, R);
      if (P.right != null)
         P.right.parent = P;
      P.parent = R;
   }
   
   //--------- fixSubtreeParent(Node oldRoot, Node newRoot) --------------
   /**
    * Fixes the parents so that a rotation is effective.
    */
   public void fixSubtreeParent(Node oldRoot, Node newRoot)
   {
      Node parent = oldRoot.parent;
      if (parent == null)
         _root = newRoot;
      else
      {
         if (parent.left == oldRoot)
            parent.left = newRoot;
         else
            parent.right = newRoot;
      }
      newRoot.parent = parent;
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
         removeNode( lastNode );
         lastNode = null;
      }
   }     
}
        
        
