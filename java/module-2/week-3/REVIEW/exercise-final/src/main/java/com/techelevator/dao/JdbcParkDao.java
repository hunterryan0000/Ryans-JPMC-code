package com.techelevator.dao;

import com.techelevator.model.Campground;
import com.techelevator.model.Park;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JdbcParkDao implements ParkDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcParkDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Park getPark(int parkId) {

        Park park = null;
        String sql = "SELECT park_id, name, location, establish_date, area, visitors, description FROM park WHERE park_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, parkId);
        while (results.next()) {
            park = mapRowToPark(results);
        }
        return park;
    }

    @Override
    public List<Park> getAllParks() {

        List<Park> parks = new ArrayList<>();
        String sql = "SELECT park_id, name, location, establish_date, area, visitors, description FROM park";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            Park park = mapRowToPark(results);
            parks.add(park);
        }
        return parks;
    }

    @Override
    public Park createPark(Park park) {

        String sql = "INSERT INTO park (name, location, establish_date, area, visitors, description) " +
                "VALUES (?, ?, ?, ?, ?, ?) " +
                "RETURNING park_id;";
        int parkId = jdbcTemplate.queryForObject(sql, int.class, park.getName(), park.getLocation(), park.getEstablishDate(),
                park.getArea(), park.getVisitors(), park.getDescription());
        park.setParkId(parkId);
        return park;
    }

    @Override
    public boolean updatePark(Park park) {

        String sql = "UPDATE park SET name = ?, location = ?, establish_date = ?, area = ?, visitors = ?, description = ? " +
                "WHERE park_id = ?;";
        int numberOfRows = jdbcTemplate.update(sql, park.getName(), park.getLocation(), park.getEstablishDate(),
                park.getArea(), park.getVisitors(), park.getDescription(), park.getParkId());
        return numberOfRows == 1;
    }

    @Override
    public void deletePark(int parkId) {

        // Delete foreign key references to parkId
        String deleteCampgroundSql = "DELETE FROM campground WHERE park_id = ?;";
        jdbcTemplate.update(deleteCampgroundSql, parkId);
        // Now safe to delete the park
        String deleteParkSql = "DELETE FROM park WHERE park_id = ?;";
        jdbcTemplate.update(deleteParkSql, parkId);
    }

    private Park mapRowToPark(SqlRowSet results) {
        Park park = new Park();
        park.setParkId(results.getInt("park_id"));
        park.setName(results.getString("name"));
        park.setLocation(results.getString("location"));
        park.setEstablishDate(results.getDate("establish_date").toLocalDate());
        park.setArea(results.getInt("area"));
        park.setVisitors(results.getInt("visitors"));
        park.setDescription(results.getString("description"));
        return park;
    }
}
