package TreeStructures;

import java.util.ArrayList;
import java.util.List;

public class TreeStructures_PortfolioHierarchies {

    /**
     * Managing portfolio hierarchies with tree structures allows for
     * efficient operations like aggregation and querying.
     * In Java, a simple binary tree could represent a hierarchical portfolio
     */

    static class PortfolioNode {
        String id; // Unique identifier for the portfolio node
        double value; // Monetary value associated with this node
        List<PortfolioNode> children; // List of child portfolio nodes

        // Constructor initializes the node with an ID, value, and an empty list of children
        public PortfolioNode(String id, double value) {
            this.id = id;
            this.value = value;
            this.children = new ArrayList<>();
        }

        // Adds a child node to this node's list of children
        public void addChild(PortfolioNode child) {
            this.children.add(child);
        }

        // Recursively calculates the total value of this node and all its descendants
        public double calculateTotalValue() {
            double total = this.value; // Start with this node's value
            for (PortfolioNode child : children) {
                total += child.calculateTotalValue(); // Add the total value of each child
            }
            return total; // Return the cumulative total
        }
    }


    public static class PortfolioHierarchy {
        public static void main(String[] args) {
            // Create the root portfolio node with an initial value
            PortfolioNode root = new PortfolioNode("Root", 1000.00);

            // Create child portfolio nodes, each with its own value
            PortfolioNode child1 = new PortfolioNode("Child1", 500.00);
            PortfolioNode child2 = new PortfolioNode("Child2", 700.00);

            // Add the child nodes to the root node, forming a tree structure
            root.addChild(child1);
            root.addChild(child2);

            // Calculate and display the total value of the entire portfolio tree
            // starting from the root. This includes the root's value plus the total values
            // of all children and their descendants (if any).
            System.out.println("Total Portfolio Value: " + root.calculateTotalValue());
        }
    }


}
