package DynamicProgramming;

/**
 * Option: A financial derivative that provides the buyer the right, but not the obligation, to buy (in the case of a call option) or sell (in the case of a put option) an asset at a specified price (strike price) before or at a certain date (expiration date).
 * Initial Price: The current price of the underlying asset when the option is being priced.
 * Strike Price: The price at which the option holder can buy or sell the underlying asset.
 * Periods: The number of time steps until the option's expiration. This model discretizes time into steps or periods.
 * Up Factor: The factor by which the price of the underlying asset increases in each period.
 * Down Factor: The factor by which the price of the underlying asset decreases in each period.
 * Risk-Free Rate: The interest rate at which money can be borrowed or lent without risk over a given period.
 */

public class OptionPricing {

    // Example of a simplified DP approach to options pricing (not actual financial code)
    public static class OptionPricingDP {
        // Method to calculate the option price using a simplified DP approach
        public static double calculateOptionPrice(double initialPrice, double strikePrice, int periods, double upFactor, double downFactor, double riskFreeRate) {
            // Calculate risk-neutral probability
            double riskNeutralProb = (riskFreeRate - downFactor) / (upFactor - downFactor);

            // Initialize the option values at maturity
            double[] optionValuesAtMaturity = new double[periods + 1];
            //Initialize the option values at maturity (the final period) for all possible states of the underlying asset's price.
            for (int i = 0; i <= periods; i++) { // Iterates through each possible state at maturity.
                //Calculates the stock price at maturity for each state. The price depends on how many times the stock went up (upFactor)
                // and down (downFactor) during the option's life. Math.pow(upFactor, i) represents the stock price going up i times,
                // and Math.pow(downFactor, periods - i) represents it going down (periods - i) times.
                double stockPriceAtMaturity = initialPrice * Math.pow(upFactor, i) * Math.pow(downFactor, periods - i);
                //Stores the option value at maturity, which is the maximum of 0 and the intrinsic value (stockPriceAtMaturity - strikePrice).
                // This accounts for the fact that an option holder will not exercise the option if it results in a loss (hence, the value cannot be negative).
                optionValuesAtMaturity[i] = Math.max(0, stockPriceAtMaturity - strikePrice);
            }

            // Calculate option price at each node, moving backwards
            // considering the possibility of early exercise (for American options).
            for (int period = periods - 1; period >= 0; period--) { //Iterates backward through each period before maturity.
                for (int i = 0; i <= period; i++) { //For each period, iterates through each possible state.
                    //Calculates the "hold" value of the option, which is the expected value of the option if the holder decides not to
                    // exercise it early. It's based on the risk-neutral probability (riskNeutralProb) of the stock price moving up or down,
                    // discounted by the risk-free rate (riskFreeRate). This accounts for the time value of money.
                    double holdValue = (riskNeutralProb * optionValuesAtMaturity[i + 1] +
                            (1 - riskNeutralProb) * optionValuesAtMaturity[i]) /
                            (1 + riskFreeRate);
                    //Calculates the "exercise" value of the option at the current node, which is the value if the holder decides to exercise the option early.
                    // It's the intrinsic value of the option at the current node, ensuring it's not negative.
                    double exerciseValue = Math.max(0, (initialPrice * Math.pow(upFactor, i) *
                            Math.pow(downFactor, period - i)) - strikePrice);
                    //Compares the hold value and exercise value, assigning the higher of the two to the current node.
                    // This decision reflects the optimal strategy for an American option, where the holder must decide whether to exercise the option early or hold it for potential future gain.
                    optionValuesAtMaturity[i] = Math.max(holdValue, exerciseValue);
                }
            }


            return optionValuesAtMaturity[0];
        }

        public static void main(String[] args) {
            double initialPrice = 100; // Current price of the underlying asset
            double strikePrice = 95; // Strike price of the option
            int periods = 3; // Number of periods until option expiration
            double upFactor = 1.1; // Factor by which the price increases in each period
            double downFactor = 0.9; // Factor by which the price decreases in each period
            double riskFreeRate = 0.05; // Annual risk-free interest rate

            double optionPrice = calculateOptionPrice(initialPrice, strikePrice, periods, upFactor, downFactor, riskFreeRate);
            System.out.println("Option Price: " + optionPrice);
        }

    }

}

/**
 * Dynamic Programming in Options Pricing:
 * Dynamic programming is a method for solving complex problems by breaking them down into simpler subproblems, solving each subproblem just once, and storing their solutions. In the context of options pricing, DP can be used to calculate the price of an option by working backwards from the option's expiration to the present, considering all possible paths the underlying asset's price could take.
 *
 * Code Explanation:
 * The calculateOptionPrice method models an option's price using a binomial tree, which is a discrete-time model for stock price movements. This method approximates the price of an option by considering all possible future movements of the underlying asset's price and discounting them back to the present value using a risk-neutral probability measure.
 *
 * Risk-Neutral Probability Calculation:
 *
 * The risk-neutral probability (riskNeutralProb) is calculated based on the up and down factors and the risk-free rate. This probability reflects the expected growth of the stock price, adjusted for the risk-free rate, and is used to weigh the future values of the option.
 * Initialization of Option Values at Maturity:
 *
 * The array optionValuesAtMaturity is initialized to store the option values at the time of expiration (maturity) for all possible states. The option value at maturity depends on the difference between the stock price at maturity and the strike price, but it cannot be negative (since the option holder would not exercise the option in that case).
 * Backward Calculation:
 *
 * The algorithm then iteratively calculates the option value at each node of the binomial tree, moving backward from expiration to the present. At each step, it calculates the expected option value based on the risk-neutral probability and discounts it back to the present using the risk-free rate.
 * Result:
 *
 * The first element of optionValuesAtMaturity after completing the backward calculation represents the present value of the option, which is returned as the result.
 */
