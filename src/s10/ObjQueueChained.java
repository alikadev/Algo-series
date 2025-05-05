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

  /// Enqueues the element
  /// @param elt The element to enqueue
  public void enqueue (Object elt) {
    QueueNode node = new QueueNode(elt);
    // Update front / last back references
    if (back == null) front = node;
    else back.next = node;
    // Push back
    back = node;
  }

  /// Verify if the queue is empty
  /// @return true when the queue is empty
  public boolean isEmpty() {
    return back==null;
  }

  /// Consults the front value without poping it out
  /// @return The element to consult
  /// PRE : !isEmpty()
  public Object consult() {
    return front.elt;
  }

  /// Dequeue the front element of the queue
  /// @return The dequeued element
  /// PRE : !isEmpty()
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
