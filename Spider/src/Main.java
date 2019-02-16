/**
 * Created by smithe68 on 2/16/19.
 */

import java.util.*;

public class Main {
    public static void main(String[] args) {
        PageReader read = new PageReader();
        LinkedList<String> testList = new LinkedList<String>();

        testList.add("https://google.com");

        Spider spider = new Spider();
        spider.spiderTime(testList,spider);

    }
}
