package s07;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class ExternalSorting {
  public static void main(String[] args) {
    String filename = "src/s07/myFile.txt";
    Path backupPath = new File(filename + ".bkp").toPath();
    try {
      // Reload backup if exists
      if (Files.exists(backupPath)) {
        Files.copy(backupPath, new File(filename).toPath(), StandardCopyOption.REPLACE_EXISTING);
      }
      // Create the backup for the next run
      Files.copy(new File(filename).toPath(), backupPath, StandardCopyOption.REPLACE_EXISTING);
    } catch (IOException e) {
      System.err.println("Failed to create and/or load backup");
      return;
    }
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
    // Working files
    BufferedReader fa = new BufferedReader(new FileReader(a));
    PrintWriter fb = new PrintWriter(new FileWriter(b));
    PrintWriter fc = new PrintWriter(new FileWriter(c));

    int monoCnt = 0; ///< The # of monotones
    String prev = fa.readLine(); ///< Previous line
    for (String cur; prev != null; prev = cur) {
      // Write the previous element in the right file (fb = pair, fc = odd)
      ((monoCnt % 2 == 0) ? fb : fc).println(prev);
      // Take the next one, verify if it is monotone
      cur = fa.readLine();
      if (!isMonotone(cur, prev))
        monoCnt++;
    }

    fa.close();
    fb.close();
    fc.close();
    return monoCnt;
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
