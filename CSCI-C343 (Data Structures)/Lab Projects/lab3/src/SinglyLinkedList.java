/**
 * Solution to the pre-lecture exercise for lec3b.
 */

public class SinglyLinkedList<T> implements List<T> {

  /**
   * Node is a pair containing a data field and a pointer to
   * the next node in the list.
   */
  class Node {
    T data;
    Node next;
    
    Node(T data) {
      this(data, null);
    }
    
    Node(T data, Node next) {
      this.data = data;
      this.next = next;
    }
  }
  
  Node head;  // points to the first node on this list, initially null
  int n;      // the number of nodes in this list, initially 0
  
  /**
   * Inserts the value x at the end of this list.
   */
  public void add(T... xs) {  
    n += xs.length;
    int i = 0;
    if (head == null) {
    	head = new Node(xs[0]);
    	i = 1;
    }
      Node p = head;
      while (p.next != null)
        p = p.next;
      for (; i < xs.length; i++)
    	  p = p.next = new Node(xs[i]);
  }
  
  /**
   * Removes the element at index i from this list.
   * @return the data in the removed node.
   * @throw IndexOutOfBoundsException iff i is out of range for this list.
   */
  public T remove(int i) {
    if (i < 0 || i >= size())
       throw new IndexOutOfBoundsException();
    n--;
    Node p = head;
    T ans;
    if (i == 0) {
      ans = head.data; 
      head = head.next;
    }
    else {
      while (--i > 0) 
	p = p.next;
      ans = p.next.data;
      p.next = p.next.next;
    }
    return ans;
  }
  
  public void swap (int i, int j) {
	  
	  if (i < 0 || j < 0 || i >= this.size() || j >= this.size()) {
		 throw new IndexOutOfBoundsException();
	  }
	  
	  Node current = head;
	  int min = Math.min(i, j);
	  int max = Math.max(i, j);
	  int k = 0;
	  Node node1 = null;
	  Node node2 = null;
	  T data1 = null;
	  T data2 = null;
	  
	  while (current.next != null) {
		  node2 = node1.next;
		  k++;
		  
		  if (k == min) {
			  node1 = current;
			  data1 = current.data;
		  }
		  if (k == max) {
			  node2 = current;
			  data2 = current.data;
		  }
		  
		  current = current.next;
	  }
	  
	  node1.data = data2;
	  node2.data = data1;
	  
  }
    
  /**
   * Returns the i-th element from this list, where i is a zero-based index.
   * @throw IndexOutOfBoundsException iff i is out of range for this list.
   */
  public T get(int i) {
    if (i < 0 || i >= size())
      throw new IndexOutOfBoundsException();
    Node p = head;
    while (i > 0) {
      p = p.next;
      i--;
    }
    return p.data;
  }

  /**
   * Returns true iff the value x appears somewhere in this list.
   */
  public boolean contains(T x) {
    Node p = head;
    while (p != null)
      if (p.data.equals(x))  // replace == test with one that works on objects
        return true;
      else
        p = p.next;
    return false;
  }

  /**
   * Returns the number of elements in this list.
   */
  public int size() {
    return n;
  }
  
  /**
   * Returns true iff this is the empty list.
   */
  public boolean isEmpty() {
    return size() == 0;
  }
  
  /**
   * Returns a string representing this list (resembling a Racket list).
   */
  public String toString() {
    if (isEmpty())
      return "()";    
    StringBuilder ans = new StringBuilder("(").append(get(0));
    for (int i = 1; i < size(); i++)
      ans.append(" ").append(get(i));
    return ans.append(")").toString();
  }
  
  /**
   * Simple testing.
   */
  public static void main(String... args) {
    // The given tests, but with Integers.
    List<Integer> xs = new SinglyLinkedList<>();
    assert "()".equals(xs.toString());
    int[] a = new int[] { 7, 4, 6, 9, 2 };
    for (int x : a)
      xs.add(x);
    assert "(7 4 6 9 2)".equals(xs.toString());
    for (int x : a)
      assert xs.contains(x);
    for (int i = 0; i < xs.size(); i++)
      assert a[i] == xs.get(i);
    assert "(7 4 6 9 2)".equals(xs.toString());
    xs.remove(3);
    assert "(7 4 6 2)".equals(xs.toString());
    while (!xs.isEmpty())
      xs.remove(0);
    assert "()".equals(xs.toString());
    
    // Test with strings.
    List<String> animals = new SinglyLinkedList<>();
    animals.add("a" + "p" + "e");
    animals.add("bat");
    animals.add("cat");
    animals.add("dog");
    assert animals.contains("ape");
    
    // Test remove first and last.
    animals.remove(0);
    animals.remove(animals.size() - 1);
    assert 2 == animals.size();
    assert "(bat cat)".equals(animals.toString());
    
    animals.add("emu");
    animals.add("fox");
    animals.add("gnu");
    
    // Test inside remove.
    animals.remove(3);
    assert !animals.contains("fox");
    
    // Test remove all.
    while (!animals.isEmpty())
      animals.remove(0);
    assert 0 == animals.size();
    assert animals.isEmpty();
    
    // Test with structured elements.
    List<Fruit> fruit = new SinglyLinkedList<>();
    fruit.add(new Fruit("apple", 3));
    fruit.add(new Fruit("orange", 4));
    fruit.add(new Fruit("pear", 2));
    assert fruit.contains(new Fruit("apple", 3));
    assert !fruit.contains(new Fruit("apple", 4));
    
    SinglyLinkedList<Integer> nums = new SinglyLinkedList<>();
    nums.add(4);
    nums.add(3);
    nums.add(6);
    nums.add(5);
    nums.add(8);
    nums.add(7);
    nums.swap(1, 4);
    assert "(4 8 6 5 3 7)".equals(nums.toString());
  }
}

/**
 * If you want to call yourself a List, then implement this interface:
 */
interface List<T> {
  void add(T... xs);
  T remove(int i);
  T get(int i);
  boolean contains(T x);
  int size();
  boolean isEmpty();
  void swap(int i, int j);
}

/**
 * Used by SinglyLinkedList.main() for testing purposes.
 */
class Fruit {
  String name;
  int count;
  
  Fruit(String name, int count) {
    this.name = name;
    this.count = count;
  }
  
  public boolean equals(Object obj) {
    if (!(obj instanceof Fruit))
      return false;
    Fruit that = (Fruit) obj;
    return name.equals(that.name) && count == that.count;
  }
}