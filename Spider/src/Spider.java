import sun.awt.image.ImageWatched;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.ForkJoinPool;




/**
 * Created by smithe68 on 2/16/19.
 */




public class Spider  {

    private LinkedList<String> newUrls;
    private PageReader pageReader = new PageReader();
    private LinkedList<String> visited = new LinkedList<String>();
    private ForkJoinPool commonPool = new ForkJoinPool(8);
    private int runCount = 0;
    private HashMap<String,Website> WHM = new HashMap<String,Website>();
    private Watchman watchman = new Watchman(WHM,visited,newUrls);
    private Thread watchThread = new Thread(watchman);

   public int getSize(){
       return WHM.size();
   }

   public void spiderTime(LinkedList<String> url,Spider spider){
        /*
        this section checks to see if were on are fist run if we are on our first run we start are watchman thread
        and check to see if we have any saved ser files if we do it converts them to the proper objects if not they
        are set to default
         */
       if(runCount == 0 ){
           watchThread.start();
           runCount++;
           try {
               FileInputStream fileIn = new FileInputStream("spiderHashMap.ser");
               ObjectInputStream in = new ObjectInputStream(fileIn);
               WHM = (HashMap<String, Website>) in.readObject();
               in.close();
               fileIn.close();
           } catch (IOException i) {
               WHM = new HashMap<String,Website>();
           } catch (ClassNotFoundException c) {
               System.out.println("HashMap class not found");
               c.printStackTrace();
               return;
           }
           try {
               FileInputStream fileIn = new FileInputStream("visited.ser");
               ObjectInputStream in = new ObjectInputStream(fileIn);
               visited = (LinkedList<String>) in.readObject();
               in.close();
               fileIn.close();
           } catch (IOException i) {
               visited = new LinkedList<String>();
           } catch (ClassNotFoundException c) {
               System.out.println("LinkedList class not found");
               c.printStackTrace();
               return;
           }
           try {
               FileInputStream fileIn = new FileInputStream("newUrls.ser");
               ObjectInputStream in = new ObjectInputStream(fileIn);
               url = (LinkedList<String>) in.readObject();
               in.close();
               fileIn.close();
           } catch (IOException i) {
               url = new LinkedList<String>();
               url.add("https://web.archive.org/web/20080916124519/http://www.dmoz.org/");
           } catch (ClassNotFoundException c) {
               System.out.println("LinkedList class not found");
               c.printStackTrace();
               return;
           }
       }

       for (String i : url) {
           System.out.println(i);
           //System.out.println(i);

           if (visited.contains(i)) {
               indexHashtable(i);
               continue;
           }
           visited.add(i);
           indexHashtable(i);

           try {
               newUrls = pageReader.pageReader(i);
               Workers worker = new Workers(newUrls, spider);
               if(WHM.size()>100){
                   printWebsiteInfo();
                   System.exit(0);

               }
               watchman.setWHM(WHM);
               watchman.setVisited(visited);
               watchman.setNewUrls(newUrls);
               commonPool.invoke(worker);



           }
           catch(IOException e){}
       }
   }


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

    public void printWebsiteInfo(){

        for (Website i : WHM.values()) {
            System.out.println("Website Name: " + i.getName());
            System.out.println("Website inLinks: " + i.getInLinks());

        }
    }
}
