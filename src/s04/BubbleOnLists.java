package s04;

import s03.List;
import s03.ListItr;

/**
 * This class provides a bubble sort implementation for linked lists.
 */

public class BubbleOnLists {
    /**
     * Sorts the given list using the bubble sort algorithm.
     *
     * @param l the list to be sorted
     */
    static void bubbleSortList(List l) {
        if (l.isEmpty()) return; // If the list is empty, no sorting is needed
        ListItr li = new ListItr(l);
        boolean goOn = true;
        while (goOn) {
            goOn = false;
            li.goToFirst(); // Reset the iterator to the beginning on each pass

            while (!li.isLast()) { // Iterate until the second-last element
                if (bubbleSwapped(li)) {
                    goOn = true; // If a swap occurs, another pass is needed
                }
                li.goToNext(); // Move to the next element
            }
        }
    }

    /**
     * Swaps adjacent elements if they are out of order.
     *
     * @param li the list iterator used for traversal and swapping
     * @return true if a swap was performed, false otherwise
     */

    static boolean bubbleSwapped(ListItr li) {
        if (li.isFirst() || li.isLast()) return false; // No swapping at the first or last position

        int currentValue = li.consultAfter();// Get the value of the current node
        li.goToPrev();  // Move to the previous node
        int nextValue = li.consultAfter(); // Get the value of the previous node

        if (nextValue > currentValue) { // If the previous value is greater than the current value, swap them
            li.removeAfter(); // Remove the current node
            li.goToNext(); // Move forward to the correct position
            li.insertAfter(nextValue); // Insert the greater value after
            return true; // Swap occurred
        }
        li.goToNext(); // Move to the next node if no swap occurred
        return false;
    }

}


