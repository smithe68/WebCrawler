/**
 * Created by sanderc7 on 2/16/19.
 */
import java.io.IOException;
import java.util.*;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
public class PageReader {
    public static void pageReader() throws IOException{
        String urlString = "https://www.youtube.com/robots.txt";
        URL url;
        try {
            url = new URL(urlString);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        reader.close();
    }
}
