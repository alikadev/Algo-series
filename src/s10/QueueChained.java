package s10;

public class QueueChained<E> {
  private static class QueueNode<E> {
    final E elt;
    QueueNode<E> next = null;
    QueueNode(E elt) { this.elt = elt; }
  }
  // ======================================================================
  private QueueNode<E> front;
  private QueueNode<E> back;
  public QueueChained() {}

  /// Enqueues the element
  /// @param elt The element to enqueue
  public void enqueue (E elt) {
    QueueNode<E> node = new QueueNode<>(elt);
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
  public E consult() {
    return front.elt;
  }

  /// Dequeue the front element of the queue
  /// @return The dequeued element
  /// PRE : !isEmpty()
  public E dequeue() {
    E e = front.elt;
    if (front == back) {
      back = null; front = null;
    } else {
      front = front.next;
    }
    return e;
  }
}
