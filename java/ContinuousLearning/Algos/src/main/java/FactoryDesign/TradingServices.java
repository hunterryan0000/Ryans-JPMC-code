package FactoryDesign;

// Import necessary classes
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class TradingServices {

    // Common trading strategy interface
    interface TradingStrategy {
        void applyStrategy();
    }

    // Concrete trading strategies
    static class AggressiveStrategy implements TradingStrategy {
        @Override
        public void applyStrategy() {
            System.out.println("Applying aggressive trading strategy");
        }
    }

    static class ConservativeStrategy implements TradingStrategy {
        @Override
        public void applyStrategy() {
            System.out.println("Applying conservative trading strategy");
        }
    }

    // Enhanced TradingService interface to include strategy setting
    interface TradingService {
        void executeTrade();
        void setTradingStrategy(TradingStrategy strategy);
    }

    // Abstract TradingService implementation to handle strategy assignment
    abstract static class AbstractTradingService implements TradingService {
        protected TradingStrategy strategy;

        @Override
        public void setTradingStrategy(TradingStrategy strategy) {
            this.strategy = strategy;
        }
    }

    // Concrete TradingService implementations
    static class EquityTradingService extends AbstractTradingService {
        @Override
        public void executeTrade() {
            System.out.println("Executing equity trade with strategy:");
            strategy.applyStrategy();
        }
    }

    static class ForexTradingService extends AbstractTradingService {
        @Override
        public void executeTrade() {
            System.out.println("Executing forex trade with strategy:");
            strategy.applyStrategy();
        }
    }

    // Factory class with dynamic registration and creation of trading services
    static class TradingServiceFactory {
        private static final Map<String, Supplier<TradingService>> registry = new HashMap<>();

        // Method to register trading services
        public static void registerService(String key, Supplier<TradingService> supplier) {
            registry.put(key, supplier);
        }

        // Static block for initial service registration
        static {
            registerService("equity", EquityTradingService::new);
            registerService("forex", ForexTradingService::new);
        }

        // Method to get trading services
        public static TradingService getTradingService(String type) {
            Supplier<TradingService> supplier = registry.get(type);
            if (supplier != null) {
                return supplier.get();
            }
            throw new IllegalArgumentException("Unknown service type: " + type);
        }
    }

    // Main class to demonstrate complex usage of Factory and Strategy Patterns
    public static class TradingPlatform {
        public static void main(String[] args) {
            TradingService equityService = TradingServiceFactory.getTradingService("equity");
            equityService.setTradingStrategy(new AggressiveStrategy());
            equityService.executeTrade();

            TradingService forexService = TradingServiceFactory.getTradingService("forex");
            forexService.setTradingStrategy(new ConservativeStrategy());
            forexService.executeTrade();
        }
    }


}
