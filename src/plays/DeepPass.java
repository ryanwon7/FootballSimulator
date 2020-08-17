package plays;

public class DeepPass extends Play {
    public static int pass(int strength) {
        int yardage;
        int randNum = Play.getRandomInteger(1, 200);
        if (randNum == 1) {
            System.out.println("Fumble!");
            return 10001;
        } else if (randNum <= 10) {
            System.out.println("Interception!");
            return 10000;
        } else if (randNum <= 50) {
            yardage = calcExp(12, 18+strength);
        } else if (randNum <= 74) {
            yardage = getRandomInteger(19, 27+strength);
        } else if (randNum <= 86) {
            yardage = calcExp(28, 40+strength);
        } else if (randNum <= 198) {
            yardage = 0;
        } else {
            yardage = calcExp(41, 100);
        }
        return yardage;
    }
}
