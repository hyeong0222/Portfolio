import java.util.ArrayList;
import java.util.List;

/**
 * TODO #2
 */

public class MatchBot extends TwitterBot {
  /**
   * Constructs a MatchBot to operate on the last numTweets of the given user.
   */
  public MatchBot(String user, int numTweets) {
    super(user, numTweets);
  }
  
  /**
   * TODO
   * 
   * Employs the KMP string matching algorithm to add all tweets containing 
   * the given pattern to the provided list. Returns the total number of 
   * character comparisons performed.
   */
  public int searchTweetsKMP(String pattern, List<String> ans) {
    return 0;
  }
  
  /**
   * TODO
   * 
   * Employs the naive string matching algorithm to find all tweets containing 
   * the given pattern to the provided list. Returns the total number of 
   * character comparisons performed.
   */
  public int searchTweetsNaive(String pattern, List<String> ans) {
    return 0;
  }
  
  public static void main(String... args) {
    String handle = "realDonaldTrump", pattern = "mexico";
    MatchBot bot = new MatchBot(handle, 2000);
   
    // Search all tweets for the pattern.
    List<String> ansNaive = new ArrayList<>();
    int compsNaive = bot.searchTweetsNaive(pattern, ansNaive); 
    List<String> ansKMP = new ArrayList<>();
    int compsKMP = bot.searchTweetsKMP(pattern, ansKMP);  
    
    System.out.println("naive comps = " + compsNaive + ", KMP comps = " + compsKMP);

    for (int i = 0; i < ansKMP.size(); i++) {
      String tweet = ansKMP.get(i);
      assert tweet.equals(ansNaive.get(i));
      System.out.println(i++ + ". " + tweet);
      System.out.println(pattern + " appears at index " + 
          tweet.toLowerCase().indexOf(pattern.toLowerCase()));
    }
    
    // Do something similar for the Boyer-Moore matching algorithm.
  
  }
}
