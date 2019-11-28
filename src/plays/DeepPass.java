package plays;

public class DeepPass extends Play {
    public static int pass(int strength) {
        int yardage;
        int randNum = Play.getRandomInteger(1, 100);
        if (randNum == 1) {
            System.out.println("Fumble!");
            return 10001;
        } else if (randNum <= 7) {
            System.out.println("Interception!");
            return 10000;
        } else if (randNum <= 25) {
            yardage = calcExp(15, 20+strength);
        } else if (randNum <= 36) {
            yardage = getRandomInteger(21, 30+strength);
        } else if (randNum <= 43) {
            yardage = calcExp(31, 45+strength);
        } else if (randNum <= 98) {
            yardage = 0;
        } else {
            yardage = calcExp(46, 100);
        }
        return yardage;
    }
}
