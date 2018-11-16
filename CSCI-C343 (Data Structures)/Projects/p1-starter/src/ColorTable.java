import java.awt.Color;
import java.util.Arrays;
import java.util.Random;

/**
 * @author (Sang Hyeong Woo)
 * 
 * 
 * A ColorTable represents a dictionary of frequency counts, keyed on Color.
 * It is a simplification of Map<Color, Integer>. The size of the key space
 * can be reduced by limiting each Color to a certain number of bits per channel.
 */

/**
 * TODO
 * 
 * Implement this class, including whatever data members you need and all of the
 * public methods below. You may create any number of private methods if you find
 * them to be helpful. Replace all TODO comments with appropriate javadoc style 
 * comments. Be sure to document all data fields and helper methods you define.
 */

public class ColorTable {
	
	public class Assoc {
		Color color;
		long count;
		
		Assoc (Color color, long count) {
			this.color = color;
			this.count = count;
		}
		
		Color getColor () {
			return this.color;
		}
		
		long getCount () {
			return this.count;
		}
		
		void setColor (Color color) {
			this.color = color;
		}
		
		void setCount (long count) {
			this.count = count;
		}
		
		void countIncrement() {
			this.count++;
		}
	}

	
  /**
   * Counts the number of collisions during an operation.
   */
	private static int numCollisions = 0;
	private Assoc[] table;
	private int bits;
	private int strategy;
	private double threshold;

	
  /**
   * Returns the number of collisions that occurred during the most recent get or
   * put operation.
   */
	public static int getNumCollisions() {
		return numCollisions;
	}

	
  /**
   * @param initialCapacity
   * @param bitsPerChannel
   * @param collisionStrategy
   * @param rehashThreshold
   * 
   * Constructs a color table with a starting capacity of initialCapacity. Keys in
   * the color key space are truncated to bitsPerChannel bits. The collision resolution
   * strategy is specified by passing either Constants.LINEAR or Constants.QUADRATIC for
   * the collisionStrategy parameter. The rehashThrehold specifies the maximum tolerable load 
   * factor before triggering a rehash.
   * 
   * @throws RuntimeException if initialCapacity is not in the range [1..Constants.MAX_CAPACITY]
   * @throws RuntimeException if bitsPerChannel is not in the range [1..8]
   * @throws RuntimeException if collisionStrategy is not one of Constants.LINEAR or Constants.QUADRATIC
   * @throws RuntimeException if rehashThreshold is not in the range (0.0..1.0] for a
   *                             linear strategy or (0.0..0.5) for a quadratic strategy
   */
	public ColorTable(int initialCapacity, int bitsPerChannel, int collisionStrategy, double rehashThreshold) {   
	  
		if (initialCapacity < 1 || initialCapacity > Constants.MAX_CAPACITY) {
		  throw new RuntimeException();
		}
		
		else {
			this.table = new Assoc[initialCapacity];
		}
		
		if (bitsPerChannel < 1 || bitsPerChannel > 8) {
			throw new RuntimeException();  
		}
	  
		else {
			this.bits = bitsPerChannel;
		}
	  
		if (collisionStrategy != Constants.LINEAR && collisionStrategy != Constants.QUADRATIC) {
			throw new RuntimeException();  
		}
	  
		else {
			this.strategy = collisionStrategy;
		}
	  
		if ((collisionStrategy == Constants.LINEAR && (rehashThreshold < 0.0 || rehashThreshold > 1.0)) 
				|| (collisionStrategy == Constants.QUADRATIC && (rehashThreshold < 0.0 || rehashThreshold > 0.5))) {
			throw new RuntimeException();  
		}
	  
		else {
			this.threshold = rehashThreshold;
		}
	}
  
