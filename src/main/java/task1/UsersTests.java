package task1;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class Users {
    public static void main(String[] args) throws IOException {
        String url = "https://jsonplaceholder.typicode.com/users";
        Document doc = Jsoup.connect(url).ignoreContentType(true).get();
        System.out.println(doc);
    }

}
