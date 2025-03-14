package s03;

// When isFirst(), it is forbidden to call goToPrev()
// When isLast(),  it is forbidden to call goToNext() 
// When isLast(),  it is forbidden to call consultAfter(), or removeAfter()
// For an empty list, isLast()==isFirst()==true
// For a fresh ListItr, isFirst()==true
// Using multiple iterators on the same list is allowed only 
// if none of them modifies the list

import java.util.NoSuchElementException;
/// ListItr.java
public class ListItr {
  final List list; ///< The reference to the list
  ListNode pred, succ; ///< The references to the nodes

  /// Constructs a List Iterator. Initially points to the beginning
  /// PRE: anyList != null
  /// @param anyList The list to iterate
  public ListItr( List anyList ) {
    list = anyList; 
    goToFirst();   
  }

  /// Inserts a new element after the current position
  /// @param e The element to insert
  public void insertAfter(int e) {
    ListNode node = new ListNode(e, pred, succ);
    // Link the predecessor element
    if (pred != null)
      pred.next = node;
    else
      list.first = node;
    // Link the successor element
    if (succ != null)
      succ.prev = node;
    else
      list.last = node;

    succ = node;
    list.size++;
  }

  /// Removes the next element
  /// PRE: !isLast()
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

  /// Getter to the successor's value
  /// PRE: !isLast()
  public int  consultAfter() {
    return succ.elt;
  }

  /// Moves the iterator to the next position
  /// PRE: !isLast()
  public void goToNext() {
    pred = succ; 
    succ = succ.next; 
  }

  /// Moves the iterator to the previous position
  /// PRE: !isFist()
  public void goToPrev() {
    succ = pred;
    pred = pred.prev; 
  }

  /// Moves to the first position
  /// PRE: !list.isEmpty()
  public void goToFirst() { 
    succ = list.first; 
    pred = null;
  }

  /// Moves to the last position
  /// PRE: !list.isEmpty()
  public void goToLast() { 
    pred = list.last;  
    succ = null;
  }

  /// Verifies if the iterator is at the first position
  public boolean isFirst() { 
    return pred == null;
  }

  /// Verifies if the iterator is at the last position
  public boolean isLast() { 
    return succ == null; 
  }
}

