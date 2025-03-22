package s06;

public class ShortToStringMap {
  //===================================================
  // A Java 'record' defines a class, here with two immutable fields
  // the corresponding getters key()/value(), and a reasonable 
  // implementation of the constructor, the equals() method
  // and a hashCode() method
  private record MapEntry(short key, String value) { }
  //===================================================

  private static final int ENTRIES_INITIAL_CAPACITY = 16;
  private static final int ENTRIES_INCREASE_FACTOR = 2;
  private int size;
  private MapEntry[] entries;
  //------------------------------
  //  Private methods
  //------------------------------
  /// Find an entry by its key and returns its index in the MapEntry
  /// @param key The key of the entry to find
  /// @return The index of the entry or -1
  private int findIdxByKey(short key) {
    for (int i = 0; i < size; i++) {
      if (entries[i].key == key)
        return i;
    }
    return -1;
  }

  //------------------------------
  //  Public methods
  //------------------------------
  public ShortToStringMap() {
    entries = new MapEntry[ENTRIES_INITIAL_CAPACITY];
    size = 0;
  }

  // adds an entry in the map, or updates it
  public void put(short key, String val) {
    // Increase buffer if needed
    if (size + 1 > entries.length) {
      MapEntry[] prev = entries;
      entries = new MapEntry[entries.length * ENTRIES_INCREASE_FACTOR];
      System.arraycopy(prev, 0, entries, 0, prev.length);
    }
    // If the entry already exists, replace. Insert new otherwise
    int prevIdx = findIdxByKey(key);
    if (prevIdx < 0) { // Insert
      entries[size++] = new MapEntry(key, val);
    } else { // Replace
      entries[prevIdx] = new MapEntry(key, val);
    }
  }

  // returns null if !containsKey(key)
  public String get(short key) {
    int i = findIdxByKey(key);
    return (i < 0) ? null : entries[i].value;
  }

  public void remove(short key) {
    int idx = findIdxByKey(key);
    if (idx < 0) return;

    for (int i = idx; i < size - 1; i++) {
      entries[i] = entries[i + 1];
    }

    size--;
  }

  public boolean containsKey(short k) {
    int i = findIdxByKey(k);
    return i >= 0;
  }

  public boolean isEmpty() {
    return size() == 0;
  }
  
  public int size() {
    return size;
  }
  
  public ShortToStringMapItr iterator() {
    return new ShortToStringMapItr() {
      int i = 0;
      @Override
      public boolean hasMoreKeys() {
        return i < size;
      }

      @Override
      public short nextKey() {
        return entries[i++].key;
      }
    };
  }

  // a.union(b) :        a becomes "a union b"
  //  values are those in b whenever possible
  public void union(ShortToStringMap b) {
    ShortToStringMapItr it = b.iterator();
    while (it.hasMoreKeys()) {
      short k = it.nextKey();
      put(k, b.get(k));
    }
  }

  // a.intersection(b) : "a becomes a intersection b"
  //  values are those in b
  public void intersection(ShortToStringMap b) {
    // Cannot use an Short2StringMapItr because it cannot iterate from the end
    // Because the current element gets removed, the iterator index gets disrupted and some values are skipped
    for (int i = size - 1; i >= 0; i--) {
      short k = entries[i].key;
      if (!b.containsKey(k))
        remove(k);
      else
        put(k, b.get(k));
    }
  }

  // a.toString() returns all elements in 
  // a string like: {3:"abc", 9:"xy", -5:"jk"}
  @Override public String toString() {
    StringBuilder s = new StringBuilder();
    s.append('{');
    for (int i = 0; i < size; i++) {
      if (i != 0)
        s.append(", ");
      s.append(entries[i].key);
      s.append(":");
      s.append(entries[i].value);
    }
    s.append('}');
    return s.toString();
  }
  // ------------------------------------------
  public static void main(String[] args) {
    // tiny demo
    ShortToStringMap sm = new ShortToStringMap();
    sm.put((short)4, "ab");
    sm.put((short)-8, "ef");
    sm.put((short)55, "xy");
    sm.put((short)12, "gh");
    sm.remove((short)55);
    sm.put((short)-8, "EF");
    System.out.println(sm);

    ShortToStringMap m1 = new ShortToStringMap();
    m1.put((short)3, "3a");
    m1.put((short)1, "1a");
    m1.put((short)2, "2a");
    m1.put((short)4, "4a");
    m1.put((short)5, "5a");

    ShortToStringMap m2 = new ShortToStringMap();
    m2.put((short)1, "1b");
    m2.put((short)6, "6b");
    m2.put((short)7, "7b");
    m2.put((short)2, "2b");
    m2.put((short)8, "8b");

    System.out.println("A: "+m1);
    System.out.println("B: "+m2);
    m1.intersection(m2);
    System.out.println("A&B: "+m1);

    ShortToStringMapTestJU.S2SMap s1 = new ShortToStringMapTestJU.S2SMap();
    s1.put((short)3, "3a");
    s1.put((short)1, "1a");
    s1.put((short)2, "2a");
    s1.put((short)4, "4a");
    s1.put((short)5, "5a");

    ShortToStringMapTestJU.S2SMap s2 = new ShortToStringMapTestJU.S2SMap();
    s2.put((short)1, "1b");
    s2.put((short)6, "6b");
    s2.put((short)7, "7b");
    s2.put((short)2, "2b");
    s2.put((short)8, "8b");

    System.out.println("A: "+s1.tr);
    System.out.println("B: "+s2.tr);
    s1.intersection(s2);
    System.out.println("A&B: "+s1.tr);
  }

}
