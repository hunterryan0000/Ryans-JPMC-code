package SlidingWindow;

public class FindMaxSum {
    public static int findMaxSumSubarray(int[] nums, int k) {
        int windowSum = 0;
        int maxSum = 0;
        int windowStart = 0;

        for (int windowEnd = 0; windowEnd < nums.length; windowEnd++) {
            windowSum += nums[windowEnd]; // Add the next element to the window

            // Slide the window when we hit the size k
            if (windowEnd >= k - 1) {
                maxSum = Math.max(maxSum, windowSum);
                windowSum -= nums[windowStart]; // Subtract the element going out of the window
                windowStart++; // Slide the window ahead
            }
        }

        return maxSum;
    }


    public static void main(String[] args) {
        int[] stockPrices = {1, 3, 2, 6, -1, 4, 1, 8, 2};
        int k = 5;

        int maxSum = findMaxSumSubarray(stockPrices, k);
        System.out.println("Max Sum:");
        System.out.println(maxSum);
    }


}
