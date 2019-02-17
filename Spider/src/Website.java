import java.io.Serializable;
import java.util.LinkedList;

/**
 * Created by smithe68 on 2/16/19.
 */
public class Website implements Serializable {
        private Integer distance;
        private boolean visited;

        private String name = "";
        private int inLinks = 1; //number of pages which contain a link to this website
        private LinkedList<Website> adjacentPages = new LinkedList<Website>();
        public Boolean getVisited(){
            return visited;
        }
        public void setVisited(boolean value){
            this.visited = value;
        }
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getInLinks() {
            return inLinks;
        }

        public void incrimentInlink() {
            this.inLinks++;
        }

        public void addToLinkedList(Website page){
            adjacentPages.add(page);

        }

        public LinkedList getLinkedList(){

            return adjacentPages;
        }

        public Website(String URL){
            this.name = URL;
        }

    }

