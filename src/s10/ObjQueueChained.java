package s10;

// This is a version using explicit Object (as if Java was not offering generic types)
public class ObjQueueChained {
  private static class QueueNode {
    final Object elt;
    QueueNode next = null;
    QueueNode(Object elt) { this.elt = elt; }
  }
  // ======================================================================
  private QueueNode front;
  private QueueNode back;
  public ObjQueueChained() {}

  public void enqueue (Object elt) {
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

  public Object consult() {
    return front.elt;
  }

  public Object dequeue() {
    Object e = front.elt;
    if (front == back) {
      back = null; front = null;
    } else {
      front = front.next;
    }
    return e;
  }
}
