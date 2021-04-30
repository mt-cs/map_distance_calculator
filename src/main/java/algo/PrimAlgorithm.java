package algo;

import graph.*;

/** Subclass of MSTAlgorithm. Uses Prim's algorithm to compute MST of the graph. */
public class PrimAlgorithm extends MSTAlgorithm {
    private int sourceVertex;

    /**
     * Constructor for PrimAlgorithm. Takes the graph
     * @param graph input graph
     * @param sourceVertex the first vertex of MST
     */
    public PrimAlgorithm(Graph graph, int sourceVertex) {
        super(graph);
        this.sourceVertex = sourceVertex;
        Node[] table = new Node[numNodes()];
    }

    private class Node {
        int cost;
        int parent;
    // store index of the parent not city node
        public Node(int cost, int parent) {
            this.cost = cost;
            this.parent = parent;
        }
    }

    /**
     * Compute minimum spanning tree for this graph using Prim's algorithm.
     * Add edges of MST to edgesMST list.
     * */
    @Override
    public void computeMST() {
        // FILL IN CODE
        // Note: must use a MinHeap and be efficient
        //TODO:
        // when you implement prims use minheap priority queue to find the minimum cost
//        Initialize the table
        for (int i =0; i < numNodes(); i++) {
            //Node n = new Node();
            // cost + infinity except for the source vertex 0
            // parent -1
        }
        // do without minheap then add minheap later, use bool added


//        Repeat numVertices times:
//          v = findMinimumUknownVertex()
//          Mark v as known
//          for each neighbor u of v:
//              if (u is unknown)
//          if table[u].cost > cost of edge from v to u {
//              table[u].cost = cost of edge from v to u table[u].path = v
//          }


    }
}
