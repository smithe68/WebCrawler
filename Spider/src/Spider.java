import sun.awt.image.ImageWatched;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * Created by smithe68 on 2/16/19.
 */

public class Spider  {
    private static HashMap<String,Website> WHM = new HashMap<String,Website>();
    private LinkedList<String> newUrls;
    private PageReader pageReader = new PageReader();
    private static ForkJoinPool commonPool = new ForkJoinPool(64);
    private Watchman watchman = new Watchman(WHM,visited,newUrls);
    private Thread watchThread = new Thread(watchman);
    private int runCount = 0;
    private static LinkedList<String> visited = new LinkedList<String>();



    public HashMap<String, Website> getHashMap(){
        return WHM;
    }
    /** web crawler. Given list of URL's, this spider will go to each URL that has not already been visited before, and collect a list of all
     * links at the given web page. It will then check all of those links, recursively.
     */
   public void spiderTime(LinkedList<String> url,Spider spider) {
       if(runCount == 0 ) {
           watchThread.start();
       }
       runCount++;
       System.out.println(runCount);
        /*
        this section checks to see if were on are fist run if we are on our first run we start are watchman thread
        and check to see if we have any saved ser files if we do it converts them to the proper objects if not they
        are set to default
         */
       //iterate over list of all new URL's
       for (int j = 0; j < url.size(); j++) {
           String i = url.get(j);
           if (WHM.containsKey(i)) {
               indexHashtable(i);
               continue;
           }
           System.out.println(i);
           indexHashtable(i);
           for (int z = 0; z < url.size(); z++){
               WHM.get(i).addToLinkedList(new Website(url.get(z)));

           }
           try {
                newUrls = pageReader.pageReader(i);
           }
           catch(IOException e){}

           Workers worker = new Workers(newUrls, spider);

           watchman.setWHM(WHM);
           watchman.setVisited(visited);
           watchman.setNewUrls(newUrls);
            if (runCount >=500){
                return;
            }
           commonPool.invoke(worker);
           commonPool.shutdown();
       }
   }
           //stop the crawler after adding a certain amount of URL's to the hashmap, then print their information
           //if visited list contains current URL, increment its index in the has table
           //and move onto next URL

   /** Takes a URL as a string and indexes into the hash map. If element already exists in hashmap, the increment its inLink counter**/

   public void indexHashtable(String websiteName){

       if(!(WHM.containsKey(websiteName))){
           Website newWebsite = new Website(websiteName);
           newWebsite.setName(websiteName);
           WHM.put(websiteName,newWebsite);
       }
       else{
           WHM.get(websiteName).incrimentInlink();
       }
   }
    /** Prints info of each web page from the URL's in hash map**/
    public void printWebsiteInfo(){

        for (Website i : WHM.values()) {
            System.out.println("Website Name: " + i.getName());
            System.out.println("Website inLinks: " + i.getInLinks());

        }
    }
    public int getSize(){
        return WHM.size();
    }
}


