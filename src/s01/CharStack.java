package s01;

import java.util.ConcurrentModificationException;
import java.util.Enumeration;
import java.util.NoSuchElementException;

public class CharStack {
  private char[] buffer;
  private int    topIndex;
  private int    version; ///< Very basic version-control

  private static final int DEFAULT_SIZE = 10;

  public CharStack(int estimatedSize) {
    buffer = new char[estimatedSize];
    topIndex = -1;
    version = 0;
  } 

  public CharStack() {
    this(DEFAULT_SIZE); 
  }

  public boolean isEmpty() {
    return topIndex == -1;
  }

  public char top() {
    return buffer[topIndex];
  }

  public char pop() {
    version++;
    return buffer[topIndex--];
  }

  public void push(char x) {
    version++;
    topIndex++;
    if (topIndex == buffer.length) {
      // Double the buffer length - Copy old buffer in the new buffer
      char[] newbuf = new char[buffer.length * 2];
      for (int i = 0; i < buffer.length; i++) {
        newbuf[i] = buffer[i];
      }
      buffer = newbuf;
    }
    buffer[topIndex] = x;
  }
  
  /* Returns a read-only snapshot, as an Enumeration instance.
   * Typical usage:
   *   Enumeration<Character> en = myStack.topDownTraversal();
   *   while(en.hasMoreElements()) 
   *     System.out.println(en.nextElement());
   * Note that that according to the specification, nextElement() 
   * has to throw a NoSuchElementException
   */
  public Enumeration<Character> topDownTraversal() {
    return new Enumeration<>() {
      private int index = topIndex; // The local index
      private final int buildVersion = version; // Saves the current version
      @Override
      public boolean hasMoreElements() {
        if (version != buildVersion) throw new ConcurrentModificationException();
        return index >= 0;
      }
      @Override
      public Character nextElement() {
        if (version != buildVersion) throw new ConcurrentModificationException();
        if (!hasMoreElements())
          throw new NoSuchElementException();
        return buffer[index--];
      }
    };
  }
}
