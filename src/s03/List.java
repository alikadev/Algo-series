package s03;
public class List {
  ListNode first, last;
  int size;

  public List() {
    first = null;
    last = null;
    size = 0;
  } 
  public int size() {
    return size;
  }
  public boolean isEmpty() {
    return size() == 0; 
  }
}
