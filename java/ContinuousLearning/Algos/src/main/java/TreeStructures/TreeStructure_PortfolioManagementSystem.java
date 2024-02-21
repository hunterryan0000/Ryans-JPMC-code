package TreeStructures;

import java.util.ArrayList;
import java.util.List;

public class TreeStructure_PortfolioManagementSystem {

    /**
     * let's consider a portfolio management system where each portfolio can contain
     * multiple assets (like stocks, bonds) and sub-portfolios.
     * This hierarchical structure allows for a comprehensive view of investments
     * and their performance. We'll implement a composite pattern in Java,
     * which is a structural design pattern that lets clients treat individual
     * objects and compositions of objects uniformly.
     */

    // Component
    interface Investment {
        // Method to get the value of an investment
        double getValue();
        // Method to display the investment information, with indentation based on
        // the hierarchy level (depth)
        void display(int depth);
    }

    /**
     * The Asset class represents a leaf in the composite structure;
     * it's an end object that doesn't contain other Investment objects.
     * Assets are the basic building blocks of the portfolio, like individual
     * stocks or bonds, or files in a system. ***
     */
    //Leaf
    static class Asset implements Investment {
        String name; // Name of the asset, e.g., "Google Stock"
        double value; // Monetary value of the asset

        public Asset(String name, double value) {
            this.name = name;
            this.value = value;
        }

        @Override
        public double getValue() {
            return this.value; // Returns the asset's value
        }

        @Override
        public void display(int depth) {
            // Prints the asset's name and value, indented by 'depth' levels
            System.out.println("-".repeat(depth) + name + ": $" + value);
        }
    }

    /**
     * The Portfolio class represents a composite object that can contain other
     * Investment objects, including Asset instances or even other Portfolio instances,
     * forming a tree structure. It implements the same interface but provides
     * additional methods like addInvestment to manage child investments.
     * Like Folders in a file system containing other folders and files. ***
     */
    // Composite
    static class Portfolio implements Investment {
        String name; // Name of the portfolio, e.g., "Tech Stocks"
        List<Investment> investments = new ArrayList<>(); // List to hold child investments

        public Portfolio(String name) {
            this.name = name;
        }

        public void addInvestment(Investment investment) {
            investments.add(investment); // Adds a new investment to the portfolio
        }

        @Override
        public double getValue() {
            // Calculates total value by summing up the values of all contained investments
            return investments.stream().mapToDouble(Investment::getValue).sum();
        }

        @Override
        public void display(int depth) {
            // Displays the portfolio name, then recursively displays contained investments, increasing the depth
            System.out.println("-".repeat(depth) + name + " Portfolio");
            for (Investment investment : investments) {
                investment.display(depth + 2); // Increase depth for child elements
            }
        }
    }


    public static class PortfolioManagement {
        public static void main(String[] args) {
            // Creating the root portfolio which contains all other portfolios and assets
            Portfolio rootPortfolio = new Portfolio("Total Investments");

            // Creating individual assets
            Asset googleStock = new Asset("Google Stock", 2000.00);
            Asset appleStock = new Asset("Apple Stock", 1500.00);

            // Creating a sub-portfolio for tech stocks and adding assets to it
            Portfolio techPortfolio = new Portfolio("Tech Stocks");
            techPortfolio.addInvestment(googleStock);
            techPortfolio.addInvestment(appleStock);

            // Creating another portfolio for bonds and adding an asset to it
            Portfolio bondPortfolio = new Portfolio("Bonds");
            bondPortfolio.addInvestment(new Asset("Government Bond", 1000.00));

            // Adding sub-portfolios to the root portfolio
            rootPortfolio.addInvestment(techPortfolio);
            rootPortfolio.addInvestment(bondPortfolio);

            // Displaying the entire investment hierarchy and the total portfolio value
            rootPortfolio.display(1); // Start with an indentation depth of 1
            System.out.println("Total Portfolio Value: $" + rootPortfolio.getValue());
        }
    }


    /**
     * In this example, both Asset and Portfolio implement the Investment interface.
     * An Asset is a leaf node that cannot have any children, while a
     * Portfolio is a composite node that can contain other Assets or Portfolios.
     * This setup allows for building a complex hierarchy of investments,
     * where the total value of a portfolio is the sum of the values of
     * its constituents, and everything can be treated uniformly through the
     * Investment interface.
     */

}
