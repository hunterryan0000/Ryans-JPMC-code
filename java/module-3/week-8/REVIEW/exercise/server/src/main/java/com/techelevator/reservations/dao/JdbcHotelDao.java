package com.techelevator.reservations.dao;

import com.techelevator.reservations.model.Address;
import com.techelevator.reservations.model.Hotel;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcHotelDao implements HotelDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcHotelDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private Hotel mapRowToHotel(SqlRowSet rs) {
        Hotel item = new Hotel();
        item.setId(rs.getInt("hotel_id"));
        item.setName(rs.getString("name"));
        item.setStars(rs.getInt("stars"));
        item.setRoomsAvailable(rs.getInt("rooms_available"));
        item.setCostPerNight(rs.getDouble("cost_per_night"));
        item.setCoverImage(rs.getString("cover_image"));

        // Create and set the Address object for the Hotel
        Address addr = new Address();
        addr.setAddress(rs.getString("address"));
        addr.setAddress2(rs.getString("address2"));
        addr.setCity(rs.getString("city"));
        addr.setState(rs.getString("state"));
        addr.setZip(rs.getString("zip"));
        item.setAddress(addr);

        return item;
    }

    static private boolean emptyString(String s) {
        // Check if a string is null or empty
        return (s == null || s.trim().length() == 0);
    }

    @Override
    public List<Hotel> list(String state, String city) {
        List<Hotel> list = new ArrayList<>();
        SqlRowSet results;
        // Build the SQL based on optional parameters
        if (emptyString(state) && emptyString(city)) {
            String sql = "SELECT * FROM hotel ORDER BY name;";
            results = jdbcTemplate.queryForRowSet(sql);
        } else if (!emptyString(state) && !emptyString(city)) {
            String sql = "SELECT * FROM hotel WHERE state = ? AND city = ? ORDER BY name;";
            results = jdbcTemplate.queryForRowSet(sql, state, city);
        } else if (!emptyString(state)) {
            String sql = "SELECT * FROM hotel WHERE state = ? ORDER BY name;";
            results = jdbcTemplate.queryForRowSet(sql, state);
        } else {
            String sql = "SELECT * FROM hotel WHERE city = ? ORDER BY name;";
            results = jdbcTemplate.queryForRowSet(sql, city);
        }

        while (results.next()) {
            Hotel item = mapRowToHotel(results);
            list.add(item);
        }
        return list;
    }

    @Override
    public Hotel create(Hotel hotel) {
        Address address = hotel.getAddress();
        String sql = "INSERT INTO hotel (name, address, address2, city, state, zip, stars, rooms_available, cost_per_night, cover_image) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING hotel_id";

        int newId = jdbcTemplate.queryForObject(sql, int.class,
                hotel.getName(), address.getAddress(), address.getAddress2(),
                address.getCity(), address.getState(), address.getZip(),
                hotel.getStars(), hotel.getRoomsAvailable(), hotel.getCostPerNight(), hotel.getCoverImage());
        return get(newId);
    }

    @Override
    public Hotel get(int id) {
        String sql = "SELECT * FROM hotel WHERE hotel_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        if (results.next()) {
            Hotel item = mapRowToHotel(results);
            return item;
        }
        return null;
    }

    @Override
    public List<String> getStatesList() {
        List<String> list = new ArrayList<>();
        String sql = "SELECT DISTINCT state FROM hotel ORDER BY state";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            list.add(results.getString("state"));
        }
        return list;
    }
}
