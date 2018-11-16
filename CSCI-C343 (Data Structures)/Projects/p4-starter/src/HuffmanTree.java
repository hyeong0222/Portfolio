import java.util.Comparator;

/**
 * TODO: Complete the implementation of this class.
 * 
 * A HuffmanTree represents a variable-length code such that the shorter the
 * bit pattern associated with a character, the more frequently that character
 * appears in the text to be encoded.
 */

public class HuffmanTree {
  
  class Node {
    protected char key;
    protected int priority;
    protected Node left, right;
    
    public Node(int priority, char key) {
      this(priority, key, null, null);
    }
    
    public Node(int priority, Node left, Node right) {
      this(priority, '\0', left, right);
    }
    
    public Node(int priority, char key, Node left, Node right) {
      this.key = key;
      this.priority = priority;
      this.left = left;
      this.right = right;
    }
    
    public boolean isLeaf() {
      return left == null && right == null;
    }
  }
  
  protected Node root;
  
  /**
   * TODO
   * 
   * Creates a HuffmanTree from the given frequencies of letters in the
   * alphabet using the algorithm described in lecture.
   */
  public HuffmanTree(FrequencyTable charFreqs) {
    Comparator<Node> comparator = (x, y) -> {

      /**
       *  TODO: x and y are Nodes
       *  x comes before y if x's priority is less than y's priority
       */
    	if (x.priority > y.priority) {
    		return 1;
    	}
    	else if (x.priority < y.priority) {
    		return -1;
    	}
    	else return 0;
    };  
    PriorityQueue<Node> forest = new Heap<Node>(comparator);

    /**
     * TODO: Complete the implementation of Huffman's Algorithm.
     * Start by populating forest with leaves.
     */
    
    charFreqs.forEach((m ,n) -> {forest.insert(new Node (n, m));});
    
    if (forest.size() == 1) {
    	forest.insert(new Node(0, '\0'));
    }
    
    while(forest.size() > 1) {
    	Node left = forest.delete();
    	Node right = forest.delete();
    	Node parent = new Node (left.priority + right.priority, left, right);
    	forest.insert(parent);
    	this.root = parent;
    }
  }
  
  /**
   * TODO
   * 
   * Returns the character associated with the prefix of bits.
   * 
   * @throws DecodeException if bits does not match a character in the tree.
   */
  public char decodeChar(String bits) {
	  Node node = root;
	  int i = 0;
	  
	  while (!node.isLeaf()) {
		  if (i < bits.length()) {
			  if (bits.charAt(i) == '0') {
				  node = node.left;
			  }
			  else if (bits.charAt(i) == '1') {
				  node = node.right;
			  }
			  i++;
		  }
	  }
	  
	  if (node != null) {
		  return node.key;
	  }
	  
	  throw new DecodeException(bits);
  }
    
  /**
   * TODO
   * 
   * Returns the bit string associated with the given character. Must
   * search the tree for a leaf containing the character. Every left
   * turn corresponds to a 0 in the code. Every right turn corresponds
   * to a 1. This function is used by CodeBook to populate the map.
   * 
   * @throws EncodeException if the character does not appear in the tree.
   */
  public String lookup(char ch) {
    if (this.root == null) {
    	return "";
    }
    String st = "";
    String string = lookupHelper(root, ch, st);
    if (string == null) {
    	throw new EncodeException(ch);
    }
    else return string;
  }
  
  public String lookupHelper ( Node node, char character, String string) {
	  String code;
	  if (node.isLeaf()) {
		  if (node.key == character) {
			  code = string;
		  }
		  else code = null;
	  }
	  else {
		  code = lookupHelper(node.left, character, string + '0');
		  if (code == null) {
			  code = lookupHelper(node.right, character, string + '1');
		  }
	  }
	  return code;
	  
	  
  }
}

