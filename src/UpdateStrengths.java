import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
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
            teamNames.put(keyStr.toString(), values.get("abrev").toString());
            teamStyles.put(keyStr.toString(), values.get("style").toString());
        });
    }
    private static void writeNewObject() throws IOException {
        //String url = "https://www.espn.com/nfl/story/_/page/Football-Power-Index/espn-nfl-football-power-index"
        String url = "https://www.espn.com/nfl/fpi/_/season/2020";
        Document document = Jsoup.connect(url).get();
        Element table_teams = document.select("tbody.table__TBODY").first();
        Elements teams = table_teams.select("td.Table__TD");

        Element table_fpi = document.select("tbody.table__TBODY").get(1);
        Elements fpi_infos = table_fpi.select("tr.Table__TR");

        ArrayList<String> team_names = new ArrayList<>();

        for (Element team : teams) {
            String team_name = team.select("a").text().replaceAll("\\s", "");
            team_names.add(team_name);
        }

        JSONObject joWrite = new JSONObject();

        for (Element fpi_info : fpi_infos) {
            int idx = fpi_infos.indexOf(fpi_info);
            String name = team_names.get(idx);
            double off = Double.parseDouble(fpi_info.select("td").get(4).text());
            double def = Double.parseDouble(fpi_info.select("td").get(5).text());
            double st = Double.parseDouble(fpi_info.select("td").get(6).text());

            Map m = new LinkedHashMap(5);
            m.put("abrev", teamNames.get(name));
            m.put("off", off);
            m.put("def", def);
            m.put("st", st);
            m.put("style", teamStyles.get(name));

            joWrite.put(name, m);
        }

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
        String filename = System.getProperty("user.dir") + File.separator + "resources" + File.separator + "teams_" + formatter.format(date) + ".json";
        SimUtil.setProperties("sim.properties", "latest.file", "teams_" + formatter.format(date) + ".json");
        PrintWriter pw = new PrintWriter(filename);
        pw.write(joWrite.toJSONString());

        pw.flush();
        pw.close();
    }
}
