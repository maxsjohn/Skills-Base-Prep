package main.java.com.graphs;

import java.util.*;

public class Graphs {

    public static void main(String[] args) {
        // Adjacency list representation of the graph
        int[][] adjacencyList = {
                {3},       // Vertex 0 is connected to vertex 3
                {6},       // Vertex 1 is connected to vertex 6
                {3},       // Vertex 2 is connected to vertex 3
                {0, 1, 2, 4}, // Vertex 3 is connected to vertices 0, 1, 2, and 4
                {3, 5},    // Vertex 4 is connected to vertices 3 and 5
                {4},       // Vertex 5 is connected to vertex 4
                {1}        // Vertex 6 is connected to vertex 1
        };

        // Perform DFS traversal
        traversalDFS(adjacencyList);
        // Perform BFS traversal
        traversalBFS(adjacencyList);
    }

    /**
     * Initiates DFS traversal from the starting vertex.
     * @param graph The adjacency list of the graph.
     */
    public static void traversalDFS(int[][] graph) {
        boolean[] seen = new boolean[graph.length]; // Track visited vertices
        List<Integer> values = new ArrayList<>();   // Store DFS traversal order
        traversalDFS(graph, seen, values, 0);       // Start DFS from vertex 0

        System.out.println("DFS Traversal: " + values);
    }

    /**
     * Recursive method for DFS traversal.
     * @param graph The adjacency list of the graph.
     * @param seen Array to track visited vertices.
     * @param values List to store the order of visited vertices.
     * @param vertex The current vertex being visited.
     */
    private static void traversalDFS(int[][] graph, boolean[] seen, List<Integer> values, int vertex) {
        values.add(vertex);      // Add current vertex to traversal order
        seen[vertex] = true;     // Mark current vertex as seen

        // Traverse all adjacent vertices
        for (int connection : graph[vertex]) {
            if (!seen[connection]) {
                traversalDFS(graph, seen, values, connection); // Recursively visit unvisited adjacent vertices
            }
        }
    }
    /**
     * ################
     * Breadth Firts Search for Adjacency list
     * #############
     */

    /**
     * Performs BFS traversal from the starting vertex.
     * @param graph The adjacency list of the graph.
     */
    public static void traversalBFS(int[][] graph) {
        Queue<Integer> queue = new LinkedList<>();  // Queue for BFS
        boolean[] seen = new boolean[graph.length]; // Track visited vertices
        List<Integer> values = new ArrayList<>();   // Store BFS traversal order

        queue.offer(0);         // Start BFS from vertex 0
        seen[0] = true;         // Mark starting vertex as seen

        while (!queue.isEmpty()) {
            Integer vertex = queue.poll(); // Dequeue the next vertex
            values.add(vertex);            // Add current vertex to traversal order

            // Traverse all adjacent vertices
            for (int connection : graph[vertex]) {
                if (!seen[connection]) {
                    queue.offer(connection);   // Enqueue unvisited adjacent vertex
                    seen[connection] = true;   // Mark adjacent vertex as seen
                }
            }
        }

        System.out.println("BFS Traversal: " + values);
    }
}