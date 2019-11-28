package plays;

public class SmashRun extends Play{
    public static int run(int strength) {
        int yardage;
        int randNum = getRandomInteger(1, 100);
        if (randNum == 1) {
            System.out.println("Fumble!");
            return 10001;
        } else if (randNum <= 4) {
            yardage = calcExp(-2+strength, -5);
        } else if (randNum <= 20) {
            yardage = getRandomInteger(-1, 2+strength);
        } else if (randNum <= 93) {
            yardage = getRandomInteger(3, 6+strength);
        } else if (randNum <= 97) {
            yardage = getRandomInteger(7, 12+strength);
        } else if (randNum <= 99) {
            yardage = calcExp(13, 20+strength);
        } else {
            yardage = calcExp(21, 100);
        }
        return yardage;
    }
}
