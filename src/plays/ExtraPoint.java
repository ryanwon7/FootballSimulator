package plays;

public class ExtraPoint extends Play {
    public static Boolean kick() {
        int kickNum = getRandomInteger(1, 100);
        if (kickNum <= 94) {
            return true;
        } else {
            return false;
        }
    }
}
