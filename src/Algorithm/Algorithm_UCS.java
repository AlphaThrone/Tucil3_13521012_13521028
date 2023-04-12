package Algorithm;

import Main.WayPoints;
import Algorithm.Node;
import Algorithm.DistanceCalculate;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;
import java.util.*;

public class Algorithm_UCS {
    
    public static List<WayPoints> pathfind(ArrayList<Node> graph, int source, int destination){
        /*TODO: UCS Algorithm */
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[graph.size()];
        int[] prev = new int[graph.size()];
        Double[] cost = new Double[graph.size()];

        Arrays.fill(cost, Integer.MAX_VALUE);

        pq.add(graph.get(source));
        cost[source] = 0.0;

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int id = node.getIdx();
            if (visited[id]) {
                continue;
            }

            visited[id] = true;

            if (id == destination) {
                break;
            }

            for (int i = 0; i < graph.get(id).getNeighbour().size(); i++) {
                int neighbour = graph.get(id).getNeighbour().get(i).getIdx();
                Double newCost = cost[id] + DistanceCalculate.distance(graph.get(id).getNode().getPosition(), graph.get(neighbour).getNode().getPosition());
                if (newCost < cost[neighbour]) {
                    cost[neighbour] = newCost;
                    prev[neighbour] = id;
                    pq.add(graph.get(neighbour));
                }
            }
        }


        List<WayPoints> path = new ArrayList<WayPoints>();
        int id = destination;
        while (id != destination) {
            path.add(graph.get(id).getNode());
            id = prev[id];
        }

        path.add(graph.get(source).getNode());
        return path;


    }
}
