package Graph;

import java.util.*;

public class Graph {

    public static class CreditNetworkAnalysis {
        // Inner class to represent an edge in the graph
        static class Edge {
            int source; // Source node of the edge
            int dest;   // Destination node of the edge
            int weight; // Weight of the edge, representing the cost or loan amount

            // Constructor to initialize an edge
            public Edge(int source, int dest, int weight) {
                this.source = source;
                this.dest = dest;
                this.weight = weight;
            }
        }

        /**
         * Implements Dijkstra's algorithm to find the shortest path from a source node to all other nodes in a weighted graph.
         *
         * @param graph  The representation of the credit network as a list of edges for each node.
         * @param source The starting node for finding shortest paths.
         */
        public static void dijkstra(List<List<Edge>> graph, int source) {
            // Priority queue to select the edge with the minimum weight at each step
            PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));

            // Array to store the shortest distance from the source to each node
            int[] distances = new int[graph.size()];
            Arrays.fill(distances, Integer.MAX_VALUE); // Initialize distances to infinity
            distances[source] = 0; // Distance from source to itself is 0
            pq.add(new Edge(source, source, 0)); // Start with the source node

            // Main loop of Dijkstra's algorithm
            while (!pq.isEmpty()) {
                Edge current = pq.poll(); // Get the edge with the smallest weight

                // Relaxation step: update distances based on current edge weights
                for (Edge edge : graph.get(current.dest)) {
                    if (distances[current.dest] + edge.weight < distances[edge.dest]) {
                        distances[edge.dest] = distances[current.dest] + edge.weight;
                        pq.add(new Edge(edge.source, edge.dest, distances[edge.dest]));
                    }
                }
            }

            // Print shortest distances from the source to all nodes
            System.out.println("Shortest distances from source " + source + ":");
            for (int i = 0; i < distances.length; i++) {
                System.out.println("To " + i + " = " + distances[i]);
            }
        }

        public static void main(String[] args) {
            // Initialize the graph with enough lists for all nodes
            List<List<Edge>> graph = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                graph.add(new ArrayList<>());
            }

            // Manually add edges to the graph
            graph.get(0).add(new Edge(0, 1, 10));
            graph.get(0).add(new Edge(0, 2, 3));
            graph.get(1).add(new Edge(1, 2, 1));
            graph.get(2).add(new Edge(2, 1, 4));
            graph.get(2).add(new Edge(2, 3, 2));
            graph.get(3).add(new Edge(3, 1, 1));

            // Example scenario: Nodes represent entities, edges represent borrowing/lending relationships with their weights as loan amounts or costs.

            // Find the shortest path for fund flow from source node 0
            dijkstra(graph, 0); // Assuming '0' is the source entity
        }
    }

}
/**
 * Shortest distance form 0 to 0 is always 0
 *
 * To 1 = 6 (via nodes 0 -> 2 -> 3 -> 1)
 * To 2 = 3 (directly from node 0)
 * To 3 = 5 (via nodes 0 -> 2)
 */