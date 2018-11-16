import java.util.LinkedList;
import java.util.List;
import java.util.function.BiPredicate;

/**
 * Collaborated with Jihoon Ban (jiban)
 * 
 * TODO: This is your first major task.
 * 
 * This class implements a generic unbalanced binary search tree (BST).
 */

public class BinarySearchTree<K> implements Tree<K> {
  
  /**
   * A Node is a Location, which means that it can be the return value
   * of a search on the tree.
   */
  
  class Node implements Location<K> { 
    protected K data;
    protected Node left, right;
    protected Node parent;     // the parent of this node
    protected int height;      // the height of the subtree rooted at this node
    protected boolean dirty;   // true iff the key in this node has been removed

    /**
     * Constructs a leaf node with the given key.
     */
    public Node(K key) {
      this(key, null, null);
    }
    
    /**
     * TODO
     * 
     * Constructs a new node with the given values for fields.
     */
    public Node(K data, Node left, Node right) {
    	this.data = data;
    	this.left = left;
    	this.right = right;
    	this.height = 1;
    	this.dirty = false;
    }

    
    /**
     * Return true iff this node is a leaf in the tree.
     */
    protected boolean isLeaf() {
      return left == null && right == null;
    }
    
    /**
     * TODO
     * 
     * Performs a local update on the height of this node. Assumes that the 
     * heights in the child nodes are correct. This function *must* run in 
     * O(1) time.
     */
    protected void fixHeight() {
    	if (isLeaf() == true) {
    		height = 1;
    	}
    	
    	else {
    		int leftH = 0;
    		int rightH = 0;
    		
    		if (left != null) {
    			leftH = left.height;
    		}
    		if (right != null) {
    			rightH = right.height;
    		}
    		
    		if (leftH >= rightH) {
    			height = leftH + 1;
    		}
    		else height = rightH + 1;
    	}
    }
    
    
    /**
     * TODO
     * 
     * Returns the data in this node.
     */
    public K get() {
      return this.data;
    }
    

    
    /**
     * TODO
     * 
     * Returns the location of the node containing the inorder predecessor
     * of this node.
     */
    
    public Node getBefore() {
    	Node node = this;
    	if (node.left != null) {
    		return maximum(node.left);
    	}
    	Node parent = node.parent;
    	while (parent != null && node == parent.left) {
			node = parent;
			parent = parent.parent;
    	}
    	return parent;
    }
    
    public Node maximum(Node root) {
    	if (root.right == null) {
    		return root;
    	}
    	else return maximum(root.right);
    }
  
    /**
     * TODO
     * 
     * Returns the location of the node containing the inorder successor
     * of this node.
     */
    public Node getAfter() {
    	Node node = this;
    	if (node.right != null) {
    		return minimum(node.right);
    	}
    	Node parent = node.parent;
    	while (parent != null && node == parent.right) {
//    		if (node == parent.right) {
    			node = parent;
    			parent = parent.parent;
//    		}
    	}
    	return parent;
    }
 
    public Node minimum(Node root) {
    	if (root.left == null) {
    		return root;
    	}
    	else return minimum(root.left);
    }

    /**
     * TODO
     *
     * Checks if the height difference of this node's subtrees is greater than 1
     */
     public boolean isOverweight() {
    	 
    	 int leftH;
    	 int rightH;
    	 
    	 if (this.right == null) {
    		 rightH = 0;
    	 }
    	 else rightH = this.right.height;
    	 
    	 if (this.left == null) {
    		 leftH = 0;
    	 }
    	 else leftH = this.left.height;
    	 
    	 return Math.abs(leftH - rightH) > 1;
     }

     /**
      * TODO
      * 
      * Checks if the subtrees of this node have the same height.
      */
      public boolean isBalanced() {
        int leftH;
        int rightH;
        
        if (this.right == null) {
        	rightH = 0;
        }
        else rightH = this.right.height;
        
        if (this.left == null) {
        	leftH = 0;
        }
        else leftH = this.left.height;
        
        return (leftH == rightH);
      }
  }
  
  protected Node root;
  protected int n;
  protected BiPredicate<K, K> lessThan;

  protected int dirtyCount;
  
  /**
   * Constructs an empty BST, where the data is to be organized according to
   * the lessThan relation.
   */
  public BinarySearchTree(BiPredicate<K, K> lessThan) {
    this.lessThan = lessThan;
  }
  
