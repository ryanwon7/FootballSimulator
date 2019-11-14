package plays;

public class DeepPass extends Play {
    public static int pass() {
        int yardage;
        int randNum = Play.getRandomInteger(1, 100);
        if (randNum == 1) {
            System.out.println("Fumble!");
            return 10001;
        } else if (randNum <= 7) {
            System.out.println("Interception!");
            return 10000;
        } else if (randNum <= 26) {
            yardage = calcExp(15, 20);
        } else if (randNum <= 38) {
            yardage = getRandomInteger(21, 30);
        } else if (randNum <= 46) {
            yardage = calcExp(31, 45);
        } else if (randNum <= 98) {
            yardage = 0;
        } else {
            yardage = calcExp(46, 100);
        }
        return yardage;
    }
}
