package SlidingWindow;

public class MovingAverage {


    public static double[] movingAverage(int[] prices, int k) {
        double[] result = new double[prices.length - k + 1]; // how many windows one can slide through
        double windowSum = 0;
        int windowStart = 0;
        for (int windowEnd = 0; windowEnd < prices.length; windowEnd++) {
            windowSum += prices[windowEnd]; // Add the next element to the window
            // Check if we've hit the size of 'k'
            if (windowEnd >= k - 1) {
                result[windowStart] = windowSum / k; // Calculate the average
                windowSum -= prices[windowStart]; // Remove the element going out of the window
                windowStart++; // Slide the window ahead
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] stockPrices = {1, 3, 2, 6, -1, 4, 1, 8, 2};
        int k = 5;
        double[] averages = movingAverage(stockPrices, k);
        System.out.println("Moving average:");
        for (double avg : averages) {
            System.out.println(avg);
        }
    }

}
