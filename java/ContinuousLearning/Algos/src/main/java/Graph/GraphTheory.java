package Graph;

import java.util.*;

public class GraphTheory {

    /**
     * Graph theory can be applied to model the complex interconnections in
     * financial markets. A graph can represent entities such as assets, companies,
     * or currencies as nodes, and the relationships between them (such as ownership,
     * trading pairs, or correlations) as edges. Here's a simplified example
     * using adjacency lists to model and analyze relationships in a financial market:
     */

    static class FinancialGraph {
        static class Node {
            String id; // Unique identifier for the node (e.g., a company or an asset)
            List<Node> adjacent = new ArrayList<>(); // List of adjacent nodes representing relationships

            Node(String id) {
                this.id = id; // Constructor to initialize the node with an ID
            }

            void addNeighbor(Node node) {
                adjacent.add(node); // Method to add an adjacent node, forming a directed edge
            }

            void removeNeighbor(Node node){
                adjacent.remove(node);
            }

            List<Node> getNeighbors(){
                return new ArrayList<>(adjacent); // Returns a copy to prevent external modifications
            }
        }


        Map<String, Node> nodes = new HashMap<>(); // Maps node IDs to node objects for quick lookup

        void addRelationship(String id1, String id2) {
            nodes.putIfAbsent(id1, new Node(id1)); // Add the node if it doesn't exist
            nodes.putIfAbsent(id2, new Node(id2)); // Ensures both nodes exist in the graph before forming a relationship

            Node node1 = nodes.get(id1); // Retrieve the first node
            Node node2 = nodes.get(id2); // Retrieve the second node

            node1.addNeighbor(node2); // Create a directed edge from node1 to node2
        }

        void removeRelationship(String id1, String id2) {
            if (nodes.containsKey(id1) && nodes.containsKey(id2)) {
                Node node1 = nodes.get(id1);
                Node node2 = nodes.get(id2);
                node1.removeNeighbor(node2);
            }
        }

        // Depth-First Search to find a path between two nodes
        boolean findPathDFS(String startId, String endId) {
            return findPathDFS(nodes.get(startId), endId, new HashSet<>());
        }

        private boolean findPathDFS(Node start, String endId, Set<String> visited) {
            if (start.id.equals(endId)) {
                return true; // End node found
            }
            visited.add(start.id);
            for (Node neighbor : start.adjacent) {
                if (!visited.contains(neighbor.id) && findPathDFS(neighbor, endId, visited)) {
                    return true; // Path found
                }
            }
            return false; // No path found
        }

        List<String> findStocksByIndex(String indexId) {
            List<String> stocks = new ArrayList<>();
            Node indexNode = nodes.get(indexId);
            for (Node stock : indexNode.adjacent) {
                stocks.add(stock.id);
            }
            return stocks;
        }


        void displayGraph() {
            for (Node node : nodes.values()) { // Iterate through all nodes in the graph
                System.out.print(node.id + " -> "); // Print the current node's ID
                node.adjacent.forEach(adjacentNode -> System.out.print(adjacentNode.id + " ")); // Print all adjacent nodes' IDs
                System.out.println(); // New line for the next node
            }
        }

    }

    public static class MarketModeling {
        public static void main(String[] args) {
            FinancialGraph graph = new FinancialGraph();

            // Model some stocks and indices
            String index1 = "Index:NASDAQ";
            String stock1 = "Stock:AAPL";
            String stock2 = "Stock:MSFT";
            String stock3 = "Stock:GOOGL";

            // Add relationships to model that stocks are part of the NASDAQ index
            graph.addRelationship(index1, stock1);
            graph.addRelationship(index1, stock2);
            graph.addRelationship(index1, stock3);

            // Display the initial graph
            System.out.println("Initial Graph:");
            graph.displayGraph();

            // Find and display stocks associated with the NASDAQ index
            List<String> nasdaqStocks = graph.findStocksByIndex(index1);
            System.out.println("Stocks in " + index1 + ": " + nasdaqStocks);

            // Remove a stock from the index and show the updated graph
            graph.removeRelationship(index1, stock3); // Assuming GOOGL is no longer part of NASDAQ
            System.out.println("\nGraph after removing " + stock3 + " from " + index1 + ":");
            graph.displayGraph();

            // Check if a path exists between NASDAQ and AAPL (it should exist)
            boolean pathExists = graph.findPathDFS(index1, stock1);
            System.out.println("Path exists between " + index1 + " and " + stock1 + ": " + pathExists);

            // Check if a path exists between NASDAQ and GOOGL (it should not exist after removal)
            pathExists = graph.findPathDFS(index1, stock3);
            System.out.println("Path exists between " + index1 + " and " + stock3 + ": " + pathExists);
        }


    }

}

/**
 * Visualization
 * For visualization, consider using Java libraries like JGraphT combined with
 * JGraphX for graphical representation, or external tools like Graphviz.
 * Visualization involves converting the graph data structure into a visual
 * graph format, which can be challenging to do directly within a console application.
 * A simple approach is to generate a DOT file (Graphviz format) from your
 * graph structure, which can be visualized using Graphviz tools.
 *
 * Real-world Financial Modeling
 * To model real-world financial scenarios, consider extending the Node class to
 * include additional information relevant to financial entities, such as market
 * cap for stocks, or interest rates for currencies. Relationships could represent
 * ownership, investment, or other financial connections.
 */
