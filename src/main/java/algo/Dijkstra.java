package algo;

import graph.CityNode;
import graph.Edge;
import graph.Graph;
import priorityQueue.MinHeap;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

/** Class graph.Dijkstra. Implementation of graph.Dijkstra's
 *  algorithm on the graph for finding the shortest path.
 *  Fill in code. You may add additional helper methods or classes.
 */
public class Dijkstra {
	private final Graph graph; // stores the graph of CityNode-s and edges connecting them
	private ArrayList<Integer> shortestPath;
	private final MinHeap unvisitedNodes;
	private final Node[] table;

	/**
	 * Inner class Node
	 */
	private static class Node {
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
        unvisitedNodes = new MinHeap(graph.numNodes() + 1);
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

		computeGraph(startNode);
		Node current = table[destinationNode];
		shortestPath.add(destinationNode);
		while (current.parent != -1) {
			shortestPath.add(current.parent);
			current = table[current.parent];
		}
		Collections.reverse(shortestPath);
		printShortestPath();
    }

	/**
	 * Compute SSSP for this graph using Djikstra's algorithm.
	 * */
	private void computeGraph(int startNode) {
		for (int i = 0; i < graph.numNodes(); i++) {
			table[i] = new Node(-1);
			unvisitedNodes.insert(i + 1, Integer.MAX_VALUE);
		}
		table[startNode].setAdded(true);
		table[startNode].setLowestDistance(0);
		unvisitedNodes.reduceKey(startNode + 1, 0);
		for (int i = 0; i < graph.numNodes(); i++) {
			int nodeId = unvisitedNodes.removeMin() - 1;
			table[nodeId].setAdded(true);
			Edge curr = graph.getFirstEdge(nodeId);
			while (curr != null) {
				Node node = table[curr.getId2()];
				if (!node.added) {
					int newDistance = table[nodeId].lowestDistance + curr.getCost();
					if (node.lowestDistance > newDistance) {
						node.setLowestDistance(newDistance);
						node.setParent(nodeId);
						unvisitedNodes.reduceKey(curr.getId2() + 1, newDistance);
					}
				}
				curr = curr.next();
			}
		}
	}

	/**
	 * a helper method to print all the shortest path
	 */
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
