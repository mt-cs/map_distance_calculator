package graph;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

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
    private int numEdges; // total number of edges
    private final HashMap<String, Integer> citiesId; // a HashMap to map cities to vertexIds.
    // Add other variable(s) as needed:

    /**
     * Constructor. Read graph info from the given file,
     * and create nodes and edges of the graph.
     *
     * @param filename name of the file that has nodes and edges
     */
    public Graph(String filename) {
        int idx = 0;
        String line;
        citiesId = new HashMap<>();
        try (FileReader f = new FileReader(filename);
             BufferedReader br = new BufferedReader(f)) {
            while (((line = br.readLine()) != null) && (!line.equals("ARCS"))){
                idx = getNodes(idx, line, br);
            }
            while (line != null) {
                line = getArcs(line, br);
            }
        } catch (IOException e) {
            System.out.println("No such file: " + filename);
        }
    }

    /**
     * A helper method to get all the nodes from input file
     * @param idx current index
     * @param line current line
     * @param br buffered reader
     * @return idx index
     * @throws IOException if file is not found
     */
    private int getNodes(int idx, String line, BufferedReader br) throws IOException {
        if (line.equals("NODES")) {
            nodes = new CityNode[Integer.parseInt(br.readLine())];
            return idx;
        }
        String[] city_info = line.split(" ");
        if (city_info.length != 3)
            throw new IndexOutOfBoundsException();
        CityNode cityNode = new CityNode
                (city_info[0], Double.parseDouble(city_info[1]), Double.parseDouble(city_info[2]));
        nodes[idx] = cityNode;
        citiesId.put(city_info[0], idx);
        idx++;
        return idx;
    }

    /**
     * A helper method to get all of the edges from input file
     * @param line current line
     * @param br buffered reader
     * @return line
     * @throws IOException if file not found
     */
    private String getArcs(String line, BufferedReader br) throws IOException {
        if (line.equals("ARCS")) {
            numEdges = 0;
            adjacencyList = new Edge[20];
            line = br.readLine();
            return line;
        }
        String[] edge_info = line.split(" ");
        if (edge_info.length != 3)
            throw new IndexOutOfBoundsException();
        int id1 = citiesId.get(edge_info[0]);
        int id2 = citiesId.get(edge_info[1]);
        int cost = Integer.parseInt(edge_info[2]);
        Edge edge1 = new Edge (id1, id2, cost);
        insertAdjacencyList(id1, edge1);
        Edge edge2 = new Edge (id2, id1, cost);
        insertAdjacencyList(id2, edge2);
        line = br.readLine();
        return line;
    }

    /**
     * A helper method to insert an edge to the adjacency linked list
     * @param idx index
     * @param edge Edge
     */
    private void insertAdjacencyList(int idx, Edge edge) {
        if (adjacencyList[idx] != null) {
            Edge current = adjacencyList[idx];
            while(current.next() != null) {
                current = current.next();
            }
            current.setNext(edge);
        } else {
            adjacencyList[idx] = edge;
        }
        numEdges++;
    }

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
        for (Edge edge : adjacencyList) {
            for (Edge tmp = edge; tmp != null; tmp = tmp.next(), idx++) {
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

    /** Take a list of node ids on the path and return an array where each
     * element contains two points (an edge between two consecutive nodes)
     * @param pathOfNodes A list of node ids on the path
     * @return array where each element is an array of 2 points
     */
    public Point[][] getPath(ArrayList<Integer> pathOfNodes) {
        int i = 0;
        Point[][] edges2D = new Point[pathOfNodes.size()-1][2];
        Integer vPrev = pathOfNodes.get(0); // node id

        for (int k = 1; k < pathOfNodes.size(); k++) {
            Integer vCurr = pathOfNodes.get(k); // node id
            // Need to add an edge between vPrev and vCurr
            edges2D[i][0] = (nodes[vPrev]).getLocation();
            edges2D[i][1] = (nodes[vCurr]).getLocation();
            i++;
            vPrev = vCurr;
        }

        return edges2D;
    }

    /**
     * Take the location of the mouse click as a parameter, and return the node
     * of the graph at this location. Needed in GUIApp class.
     * @param loc the location of the mouse click
     * @return reference to the corresponding CityNode
     */
    public CityNode getNode(Point loc) {
        for (CityNode v : nodes) {
            Point p = v.getLocation();
            if ((Math.abs(loc.x - p.x) < 5) && (Math.abs(loc.y - p.y) < 5))
                return v;
        }
        return null;
    }

    /**
     * Returns an integer id of the given city node
     * @param city node of the graph
     * @return its integer id
     */
    public int getId(CityNode city) {
        return citiesId.get(city.getCity());
    }
}
