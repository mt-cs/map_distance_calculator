package graph;

import java.awt.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * A class that represents a graph: stores the array of city nodes, the
 * adjacency list, as well as the hash table that maps city names to node ids.
 * Nodes are cities (of type CityNode); edges connect them and the cost of each edge
 * is the distance between the cities.
 * Fill in code in this class. You may add additional methods and variables.
 * You are required to implement a MinHeap from scratch, instead of using Java's built in PriorityQueue.
 */
public class Graph {
    private CityNode[] nodes; // nodes of the graph
    private Edge[] adjacencyList; // adjacency list; for each vertex stores a linked list of edges
    private int numEdges; // total number of edges --> increment as you go through the text * 2 because you need backward
    // Add other variable(s) as needed:
    // add a HashMap to map cities to vertexIds. // use class HashMaps
    HashMap<String, Integer> citiesId;

    /**
     * Constructor. Read graph info from the given file,
     * and create nodes and edges of the graph.
     *
     *   @param filename name of the file that has nodes and edges
     */
    public Graph(String filename) {
        int idx = 0;
        String line;
        citiesId = new HashMap<>();
        try (FileReader f = new FileReader(filename);
             BufferedReader br = new BufferedReader(f)) {
            while (((line = br.readLine()) != null) && (!line.equals("ARCS"))){
                if (line.equals("NODES")) {
                    nodes = new CityNode[Integer.parseInt(br.readLine())];
                    continue;
                }
                String[] city_info = line.split(" ");
                if (city_info.length != 3)
                    throw new IndexOutOfBoundsException();
                CityNode cityNode = new CityNode
                        (city_info[0], Double.parseDouble(city_info[1]), Double.parseDouble(city_info[2]));
                nodes[idx] = cityNode;
                citiesId.put(city_info[0], idx);
                idx++;
            }
            while (line != null) {
                if (line.equals("ARCS")) {
                    numEdges = 0;
                    adjacencyList = new Edge[100];
                    line = br.readLine();
                    continue;
                }
                String[] edge_info = line.split(" ");
                if (edge_info.length != 3)
                    throw new IndexOutOfBoundsException();
                Edge edge1 = new Edge
                        (citiesId.get(edge_info[0]), citiesId.get(edge_info[1]), Integer.parseInt(edge_info[2]));
                adjacencyList[numEdges] = edge1;
                numEdges++;
                Edge edge2 = new Edge
                        (citiesId.get(edge_info[1]), citiesId.get(edge_info[0]), Integer.parseInt(edge_info[2]));
                adjacencyList[numEdges] = edge2;
                numEdges++;
                line = br.readLine();
            }
        } catch (IOException e) {
            System.out.println("No such file: " + filename);
        }
    }
    /**
    public Graph(String filename) {
        int arrSize;
        int idx = 0;
        String cityName;
        String line;
        citiesId = new HashMap<>();
        try {
            Scanner sc = new Scanner(new File(filename));
            line = sc.next();
            while (sc.hasNext() && !line.equals("ARCS")) {
                if (line.equals("NODES")) {
                    arrSize = sc.nextInt();
                    nodes = new CityNode[arrSize];
                    cityName = sc.next();
                } else {
                    cityName = line;
                }
                double xInt = sc.nextDouble();
                double yInt = sc.nextDouble();
                CityNode cityNode = new CityNode(cityName, xInt, yInt);
                nodes[idx] = cityNode;
                citiesId.put(cityName, idx);
                idx++;
                line = sc.next();
            }
            while (sc.hasNext()) {
                if (line.equals("ARCS")) {
                    numEdges = 0;
                    adjacencyList = new Edge[100];
                    line = sc.next();
                    continue;
                }
                int id1 = citiesId.get(line);
                int id2 = citiesId.get(sc.next());
                int cost = sc.nextInt();
                Edge edge1 = new Edge(id1, id2, cost);
                adjacencyList[numEdges] = edge1;
                numEdges++;
                Edge edge2 = new Edge(id2, id1, cost);
                adjacencyList[numEdges] = edge2;
                numEdges++;
                if (sc.hasNext()) {
                    line = sc.next();
                }
            }
        } catch(FileNotFoundException ex) {
            System.out.println("File not found");
        }
    }
    */

    /**
     * Return the number of nodes in the graph
     * @return number of nodes
     */
    public int numNodes() {
        return nodes.length;
    }

    /** Return the head of the linked list that contains all edges outgoing
     * from nodeId
     * @param nodeId id of the node
     * @return head of the linked list of Edges
     */
    public Edge getFirstEdge(int nodeId) {
        return adjacencyList[nodeId];
    }

    /**
     * Return the edges of the graph as a 2D array of points.
     * Called from GUIApp to display the edges of the graph.
     *
     * @return a 2D array of Points.
     * For each edge, we store an array of two Points, v1 and v2.
     * v1 is the source vertex for this edge, v2 is the destination vertex.
     * This info can be obtained from the adjacency list
     */
    public Point[][] getEdges() {
        if (adjacencyList == null || adjacencyList.length == 0) {
            System.out.println("Adjacency list is empty. Load the graph first.");
            return null;
        }
        Point[][] edges2D = new Point[numEdges][2];
        int idx = 0;
        for (int i = 0; i < adjacencyList.length; i++) {
            for (Edge tmp = adjacencyList[i]; tmp != null; tmp = tmp.next(), idx++) {
                edges2D[idx][0] = nodes[tmp.getId1()].getLocation();
                edges2D[idx][1] = nodes[tmp.getId2()].getLocation();
            }
        }
        return edges2D;
    }

    /**
     * Get the nodes of the graph as a 1D array of Points.
     * Used in GUIApp to display the nodes of the graph.
     * @return a list of Points that correspond to nodes of the graph.
     */
    public Point[] getNodes() {
        if (nodes == null) {
            System.out.println("Array of nodes is empty. Load the graph first.");
            return null;
        }
        Point[] nodes = new Point[this.nodes.length];
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = this.nodes[i].getLocation();
        }

        return nodes;
    }

    /**
     * Used in GUIApp to display the names of the cities.
     * @return the list that contains the names of cities (that correspond
     * to the nodes of the graph)
     */
    public String[] getCities() {
        if (nodes == null) {
            return null;
        }
        String[] labels = new String[nodes.length];
        for (int i = 0; i < nodes.length; i++) {
            labels[i] = nodes[i].getCity();
        }
        return labels;
    }

    /**
     * Return the CityNode for the given nodeId
     * @param nodeId id of the node
     * @return CityNode
     */
    public CityNode getNode(int nodeId) {
        return nodes[nodeId];
    }
}
