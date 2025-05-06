package s10;

// ======================================================================
public class IntQueueArray {
  private static int DEFAULT_SIZE = 10;
  private int[] buffer = new int[DEFAULT_SIZE];
  private int   front = 1;
  private int   back = 0;
  private int   size = 0;
  // ------------------------------
  public IntQueueArray() { }

  public void enqueue(int elt) {
    checkSize();
    back++; if(back==buffer.length) back=0;
    buffer[back]=elt;
    size++;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  // PRE: !isEmpty()
  public int     consult() {
    return buffer[front];
  }

  // PRE: !isEmpty()
  public int dequeue() {
    int result = buffer[front];
    front++;
    if (front == buffer.length) front = 0;
    size--;
    return result;
  }

  private void checkSize() {
    if (size < buffer.length) return;
    int[] newBuffer = new int[2 * buffer.length];
    for (int i = 0; i < size; i++) {
      newBuffer[i] = buffer[(front + i) % buffer.length];
    }
    buffer = newBuffer;
    front = 0;
    back = size - 1;
  }
}
