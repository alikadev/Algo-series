
package s06;

public class Ex1SetDemo {

  /// Returns a subset of s where values are lower than x
  /// @param s The set we'll get a subset of
  /// @param x The value the subset's values should be lower than
  /// @return The subset of s where the value are lower than x
  public static SetOfShorts z(SetOfShorts s, int x) {
    SetOfShorts res = new SetOfShorts();
    // Iterates through the superset
    SetOfShortsItr itr = s.iterator();
    while (itr.hasMoreElements()) {
      short i = itr.nextElement();
      // Only add a value in the subset if it's lower than x
      if (i<x) res.add(i);
    }
    return res;
  }

}
