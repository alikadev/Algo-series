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
    // Fill up the set
    SetOfShorts set = new SetOfShorts();
    while (set.size() < m) {
      set.add((short)rnd.nextInt(n)); // Bound of `n` means from 0 (inclusive) to n (exclusive)
    }
    // Copy to result
    result = new short[set.size()];
    SetOfShortsItr it = set.iterator();
    for (int i = 0; it.hasMoreElements(); i++) {
      result[i] = it.nextElement();
    }
    Arrays.sort(result);
    return result;
  }
}
