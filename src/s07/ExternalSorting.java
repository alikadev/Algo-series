package s07;
import java.io.*;

public class ExternalSorting {
  public static void main(String[] args) {
    // NOTE: by default, the "current directory" is the folder of the project (not
    // the folder with the source code!). Either put your test file there, or change
    // the filename below with an appropriate (eg absolute) path.
    String filename = "myFile.txt";
    if (args.length > 0) filename = args[0];
    monotonySort(filename);
  }
  //------------------------------------------------------------
  // ------------------------------------------------------------
  private static boolean isMonotone(String crt, String prev) {
    if (crt  == null) return false;
    if (prev == null) return true;
    return (crt.compareTo(prev) >= 0);
  }
  // ------------------------------------------------------------
  private static void merge(String a, String b, String c) throws IOException {
    BufferedReader fa = new BufferedReader(new FileReader(a));
    BufferedReader fb = new BufferedReader(new FileReader(b));
    PrintWriter    fc = new PrintWriter(new FileWriter(c));
    String sa = fa.readLine(); String saPrev = null;
    String sb = fb.readLine(); String sbPrev = null;
    while (sa != null || sb != null) {
      // if needed, go to the next two monotone sequences
      if (!isMonotone(sa, saPrev) && !isMonotone(sb, sbPrev)) {
        saPrev = sa; sbPrev = sb;
      }
      if (!isMonotone(sb, sbPrev) ||
           isMonotone(sa, saPrev) && sa.compareTo(sb) <= 0) {
        fc.println(sa); saPrev = sa; sa = fa.readLine();
      } else {
        fc.println(sb); sbPrev = sb; sb = fb.readLine();
      }
    }
    fa.close(); fb.close(); fc.close();
    // A possible pseudocode:
    //   while there are remaining elements to process
    //     if both monotonies are ongoing
    //       choose the smallest of the two next elements
    //     if one monotony is finished
    //       take an element from the other monotony
    //     if both monotonies are finished
    //       start two fresh new ones
  }
  // ------------------------------------------------------------
  // Splits the successive monotonies found in file named a
  // into the new files named b and c
  // Returns the number of monotonies
  private static int split(String a, String b, String c) throws IOException {
    BufferedReader fa = new BufferedReader(new FileReader(a));
    PrintWriter    fb = new PrintWriter   (new FileWriter(b));
    PrintWriter    fc = new PrintWriter   (new FileWriter(c));
    return 0; // TODO
  }
  // ------------------------------------------------------------
  public static void monotonySort(String filename) {
    String tmp1 = filename + ".tmp1"; // somewhat...
    String tmp2 = filename + ".tmp2"; // ...dangerous...
    int nMonotonies;
    try {
      nMonotonies = split(filename, tmp1, tmp2);
      while (nMonotonies > 1) {
        merge(tmp1, tmp2, filename);
        nMonotonies = split(filename, tmp1, tmp2);
      }
    } catch (IOException e) {
      System.out.println(e);
    }
  }
  // ------------------------------------------------------------
}
