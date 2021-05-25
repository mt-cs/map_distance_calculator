# US Map MST and SSSP

Author: Marisa Tania  

## About This Project
This project implements Prim's and Kruskal's algorithms for computing the minimum spanning tree (MST), as well as Djikstra's and Floyd's algorithms for computing Single-Source-Shortest-Path (SSSP) of all major cities in the United States.The graph costs are proportional to the distance between cities. This project has a graphical user interface (GUI) that will display the graph of cities on the "map"1 (the "map" is simply an image), and the minimal spanning tree:

<img width="579" alt="mst" src="https://user-images.githubusercontent.com/60201466/119550403-3c7eb380-bd4d-11eb-82f5-6209691f91d0.png">
Figure 1: The "map" of the USA shows "nodes" (major cities) and edges connecting them, as well as the edges in the MST (shown in blue). Users can pick the algorithm that they want to use to display the result.

<img width="580" alt="sssp" src="https://user-images.githubusercontent.com/60201466/119550482-51f3dd80-bd4d-11eb-9f11-5c309f1efd9e.png">
Figure 2: Click two cities and the edges in SSSP (shown in magenta) will appear.


### Program Options
Each portion of the GUI display can show results on the map. Here are the options:
```bash
$ ./inspector -h
Usage: ./inspector [-ahlrst] [-p procfs_dir]

Options:
    * -a              Display all (equivalent to -lrst, default)
    * -h              Help/usage information
    * -l              Task List
    * -p procfs_dir   Change the expected procfs mount point (default: /proc)
    * -r              Hardware Information
    * -s              System Information
    * -t              Task Information
```
The task list, hardware information, system information, and task information can all be turned on/off with the command line options. By default, all of them are displayed.

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
   

### Program Output
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
START -> LosAngeles

Destination: Distance
SanFrancisco: 320
LosAngeles: 0
Orlando: 2123
WashingtonDC: 2080
NewYork: 2170
Dallas: 1103
Portland: 671
Minneapolis: 1590
OklahomaCity: 1043
Raleigh: 2113
NewOrleans: 1443
Denver: 740
SaltLakeCity: 850
Seattle: 901
Chicago: 1560
Atlanta: 1763
Boston: 2310
Phoenix: 353
LasVegas: 220
KansasCity: 1210
---------------------------------
START -> Orlando

Destination: Distance
SanFrancisco: 2381
LosAngeles: 2123
Orlando: 0
WashingtonDC: 910
NewYork: 1120
Dallas: 1020
Portland: 2515
Minneapolis: 1242
OklahomaCity: 1241
Raleigh: 710
NewOrleans: 761
Denver: 1641
SaltLakeCity: 1995
Seattle: 2360
Chicago: 890
Atlanta: 360
Boston: 1260
Phoenix: 1770
LasVegas: 2001
KansasCity: 1341
---------------------------------
START -> WashingtonDC

Destination: Distance
SanFrancisco: 2050
LosAngeles: 2080
Orlando: 910
WashingtonDC: 0
NewYork: 210
Dallas: 1210
Portland: 2214
Minneapolis: 872
OklahomaCity: 1431
Raleigh: 200
NewOrleans: 951
Denver: 1340
SaltLakeCity: 1694
Seattle: 2072
Chicago: 520
Atlanta: 550
Boston: 350
Phoenix: 1960
LasVegas: 1870
KansasCity: 1252
---------------------------------
START -> NewYork

Destination: Distance
SanFrancisco: 2260
LosAngeles: 2170
Orlando: 1120
WashingtonDC: 210
NewYork: 0
Dallas: 1420
Portland: 2304
Minneapolis: 900
OklahomaCity: 1530
Raleigh: 410
NewOrleans: 1161
Denver: 1430
SaltLakeCity: 1784
Seattle: 2100
Chicago: 610
Atlanta: 760
Boston: 140
Phoenix: 2170
LasVegas: 1960
KansasCity: 1280
---------------------------------
START -> Dallas

