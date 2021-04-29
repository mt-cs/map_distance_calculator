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
        allEdges.sort(Edge::compareTo);
        for (Edge e : allEdges) {
            if (sets.find(e.getId1()) != sets.find(e.getId2())) {
                addMSTEdge(e);
                sets.union(e.getId1(), e.getId2());
            }
        }
    }

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

