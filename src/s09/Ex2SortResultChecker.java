package s09;

/**
 * Checks if the observedOutput is a correctly sorted version of givenInput.
 * This method does not modify any of the inputs and does not use built-in sorting.
 *
 * @param givenInput     the original unsorted input array
 * @param observedOutput the array that is supposed to be the sorted result
 * @return true if observedOutput is a valid sorted version of givenInput, false otherwise
 */

public class Ex2SortResultChecker {
  public static boolean isSortingResultCorrect(int[] givenInput, int[] observedOutput) {
    // Step 1: Check if both arrays have the same length
    if (givenInput.length != observedOutput.length) {
      return false;
    }

    // Step 2: Check if observedOutput is sorted in non-decreasing (ascending) order
    for (int i = 1; i < observedOutput.length; i++) {
      if (observedOutput[i - 1] > observedOutput[i]) {
        return false;
      }
    }

    // Step 3: Check if both arrays have the same elements with the same number of occurrences
    for (int i = 0; i < givenInput.length; i++) {
      int e = givenInput[i];
      if (nbOfOccurrences(givenInput, e) != nbOfOccurrences(observedOutput, e)) {
        return false;
      }
    }

    // All checks passed: the sorting result is correct
    return true;
  }

  /**
   * Counts the number of times a specific element appears in an array.
   *
   * @param t the array to search
   * @param e the element to count
   * @return how many times e appears in t
   */
  // Maybe you'll find such a method useful...
  private static int nbOfOccurrences(int[] t, int e) {
    int count = 0;
    for (int i = 0; i < t.length; i++) {
      if (t[i] == e) {
        count++;
      }
    }
    return count;
  }
}