Destination: Distance
SanFrancisco: 1361
LosAngeles: 1103
Orlando: 1020
WashingtonDC: 1210
NewYork: 1420
Dallas: 0
Portland: 1495
Minneapolis: 851
OklahomaCity: 221
Raleigh: 1010
NewOrleans: 340
Denver: 621
SaltLakeCity: 975
Seattle: 1541
Chicago: 1190
Atlanta: 660
Boston: 1560
Phoenix: 750
LasVegas: 981
KansasCity: 471
---------------------------------
START -> Portland

Destination: Distance
SanFrancisco: 351
LosAngeles: 671
Orlando: 2515
WashingtonDC: 2214
NewYork: 2304
Dallas: 1495
Portland: 0
Minneapolis: 1430
OklahomaCity: 1274
Raleigh: 2414
NewOrleans: 1835
Denver: 874
SaltLakeCity: 520
Seattle: 230
Chicago: 1694
Atlanta: 2155
Boston: 2444
Phoenix: 962
LasVegas: 731
KansasCity: 1344
---------------------------------
START -> Minneapolis

Destination: Distance
SanFrancisco: 1400
LosAngeles: 1590
Orlando: 1242
WashingtonDC: 872
NewYork: 900
Dallas: 851
Portland: 1430
Minneapolis: 0
OklahomaCity: 630
Raleigh: 1072
NewOrleans: 960
Denver: 850
SaltLakeCity: 1204
Seattle: 1200
Chicago: 352
Atlanta: 882
Boston: 1040
Phoenix: 1320
LasVegas: 1380
KansasCity: 380
---------------------------------
START -> OklahomaCity

Destination: Distance
SanFrancisco: 1250
LosAngeles: 1043
Orlando: 1241
WashingtonDC: 1431
NewYork: 1530
Dallas: 221
Portland: 1274
Minneapolis: 630
OklahomaCity: 0
Raleigh: 1231
NewOrleans: 561
Denver: 400
SaltLakeCity: 754
Seattle: 1320
Chicago: 982
Atlanta: 881
Boston: 1670
Phoenix: 690
LasVegas: 921
KansasCity: 250
---------------------------------
START -> Raleigh

Destination: Distance
SanFrancisco: 2250
LosAngeles: 2113
Orlando: 710
WashingtonDC: 200
NewYork: 410
Dallas: 1010
Portland: 2414
Minneapolis: 1072
OklahomaCity: 1231
Raleigh: 0
NewOrleans: 751
Denver: 1540
SaltLakeCity: 1894
Seattle: 2272
Chicago: 720
Atlanta: 350
Boston: 550
Phoenix: 1760
LasVegas: 1991
KansasCity: 1331
---------------------------------
START -> NewOrleans

Destination: Distance
SanFrancisco: 1701
LosAngeles: 1443
Orlando: 761
WashingtonDC: 951
NewYork: 1161
Dallas: 340
Portland: 1835
Minneapolis: 960
OklahomaCity: 561
Raleigh: 751
NewOrleans: 0
Denver: 961
SaltLakeCity: 1315
Seattle: 1881
Chicago: 931
Atlanta: 401
Boston: 1301
Phoenix: 1090
LasVegas: 1321
KansasCity: 580
---------------------------------
START -> Denver

Destination: Distance
SanFrancisco: 850
LosAngeles: 740
Orlando: 1641
WashingtonDC: 1340
NewYork: 1430
Dallas: 621
Portland: 874
Minneapolis: 850
OklahomaCity: 400
Raleigh: 1540
NewOrleans: 961
Denver: 0
SaltLakeCity: 354
Seattle: 920
Chicago: 820
Atlanta: 1281
Boston: 1570
Phoenix: 761
LasVegas: 530
KansasCity: 470
---------------------------------
START -> SaltLakeCity

Destination: Distance
SanFrancisco: 530
LosAngeles: 850
Orlando: 1995
WashingtonDC: 1694
NewYork: 1784
Dallas: 975
Portland: 520
Minneapolis: 1204
OklahomaCity: 754
Raleigh: 1894
NewOrleans: 1315
Denver: 354
SaltLakeCity: 0
Seattle: 750
Chicago: 1174
Atlanta: 1635
Boston: 1924
Phoenix: 1115
LasVegas: 884
KansasCity: 824
---------------------------------
START -> Seattle

