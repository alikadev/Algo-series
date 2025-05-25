package s13;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.BitSet;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SetOfStringsTestJU {
  static final String PREFIX = "";

  static String str(int i) {
    return PREFIX + i;
  }

  static boolean areSetEqual(SetOfStrings s, BitSet bs) {
    int l = 0;
    for (int i=0; i<bs.length(); i++) {
      if(bs.get(i) != s.contains(str(i))) {
        System.out.println("\nSetOf : " + s);
        System.out.println("BitSet: " + bs);
        System.out.println("Size: " + s.size());
        System.out.println("conflicting element : " + i);
        return false;
      }
      if (s.contains(str(i)))
        l++;
    }
    if (l != s.size()) {
      System.out.println("\nSetOf : " + s);
      System.out.println("BitSet: " + bs);
      System.out.println("Size: " + s.size());
      System.out.println("bad size...");
      return false;
    }
    return true;
  }

  public static void testUnion(int nOperations, Random r) {
    int n=nOperations;
    while(n-- >0) {
      SetOfStrings s1 = new SetOfStrings();
      SetOfStrings s2 = new SetOfStrings();
      BitSet bs1 = new BitSet();
      BitSet bs2 = new BitSet();
      manyAddRm(s1, bs1, r, r.nextInt(nOperations));
      manyAddRm(s2, bs2, r, r.nextInt(nOperations));
      s1.union(s2);
      bs1.or(bs2);
      assertTrue(areSetEqual(s1, bs1));
    }
  }

  public static void testIntersection(int nOperations, Random r) {
    int n=nOperations;
    while(n-- >0) {
      SetOfStrings s1 = new SetOfStrings();
      SetOfStrings s2 = new SetOfStrings();
      BitSet bs1 = new BitSet();
      BitSet bs2 = new BitSet();
      manyAddRm(s1, bs1, r, r.nextInt(nOperations));
      manyAddRm(s2, bs2, r, r.nextInt(nOperations));
      s1.intersection(s2);
      bs1.and(bs2);
      assertTrue(areSetEqual(s1, bs1));
    }
  }

  private static void manyAddRm(SetOfStrings s, BitSet bs, Random r, int nOperations) {
    while(nOperations-- >0)
      rndAddRm(r, s, bs);
  }

  public static void testAddRm(SetOfStrings s, BitSet bs, Random r, int nOperations) {
    int i = 0;
    for (i = 0; i < 10; i++) {
      if (!areSetEqual(s, bs))
        throw new RuntimeException("Error in add/remove/contains !");
      rndAddRm(r, s, bs);
    }
    while(nOperations-- >0) {
      rndAddRm(r, s, bs);
      if (!areSetEqual(s, bs))
        break;
    }
    assertTrue(areSetEqual(s, bs), "Error in add/remove/contains !");
  }

  public static void testItr(SetOfStrings s) {
    // ---- test itr
    int x = 0;
    SetOfStrings s2 = new SetOfStrings();
    SetOfStringsItr ai = s.iterator();
    String e;
    while (ai.hasMoreElements()) {
      e = ai.nextElement();
      x++;
      s2.add(e);
      assertTrue(s.contains(e), "Oops ! The iterator gives an absent elt");
    }
    assertEquals(s.size(), s2.size(), "Error in iterator...");
    assertEquals(s.size(), x, "Error in iterator...");
  }

  //------------------------------------------------------------
  // ------------------------------------------------------------
  final int testIntensity = 500;
  final int smallIntensity = 50;
  Random r;

  @BeforeEach
  void setup() {
    r = new Random(1610);
  }

  @Test
  @Order(0)
  void manyAddRemoveContains() {
    int intensity = testIntensity; // for the 1st phase
    for(int i=0; i<smallIntensity; i++) {
      SetOfStrings s = new SetOfStrings();
      BitSet bs = new BitSet();
      testAddRm(s, bs, r, intensity);
      intensity = smallIntensity; // for all but 1st phase
    }
  }

  @Test @Order(1)
  void testIterator() {
    int intensity = testIntensity; // for the 1st phase
    for(int i=0; i<smallIntensity; i++) {
      SetOfStrings s = new SetOfStrings();
      BitSet bs = new BitSet();
      testAddRm(s, bs, r, intensity);
      testItr(s);
      intensity = smallIntensity; // for all but 1st phase
    }
  }

  @Test @Order(2)
  void testUnion() {
    testUnion(testIntensity, r);
    for(int i=0; i<smallIntensity; i++)
      testUnion(smallIntensity, r);
  }

  @Test @Order(3)
  void testIntersection() {
    testIntersection(testIntensity, r);
    for(int i=0; i<smallIntensity; i++)
      testIntersection(smallIntensity, r);
  }

  static void rndAddRm(Random r, SetOfStrings s, BitSet bs) {
    int i=r.nextInt(1000);
    if (r.nextBoolean()) {
      s.add(""+i); 
      bs.set(i);
    } else {
      s.remove(""+i); 
      bs.clear(i);
    }
  }
}
