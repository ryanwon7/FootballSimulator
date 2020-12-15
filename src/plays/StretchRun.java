package plays;

public class StretchRun extends Play{
    public static int run(int strength) {
        int yardage;
        int randNum = getRandomInteger(1, 200);
        if (randNum == 1) {
            System.out.println("Fumble!");
            return 10001;
        } else if (randNum <= 24) {
            yardage = calcExp(-2+strength, -4);
        } else if (randNum <= 46) {
            yardage = getRandomInteger(-1, (int) (1 + Math.ceil(strength/2)));
        } else if (randNum <= 185) {
            yardage = getRandomInteger(2, (int) (6 + Math.ceil(strength/2)));
        } else if (randNum <= 193) {
            yardage = getRandomInteger(6, 13+strength);
        } else if (randNum <= 197) {
            yardage = calcExp(14, 20+strength);
        } else {
            yardage = calcExp(21, 100);
        }
        return yardage;
    }
}
