/**
 * Created by smithe68 on 2/16/19.
 */
public class Website {
    private String name = "";
    private int inLinks = 1; //number of pages which contain a link to this website
    private int outLinks = 0;

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

}
