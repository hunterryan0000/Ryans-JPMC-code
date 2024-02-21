import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Greedy algorithms make the most favorable choice at each step,
 * aiming for a local optimum with the hope of finding a global optimum.
 * They can be used in financial scenarios like asset liquidation,
 * where you want to maximize returns or minimize losses quickly.
 */

public class Greedy_AssetLiquidation {
    // Greedy algorithm to maximize returns by selling assets
    public static double maximizeReturns(List<Double> assetValues) {
        // Sort assets by value in descending order
        Collections.sort(assetValues, Collections.reverseOrder());
        double totalValue = 0;
        for (double value : assetValues) {
            System.out.println("Selling asset for: $" + value);
            totalValue += value; // Accumulate total value
        }
        return totalValue;
    }

    public static void main(String[] args) {
        List<Double> assetValues = Arrays.asList(500.0, 1500.0, 1200.0, 800.0);
        double totalReturns = maximizeReturns(assetValues);
        System.out.printf("Total returns from liquidation: $%.2f\n", totalReturns);
    }
}
