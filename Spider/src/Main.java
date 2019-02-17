/**
 * Created by smithe68 on 2/16/19.
 */

import java.util.*;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        PageReader read = new PageReader();
        Spider spider = new Spider();
        KevinBacon kev = new KevinBacon();
        LinkedList<String> testList = new LinkedList<String>();
        testList.add("https://web.archive.org/web/20080916124519/http://www.dmoz.org/");
        //spider.spiderTime(testList,spider);
        kev.kevinBacon("https://www.reddit.com/","https://www.wikipedia.org/");
    }
}
