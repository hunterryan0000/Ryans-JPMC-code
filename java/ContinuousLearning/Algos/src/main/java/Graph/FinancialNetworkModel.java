package Graph;

import java.util.*;

public class FinancialNetworkModel {

    static class FinancialNetwork {
        static class Entity {
            String id;
            List<Entity> relationships = new ArrayList<>();

            Entity(String id) {
                this.id = id;
            }

            // Add a relationship from this entity to another
            void addRelationship(Entity entity) {
                relationships.add(entity);
            }
        }

        protected final Map<String, Entity> entities = new HashMap<>();

        // Add an entity to the network if it doesn't already exist
        private Entity addEntity(String id) {
            entities.putIfAbsent(id, new Entity(id));
            return entities.get(id);
        }

        // Establish a directed relationship between two entities
        void addRelationship(String fromId, String toId) {
            Entity fromEntity = addEntity(fromId);
            Entity toEntity = addEntity(toId);
            fromEntity.addRelationship(toEntity);
        }

        // Display the financial network
        void displayNetwork() {
            for (Entity entity : entities.values()) {
                System.out.print(entity.id + " -> ");
                for (Entity relatedEntity : entity.relationships) {
                    System.out.print(relatedEntity.id + " ");
                }
                System.out.println();
            }
        }

        // Find all entities directly related to the given entity
        List<String> findDirectRelationships(String entityId) {
            if (!entities.containsKey(entityId)) {
                return Collections.emptyList();
            }
            List<String> relatedEntities = new ArrayList<>();
            for (Entity entity : entities.get(entityId).relationships) {
                relatedEntities.add(entity.id);
            }
            return relatedEntities;
        }
    }

    public static class FinancialMarketGraphExample {
        public static void main(String[] args) {
            FinancialNetwork network = new FinancialNetwork();

            // Adding companies (C), assets (A), and currencies (Cur) to the network
            network.addRelationship("C:JPMC", "A:Stock1");
            network.addRelationship("C:JPMC", "A:Stock2");
            network.addRelationship("A:Stock1", "Cur:USD");
            network.addRelationship("A:Stock2", "Cur:EUR");
            network.addRelationship("C:JPMC", "Cur:USD"); // JPMC operates in USD

            // Display the network
            network.displayNetwork();

            // Find and display all assets owned by JPMC
            List<String> jpmcAssets = network.findDirectRelationships("C:JPMC");
            System.out.println("JPMC owns: " + jpmcAssets);

            // Find and display all currencies JPMC's assets can be traded in
            Set<String> tradableCurrencies = new HashSet<>();
            for (String asset : jpmcAssets) {
                tradableCurrencies.addAll(network.findDirectRelationships(asset));
            }
            System.out.println("JPMC's assets can be traded in: " + tradableCurrencies);
        }
    }
}
/**
 * In this example:
 *
 * Entity represents a node in our financial network graph,
 * which could be an asset, a company, or a currency. Each Entity has
 * a list of relationships to other entities, representing directed edges
 * in the graph.
 *
 * FinancialNetwork manages the entities and their relationships.
 * It provides methods to add entities, establish relationships between them,
 * display the network, and find direct relationships for a given entity.
 *
 * The main method sets up a simple financial network with companies (C),
 * assets (A), and currencies (Cur) as nodes. It establishes relationships
 * to model ownership of assets by a company and the currencies in which
 * those assets can be traded.
 *
 * The example demonstrates finding all assets owned by a company
 * (in this case, JPMC) and identifying all currencies those assets can be
 * traded in, showcasing how graph theory can model complex relationships
 * in financial markets.
 */
