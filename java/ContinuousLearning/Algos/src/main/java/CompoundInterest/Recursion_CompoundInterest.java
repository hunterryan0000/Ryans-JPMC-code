package CompoundInterest;

/**
 * Recursion is a powerful concept where a function calls itself to solve
 * smaller instances of the same problem. It's particularly useful in
 * financial applications for tasks like compound interest calculations or
 * forecasting financial growth over time.
 */

public class Recursion_CompoundInterest {
    // Recursive method to calculate compound interest
    public static double calculateCompoundInterest(double principal, double rate, int years) {
        if (years == 0) {
            return principal; // Base case: no more years to compound
        }
        double newPrincipal = principal * (1 + rate);
        return calculateCompoundInterest(newPrincipal, rate, years - 1); // Recursive call
    }

    public static void main(String[] args) {
        double principal = 1000; // Initial investment
        double rate = 0.05; // Annual interest rate (5%)
        int years = 5; // Period in years
        double futureValue = calculateCompoundInterest(principal, rate, years);
        System.out.printf("Future value: $%.2f\n", futureValue);
    }
}

