package plays;

public class ShortPass extends Play{
    public static int run() {
        int yardage;
        int randNum = Play.getRandomInteger(1, 100);
        if (randNum == 1) {
            System.out.println("Fumble!");
            return 10001;
        } else if (randNum <= 3) {
            System.out.println("Interception!");
            return 10000;
        } else if (randNum <= 6) {
            yardage = calcExp(1, -2);
        } else if (randNum <= 73) {
            yardage = getRandomInteger(2, 6);
        } else if (randNum <= 78) {
            yardage = getRandomInteger(7, 15);
        } else if (randNum <= 99) {
            yardage = 0;
        } else {
            yardage = calcExp(16, 100);
        }
        return yardage;
    }
}
