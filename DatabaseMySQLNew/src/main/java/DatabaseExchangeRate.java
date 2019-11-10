import java.io.*;
import java.sql.*;
import java.util.Formatter;
import org.apache.log4j.Logger;

public class DatabaseExchangeRate extends DatabaseMySQL {
    private final static Logger logger = Logger.getLogger(DatabaseExchangeRate.class);
    public void getResult(String sql) {
        try (Statement statement = statementDatabase()) {
            ResultSet resultSet = statement.executeQuery(sql);
            ResultSetMetaData setMetaData = resultSet.getMetaData();
            int columns = resultSet.getMetaData().getColumnCount();
            int border = 0;
            for (int i = 1; i <= columns; i++) {
                System.out.printf("%-" + setMetaData.getPrecision(i) + "s | ", setMetaData.getColumnLabel(i));
                border += ((setMetaData.getPrecision(i)) + 3) ;
            }
            System.out.println();
            for (int i = 1; i < border; i++){
                System.out.print("-");
        }
            System.out.println();
            while (resultSet.next()) {
                for (int i = 1; i <= columns; i++) {
                    System.out.printf("%-"+ setMetaData.getPrecision(i) +"s | ",resultSet.getString(i));
                }
                System.out.println();
            }
            for (int i = 1; i < border; i++){
                System.out.print("-");
            }
            System.out.println();
        } catch (SQLException ex) {
            logger.error(new Exception(ex));
        }
    }
    public void saveResult(String sql) {
        try (Statement statement = statementDatabase();
             FileWriter writer = new FileWriter("D:/test.txt")) {
            ResultSet resultSet = statement.executeQuery(sql);
            ResultSetMetaData setMetaData = resultSet.getMetaData();
            Formatter format;
            int columns = resultSet.getMetaData().getColumnCount();
            int border = 0;
            for (int i = 1; i <= columns; i++) {
                format = new Formatter();
                writer.write(String.valueOf
                        (format.format("%-" + setMetaData.getPrecision(i) + "s | ", setMetaData.getColumnLabel(i))));
                border += ((setMetaData.getPrecision(i)) + 3) ;
            }
            writer.append("\n");
            for (int i = 1; i < border; i++){
                writer.write("-");
            }
            writer.append("\n");
            while (resultSet.next()) {
                for (int i = 1; i <= columns; i++) {
                    format = new Formatter();
                    writer.write(String.valueOf
                            (format.format("%-"+ setMetaData.getPrecision(i) +"s | ",resultSet.getString(i))));
                }
                writer.append("\n");
            }
            for (int i = 1; i < border; i++){
                writer.write("-");
            }
            writer.flush();
            logger.info("Таблица успешна сохранена");
        } catch (IOException | SQLException ex) {
            logger.error(new Exception(ex));
        }
    }
    // Old method
    public void saveResultOld(String sql) {
        try (Statement statement = statementDatabase();
             FileWriter writer = new FileWriter("D:/test.txt")) {
            ResultSet resultSet = statement.executeQuery(sql);
            ResultSetMetaData setMetaData = resultSet.getMetaData();
            Formatter format = new Formatter();
            writer.write(String.valueOf(format.format("%-2s | %-20s | %-15s | %-5s | %s\n",
                    setMetaData.getColumnLabel(1),
                    setMetaData.getColumnLabel(2),
                    setMetaData.getColumnLabel(3),
                    setMetaData.getColumnLabel(4),
                    setMetaData.getColumnLabel(5))));
            writer.write("==============================================================\n");
            while (resultSet.next()) {
                format = new Formatter();
                writer.write(String.valueOf(format.format("%-1d. | %-20s | %-15s | %-5d | %d\n",
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getInt(4),
                        resultSet.getInt(5))));
            }
        }catch(IOException | SQLException ex){
                logger.error(new Exception(ex));
            }
        }
    public void getResultOld(String sql) {
        try (Statement statement = statementDatabase()) {
            ResultSet resultSet = statement.executeQuery(sql);
            ResultSetMetaData setMetaData = resultSet.getMetaData();
            int columns = resultSet.getMetaData().getColumnCount();
            System.out.printf("%-2s | %-20s | %-15s | %-5s | %s\n",
                    setMetaData.getColumnLabel(1),
                    setMetaData.getColumnLabel(2),
                    setMetaData.getColumnLabel(3),
                    setMetaData.getColumnLabel(4),
                    setMetaData.getColumnLabel(5));
            System.out.println("==============================================================");
            while (resultSet.next()) {
                System.out.printf("%-1d. | %-20s | %-15s | %-5d | %d\n",
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getInt(4),
                        resultSet.getInt(5));
            }
        } catch (SQLException ex) {
            logger.error(new Exception(ex));
        }
    }
}