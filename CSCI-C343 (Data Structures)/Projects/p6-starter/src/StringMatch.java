/**
 * TODO #1
 */

public class StringMatch {

  /**
   * TODO
   * 
   * Returns the result of running the naive algorithm to match pattern in text.
   */
  public static Result matchNaive(String pattern, String text) {  
    return new Result(-1, 0);
  }
  
  /**
   * TODO
   * 
   * Populates flink with the failure links for the KMP machine associated with the
   * given pattern, and returns the cost in terms of the number of character comparisons.
   */
  public static int buildKMP(String pattern, int[] flink) {
    return 0;
  }
  
  /**
   * TODO
   * 
   * Returns the result of running the KMP machine specified by flink (built for the
   * given pattern) on the text.
   */
  public static Result runKMP(String pattern, String text, int[] flink) {
    return new Result(-1, 0);   
  }
  
  /**
   * Returns the result of running the KMP algorithm to match pattern in text. The number
   * of comparisons includes the cost of building the machine from the pattern.
   */
  public static Result matchKMP(String pattern, String text) {
    int m = pattern.length();
    int[] flink = new int[m + 1];
    int comps = buildKMP(pattern, flink);
    Result ans = runKMP(pattern, text, flink);
    return new Result(ans.pos, comps + ans.comps);
  }
  
  /**
   * TODO
   * 
   * Populates delta1 with the shift values associated with each character in the
   * alphabet. Assume delta1 is large enough to hold any ASCII value.
   */
  public static void buildDelta1(String pattern, int[] delta1) {
    
  }

  /**
   * TODO
   * 
   * Returns the result of running the simplified Boyer-Moore algorithm using the
   * delta1 table from the pre-processing phase.
   */
  public static Result runBoyerMoore(String pattern, String text, int[] delta1) {
    return new Result(-1, 0);
  }
  
  /**
   * Returns the result of running the simplified Boyer-Moore algorithm to match 
   * pattern in text. 
   */
  public static Result matchBoyerMoore(String pattern, String text) {
    int[] delta1 = new int[Constants.SIGMA_SIZE];
    buildDelta1(pattern, delta1);
    return runBoyerMoore(pattern, text, delta1);
  }

}
