package s10;

public class IntQueueChained {
  //======================================================================
  private static class QueueNode {
    final int elt;
    QueueNode next = null;
    // ----------
    QueueNode(int elt) { this.elt = elt; }
  }
  // ======================================================================
  private QueueNode front;
  private QueueNode back;

  public IntQueueChained() { }

  public void enqueue(int elt) {
    QueueNode node = new QueueNode(elt);
    // Update front / last back references
    if (back == null) front = node;
    else back.next = node;
    // Push back
    back = node;
  }

  public boolean isEmpty() {
    return back==null;
  }

  // PRE : !isEmpty()
  public int consult()     {
    return front.elt;
  }

  // PRE : !isEmpty()
  public int dequeue() {
    int e = front.elt;
    if (front == back) {
      back = null; front = null;
    } else {
      front = front.next;
    }
    return e;
  }

  public String toString() {
    String res="";
    QueueNode c=front;
    while(c!=null) {
      res += c.elt+" ";
      c=c.next;
    }
    return res;
  }
}
