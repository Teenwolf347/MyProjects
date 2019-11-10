import org.apache.log4j.Logger;

import java.io.*;
import java.util.Properties;

class DatabaseConfig {
    private final static Logger logger = Logger.getLogger(DatabaseConfig.class);
    private static File file =
            new File("D:/Владимир/Программы/Maven/MavenProject/DatabaseMySQL/src/main/resources/Config.properties");
    private static Properties prop = new Properties();
    static String getUrl()  {
        try (FileInputStream readfile = new FileInputStream(file)) {
            prop.load(readfile);
            return  prop.getProperty("Url");
        }catch (IOException ex){
            logger.error(new Exception(ex));
        }
        return null;
    }
    static String getUser()  {
        try (FileInputStream readfile = new FileInputStream(file)){
            prop.load(readfile);
            return prop.getProperty("user");
        }catch (IOException ex){
            logger.error(new Exception(ex));
        }
        return null;
    }
    static String getPassword() {
        try (FileInputStream readfile = new FileInputStream(file)) {
            prop.load(readfile);
            return  prop.getProperty("password");
        }catch (IOException ex){
            logger.error(new Exception(ex));
        }
        return null;
    }
    static String getDriver(){
        try (FileInputStream readfile = new FileInputStream(file)) {
            prop.load(readfile);
            return prop.getProperty("driver");
        }catch (IOException ex){
            logger.error(new Exception(ex));
        }
        return null;
    }
}
