package Sorting;

import java.util.*;

public class Sorting {
    /**
     * Implementing the Comparable interface in the Order class allows objects of
     * this class to be compared to each other based on a specified property,
     * in this case, the price.
     *
     * Without the Comparable interface and compareTo method a compiler error is thrown
     */

    static class Order implements Comparable<Order> {
        String symbol;
        double price;

        public Order(String symbol, double price) {
            this.symbol = symbol;
            this.price = price;
        }

        @Override
        public int compareTo(Order other) {
            int priceComparison = Double.compare(this.price, other.price);
            if (priceComparison == 0) {
                // Compare by symbol if prices are the same
                return this.symbol.compareTo(other.symbol);
            }
            return priceComparison;
        }


        @Override
        public String toString() {
            return " Order {" +
                    "symbol = '" + symbol + '\'' +
                    ", price = " + price +
                    "}";
        }
    }

    public static class OrderBook {
        public static void main(String[] args) {
            List<Order> orders = new ArrayList<>();
            orders.add(new Order("AAPL", 150.00));
            orders.add(new Order("GOOGL", 2000.50));
            orders.add(new Order("MSFT", 300.25));
            orders.add(new Order("Tesla", 2000.50));

            Collections.sort(orders); // Sorts based on price
            System.out.println("Sorted Orders: ");

            for (Order order: orders){
                System.out.println(order);
            }

        }
    }

}
