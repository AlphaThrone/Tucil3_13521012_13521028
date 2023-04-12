package Algorithm;

import java.util.*;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class UCS {

    private final double[][] graph;

    public UCS(int nodes) {
        graph = new double[nodes][nodes];
    }

    public void addEdge(int source, int destination, double cost) {
        graph[source][destination] = cost;
    }

    public List<Integer> ucs(int source, int destination) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[graph.length];
        int[] prev = new int[graph.length];
        double[] cost = new double[graph.length];
        Arrays.fill(cost, Double.MAX_VALUE);
        pq.add(new Node(source, 0));
        cost[source] = 0;
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int id = node.getId();
            if (visited[id]) {
                continue;
            }
            visited[id] = true;
            if (id == destination) {
                break;
            }
            for (int i = 0; i < graph.length; i++) {
                if (graph[id][i] != 0) {
                    int neighborId = i;
                    double neighborCost = graph[id][i];
                    double newCost = cost[id] + neighborCost;
                    if (newCost < cost[neighborId]) {
                        cost[neighborId] = newCost;
                        prev[neighborId] = id;
                        pq.add(new Node(neighborId, newCost));
                    }
                }
            }
        }
        if (cost[destination] == Double.MAX_VALUE) {
            JPanel panel = new JPanel();
            panel.add(new JLabel("Tidak ditemukan path!"));
            JOptionPane.showMessageDialog(null, panel, "Cost (Algoritma UCS)", JOptionPane.PLAIN_MESSAGE);
            return null;
        }
        JPanel panel = new JPanel();
        panel.add(new JLabel("Cost : " + cost[destination]));
        JOptionPane.showMessageDialog(null, panel, "Cost", JOptionPane.PLAIN_MESSAGE);

        List<Integer> path = new ArrayList<>();
        int id = destination;
        while (id != source) {
            path.add(id);
            id = prev[id];
        }
        path.add(source);
        Collections.reverse(path);
        return path;
        
    }

    private static class Node implements Comparable<Node> {
        private final int id;
        private final double cost;

        public Node(int id, double cost) {
            this.id = id;
            this.cost = cost;
        }

        public int getId() {
            return id;
        }

        public double getCost() {
            return cost;
        }

        @Override
        public int compareTo(Node o) {
            return Double.compare(this.cost, o.cost);
        }
    }
}