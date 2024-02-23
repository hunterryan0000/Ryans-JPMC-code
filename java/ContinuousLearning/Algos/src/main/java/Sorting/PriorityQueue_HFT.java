package Sorting;

import java.util.Comparator;
import java.util.PriorityQueue;

public class PriorityQueue_HFT {

    /**
     * In HFT, orders must be processed as quickly as possible.
     * An efficient way to manage orders is by using priority queues,
     * ensuring that the highest buy orders and the lowest sell orders are
     * matched first, following the price-time priority rule.
     */

    // Define a static inner class named Order which implements the Comparable interface to allow sorting.
    static class Order implements Comparable<Order> {
        // Define an enumeration 'Type' with two possible values to distinguish between buy and sell orders.
        enum Type { BUY, SELL }

        // Declare variables for the order's ID, type, price, and timestamp.
        String id;
        Type type;
        double price;
        long timestamp;

        // Constructor for the Order class, initializing an order with an ID, type, price, and timestamp.
        public Order(String id, Type type, double price, long timestamp) {
            this.id = id;
            this.type = type;
            this.price = price;
            this.timestamp = timestamp;
        }

        // Override the compareTo method to define the natural order of Order objects.
        @Override
        public int compareTo(Order other) {
            // Compare prices, inverting the comparison for buy orders to prioritize higher prices.
            int priceComparison = Double.compare(this.price, other.price) * (type == Type.BUY ? -1 : 1);
            // If prices are equal, compare timestamps to prioritize earlier orders.
            if (priceComparison == 0) {
                return Long.compare(this.timestamp, other.timestamp);
            }
            return priceComparison;
        }

        // Override the toString method to provide a string representation of an order.
        @Override
        public String toString() {
            return "Order {" + "id='" + id + '\'' + ", type=" + type + ", price=" + price + ", timestamp=" + timestamp + '}';
        }
    }

    // Define a static inner class named OrderBook to manage buy and sell orders.
    static class OrderBook {
        // Use PriorityQueues to automatically sort orders. Buy orders use a max heap, sell orders use a min heap.
        private PriorityQueue<Order> buyOrders;
        private PriorityQueue<Order> sellOrders;

        // Constructor for the OrderBook class, initializing the priority queues with appropriate comparators.
        public OrderBook() {
            buyOrders = new PriorityQueue<>(Comparator.reverseOrder()); // Max heap for buy orders
            sellOrders = new PriorityQueue<>(); // Min heap for sell orders
        }

        // Method to add an order to the appropriate priority queue based on its type.
        public void addOrder(Order order) {
            if (order.type == Order.Type.BUY) {
                buyOrders.add(order);
            } else {
                sellOrders.add(order);
            }
        }

        // Method to simulate order matching and removal. Matches are made when a buy price is equal to or higher than a sell price.
        public void matchOrders() {
            while (!buyOrders.isEmpty() && !sellOrders.isEmpty() &&
                    buyOrders.peek().price >= sellOrders.peek().price) {
                Order buyOrder = buyOrders.poll();
                Order sellOrder = sellOrders.poll();
                System.out.println("Matched Buy: " + buyOrder + " with Sell: " + sellOrder);
            }
        }

        // Method to print the current state of buy and sell orders.
        public void displayOrders() {
            System.out.println("Buy Orders: " + buyOrders);
            System.out.println("Sell Orders: " + sellOrders);
        }
    }

    // Main class to demonstrate the usage of the OrderBook system.
    public static class HFTOrderBookExample {
        public static void main(String[] args) {
            // Create a new instance of OrderBook.
            OrderBook orderBook = new OrderBook();
            // Add sample orders to the order book with current timestamps.
            orderBook.addOrder(new Order("1", Order.Type.BUY, 100.5, System.currentTimeMillis()));
            orderBook.addOrder(new Order("2", Order.Type.SELL, 101.4, System.currentTimeMillis() + 1));
            orderBook.addOrder(new Order("3", Order.Type.BUY, 102.6, System.currentTimeMillis() + 2));
            orderBook.addOrder(new Order("4", Order.Type.SELL, 100.3, System.currentTimeMillis() + 3));
            orderBook.addOrder(new Order("5", Order.Type.SELL, 100, System.currentTimeMillis() + 4));

            // Display orders before matching to show initial state.
            System.out.println("Before matching:");
            orderBook.displayOrders();

            // Perform matching of buy and sell orders.
            orderBook.matchOrders();

            // Display orders after matching to show the final state.
            System.out.println("After matching:");
            orderBook.displayOrders();
        }
    }


    /**
     * Priority Queues for Buy and Sell Orders: Orders are sorted automatically
     * by price and time, ensuring that the most competitive orders are
     * matched first.
     *
     * Order Matching: A simple matching mechanism is simulated, where buy
     * and sell orders are matched if the buy order price is equal to or
     * higher than the sell order price.
     *
     * Efficient Order Processing: The priority queue data structure enables
     * efficient insertion and retrieval operations, which are crucial for
     * the fast-paced nature of HFT.
     */

}
