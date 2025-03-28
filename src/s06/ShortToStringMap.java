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

  /// Adds an entry in the map, or updates it
  /// @param key The key of the entry
  /// @param val The value of the entry
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

  /// Returns the value at the key or null
  /// @param key The key of the value to read
  /// @return The value at the given key or null if there's no entry at this key
  public String get(short key) {
    int i = findIdxByKey(key);
    return (i < 0) ? null : entries[i].value;
  }

  /// Removes the entry at the given key.
  /// If !containsKey(key), this operates as a no-op
  /// @param key The key of the entry to remove
  public void remove(short key) {
    int idx = findIdxByKey(key);
    if (idx < 0) return;

    for (int i = idx; i < size - 1; i++) {
      entries[i] = entries[i + 1];
    }

    size--;
  }

  /// Verify if the map contains an entry with the given key
  /// @param k The key of the entry
  /// @return true if an entry with this key is present, false otherwise
  public boolean containsKey(short k) {
    int i = findIdxByKey(k);
    return i >= 0;
  }

  /// Verify if the map is empty
  /// @return true if the map is empty, false otherwise
  public boolean isEmpty() {
    return size() == 0;
  }

  /// Returns the size of the map
  /// @return The number of entry in the map
  public int size() {
    return size;
  }

  /// Returns a new iterator to the keys of the map.
  /// Warn: Do not edit the map while an iterator is used
  /// @return The iterator
  public ShortToStringMapItr iterator() {
    return new ShortToStringMapItr() {
      private int i = 0; ///< Current index
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

  /// Updates this map such that it becomes the union of itself and another map.
  /// When a key is present in both maps, the value from the other map (b) is used.
  /// @param b The other ShortToStringMap to union with this one.
  public void union(ShortToStringMap b) {
    ShortToStringMapItr it = b.iterator();
    while (it.hasMoreKeys()) {
      short k = it.nextKey();
      put(k, b.get(k));
    }
  }

  /// Updates this map such that it becomes the intersection of itself and another map.
  /// The values for the common keys will be those from the other map (b).
  /// @param b The other ShortToStringMap to intersect with this one.
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

  /// Returns a string representation of this map.
  /// @return A string looking like that: "{3:abc, 9:xy, -5:jk}"
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
  /// Debug
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
  }
}
