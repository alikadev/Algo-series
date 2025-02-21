package s01;
public class Parenth {
  public static boolean isBalanced(String l) {
    char c;
    CharStack s = new CharStack();
    for (int i=0; i<l.length(); i++) {
      c = l.charAt(i);
      // TODO
    }
    return false; // TODO 
  }
  //-------------------------------------
  private static boolean isOpeningSign(char c) {
    return (c == '(') || (c == '{');
  }
  private static boolean isClosingSign(char c) {
    return (c == ')') || (c == '}');
  }
  private static boolean isMatchingPair(char c1, char c2) {
    return (  (c1=='(') && (c2==')'))
      ||   (  (c1=='{') && (c2=='}'));
  }
}
