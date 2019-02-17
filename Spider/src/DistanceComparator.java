/**
 * Created by sanderc7 on 2/17/19.
 */
import java.util.Comparator;

public class DistanceComparator implements Comparator<Website> {

    public int compare(Website page1, Website page2){
        if(page1.getDistance() < page2.getDistance()){
            return -1;
        }
        if(page2.getDistance() < page1.getDistance()){
            return 1;
        }
        return 0;
    }
}
