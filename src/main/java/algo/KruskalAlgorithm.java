package algo;

import graph.*;
import sets.DisjointSets;

import java.util.ArrayList;

/** Subclass of MSTAlgorithm. Computes MST of the graph using Kruskal's algorithm. */
public class KruskalAlgorithm extends MSTAlgorithm {

    /**
     * Constructor for KruskalAlgorithm. Takes the graph
     * @param graph input graph
     */
    public KruskalAlgorithm(Graph graph) {
        super(graph);
    }

    /**
     * Compute minimum spanning tree for this graph. Add edges of MST to
     * edgesMST list. Should use Kruskal's algorithm and DisjointSets class.
     */
    @Override
    public void computeMST() {
        DisjointSets sets = new DisjointSets();
        sets.createSets(numNodes());
        ArrayList<Edge> allEdges = new ArrayList<>();
        getAllEdges(allEdges);
        int count = numNodes() - 1;
        allEdges.sort(Edge::compareTo); // Collections.sort
        for (Edge e : allEdges) {
            if (sets.find(e.getId1()) != sets.find(e.getId2())) {
                addMSTEdge(e);
                sets.union(e.getId1(), e.getId2());
                count--;
            }
            if (count == 0) {
                break;
            }
        }
        printMST();
    }

    /**
     * A helper method to get all the edges from graph adjacency list
     * @param allEdges ArrayList to store all edges
     */
    private void getAllEdges(ArrayList<Edge> allEdges) {
        for (int i = 0; i < numNodes(); i++) {
            Edge curr = getFirstEdge(i);
            while (curr != null) {
                allEdges.add(curr);
                curr = curr.next();
            }
        }
    }

}