	  /**
	   * @param color
	   * @return the index number of a given color
	   * 
	   */
	public int lookup(Color color) {
		int start = Util.pack(color, this.getBitsPerChannel()) % table.length;
		int i = start;
		int k = 0;
		numCollisions = 0;
		
		while (this.table[i] != null) {
			if (Util.pack(this.table[i].getColor(), this.bits) == Util.pack(color, this.bits)) {
				return i;
			}
			k++;
			if (this.getCapacity() == k) {
				return -1;
			}
			if (this.strategy == Constants.QUADRATIC) {
				i = (start + k * k) % table.length;
			}
			else i = (start + k) % table.length;
			
			numCollisions++;

		}
		return i;
	}

	
  /**
   * @return the number of bits
   * 
   * Returns the number of bits per channel used by the colors in this table.
   */
	public int getBitsPerChannel() {
		return this.bits;
	}

  
  /**
   * @param color
   * @return the count associated with color
   * 
   * Returns the frequency count associated with color. Note that colors not
   * explicitly represented in the table are assumed to be present with a
   * count of zero. Uses Util.pack() as the hash function.
   */
	public long get(Color color) {
		int i = lookup(color);
		
		if (i != -1 && this.table[i] == null) {
			return 0;
		}
		else if (i != -1) {
			return table[i].getCount();
		}
		
		return 0;
	}

	
  /**
   * @param color
   * @param count
   * 
   * Associates the count with the color in this table. Do nothing if count is less than
   * or equal to zero. Uses Util.pack() as the hash function.
   */
	public void put(Color color, long count) {
	  
		int i = lookup(color);
	  
		if (i == -1) {
			this.rehash();
			i = lookup(color);
		}
		
		if (count > 0 && this.table[i] == null) {
			this.table[i] = new Assoc(color, count);
		}
		
		else if (count > 0) {
			this.table[i].setCount(count);
		}
		
		if (this.getLoadFactor() > this.threshold) {
			this.rehash();
		}
	}

	
  /**
   * @param color
   * 
   * Increments the frequency count associated with color. Note that colors not
   * explicitly represented in the table are assumed to be present with a
   * count of zero.
   */
	public void increment(Color color) {
		int i = lookup(color);
		
		if (i == -1 || this.table[i] == null) {
			this.put(color, 1);
		}
		else this.table[i].countIncrement();
	}

	
  /**
   * @return the load factor
   * 
   * Returns the load factor for this table.
   */
	public double getLoadFactor() {
		return (double) (this.getSize() / this.getCapacity());
	}

	
  /**
   * @return the size of the table
   * 
   * Returns the size of the internal array representing this table.
   */
	public int getCapacity() {
		return table.length;
	}

	
  /**
   * @return the number of keys in the table
   * 
   * Returns the number of key/value associations in this table.
   */
	public int getSize() {
		int i = 0;
		
		for (int j = 0; j < table.length; j++) {
			if (table[j] != null) {
				i++;
			}
		}
		
		return i;
	}

	
  /**
   * @return true iff this table is empty
   * 
   * Returns true iff this table is empty.
   */
	public boolean isEmpty() {
		if (table == null || getSize() == 0) {
			return true;
		}
		else return false;
	}

	
  /**
   * TODO
   * 
   * Increases the size of the array to the smallest prime greater than double the 
   * current size that is of the form 4j + 3, and then moves all the key/value 
   * associations into the new array. 
   * 
   * Hints: 
   * -- Make use of Util.isPrime().
   * -- Multiplying a positive integer n by 2 could result in a negative number,
   *    corresponding to integer overflow. You should detect this possibility and
   *    crop the new size to Constants.MAX_CAPACITY.
   * 
   * @throws RuntimeException if the table is already at maximum capacity.
   */
	private void rehash() { 
		
		int before = this.getCapacity();
		int after = 0;
	  
		if (before == Constants.MAX_CAPACITY) {
			throw new RuntimeException();
		}
	  
		else if (before * 2 < 0) {
			after = Constants.MAX_CAPACITY;
		}
		
		else {
			int doubleSize = before * 2;
			for (int i = 0; i < Constants.MAX_CAPACITY - doubleSize; i++) {
				int j = doubleSize + i;
				if (Util.isPrime(j) == true && ((j - 3) % 4 == 0)) {
					after = j;
					break;
				}
			}
		  
			Assoc[] temp = Arrays.copyOf(this.table, before);
			this.table = new Assoc[after];
			for (int k = 0; k < before; k++) {
				if (temp[k] != null) {
					this.put(temp[k].getColor(), temp[k].getCount());
				}
			}
		}
	}

	
  /**
   * TODO
   * 
   * Returns an Iterator that marches through each color in the key color space and
   * returns the sequence of frequency counts.
   */
	public Iterator iterator() {
		return new Iterator() {
			int curr = 0;
			int end = table.length - 1;
			
			public boolean hasNext() {
				return curr < end;
			}
			
			public long next() {
				Assoc iter = table[curr + 1];
				if (iter == null) {
					return 0;
				}
				return iter.getCount();
			}
		};
	}

	
  /**
   * TODO
   * 
   * Returns a String representation of this table.
   */
	public String toString() {
		String table = "";
		for (int i = 0; i < this.table.length; i++) {
			if (this.table[i] != null) {
				table += "Position: " + i + ", Color: " + this.table[i].getColor() + ", Count: " + this.table[i].getCount() + "\n";
			}
		}
		return table;
	}

	
  /**
   * @param i
   * @return the count i the table at index i
   * 
   * Returns the count in the table at index i in the array representing the table.
   * The sole purpose of this function is to aid in writing the unit tests.
   */
	public long getCountAt(int i) { 
		if (table[i] != null) {
			return table[i].getCount();		
		}
		else return 0;
	}

  /**
   * Simple testing.
   */
	public static void main(String[] args) {
		ColorTable table = new ColorTable(3, 6, Constants.QUADRATIC, .49);
		
		int[] data = new int[] { 32960, 4293315, 99011, 296390 };
		for (int i = 0; i < data.length; i++) {
			table.increment(new Color(data[i]));
		}
		
		System.out.println("capacity: " + table.getCapacity()); // Expected: 7
		System.out.println("size: " + table.getSize());         // Expected: 3
    
    /* The following automatically calls table.toString().
       Notice that we only include non-zero counts in the String representation.
       
       Expected: [3:2096,2, 5:67632,1, 6:6257,1]
       
       This shows that there are 3 keys in the table. They are at positions 3, 5, and 6.
       Their color codes are 2096, 67632, and 6257. The code 2096 was incremented twice.
       You do not have to mimic this format exactly, but strive for something compact
       and readable.
       */
		System.out.println(table);  
	}
}
