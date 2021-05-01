package graph;

import algo.KruskalAlgorithm;
import algo.PrimAlgorithm;
import priorityQueue.MinHeap;
import sets.DisjointSets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TesterGraph {
    public static void main(String[] args) {
        Graph g = new Graph("/Users/marisatania/Documents/cs545/projects/project5-mt-cs/input/USA.txt");
//        // Disjoint Sets
//        DisjointSets sets = new DisjointSets();
//        sets.createSets(10);
//        sets.union(0, 1);
//        sets.union(2, 3);
//        sets.union(7, 8);
//        sets.union(2, 5);
//        sets.union(3, 4);
//        sets.union(2, 6);
//        sets.union(3, 8);
//        System.out.println(sets.find(2));
//        System.out.println(sets.find(5));
//
//        //Kruskal
//        KruskalAlgorithm kruskal = new KruskalAlgorithm(g);
//        kruskal.computeMST();
//        kruskal.printMST();
//
//        //MinHeap
//        MinHeap minheap = new MinHeap(20);
//        minheap.insert(14, 140);
//        minheap.insert(0, 100);
//        minheap.insert(1, 300);
//        minheap.insert(2, 50);
//        minheap.reduceKey(1, 20);
//        System.out.println(minheap.removeMin());

        //Prim
        PrimAlgorithm prim = new PrimAlgorithm(g, 0);
        prim.computeMST();


    }
}
