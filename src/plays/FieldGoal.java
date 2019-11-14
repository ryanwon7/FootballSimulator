package plays;

public class FieldGoal extends Play {
    public static boolean kick(int yardline) {
        int fgDistance = yardline + 17;
        int randNum = getRandomInteger(1, 100);
        if (fgDistance <= 29) {
            return fgAttempt29Below(randNum);
        } else if (fgDistance <= 39) {
            return fgAttempt39Below(randNum);
        } else if (fgDistance <= 49) {
            return fgAttempt49Below(randNum);
        } else {
            return fgAttempt50Plus(randNum);
        }
    }
    private static boolean fgAttempt29Below(int kickNum) {
        if (kickNum <= 98) {
            return true;
        } else {
            return false;
        }
    }
    private static boolean fgAttempt39Below(int kickNum) {
        if (kickNum <= 94) {
            return true;
        } else {
            return false;
        }
    }
    private static boolean fgAttempt49Below(int kickNum) {
        if (kickNum <= 76) {
            return true;
        } else {
            return false;
        }
    }
    private static boolean fgAttempt50Plus(int kickNum) {
        if (kickNum <= 65) {
            return true;
        } else {
            return false;
        }
    }
}
