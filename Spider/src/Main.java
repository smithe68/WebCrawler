/**
 * Created by smithe68 on 2/16/19.
 */

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.*;

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
        testList.add("https://stackoverflow.com/questions/8116147/java-how-to-make-this-serializable");
        //String testString = "https://www.youtube.com";
        spider.spiderTime(testList,spider);
       /* try {
            FileOutputStream fileOut =
                    new FileOutputStream("/tmp/spider.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(spider);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in /tmp/spider.ser");
        } catch (IOException i) {
            i.printStackTrace();
        }*/

    }
}
