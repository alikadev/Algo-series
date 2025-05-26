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
    /* Pseudo-code:
    count = total[e.hash]
    pos = e.hash
    ; Case count > 0 => try to find the current element
    while count > 0 loop
      pos = get next used location from pos
      ; Hash are the same => we process it as one of ours
      if elt[pos].hash ?= e.hash then
        if elt[pos] = e then return pos endif
        count--;
      endif
      ; Increment pos (circular)
      pos = (pos + 1) mod capacity
    endloop
    ; Otherwise => find first free location
    return get next free location from e.hash
    */
    int hash = hashString(e);
    int pos = hash;
    int count = total[pos];

    while (count > 0) {
      pos = busy.nextSetBit(pos);
      if (pos < 0) pos = busy.nextSetBit(0);
      if (hashString(elt[pos]) == hash) {
        if (e.equals(elt[pos])) return pos;
        count--;
      }
      pos = (pos + 1) % capacity();
    }

    pos = busy.nextClearBit(hash);
    if (pos >= capacity()) return busy.nextClearBit(0);
    return pos;
  }

  public void add(String e) {
    if (currentSize *2 >= capacity())
      doubleTable();
    int idx = targetIndex(e);
    if (busy.get(idx)) return;
    busy.set(idx);
    elt[idx] = e;
    total[hashString(e)]++;
    currentSize++;
  } 

  private void doubleTable() {
    // Backup needed attrs to reconstruct the entire table
    String[] eltBkp = elt;
    BitSet busyBkp = busy;

    // Reinit attrs and double their length
    total = new int[2 * total.length];
    elt = new String[2 * eltBkp.length];
    busy = new BitSet(2 * busy.length());
    currentSize = 0;

    // Restore the elements back in the new hash-table
    int idx = busyBkp.nextSetBit(0);
    while (idx >= 0) {
      add(eltBkp[idx]);
      idx = busyBkp.nextSetBit(idx + 1);
    }
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
    return new SetOfStringsItr() {
      private int idx = 0;
      @Override
      public boolean hasMoreElements() {
        return busy.nextSetBit(idx) != -1;
      }
      @Override
      public String nextElement() {
        int cur = busy.nextSetBit(idx);
        idx = cur  + 1;
        return elt[busy.nextSetBit(cur)];
      }
    };
  }

  public void union (SetOfStrings s) {
    SetOfStringsItr it = s.iterator();
    while (it.hasMoreElements()) {
      add(it.nextElement());
    }
  }

  public void intersection(SetOfStrings s) {
    SetOfStringsItr it = iterator();
    while (it.hasMoreElements()) {
      String e = it.nextElement();
      if (!s.contains(e))
        remove(e);
    }
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
    System.out.println(s.targetIndex(a) + " : " + s);
    s.add(a);
    System.out.println(s.targetIndex(a) + " : " + s);
    s.add(a);
    System.out.println(s.targetIndex(a) + " : " + s);
    s.add(b);
    s.remove(a);
    if (s.contains(a) || s.contains(c) || !s.contains(b)) {
      System.out.println("bad news: " + s);
    } else
      System.out.println("ok");

  }
}
