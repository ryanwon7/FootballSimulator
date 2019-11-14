package plays;

public class FieldGoal extends Play {
    public static boolean kick(int yardline) {
        int fgDistance = yardline + 17;
        int randNum = getRandomInteger(1, 100);
        if (fgDistance <= 29) {
            return fgAttempt29Below(randNum, fgDistance);
        } else if (fgDistance <= 39) {
            return fgAttempt39Below(randNum, fgDistance);
        } else if (fgDistance <= 49) {
            return fgAttempt49Below(randNum, fgDistance);
        } else {
            return fgAttempt50Plus(randNum, fgDistance);
        }
    }
    private static boolean fgAttempt29Below(int kickNum, int distance) {
        if (kickNum <= 98) {
            System.out.println("The kick is good from " + distance + " yards out!");
            return true;
        } else {
            System.out.println("The kick is no good from " + distance + " yards out!");
            return false;
        }
    }
    private static boolean fgAttempt39Below(int kickNum, int distance) {
        if (kickNum <= 94) {
            System.out.println("The kick is good from " + distance + " yards out!");
            return true;
        } else {
            System.out.println("The kick is no good from " + distance + " yards out!");
            return false;
        }
    }
    private static boolean fgAttempt49Below(int kickNum, int distance) {
        if (kickNum <= 76) {
            System.out.println("The kick is good from " + distance + " yards out!");
            return true;
        } else {
            System.out.println("The kick is no good from " + distance + " yards out!");
            return false;
        }
    }
    private static boolean fgAttempt50Plus(int kickNum, int distance) {
        if (kickNum <= 65) {
            System.out.println("The kick is good from " + distance + " yards out!");
            return true;
        } else {
            System.out.println("The kick is no good from " + distance + " yards out!");
            return false;
        }
    }
}
