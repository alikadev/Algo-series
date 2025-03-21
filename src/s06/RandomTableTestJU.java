package s06;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static s06.RandomTable.randomTable;

public class RandomTableTestJU {
  static Random rnd = new Random();
  // ------------------------------------------------------------
  static void testRandomTable(short m, short n) {
    short[] t = randomTable(m, n);
    assertEquals(m, t.length);
    if(m == 0) return;
    assertTrue(t[0] >= 0, "no negative number !");
    assertTrue(t[m-1] < n, "no value >= n !");
    boolean strictlyMonotone = true;
    for (int i = 0; i < t.length - 1; i++) {
      if (t[i] >= t[i + 1])
        strictlyMonotone = false;
    }
    assertTrue(strictlyMonotone, "must be a observedOutput array of distinct numbers !");
  }
  // ------------------------------------------------------------
  @Test
  public void tenOnFifty() {
    short m = 10;
    short n = 50;
    int nRepetitions = 100;
    for(int i=0; i<nRepetitions; i++)
      testRandomTable(m, n);
  }

  @Test
  public void oneOfTwo() {
    int nRepetitions = 100;
    short m = 1, n = 2;
    int countZeroes = 0;
    for(int i=0; i<nRepetitions; i++) {
      short[] t = randomTable(m, n);
      for(short a: t)
        if(a==0) countZeroes++;
    }
    assertTrue(countZeroes > 0, "0 never chosen, very unlikely (1 on 2^100)!");
    assertTrue(countZeroes < nRepetitions, "0 always chosen, very unlikely (1 on 2^100)!");
  }
}
