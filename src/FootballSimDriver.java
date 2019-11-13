/**
 *
 * @author Ryan
 */
public class FootballSimDriver {
    public static void main(String[] args) {
        String teamfile = "teams.txt";

        FootballSim game = new FootballSim(teamfile);
        game.play();
    }
}