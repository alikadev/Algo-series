package s09;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IntStackTestJU {
  @Test public void testCase1() {
    IntStack s = new IntStack();
    s.push(9);
    assertEquals (9, s.pop());
    assertTrue(s.isEmpty());
  }

  @Test public void testCase2() {
    IntStack s = new IntStack();
    s.push(9);
    s.push(5);
    assertFalse(s.isEmpty());
  }

  @Test public void testCase3() {
    IntStack s = new IntStack();
    s.push(6); s.push(5);
    s.pop();   s.push(8);
    assertEquals(8, s.pop());
    assertFalse(s.isEmpty());
  }

}
