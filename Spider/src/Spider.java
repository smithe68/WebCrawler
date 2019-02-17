import sun.awt.image.ImageWatched;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;


/**
 * Created by smithe68 on 2/16/19.
 */




public class Spider {
    ExecutorService executor = Executors.newFixedThreadPool(64);//creating a pool of 5 threads
    //ScheduledExecutorService executor= Executors.newScheduledThreadPool(1);
    LinkedList<String> newUrls;
    PageReader pageReader = new PageReader();
    LinkedList<String> visited = new LinkedList<String>();


   private HashMap<String,Website> WHM = new HashMap<String,Website>();
   public int getSize(){
       return WHM.size();
   }

   public void spiderTime(LinkedList<String> url,Spider spider){

       for (String i : url) {
           System.out.println(i);
           if (visited.contains(i)) {
               continue;
           }
           visited.add(i);
           try {
               newUrls = pageReader.pageReader(i);
               spiderTime(newUrls ,spider);
               if (spider.getSize() > 1500) {
                   System.out.print("15000 links found");
                   spider.printWebsiteInfo();
                   System.exit(0);
               }

           }
           catch(IOException e){}
           //Runnable worker = new Workers(i, spider);
           //executor.execute(worker);

       }
       //executor.shutdown();


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
