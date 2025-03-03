package s03;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static s03.AmStramGram.winnerAmStramGram;

public class AmStramGramTestJU {

  @Test
  void josephusTest() {
    int[][] sol = {
        {1, 1, 1, 1, 1, 1, 1},  // n=1
        {2, 1, 2, 1, 2, 1, 2},  // n=2
        {3, 3, 2, 2, 1, 1, 3},  // n=3
        {4, 1, 1, 2, 2, 3, 2},  // n=4
        {5, 3, 4, 1, 2, 4, 4},  // n=5
        {6, 5, 1, 5, 1, 4, 5},  // n=6
        {7, 7, 4, 2, 6, 3, 5}   // n=7
    //   1  2  3  4  5  6  7 <--- k
    };
    for(int n=1; n<8; n++)
      for(int k=1; k<8; k++) {
        int r = winnerAmStramGram(n, k);
        int expected = sol[n-1][k-1];
        String msg = String.format("mismatch: n=%d, k=%d, expected=%d, observed=%d%n",
                n, k, expected, r);
        assertEquals(expected, r, msg);
    }
  }
}
