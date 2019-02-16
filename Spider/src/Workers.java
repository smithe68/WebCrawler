import java.io.IOException;
import java.util.LinkedList;

/**
 * Created by smithe68 on 2/16/19.
 */

public class Workers implements Runnable{
    private static int count = 0;
    PageReader pageReader = new PageReader();

    String newUrl;
    Spider spider;
    LinkedList<String> newUrls;
    public Workers(String newUrl,Spider spider){
        this.newUrl = newUrl;
        this.spider = spider;
    }
    public void run(){
            try {
                newUrls = pageReader.pageReader(newUrl);
                spider.indexHastable(newUrl);
                count++;
                spider.spiderTime(newUrls, spider);
            }
            catch (IOException e){

            }
        }
    private void process() {
        try {  Thread.sleep(2000);  } catch (InterruptedException e) { e.printStackTrace(); }
    }
}
