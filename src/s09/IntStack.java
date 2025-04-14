package s09;

public class IntStack {
  // Software defect (D): declare buf and top with 'static' modifier
  private int[] buf;     
  private int top;  // growing downwards

  public IntStack() {
    this(10);
  }

  public IntStack(int initialCapacity) {
    buf = new int[initialCapacity]; 
    top = initialCapacity;
  }

  public boolean isEmpty() {
    return top == buf.length;
  }
  private boolean isEmpty_buggy_A() {
    return top == buf.length -1 ; // Software defect (A)
  }

  public int pop() {
    int e = buf[top];  
    top++; 
    return e;
  }

  public void push(int x) { 
    checkSize(); 
    top--;
    buf[top] = x;
  }

  private void push_buggy_B(int x) {
    checkSize();
    buf[top] = x;   // Software defect (B)
    top--;          // Software defect (B)
  }

  private void push_buggy_C(int x) {
    checkSize();
    top--;
    buf[x] = top;  // Software defect (C)
  }

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
