
package s01;

import org.junit.jupiter.api.Test;

import java.util.ConcurrentModificationException;
import java.util.Enumeration;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class Ex1SmallTestJU {

  @Test
  public void shouldManageSomePushAndPop() {
    CharStack st = new CharStack();
    assertTrue(st.isEmpty());
    char[] t = {'h', 'e', 'y'};
    for(char c: t) {
      st.push(c);
      assertFalse(st.isEmpty());
      assertEquals(c, st.top());
      assertEquals(c, simulatedTop(st));
    }
    for(int i=t.length-1; i>=0; i--)
      assertEquals(t[i], st.pop());
    assertTrue(st.isEmpty());
  }

  @Test
  public void shouldManagePossibleExpansion() {
    int initialSize = 2;
    int size = initialSize * 5;
    CharStack st = new CharStack(initialSize);
    for(int i=0; i<size; i++) 
      st.push((char)('B' + i));
    char c = st.pop();
    while(!st.isEmpty()) {
      char e = st.top();
      assertEquals(e, st.pop());
      assertEquals(e, c-1);
      c = e;
    }
    assertEquals('B', c);
  }

  @Test
  public void shouldSupportTopDownTraversal() {
    CharStack st = new CharStack();
    char[] t = {'h', 'e', 'y'};
    assertEquals(0, sizeFromEnumerator(st.topDownTraversal()));
    for(int i=0; i<t.length; i++) {
      st.push(t[i]);
      assertEquals(i+1, sizeFromEnumerator(st.topDownTraversal()));
    }
    Enumeration<Character> en = st.topDownTraversal();
    for(int i=t.length-1; i>=0; i--)
      assertEquals(t[i], en.nextElement());
  }

  @Test
  public void nextElementShouldRaiseWhenNoMoreElements() {
    CharStack st = new CharStack();
    char[] t = {'h', 'e', 'y'};
    for(char c: t)
      st.push(c);
    Enumeration<Character> en = st.topDownTraversal();
    for(char c: t)
      en.nextElement();
    assertThrows(NoSuchElementException.class, en::nextElement);
  }

  @Test
  public void shouldDetectInvalidStateEnumerationEx1b() {
    CharStack st = new CharStack();
    char[] t = {'h', 'e', 'y'};
    Enumeration<Character> en0 = st.topDownTraversal();
    for(char c: t) st.push(c); // now en0 should be invalid
    Enumeration<Character> en3 = st.topDownTraversal();
    // en0 should still be invalid
    assertThrows(ConcurrentModificationException.class, en0::hasMoreElements);
    assertThrows(ConcurrentModificationException.class, en0::nextElement);
    en3.nextElement();
    st.push('a');
    // now en3 should also be "invalid"
    assertThrows(ConcurrentModificationException.class, en3::hasMoreElements);
    assertThrows(ConcurrentModificationException.class, en3::nextElement);

    // check if topIndex is used
    st = new CharStack();
    st.push('x');
    Enumeration<Character> en = st.topDownTraversal();
    st.push('y');
    st.pop();
    assertThrows(ConcurrentModificationException.class, en::hasMoreElements);
    assertThrows(ConcurrentModificationException.class, en::nextElement);
  }
    
  static char simulatedTop(CharStack st) {
    char c = st.pop();
    st.push(c);
    return c;
  }
  
  static int sizeFromEnumerator(Enumeration<?> en) {
    int s = 0; 
    while(en.hasMoreElements()) {
      s++;
      en.nextElement();
    }
    return s;
  }

}
