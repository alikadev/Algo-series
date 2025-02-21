package s01;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static s01.Parenth.isBalanced;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParenthTestJU {

  @ParameterizedTest
  @MethodSource("balancedInstances")
  public void shouldAcceptBalancedSample(String s) {
    assertTrue(isBalanced(s));
  }

  @ParameterizedTest
  @MethodSource("unbalancedInstances")
  public void shouldRejectUnbalancedSample(String s) {
    assertFalse(isBalanced(s));
  }

  static String[] balancedInstances() {
    return new String[]{
            "((o{()oo})o)",
            "oo((((((((((((((((((((o))))))))))))))))))))o{}"
    };
  }

  static String[] unbalancedInstances() {
    return new String[]{
            "oo())(()",
            "oo()((())()",
            "oo()((()})",
            ")()",
            "((}))",
            "(("
    };
  }

}