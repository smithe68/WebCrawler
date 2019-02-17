/**
 * Created by smithe68 on 2/16/19.
 */

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.*;
import java.io.IOException;
import java.util.Iterator;

public class Main {

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);  // Reading from System.in
        System.out.println("Enter a search and I'll see if i got it");
        String string = reader.nextLine(); // Scans the next token of the input as an int.
            PageReader read = new PageReader();
            Spider spider = new Spider();

            LinkedList<String> testList = new LinkedList<String>();
            testList.add("https://www.seattletimes.com");

            if (string.equals("KevinBacon")){
                KevinBacon kev = new KevinBacon();
                System.out.println("Enter two websites and I'll try to find a path between them");
                System.out.print("First Site: ");
                String startingPage = reader.nextLine();
                System.out.print("Second Site: ");
                String endingPage = reader.nextLine();
                reader.close();
                spider.spiderTimeForBacon(testList, spider);
                Iterator it = spider.getHashMap().values().iterator();

                Website startingPageWeb = spider.returnHashIndex(startingPage);
                //System.out.println(startingPageWeb.getName());
                kev.baconTime(startingPageWeb,endingPage,spider.getHashMap());
                System.exit(0);
            }

            reader.close();
            spider.spiderTime(testList,spider,string);
            //kev.kevinBacon("https://www.reddit.com/","https://www.wikipedia.org/");
    }
}
