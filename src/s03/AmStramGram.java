package s03;

public class AmStramGram {
  
  // "Josephus problem" with persons numbered 1..n
  // Removes every k-th persons (ie skip k-1 persons)
  // PRE: n>=1, k>=1
  // RETURNS: the survivor
  // Example: n=5, k=2: 
  //   '12345 → 1'2345 → 1'(2)345 → 1'345 
  //          → 13'45  → 13'(4)5  → 13'5  
  //          → 135'   → '(1)35   → '35 
  //          → 3'5    → 3'(5)    → 3'  ===> survivor: 3
  public static int winnerAmStramGram(int n, int k) {
    List list = new List();
    ListItr li = new ListItr(list);

    // Fill the list with the values //
    for (int i = 1; i <= n; i++) {
      li.insertAfter(i);
      li.goToNext();
    }
    li.goToFirst();

    // Find the survivor //
    while (list.size != 1) {
      // Move for k-1 steps, so from 1 to k-1
      for (int i = 1; i <= (k-1); i++) {
        if (li.isLast())
          li.goToFirst();
        li.goToNext();
      }
      // Remove the next element
      if (li.isLast()) li.goToFirst();
      li.removeAfter();
    }

    // Returns the element //
    li.goToFirst();
    return li.consultAfter();
  }
  // ----------------------------------------------------------  
  static void josephusDemo(int n, int k) {
    System.out.printf("n=%d, k=%d : Survivor is %d\n", n, k, winnerAmStramGram(n, k));
  }

  public static void main(String[] args) {
    int n=5, k=2;  
    if (args.length == 2) {
      n = Integer.parseInt(args[0]);
      k = Integer.parseInt(args[1]);
    }
    josephusDemo(n, k);
  }
}