Destination: Distance
SanFrancisco: 581
LosAngeles: 901
Orlando: 2360
WashingtonDC: 2072
NewYork: 2100
Dallas: 1541
Portland: 230
Minneapolis: 1200
OklahomaCity: 1320
Raleigh: 2272
NewOrleans: 1881
Denver: 920
SaltLakeCity: 750
Seattle: 0
Chicago: 1552
Atlanta: 2000
Boston: 2240
Phoenix: 1192
LasVegas: 961
KansasCity: 1390
---------------------------------
START -> Chicago

Destination: Distance
SanFrancisco: 1670
LosAngeles: 1560
Orlando: 890
WashingtonDC: 520
NewYork: 610
Dallas: 1190
Portland: 1694
Minneapolis: 352
OklahomaCity: 982
Raleigh: 720
NewOrleans: 931
Denver: 820
SaltLakeCity: 1174
Seattle: 1552
Chicago: 0
Atlanta: 530
Boston: 750
Phoenix: 1581
LasVegas: 1350
KansasCity: 732
---------------------------------
START -> Atlanta

Destination: Distance
SanFrancisco: 2021
LosAngeles: 1763
Orlando: 360
WashingtonDC: 550
NewYork: 760
Dallas: 660
Portland: 2155
Minneapolis: 882
OklahomaCity: 881
Raleigh: 350
NewOrleans: 401
Denver: 1281
SaltLakeCity: 1635
Seattle: 2000
Chicago: 530
Atlanta: 0
Boston: 900
Phoenix: 1410
LasVegas: 1641
KansasCity: 981
---------------------------------
START -> Boston

Destination: Distance
SanFrancisco: 2400
LosAngeles: 2310
Orlando: 1260
WashingtonDC: 350
NewYork: 140
Dallas: 1560
Portland: 2444
Minneapolis: 1040
OklahomaCity: 1670
Raleigh: 550
NewOrleans: 1301
Denver: 1570
SaltLakeCity: 1924
Seattle: 2240
Chicago: 750
Atlanta: 900
Boston: 0
Phoenix: 2310
LasVegas: 2100
KansasCity: 1420
---------------------------------
START -> Phoenix

Destination: Distance
SanFrancisco: 611
LosAngeles: 353
Orlando: 1770
WashingtonDC: 1960
NewYork: 2170
Dallas: 750
Portland: 962
Minneapolis: 1320
OklahomaCity: 690
Raleigh: 1760
NewOrleans: 1090
Denver: 761
SaltLakeCity: 1115
Seattle: 1192
Chicago: 1581
Atlanta: 1410
Boston: 2310
Phoenix: 0
LasVegas: 231
KansasCity: 940
---------------------------------
START -> LasVegas

Destination: Distance
SanFrancisco: 380
LosAngeles: 220
Orlando: 2001
WashingtonDC: 1870
NewYork: 1960
Dallas: 981
Portland: 731
Minneapolis: 1380
OklahomaCity: 921
Raleigh: 1991
NewOrleans: 1321
Denver: 530
SaltLakeCity: 884
Seattle: 961
Chicago: 1350
Atlanta: 1641
Boston: 2100
Phoenix: 231
LasVegas: 0
KansasCity: 1000
---------------------------------
START -> KansasCity

Destination: Distance
SanFrancisco: 1320
LosAngeles: 1210
Orlando: 1341
WashingtonDC: 1252
NewYork: 1280
Dallas: 471
Portland: 1344
Minneapolis: 380
OklahomaCity: 250
Raleigh: 1331
NewOrleans: 580
Denver: 470
SaltLakeCity: 824
Seattle: 1390
Chicago: 732
Atlanta: 981
Boston: 1420
Phoenix: 940
LasVegas: 1000
KansasCity: 0
---------------------------------

```
