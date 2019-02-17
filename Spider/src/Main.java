/**
 * Created by smithe68 on 2/16/19.
 */

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.*;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);  // Reading from System.in
        System.out.println("Enter a search and I'll see if i got it");
        String string = reader.nextLine(); // Scans the next token of the input as an int.
        reader.close();
            PageReader read = new PageReader();
            Spider spider = new Spider();
            //KevinBacon kev = new KevinBacon();
            LinkedList<String> testList = new LinkedList<String>();
            testList.add("https://www.seattletimes.com/");
            spider.spiderTime(testList,spider,string);
            //kev.kevinBacon("https://www.reddit.com/","https://www.wikipedia.org/");
    }
}
