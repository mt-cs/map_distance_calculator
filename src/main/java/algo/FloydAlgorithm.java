package algo;
import graph.Edge;
import graph.Graph;

public class FloydAlgorithm {
    private final Graph graph; // stores the graph of CityNode-s and edges connecting them
    private final int[][] distance;
    private final int n;
    private final int I = 99999999; // for INFINITY

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

    public void printAllShortestDistance() {
        System.out.println("Shortest Distance Between Cities");
        System.out.println("---------------------------------");
        for (int i = 0; i < n; i++) {
            System.out.println("START -> " + graph.getNode(i).getCity());
            System.out.println("\nDestination: Distance");
            for (int j = 0; j < n; j++) {
                System.out.println(graph.getNode(j).getCity() + ": " + distance[i][j]);
            }
            System.out.println("---------------------------------");
        }
    }

    /**
     * Get shortest path between two cities
     * @param id1 city id 1
     * @param id2 city id 2
     * @return shortest path between id1 and id2
     */
    public int calculateShortestPath(int id1, int id2) {
        return distance[id1][id2];
    }

    /**
     * Run floyd algorithm to print out all shortest path from MST Driver
     */
    public void runFloyd() {
        getDistanceTable();
        computeShortestDistance();
        printAllShortestDistance();
    }
}
