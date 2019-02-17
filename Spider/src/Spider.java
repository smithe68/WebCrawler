import sun.awt.image.ImageWatched;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ScheduledExecutorService;



/**
 * Created by smithe68 on 2/16/19.
 */

public class Spider  {

    LinkedList<String> newUrls;
    PageReader pageReader = new PageReader();
    LinkedList<String> visited = new LinkedList<String>();
    ForkJoinPool commonPool = new ForkJoinPool(64);
   private HashMap<String,Website> WHM = new HashMap<String,Website>();

   /**returns size of hash table**/
   public int getSize(){
       return WHM.size();
   }

    /** web crawler. Given list of URL's, this spider will go to each URL that has not already been visited before, and collect a list of all
     * links at the given web page. It will then check all of those links, recursively.
     */
   public void spiderTime(LinkedList<String> url,Spider spider){

       //iterate over list of all new URL's
       for (String i : url) {

           //if visited list contains current URL, increment its index in the has table
           //and move onto next URL
           if (visited.contains(i)) {
               indexHashtable(i);
               continue;
           }

           visited.add(i);
           indexHashtable(i);

           try {

               //call page reader to get a list of all URL's on the page of the current URL
               newUrls = pageReader.pageReader(i);
               //create a worker which will call spiderTime on the list of new URL's
               Workers worker = new Workers(newUrls,spider);

               //stop the crawler after adding a certain amount of URL's to the hashmap, then print their information
               if(WHM.size()>1000){
                   printWebsiteInfo();
                   System.exit(0);
               }

               commonPool.invoke(worker);
           }
           catch(IOException e){}
       }
   }

   /** Takes a URL as a string and indexes into the hash map. If element already exists in hashmap, the increment its inLink counter**/
   public void indexHashtable(String websiteName){

            if(!(WHM.containsKey(websiteName))){
                Website newWebsite = new Website();
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
}
