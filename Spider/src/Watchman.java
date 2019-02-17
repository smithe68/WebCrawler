import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.LinkedList;

import static java.lang.Thread.sleep;

/**
 * Created by smithe68 on 2/16/19.
 */
public class Watchman implements Runnable{
    HashMap hashmap;
    int size = 100 ;
    LinkedList<String> visited;
    private LinkedList<String> newUrls;
    public Watchman(HashMap WHM, LinkedList<String> visited,LinkedList<String>newUrls){
        this.hashmap = WHM;
        this.visited = visited;
        this.newUrls = newUrls;


    }
    public void run(){
        /*
        This block of code takes the objects and serializes  them to the proper files
         it works by opening a fileOutputStream and sending that to an object stream to create
        a numerical representation of an  object which is saved in a file.
         */
        while(true) {
            try {
                sleep(60000);
            }catch(InterruptedException e){}

                System.out.println("seralizing");
                try {
                    FileOutputStream fileOut =
                            new FileOutputStream("spiderHashMap.ser");
                    ObjectOutputStream out = new ObjectOutputStream(fileOut);
                    out.writeObject(hashmap);
                    out.close();
                    fileOut.close();
                    System.out.println("Serialized data is saved in spiderHashMap.ser");
                } catch (IOException i) {
                    i.printStackTrace();
                }
                try {
                    FileOutputStream fileOut =
                            new FileOutputStream("newUrls.ser");
                    ObjectOutputStream out = new ObjectOutputStream(fileOut);
                    out.writeObject(newUrls);
                    out.close();
                    fileOut.close();
                    System.out.println("Serialized data is saved in newUrls.ser");
                } catch (IOException i) {
                    i.printStackTrace();
                }

                try {
                    FileOutputStream fileOut =
                            new FileOutputStream("visited.ser");
                    ObjectOutputStream out = new ObjectOutputStream(fileOut);
                    out.writeObject(visited);
                    out.close();
                    fileOut.close();
                    System.out.println("Serialized data is saved in visited.ser");
                } catch (IOException i) {
                    i.printStackTrace();
                }

        }
    }

    public void setWHM(HashMap WHM) {
        this.hashmap = WHM;
    }

    public void setVisited(LinkedList<String> visited) {
        this.visited = visited;
    }

    public void setNewUrls(LinkedList<String> newUrls) {
        this.newUrls = newUrls;
    }
}
