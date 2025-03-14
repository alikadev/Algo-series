package s04;

import org.junit.jupiter.api.Test;
import s03.List;
import s03.ListItr;

import static org.junit.jupiter.api.Assertions.*;
import static s04.BubbleOnLists.bubbleSortList;

public class BubbleOnListTestJU {
  @Test
  public void shouldSortThatSmallSample() {
    List l  =new List();
    ListItr li = new ListItr(l);
    int[] t = {4,3,9,2,1,8,0};
    int[] r = {0,1,2,3,4,8,9};
    for(int ti: t) {
      li.insertAfter(ti); li.goToNext();
    }
    bubbleSortList(l);
    li = new ListItr(l);
    for (int ri: r) {
      assertFalse(li.isLast());
      assertEquals(ri, li.consultAfter());
      li.goToNext();
    }
    assertTrue(li.isLast(), "Oops, too many elements!");
  }
}