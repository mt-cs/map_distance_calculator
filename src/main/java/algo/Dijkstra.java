package algo;

import graph.CityNode;
import graph.Edge;
import graph.Graph;
import priorityQueue.MinHeap;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/** Class graph.Dijkstra. Implementation of graph.Dijkstra's
 *  algorithm on the graph for finding the shortest path.
 *  Fill in code. You may add additional helper methods or classes.
 */
public class Dijkstra {
	private Graph graph; // stores the graph of CityNode-s and edges connecting them
	private ArrayList<Integer> shortestPath;

      /** Constructor
	 *
     * @param graph graph
	 */
	public Dijkstra(Graph graph) {
	    this.graph = graph;
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
            // FILL IN CODE

		int startNode = graph.getId(origin);
		int destinationNode = graph.getId(destination);
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
