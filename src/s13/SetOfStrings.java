package s13;
import java.util.BitSet;

public class SetOfStrings {
  static final int DEFAULT_CAPACITY = 5;
  int currentSize;
  // three "parallel arrays" (alternative: one array HashEntry[],
  // with String/int/boolean fields)
  String[] elt;
  int[]    total;
  BitSet   busy;
  // ------------------------------------------------------------
  public SetOfStrings ()  {
    this(DEFAULT_CAPACITY);
  }

  public SetOfStrings(int initialCapacity)  {
    initialCapacity = Math.max(2, initialCapacity);
    elt   = new String[initialCapacity];
    total = new    int[initialCapacity];
    busy  = new BitSet(initialCapacity);
    currentSize = 0;
  }

  int capacity() {
    return elt.length;
  }

  // Here is the hash function :
  int hashString(String s) {
    int h = s.hashCode() % capacity();
    if (h<0) h=-h;
    return h;
  }

  // PRE: table is not full
  // returns the index where element e is stored, or, if 
  // absent, the index of an appropriate free cell 
  // where e can be stored
  int targetIndex(String e) { 
    return 0; // TODO 
  }

  public void add(String e) {
    if (currentSize *2 >= capacity())
      doubleTable();
    // TODO 
  } 

  private void doubleTable() {
    // TODO 
  }

  public void remove(String e) {
    int i = targetIndex(e);
    if (!busy.get(i)) return; // elt is absent
    int h = hashString(e);
    total[h]--;
    elt[i]=null;
    busy.clear(i); 
    currentSize--;
  }

  public boolean contains(String e) {
    return busy.get(targetIndex(e));
  }
  
  public SetOfStringsItr iterator() {
    return null;     // TODO 
  }

  public void union (SetOfStrings s) {
    // TODO 
  }

  public void intersection(SetOfStrings s) {
    // TODO 
  }

  public int size() {
    return currentSize;
  }

  public boolean isEmpty() {
    return size() == 0;
  }

  private String[] arrayFromSet() {
    String[] t = new String[size()];
    int i=0;
    SetOfStringsItr itr = this.iterator();
    while (itr.hasMoreElements()) {
      t[i++] = itr.nextElement();
    }
    return t;
  }

  public String toString() { 
    SetOfStringsItr itr = this.iterator();
    if (isEmpty()) return "{}";
    String r = "{" + itr.nextElement();
    while (itr.hasMoreElements()) {
      r += ", " + itr.nextElement();
    }
    return r + "}";
  }
  // ------------------------------------------------------------
  // tiny demo
  // ------------------------------------------------------------
  public static void main(String [] args) {
    String a="abc";
    String b="defhijk";
    String c="hahaha";
    SetOfStrings s=new SetOfStrings();
    s.add(a); s.add(b); s.remove(a);
    if (s.contains(a) || s.contains(c) || !s.contains(b))
      System.out.println("bad news...");
    else 
      System.out.println("ok");
  }
}
