package s10;

public class Ex3Demo {

  // Original version
  static void demo(int n) {
    IntQueueChained f; 
    int i, sum=0;
    f = new IntQueueChained();
    for (i=0; i<n; i++)
      f.enqueue(i);
    while(! f.isEmpty()) 
      sum = sum + f.dequeue();
    System.out.println(sum);
  }

  // Rewrite demo() to use QueueChained<T>
  static void demo3b(int n) {
    // TODO
  }

  // Rewrite demo() to use ObjQueueChained
  static void demo3c(int n) {
    // TODO
  }

  public static void main(String[] args) {
    int n = 10;
    demo(n);
    demo3b(n);
    demo3c(n);
  }
}
