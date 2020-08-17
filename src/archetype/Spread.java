package archetype;

import plays.*;

import java.util.HashMap;
import java.util.Map;

public class Spread extends Archetype {
    public static HashMap<String, String> drive(int yardline, int strength) {
        HashMap<String, String> returnData= new HashMap<String, String>();
        int gainedYards, firstdown;
        int currYardline = yardline;

        while (true) {
            if (currYardline+10 > 100) {
                firstdown = 100;
            } else {
                firstdown = currYardline + 10;
            }
            for(int down=1; down<=4; down++) {
                System.out.println(stateStatus(down, firstdown-currYardline, currYardline));
                gainedYards = choosePlay(strength);
                if (gainedYards==10000) {
                    System.out.println("Pass is Intercepted!");
                    returnData.put("Code", "Interception");
                    returnData.put("Points", "0");
                    returnData.put("Yardline", "" + (100-currYardline));
                    return returnData;
                } else if (gainedYards==10001) {
                    System.out.println("Ball is fumbled! Turn over!");
                    returnData.put("Code", "Fumble");
                    returnData.put("Points", "0");
                    returnData.put("Yardline", "" + (100-currYardline));
                    return returnData;
                } else {
                    currYardline += gainedYards;
                    if (currYardline >= 100) {
                        System.out.println("Touchdown!");
                        returnData.put("Code", "Touchdown");
                        returnData.put("Points", "7");
                        returnData.put("Yardline", "25");
                        return returnData;
                    } else if (currYardline >= firstdown) {
                        break;
                    } else if (down == 4) {
                        System.out.println("Turnover on Downs.");
                        returnData.put("Code", "Turnover on Downs");
                        returnData.put("Points", "0");
                        returnData.put("Yardline", "" + (100-currYardline));
                        return returnData;
                    } else if (down==3 && (firstdown-currYardline)<=2 && (100-currYardline) < 45) {
                        continue;
                    } else if (down==3) {
                        System.out.println(stateStatus(4, firstdown-currYardline, currYardline));
                        int oppLine = 100 - currYardline;
                        if (oppLine<38) {
                            System.out.println("Attempting a field goal...");
                            if (FieldGoal.kick((100-currYardline))) {
                                returnData.put("Code", "Field Goal");
                                returnData.put("Points", "3");
                                returnData.put("Yardline", "25");
                                return returnData;
                            } else {
                                returnData.put("Code", "Turnover on Downs");
                                returnData.put("Points", "0");
                                returnData.put("Yardline", "" + oppLine);
                                return returnData;
                            }
                        } else {
                            System.out.println("Team is punting.");
                            returnData.put("Code", "Punt");
                            returnData.put("Points", "0");
                            returnData.put("Yardline", "" + Punt.kick(currYardline));
                            return returnData;
                        }
                    }
                }
            }
        }
    }
    private static int choosePlay(int strength) {
        int gained;
        int playNum = getRandomInteger(1, 100);
        if (playNum<=20) {
            gained = SmashRun.run(strength+1);
            if (gained != 10000 && gained != 10001) {
                System.out.println("It's a smash run for a gain of " + gained);
            }
            return gained;
        } else if (playNum<=40) {
            gained = StretchRun.run(strength+1);
            if (gained != 10000 && gained != 10001) {
                System.out.println("It's a stretch run for a gain of " + gained);
            }
            return gained;
        } else if (playNum<=65) {
            gained = ShortPass.pass(strength);
            if (gained == 0) {
                System.out.println("Incomplete short pass!");
            } else if (gained != 10000 && gained != 10001) {
                System.out.println("It's a short pass for a gain of " + gained);
            }
            return gained;
        } else if (playNum<=85) {
            gained = MediumPass.pass(strength+1);
            if (gained == 0) {
                System.out.println("Incomplete medium pass!");
            } else if (gained != 10000 && gained != 10001) {
                System.out.println("It's a medium pass for a gain of " + gained);
            }
            return gained;
        } else {
            gained = DeepPass.pass(strength);
            if (gained == 0) {
                System.out.println("Incomplete deep pass!");
            } else if (gained != 10000 && gained != 10001) {
                System.out.println("It's a long pass for a gain of " + gained);
            }
            return gained;
        }
    }
}
