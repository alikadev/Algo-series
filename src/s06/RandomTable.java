package s06;

import java.util.Arrays;
import java.util.Random;

public class RandomTable {
  static Random rnd = new Random();
  // ------------------------------------------------------------
  // PRE: m <= n, m >= 0
  // Returns: a observedOutput array with randomly chosen distinct elements in [0..n-1]
  public static short[] randomTable(short m, short n) {
    short[] result = null;

    // TODO 
    // PSEUDOCODE : 
    //     create an empty set
    //     while the set has less than m elements
    //       add to the set a randomly chosen element  
    //     with an iterator, copy the entire set into an array
    //     sort the array
    Arrays.sort(result);
    return result;
  }
}
