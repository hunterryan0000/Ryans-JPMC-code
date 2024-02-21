package DBMS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseManagementANDSQL {

    public static class CustomerAccountManagement {
        public static void main(String[] args) {
            // Try-with-resources statement to automatically close the connection
            try (Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/bank", "postgres", "postgres1")) {
                // Connection string to connect to the PostgreSQL database named 'bank' running on localhost:5432
                // 'postgres' is the username, and 'postgres1' is the password

                // SQL query to select account_id and balance from the accounts table where customer_id matches a specific value
                String query = "SELECT account_id, balance FROM accounts WHERE customer_id = ?";
                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    // Prepared statements are used for executing SQL queries securely.
                    // Here, customer_id is set to 123
                    statement.setInt(1, 123);

                    // Execute the query and obtain the result set
                    ResultSet resultSet = statement.executeQuery();

                    // Iterate through the result set and print out account ID and balance for each row
                    while (resultSet.next()) {
                        System.out.println("Account ID: " + resultSet.getInt("account_id") + ", Balance: " + resultSet.getDouble("balance"));
                    }
                } // The PreparedStatement and ResultSet are automatically closed here due to try-with-resources

            } catch (SQLException e) {
                // Catch and print any SQL exceptions that occur during the connection or query execution
                e.printStackTrace();
            }
        }
    }

}
