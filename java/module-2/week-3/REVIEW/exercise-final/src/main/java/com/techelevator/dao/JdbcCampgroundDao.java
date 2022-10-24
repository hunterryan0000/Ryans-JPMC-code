package com.techelevator.dao;

import com.techelevator.model.Campground;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JdbcCampgroundDao implements CampgroundDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcCampgroundDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Campground getCampground(int campgroundId) {

        Campground campground = null;
        String sql = "SELECT campground_id, park_id, name, open_from_mm, open_to_mm, daily_fee " +
                "FROM campground WHERE campground_id = ? ORDER BY name";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, campgroundId);
        while (results.next()) {
            campground = mapRowToCampground(results);
        }
        return campground;
    }

    @Override
    public List<Campground> getCampgroundsByParkId(int parkId) {

        List<Campground> campgrounds = new ArrayList<>();
        String sql = "SELECT campground_id, park_id, name, open_from_mm, open_to_mm, daily_fee " +
                "FROM campground WHERE park_id = ? ORDER BY name";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, parkId);
        while (results.next()) {
            Campground campground = mapRowToCampground(results);
            campgrounds.add(campground);
        }
        return campgrounds;
    }

    @Override
    public Campground createCampground(Campground campground) {

        String sql = "INSERT INTO campground (park_id, name, open_from_mm, open_to_mm, daily_fee) " +
                     "VALUES (?, ?, ?, ?, ?) " +
                     "RETURNING campground_id;";
        int campgroundId = jdbcTemplate.queryForObject(sql, int.class, campground.getParkId(), campground.getName(),
                    campground.getOpenFromMonth(), campground.getOpenToMonth(), campground.getDailyFee());
        campground.setCampgroundId(campgroundId);
        return campground;
    }

    @Override
    public boolean updateCampground(Campground campground) {

        String sql = "UPDATE campground SET park_id = ?, name = ?, open_from_mm = ?, open_to_mm = ?, daily_fee = ? " +
                "WHERE campground_id = ?;";
        int numberOfRows = jdbcTemplate.update(sql, campground.getParkId(), campground.getName(), campground.getOpenFromMonth(),
                campground.getOpenToMonth(), campground.getDailyFee(), campground.getCampgroundId());
        return numberOfRows == 1;
    }

    @Override
    public void deleteCampground(int campgroundId) {

        // No need to handle site or reservation cascades since the tables
        // do not exist in the light-weight version of the database.
        // Delete the campground
        String deleteCampgroundSql = "DELETE FROM campground WHERE campground_id = ?;";
        jdbcTemplate.update(deleteCampgroundSql, campgroundId);
    }

    private Campground mapRowToCampground(SqlRowSet results) {

        Campground camp = new Campground();
        camp.setCampgroundId(results.getInt("campground_id"));
        camp.setParkId(results.getInt("park_id"));
        camp.setName(results.getString("name"));
        camp.setOpenFromMonth(results.getInt("open_from_mm"));
        camp.setOpenToMonth(results.getInt("open_to_mm"));
        camp.setDailyFee(results.getBigDecimal("daily_fee"));
        return camp;
    }
}
