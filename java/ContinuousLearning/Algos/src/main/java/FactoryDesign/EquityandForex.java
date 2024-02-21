package FactoryDesign;

public class EquityandForex {
    // Define a common interface for all trading services. This ensures consistency in how trading services are used.
    interface TradingService {
        void executeTrade(); // Method signature for executing a trade
    }

    // Concrete implementation of TradingService for equity trading.
    static class EquityTradingService implements TradingService {
        @Override
        public void executeTrade() {
            // Print a message to indicate an equity trade execution.
            System.out.println("Executing equity trade");
        }
    }

    // Concrete implementation of TradingService for forex trading.
    static class ForexTradingService implements TradingService {
        @Override
        public void executeTrade() {
            // Print a message to indicate a forex trade execution.
            System.out.println("Executing forex trade");
        }
    }

    // Factory class to create instances of TradingService based on a specified type.
    static class TradingServiceFactory {
        // Static method to get a trading service based on the type argument.
        public static TradingService getTradingService(String type) {
            switch (type) {
                case "equity":
                    // Return an instance of EquityTradingService if "equity" is specified.
                    return new EquityTradingService();
                case "forex":
                    // Return an instance of ForexTradingService if "forex" is specified.
                    return new ForexTradingService();
                default:
                    // Throw an exception if an unknown service type is requested.
                    throw new IllegalArgumentException("Unknown service type");
            }
        }
    }

    // Main class to demonstrate the use of the TradingServiceFactory.
    public static class TradingPlatform {
        public static void main(String[] args) {
            // Request an equity trading service from the factory and execute a trade.
            TradingService equityService = TradingServiceFactory.getTradingService("equity");
            equityService.executeTrade();

            // Request a forex trading service from the factory and execute a trade.
            TradingService forexService = TradingServiceFactory.getTradingService("forex");
            forexService.executeTrade();
        }
    }


}

/**
 * The Factory Design Pattern simplifies object creation, especially when there are multiple types or
 * classes of objects to create based on certain parameters.
 * It promotes loose coupling by hiding the creation logic of instances from the client, making it easier
 * to extend or modify the types of objects created without impacting the client code.
 * In this example, adding a new type of trading service (like commodities trading) would
 * only require adding a new class that implements TradingService and updating the TradingServiceFactory
 * to handle the new type. The rest of the application remains unchanged.
 */
