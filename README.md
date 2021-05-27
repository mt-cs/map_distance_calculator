# US Map MST and SSSP

Author: Marisa Tania  

## About This Project
This project implements Prim's and Kruskal's algorithms for computing the minimum spanning tree (MST), as well as Djikstra's and Floyd's algorithms for computing Single-Source-Shortest-Path (SSSP) of all major cities in the United States. The graph costs are proportional to the distance between cities. This project has a graphical user interface (GUI) that will display the graph of cities on the "map"1 (the "map" is simply an image), and the minimal spanning tree:


<img width="579" alt="mst" src="https://user-images.githubusercontent.com/60201466/119550403-3c7eb380-bd4d-11eb-82f5-6209691f91d0.png">
Figure 1: The "map" of the USA shows "nodes" (major cities) and edges connecting them, as well as the edges in the MST (shown in blue). Users can pick the algorithm that they want to use to display the result.


<img width="580" alt="sssp" src="https://user-images.githubusercontent.com/60201466/119550482-51f3dd80-bd4d-11eb-9f11-5c309f1efd9e.png">
Figure 2: Click two cities and the edges in SSSP (shown in magenta) will appear.


### Included Files
There are several files included. These are:
   - <b>MSTDriver</b>: The Driver class that takes the name of the input.txt file as a command line argument.
   - <b>Djikstra.java</b>: Computing SSSP between two cities.
   - <b>DjikstraAlgorithm.java</b>: Computing SSSP from San Francisco to all other major US cities.
   - <b>FloydAlgorithm.java</b>: Computing SSSP distance from each cities to the others using adjacency matrix.
   - <b>KruskalAlgoritm.java</b>: Computing MST using the Disjoint Sets data structure.
   - <b>PrimsAlgorithm.java</b>: Computing MST given the source vertex using MinHeap PriorityQueue
   - <b>MSTAlgorithm</b>: An abstract class that is the parent of classes PrimAlgorithm and KruskalAlgorithm.
   - <b>Graph.java</b>: A class that represents a graph: stores the array of city nodes, the adjacency list, as well as the hash table that maps city names to node ids.
   - <b>CityNode.java</b>: A class that represents a node of the graph. Contains the name of the city and the location  (x, y coordinates) on the map.
   - <b>Edge.java</b>: Represents an edge in the adjacency list of the graph. Implements Comparable. Compares Edges based on the cost.
   - <b>MinHeap.java</b>: A priority queue: represented by the min heap. Used in Prim's and Djikstra's.
   - <b>DisjointSets.java</b>: Represents the Disjoint Sets data structure. Used in Kruskal's.
   - <b>GUIApp.java</b>: A GUI for MST
   - <b>GUIAppForDjikstra.java</b>: A GUI for SSSP
   

### Program Output Example
```bash
Shortest Distance Between Cities
---------------------------------
START -> SanFrancisco

Destination: Distance
SanFrancisco: 0
LosAngeles: 320
Orlando: 2381
WashingtonDC: 2050
NewYork: 2260
Dallas: 1361
Portland: 351
Minneapolis: 1400
OklahomaCity: 1250
Raleigh: 2250
NewOrleans: 1701
Denver: 850
SaltLakeCity: 530
Seattle: 581
Chicago: 1670
Atlanta: 2021
Boston: 2400
Phoenix: 611
LasVegas: 380
KansasCity: 1320
---------------------------------

```
