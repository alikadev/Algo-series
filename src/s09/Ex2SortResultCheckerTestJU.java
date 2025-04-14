package s09;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static s09.Ex2SortResultChecker.isSortingResultCorrect;

public class Ex2SortResultCheckerTestJU {
  public record ArrayPair(int[] originalInput, int[] observedOutput) {
    @Override public String toString() {
      return Arrays.toString(originalInput) + " --> " + Arrays.toString(observedOutput);
    }
  }
  //===========================================
  @ParameterizedTest
  @MethodSource("trueInstances")
  public void shouldAcceptValidResult(ArrayPair p) {
    assertTrue(isSortingResultCorrect(p.originalInput(), p.observedOutput()));
  }

  @ParameterizedTest
  @MethodSource("falseInstances")
  public void shouldRejectInvalidResults(ArrayPair p) {
    assertFalse(isSortingResultCorrect(p.originalInput(), p.observedOutput()));
  }

  static ArrayPair[] trueInstances() {
    return new ArrayPair[] {
            new ArrayPair(new int[]{4,9,2}, new int[]{2,4,9})
            // TODO: add some more test cases
    };
  }

  static ArrayPair[] falseInstances() {
    return new ArrayPair[] {
            new ArrayPair(new int[]{4,9,2}, new int[]{2,4,4}),
            new ArrayPair(new int[]{4,9,2}, new int[]{4,9,2}),
            new ArrayPair(new int[]{4,9,2}, new int[]{2,2,4,4})
            // TODO: add some more test cases
    };
  }
}
