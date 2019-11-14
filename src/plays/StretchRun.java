package plays;

public class StretchRun extends Play{
    public static int run() {
        int yardage;
        int randNum = getRandomInteger(1, 100);
        if (randNum == 1) {
            System.out.println("Fumble!");
            return 10001;
        } else if (randNum <= 9) {
            yardage = calcExp(-2, -4);
        } else if (randNum <= 19) {
            yardage = getRandomInteger(-1, 1);
        } else if (randNum <= 90) {
            yardage = getRandomInteger(2, 6);
        } else if (randNum <= 95) {
            yardage = getRandomInteger(7, 13);
        } else if (randNum <= 98) {
            yardage = calcExp(14, 20);
        } else {
            yardage = calcExp(21, 100);
        }
        return yardage;
    }
}
