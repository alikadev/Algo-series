package s04;

import s03.List;
import s03.ListItr; 

public class BubbleOnLists {

  static void bubbleSortList(List l) {
    if (l.isEmpty()) return;
    ListItr li = new ListItr(l);
    boolean goOn = true;
    while(goOn) {
      // TODO 
    }
  }

  //Swaps between left and right element if needed
  //Returns true if swap occurred
  static boolean bubbleSwapped(ListItr li) {
    if (li.isFirst() || li.isLast()) return false;
    // TODO 
    return true;
  }
}