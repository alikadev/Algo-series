package s12;
import java.util.Random;
public class RndTriangle {
  public static void main(String[] args) {
    int nbOfExperiments = 100_000;
    Random r = new Random();
    if (args.length>0) nbOfExperiments = Integer.parseInt(args[0]);
    System.out.println(rndTriangleAvgArea(r, nbOfExperiments));
  }
  //============================================================
  public static double rndTriangleAvgArea(Random r, int nbOfExperiments) {
    return 0.0; // TODO
  }
}