  /**
   * TODO
   * 
   * Looks up the key in this tree and, if found, returns the (possibly dirty)
   * location containing the key.
   */
  public Node search(K key) {
	  Node node = root;
	  
	  while (node != null) {
		  if (node.data.equals(key)) {
			  return node;
		  }
		  else if (lessThan.test(node.data, key)){
			  node = node.right;
		  }
		  else node = node.left;
		  
	  }
	  
    return null;
  }
  
  

  /**
   * TODO
   * 
   * Returns the height of this tree. Runs in O(1) time!
   */
  public int height() {
	  if (root != null) {
		  return root.height;
	  }
	  else return 0;
  }
  
  /**
   * TODO
   * 
   * Clears all the keys from this tree. Runs in O(1) time!
   */
  public void clear() {
	  root = null;
	  n = 0;
  }

  /**
   * Returns the number of keys in this tree.
   */
  public int size() {
    return n;
  }
  
  /**
   * TODO
   * 
   * Inserts the given key into this BST, as a leaf, where the path
   * to the leaf is determined by the predicate provided to the tree
   * at construction time. The parent pointer of the new node and
   * the heights in all node along the path to the root are adjusted
   * accordingly.
   * 
   * Note: we assume that all keys are unique. Thus, if the given
   * key is already present in the tree, nothing happens.
   * 
   * Returns the location where the insert occurred (i.e., the leaf
   * node containing the key).
   */
  public Node insert(K key) {
	  Node node = root;
	  boolean contain = contains(key);
	  
	  if (root == null) {
		 n++;
		 root = new Node(key, null, null);
		 contain = true;
	  }
	  else if (root.data.equals(key)) {
		  if (root.dirty) {
			  n++;
			  root.dirty = false;
			  contain = true;
		  }
	  }
	  
	  while (node != null && contain == false) {
		  if (lessThan.test(key, node.data)) {
			  if (node.left == null) {
				  n++;
				  node.left = new Node (key, null, null);
				  node.left.parent = node;
				  node = node.left;
				  contain = true;
			  }
			  else if (node.left.data.equals(key) && node.left.dirty == true) {
				  n++;
				  node.left.dirty = false;
				  node = node.left;
				  contain = true;
			  }
			  else node = node.left;
		  }
		  else {
			  if(node.right == null) {
				  n++;
				  node.right = new Node (key, null, null);
				  node.right.parent = node;
				  node = node.right;
				  contain = true;
			  }
			  else if (node.right.data.equals(key) && node.right.dirty == true) {
				  n++;
				  node.right.dirty = false;
				  node = node.right;
				  contain = true;
			  }
			  else node = node.right;
			  
		  }
	  }
	  
	  node = search(key);
	  Node node2 = node;
	  
	  while (node != null) {
		  node.fixHeight();
		  node = node.parent;
	  }
	  
	  return node2;
  }
  
  /**
   * TODO
   * 
   * Returns true iff the given key is in this BST.
   */
  public boolean contains(K key) {
    Node node = search(key);
    if (node != null && node.dirty == false) {
    	return true;
    }
    else return false;
  }

  /**
   * TODO
   * 
   * Removes the key from this BST. If the key is not in the tree,
   * nothing happens. Implement the removal using lazy deletion.
   */
  public void remove(K key) {
	  Node node = search(key);
	  if (node != null) {
		  if(node.dirty == false) {
			  n--;
			  node.dirty = true;
		  }
	  }
  }
  
  /**
   * TODO
   * 
   * Clears out all dirty nodes from this BST.
   * 
   * Use the following algorithm:
   * (1) Let ks be the list of keys in this tree. 
   * (2) Clear this tree.
   * (2) For each key in ks, insert it into this tree.
   */
  public void rebuild() {
	  List<K> ks = keys();
	  clear();
	  for (K elements : ks) {
		  insert(elements);
	  }
  }
    
  /**
   * TODO
   * 
   * Returns a sorted list of all the keys in this tree.
   */
  public List<K> keys() {
//	  List<K> list = new LinkedList<>();
//	  Node node = root;
//	  
//	  if (root != null) {
//		  while
//		  
//	  }
	  List<K> list = new LinkedList<>();
	  Node p = root;
	  
	  if(root != null) {
		  while(p.left != null) {
			  p = p.left;
		  }
		  
		  if(!p.dirty)
			  list.add(p.data);
		  
		  while(p.getAfter() != null) {
			  Node tempNode = p.getAfter();
			  list.add(tempNode.data);
			  p = tempNode;
		  }
	  }
	  
    return list;
  }

  /**
   * TODO
   * 
   * Returns a textual representation of this BST.
   */
  public String toString() {
    return "";
  }
}
