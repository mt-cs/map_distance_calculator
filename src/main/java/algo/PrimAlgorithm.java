package algo;

import graph.*;
import priorityQueue.MinHeap;

/** Subclass of MSTAlgorithm. Uses Prim's algorithm to compute MST of the graph. */
public class PrimAlgorithm extends MSTAlgorithm {
    private int sourceVertex;
    private MinHeap unvisitedNodes;
    private Node[] table;

    /**
     * Constructor for PrimAlgorithm. Takes the graph
     * @param graph input graph
     * @param sourceVertex the first vertex of MST
     */
    public PrimAlgorithm(Graph graph, int sourceVertex) {
        super(graph);
        this.sourceVertex = sourceVertex;
        table = new Node[numNodes()];
        unvisitedNodes = new MinHeap(numNodes());
    }

    private class Node {
        int cost;
        int parent;
        boolean added;
        // store index of the parent not city node
        public Node(int cost, int parent) {
            this.cost = cost;
            this.parent = parent;
            added = false;
        }

        public void setAdded(boolean added) {
            this.added = added;
        }

        public void setParent(int parent) {
            this.parent = parent;
        }

        public void setCost(int cost) {
            this.cost = cost;
        }
    }

    /**
     * Compute minimum spanning tree for this graph using Prim's algorithm.
     * Add edges of MST to edgesMST list.
     * */
    @Override
    public void computeMST() {
        // Initialize the table
        for (int i = 1; i < numNodes(); i++) {
            table[i] = new Node(Integer.MAX_VALUE, -1);
            unvisitedNodes.insert(i, Integer.MAX_VALUE);
        }
        table[0] = new Node(0, -1);
        table[0].setAdded(true);

        // initially min heap is everything but the source
        for (int i = 0; i < numNodes(); i++) {
        // Repeat numVertices times:
            // v = findMinimumUnknownVertex()
            // Mark v as known
            int nodeId;
            if (i == 0) {
                nodeId = sourceVertex;
            } else {
                nodeId = unvisitedNodes.removeMin();
                table[nodeId].setAdded(true);
            }
            // update table when we mark the node is known, update parent and cost
            // adjacency list at that vertex
            Edge curr = getFirstEdge(nodeId);
            while (curr != null) {
                if (!table[curr.getId2()].added) {
                    if (table[curr.getId2()].cost > curr.getCost()) {
                        table[curr.getId2()].setCost(curr.getCost());
                        table[curr.getId2()].setParent(curr.getId1());
                        unvisitedNodes.reduceKey(curr.getId2(), curr.getCost());
                    }
                }
                curr = curr.next();
            }
//          for each neighbor u of v:
//              if (u is unknown)
//              if table[u].cost > cost of edge from v to u {
//                  table[u].cost = cost of edge from v to u table[u].path = v
//              }
        }
        printMST();
    }
}
