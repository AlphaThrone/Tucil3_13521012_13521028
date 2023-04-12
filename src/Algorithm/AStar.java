package Algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class AStar {
    
    private final double[][] adjacencyMatrix;
    private final int numberOfNodes;
    
    public AStar(double[][] adjacencyMatrix) {
        this.adjacencyMatrix = adjacencyMatrix;
        this.numberOfNodes = adjacencyMatrix.length;
    }
    
    public ArrayList<Integer> findShortestPath(int startNode, int endNode) {
        // Array untuk menyimpan jarak terpendek dari startNode ke setiap node
        double[] shortestDistances = new double[numberOfNodes];
        Arrays.fill(shortestDistances, Integer.MAX_VALUE);
        shortestDistances[startNode] = 0;
        
        // Priority queue untuk menyimpan node-node yang akan dieksplorasi
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingDouble(node -> node.fScore));
        queue.add(new Node(startNode, 0, heuristic(startNode, endNode), null));
        
        // Array untuk menyimpan node-node yang sudah dieksplorasi
        boolean[] visited = new boolean[numberOfNodes];
        
        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            int currentNodeIndex = currentNode.index;
            
            if (currentNodeIndex == endNode) {
                // Kita sudah mencapai node tujuan, maka kita dapat mengembalikan path terpendek dari startNode ke endNode
                ArrayList<Integer> path = new ArrayList<>();
                Node node = currentNode;
                while (node != null) {
                    path.add(0, node.index);
                    node = node.parent;
                }
                // Munculkan cost
                JPanel panel = new JPanel();
                panel.add(new JLabel("Cost : " + currentNode.gScore));
                JOptionPane.showMessageDialog(null, panel, "Cost (Algoritma A*)", JOptionPane.PLAIN_MESSAGE);

                return path;
            }
            
            if (visited[currentNodeIndex]) {
                // Node ini sudah dieksplorasi, lewati
                continue;
            }
            
            visited[currentNodeIndex] = true;
            
            for (int i = 0; i < numberOfNodes; i++) {
                if (adjacencyMatrix[currentNodeIndex][i] != 0 && !visited[i]) {
                    // Node tetangga yang belum dieksplorasi
                    double tentativeDistance = shortestDistances[currentNodeIndex] + adjacencyMatrix[currentNodeIndex][i];
                    if (tentativeDistance < shortestDistances[i]) {
                        // Node tetangga dapat dicapai dengan jarak terpendek melalui currentNode
                        shortestDistances[i] = tentativeDistance;
                        Node neighborNode = new Node(i, shortestDistances[i], heuristic(i, endNode), currentNode);
                        queue.add(neighborNode);
                    }
                }
            }
        }
        JPanel panel = new JPanel();
        panel.add(new JLabel("Tidak ditemukan path!"));
        JOptionPane.showMessageDialog(null, panel, "Cost (Algoritma A*)", JOptionPane.PLAIN_MESSAGE);
        // Tidak ditemukan path dari startNode ke endNode
        return null;
    }
    
    private int heuristic(int nodeIndex, int endNodeIndex) {
        // Fungsi heuristik, misalnya menggunakan jarak Euclidean
        int x1 = nodeIndex / numberOfNodes;
        int y1 = nodeIndex % numberOfNodes;
        int x2 = endNodeIndex / numberOfNodes;
        int y2 = endNodeIndex % numberOfNodes;
        return (int) Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }
    
    private static class Node {
        public final int index;
        public final double gScore;
        public final double hScore;
        public final double fScore;
        public final Node parent;
        
        public Node(int index, double gScore, double hScore,Node parent) {
            this.index = index;
            this.gScore = gScore;
            this.hScore = hScore;
            this.fScore = gScore + hScore;
            this.parent = parent;
        }
    }
    // public static void main(String[] args) {
    //     // Contoh adjacency matrix
    //     int[][] adjacencyMatrix = {
    //         {0, 4, 0, 0, 0, 0, 0, 8, 0},
    //         {4, 0, 8, 0, 0, 0, 0, 11, 0},
    //         {0, 8, 0, 7, 0, 4, 0, 0, 2},
    //         {0, 0, 7, 0, 9, 14, 0, 0, 0},
    //         {0, 0, 0, 9, 0, 10, 0, 0, 0},
    //         {0, 0, 4, 14, 10, 0, 2, 0, 0},
    //         {0, 0, 0, 0, 0, 2, 0, 1, 6},
    //         {8, 11, 0, 0, 0, 0, 1, 0, 7},
    //         {0, 0, 2, 0, 0, 0, 6, 7, 0}
    //     };
        
    //     AStar astar = new AStar(adjacencyMatrix);
    //     ArrayList<Integer> path = astar.findShortestPath(0, 8);
        
    //     if (path != null) {
    //         System.out.print("Path: ");
    //         for (int i = 0; i < path.size(); i++) {
    //             System.out.print(path.get(i) + " ");
    //         }
    //     } else {
    //         System.out.println("Path not found.");
    //     }
    // }
}
