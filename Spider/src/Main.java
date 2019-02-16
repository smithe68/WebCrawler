/**
 * Created by smithe68 on 2/16/19.
 */

import java.util.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        PageReader read = new PageReader();
//        LinkedList<String> testList = new LinkedList<String>();
//
//        testList.add("https://google.com");
//
//        Spider spider = new Spider();
//        spider.spiderTime(testList,spider);
        //String testString = "https://en.wikipedia.org/wiki/RuneScape";
        String testString = "file:///home/sanderc7/WebCrawler/WebCrawler/TestSite.html";

        //String testString = "https://www.youtube.com";
        try{
            read.pageReader(testString);
        } catch (IOException e){
            System.out.println("invalid url");
        }
    }
}
