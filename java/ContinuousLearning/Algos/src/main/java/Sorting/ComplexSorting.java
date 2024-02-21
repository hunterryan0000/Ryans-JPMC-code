package Sorting;

import java.util.*;

public class ComplexSorting {
    /**
     * let's consider an order book where orders are sorted not just by price
     * but also by time, simulating a real-world financial trading platform scenario.
     * In financial markets, orders are typically prioritized by price
     * (higher bids or lower asks first) and then by the time the order was placed
     * (earlier orders first). This is known as price-time priority.
     */

    enum OrderType {
        BID, // Represents a buy order
        ASK  // Represents a sell order
    }


    static class Order {
        String id; // Unique identifier for the order
        OrderType type; // Added to distinguish between BID and ASK
        double price; // Price of the order
        long timestamp; // Timestamp when the order was placed, for sorting orders placed at the same price

        public Order(String id, OrderType type, Double price, long timestamp) {
            this.id = id;
            this.type = type;
            this.price = price;
            this.timestamp = timestamp;
        }

        @Override
        public String toString() {
            // Provides a string representation of the order object
            return "Order {" + "id='" + id + '\'' + ", type=" + type + ", price=" + price + ", timestamp=" + timestamp + '}';        }

        // Getters omitted for brevity
    }


    static class PriceTimeComparator implements Comparator<Order> {
        @Override
        public int compare(Order o1, Order o2) {
            // Check if both orders are of the same type (either both BID or both ASK)
            if (o1.type == o2.type) {
                // For BID orders, higher prices come first. For ASK orders, lower prices come first.
                int priceCompare = (o1.type == OrderType.BID) ? Double.compare(o2.price, o1.price) : Double.compare(o1.price, o2.price);
                if (priceCompare == 0) {
                    // If prices are equal, earlier orders come first
                    return Long.compare(o1.timestamp, o2.timestamp);
                }
                return priceCompare;
            } else {
                // Arbitrarily prioritize BID over ASK if comparing different types
                // This part can be adjusted based on specific requirements
                return o1.type == OrderType.BID ? -1 : 1;
            }
        }
    }


    public static class ComplexOrderSorting {
        public static void main(String[] args) {
            List<Order> orders = new ArrayList<>();
            orders.add(new Order("Order1", OrderType.BID, 100.50, System.currentTimeMillis()));

            orders.add(new Order("Order2", OrderType.ASK, 100.50, System.currentTimeMillis()));

            try { Thread.sleep(10); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

            orders.add(new Order("Order3", OrderType.BID, 99.75, System.currentTimeMillis()));

            orders.add(new Order("Order4", OrderType.ASK, 105.00, System.currentTimeMillis()));

            try { Thread.sleep(10); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

            orders.add(new Order("Order5", OrderType.ASK, 105.00, System.currentTimeMillis()));

            // Sort the orders
            orders.sort(new PriceTimeComparator());

            System.out.println("Sorted Orders: ");
            orders.forEach(System.out::println);
        }

    }

}
