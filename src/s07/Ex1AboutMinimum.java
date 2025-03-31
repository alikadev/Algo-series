package s07;

public class Ex1AboutMinimum {

  // Finds the minimum value in an array using a single recursive call
  public static int min1(int[] t) {
    if (t == null || t.length == 0) {
      throw new IllegalArgumentException("Array must not be empty");
    }
    return min1Recursive(t, 0);
  }

  // Recursive helper that compares the current element with the minimum of the rest
  private static int min1Recursive(int[] t, int index) {
    if (index == t.length - 1) {
      return t[index]; // Base case: only one element left
    }
    int minRest = min1Recursive(t, index + 1);
    return Math.min(t[index], minRest);
  }

  // Finds the minimum value in an array using two recursive calls (divide-and-conquer)
  public static int min2(int[] t) {
    if (t == null || t.length == 0) {
      throw new IllegalArgumentException("Array must not be empty");
    }
    return min2Recursive(t, 0, t.length);
  }

  // Recursive helper that divides the array in two and finds the min in both halves
  private static int min2Recursive(int[] t, int start, int end) {
    if (end - start == 1) {
      return t[start]; // Base case: only one element
    }
    int mid = (start + end) / 2;
    int minLeft = min2Recursive(t, start, mid); // Recursive call on the left half
    int minRight = min2Recursive(t, mid, end);  // Recursive call on the right half
    return Math.min(minLeft, minRight); // Return the smaller value between both halves
  }
}
