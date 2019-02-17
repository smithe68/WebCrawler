import java.io.Serializable;

/**
 * Created by smithe68 on 2/16/19.
 */
public class Website implements Serializable{
    private String name = "";
    private int inLinks = 1;
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
