////////////////////////////////////////////////////////////////////////////////////
//
//  Lab 12
//
//  Author  Sang Hyeong Woo 
//  Username   sangwoo
//  Last Edited:  April 24th
//
//
//////////////////////////////////////////////////////////////////////////////////
package Lab12;

import java.util.Random;

public class BST<Key extends Comparable, Value> {

	private Node root;
	
	// note: the outer class has direct access to values in this inner class
	private class Node {
		
		// instance variables
		private Key key;
		private Value value;
		private Node lChild;
		private Node rChild;

		// number of nodes at this subtree
		// N for the root == # of nodes in entire tree
		// N for lead node == 1
		private int N;

		// Constructor with 3 variables
		public Node(Key key, Value value, int N) {
			// TODO
			this.key = key;
			this.value = value;
			this.N = N;
		}
	}
	
	
	// getter method to be used in JUnit test
	public Node getRoot() {
		return this.root;
	}

	
	// returns the number of nodes in the tree through recursion
	public int size(Node root) {
		// TODO: return # of nodes in the tree
		if (root == null) {
			return 0;
		}
		else return 1 + size(root.lChild) + size(root.rChild);
	}

	
	// helper method to go through the entire tree and get the desired value
	public Node getHelper(Key key, Node root) {
		if (root == null || root.key.compareTo(key) == 0) {
			return root;
		}
		else if (key.compareTo(root.key) < 0) {
			return getHelper(key, root.lChild);
		}
		else {
			return getHelper(key, root.rChild);
		}
	}
	
	
	// get function to return desired value
	public Value get(Key key) {
		// TODO: return value associated with key, or null if key doesn't exist
		
		Node root = getHelper(key, this.root);
		if (root != null) {
			return root.value;
		}
		else {
			return null;
		}
		
	}

	
	// helper method to go through an existing tree recursively and find the ideal position to put the given value
	public void putHelper (Key key, Value val, Node root) {
		// if a node is null, then put it in the current position
		if (root == null) {
			root = new Node (key, val, 1);
		}
		// if the key is smaller, then move onto the left subtree
		else if (key.compareTo(root.key) <= 0) {
			if (root.lChild == null) {
				root.lChild = new Node (key, val, 1);
				root.N += 1;
			}
			else {
				root.N += 1;
				putHelper (key, val, root.lChild);
			}
		}
		// if the key is larger, then move onto the right subtree
		else if (key.compareTo(root.key) > 0) {
			if (root.rChild == null) {
				root.rChild = new Node (key, val, 1);
				root.N += 1;
			}
			else {
				root.N += 1;
				putHelper(key, val, root.rChild);
			}
		}
	}
	
	
	// find the ideal place to insert a given value in an existing tree
	public void put(Key key, Value val) {
		// TODO: insert key/value pair into tree
		
		if (root == null) {
			root = new Node (key, val, 1);
		}
		else {
			putHelper(key, val, root);
		}
	}

	
	// helper method to go through the entire tree and add each values to a string
	public String walkHelper (Node root) {
		String inOrder = "";
		// if the current position is null, then return an empty string
		if (root == null) {
			return "";
		}
		// if the current position is a leaf, then add the current position's value into the string
		else if (root.lChild == null && root.rChild == null) {
			inOrder += root.value;
		}
		// go through the entire tree using recursive method
		else {
			inOrder += walkHelper(root.lChild);
			inOrder += root.value;
			inOrder += walkHelper(root.rChild);
		}
		return inOrder;
	}
	
	
	// walk the tree in order & print the values
	public void walk(Node root) {
		System.out.println(walkHelper(root));
		System.out.println("The number of nodes is: " + root.N);
	}

	
	// return the inorder value of a tree
	public String inOrderString (Node root) {
		String inOrder = "";
		// if the current position is null, then return an empty string
		if (root == null) {
			return "";
		}
		// if the current position is a leaf, then add the current position's value into the string
		else if (root.lChild == null && root.rChild == null) {
			inOrder += root.value;
		}
		// go through the entire tree using recursive method
		else {
			inOrder += preOrderString(root.lChild);
			inOrder += root.value;
			inOrder += preOrderString(root.rChild);
		}
		return inOrder;
	}
	
	
	// return the preorder value of a tree
	public String preOrderString (Node root) {
		String preOrder = "";
		// if the current position is null, then return an empty string
		if (root == null) {
			return "";
		}
		// if the current position is a leaf, then add the current position's value into the string
		else if (root.lChild == null && root.rChild == null) {
			preOrder += root.value;
		}
		// go through the entire tree using recursive method
		else {
			preOrder += root.value;
			preOrder += preOrderString(root.lChild);
			preOrder += preOrderString(root.rChild);
		}
		return preOrder;
	}
	
	
	// return the postorder value of a tree
	public String postOrderString (Node root) {
		String postOrder = "";
		// if the current position is null, then return an empty string
		if (root == null) {
			return "";
		}
		// if the current position is a leaf, then add the current position's value into the string
		else if (root.lChild == null && root.rChild == null) {
			postOrder += root.value;
		}
		// go through the entire tree using recursive method
		else {
			postOrder += postOrderString(root.lChild);
			postOrder += postOrderString(root.rChild);
			postOrder += root.value;
		}
		return postOrder;
	}
	
	
	// return preorder, postorder, and inorder strings
	@Override
	public String toString() {
		return "Pre order is: " + preOrderString(root) + 
				"\nPost order is: " + postOrderString(root) +
				"\nIn order is: " + inOrderString(root);
		// TODO: "returns a pre-order, post-order, and in-order walk of the tree printing the values"
		
	}
	
	
	// helper method to check if two given nodes have equal key and equal value
	public boolean equalsHelper (Node node1, Node node2) {
		if (node1 == null && node2 == null) {
			return true;
		}
		else if (node1 != null && node2 != null) {
			return (node1.key == node2.key && node1.value.equals(node2.value) && equalsHelper(node1.lChild, node2.lChild) && equalsHelper(node1.rChild, node2.rChild));			
		}
		else return false;
	}
	
	
	// returns boolean after checking if two nodes are equal
	public boolean equals(BST<Key, Value> other) {
		return equalsHelper(this.root, other.root);
		// TODO: returns whether this tree (using root node) is exactly same as another BST
 
	}

	
	public static void main(String[] args) {
		Random rand = new Random();
		BST<Integer, Character> tree = new BST<>();

		for (int i = 0; i < 25; i++) {
			int key = rand.nextInt(26) + 'a';
			char val = (char) key;
			tree.put(key, val);
		}

		// note: not all of these chars will end up being generated from the for loop
		// some of these return values will be null
		System.out.println(tree.get((int) 'a'));
		System.out.println(tree.get((int) 'b'));
		System.out.println(tree.get((int) 'c'));
		System.out.println(tree.get((int) 'f'));
		System.out.println(tree.get((int) 'k'));
		System.out.println(tree.get((int) 'x'));

		tree.walk(tree.root);

		// TODO: test equals and toString methods
		System.out.println();
		System.out.println(tree.equals(tree));
		System.out.println(tree.toString());
		}
	}
