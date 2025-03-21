
package s06;

public class Ex1SetDemo {

  public static SetOfShorts z(SetOfShorts s, int x) {
    SetOfShorts res = new SetOfShorts();
    SetOfShortsItr itr = s.iterator();
    while (itr.hasMoreElements()) {
      short i = itr.nextElement();
      if (i<x) res.add(i);
    }
    return res;
  }

}
