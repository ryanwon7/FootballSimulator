import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import javax.swing.*;
import java.io.*;


//https://stackoverflow.com/questions/8363493/hiding-system-out-print-calls-of-a-class

/**
 *
 * @author Ryan Won
 */
public class FootballSim {
    //Game Settings
    private final int quarters = 4;
    private final String[] teams = {"Arizona Cardinals", "Atlanta Falcons", "Baltimore Ravens", "Buffalo Bills", "Carolina Panthers", "Chicago Bears",
            "Cincinnati Bengals", "Cleveland Browns", "Dallas Cowboys", "Denver Broncos", "Detroit Lions", "Green Bay Packers", "Houston Texans",
            "Indianapolis Colts", "Jacksonville Jaguars", "Kansas City Chiefs", "Los Angeles Chargers", "Los Angeles Rams", "Miami Dolphins",
            "Minnesota Vikings", "New England Patriots", "New Orleans Saints", "New York Giants", "New York Jets", "Oakland Raiders", "Philadelphia Eagles",
            "Pittsburgh Steelers", "San Francisco 49ers", "Seattle Seahawks", "Tampa Bay Buccaneers", "Tennessee Titans", "Washington Redskins"};

    //
    private int homeScore, awayScore, homeOff, homeDef, awayOff, awayDef;
    private String jsonFile, homeTeam, awayTeam, homeAbrev, awayAbrev, homeStyle, awayStyle;

    //System print settings
    PrintStream originalStream = System.out;
    PrintStream dummyStream = new PrintStream(new OutputStream(){
        public void write(int b) {
            // NO-OP
        }
    });

    public FootballSim(String teamfile, String stream) {
        homeScore = 0;
        awayScore = 0;
        if (stream.equals("yes")) {
            System.setOut(originalStream);
        } else {
            System.setOut(dummyStream);
        }
        homeTeam = "";
        awayTeam = "";
        jsonFile = System.getProperty("user.dir") + File.separator + "resources" + File.separator + teamfile;
    }
    public void play() throws Exception{
        homeTeam = (String) JOptionPane.showInputDialog(null, "Please enter the Home Team.", "Choose Home Team", JOptionPane.PLAIN_MESSAGE, null, teams, teams[0]);
        awayTeam = (String) JOptionPane.showInputDialog(null, "Please enter the Away Team.", "Choose Away Team", JOptionPane.PLAIN_MESSAGE, null, teams, teams[0]);
        setTeamAttr();

        createGame();
    }
    private void setTeamAttr() throws IOException, ParseException {
        Object obj = new JSONParser().parse(new FileReader(jsonFile));
        JSONObject jo = (JSONObject) obj;
        JSONObject home = (JSONObject) jo.get(homeTeam.replace(" ", ""));
        homeAbrev = home.get("abrev").toString();
        homeOff = ((Number) home.get("off")).intValue();
        homeDef = ((Number) home.get("def")).intValue();
        homeStyle = home.get("style").toString();
        JSONObject away = (JSONObject) jo.get(awayTeam.replace(" ", ""));
        awayAbrev = away.get("abrev").toString();
        awayOff = ((Number) away.get("off")).intValue();
        awayDef = ((Number) away.get("def")).intValue();
        awayStyle = away.get("style").toString();
    }
    private void createGame() {
    }
}