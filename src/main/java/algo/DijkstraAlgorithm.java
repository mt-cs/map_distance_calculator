package algo;
import graph.*;
import priorityQueue.MinHeap;

/** Computing SSSP. Uses Djikstra's algorithm to compute the shortest path from San Francisco to every other city in the graph. */
public class DijkstraAlgorithm extends MSTAlgorithm{
    private int sourceVertex;
    private MinHeap unvisitedNodes;
    private Node[] table;

    public DijkstraAlgorithm(Graph graph, int sourceVertex) {
        super(graph);
        this.sourceVertex = sourceVertex;
        table = new Node[numNodes()];
        unvisitedNodes = new MinHeap(numNodes());
    }

    /**
     * Inner class Node
     */
    private class Node {
        int parent;
        int lowestDistance;
        boolean added;

        public Node(int parent) {
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

        /**
         * setter for cost
         * @param lowestDistance int
         */
        public void setLowestDistance(int lowestDistance) {
            this.lowestDistance = lowestDistance;
        }

    }

    /**
     * Compute SSSP for this graph using Djikstra's algorithm.
     * */
    @Override
    public void computeMST() {
        for (int i = 1; i < numNodes(); i++) {
            table[i] = new Node(-1);
            unvisitedNodes.insert(i, Integer.MAX_VALUE);
        }
        table[0] = new Node(-1);
        table[0].setAdded(true);
        table[0].setLowestDistance(0);
        int nodeId;
        for (int i = 0; i < numNodes(); i++) {
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
