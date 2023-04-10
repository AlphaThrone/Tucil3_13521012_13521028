package Algorithm;

import java.util.ArrayList;

import org.jxmapviewer.viewer.GeoPosition;

import Main.EventWaypoint;
import Main.WayPoints;

public class Node {
    private WayPoints node;
    private ArrayList<WayPoints> neighbourNodes;
    private ArrayList<Double> distanceNeighbour;
    private ArrayList<Double> straightDistance;

    public Node(String name, EventWaypoint event ,Double latitude, Double longitude){
        GeoPosition temp = new GeoPosition(latitude, longitude);
        node = new WayPoints(name, event, temp);
        neighbourNodes = new ArrayList<WayPoints>();
        distanceNeighbour = new ArrayList<Double>();
        straightDistance = new ArrayList<Double>();
    }

    public Node(Node other){
        node = other.node;
        neighbourNodes = other.neighbourNodes;
        straightDistance = other.straightDistance;
    }

    public WayPoints getNode(){
        return this.node;
    }

    public void setNode(WayPoints node){
        this.node = node;
    }

    public ArrayList<WayPoints> getNeighbour(){
        return this.neighbourNodes;
    }

    public void setNeighbour(ArrayList<WayPoints> other, ArrayList<Double> dist, ArrayList<Double> strDis){
        this.neighbourNodes = new ArrayList<WayPoints>(other.size());
        this.distanceNeighbour = new ArrayList<Double>(dist.size());
        this.straightDistance = new ArrayList<Double>(strDis.size());
        for (WayPoints i : other) {
            this.neighbourNodes.add(i);
        }
        for (Double j : dist) {
            this.distanceNeighbour.add(j);
        }
        for (Double k : strDis) {
            this.straightDistance.add(k);
        }
    }

    public void addNeighbour(WayPoints neighbour, Double distance, Double straightDistance){
        this.neighbourNodes.add(neighbour);
        this.distanceNeighbour.add(distance);
        this.straightDistance.add(straightDistance);
    }

    public void printNode(){
        System.out.println(node.getName() + node.getPosition().getLatitude() + node.getPosition().getLongitude());
    }
}
