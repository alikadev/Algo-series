package s11;
public class MyBitSet {
  // ------------------------------------------------------------
  private int[] buffer;
  private static final short NB_OF_BITS = 32;
  private static final int DEFAULT_CAPACITY = 100;
  // ------------------------------------------------------------
  public MyBitSet() {
    this(DEFAULT_CAPACITY);
  }

  public MyBitSet(int capacity) {
    buffer = new int[1+(capacity/NB_OF_BITS)];
  }
  /// Sets a bit's value in the bitset
  /// @param bitIndex The index of the bit
  /// @param value The value of the bit (true = 1, false = 0)
  /// PRE: bitIndex >= 0
  public void set(int bitIndex, boolean value) {
    assert bitIndex >= 0;
    int buffIdx = bitIndex / NB_OF_BITS;
    int bitMask = 1 << (bitIndex % NB_OF_BITS);
    checkSize(buffIdx);

    if (value) buffer[buffIdx] |= bitMask;
    else buffer[buffIdx] &= ~bitMask;
  }


  public void set  (int bitIndex) { set(bitIndex, true);   }
  public void clear(int bitIndex) { set(bitIndex, false);  }
  // ------------------------------------------------------------
  // ------------------------------------------------------------
  // ensures that that array cell exists
  // (re-dimensions the array if necessary)
  private void checkSize(int arrayIndex) {
    if (arrayIndex<buffer.length) return;
    int f = 1+arrayIndex/buffer.length;
    int[] aux = new int [f*buffer.length]; // or new int[arrayIndex+1] if
    for (int j=0; j<buffer.length; j++)    // we choose the minimal size
      aux[j] = buffer[j];
    buffer = aux;
    assert arrayIndex < buffer.length;
  }

  /// Get the bit's value at the requested index
  /// @param bitIndex The bit index
  /// @return The value of the bit (true = 1, false = 0)
  /// PRE: bitIndex >= 0
  public boolean get(int bitIndex) {
    assert bitIndex >= 0;
    int buffIdx = bitIndex / NB_OF_BITS;
    int bitMask = 1 << (bitIndex % NB_OF_BITS);
    checkSize(buffIdx);
    return (buffer[buffIdx] & bitMask) != 0;
  }

  /// Binary ANDs this bitset with o's one and self-assign it
  /// @param o The other bitset to AND with
  /// PRE: o != null
  public void and(MyBitSet o) {
    assert o != null;
    // Grow this set to match o if needed
    checkSize(o.size() - 1);
    for (int i = 0; i < o.buffer.length; i++) {
      buffer[i] &= o.buffer[i];
    }
    for (int i = o.buffer.length; i < buffer.length; i++)
      buffer[i] = 0;
  }

  /// Binary ORs this bitset with o's one and self-assign it
  /// @param o The other bitset to OR with
  /// PRE: o != null
  public void or(MyBitSet o) {
    assert o != null;
    // Grow this set to match o if needed
    checkSize(o.buffer.length - 1);
    for (int i = 0; i < o.buffer.length; i++) {
      buffer[i] |= o.buffer[i];
    }
  }

  /// XORs this bitset with o's one and self-assign it
  /// @param o The other bitset to XOR with
  /// PRE: o != null
  public void xor(MyBitSet o) {
    assert o != null;
    // Grow this set to match o if needed
    checkSize(o.buffer.length - 1);
    for (int i = 0; i < o.buffer.length; i++) {
      buffer[i] ^= o.buffer[i];
    }
  }

  public int size() { // crt capacity, total nb of bits
    return buffer.length * NB_OF_BITS;
  }

  public int length() { // highest bit "on" + 1
    int n = 0;
    for(int i=0; i<buffer.length * NB_OF_BITS; i++)
      if (get(i)) n = i+1;
    return n;
  }

  /// Finds the next set bit starting at the specified index (inclusive)
  /// @param fromIndex The index (inclusive) to start to search from
  /// @return The index of the next set bit or -1 is none were found
  /// PRE: fromIndex >= 0
  public int nextSetBit(int fromIndex) {
    assert fromIndex >= 0;
    if (fromIndex >= size()) return -1;
    for(int i = fromIndex; i < size(); i++)
      if (get(i)) return i;
    return -1;
  }

  /// Finds the cardinality of the bitset - The number of bit set
  /// @return The cardinality of the bitset
  public int cardinality() {  // nb of bits set to true
    int n = 0;
    for (int i = 0; i < size(); i++) {
      if (get(i)) n++;
    }
    return n;
  }

  public String toString() {
    String r = "{";
    for(int i=0; i<buffer.length * NB_OF_BITS; i++)
      if (get(i))
        if(r.length() == 1) r += i;
        else r+= "," + i;
    return r + "}";
  }
  // ------------------------------------------------------------
  public static void main (String [] args) {
    MyBitSet a = new MyBitSet(100);
    ok(a.length()==0);
    System.out.println(a);
    a.set(4);
    ok(a.get(4));
    ok(!a.get(3));
    a.clear(4);a.clear(5);
    a.set(6);
    ok(!a.get(4));
    ok(a.get(6));
    ok(!a.get(5));
    System.out.println(a);
  }

  static void ok(boolean b) {
    if (b) return;
    throw new RuntimeException("property not verified: ");
  }
}
