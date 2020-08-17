import archetype.AirRaid;
import archetype.Smashmouth;
import archetype.Spread;
import archetype.WestCoast;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import javax.swing.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;


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
            "Minnesota Vikings", "New England Patriots", "New Orleans Saints", "New York Giants", "New York Jets", "Las Vegas Raiders", "Philadelphia Eagles",
            "Pittsburgh Steelers", "San Francisco 49ers", "Seattle Seahawks", "Tampa Bay Buccaneers", "Tennessee Titans", "Washington Redskins"};

    //Variable Initializers
    private int homeScore, awayScore, homeOffStr, awayOffStr;
    private double homeOff, homeDef, homeSt, awayOff, awayDef, awaySt;
    private String jsonFile, homeTeam, awayTeam, homeAbrev, awayAbrev, homeStyle, awayStyle;
    private boolean ot = false;

    // Game Variables
    private String possession = "home";
    private int lastYardLine = 25;
    private int homeQ1 = 0, homeQ2 = 0, homeQ3 = 0, homeQ4 = 0, homeOt = 0, awayQ1 = 0, awayQ2 = 0, awayQ3 = 0, awayQ4 = 0, awayOt = 0;

    //System Print settings
    PrintStream originalStream = System.out;
    PrintStream dummyStream = new PrintStream(new OutputStream(){
        public void write(int b) {

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
        homeOff = ((Number) home.get("off")).doubleValue();
        homeDef = ((Number) home.get("def")).doubleValue();
        homeSt = ((Number) home.get("st")).doubleValue();
        homeStyle = home.get("style").toString();
        JSONObject away = (JSONObject) jo.get(awayTeam.replace(" ", ""));
        awayAbrev = away.get("abrev").toString();
        awayOff = ((Number) away.get("off")).doubleValue();
        awayDef = ((Number) away.get("def")).doubleValue();
        awaySt = ((Number) away.get("st")).doubleValue();
        awayStyle = away.get("style").toString();
    }
    private void createGame() {
        homeScore = 0;
        awayScore = 0;
        homeOffStr = (int) Math.ceil((homeOff + 1 - awayDef)/4);
        awayOffStr = (int) Math.ceil((awayOff - homeDef-1)/4);
        for(int quarter = 1; quarter<=quarters; quarter++) {
            System.out.println("\nStart of the " + quarter + " quarter.");
            if (quarter==3) {
                possession = "away";
                lastYardLine = 25;
            }
            for (int possessions = 1; possessions<=5; possessions++) {
                if (possession.equals("home")) {
                    System.out.println("\n" + homeAbrev + " has possession.");
                    doDrive(homeStyle, lastYardLine, homeOffStr);
                } else {
                    System.out.println("\n" + awayAbrev + " has possession.");
                    doDrive(awayStyle,lastYardLine, awayOffStr);
                }
            }
            getQuarterScore(quarter);
            if (quarter ==4 && homeScore == awayScore) {
                System.out.println("\nStart of the overtime.");
                ot = true;
                lastYardLine=25;
                int headsTails = (int)(Math.random() * (2) + 1);
                if (headsTails == 1) {
                    possession = "home";
                } else {
                    possession = "away";
                }
                for (int drives = 1; drives <= 7; drives++) {
                    if (possession.equals("home")) {
                        System.out.println("\n" + homeAbrev + " has possession.");
                        doDrive(homeStyle, lastYardLine, homeOffStr);
                    } else {
                        System.out.println("\n" + awayAbrev + " has possession.");
                        doDrive(awayStyle,lastYardLine, awayOffStr);
                    }
                    if (drives == 1 && (homeScore == awayScore + 7 || homeScore + 7 == awayScore)) {
                        getQuarterScore(5);
                        break;
                    } else if (homeScore != awayScore) {
                        getQuarterScore(5);
                        break;
                    }
                }
            }
        }
        printSummary();
    }
    private void doDrive(String driveType, int yardLine, int strength) {
        HashMap<String, String> returnData;
        if (driveType.equals("Air Raid")) {
            returnData =  AirRaid.drive(yardLine, strength);
        } else if (driveType.equals("Smashmouth")) {
            returnData =  Smashmouth.drive(yardLine, strength);
        } else if (driveType.equals("Spread")) {
            returnData =  Spread.drive(yardLine, strength);
        } else {
            returnData =  WestCoast.drive(yardLine, strength);
        }
        if (returnData.get("Code").equals("Interception") || returnData.get("Code").equals("Fumble") || returnData.get("Code").equals("Turnover on Downs") || returnData.get("Code").equals("Punt")) {
            setTurnover();
            lastYardLine = Integer.parseInt(returnData.get("Yardline"));
        } else if (returnData.get("Code").equals("Touchdown") || returnData.get("Code").equals("Field Goal")) {
            addPoints(Integer.parseInt(returnData.get("Points")));
            System.out.println("\n" + homeAbrev + " " + homeScore + " - " + awayScore + " " + awayAbrev);
            setTurnover();
            lastYardLine = Integer.parseInt(returnData.get("Yardline"));
        }
    }
    private void setTurnover() {
        if (possession.equals("home")) {
            possession = "away";
        } else {
            possession = "home";
        }
    }
    private void addPoints(int points) {
        if (possession.equals("home")) {
            homeScore += points;
        } else {
            awayScore += points;
        }
    }
    private void getQuarterScore(int quarter) {
        if (quarter == 1) {
            homeQ1 = homeScore;
            awayQ1 = awayScore;
        } else if (quarter == 2) {
            homeQ2 = homeScore - homeQ1;
            awayQ2 = awayScore - awayQ1;
        } else if (quarter == 3) {
            homeQ3 = homeScore - homeQ1 - homeQ2;
            awayQ3 = awayScore - awayQ1 - awayQ2;
        } else if (quarter == 4){
            homeQ4 = homeScore - homeQ1 - homeQ2 - homeQ3;
            awayQ4 = awayScore - awayQ1 - awayQ2 - awayQ3;
        } else {
            homeOt = homeScore - homeQ1 - homeQ2 - homeQ3 - homeQ4;
            awayOt = awayScore - awayQ1 - awayQ2 - awayQ3 - awayQ4;
        }
    }
    private void printSummary() {
        System.setOut(originalStream);
        System.out.println("\nFinal Score:\n" + homeAbrev + " " + homeScore + " - " + awayScore + " " + awayAbrev);
        if (!ot) {
            System.out.println("\nBox Score");
            System.out.println("-----------------------------------------------------------------------------");
            System.out.printf("%20s %10s %10s %10s %10s %10s", "TEAM", "Q1", "Q2", "Q3", "Q4", "FIN");
            System.out.println();
            System.out.println("-----------------------------------------------------------------------------");
            System.out.format("%20s %10s %10s %10s %10s %10s",
                    homeTeam, homeQ1, homeQ2, homeQ3, homeQ4, homeScore);
            System.out.println();
            System.out.format("%20s %10s %10s %10s %10s %10s",
                    awayTeam, awayQ1, awayQ2, awayQ3, awayQ4, awayScore);
            System.out.println();
        } else {
            System.out.println("\nBox Score");
            System.out.println("-----------------------------------------------------------------------------------------");
            System.out.printf("%20s %10s %10s %10s %10s %10s %10s", "TEAM", "Q1", "Q2", "Q3", "Q4", "OT", "FIN");
            System.out.println();
            System.out.println("-----------------------------------------------------------------------------------------");
            System.out.format("%20s %10s %10s %10s %10s %10s %10s ",
                    homeTeam, homeQ1, homeQ2, homeQ3, homeQ4, homeOt, homeScore);
            System.out.println();
            System.out.format("%20s %10s %10s %10s %10s %10s %10s",
                    awayTeam, awayQ1, awayQ2, awayQ3, awayQ4, awayOt, awayScore);
            System.out.println();
        }

    }
}