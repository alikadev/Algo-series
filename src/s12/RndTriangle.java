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
    double sum = 0.0;
    for (int i = 0; i < nbOfExperiments; i++) {
        final double x1 = r.nextDouble();
        final double x2 = r.nextDouble();
        final double x3 = r.nextDouble();
        final double y1 = r.nextDouble();
        final double y2 = r.nextDouble();
        final double y3 = r.nextDouble();
        sum += 0.5 * Math.abs(x1*(y2-y3) + x2*(y3-y1) + x3*(y1-y2));
    }
    return sum / nbOfExperiments;
  }
}
