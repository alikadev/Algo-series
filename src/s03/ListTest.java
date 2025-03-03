package s03;
import java.util.ArrayList;
import java.util.Random;

public class ListTest {
  static Random r = new Random();
  static List    l =new List   ();
  static ListItr li=new ListItr(l);
  static ListWithArray la = new ListWithArray();
  static int traces = 0;
  // ------------------------------------------------------------
  static void test(int n) {
    int a,i;
    boolean ok=true;
    for (i=0; i<n; i++) {
      switch(r.nextInt(6)) {
      case 0: // insert
        trace("i");
        a = r.nextInt(100);
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
      if (  la.size()    != l.size() |
            la.isEmpty() != l.isEmpty() |
            la.isFirst() != li.isFirst() |
            la.isLast()  != li.isLast() ) {
        ok=false; break;
      }
      if (!li.isLast() && la.consultAfter() != li.consultAfter()) {
        ok=false; break;
      }
    }
    if (!ok) {
      System.out.println("\n\nOups...there's a bug...");
      System.exit(-1);                         
    }
  }
  static void trace(String s) {
    if (traces++ >100) return;
    System.out.print(s);
  }
  // ----------------------------------------------------------
  public static void main (String [] args) {
    int n = 10000;
    long seed = r.nextInt(1000);
    r.setSeed(seed);
    System.out.println("Using seed "+seed);
    if (args.length==1)
      n=Integer.parseInt(args[0]);
    for(int i=0; i<50; i++)
      test(n);
    System.out.println("\nTest passed successfully...");
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
    void    insertAfter(int e) { v.add(pos, Integer.valueOf(e)); }
    void    removeAfter() {  v.remove(pos); if(isEmpty()) pos=0;}
    int    consultAfter() { return v.get(pos); }
    void goToNext()       { pos++;}
    void goToPrev()       { pos--;}
    void goToFirst()      { pos=0;}
    void goToLast()       { pos = size()==0?0:size();}
    boolean isFirst()     { return pos==0;}
    boolean isLast()      { return size()==0?true:pos==size();                 }
  }
}
