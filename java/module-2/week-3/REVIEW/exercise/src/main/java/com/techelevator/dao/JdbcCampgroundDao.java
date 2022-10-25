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

        return null;
    }

    @Override
    public List<Campground> getCampgroundsByParkId(int parkId) {

        return new ArrayList<>();
    }

    @Override
    public Campground createCampground(Campground campground) {

        return null;
    }

    @Override
    public boolean updateCampground(Campground campground) {

        return false;
    }

    @Override
    public void deleteCampground(int campgroundId) {

    }
}
