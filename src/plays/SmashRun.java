package plays;

public class SmashRun extends Play{
    public static int run() {
        int yardage;
        int randNum = getRandomInteger(1, 100);
        if (randNum == 1) {
            System.out.println("Fumble!");
            return 10001;
        } else if (randNum <= 4) {
            yardage = calcExp(-2, -5);
        } else if (randNum <= 10) {
            yardage = getRandomInteger(-1, 1);
        } else if (randNum <= 92) {
            yardage = getRandomInteger(2, 5);
        } else if (randNum <= 99) {
            yardage = getRandomInteger(6, 10);
        } else {
            yardage = calcExp(11, 20);
        }
        return yardage;
    }
}
