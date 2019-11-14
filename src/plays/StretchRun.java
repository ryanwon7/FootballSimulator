package plays;

public class StretchRun extends Play{
    public static int run() {
        int yardage;
        int randNum = getRandomInteger(1, 100);
        if (randNum == 1) {
            System.out.println("Fumble!");
            return 10001;
        } else if (randNum <= 9) {
            yardage = calcExp(-1, -4);
        } else if (randNum <= 23) {
            yardage = getRandomInteger(0, 2);
        } else if (randNum <= 93) {
            yardage = getRandomInteger(3, 5);
        } else if (randNum <= 98) {
            yardage = getRandomInteger(6, 10);
        } else {
            yardage = calcExp(11, 20);
        }
        return yardage;
    }
}
