/**
 * Created by sanderc7 on 2/16/19.
 */
import java.io.IOException;
import java.util.*;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class PageReader {
    LinkedList<String> listOfPages = new LinkedList<String>();
     public void pageReader(String urlString) throws IOException{

        URL url;
        try {
            url = new URL(urlString);
        } catch (IOException e) {
            System.out.println("asd");
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
                if (!(splitLine[i].equals(""))){
                    char c = splitLine[i].charAt(0);
                    if(c == 104){
                        //System.out.println(splitLine[i]);
                        if (splitLine[i].length() > 5) {
                            String httpsCheck = "";
                            for (int j = 0; j < 5; j++){
                                c = splitLine[i].charAt(j);
                                httpsCheck+=c;

                            }
                            if (httpsCheck.equals("https")){
                                listOfPages.add(splitLine[i]);
                            }
                        }

                    }
                }


            }
        }
        for (int k =0; k < listOfPages.size(); k++){
            System.out.println(listOfPages.get(k));
        }
        reader.close();
    }

}
