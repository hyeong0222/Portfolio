import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Tree interface and BinaryTree class from lec4b.
 */

public class BinaryTree implements Tree {
  
  class Node {
    int data;
    Node left, right;
    
    Node(int key) {
      this(key, null, null);
    }
    
    Node(int data, Node left, Node right) {
      this.data = data;
      this.left = left;
      this.right = right;
    }
    
    boolean isLeaf() {
      return left == null && right == null;
    }
  }
  
  Node root;
  int n;
  
  /**
   * @param key
   * 
   * insert method takes a key integer and loop through a binary tree 
   * to find where to insert the key value.
   * 
   */
  public void insert(int key) {
    n++;
    if (root == null)
      root = new Node(key);
    else if (root.left == null)
      root.left = new Node(key);
    else if (root.right == null)
      root.right = new Node(key);
    else if (key % 2 == 0)
      root = new Node(key, root, null);
    else
      root = new Node(key, null, root);
  }
  
  /**
   * @param key
   * @return boolean result of the containsHelper method
   */
  public boolean contains(int key) {
    return containsHelper(key, root);
  }
  
  /**
   * @param key
   * @param p
   * @return boolean if given key is included in the tree
   */
  private boolean containsHelper(int key, Node p) {
    if (p == null)
      return false;
    if (p.data == key)
      return true;
    return containsHelper(key, p.left) || containsHelper(key, p.right);
  }
  
  /**
   * @return the size of the tree
   */
  public int size() {
    return n;
  }
  
  /**
   * @param node
   * @return the height of the binary tree
   */
  public int height(Node node) {
	  if (node == null) {
		  return 0;
	  }
	  
	  else return Math.max(height(node.left), height(node.right)) + 1;
  }
  
  public Node pruneLeaves(Node T) {
	  
	  // if the node is null, return itself
	  if (T == null)
		  return T;
	  
	  // when the left child of the node is a leaf
	  else if (T.left.isLeaf()) {
		  T.left = null;
		  n--;
	  }
	  
	  // when the right child of the node is a leaf
	  else if (T.right.isLeaf()) {
		  T.right = null;
		  n--;
	  }
	  
	  // recursion on the left subtree and the right subtree
	  pruneLeaves(T.left);
	  pruneLeaves(T.right);
	  return T;
  }
  
  public List<Integer> levelOrder() {
	  // use queue because you want to pull the element from the first, not from the back
	  Queue<Node> queue = new LinkedList<Node>();
	  ArrayList<Integer> list = new ArrayList<Integer>();
	  
	  // adding the root as the first element to the queue
	  queue.add(root);
	  
	  // recursion while the queue is not empty
	  while(queue.isEmpty() == false) {
		  Node T = queue.poll();
		  list.add(T.data);
		  
		  // recursive on the left subtree
		  if(T.left != null) {
			  queue.add(T.left);
		  }
		  
		  // recursive on the right subtree
		  else if (T.right != null) {
			  queue.add(T.right);
		  }
	  }
	  return list;
	  
  }
  
  public static void main(String... args) {
    int[] a = new int[] { 3, 9, 7, 2, 1, 5, 6, 4, 8 };
    BinaryTree tr = new BinaryTree();
    assert tr.isEmpty();
    for (int key : a)
      tr.insert(key);
    assert tr.size() == a.length;
    assert !tr.root.isLeaf();
    for (int key : a)
      assert tr.contains(key);
    try { 
      tr.remove(3);
    }
    catch (UnsupportedOperationException e) {
    }
    System.out.println("Height of the tree is: " + tr.height(tr.root));
    
//    int[] b = new int[] { 8, 2, 5, 4, 9, 3, 6, 1, 7, 12, 18, 14, 11, 27 };
//    BinaryTree tr2 = new BinaryTree();
//    assert tr2.isEmpty();
//    for (int key2 : b)
//    	tr2.insert(key2);
//    assert tr2.size() == b.length;
//    assert !tr2.root.isLeaf();
//    for (int key2 : b)
//    	assert tr2.contains(key2);
//    try {
//    	tr2.remove(3);
//    }
//    catch (UnsupportedOperationException e) {
//    }
//    System.out.println("Height of the tree is: " + tr2.height(tr2.root));


    System.out.println("Passed all tests...");
  }
}

interface Tree {
  void insert(int key);
  default void remove(int key) {
    throw new UnsupportedOperationException();
  }
  boolean contains(int key);
  int size();
  default boolean isEmpty() {
    return size() == 0;
  }
}

