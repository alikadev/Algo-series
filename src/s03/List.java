package s03;

/// List.java
public class List {
  ListNode first, last; ///< The first and last node
  int size; ///< The size of the List

  /// Constructs an empty List
  public List() {
    first = null;
    last = null;
    size = 0;
  }
  /// Getter to the size of the List
  /// @return The size of the List
  public int size() {
    return size;
  }
  /// Verify if the List is empty
  /// @return true when the size of the list is 0, false otherwise
  public boolean isEmpty() {
    return size() == 0; 
  }
}
