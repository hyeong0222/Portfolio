import java.util.function.BiPredicate;

/**
 * TODO: This is your second major task.
 *
 * This class implements a generic height-balanced binary search tree, 
 * using the AVL algorithm. Beyond the constructor, only the insert()
 * method needs to be implemented. All other methods are unchanged.
 */

public class AVLTree<K> extends BinarySearchTree<K> {

  /**
   * Creates an empty AVL tree as a BST organized according to the
   * lessThan predicate.
   */
  public AVLTree(BiPredicate<K, K> lessThan) {
    super(lessThan);
  }

  /**
   * TODO
   * 
   * Inserts the given key into this AVL tree such that the ordering 
   * property for a BST and the balancing property for an AVL tree are
   * maintained.
   */
  
  public Node insert(K key) {
    Node q = super.insert(key);
    return q;
  }
}
