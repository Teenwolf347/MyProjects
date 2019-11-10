import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test {
    public static void main(String[] args) {
        DatabaseExchangeRate database = new DatabaseExchangeRate();
        database.connectionDatabase();
        database.saveResult("select * from bookstore");

    }
}
