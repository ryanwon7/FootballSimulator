package plays;

public class StretchRun extends Play{
    public static int run(int strength) {
        int yardage;
        int randNum = getRandomInteger(1, 100);
        if (randNum == 1) {
            System.out.println("Fumble!");
            return 10001;
        } else if (randNum <= 12) {
            yardage = calcExp(-2+strength, -4);
        } else if (randNum <= 23) {
            yardage = getRandomInteger(-1, 1+strength);
        } else if (randNum <= 91) {
            yardage = getRandomInteger(2, 6+strength);
        } else if (randNum <= 95) {
            yardage = getRandomInteger(7, 13+strength);
        } else if (randNum <= 98) {
            yardage = calcExp(14, 20+strength);
        } else {
            yardage = calcExp(21, 100);
        }
        return yardage;
    }
}
