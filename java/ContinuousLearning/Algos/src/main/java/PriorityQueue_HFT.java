import java.util.Comparator;
import java.util.PriorityQueue;

public class PriorityQueue_HFT {

    /**
     * In HFT, orders must be processed as quickly as possible.
     * An efficient way to manage orders is by using priority queues,
     * ensuring that the highest buy orders and the lowest sell orders are
     * matched first, following the price-time priority rule.
     */

    static class Order implements Comparable<Order> {
        enum Type { BUY, SELL }

        String id;
        Type type;
        double price;
        long timestamp;

        public Order(String id, Type type, double price, long timestamp) {
            this.id = id;
            this.type = type;
            this.price = price;
            this.timestamp = timestamp;
        }

        @Override
        public int compareTo(Order other) {
            // For buy orders: higher prices and earlier times have higher priority
            // For sell orders: lower prices and earlier times have higher priority
            int priceComparison = Double.compare(this.price, other.price) * (type == Type.BUY ? -1 : 1);
            if (priceComparison == 0) {
                return Long.compare(this.timestamp, other.timestamp);
            }
            return priceComparison;
        }

        @Override
        public String toString() {
            return "Order{" + "id='" + id + '\'' + ", type=" + type + ", price=" + price + ", timestamp=" + timestamp + '}';
        }
    }

    static class OrderBook {
        private PriorityQueue<Order> buyOrders;
        private PriorityQueue<Order> sellOrders;

        public OrderBook() {
            buyOrders = new PriorityQueue<>(Comparator.reverseOrder()); // Max heap for buy orders
            sellOrders = new PriorityQueue<>(); // Min heap for sell orders
        }

        public void addOrder(Order order) {
            if (order.type == Order.Type.BUY) {
                buyOrders.add(order);
            } else {
                sellOrders.add(order);
            }
        }

        // Simulate order matching and removal (simplified for demonstration)
        public void matchOrders() {
            while (!buyOrders.isEmpty() && !sellOrders.isEmpty() &&
                    buyOrders.peek().price >= sellOrders.peek().price) {
                Order buyOrder = buyOrders.poll();
                Order sellOrder = sellOrders.poll();
                System.out.println("Matched Buy: " + buyOrder + " with Sell: " + sellOrder);
            }
        }

        public void displayOrders() {
            System.out.println("Buy Orders: " + buyOrders);
            System.out.println("Sell Orders: " + sellOrders);
        }
    }

    public static class HFTOrderBookExample {
        public static void main(String[] args) {
            OrderBook orderBook = new OrderBook();
            orderBook.addOrder(new Order("1", Order.Type.BUY, 100.5, System.currentTimeMillis()));
            orderBook.addOrder(new Order("2", Order.Type.SELL, 100.4, System.currentTimeMillis() + 1));
            orderBook.addOrder(new Order("3", Order.Type.BUY, 100.6, System.currentTimeMillis() + 2));
            orderBook.addOrder(new Order("4", Order.Type.SELL, 100.3, System.currentTimeMillis() + 3));

            System.out.println("Before matching:");
            orderBook.displayOrders();

            orderBook.matchOrders();

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
