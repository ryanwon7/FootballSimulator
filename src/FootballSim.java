import javax.swing.JOptionPane;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ryan Won
 */
public class FootballSim {
    private int homeScore, awayScore;
    private String homeTeam, awayTeam;
    private List<String> teams, textFile;
    public FootballSim(String teamfile) {
        homeScore = 0;
        awayScore = 0;
        homeTeam = "";
        awayTeam = "";
        retrieveTeams(teamfile);
    }
    public void play() {
        System.out.print(teams);
        homeTeam = JOptionPane.showInputDialog(null, "Please enter the Home Team.");
        awayTeam = JOptionPane.showInputDialog(null, "Please enter the Away Team.");
    }
    private void retrieveTeams(String teamfile) {
        int index = 0;
        try {
            textFile = Files.readAllLines(Paths.get(teamfile));
            System.out.println("here");
            for(String line : textFile) {
                if ((index + 4) % 5 == 0) {
                    teams.add(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Input file not found.");
            System.exit(1);
        }
    }
}