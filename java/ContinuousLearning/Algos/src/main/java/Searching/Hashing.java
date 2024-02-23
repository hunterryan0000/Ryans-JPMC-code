package Searching;

import java.util.HashMap;
import java.util.Map;

public class Hashing {

    public static class FastTransactionLookup {
        static class Order {
            String symbol;
            double price;
            int quantity;

            public Order(String symbol, double price, int quantity) {
                this.symbol = symbol;
                this.price = price;
                this.quantity = quantity;
            }

            // Getters and setters omitted for brevity
        }

        public static void main(String[] args) {
            // Using a HashMap to simulate fast lookup with hashing
            Map<String, Order> orderBook = new HashMap<>();

            // Simulate adding orders to the order book
            orderBook.put("AAPL:150", new Order("AAPL", 150, 100));
            orderBook.put("GOOGL:2000", new Order("GOOGL", 2000, 50));

            // Fast lookup
            String searchKey = "AAPL:150";
            if (orderBook.containsKey(searchKey)) {
                Order order = orderBook.get(searchKey);
                System.out.println("Order found: " + order.symbol + " at " + order.price + " for " + order.quantity + " shares");
            } else {
                System.out.println("Order not found.");
            }
        }
    }

}
