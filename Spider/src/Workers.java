
import java.util.LinkedList;
import java.util.concurrent.RecursiveAction;

/**
 * Created by smithe68 on 2/16/19.
 */
public class Workers extends RecursiveAction{
    private static int count = 0;
    PageReader pageReader = new PageReader();

    Spider spider;
    LinkedList<String> newUrls;
    public Workers(LinkedList newUrls,Spider spider){
        this.newUrls = newUrls;
        this.spider = spider;
    }
    public void compute(){
        spider.spiderTime(newUrls, spider);


    }
    private void process() {
        try {  Thread.sleep(2000);  } catch (InterruptedException e) { e.printStackTrace(); }
    }
}
