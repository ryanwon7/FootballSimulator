import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

public class UpdateStrengths {
    private static Map<String, String> teamNames = new HashMap<>();
    private static Map<String, String> teamStyles = new HashMap<>();

    private static String resourceDirectory = System.getProperty("user.dir") + File.separator + "resources" + File.separator;
    private static String jsonFile = resourceDirectory + "base_teams.json";

    public static void main(String[] args) throws IOException, ParseException, java.text.ParseException {
        if (checkUpdate()){
            System.out.println("Latest version of FPI strengths already exists. Exiting...");
            System.exit(0);
        } else {
            createMaps();
            writeNewObject();
            System.out.println("Successfully updated team strengths. New file created under FootballSimulator/resources/");
        }
    }
    private static boolean checkUpdate() throws IOException, java.text.ParseException {
        final File folder = new File(resourceDirectory);
        ArrayList<String> fileNames = new ArrayList<String>();
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                continue;
            } else {
                fileNames.add(fileEntry.getName());
            }
        }
        return compareDates(fileNames);
    }
    private static boolean compareDates(ArrayList<String> fileNames) throws IOException, java.text.ParseException {
        String url = "https://www.espn.com/nfl/story/_/page/Football-Power-Index/espn-nfl-football-power-index";
        Document document = Jsoup.connect(url).get();

        String updateDate = document.select("span[data-date]").attr("data-date").substring(0,10);
        Date formattedDate = new SimpleDateFormat("yyyy-MM-dd").parse(updateDate);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
        String formatUpdateDate = formatter.format(formattedDate);


        for (String fileName : fileNames) {
            String fileDate = fileName.replace(".json", "").replaceAll("[^0-9.]", "");
            if (fileDate.equals(formatUpdateDate)) {
                return true;
            }
        }
        return false;
    }
    private static void createMaps() throws IOException, ParseException {
        Object obj = new JSONParser().parse(new FileReader(jsonFile));
        JSONObject joRead = (JSONObject) obj;
        joRead.keySet().forEach(keyStr ->
        {
            Object keyvalue = joRead.get(keyStr);
            JSONObject values = (JSONObject) keyvalue;
            teamNames.put(values.get("abrev").toString(), keyStr.toString());
            teamStyles.put(values.get("abrev").toString(), values.get("style").toString());
        });
    }
    private static void writeNewObject() throws IOException {
        String url = "https://www.espn.com/nfl/story/_/page/Football-Power-Index/espn-nfl-football-power-index";
        Document document = Jsoup.connect(url).get();
        Element table = document.select("table.inline-table").first();
        Elements rows = table.select("tr.last");

        JSONObject joWrite = new JSONObject();

        for (Element row : rows) {
            String abrev = row.select("td").get(0).text().replaceAll("[^A-Z]", "");
            double off = Double.parseDouble(row.select("td").get(2).text());
            double def = Double.parseDouble(row.select("td").get(3).text());
            double st = Double.parseDouble(row.select("td").get(4).text());

            Map m = new LinkedHashMap(5);
            m.put("abrev", abrev);
            m.put("off", off);
            m.put("def", def);
            m.put("st", st);
            m.put("style", teamStyles.get(abrev));

            joWrite.put(teamNames.get(abrev), m);
        }

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
        String filename = System.getProperty("user.dir") + File.separator + "resources" + File.separator + "teams_" + formatter.format(date) + ".json";
        SimUtil.setProperties("sim.properties", "latest.file", filename);
        PrintWriter pw = new PrintWriter(filename);
        pw.write(joWrite.toJSONString());

        pw.flush();
        pw.close();
    }
}
