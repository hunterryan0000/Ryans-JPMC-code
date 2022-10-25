package com.techelevator.ssgeek.dao;

import com.techelevator.ssgeek.model.Sale;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class JdbcSaleDao implements SaleDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcSaleDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Sale getSale(int saleId) {
        Sale sale = null;
        String sql = "SELECT sale_id, c.customer_id, sale_date, ship_date, c.name AS customer_name " +
                "FROM sale s " +
                "JOIN customer c on s.customer_id = c.customer_id " +
                "WHERE sale_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, saleId);
        if (results.next()) {
            sale = mapRowToSale(results);
        }
        return sale;
    }

    @Override
    public List<Sale> getSalesUnshipped() {
        List<Sale> sales = new ArrayList<>();
        String sql = "SELECT sale_id, c.customer_id, sale_date, ship_date, c.name AS customer_name " +
                "FROM sale s " +
                "JOIN customer c on s.customer_id = c.customer_id " +
                "WHERE ship_date is null " +
                "ORDER BY sale_id;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            Sale sale = mapRowToSale(results);
            sales.add(sale);
        }
        return sales;
    }

    @Override
    public List<Sale> getSalesByCustomerId(int customerId) {
        List<Sale> sales = new ArrayList<>();
        String sql = "SELECT sale_id, c.customer_id, sale_date, ship_date, c.name AS customer_name " +
                "FROM sale s " +
                "JOIN customer c on s.customer_id = c.customer_id " +
                "WHERE s.customer_id = ? " +
                "ORDER BY sale_id;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, customerId);
        while (results.next()) {
            Sale sale = mapRowToSale(results);
            sales.add(sale);
        }
        return sales;
    }

    @Override
    public List<Sale> getSalesByProductId(int productId) {
        List<Sale> sales = new ArrayList<>();
        String sql = "SELECT sale_id, c.customer_id, sale_date, ship_date, c.name AS customer_name " +
                "FROM sale s " +
                "JOIN customer c on s.customer_id = c.customer_id " +
                "WHERE sale_id in " +
                "(SELECT DISTINCT sale_id FROM line_item WHERE product_id = ?) " +
                "ORDER BY sale_id;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, productId);
        while (results.next()) {
            Sale sale = mapRowToSale(results);
            sales.add(sale);
        }
        return sales;
    }

    @Override
    public Sale createSale(Sale newSale) {
        String sql = "INSERT INTO sale (customer_id, sale_date, ship_date) " +
                "VALUES (?, ?, ?) RETURNING sale_id;";
        int newId = jdbcTemplate.queryForObject(sql, int.class, newSale.getCustomerId(), newSale.getSaleDate(),
                newSale.getShipDate());
        return getSale(newId);
    }

    @Override
    public void updateSale(Sale updatedSale) {
        String sql = "UPDATE sale " +
                "SET customer_id = ?, sale_date = ?, ship_date = ? " +
                "WHERE sale_id = ?";
        jdbcTemplate.update(sql, updatedSale.getCustomerId(), updatedSale.getSaleDate(),
                updatedSale.getShipDate(), updatedSale.getSaleId());
    }

    @Override
    public void deleteSale(int saleId) {
        // Multiple statements in a single batch are automatically wrapped in a transaction -
        // these two statements will either succeed or fail as a unit.
        // This guarantees we won't be left with an order with no line items because only the first succeeded.
        String sql =
                "DELETE FROM line_item WHERE sale_id = ?;" +
                "DELETE FROM sale WHERE sale_id = ?;";
        jdbcTemplate.update(sql, saleId, saleId);
    }

    private Sale mapRowToSale(SqlRowSet results) {
        // Create a model object and fill in its properties with values from the result set.
        Sale sale = new Sale();
        sale.setSaleId(results.getInt("sale_id"));
        sale.setCustomerId(results.getInt("customer_id"));
        sale.setCustomerName(results.getString("customer_name"));
        sale.setSaleDate(results.getDate("sale_date").toLocalDate());

        // NULLable column ship_date
        Date date = results.getDate("ship_date");
        if (date == null) {
            sale.setShipDate(null);
        } else {
            sale.setShipDate(date.toLocalDate());
        }

        return sale;
    }
}
