package s03;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ListTestJU {
  static Random r = new Random(1256);

  List list = new List();
  ListItr li = new ListItr(list);
  ListWithArray la = new ListWithArray();
  int traces = 0;

  @BeforeEach
  void initListInstance() {
    list = new List();
    li = new ListItr(list);
    la = new ListWithArray();
    traces = 0;
  }

  // ------------------------------------------------------------
  void doRandomOperations(int n) {
    for (int i=0; i<n; i++) {
      switch(r.nextInt(6)) {
        case 0: // insert
          trace("i");
          int a = r.nextInt(100);
          li.insertAfter(a); la.insertAfter(a);
          break;
        case 1: // remove
          if (la.isLast()) break;
          trace("r");
          li.removeAfter(); la.removeAfter();
          break;
        case 2: // next
          if (la.isLast()) break;
          trace("n");
          li.goToNext(); la.goToNext();
          break;
        case 3: // prev
          if (la.isFirst()) break;
          trace("p");
          li.goToPrev(); la.goToPrev();
          break;
        case 4: // first
          trace("f");
          li.goToFirst(); la.goToFirst();
          break;
        case 5: // last
          trace("l");
          li.goToLast(); la.goToLast();
          break;
      }
      assertEquals(la.size(), list.size());
      assertEquals(la.isEmpty(), list.isEmpty());
      assertEquals(la.isFirst(), li.isFirst());
      assertEquals(la.isLast(), li.isLast());
      if(!la.isLast())
        assertEquals(la.consultAfter(), li.consultAfter());
    }
  }
  void trace(String s) {
    if (traces++ > 100) return;
    System.out.print(s);
  }

  @RepeatedTest(value = 50)
  public void shouldWorkOnRandomOperations() {
    int nOperations = 10_000;
    doRandomOperations(nOperations);
  }

  @Test
  void shouldMatchThatSmallScenario() {
    int[] t = {10,20,30,40,50};
    assertTrue(list.isEmpty());
    assertTrue(li.isFirst());
    assertTrue(li.isLast());
    li.insertAfter(30);
    li.insertAfter(10);
    li.goToNext();
    li.insertAfter(20);
    li.goToLast();
    li.insertAfter(50);
    li.insertAfter(40);
    li.goToFirst();
    for (int ti: t) {
      assertEquals(ti, li.consultAfter());
      li.goToNext();
    }
    assertTrue(li.isLast());
    li.goToFirst(); li.removeAfter();
    li.goToNext();  li.removeAfter();
    li.goToNext();  li.removeAfter();
    li.goToFirst();
    assertEquals(20, li.consultAfter());
    li.goToNext();
    assertEquals(40, li.consultAfter());
    li.goToNext();
    assertTrue(li.isLast());
  }
//===========================

  static class ListWithArray {
    private ArrayList<Integer> v = new ArrayList<Integer>();
    // ------------------
    boolean isEmpty() { return v.isEmpty();}
    int        size() { return v.size();}
    // ------------------
    private int pos;
    // ------------------
    void insertAfter(int e) { v.add(pos, Integer.valueOf(e)); }
    void removeAfter() {  v.remove(pos); if(isEmpty()) pos=0; }
    int consultAfter() { return v.get(pos); }
    void goToNext()    { pos++;}
    void goToPrev()    { pos--;}
    void goToFirst()   { pos=0;}
    void goToLast()    { pos = size()==0?0:size();}
    boolean isFirst()  { return pos==0;}
    boolean isLast()   { return size()==0?true:pos==size();                 }
  }
}
