package algo;

import graph.Edge;
import graph.Graph;

import java.util.Vector;

public class FloydAlgorithm {
    private final Graph graph; // stores the graph of CityNode-s and edges connecting them
    private int[][] distance;
    private int n;
    private int I = 99999999; // for INFINITY

    /**
     * Constructor
     * @param graph adjacency list
     */
    public FloydAlgorithm(Graph graph) {
        n = graph.numNodes();
        this.graph = graph;
        distance = new int[n][n];
    }

    /**
     * Initialize the distance table
     */
    public void getDistanceTable() {
        // initialize value to infinity and 0
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    distance[i][j] = 0;
                } else {
                    distance[i][j] = I;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            Edge curr = graph.getFirstEdge(i);
            while (curr != null) {
                int j = curr.getId2();
                distance[i][j] = curr.getCost();
                curr = curr.next();
            }
        }
    }

    /**
     * update the shortest path in the distance table
     */
    public void computeShortestDistance() {
        for (int k = 0; k < n; k++) {
            for (int j = 0; j < n; j++) {
                for (int i = 0; i < n; i++) {
                    if (distance[i][k] + distance[k][j] < distance[i][j]) {
                        distance[i][j] = distance[i][k] + distance[k][j];
                    }
                }
            }
        }
    }

    public void printMatrix()
    {
        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < n; j++)
            {
                if (distance[i][j] == I) {
                    System.out.print("I ");
                } else {
                    System.out.print(distance[i][j] + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }


    public int calculateShortestPath(int id1, int id2) {
        return distance[id1][id2];
    }
}
