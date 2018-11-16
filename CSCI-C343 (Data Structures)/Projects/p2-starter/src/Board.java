import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

/**
 * A Board represents the current state of the game. Boards know their dimension, 
 * the collection of tiles that are inside the current flooded region, and those tiles 
 * that are on the outside.
 * 
 * @author <Sang Hyeong Woo>
 */

public class Board {
  public Map<Coord, Tile> inside, outside;
  private int size;
  
  /**
   * Constructs a square game board of the given size, initializes the list of 
   * inside tiles to include just the tile in the upper left corner, and puts 
   * all the other tiles in the outside list.
   */
  public Board(int size) {
    // A tile is either inside or outside the current flooded region.
    inside = new HashMap<>();
    outside = new HashMap<>();
    this.size = size;
    for (int y = 0; y < size; y++)
      for (int x = 0; x < size; x++) {
        Coord coord = new Coord(x, y);
        outside.put(coord, new Tile(coord));
      }
    // Move the corner tile into the flooded region and run flood on its color.
    Tile corner = outside.remove(Coord.ORIGIN);
    inside.put(Coord.ORIGIN, corner);
    flood(corner.getColor());
  }
  
  /**
   * Returns the tile at the specified coordinate.
   */ 
  public Tile get(Coord coord) {
    if (outside.containsKey(coord))
      return outside.get(coord);
    return inside.get(coord);
  }
  
  /**
   * Returns the size of this board.
   */
  public int getSize() {
    return size;
  }
  
  /**
   * TODO
   * 
   * @return true iff all tiles on the board have the same color.
   */
  public boolean fullyFlooded() {
	  if (outside.isEmpty()) {
		  return true;
	  }
	  else return false;
  }
  
  /**
   * TODO
   * @param the color that would flood the region corresponding to the inside
   * 
   * Updates this board by changing the color of the current flood region 
   * and extending its reach.
   */
  public void flood(WaterColor color) {
	  
	  for (Coord coord : inside.keySet()) {
		  inside.get(coord).setColor(color);
	  }
	  
	  Queue flooded = new LinkedList(inside.keySet());
	  while (flooded.isEmpty() == false) {
		  Coord current = (Coord) flooded.remove();
		  List neighbors = current.neighbors(size);
		  Iterator<Coord> iterator = neighbors.iterator();
		  while (iterator.hasNext() == true) {
			  Coord neighbor2 = (Coord) iterator.next();
			  if (inside.containsKey(neighbor2) == false && outside.get(neighbor2).getColor() == color) {
				  inside.put(neighbor2, outside.get(neighbor2));
				  flooded.add(neighbor2);
				  outside.remove(neighbor2);
			  }
		  }
	  }
  }
  
  /**
   * TODO
   * 
   * @param the color that would flood the region corresponding to the inside
   * 
   * Explore a variety of algorithms for handling a flood. Use the same interface 
   * as for flood above, but add an index so your methods are named flood1,
   * flood2, ..., and so on. Then, use the batchTest() tool in Driver to run game
   * simulations and then display a graph showing the run times of all your different 
   * flood functions. Do not delete your flood code attempts. For each variety of 
   * flood, including the one above that you eventually settle on, write a comment
   * that describes your algorithm in English. For those implementations that you
   * abandon, state your reasons.
   */
  
  public void flood1(WaterColor color) {
	  
	  for (Coord coord : inside.keySet()) {
		  inside.get(coord).setColor(color);
	  }
	  
	  Queue flooded = new PriorityQueue(inside.keySet());
	  while (flooded.isEmpty() == false) {
		  Coord current = (Coord) flooded.remove();
		  List neighbors = current.neighbors(size);
		  Iterator<Coord> iterator = neighbors.iterator();
		  while (iterator.hasNext() == true) {
			  Coord neighbor2 = (Coord) iterator.next();
			  if (inside.containsKey(neighbor2) == false && outside.get(neighbor2).getColor() == color) {
				  inside.put(neighbor2, outside.get(neighbor2));
				  flooded.add(neighbor2);
				  outside.remove(neighbor2);
			  }
		  }
	  }
  }
  
  public void flood2(WaterColor color) {
	  
	  for (Coord coord : inside.keySet()) {
		  inside.get(coord).setColor(color);
	  }

	  for (int x = 0; x < size; x++) {
		  for (int y = 0; y < size; y++) {
			  Coord inside1 = new Coord(x,y);
			  if (inside.containsKey(inside1)) {
				  Queue neighbors = new PriorityQueue();
				  neighbors.addAll(inside1.neighbors(size));
				  Iterator<Coord> iterator = neighbors.iterator();
				  
				  while (iterator.hasNext()) {
					  Coord neighbor2 = (Coord) iterator.next();
					  if (inside.containsKey(neighbor2) == false && outside.get(neighbor2).getColor() == color) {
						  inside.put(neighbor2, outside.get(neighbor2));
						  outside.remove(neighbor2);
						  x = 0;
						  y = 0;
					  }
				  }
			  }
		  }
	  }
  }
  
  /**
   * TODO
   * 
   * @return the "best" GameColor for the next move. 
   * 
   * Modify this comment to describe your algorithm. Possible strategies to pursue 
   * include maximizing the number of tiles in the current flooded region, or maximizing
   * the size of the perimeter of the current flooded region.
   */
  public WaterColor suggest() {
    WaterColor cornerColor = inside.get(Coord.ORIGIN).getColor();
    return WaterColor.pickOneExcept(cornerColor);
  }
  
  /**
   * Returns a string representation of this board. Tiles are given as their
   * color names, with those inside the flooded region written in uppercase.
   */ 
  public String toString() {
    StringBuilder ans = new StringBuilder();
    for (int y = 0; y < size; y++) {
      for (int x = 0; x < size; x++) {
        Coord curr = new Coord(x, y);
        WaterColor color = get(curr).getColor();
        ans.append(inside.containsKey(curr) ? color.toString().toUpperCase() : color);
        ans.append("\t");
      }
      ans.append("\n");
    }
    return ans.toString();
  }
  
  /**
   * Simple testing.
   */
  public static void main(String... args) {
    // Print out boards of size 1, 2, ..., 5
    int n = 5;
    for (int size = 1; size <= n; size++) {
      Board someBoard = new Board(size);
      System.out.println(someBoard);
    }    
  }
}






