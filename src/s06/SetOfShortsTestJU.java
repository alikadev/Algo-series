package s06;

import org.junit.jupiter.api.*;

import java.util.BitSet;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SetOfShortsTestJU {

  static void rndAddRm(Random r, SetOfShorts s, BitSet bs) {
    int i=r.nextInt(1000);
    if (r.nextBoolean()) {
      s.add( (short) i);
      bs.set(i);
    } else {
      s.remove( (short) i);
      bs.clear(i);
    }
  }

  static boolean areSetEqual(SetOfShorts s, BitSet bs) {
    int l = 0;
    for (int i=0; i<bs.length(); i++) {
      if(bs.get(i) != s.contains( (short) i)) {
        System.out.println("\nSetOf : " + s);
        System.out.println("BitSet: " + bs);
        System.out.println("Size: " + s.size());
        System.out.println("conflicting element : " + i);
        return false;
      }
      if (s.contains( (short) i))
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
      SetOfShorts s1 = new SetOfShorts();
      SetOfShorts s2 = new SetOfShorts();
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
      SetOfShorts s1 = new SetOfShorts();
      SetOfShorts s2 = new SetOfShorts();
      BitSet bs1 = new BitSet();
      BitSet bs2 = new BitSet();
      manyAddRm(s1, bs1, r, r.nextInt(nOperations));
      manyAddRm(s2, bs2, r, r.nextInt(nOperations));
      s1.intersection(s2);
      bs1.and(bs2);
      assertTrue(areSetEqual(s1, bs1));
    }
  }
  
  private static void manyAddRm(SetOfShorts s, BitSet bs, Random r, int nOperations) {
    while(nOperations-- >0)
      rndAddRm(r, s, bs);
  }
  
  public static void testAddRm(SetOfShorts s, BitSet bs, Random r, int nOperations) {
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
  
  public static void testItr(SetOfShorts s) {
    // ---- test itr
    int x = 0;
    SetOfShorts s2 = new SetOfShorts();
    SetOfShortsItr ai = s.iterator();
    short e = 0;
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
  final int smallIntensity = 20;
  Random r;

  @BeforeEach
  void setup() {
    r = new Random(1610);
  }

  @Test @Order(0)
  void manyAddRemoveContains() {
    int intensity = testIntensity; // for the 1st phase
    for(int i=0; i<smallIntensity; i++) {
      SetOfShorts s = new SetOfShorts();
      BitSet bs = new BitSet();
      testAddRm(s, bs, r, intensity);
      intensity = smallIntensity; // for all but 1st phase
    }
  }

  @Test @Order(1)
  void testIterator() {
    int intensity = testIntensity; // for the 1st phase
    for(int i=0; i<smallIntensity; i++) {
      SetOfShorts s = new SetOfShorts();
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
}
