package archetype;

import plays.*;

public class AirRaid extends Archetype {
    public static int drive(int yardline) {
        int driveYardage, gainedYards, firstdown;
        int currYardline = yardline;
        boolean possession = true;

        while (true) {
            firstdown = currYardline + 10;
            for(int down=1; down<=4; down++) {
                System.out.println(stateStatus(down, firstdown-currYardline, currYardline));
                gainedYards = choosePlay();
                if (gainedYards==10000) {
                    System.out.println("Pass is Intercepted!");
                    return 0;
                } else if (gainedYards==10001) {
                    System.out.println("Ball is fumbled! Turn over!");
                    return 0;
                } else {
                    currYardline += gainedYards;
                    if (down == 4) {
                        System.out.println("Turnover on downs.");
                        return 0;
                    } else if (currYardline >= 100) {
                        System.out.println("Touchdown!");
                        return 7;
                    } else if (currYardline >= firstdown) {
                        break;
                    } else if (down==3 && (firstdown-currYardline)<=2 && (100-currYardline) < 45) {
                        break;
                    } else if (down==3) {
                        System.out.println(stateStatus(4, firstdown-currYardline, currYardline));
                        if ((100-currYardline)<38) {
                            System.out.println("Attempting a field goal...");
                            if (FieldGoal.kick((100-currYardline))) {
                                return 3;
                            } else {
                                return 0;
                            }
                        } else {
                            System.out.println("Team is punting.");
                            return 0;
                        }
                    }
                }
            }
        }
    }
    private static int choosePlay() {
        int gained;
        int playNum = getRandomInteger(1, 100);
        if (playNum<=15) {
            gained = SmashRun.run();
            if (gained != 10000 && gained != 10001) {
                System.out.println("It's a smash run for a gain of " + gained);
            }
            return gained;
        } else if (playNum<=30) {
            gained = StretchRun.run();
            if (gained != 10000 && gained != 10001) {
                System.out.println("It's a stretch run for a gain of " + gained);
            }
            return gained;
        } else if (playNum<=55) {
            gained = ShortPass.pass();
            if (gained == 0) {
                System.out.println("Incomplete short pass!");
            } else if (gained != 10000 && gained != 10001) {
                System.out.println("It's a short pass for a gain of " + gained);
            }
            return gained;
        } else if (playNum<=80) {
            gained = MediumPass.pass();
            if (gained == 0) {
                System.out.println("Incomplete medium pass!");
            } else if (gained != 10000 && gained != 10001) {
                System.out.println("It's a medium pass for a gain of " + gained);
            }
            return gained;
        } else {
            gained = DeepPass.pass();
            if (gained == 0) {
                System.out.println("Incomplete deep pass!");
            } else if (gained != 10000 && gained != 10001) {
                System.out.println("It's a long pass for a gain of " + gained);
            }
            return gained;
        }
    }
}
