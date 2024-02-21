package DynamicProgramming;

/**
 * incomplete
 */

public class OptimalDecision {
    public static class OptionPricingDP {
        static class OptionDecision {
            double value;
            String decision;
            int period;

            OptionDecision(double value, String decision, int period) {
                this.value = value;
                this.decision = decision;
                this.period = period;
            }
        }

        public static OptionDecision calculateOptionPrice(double initialPrice, double strikePrice, int periods, double upFactor, double downFactor, double riskFreeRate) {
            double riskNeutralProb = (riskFreeRate - downFactor) / (upFactor - downFactor);
            double[][] values = new double[periods + 1][periods + 1];

            // Calculate terminal values
            for (int i = 0; i <= periods; i++) {
                values[periods][i] = Math.max(0, initialPrice * Math.pow(upFactor, i) * Math.pow(downFactor, periods - i) - strikePrice);
            }

            // Backward induction to find optimal value
            for (int period = periods - 1; period >= 0; period--) {
                for (int i = 0; i <= period; i++) {
                    double expectedValue = (riskNeutralProb * values[period + 1][i + 1] + (1 - riskNeutralProb) * values[period + 1][i]) / (1 + riskFreeRate);
                    values[period][i] = Math.max(initialPrice * Math.pow(upFactor, i) * Math.pow(downFactor, period - i) - strikePrice, expectedValue);
                }
            }

            // Determine the optimal strategy based on the calculated values
            double optionValue = values[0][0];
            String decision = optionValue > 0 ? "hold" : "exercise";
            int optimalPeriod = -1;  // Indicates no early exercise is optimal unless a specific condition is met later

            // Correct logic to find if there's an actual beneficial period to exercise
            for (int period = 1; period <= periods; period++) {
                if (values[period][0] - strikePrice > optionValue) {
                    decision = "exercise";
                    optimalPeriod = period;
                    optionValue = values[period][0] - strikePrice;
                    break;
                }
            }

            return new OptionDecision(optionValue, decision, optimalPeriod);
        }

        public static void evaluateScenario(double initialPrice, double strikePrice, int periods, double upFactor, double downFactor, double riskFreeRate) {
            OptionDecision optionDecision = OptimalDecision.OptionPricingDP.calculateOptionPrice(initialPrice, strikePrice, periods, upFactor, downFactor, riskFreeRate);
            System.out.println("Scenario with Initial Price: " + initialPrice + ", Strike Price: " + strikePrice + ", Periods: " + periods +
                    ", Up Factor: " + upFactor + ", Down Factor: " + downFactor + ", Risk-Free Rate: " + riskFreeRate);
            System.out.println("Optimal Strategy: " + optionDecision.decision.toUpperCase() +
                    (optionDecision.decision.equals("exercise") ? " at Period: " + optionDecision.period : "") +
                    " with Option Price: " + optionDecision.value + "\n");
        }


        public static void main(String[] args) {
            // Scenario 1: Likely to hold due to high volatility and longer duration
            evaluateScenario(100, 100, 5, 1.2, 0.8, 0.05);

            // Scenario 2: Likely to exercise early, "in the money" with less time until expiration
            evaluateScenario(105, 100, 2, 1.1, 0.9, 0.05);

            // Scenario 3: Likely to hold, "at the money" with significant time and high volatility
            evaluateScenario(100, 100, 5, 1.3, 0.7, 0.03);

            // Scenario 4: Likely to exercise early due to being deeply "in the money" and lower volatility
            evaluateScenario(110, 100, 3, 1.05, 0.95, 0.06);

            // Scenario 5: Likely to hold due to being "out of the money" with a chance for high upside
            evaluateScenario(95, 100, 4, 1.25, 0.75, 0.04);

            /**
             * Explanation for Each Scenario:
             * Scenario 1: High volatility and a longer duration typically make holding more attractive due to the potential for substantial price increases over time.
             *
             * Scenario 2: Being "in the money" with a shorter time until expiration may make exercising early more attractive, especially if the up factor suggests limited upside potential.
             *
             * Scenario 3: "At the money" with significant volatility and time indicates a strong potential for price movement, favoring holding to capture time value.
             *
             * Scenario 4: Deeply "in the money" with lower volatility suggests that the option already captures significant intrinsic value, which might make early exercise appealing to lock in gains.
             *
             * Scenario 5: "Out of the money" but with a chance for a high upside due to a high up factor might encourage holding, betting on future price increases to bring the option "in the money."
             */

        }

    }
}


/**
 * Up Factor and Down Factor
 * The up and down factors in a binomial tree model represent the possible percentage changes in the price of the underlying asset over a short period. These factors are crucial for modeling the asset's price dynamics under uncertainty.
 *
 * Historical Volatility: One common approach to estimating up and down factors is to use the historical volatility of the underlying asset. Historical volatility is a measure of the variance in the asset's price over a specific period in the past. It can be calculated using standard deviation of the asset’s logarithmic returns.
 *
 * Implied Volatility: Alternatively, implied volatility derived from market prices of options on the asset can be used. Implied volatility represents the market's view on the future volatility of the asset's price. It is extracted from the prices of traded options using models like Black-Scholes or a numerical method.
 *
 * Once the volatility (σ) is determined, the up and down factors ( u and d) for a given period can be calculated using formulas derived from the geometric Brownian motion model of stock price movements.

 * Risk-Free Rate
 * The risk-free rate is theoretically the return of an investment with zero risk, meaning it is the return investors would expect from an absolutely safe investment over a specific period.
 *
 * Treasury Securities: In practice, the yield on government treasury securities (such as U.S. Treasury bills) is often used as a proxy for the risk-free rate. The maturity of the treasury security chosen should match the time horizon of the option being priced.
 *
 * Adjustments for Option Maturity: For options with maturities that do not match available treasury securities exactly, interpolation or extrapolation of yields on securities with the nearest maturities can be used to estimate the risk-free rate for the option's life.
 */