package s12;

import java.util.Arrays;
import java.util.Random;

public class RndMajority {
  // PRE: 0 < risk <= 1.0
  public static boolean hasMajority(Random r, int[] t, double risk) {
      while (risk < 1.0) {
          int majority = 0;
          int rndIdx = r.nextInt(t.length);
          // Count the element
          for (int i = 0; i < t.length; i++) {
              if (t[rndIdx] == t[i]) {
                  majority++;
                  if (majority > t.length / 2) return true;
              }
          }
          // Reduce risk
          risk *= 2.0;
      }
      return false;
  }
  // ------------------------------------------------------------

  static final int MAX_VALUE = 1000;

  public static int[] generateMajorityArray(int n, Random r) {
    // Generate an array containing a random majority value
    int[] t = new int[n];
    int m = r.nextInt(MAX_VALUE);
    // make m the majority value
    for (int i = 0; i < n; i++) {
      if (i < (n-1)/2)
        t[i] = r.nextInt(MAX_VALUE);
      else 
        t[i] = m;
    }
    shuffle(r, t);
    return t;
  }

  public static int[] generateNotMajorityArray(int n, Random r) {
    assert(n>1);
    int[] t = new int[n];
    int m = r.nextInt(MAX_VALUE);
    int i;
    t[0] = m-1;
    for (i=1; i<n; i++)
      if (i-1<n/2) t[i] = m;
      else         t[i] = m+1;
    shuffle(r, t);
    return t;
  }  

  public static void testMajority(int n, Random r, double risk) {
    int mistakes = 0;
    // test with some majority arrays
    for (int i = 0; i < n; i++) {
      int[] t = generateMajorityArray(n, r);
      if (!hasMajority(r, t, risk)) mistakes++;
    }
    // test with some not majority arrays
    for (int i = 0; i < n; i++) {
      int[] t = generateNotMajorityArray(n, r);
      if (hasMajority(r, t, risk))
        throw new RuntimeException(
            "This array has no majority, but your program has detected one: "
            + Arrays.toString(t));
    }
    double mistakeRatio = mistakes/(double)n;
    System.out.println("\n Mistake ratio : " + mistakeRatio);
    if(mistakeRatio > 2 * risk)
      throw new RuntimeException("Apparently the mistake ratio is too high !!"
          + " Should be ~" + risk);
    System.out.println("\nTest passed successfully !");
  }

  private static void shuffle(Random r, int[] t) {
    for(int i=1; i<t.length; i++) {
      int j = r.nextInt(i+1);
      int a=t[i]; t[i]=t[j]; t[j]=a;
    }
  }
  
  public static void main(String[] args) {
    int nbOfExperiments = 10_000;
    double risk = 1.0 / 1_000;
    Random r = new Random();
    testMajority(nbOfExperiments, r, risk);
  }

}
