import sun.awt.image.ImageWatched;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Created by smithe68 on 2/16/19.
 */




public class Spider {
    ExecutorService executor = Executors.newFixedThreadPool(5);//creating a pool of 5 threads
    LinkedList<String> newUrls;
    PageReader pageReader = new PageReader();


   private HashMap<String,Website> WHM = new HashMap<String,Website>();
   public int getSize(){
       return WHM.size();
   }

   public void spiderTime(LinkedList<String> url,Spider spider){

       for (String i : url) {
           try {
               newUrls = pageReader.pageReader(i);
               Runnable worker = new Workers(newUrls,spider);
               executor.execute(worker);
               executor.shutdown();
               while (!executor.isTerminated()) {   }
               System.out.println("Finished all threads");
           }
           catch (IOException e) {}

       }
       if (spider.getSize() > 1500) {
           System.out.print("1500 links found");
           spider.printWebsiteInfo();
           System.exit(0);
       }

   }


   public void indexHastable(String websiteName){

            if(!(WHM.containsKey(websiteName))){
                Website newWebsite = new Website();
                newWebsite.setName(websiteName);
                WHM.put(websiteName,newWebsite);
            }
            else{
               Website foundWP =  WHM.get(websiteName);
               foundWP.incrimentInlink();
            }
    }

    public void printWebsiteInfo(){

        for (Website i : WHM.values()) {
            System.out.println("Website Name: " + i.getName());
            System.out.println("Website inLinks: " + i.getInLinks());
            System.out.println("Website outLinks: " + i.getOutLinks());
        }
    }
}
