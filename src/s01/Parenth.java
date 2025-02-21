package s01;
public class Parenth {
  public static boolean isBalanced(String l) {
    char c;
    CharStack s = new CharStack();
    for (int i=0; i<l.length(); i++) {
      c = l.charAt(i);
      if(isOpeningSign(c)){
        // If an opening bracket is found, push it onto the stack
        s.push(c);
      }else if(isClosingSign(c)){
        // If a closing bracket is found:
        // 1. Check if the stack is empty (no corresponding opening bracket)
        // 2. Check if the popped character from the stack matches the closing bracket
        if(s.isEmpty()|| !isMatchingPair(s.pop(),c)){
          return false;
          // Unmatched closing bracket or wrong pair
        }
      }
    }
    // The string is balanced only if the stack is empty (all brackets matched)
    return s.isEmpty();
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
