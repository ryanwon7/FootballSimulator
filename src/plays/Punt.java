package plays;

public class Punt extends Play {
    public static int kick(int startYardline) {
        int randNum = getRandomInteger(1, 100);
        if (100-startYardline <= 50) {
            if (randNum <= 35) { //35% chance of a touchback
                System.out.println("Touchback. Ball is placed at the 20 yard line.");
                return 20;
            } else {
                int yardage = calcExp(20, 99-startYardline);
                System.out.println("Ball is punted for " + yardage + " yards. Ball is placed at the " + (100 - (startYardline+yardage)) + " yard line.");
                return (100 - (startYardline + yardage));
            }
        } else if (100-startYardline <= 65) {
            if (randNum <= 20) { //20% chance of touchback
                System.out.println("Touchback. Ball is placed at the 20 yard line.");
                return 20;
            } else if (randNum <= 50) {//30% chance of being for 40+ yards
                int yardage = calcExp(40, 99-startYardline);
                System.out.println("Ball is punted for " + yardage + " yards. Ball is placed at the " + (100 - (startYardline+yardage)) + " yard line.");
                return (100 - (startYardline + yardage));
            } else {
                int yardage = calcExp(25, 99-startYardline); //50% chance of 25+ yards
                System.out.println("Ball is punted for " + yardage + " yards. Ball is placed at the " + (100 - (startYardline+yardage)) + " yard line.");
                return (100 - (startYardline + yardage));
            }
        } else {
            if (randNum <= 30) {//30% chance of being for 40-60 yards
                int yardage = calcExp(40, 60);
                System.out.println("Ball is punted for " + yardage + " yards. Ball is placed at the " + (100 - (startYardline + yardage)) + " yard line.");
                return (100 - (startYardline + yardage));
            } else {
                int yardage = calcExp(30, 50); //50% chance of 30-50 yards
                System.out.println("Ball is punted for " + yardage + " yards. Ball is placed at the " + (100 - (startYardline + yardage)) + " yard line.");
                return (100 - (startYardline + yardage));
            }
        }
    }
}
