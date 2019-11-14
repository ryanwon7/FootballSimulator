package archetype;

import plays.*;

public class Archetype {
    protected static int getRandomInteger(int min, int max){
        int x = (int)(Math.random()*((max-min)+1))+min;
        return x;
    }
    protected static String getYardageString(int yardline){
        if (yardline<50){
            return ("own " + yardline);
        } else {
            return "opp " + (100 - yardline);
        }
    }
    protected static String stateStatus(int down, int toGo, int yardline) {
        String downline;
        if (down == 1) {
            downline = "1st and " + toGo + " on ";
        } else if (down ==2) {
            downline = "2nd and " + toGo + " on ";
        } else if (down==3) {
            downline = "3rd and " + toGo + " on ";
        } else if (down==4) {
            downline = "4th and " + toGo + " on ";
        } else {
            downline = "Turnover on the ";
        }
        return (downline + getYardageString(yardline));
    }
}
