package algo;

import graph.CityNode;
import graph.Edge;
import graph.Graph;
import priorityQueue.MinHeap;

import java.awt.*;
import java.util.ArrayList;

/** Class graph.Dijkstra. Implementation of graph.Dijkstra's
 *  algorithm on the graph for finding the shortest path.
 *  Fill in code. You may add additional helper methods or classes.
 */
public class Dijkstra {
	private Graph graph; // stores the graph of CityNode-s and edges connecting them
	private ArrayList<Integer> shortestPath;
	private MinHeap unvisitedNodes;
	private Node[] table;

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

	/** Constructor
	 *
     * @param graph graph
	 */
	public Dijkstra(Graph graph) {
	    this.graph = graph;
        table = new Node[graph.numNodes()];
        unvisitedNodes = new MinHeap(graph.numNodes());
	}

	/**
	 * Returns the shortest path between the origin vertex and the destination vertex.
	 * The result is stored in shortestPathEdges.
	 * This function is called from GUIApp, when the user clicks on two cities.
	 * @param origin source node
	 * @param destination destination node
	 */
	public void computeShortestPath(CityNode origin, CityNode destination) {
	    shortestPath = new ArrayList<>();

		int startNode = graph.getId(origin);
		int destinationNode = graph.getId(destination);

		for (int i = 1; i < graph.numNodes(); i++) {
			table[i] = new Node(-1);
			unvisitedNodes.insert(i, Integer.MAX_VALUE);
		}
		table[0] = new Node(-1);
		table[0].setAdded(true);
		table[0].setLowestDistance(0);

		for (int i = 0; i < graph.numNodes(); i++) {
			int nodeId;
			if (i == 0) {
				nodeId = startNode;
			} else {
				nodeId = unvisitedNodes.removeMin();
				table[nodeId].setAdded(true);
			}
			shortestPath.add(nodeId);
			if (nodeId == destinationNode) {
			    printShortestPath();
			    break;
            }
			Edge curr = graph.getFirstEdge(nodeId);
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
    }

    private void printShortestPath() {
	    for (int path : shortestPath) {
            System.out.println(graph.getNode(path).getCity());
        }
    }

    /**
     * Return the shortest path as a 2D array of Points.
     * Each element in the array is another array that has 2 Points:
     * these two points define the beginning and end of a line segment.
     * @return 2D array of points
     */
    public Point[][] getPath() {
	    if (shortestPath == null)
	        return null;
	    return graph.getPath(shortestPath);
    }
}
