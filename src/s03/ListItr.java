package s03;

// When isFirst(), it is forbidden to call goToPrev()
// When isLast(),  it is forbidden to call goToNext() 
// When isLast(),  it is forbidden to call consultAfter(), or removeAfter()
// For an empty list, isLast()==isFirst()==true
// For a fresh ListItr, isFirst()==true
// Using multiple iterators on the same list is allowed only 
// if none of them modifies the list

import java.util.NoSuchElementException;

public class ListItr {
  final List list;
  ListNode pred, succ;
  // ----------------------------------------------------------
  public ListItr( List anyList ) {
    list = anyList; 
    goToFirst();   
  }

  public void insertAfter(int e) {
    ListNode node = new ListNode(e, pred, succ);
    if (pred != null)
      pred.next = node;
    else
      list.first = node;

    if (succ != null)
      succ.prev = node;
    else
      list.last = node;

    succ = node;
    list.size++;
  }

  public void removeAfter() {
    // Unlink the node
    ListNode n = succ;
    if (n == null) throw new NoSuchElementException();
    if (n.next != null) n.next.prev = n.prev;
    if (n.prev != null) n.prev.next = n.next;
    succ = n.next;
    list.size--;
    // Has first/last changed
    if (pred == null)
      list.first = succ;
    if (succ == null)
      list.last = pred;
  }

  public int  consultAfter() {
    return succ.elt;
  }
  public void goToNext() {
    pred = succ; 
    succ = succ.next; 
  }
  public void goToPrev() {
    succ = pred;
    pred = pred.prev; 
  }
  public void goToFirst() { 
    succ = list.first; 
    pred = null;
  }
  public void goToLast() { 
    pred = list.last;  
    succ = null;
  }
  public boolean isFirst() { 
    return pred == null;
  }
  public boolean isLast() { 
    return succ == null; 
  }

  public static void main(String[] args) {
    List l = new List();
    ListItr i = new ListItr(l);
    System.out.println("NY: " + (i.pred != null ? i.pred.elt : -1) + ", " + (i.succ != null ? i.succ.elt : -1) + ", f=" + (l.first != null ? l.first.elt : -1) + ", l=" + (l.last != null ? l.last.elt : -1));
    i.insertAfter(1);
    System.out.println("IN: " + (i.pred != null ? i.pred.elt : -1) + ", " + (i.succ != null ? i.succ.elt : -1) + ", f=" + (l.first != null ? l.first.elt : -1) + ", l=" + (l.last != null ? l.last.elt : -1));
    i.insertAfter(2);
    System.out.println("IN: " + (i.pred != null ? i.pred.elt : -1) + ", " + (i.succ != null ? i.succ.elt : -1) + ", f=" + (l.first != null ? l.first.elt : -1) + ", l=" + (l.last != null ? l.last.elt : -1));
    i.removeAfter();
    System.out.println("UT: " + (i.pred != null ? i.pred.elt : -1) + ", " + (i.succ != null ? i.succ.elt : -1) + ", f=" + (l.first != null ? l.first.elt : -1) + ", l=" + (l.last != null ? l.last.elt : -1));
    i.removeAfter();
    System.out.println("UT: " + (i.pred != null ? i.pred.elt : -1) + ", " + (i.succ != null ? i.succ.elt : -1) + ", f=" + (l.first != null ? l.first.elt : -1) + ", l=" + (l.last != null ? l.last.elt : -1));
  }
}

