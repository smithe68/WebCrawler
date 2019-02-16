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

     public LinkedList<String> pageReader(String urlString) throws IOException{

        URL url;
        try {
            url = new URL(urlString);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

        String line;

        while ((line = reader.readLine()) != null) {

            String[] splitLine = line.split("\"");

            for (int i = 0; i < splitLine.length; i++){
                if (!(splitLine[i].equals(""))){
                    char c = splitLine[i].charAt(0);

                    if(c == 104){
                        if (splitLine[i].length() > 8) {
                            String httpsCheck = "";
                            for (int j = 0; j < 8; j++){
                                c = splitLine[i].charAt(j);
                                httpsCheck+=c;
                            }

                            if (httpsCheck.equals("https://")){
                                try{
                                    URL testString = new URL(splitLine[i]);
                                } catch(Exception e){
                                    System.out.println("bad url");
                                    break;
                                }
                                String[] putMeBackTogether = splitLine[i].split("/");
                                String cutAddress = "https://" + putMeBackTogether[2] + "/";
                                Boolean addMe = true;
                                for (int l = 0; l < listOfPages.size(); l++){

                                    if (listOfPages.get(l).equals(cutAddress)){
                                        addMe = false;
                                    }
                                }
                                if(addMe == true){
                                    listOfPages.add(cutAddress);
                                }
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
         return listOfPages;
    }

}
