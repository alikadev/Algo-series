package s09;

public class IntStack {
  private int[] buf; ///< The internal buffer for the stack
  private int top;  ///< The "top" of the stack index. Growing downwards

  /// Creates a typical IntStack with an initial capacity that will work
  /// for most!
  public IntStack() {
    this(10);
  }

  /// Creates an IntStack with an initial capacity
  /// @param initialCapacity The initial capacity
  /// PRE: initialCapacity >= 0
  public IntStack(int initialCapacity) {
    // Verify that the initial capacity is valid (not negative, not null)
    assert initialCapacity > 0;
    buf = new int[initialCapacity]; 
    top = initialCapacity;
    // Verify that isEmpty and the constructor are doing fine
    assert isEmpty();
  }

  /// @return true when the stack is empty, false otherwise
  public boolean isEmpty() {
    return top == buf.length - 1;
  }

  /// Pops out an element from the stack and returns it
  /// @return The poped element from the stack
  /// PRE: !isEmpty()
  public int pop() {
    // Verify that we don't pop if empty
    assert !isEmpty();
    int e = buf[top];  
    top++;
    return e;
  }

  /// Push an element on the stack. Will resize the stack if needed!
  /// @param x The element to push on the stack
  public void push(int x) { 
    checkSize(); 
    top--;
    buf[top] = x;
    // Shouldn't be empty by now...
    assert !isEmpty();
  }

  /// Verifies if the stack is full and grows it if needed.
  private void checkSize() {
    int n = buf.length;
    if (top == 0) { 
      int[] t = new int[2*n];
      for (int i=0; i<n; i++) 
        t[i+n] = buf[i];
      buf = t;
      top = n;
    }
  }
}
