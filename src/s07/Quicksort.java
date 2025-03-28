package s07;

import java.util.Arrays;

public class Quicksort {
  public static void main(String [] args) {
    int[] t = {4, 3, 2, 6, 8, 7};
    int[] u = {2, 3, 4, 6, 7, 8};
    quickSort(t);
    if(!Arrays.equals(t, u))
        throw new IllegalStateException("Oops. Something is wrong...");
    System.out.println("OK. Tiny test passed...");
  }
  //------------------------------------------------------------
  // chooses a pivot, and partitions the sub-array
  // Returns the final position of the pivot
  private static int partition(int[] t, int left, int right) {
    return 0; // TODO
  }

  private static void quickSort(int[] t, int left, int right) {
    if (left >= right)
      return;
    int p = partition(t, left, right);
    quickSort(t, left, p-1);
    quickSort(t, p+1, right);
  }

  public static void quickSort(int[] t) {
    quickSort(t, 0, t.length - 1);
  }
}
