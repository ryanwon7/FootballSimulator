/**
 *
 * @author Ryan
 */
public class FootballSimDriver {
    public static void main(String[] args) throws Exception {
        String teamfile = SimUtil.getProperties("sim.properties").getProperty("latest.file");

        FootballSim game = new FootballSim(teamfile, "yes");
        game.play();
    }
}