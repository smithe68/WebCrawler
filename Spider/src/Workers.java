
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.RecursiveAction;

/**
 * Created by smithe68 on 2/16/19.
 */
public class Workers extends RecursiveAction {


    private LinkedList<String> url;

    private static int count = 0;
    PageReader pageReader = new PageReader();

    Spider spider;
    String search;

    public Workers(LinkedList url, Spider spider,String search) {
        this.url = url;
        this.spider = spider;
        this.search = search;
    }

    public void compute() {
        //call page reader to get a list of all URL's on the page of the current URL
        spider.spiderTime(url, spider,search);

        //create a worker which will call spiderTime on the list of new URL's
    }

    private void process() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


