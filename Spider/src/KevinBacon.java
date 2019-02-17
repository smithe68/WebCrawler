/**
 * Created by sanderc7 on 2/16/19.
 */
import java.util.LinkedList;
import java.util.*;
import java.util.PriorityQueue;
import java.util.Comparator;


public class KevinBacon {
    Comparator<Website> comparator = new DistanceComparator();
    private PriorityQueue<Website> minHeap = new PriorityQueue<Website>(11, comparator);

    public int baconTime(Website startingPage, String endingPage, HashMap<String, Website> mp){
        LinkedList<Website> pageList = startingPage.getLinkedList();
        Spider spider = new Spider();
        Iterator it = spider.getHashMap().values().iterator();
        for (int j = 0; j < pageList.size(); j++){
            pageList.get(j).setVisited(false);
            pageList.get(j).setDistance(Integer.MAX_VALUE);
            //minHeap.add(pageList.get(j));
        }
        while(it.hasNext()){
            Website temp = (Website) it.next();
            LinkedList<Website> tempList = temp.getLinkedList();
            for (int i = 0; i < tempList.size(); i++){
                tempList.get(i).setDistance(Integer.MAX_VALUE);
                tempList.get(i).setVisited(false);

            }

        }
        startingPage.setDistance(0);
        startingPage.setVisited(false);
        minHeap.add(startingPage);

        while(minHeap.size()!=0){
            Website currentWeb = minHeap.poll();
            if(currentWeb.getVisited().equals(false)){

                currentWeb.setVisited(true);
                for (int h = 0; h < currentWeb.getLinkedList().size(); h++){
                    Website tempBabe = (Website) currentWeb.getLinkedList().get(h);
                    if (tempBabe.getVisited().equals(false)){
                        minHeap.add(tempBabe);
                    }
                    if (tempBabe.getDistance() > (currentWeb.getDistance() + 1)){
                        tempBabe.setDistance(currentWeb.getDistance() + 1);
                        //System.out.println(tempBabe.getDistance() + tempBabe.getName());
                    }
                    if(tempBabe.getName().equals(endingPage)){
                        System.out.println(startingPage.getName() + " can reach " +endingPage+ " by going through " +tempBabe.getDistance()+" link(s)");
                    }
                }



            }

        }
//        for (int i = 0; i < pageList.size(); i++) {
//                System.out.println(pageList.get(i).getName() + " woohoo");
//                System.out.println(pageList.get(i).getDistance());
//                System.out.println(pageList.get(i).getVisited());
//            }

        return 0;
    }



}
