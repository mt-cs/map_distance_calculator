package graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TesterGraph {
    public static void main(String[] args) {
        Graph g = new Graph("/Users/marisatania/Documents/cs545/projects/project5-mt-cs/input/USA.txt");
        // FILL IN CODE
        try (FileReader f = new FileReader("/Users/marisatania/Documents/cs545/projects/project5-mt-cs/input/USA.txt");
             BufferedReader br = new BufferedReader(f)) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                br.readLine();


            }
        }
        catch (IOException e) {
            System.out.println("No such file: ");
        }
    }
}
