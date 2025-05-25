package s12;
import java.util.Random;
public class RndWalk {
    public static void main(String [] args) {
        int nbOfExperiments = 100_000;
        int n=20;
        int leftChoicePercentage = 50;
        Random r = new Random();
        // 50-50
        System.out.printf("a) n=%d, leftPercentage=%d, nExperiments=%d = ",
                n, leftChoicePercentage, nbOfExperiments);
        System.out.println(rndWalkMirrorAvgLength(r, n, leftChoicePercentage,
                    nbOfExperiments));
        // 45-55
        leftChoicePercentage = 45;
        System.out.printf("b) n=%d, leftPercentage=%d, nExperiments=%d = ",
                n, leftChoicePercentage, nbOfExperiments);
        System.out.println(rndWalkMirrorAvgLength(r, n, leftChoicePercentage,
                    nbOfExperiments));
        // 55-45
        leftChoicePercentage = 55;
        System.out.printf("c) n=%d, leftPercentage=%d, nExperiments=%d = ",
                n, leftChoicePercentage, nbOfExperiments);
        System.out.println(rndWalkMirrorAvgLength(r, n, leftChoicePercentage,
                    nbOfExperiments));
    }
    //============================================================
    static double rndWalkMirrorAvgLength(Random r, int pointToReach, 
            int leftChoicePercentage,
            int nbOfExperiments) {
        int x;
        long nbOfSteps=0, total=0;
        for (int i=0; i<nbOfExperiments; i++) {
            x=0; nbOfSteps=0;
            while(x != pointToReach) {
                int prob = r.nextInt(1, 101);
                if (prob <= leftChoicePercentage) {
                    if (x != 0) x--;
                } else {
                    x++;
                }
                nbOfSteps++;
            }
            total += nbOfSteps;
        }
        return total/(double)nbOfExperiments;
    }
}
