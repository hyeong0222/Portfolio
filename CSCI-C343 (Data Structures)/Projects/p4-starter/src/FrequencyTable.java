import java.util.HashMap;

/**
 * TODO: Complete the implementation of this class.
 */

public class FrequencyTable extends HashMap<Character, Integer> {
  /**
   * Constructs an empty table.
   */
  public FrequencyTable() {
    super();
  }
    
  /**
   * TODO: Make use of get() and put().
   * 
   * Constructs a table of character counts from the given text string.
   */
  public FrequencyTable(String text) {
	  char[] characters = text.toCharArray();
	  for (char c : characters) {
		  int count = get(c);
		  
		  put(c, count + 1);
	  }
    
  }
  
  /**
   * TODO
   * 
   * Returns the count associated with the given character. In the case that
   * there is no association of ch in the map, return 0.
   */
  @Override
  public Integer get(Object ch) {
	  Integer count = super.get(ch);
	  if (count == null) {
		  return 0;
	  }
	  else return count;
  }
}
