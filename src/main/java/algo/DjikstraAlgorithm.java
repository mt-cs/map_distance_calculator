package algo;
import graph.*;
import priorityQueue.MinHeap;

/** Subclass of MSTAlgorithm. Uses Djikstra's algorithm to compute MST of the graph. */
public class DjikstraAlgorithm extends MSTAlgorithm{
    private int sourceVertex;
    private MinHeap unvisitedNodes;
    private Node[] table;

    public DjikstraAlgorithm(Graph graph, int sourceVertex) {
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
        int lowestDistance;
        boolean added;

        public Node(int cost, int parent) {
            this.cost = cost;
            this.parent = parent;
            this.lowestDistance = Integer.MAX_VALUE;
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

        public void setLowestDistance(int lowestDistance) {
            this.lowestDistance = lowestDistance;
        }

        /**
         * setter for cost
         * @param cost int
         */
        public void setCost(int cost) {
            this.cost = cost;
        }

        public int getCost() {
            return cost;
        }

        public int getParent() {
            return parent;
        }

        public boolean isAdded() {
            return added;
        }
    }

    /**
     * Compute minimum spanning tree for this graph using Djikstra's algorithm.
     * Add edges of MST to edgesMST list.
     * */
    @Override
    public void computeMST() {
        for (int i = 1; i < numNodes(); i++) {
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
                addMSTEdge(new Edge(nodeId, table[nodeId].parent, table[nodeId].lowestDistance));
            }
            Edge curr = getFirstEdge(nodeId);
            while (curr != null) {
                Node node = table[curr.getId2()];
                if (!node.added) {
                    int newDistance = table[nodeId].lowestDistance + curr.getCost();
                    if (node.lowestDistance > newDistance) {
                        node.setLowestDistance(newDistance);
                        node.setParent(nodeId);
                        unvisitedNodes.reduceKey(curr.getId2(), newDistance);
                    }
                }
                curr = curr.next();
            }
        }
        printMST();
    }
}
