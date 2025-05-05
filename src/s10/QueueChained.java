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

  public void enqueue (E elt) {
    QueueNode<E> node = new QueueNode<>(elt);
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
  public E consult() {
    return front.elt;
  }

  // PRE : !isEmpty()
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
