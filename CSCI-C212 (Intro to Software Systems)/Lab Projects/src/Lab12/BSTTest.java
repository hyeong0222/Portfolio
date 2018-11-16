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

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BSTTest {

	@Test
	void testInOrderString() {

		// creating a tree called tree1 with 6 nodes
		BST<Integer, Character> tree1 = new BST<> ();
		
		tree1.put(97, 'a');
		tree1.put(98, 'b');
		tree1.put(99, 'c');
		tree1.put(100, 'd');
		tree1.put(101, 'e');
		tree1.put(102, 'f');
		
		// an inorder string of tree1
		String inOrder = tree1.inOrderString(tree1.getRoot());
		
		// checks if the inorder string is equal to the given string
		assertEquals("abcdef", inOrder);
	}

	@Test
	void testPreOrderString() {

		// creating a tree called tree2 with 6 nodes
		BST<Integer, Character> tree2 = new BST<> ();
		
		tree2.put(97, 'a');
		tree2.put(98, 'b');
		tree2.put(99, 'c');
		tree2.put(100, 'd');
		tree2.put(101, 'e');
		tree2.put(102, 'f');
		
		// a preorder string of tree2
		String preOrder = tree2.preOrderString(tree2.getRoot());
		
		// checks if the preorder string is equal to the given string
		assertEquals("abcdef", preOrder);
	}

	@Test
	void testPostOrderString() {
		BST<Integer, Character> tree3 = new BST<> ();
		
		tree3.put(97, 'a');
		tree3.put(98, 'b');
		tree3.put(99, 'c');
		tree3.put(100, 'd');
		tree3.put(101, 'e');
		tree3.put(102, 'f');
		
		// a postorder string of tree3
		String postOrder = tree3.postOrderString(tree3.getRoot());
		
		// checks if the postorder string is equal to the given string
		assertEquals("fedcba", postOrder);
	}

	@Test
	void testEqualsBSTOfKeyValue() {
		
		// Creating a tree called tree4
		BST<Integer, Character> tree4 = new BST<> ();
		
		tree4.put(97, 'a');
		tree4.put(98, 'b');
		tree4.put(99, 'c');
		tree4.put(100, 'd');
		tree4.put(101, 'e');
		tree4.put(102, 'f');
		
		// check if the equals method returns true when compared to itself
		assertEquals(true, tree4.equals(tree4));	
		
		// A new tree with one different node to tree4
		BST<Integer, Character> tree5 = new BST<> ();
		
		tree4.put(97, 'a');
		tree4.put(98, 'b');
		tree4.put(99, 'c');
		tree4.put(100, 'd');
		tree4.put(101, 'e');
		tree4.put(103, 'g');
		
		// check if the equals method returns true when tree5 is compared to tree4
		assertEquals(false, tree5.equals(tree4));
	}

}
