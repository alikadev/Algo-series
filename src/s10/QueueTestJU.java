package s10;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

// ======================================================================
public class QueueTestJU {
  static final int N_OPERATIONS = 10_000;
  static final int N_TEST_RUNS = 100;
  Random rnd;

  @BeforeEach void setup() {
    rnd = new Random(2209);
  }

  interface IIntQueue {
    void enqueue(int e);
    int dequeue();
    int consult();
    boolean isEmpty();
  }

  static class MyIntQueueArray extends IntQueueArray implements IIntQueue {}
  static class MyIntQueueChained extends IntQueueChained implements IIntQueue {}
  static class MyQueueChained  implements IIntQueue {
    QueueChained<Integer> q = new QueueChained<>();
    public void enqueue(int e) { q.enqueue(e);       }
    public int dequeue()       { return q.dequeue(); }
    public int consult()       { return q.consult(); }
    public boolean isEmpty()   { return q.isEmpty(); }
  }
  static class MyObjQueueChained  implements IIntQueue {
    ObjQueueChained q = new ObjQueueChained();
    public void enqueue(int e) { q.enqueue(e);       }
    public int dequeue()       { return (Integer) q.dequeue(); }
    public int consult()       { return (Integer) q.consult(); }
    public boolean isEmpty()   { return q.isEmpty(); }
  }

  void exercise(Supplier<IIntQueue> qc) {
    for(int j=0; j<N_TEST_RUNS; j++) {
      IIntQueue q = qc.get();
      int m=0; int k=0; int p = 0;
      for(int i = 0; i< N_OPERATIONS; i++) {
        boolean doAdd = rnd.nextBoolean();
        if (doAdd) {
          k++;
          q.enqueue(k);
          assertFalse(q.isEmpty());
          m++;
          //System.out.print("a("+k+")");
        } else {
          if (m==0) {
            assertTrue(q.isEmpty());
          } else {
            assertFalse(q.isEmpty());
            int ec = q.consult();
            int e = q.dequeue();
            //System.out.print("r("+e+")");
            m--;
            assertEquals( p+1, e);
            assertEquals( p+1, ec);
            p++;
          }
        }
      }
    }
  }

  @Test void testIntQueueArray() {
    exercise(MyIntQueueArray::new);
  }

  @Test void testIntQueueChained() {
    exercise(MyIntQueueChained::new);
  }

  @Test void testGenericQueue() {
    exercise(MyQueueChained::new);
  }

  @Test void testObjQueue() {
    exercise(MyObjQueueChained::new);
  }

}
