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

    /**take a URL as a string as an input, parses trough the html source code for current webpage and saves all links
     * to other web pages into listOfPages**/
    public Boolean contains(String string,String search) throws IOException {
        URL url;
        try {
            url = new URL(string);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

        String line;
        int found = 0;
        //iterates over every line of html code
        int lineCount = 0;
        while ((line = reader.readLine()) != null) {
            if (line.contains(search)){
                found ++;
            }
            if (lineCount == 1000) {
                break;
            }
            if (found > 2){
                return true;
            }
            lineCount++;

        }
        return false;
    }

     public LinkedList<String> pageReader(String urlString) throws IOException{
        //Checks to see if urlString is a valid url
        URL url;
        try {
            url = new URL(urlString);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

        String line;
        //iterates over every line of html code
         int lineCount =0;
        while ((line = reader.readLine()) != null) {
            if(lineCount == 1000){
                break;
            }
            lineCount++;
            //splits the current line using " as deliminator because all links will be contained within "" in html,so it makes it easier to find links
            String[] splitLine = line.split("\"");

            for (int i = 0; i < splitLine.length; i++){

                if (!(splitLine[i].equals(""))){
                    char c = splitLine[i].charAt(0);
                    //104=h; all URL's we are interested in start with h, so look for lines that start with h
                    if(c == 104){
                        if (splitLine[i].length() > 8) {
                            String httpsCheck = "";
                            //get the first 8 letters of current string
                            for (int j = 0; j < 8; j++){
                                c = splitLine[i].charAt(j);
                                httpsCheck+=c;
                            }
                            //if the first 8 letters of current string equal https:// then we keep the string
                            if (httpsCheck.equals("https://")){
                                //check to make sure the URL is valid
                                try{
                                    URL testString = new URL(splitLine[i]);
                                } catch(Exception e){
                                    System.out.println("bad url");
                                    break;
                                }

                                //split the URL up so we get rid of unwanted directories; formats so we only get home page of web site
                                String[] putMeBackTogether = splitLine[i].split("/");
                                //System.out.print("lol");
                                String[] checkForBogus = putMeBackTogether[2].split("\\.");
                                Boolean addMe = true;
                                //ignore webpage which do not start with either www, edu, en
                                if(!(checkForBogus[0].equals("www") || checkForBogus[0].equals("edu") || checkForBogus[0].equals("en"))){
                                    addMe = false;
                                }

                                String cutAddress = "https://" + putMeBackTogether[2];
                                //if URL is already in listOfPages, do not add then
                                for (int l = 0; l < listOfPages.size(); l++){

                                    if (listOfPages.get(l).equals(cutAddress)){
                                        addMe = false;
                                    }
                                }
                                if(addMe == true){
                                    listOfPages.add(splitLine[i]);
                                }
                            }
                        }

                        if (splitLine[i].length() > 2) {
                            String httpCheck = "";
                            //get the first 8 letters of current string
                            for (int j = 0; j < 2; j++){
                                c = splitLine[i].charAt(j);
                                httpCheck+=c;
                            }
                            //if the first 8 letters of current string equal https:// then we keep the string
                            if (httpCheck.equals("//")){
                                //check to make sure the URL is valid
                                try{
                                    URL testString = new URL(splitLine[i]);
                                } catch(Exception e){
                                    System.out.println("bad url");
                                    break;
                                }

                                //split the URL up so we get rid of unwanted directories; formats so we only get home page of web site
                                String[] putMeBackTogether = splitLine[i].split("/");
                                //System.out.print("lol");
                                String[] checkForBogus = putMeBackTogether[2].split("\\.");
                                Boolean addMe = true;
                                //ignore webpage which do not start with either www, edu, en
                                if(!(checkForBogus[0].equals("www") || checkForBogus[0].equals("edu") || checkForBogus[0].equals("en"))){
                                    addMe = false;
                                }

                                String cutAddress = "//" + putMeBackTogether[2];
                                //if URL is already in listOfPages, do not add then
                                for (int l = 0; l < listOfPages.size(); l++){

                                    if (listOfPages.get(l).equals(cutAddress)){
                                        addMe = false;
                                    }
                                }
                                if(addMe == true){
                                    listOfPages.add(splitLine[i]);
                                }
                            }
                        }

                    }
                }


            }
        }

        reader.close();
         return listOfPages;
    }

}
