/**
 * To test with JUnit, add JUnit to your project. To do this, go to
 * Project->Properties. Select "Java Build Path". Select the "Libraries"
 * tab and "Add Library". Select JUnit, then JUnit 4.
 */

import static org.junit.Assert.*;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Testing {

  @Test
  public void defaultJudgeTest() {
    Judge judge = new Judge();
    assertEquals(2, judge.score('A',  'A'));
    assertEquals(-2, judge.score('A',  'C'));
    assertEquals(-1, judge.score('A',  '_'));
    assertEquals(-1, judge.score('_',  'A'));
    assertEquals(-1, judge.score('_',  '_'));
    assertEquals(2, judge.score("A",  "A"));
    assertEquals(-2, judge.score("A",  "C"));
    assertEquals(-1, judge.score("A",  "_"));
    assertEquals(-1, judge.score("_",  "A"));
    assertEquals(-1, judge.score("_",  "_"));
    assertEquals(8, judge.score("ACTG",  "ACTG"));
    assertEquals(-8, judge.score("ACTG",  "CTGA"));
    assertEquals(-4, judge.score("AAAA",  "____"));
    assertEquals(-4, judge.score("____",  "AAAA"));
    assertEquals(-4, judge.score("____",  "____"));
    assertEquals(2 + -1 + -1 + -2, judge.score("AC_G",  "A_TA"));
  }
  
  @Test
  public void defaultJudgeTest2() {
    Judge judge = new Judge();
    assertEquals(2, judge.score('C',  'C'));
    assertEquals(-2, judge.score('C', 'b'));
    assertEquals(-1, judge.score("_",  "_"));
    assertEquals(8, judge.score("ABCD",  "ABCD"));
    assertEquals(-8, judge.score("ABCD",  "DCBA"));
    assertEquals(-2, judge.score("__",  "__"));
  }
  
  @Test
  public void customJudgeTest() {
    Judge judge = new Judge(3, -3, -2);
    assertEquals(3, judge.score('A',  'A'));
    assertEquals(-3, judge.score('A',  'C'));
    assertEquals(-2, judge.score('A',  '_'));
    assertEquals(-2, judge.score('_',  'A'));
    assertEquals(-2, judge.score('_',  '_'));
    assertEquals(3, judge.score("A",  "A"));
    assertEquals(-3, judge.score("A",  "C"));
    assertEquals(-2, judge.score("A",  "_"));
    assertEquals(-2, judge.score("_",  "A"));
    assertEquals(-2, judge.score("_",  "_"));
    assertEquals(12, judge.score("ACTG",  "ACTG"));
    assertEquals(-12, judge.score("ACTG",  "CTGA"));
    assertEquals(-8, judge.score("AAAA",  "____"));
    assertEquals(-8, judge.score("____",  "AAAA"));
    assertEquals(-8, judge.score("____",  "____"));
    assertEquals(3 + -2 + -2 + -3, judge.score("AC_G",  "A_TA"));
  }
  
  @Test
  public void customJudgeTest2() {
    Judge judge = new Judge(5, -5, -2);
    assertEquals(5, judge.score('C',  'C'));
    assertEquals(-5, judge.score('C', 'b'));
    assertEquals(-2, judge.score("_",  "_"));
    assertEquals(20, judge.score("ABCD",  "ABCD"));
    assertEquals(-20, judge.score("ABCD",  "DCBA"));
    assertEquals(-4, judge.score("__",  "__"));
  }

  @Test
  public void testBestResult() {
    Result best;
    for (int x = 1; x <= 3; x++)
      for (int y = x + 1; y <= 3; y++)
        for (int z = y + 1; z <= 3; z++) {
          best = SequenceAligner.bestResult(x, y, z);
          assertEquals(3, best.getScore());
          if (x == 3)
            assertEquals(Direction.DIAGONAL, best.getParent());
          else if (y == 3)
            assertEquals(Direction.LEFT, best.getParent());
          else
            assertEquals(Direction.UP, best.getParent());
        }
    // Test tiebreaker rule
    best = SequenceAligner.bestResult(1, 1, 1);
    assertEquals(1, best.getScore());
    assertEquals(Direction.DIAGONAL, best.getParent());
    best = SequenceAligner.bestResult(2, 2, 1);
    assertEquals(2, best.getScore());
    assertEquals(Direction.DIAGONAL, best.getParent());
    best = SequenceAligner.bestResult(2, 1, 2);
    assertEquals(2, best.getScore());
    assertEquals(Direction.DIAGONAL, best.getParent());
    best = SequenceAligner.bestResult(1, 2, 2);
    assertEquals(2, best.getScore());
    assertEquals(Direction.LEFT, best.getParent());
  }
  
  @Test
  public void testBestResult2() {
    Result best;
    for (int x = 1; x <= 5; x++)
      for (int y = x + 1; y <= 5; y++)
        for (int z = y + 1; z <= 5; z++) {
          best = SequenceAligner.bestResult(x, y, z);
          if (x == 5)
            assertEquals(Direction.DIAGONAL, best.getParent());
          else if (y == 5)
            assertEquals(Direction.LEFT, best.getParent());
          else
            assertEquals(Direction.UP, best.getParent());
        }
    // Test tiebreaker rule
    best = SequenceAligner.bestResult(2, 2, 2);
    assertEquals(2, best.getScore());
    assertEquals(Direction.DIAGONAL, best.getParent());
    best = SequenceAligner.bestResult(2, 2, 1);
    assertEquals(2, best.getScore());
    assertEquals(Direction.DIAGONAL, best.getParent());
  }

  /**********************************************
   * Testing SequenceAligner.fillCache()
   **********************************************/
    
  @Test
  public void empties() {
    SequenceAligner sa;
    Result result;
    sa = new SequenceAligner("", "");
    result = sa.getResult(0, 0);
    assertNotNull(result);
    assertEquals(0, result.getScore());
    assertEquals(Direction.NONE, result.getParent());
  }
  
  
  
  @Test
  public void singletons() {
    SequenceAligner sa;
    Result result;
    sa = new SequenceAligner("A", "A");
    result = sa.getResult(0, 0);
    assertNotNull(result);
    assertEquals(0, result.getScore());
    assertEquals(Direction.NONE, result.getParent());
    result = sa.getResult(0, 1);
    assertNotNull(result);
    assertEquals(-1, result.getScore());
    assertEquals(Direction.LEFT, result.getParent());
    result = sa.getResult(1, 0);
    assertNotNull(result);
    assertEquals(-1, result.getScore());
    assertEquals(Direction.UP, result.getParent());
    result = sa.getResult(1, 1);
    assertNotNull(result);
    assertEquals(2, result.getScore());
    assertEquals(Direction.DIAGONAL, result.getParent());
    sa = new SequenceAligner("A", "C");
    result = sa.getResult(0, 0);
    assertNotNull(result);
    assertEquals(0, result.getScore());
    assertEquals(Direction.NONE, result.getParent());
    result = sa.getResult(0, 1);
    assertNotNull(result);
    assertEquals(-1, result.getScore());
    assertEquals(Direction.LEFT, result.getParent());
    result = sa.getResult(1, 0);
    assertNotNull(result);
    assertEquals(-1, result.getScore());
    assertEquals(Direction.UP, result.getParent());
    result = sa.getResult(1, 1);
    assertNotNull(result);
    assertEquals(-2, result.getScore());
    assertEquals(Direction.DIAGONAL, result.getParent());
  }
  
  
  @Test
  public void singletons2() {
    SequenceAligner sa;
    Result result;
    sa = new SequenceAligner("B", "C");
    result = sa.getResult(0, 0);
    assertNotNull(result);
    assertEquals(0, result.getScore());
    assertEquals(Direction.NONE, result.getParent());
    result = sa.getResult(0, 1);
    assertNotNull(result);
    assertEquals(-1, result.getScore());
    assertEquals(Direction.LEFT, result.getParent());
  }
  
  
  @Test
  public void singletonsWithCustomJudge() {
    Judge judge;
    SequenceAligner sa;
    Result result;
    judge = new Judge(3, -3, -2);
    sa = new SequenceAligner("A", "A", judge);
    result = sa.getResult(0, 0);
    assertNotNull(result);
    assertEquals(0, result.getScore());
    assertEquals(Direction.NONE, result.getParent());
    result = sa.getResult(0, 1);
    assertNotNull(result);
    assertEquals(-2, result.getScore());
    assertEquals(Direction.LEFT, result.getParent());
    result = sa.getResult(1, 0);
    assertNotNull(result);
    assertEquals(-2, result.getScore());
    assertEquals(Direction.UP, result.getParent());
    result = sa.getResult(1, 1);
    assertNotNull(result);
    assertEquals(3, result.getScore());
    assertEquals(Direction.DIAGONAL, result.getParent());  
    judge = new Judge(3, -3, -1);
    sa = new SequenceAligner("A", "C", judge);
    result = sa.getResult(0, 0);
    assertNotNull(result);
    assertEquals(0, result.getScore());
    assertEquals(Direction.NONE, result.getParent());
    result = sa.getResult(0, 1);
    assertNotNull(result);
    assertEquals(-1, result.getScore());
    assertEquals(Direction.LEFT, result.getParent());
    result = sa.getResult(1, 0);
    assertNotNull(result);
    assertEquals(-1, result.getScore());
    assertEquals(Direction.UP, result.getParent());
    result = sa.getResult(1, 1);
    assertNotNull(result);
    assertEquals(-2, result.getScore());
    assertEquals(Direction.LEFT, result.getParent());
  }
  
  @Test
  public void doubletons() {
    SequenceAligner sa;
    Result result;
    sa = new SequenceAligner("AG", "AG");
    assertEquals(-2, sa.getResult(0, 2).getScore());
    assertEquals(-2, sa.getResult(2, 0).getScore());
    assertEquals(Direction.LEFT, sa.getResult(0, 2).getParent());
    assertEquals(Direction.UP, sa.getResult(2, 0).getParent());
    result = sa.getResult(1, 1);
    assertEquals(2, result.getScore());
    assertEquals(Direction.DIAGONAL, result.getParent());
    result = sa.getResult(1, 2);
    assertEquals(1, result.getScore());
    assertEquals(Direction.LEFT, result.getParent());
    result = sa.getResult(2, 1);
    assertEquals(1, result.getScore());
    assertEquals(Direction.UP, result.getParent());
    result = sa.getResult(2, 2);
    assertEquals(4, result.getScore());
    assertEquals(Direction.DIAGONAL, result.getParent());
    sa = new SequenceAligner("AG", "GA");
    assertEquals(-2, sa.getResult(0, 2).getScore());
    assertEquals(-2, sa.getResult(2, 0).getScore());
    assertEquals(Direction.LEFT, sa.getResult(0, 2).getParent());
    assertEquals(Direction.UP, sa.getResult(2, 0).getParent());
    result = sa.getResult(1, 1);
    assertEquals(-2, result.getScore());
    assertEquals(Direction.DIAGONAL, result.getParent());
    result = sa.getResult(1, 2);
    assertEquals(1, result.getScore());
    assertEquals(Direction.DIAGONAL, result.getParent());
    result = sa.getResult(2, 1);
    assertEquals(1, result.getScore());
    assertEquals(Direction.DIAGONAL, result.getParent());
    result = sa.getResult(2, 2);
    assertEquals(0, result.getScore());
    assertEquals(Direction.LEFT, result.getParent());
  }
  
  
  public void doubletons2() {
    SequenceAligner sa;
    Result result;
    sa = new SequenceAligner("XY", "YX");
    result = sa.getResult(1, 1);
    assertEquals(2, result.getScore());
    assertEquals(Direction.DIAGONAL, result.getParent());
    result = sa.getResult(2, 2);
    assertEquals(4, result.getScore());
    assertEquals(Direction.DIAGONAL, result.getParent());
  }
  
  
  @Test
  public void oneTwos() {
    SequenceAligner sa;
    Result result;
    sa = new SequenceAligner("A", "AG");
    assertEquals(-2, sa.getResult(0, 2).getScore());
    assertEquals(Direction.LEFT, sa.getResult(0, 2).getParent());
    result = sa.getResult(1, 1);
    assertEquals(2, result.getScore());
    assertEquals(Direction.DIAGONAL, result.getParent());
    result = sa.getResult(1, 2);
    assertEquals(1, result.getScore());
    assertEquals(Direction.LEFT, result.getParent());
    sa = new SequenceAligner("AG", "G");
    assertEquals(-2, sa.getResult(2, 0).getScore());
    assertEquals(Direction.UP, sa.getResult(2, 0).getParent());
    result = sa.getResult(1, 1);
    assertEquals(-2, result.getScore());
    assertEquals(Direction.DIAGONAL, result.getParent());
    result = sa.getResult(2, 1);
    assertEquals(1, result.getScore());
    assertEquals(Direction.DIAGONAL, result.getParent());
  }
  
  
  @Test
  public void oneTwos2() {
    SequenceAligner sa;
    Result result;
    sa = new SequenceAligner("A", "AB");
    assertEquals(-2, sa.getResult(0, 2).getScore());
    result = sa.getResult(1, 1);
    assertEquals(2, result.getScore());
    result = sa.getResult(1, 2);
    assertEquals(1, result.getScore());
  }
  
  /**********************************************
   * Try running the Driver to see what happens.
   **********************************************/
  
  @Test
  public void bigBases() {
    SequenceAligner sa;
    sa = new SequenceAligner(1000);
    for (int i = 0; i < sa.getX().length() + 1; i++)
      assertEquals(-i, sa.getResult(i, 0).getScore());
    for (int j = 0; j < sa.getY().length() + 1; j++)
      assertEquals(-j, sa.getResult(0, j).getScore());
    sa = new SequenceAligner("AAAAAAAAAA", "CCCCCCCCCCCC", new Judge(4, 5, 6));
    for (int i = 0; i < sa.getX().length() + 1; i++)
      assertEquals(6 * i, sa.getResult(i, 0).getScore());
    for (int j = 0; j < sa.getY().length() + 1; j++)
      assertEquals(6 * j, sa.getResult(0, j).getScore());
  }
  
  /**********************************************
   * Testing SequenceAligner.traceback()
   **********************************************/
  
  @Test
  public void simpleAlignment() {
    SequenceAligner sa;
    sa = new SequenceAligner("ACGT", "ACGT");
    assertTrue(sa.isAligned());
    assertEquals("ACGT", sa.getAlignedX());
    assertEquals("ACGT", sa.getAlignedY());
    sa = new SequenceAligner("ACT", "ACGT");
    assertTrue(sa.isAligned());
    assertEquals("AC_T", sa.getAlignedX());
    assertEquals("ACGT", sa.getAlignedY());
    sa = new SequenceAligner("ACGT", "CGT");
    assertTrue(sa.isAligned());
    assertEquals("ACGT", sa.getAlignedX());
    assertEquals("_CGT", sa.getAlignedY());
    sa = new SequenceAligner("ACGT", "AGT");
    assertTrue(sa.isAligned());
    assertEquals("ACGT", sa.getAlignedX());
    assertEquals("A_GT", sa.getAlignedY());
    sa = new SequenceAligner("ACGT", "ACT");
    assertTrue(sa.isAligned());
    assertEquals("ACGT", sa.getAlignedX());
    assertEquals("AC_T", sa.getAlignedY());
    sa = new SequenceAligner("ACGT", "ACG");
    assertTrue(sa.isAligned());
    assertEquals("ACGT", sa.getAlignedX());
    assertEquals("ACG_", sa.getAlignedY());
    sa = new SequenceAligner("ACGT", "AC");
    assertTrue(sa.isAligned());
    assertEquals("AC__", sa.getAlignedY());
    sa = new SequenceAligner("ACGT", "CG");
    assertTrue(sa.isAligned());
    assertEquals("_CG_", sa.getAlignedY());
    sa = new SequenceAligner("ACGT", "GT");
    assertTrue(sa.isAligned());
    assertEquals("__GT", sa.getAlignedY());
    sa = new SequenceAligner("A", "ACGT");
    assertEquals("A___", sa.getAlignedX());
    sa = new SequenceAligner("C", "ACGT");
    assertEquals("_C__", sa.getAlignedX());
    sa = new SequenceAligner("G", "ACGT");
    assertEquals("__G_", sa.getAlignedX());
    sa = new SequenceAligner("T", "ACGT");
    assertEquals("___T", sa.getAlignedX());
    sa = new SequenceAligner("AA", "A");
    assertEquals("_A", sa.getAlignedY());
    System.out.println(sa);
    sa = new SequenceAligner("AACCGGTT", "ACGT");
    assertEquals("_A_C_G_T", sa.getAlignedY());
    sa = new SequenceAligner("AAGGTT", "AACCGG");
    assertEquals("AA__GGTT", sa.getAlignedX());
    assertEquals("AACCGG__", sa.getAlignedY());
  }
  
  
  @Test
  public void simpleAlignment2() {
    SequenceAligner sa;
    sa = new SequenceAligner("ABCD", "ABCD");
    assertTrue(sa.isAligned());
    assertEquals("ABCD", sa.getAlignedX());
    assertEquals("ABCD", sa.getAlignedY());
    sa = new SequenceAligner("ABCD", "DCBA");
    assertTrue(sa.isAligned());
    assertEquals("ABC_D", sa.getAlignedX());
    assertEquals("_DCBA", sa.getAlignedY());
  }
  
  @Test
  public void pathMarks() {
    SequenceAligner sa;
    sa = new SequenceAligner("AGACG", "CCGCT");
    assertEquals("_AGACG", sa.getAlignedX());
    assertEquals("CCG_CT", sa.getAlignedY());
    // check that start and end are on the path
    assertTrue(sa.getResult(0, 0).onPath());
    assertTrue(sa.getResult(5, 5).onPath());
    int[][] expectedScores = {
        {  0, -1, -2, -3, -4, -5 },
        { -1, -2, -3, -4, -5, -6 },
        { -2, -3, -4, -1, -2, -3 },
        { -3, -4, -5, -2, -3, -4 },
        { -4, -1, -2, -3,  0, -1 },
        { -5, -2, -3,  0, -1, -2 },
    };
    for (int i = 0; i < 6; i++)
      for (int j = 0; j < 6; j++) 
        assertEquals(expectedScores[i][j], 
            sa.getResult(i, j).getScore());
    // expected coords on optimal path
    int[] is = { 0, 0, 1, 2, 3, 4, 5 };
    int[] js = { 0, 1, 2, 3, 3, 4, 5 };
    int k = 0;
    for (int i = 0; i < 6; i++)
      for (int j = 0; j < 6; j++) 
        if (i == is[k] && j == js[k]) {
          assertTrue(sa.getResult(i, j).onPath());
          k++;
        }
        else 
          assertFalse(sa.getResult(i, j).onPath());
  }
  
  /**********************************************
   * Benchmarks
   **********************************************/

  @Test
  public void runBenchmarks() {
    System.out.println("Running Test Suite:\n");
    String filename = Constants.SUITE_FILENAME;
    SequenceAligner strands;
    try {
      FileReader reader = new FileReader(filename);
      BufferedReader in = new BufferedReader(reader);
      String testCase;
      int testNo = 1;
      while ((testCase = in.readLine()) != null) {     
        System.out.println("Test " + testNo++ + ":");
        String[] field = testCase.split(",");
        String X = field[0], Y = field[1];
        System.out.println("X = " + X + "\nY = " + Y + "\n");
        strands = new SequenceAligner(X, Y);
        int expectedScore = Integer.parseInt(field[2]);
        System.out.println(strands + " (expecting " + expectedScore + ")\n"); 
        assert strands.getScore() == expectedScore;
        assert strands.getAlignedX().replaceAll("_", "").equals(strands.getX());
        assert strands.getAlignedY().replaceAll("_", "").equals(strands.getY());
        System.out.println("------------------\n");
      }
      in.close();
      System.out.println("All tests passed...\n");
    }
    catch (FileNotFoundException e) {
      System.out.println("The test suite file, " + filename + ", is not found.\nYou probably deleted it by mistake.");
    }
    catch (IOException e) {
      e.printStackTrace(System.out);
    }   
  }
}