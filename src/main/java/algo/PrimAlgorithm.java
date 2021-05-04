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

    /**
     * Inner class Node
     */
    private class Node {
        int cost;
        int parent;
        boolean added;

        public Node(int cost, int parent) {
            this.cost = cost;
            this.parent = parent;
            added = false;
        }

        /**
         * setter for added
         * @param added boolean
         */
        public void setAdded(boolean added) {
            this.added = added;
        }

        /**
         * setter for parent
         * @param parent int
         */
        public void setParent(int parent) {
            this.parent = parent;
        }

        /**
         * setter for cost
         * @param cost int
         */
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
        for(int i = 1; i < numNodes(); i++) {
            table[i] = new Node(Integer.MAX_VALUE, -1);
            unvisitedNodes.insert(i, Integer.MAX_VALUE);
        }
        table[0] = new Node(0, -1);
        table[0].setAdded(true);
        for (int i = 0; i < numNodes(); i++) {
            int nodeId;
            if (i == 0) {
                nodeId = sourceVertex;
            } else {
                nodeId = unvisitedNodes.removeMin();
                table[nodeId].setAdded(true);
                addMSTEdge(new Edge(nodeId, table[nodeId].parent, table[nodeId].cost));
            }
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
        }
        printMST();
    }
}
