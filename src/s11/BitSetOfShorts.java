package s11;

import java.util.BitSet;

/**
 * BitSetOfShorts: A set of short integers using java.util.BitSet.
 * This class supports efficient add, remove, contains, union, intersection,
 * and iteration over all stored short values.
 */
public class BitSetOfShorts {
  final BitSet bs;
  static final short MINV = Short.MIN_VALUE; // -32768
  static final short MAXV = Short.MAX_VALUE; // +32767

  /**
   * Converts a short value to a valid index in the BitSet.
   * Since BitSet only accepts non-negative indices, we shift the range
   * so that MINV maps to 0, MINV+1 to 1, ..., MAXV to 65535.
   */
  static int indexFromElt(short e) {
    return e - MINV;
  }

  /**
   * Converts a BitSet index back to the original short value.
   * Inverse of indexFromElt.
   */
  static short eltFromIndex(int i) {
    return (short)(i + MINV);
  }

  /**
   * Constructor: initializes the BitSet with default size.
   * No need to set capacity manually unless performance tuning is required.
   */
  public BitSetOfShorts() {
    bs = new BitSet(); // default size is fine for dynamic use
  }

  // ------------------------------------------------------------

  /** Adds element e to the set. */
  public void add(short e) {
    bs.set(indexFromElt(e));
  }

  /** Removes element e from the set. */
  public void remove(short e) {
    bs.clear(indexFromElt(e));
  }

  /** Checks if the element e is in the set. */
  public boolean contains(short e) {
    return bs.get(indexFromElt(e));
  }

  /**
   * Returns an iterator over the set elements.
   * The iterator skips over all values that are not present (bit = 0).
   */
  public BitSetOfShortsItr iterator() {
    return new BitSetOfShortsItr() {
      private int currentIndex = bs.nextSetBit(0); // first index with bit 1

      @Override
      public boolean hasMoreElements() {
        return currentIndex >= 0;
      }

      @Override
      public short nextElement() {
        short result = eltFromIndex(currentIndex);
        currentIndex = bs.nextSetBit(currentIndex + 1); // move to next set bit
        return result;
      }
    };
  }

  /** Performs union with another BitSetOfShorts (bitwise OR). */
  public void union(BitSetOfShorts s) {
    bs.or(s.bs);
  }

  /** Performs intersection with another BitSetOfShorts (bitwise AND). */
  public void intersection(BitSetOfShorts s) {
    bs.and(s.bs);
  }

  /** Returns the number of elements in the set. */
  public int size() {
    return bs.cardinality(); // count of bits set to 1
  }

  /** Returns true if the set is empty. */
  public boolean isEmpty() {
    return bs.length() == 0;
  }

  /**
   * Returns a string representation of the set, e.g. {-3, 2, 8}
   * Uses the iterator to list the present values.
   */
  public String toString() {
    String r = "{";
    BitSetOfShortsItr itr = iterator();
    if (isEmpty()) return "{}";
    r += itr.nextElement(); // first element
    while (itr.hasMoreElements()) {
      r += ", " + itr.nextElement(); // comma-separated
    }
    return r + "}";
  }

  // ------------------------------------------------------------

  /**
   * Demo main method.
   * Builds two sets from sample arrays, prints their contents, performs union.
   */
  public static void main(String[] args) {
    BitSetOfShorts a = new BitSetOfShorts();
    BitSetOfShorts b = new BitSetOfShorts();
    short[] ta = {-3, 5, 6, -3, 9, 9};
    short[] tb = {6, 7, -2, -3};

    for (int i = 0; i < ta.length; i++) {
      a.add(ta[i]);
      System.out.println("" + a + a.size());
    }

    for (int i = 0; i < tb.length; i++) {
      b.add(tb[i]);
      System.out.println("" + b + b.size());
    }

    a.union(b); // union of a and b
    System.out.println("" + a + a.size());
  }
}
