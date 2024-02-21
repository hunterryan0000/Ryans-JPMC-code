package Threading;

public class ConcurrencyandMultithreading {
    public static class MarketDataProcessor implements Runnable {
        private final String data;

        public MarketDataProcessor(String data) {
            this.data = data; // Simulated market data
        }

        @Override
        public void run() {
            System.out.println("Processing data: " + data + " on thread " + Thread.currentThread().getName());
            // Process the data
        }

        public static void main(String[] args) {
            Thread t1 = new Thread(new MarketDataProcessor("Data1"), "DataProcessorThread1");
            Thread t2 = new Thread(new MarketDataProcessor("Data2"), "DataProcessorThread2");

            t1.start(); // Start the first thread
            t2.start(); // Start the second thread
        }
    }

}
