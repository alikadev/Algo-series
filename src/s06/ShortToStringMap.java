package s06;
public class ShortToStringMap {
  //===================================================
  // A Java 'record' defines a class, here with two immutable fields
  // the corresponding getters key()/value(), and a reasonable 
  // implementation of the constructor, the equals() method
  // and a hashCode() method
  private record MapEntry(short key, String value) {}
  //===================================================

  private int size;
  // TODO ...
  //------------------------------
  //  Private methods
  //------------------------------
 
  // Could be useful, for instance :
  // - one method to detect and handle the "array is full" situation
  // - one method to locate a key in the array 
  //   (to be called from containsKey(), put(), and remove())

  //------------------------------
  //  Public methods
  //------------------------------
  public ShortToStringMap() {
    // TODO ...
  }

  // adds an entry in the map, or updates it
  public void put(short key, String val) {
    // TODO ...
  } 

  // returns null if !containsKey(key)
  public String get(short key) {
    return null; // TODO ...
  }

  public void remove(short e) { 
    // TODO - ...
  }

  public boolean containsKey(short k) {
    return false; // TODO ...
  }

  public boolean isEmpty() {
    return size() == 0;
  }
  
  public int size() {
    return size;
  }
  
  public ShortToStringMapItr iterator() {
    return null; // TODO ...
  }

  // a.union(b) :        a becomes "a union b"
  //  values are those in b whenever possible
  public void union(ShortToStringMap m) {
    // TODO ...
  }

  // a.intersection(b) : "a becomes a intersection b"
  //  values are those in b
  public void intersection(ShortToStringMap s) {
    // TODO ...
  }

  // a.toString() returns all elements in 
  // a string like: {3:"abc", 9:"xy", -5:"jk"}
  @Override public String toString() {
    return ""; // TODO ...
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
  }

}
