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
        } else if (randNum <= 15) {
            yardage = getRandomInteger(-1, 2);
        } else if (randNum <= 92) {
            yardage = getRandomInteger(3, 6);
        } else if (randNum <= 96) {
            yardage = getRandomInteger(7, 12);
        } else if (randNum <= 99) {
            yardage = calcExp(13, 20);
        } else {
            yardage = calcExp(21, 100);
        }
        return yardage;
    }
}
