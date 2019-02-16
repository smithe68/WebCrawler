/**
 * Created by smithe68 on 2/16/19.
 */
import java.io.IOException;
import java.util.*;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        PageReader read = new PageReader();
        //String startingWebPage = "file:///home/sanderc7/WebCrawler/WebCrawler/TestSite.html";
        String startingWebPage = "https://www.youtube.com/";
        //String startingWebPage = "https://www.youtube.com/yt/about/press/";
        try{
            read.pageReader(startingWebPage);
        } catch (IOException e){
            System.out.println("Can't read given URL");
        }


    }
}
