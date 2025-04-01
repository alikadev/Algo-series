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
    // choisir le premier élément comme pivot
    int p = t[left];
    int k = left;
    // pour chaque case suivante i de left+1 à right:
    for (int i = left+1; i <= right; i++) {
      // si l'élément courant doit aller dans la partie grisée
      if (t[i] <= p) {
        // agrandir la partie grisée, en échangeant
        k++;
        int tmp = t[k];
        t[k] = t[i];
        t[i] = tmp;
      }
    }

    // mettre le pivot à la fin de la partie grisée
    int tmp = t[left];
    t[left] = t[k];
    t[k] = tmp;

    // retourner la position de l'extrémité droite de la
    return k;
  }

  /*
   * Pk <= pivot et pas < pivot ?
   * - Stabilité: si < pivot, alors les éléments égaux au pivot seront swaped
   * - WorstCaseSenario: si < pivot, n'itère pas le k si le tab like [1,1,1,1,1,1,1], donc O(n^2) au lieu de O(n)
   */

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
