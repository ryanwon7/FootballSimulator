package plays;

public class MediumPass extends Play {
    public static int pass() {
        int yardage;
        int randNum = Play.getRandomInteger(1, 100);
        if (randNum == 1) {
            System.out.println("Fumble!");
            return 10001;
        } else if (randNum <= 5) {
            System.out.println("Interception!");
            return 10000;
        } else if (randNum <= 35) {
            yardage = calcExp(5, 10);
        } else if (randNum <= 55) {
            yardage = getRandomInteger(11, 15);
        } else if (randNum <= 60) {
            yardage = getRandomInteger(16, 26);
        } else if (randNum <= 99) {
            yardage = 0;
        } else {
            yardage = calcExp(26, 100);
        }
        return yardage;
    }
}
