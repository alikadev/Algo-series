package s04;

import java.util.Arrays;

public class Sorting {
  public static void main(String[] args) {
    int[] input  = {4, 3, 2, 6, 8, 7};
    int[] sorted = {2, 3, 4, 6, 7, 8};
    insertionSort(input);
    if(!Arrays.equals(sorted, input)) {
        System.out.println("Something is wrong..."); return;
      }
    int[] v = {5};
    insertionSort(v);
    int[] w = {};
    insertionSort(w);
    System.out.println("\nEnd of tiny demo");
  }

  public static void selectionSort(int[] a) {
    // TODO 
  }

  public static void shellSort(int[] a) {
    // TODO 
  }

  public static void insertionSort(int[] a) {
    for (int i=1; i < a.length; i++) {
      int v = a[i];          // v is the element to insert
      int j = i;
      while (j>0 && a[j-1] > v) {
        a[j] = a[j-1];   // move to the right
        j--;
      }
      a[j] = v;          // insert the element
    }
  }
}
