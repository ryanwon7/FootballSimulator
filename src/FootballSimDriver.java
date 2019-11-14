import archetype.*;
import plays.SmashRun;

/**
 *
 * @author Ryan
 */
public class FootballSimDriver {
    public static void main(String[] args) {
        /*String teamfile = "teams.txt";

        FootballSim game = new FootballSim(teamfile);
        game.play();*/
        int test = 0;
        int totScore = 0;
        while (test < 1000) {
            int drives = 0;
            int gameScore = 0;
            int yardline;
            while (drives < 12){
                yardline = (int)(Math.random()*((45-20)+1))+20;
                gameScore += Smashmouth.drive(yardline);
                System.out.println("");
                drives++;
            }
            totScore += gameScore;
            System.out.println("Total score over 12 drives is " + gameScore);
            test++;
        }
        double ppg = (double) totScore/test;
        System.out.println("This offense averaged " + ppg + "ppg over 12 drives.");
    }
}