/**
 * Created by smithe68 on 2/16/19.
 */

import java.util.*;
import java.util.Iterator;

public class Main {

    public static void main(String[] args) {
        int countMeIn = 0;
        PageReader read = new PageReader();
        Spider spider = new Spider();
        //KevinBacon kev = new KevinBacon();
        LinkedList<String> testList = new LinkedList<String>();
        testList.add("https://web.archive.org/web/20080916124519/http://www.dmoz.org/");
        //testList.add("file:///home/sanderc7/WebCrawler/WebCrawler/TestSite.html");

        spider.spiderTime(testList,spider);
        //kev.kevinBacon("https://www.reddit.com/","https://www.wikipedia.org/");
        Iterator it = spider.getHashMap().entrySet().iterator();

        while(it.hasNext()){

            System.out.println("LOOK " + it.next());
        }
        System.exit(0);
    }
}
