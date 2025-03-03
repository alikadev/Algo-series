package s03;

// When isFirst(), it is forbidden to call goToPrev()
// When isLast(),  it is forbidden to call goToNext() 
// When isLast(),  it is forbidden to call consultAfter(), or removeAfter()
// For an empty list, isLast()==isFirst()==true
// For a fresh ListItr, isFirst()==true
// Using multiple iterators on the same list is allowed only 
// if none of them modifies the list

public class ListItr {
  final List list;
  ListNode pred, succ;
  // ----------------------------------------------------------
  public ListItr( List anyList ) {
    list = anyList; 
    goToFirst();   
  }

  public void insertAfter(int e) {
    // TODO
  }

  public void removeAfter() {
    // TODO 
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
}

