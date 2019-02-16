import java.util.LinkedList;

/**
 * Created by smithe68 on 2/16/19.
 */

public class Workers implements Runnable {

    LinkedList<String> newUrls;
    Spider spider;
    public Workers(LinkedList<String> newUrls,Spider spider){
        this.newUrls = newUrls;
        this.spider = spider;
    }
    public void run(){
        for(String i : newUrls){
            spider.indexHastable(i);
        }
        spider.spiderTime(newUrls,spider);
        System.out.print("hello");
        process();

    }
    private void process() {
        try {  Thread.sleep(2000);  } catch (InterruptedException e) { e.printStackTrace(); }
    }
}
