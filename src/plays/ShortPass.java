package plays;

public class ShortPass extends Play{
    public static int pass(int strength) {
        int yardage;
        int randNum = Play.getRandomInteger(1, 100);
        if (randNum == 1) {
            System.out.println("Fumble!");
            return 10001;
        } else if (randNum <= 3) {
            System.out.println("Interception!");
            return 10000;
        } else if (randNum <= 6) {
            yardage = calcExp(1+strength, -2);
        } else if (randNum <= 59) {
            yardage = getRandomInteger(2, 6+strength);
        } else if (randNum <= 76) {
            yardage = calcExp(7, 15+strength);
        } else if (randNum <= 99) {
            yardage = 0;
        } else {
            yardage = calcExp(16, 100);
        }
        return yardage;
    }
}
