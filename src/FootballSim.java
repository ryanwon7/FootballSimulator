import javax.swing.JOptionPane;
import java.io.*;

/**
 *
 * @author Ryan Won
 */
public class FootballSim {
    private int homeScore, awayScore;
    private String homeTeam, awayTeam;
    public FootballSim() {
        homeScore = 0;
        awayScore = 0;
        homeTeam = "";
        awayTeam = "";
    }
    public void play() {
        homeTeam = JOptionPane.showInputDialog(null, "Please enter the Home Team.");
        awayTeam = JOptionPane.showInputDialog(null, "Please enter the Away Team.");
    }
}