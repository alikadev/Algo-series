package s07;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Ex1AboutMinTestJU {

  @FunctionalInterface
  interface MinFunction {
    int min(int[] t);
  }
  
  static void checkTestCase(int[] t, MinFunction mf) {
    int[] t1 = Arrays.copyOf(t, t.length);
    int observed = mf.min(t1);
    assertArrayEquals(t, t1, "The input array is modified!");
    int expected = Arrays.stream(t).min().getAsInt();
    assertEquals(expected, observed);
  }

  static int[][] samples() {
    int[][] samples = {
            {3, 4, 5},
            {5, 4, 3},
            {4, 3, 5},
            {-1, -9},
            {-9, -1},
            {8, 6, 5, 3, 3},
            {3, 5, 4, 3, 8},
            {8}
    };
    return samples;
  }

  @ParameterizedTest
  @MethodSource("samples")
  void minOneRecursiveCallTinyTest(int[] u) {
      checkTestCase(u, Ex1AboutMinimum::min1);
  }

  @ParameterizedTest
  @MethodSource("samples")
  void minTwoRecursiveCallsTinyTest(int[] u) {
    checkTestCase(u, Ex1AboutMinimum::min2);
  }
}
