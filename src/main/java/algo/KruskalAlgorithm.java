package algo;

import graph.*;

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
    public void computeMST() { // call mst edge, rewatch //TODO
        // FILL IN CODE use disjoint sets from class sets
        // get all the edges obtain from the adjacency list form the graph
        // sort by the sort
        // put all vertices in their own set
        // check before add it it will introduce a cycle (WHITEBOARD 4/28)
        // if not insert

        // use arraylist sort method

    }

}

