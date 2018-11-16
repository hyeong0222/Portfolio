import static org.junit.Assert.*;

import org.junit.Test;

/**
 * JUnit tests for all TODO methods.
 */

public class Testing {
	
  // Testing the OnBoard method
  @Test
  public void testOnBoard() {
	assertFalse(new Coord(3, 4).onBoard(4));
    assertTrue(new Coord(3, 4).onBoard(5));
  }
  
  
  // Testing the neighbors method
  @Test
  public void testNeighbors() {
	  assertEquals("[(2, 0), (2, 2), (1, 1)]", new Coord(2, 1).neighbors(3).toString());
	  assertEquals("[(0, 0), (0, 2), (1, 1)]", new Coord(0, 1).neighbors(3).toString());
	  assertEquals("[(1, 0), (1, 2), (2, 1), (0, 1)]", new Coord(1, 1).neighbors(4).toString());	  
  }
  
  
  // Testing hashCode method
  @Test
  public void testHashCode() {
	  assertEquals(4294971, new Coord(3, 4).hashCode());
	  assertEquals(0, new Coord(0, 0).hashCode());
	  assertEquals(4294969, new Coord(7, 2).hashCode());
  }
  
  
  // Testing fullyFlooded method
  @Test
  public void testFullyFlooded() {
	  Board board = new Board(10);
	  
	  while(board.fullyFlooded() == false) {
		  WaterColor nextColor = WaterColor.RED;
		  board.flood(nextColor);
	  }

	  assertFalse(board.fullyFlooded());
  }
  
  
  // Testing flood method
  @Test
  public void flood() {
	  Board board = new Board(10);
	  
	  while (board.fullyFlooded() == false) {
		  assertFalse(board.fullyFlooded());
		  WaterColor nextColor = board.suggest();
		  board.flood(nextColor);
	  }
	  
	  assertTrue(board.fullyFlooded());
  }
  
  
  // Testing flood1 method
  @Test
  public void flood1() {
	  Board board = new Board(10);
	  
	  while (board.fullyFlooded() == false) {
		  assertFalse(board.fullyFlooded());
		  WaterColor nextColor = board.suggest();
		  board.flood1(nextColor);
	  }
	
	  assertTrue(board.fullyFlooded());
  }
  
  
  // Testing flood2 method
  @Test
  public void flood2() {
	  Board board = new Board(10);
	  
	  while (board.fullyFlooded() == false) {
		  assertFalse(board.fullyFlooded());
		  WaterColor nextColor = board.suggest();
		  board.flood2(nextColor);
	  }
	
	  assertTrue(board.fullyFlooded());
  }
  
  
  // Testing suggest method
  @Test
  public void suggest() {
	  
  }

}