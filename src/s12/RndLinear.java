package s12;
import java.util.Random;
public class RndLinear {
  public static void main(String [] args) {
    int nbOfExperiments = 1_000_000;
    int n = 10;
    Random r = new Random();
    testLinear(r, n, nbOfExperiments);
  }
  //============================================================
  // PRE: n>0
  // POST: 0 < result <= n
  //       with a preference for "high values", in a very precise sense 
  // Example : with n==100, the output will be…
  //                   …  50 " 2x more often than" 25
  //                   … 100 "20x more often than"  5
  //                   …  70 " 7x more often than" 10 ...
  public static int rndLinear(Random r, int n) {
    int sum = n * (n + 1) / 2, x = r.nextInt(sum), acc = 0;
    for (int i = 1; i <= n; i++) if ((acc += i) > x) return i;
    return 0;
  }
  //============================================================
  static void testLinear(Random r, int n, int nbOfExperiments) {
    int[] t = new int[n + 1];
    for (int i = 0; i < nbOfExperiments; i++)
      t[rndLinear(r, n)]++;
    System.out.println(0 + " : " + t[0]);
    for (int i = 1; i < n + 1; i++)
      System.out.println(i + " : " + (double) t[i] / nbOfExperiments);
  }
}
