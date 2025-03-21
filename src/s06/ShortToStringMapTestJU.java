package s06;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import java.util.Random;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
// ------------------------------------------------------------
public class ShortToStringMapTestJU {
  static class S2SMap {
    TreeMap<Short,String> tr = new TreeMap<Short, String>();

    public S2SMap () {}
    public void    put(short key, String img) { tr.put(key, img);         }
    public String  get(short key)             { return tr.get(key);       }
    public void    remove(short e)            { tr.remove(e);             }
    public boolean containsKey(short k)       { return tr.containsKey(k); }
    public boolean isEmpty()                  { return size() == 0;      }
    public int     size()                     { return tr.size();         }
    public void    union(S2SMap m)            { tr.putAll(m.tr);           }

    public void intersection(S2SMap s) {
      S2SMap a = new S2SMap();
      for(short e:s.tr.keySet()) {
        if (containsKey(e)) a.put(e,s.get(e));
      }
      tr =a.tr;
    }
  }
  //============================================================

  static void rndAddRm(Random r, ShortToStringMap s, S2SMap g) {
    short i= (short)(r.nextInt(10_000));
    if (r.nextBoolean()) {
      String v = "" + r.nextInt(10);
      s.put(i,v);
      g.put(i,v);
    } else {
      s.remove(i);
      g.remove(i);
    }
  }
  // ------------------------------------------------------------
  static void checkSetAreEqual(ShortToStringMap s, S2SMap g) {
    int l = 0;
    short lastKey = 10;
    if (g.size() > 0) lastKey = g.tr.lastKey();
    for (short i = 0; i <= lastKey; i++) {
      assertEquals(g.containsKey(i), s.containsKey(i));
      if (g.containsKey(i)) {
        l++;
        assertEquals(g.get(i), s.get(i));
      }
    }
    assertEquals(g.size(), s.size());
    assertEquals(g.size(), l);
  }

  public static void testUnion(int nOperations, Random r) {
    int n = nOperations;
    while(n-- >0) {
      ShortToStringMap s1 = new ShortToStringMap();
      ShortToStringMap s2 = new ShortToStringMap();
      S2SMap bs1 = new S2SMap();
      S2SMap bs2 = new S2SMap();
      manyAddRm(s1, bs1, r, r.nextInt(nOperations));
      manyAddRm(s2, bs2, r, r.nextInt(nOperations));
      s1.union(s2);
      bs1.union(bs2);
      checkSetAreEqual(s1, bs1);
    }
  }

  public static void testIntersection(int nOperations, Random r) {
    int n=nOperations;
    while(n-- >0) {
      ShortToStringMap s1 = new ShortToStringMap();
      ShortToStringMap s2 = new ShortToStringMap();
      S2SMap bs1 = new S2SMap();
      S2SMap bs2 = new S2SMap();
      manyAddRm(s1, bs1, r, r.nextInt(nOperations));
      manyAddRm(s2, bs2, r, r.nextInt(nOperations));
      s1.intersection(s2);
      bs1.intersection(bs2);
      checkSetAreEqual(s1, bs1);
    }
  }

  private static void manyAddRm(ShortToStringMap s, S2SMap bs, Random r, int nOperations) {
    while(nOperations-- >0)
      rndAddRm(r, s, bs);
  }

  static void testAddRm(ShortToStringMap s, S2SMap bs, Random r, int nOperations) {
    int i = 0;
    for (i = 0; i < 10; i++) {
      checkSetAreEqual(s, bs);
      rndAddRm(r, s, bs);
    }
    while(nOperations-- >0) {
      rndAddRm(r, s, bs);
      checkSetAreEqual(s, bs);
    }
  }

  public static void testItr(ShortToStringMap s) {
    int x = 0;
    ShortToStringMap s2 = new ShortToStringMap();
    ShortToStringMapItr ai = s.iterator();
    short e = 0;
    while (ai.hasMoreKeys()) {
      e = ai.nextKey();
      x++;
      s2.put(e, "");
      assertTrue(s.containsKey(e));
    }
    assertEquals(s.size(), s2.size());
    assertEquals(s.size(), x);
  }

  final int testIntensity = 300;
  Random rnd = new Random(2209);
  ShortToStringMap set = new ShortToStringMap();
  S2SMap bs = new S2SMap();

  @BeforeEach
  void setup() {
    set = new ShortToStringMap();
    bs = new S2SMap();
  }

  @RepeatedTest(value=5)
  void testAddRemoveContains() {
    testAddRm(set, bs, rnd, testIntensity);
  }

  @RepeatedTest(value=5)
  void testWithIterator() {
    testAddRm(set, bs, rnd, testIntensity);
    testItr(set);
  }

  @RepeatedTest(value=5)
  void testUnion() {
    testUnion(testIntensity, rnd);
  }

  @RepeatedTest(value=5)
  void testIntersection() {
    testIntersection(testIntensity, rnd);
  }
}
