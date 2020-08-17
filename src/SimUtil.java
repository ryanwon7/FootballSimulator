import java.io.*;
import java.util.Properties;

public class SimUtil {
    public static Properties getProperties(String fileName){
        Properties prop = new Properties();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(
                    System.getProperty("user.dir") + File.separator + "resources" + File.separator + fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            prop.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }
    public static void setProperties(String fileName, String propertyName, String propertyValue){
        Properties prop = new Properties();
        try {
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + File.separator + "resources" + File.separator + fileName);
            prop.load(fis);
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileOutputStream fos = new FileOutputStream(System.getProperty("user.dir") + File.separator + "resources" + File.separator + fileName);
            prop.setProperty(propertyName, propertyValue);
            prop.store(fos, null);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
