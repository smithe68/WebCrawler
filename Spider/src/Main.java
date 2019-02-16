/**
 * Created by smithe68 on 2/16/19.
 */

import java.util.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        PageReader read = new PageReader();
        Spider spider = new Spider();
       LinkedList<String> testList = new LinkedList<String>();
//
//
//        Spider spider = new Spider();
//        spider.spiderTime(testList,spider);
        //String testString = "https://en.wikipedia.org/wiki/RuneScape";
        testList.add("https://www.callicoder.com/java-executor-service-and-thread-pool-tutorial/");

        //String testString = "https://www.youtube.com";
            spider.spiderTime(testList,spider);

    }
}
