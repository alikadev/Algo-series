package s11;

import org.junit.jupiter.api.*;

import java.util.BitSet;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//TODO: maybe test the "compactness" of the structure...

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MyBitSetTestJU {

  // ------------------------------------------------------------
  static void rndAddRm(Random r, MyBitSet s, BitSet bs) {
    int i = r.nextInt(1000);
    if (r.nextBoolean())
      assertEquals(bs.get(i), s.get(i));
    if (r.nextBoolean()) {
      s.set(i);
      bs.set(i);
    } else {
      s.clear((short) i);
      bs.clear(i);
    }
  }

  public static void testUnion(int nOperations, Random r) {
    int n = nOperations;
    while (n-- > 0) {
      MyBitSet s1 = new MyBitSet();
      MyBitSet s2 = new MyBitSet();
      BitSet bs1 = new BitSet();
      BitSet bs2 = new BitSet();
      manyAddRm(s1, bs1, r, r.nextInt(nOperations));
      manyAddRm(s2, bs2, r, r.nextInt(nOperations));
      s1.or(s2);
      bs1.or(bs2);
      assertTrue(areSetEqual(s1, bs1));
    }
  }

  public static void testIntersection(int nOperations, Random r) {
    int n = nOperations;
    while (n-- > 0) {
      MyBitSet s1 = new MyBitSet();
      MyBitSet s2 = new MyBitSet();
      BitSet bs1 = new BitSet();
      BitSet bs2 = new BitSet();
      manyAddRm(s1, bs1, r, r.nextInt(nOperations));
      manyAddRm(s2, bs2, r, r.nextInt(nOperations));
      s1.and(s2);
      bs1.and(bs2);
      assertTrue(areSetEqual(s1, bs1));
    }
  }

  public static void testXor(int nOperations, Random r) {
    int n = nOperations;
    while (n-- > 0) {
      MyBitSet s1 = new MyBitSet();
      MyBitSet s2 = new MyBitSet();
      BitSet bs1 = new BitSet();
      BitSet bs2 = new BitSet();
      manyAddRm(s1, bs1, r, r.nextInt(nOperations));
      manyAddRm(s2, bs2, r, r.nextInt(nOperations));
      s1.xor(s2);
      bs1.xor(bs2);
      assertTrue(areSetEqual(s1, bs1));
    }
  }

  public static void testSpecialSelfAndOrXor(int nOperations, Random r) {
    int n = nOperations;
    while (n-- > 0) {
      MyBitSet s1 = new MyBitSet();
      BitSet bs1 = new BitSet();
      manyAddRm(s1, bs1, r, r.nextInt(nOperations));
      s1.and(s1);
      if (!areSetEqual(s1, bs1))
        throw new RuntimeException("Error in a.and(a)!");
      s1.or(s1);
      if (!areSetEqual(s1, bs1))
        throw new RuntimeException("Error in a.or(a) !");
      s1.xor(s1);
      if (s1.cardinality() != 0)
        throw new RuntimeException("Error in a.xor(a) !");
    }
  }

  private static void manyAddRm(MyBitSet s, BitSet bs, Random r, int nOperations) {
    while (nOperations-- > 0)
      rndAddRm(r, s, bs);
  }

  // ------------------------------------------------------------
  final int testIntensity = 500;
  final int smallIntensity = 20;
  Random rnd;

  @BeforeEach
  void setup() {
    rnd = new Random(1610);
  }

  @Test
  @Order(0)
  void manyAddRemoveContains() {
    int intensity = testIntensity; // for the 1st phase
    for (int i = 0; i < smallIntensity; i++) {
      MyBitSet s = new MyBitSet();
      BitSet bs = new BitSet();
      testAddRm(s, bs, rnd, intensity);
      intensity = smallIntensity; // for all but 1st phase
    }
  }

  @Test @Order(2)
  void testUnion() {
    testUnion(testIntensity, rnd);
    for (int i = 0; i < smallIntensity; i++)
      testUnion(smallIntensity, rnd);
  }

  @Test @Order(3)
  void testIntersection() {
    testIntersection(testIntensity, rnd);
    for (int i = 0; i < smallIntensity; i++)
      testIntersection(smallIntensity, rnd);
  }

  @Test @Order(4)
  void testXor() {
    testXor(testIntensity, rnd);
    for (int i = 0; i < smallIntensity; i++)
      testXor(smallIntensity, rnd);
  }

  @Test @Order(4)
  void testSpecialSelf() {
    for (int i = 0; i < smallIntensity; i++)
      testSpecialSelfAndOrXor(smallIntensity, rnd);
  }

  static boolean areSetEqual(MyBitSet s, BitSet bs) {
    if (bs.length() != s.length()) {
      System.out.println("wrong length : " + s.length() + " " + bs.length());
      return false;
    }
    if (bs.cardinality() != s.cardinality()) {
      System.out.println("wrong cardinality : "
              + s.cardinality() + " " + bs.cardinality());
      return false;
    }
    for (int i = 0; i < bs.length(); i++) {
      if (bs.get(i) != s.get(i)) {
        System.out.println("\nSetOf : " + s);
        System.out.println("BitSet: " + bs);
        System.out.println("Size: " + s.size());
        System.out.println("missing element : " + i);
        return false;
      }
    }
    int i;
    for (i = 0; i < bs.length(); i++) {
      if (bs.nextSetBit(i) != s.nextSetBit(i)) {
        System.out.println("nextSetBit(" + i + ") "
                + bs.nextSetBit(i) + " " + s.nextSetBit(i));
        return false;
      }
    }
    if (bs.nextSetBit(i) != s.nextSetBit(i)) {
      System.out.println("nextSetBit(" + i + ") "
              + bs.nextSetBit(i) + " " + s.nextSetBit(i));
      return false;
    }
    return true;
  }

  public static void testAddRm(MyBitSet s, BitSet bs, Random r, int nOperations) {
    int i = 0;
    for (i = 0; i < 10; i++) {
      assertTrue(areSetEqual(s, bs));
      rndAddRm(r, s, bs);
    }
    while (nOperations-- > 0) {
      rndAddRm(r, s, bs);
      if (!areSetEqual(s, bs))
        break;
    }
    assertTrue(areSetEqual(s, bs), "Error in add/remove/contains !");
  }
}
