package plays;

public class MediumPass extends Play {
    public static int pass(int strength) {
        int yardage;
        int randNum = Play.getRandomInteger(1, 200);
        if (randNum == 1) {
            System.out.println("Fumble!");
            return 10001;
        } else if (randNum <= 7) {
            System.out.println("Interception!");
            return 10000;
        } else if (randNum <= 68 ) {
            yardage = calcExp(5, 10+strength);
        } else if (randNum <= 108) {
            yardage = getRandomInteger(11, 16+strength);
        } else if (randNum <= 117) {
            yardage = getRandomInteger(17, 27+strength);
        } else if (randNum <= 198) {
            yardage = 0;
        } else {
            yardage = calcExp(28, 100);
        }
        return yardage;
    }
}
