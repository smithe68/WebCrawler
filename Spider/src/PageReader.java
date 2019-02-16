/**
 * Created by sanderc7 on 2/16/19.
 */
import java.io.IOException;
import java.util.*;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class PageReader {
     public void pageReader(String urlString) throws IOException{

        //String urlString = "https://stackoverflow.com/sitemap.xml";
        URL url;
        try {
            url = new URL(urlString);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

        String line;
        //12
         int counter = 0;
        while ((line = reader.readLine()) != null) {
            //System.out.println(line);
            String[] splitLine = line.split("\"");
            counter++;
            //System.out.println(counter);
            for (int i = 0; i < splitLine.length; i++){
                System.out.println(splitLine[i]);
                if (splitLine[i].equals("")){
                    System.out.println("yessir");
                }
//                char c = splitLine[i].charAt(0);
//                if(c == 104){
//                    System.out.println("first try baby");
//                }

            }
        }

        reader.close();
    }

}
