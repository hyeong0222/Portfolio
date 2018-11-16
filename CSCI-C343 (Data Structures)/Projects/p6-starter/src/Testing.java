import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

public class Testing {
  private static Random random = new Random();
  private static String alphabet = "abcdefghijklmnopqrstuvwxyz0123456789";

  @Test 
  public void testEmpty() {
    System.out.println("testEmpty");
    match("", "");
    match("", "ab");
    System.out.println();
  }

  @Test 
  public void testOneChar() {
    System.out.println("testOneChar");
    match("a", "a");
    match("a", "b");
    System.out.println();
  }

  @Test 
  public void testRepeat() {
    System.out.println("testRepeat");
    match("aaa", "aaaaa");
    match("aaa", "abaaba");
    match("abab", "abacababc");
    match("abab", "babacaba");
    System.out.println();
  }

  @Test 
  public void testPartialRepeat() {
    System.out.println("testPartialRepeat");
    match("aaacaaaaac", "aaacacaacaaacaaaacaaaaac");
    match("ababcababdabababcababdaba", "ababcababdabababcababdaba");
    System.out.println();
  }

  @Test 
  public void testRandomly() {
    System.out.println("testRandomly");
    for (int i = 0; i < 100; i++) {
      String pattern = makeRandomPattern();
      for (int j = 0; j < 100; j++) {
        String text = makeRandomText(pattern);
        match(pattern, text);
      }
    }
    System.out.println();
  }

  /* Helper functions */

  private static String makeRandomPattern() {
    StringBuilder sb = new StringBuilder();
    int steps = random.nextInt(10) + 1;
    for (int i = 0; i < steps; i++) {
      if (sb.length() == 0 || random.nextBoolean()) {  // Add literal
        int len = random.nextInt(5) + 1;
        for (int j = 0; j < len; j++)
          sb.append(alphabet.charAt(random.nextInt(alphabet.length())));
      } 
      else {  // Repeat prefix
        int len = random.nextInt(sb.length()) + 1;
        int reps = random.nextInt(3) + 1;
        if (sb.length() + len * reps > 1000)
          break;
        for (int j = 0; j < reps; j++)
          sb.append(sb.substring(0, len));
      }
    }
    return sb.toString();
  }

  private static String makeRandomText(String pattern) {
    StringBuilder sb = new StringBuilder();
    int steps = random.nextInt(100);
    for (int i = 0; i < steps && sb.length() < 10000; i++) {
      if (random.nextDouble() < 0.7) {  // Add prefix of pattern
        int len = random.nextInt(pattern.length()) + 1;
        sb.append(pattern.substring(0, len));
      } 
      else {  // Add literal
        int len = random.nextInt(30) + 1;
        for (int j = 0; j < len; j++)
          sb.append(alphabet.charAt(random.nextInt(alphabet.length())));
      }
    }
    return sb.toString();
  }

  private static void match(String pattern, String text) {
    // run all three algorithms and test for correctness
    Result ansNaive = StringMatch.matchNaive(pattern, text);
    int expected = text.indexOf(pattern);
    assertEquals(expected, ansNaive.pos);
    Result ansKMP = StringMatch.matchKMP(pattern, text);
    assertEquals(expected, ansKMP.pos);
    Result ansBoyerMoore = StringMatch.matchBoyerMoore(pattern, text);
    assertEquals(expected, ansBoyerMoore.pos);
    System.out.println(String.format("%5d %5d %5d : %s", 
        ansNaive.comps, ansKMP.comps, ansBoyerMoore.comps,
        (ansNaive.comps < ansKMP.comps && ansNaive.comps < ansBoyerMoore.comps) ?
            "Naive" :
              (ansKMP.comps < ansNaive.comps && ansKMP.comps < ansBoyerMoore.comps) ?
                  "KMP" : "Boyer-Moore"));
  }
}
