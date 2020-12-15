package plays;

public class SmashRun extends Play{
    public static int run(int strength) {
        int yardage;
        int randNum = getRandomInteger(1, 200);
        if (randNum == 1) {
            System.out.println("Fumble!");
            return 10001;
        } else if (randNum <= 8) {
            yardage = calcExp(-2+strength, -4);
        } else if (randNum <= 60) {
            yardage = getRandomInteger(-1, (int) (1 + Math.ceil(strength/2)));
        } else if (randNum <= 190) {
            yardage = getRandomInteger(3, (int) (4 + Math.ceil(strength/2)));
        } else if (randNum <= 195) {
            yardage = getRandomInteger(6, 12+strength);
        } else if (randNum <= 199) {
            yardage = calcExp(13, 20+strength);
        } else {
            yardage = calcExp(21, 100);
        }
        return yardage;
    }
}
