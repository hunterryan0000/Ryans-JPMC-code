package com.techelevator.reservations.dao;

import com.techelevator.reservations.model.Reservation;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcReservationDao implements ReservationDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcReservationDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private Reservation mapRowToReservation(SqlRowSet rs) {
        Reservation item = new Reservation();
        item.setId(rs.getInt("reservation_id"));
        item.setHotelId(rs.getInt("hotel_id"));
        item.setFullName(rs.getString("full_name"));
        item.setCheckinDate(rs.getDate("checkin_date").toLocalDate());
        item.setCheckoutDate(rs.getDate("checkout_date").toLocalDate());
        item.setGuests(rs.getInt("guests"));
        return item;
    }

    @Override
    public List<Reservation> list() {
        List<Reservation> list = new ArrayList<>();
        String sql = "SELECT * FROM reservation ORDER BY hotel_id, checkin_date, checkout_date";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            Reservation item = mapRowToReservation(results);
            list.add(item);
        }
        return list;
    }

    @Override
    public List<Reservation> listByHotel(int hotelId) {
        List<Reservation> list = new ArrayList<>();
        String sql = "SELECT * FROM reservation WHERE hotel_id = ? ORDER BY checkin_date, checkout_date";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, hotelId);
        while (results.next()) {
            Reservation item = mapRowToReservation(results);
            list.add(item);
        }
        return list;
    }

    @Override
    public Reservation get(int reservationId) {
        String sql = "SELECT * FROM reservation WHERE reservation_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, reservationId);
        if (results.next()) {
            Reservation item = mapRowToReservation(results);
            return item;
        }
        return null;
    }

    @Override
    public Reservation create(Reservation reservation, int hotelId) {
        String sql = "INSERT INTO reservation (hotel_id, full_name, checkin_date, checkout_date, guests) " +
                "VALUES (?, ?, ?, ?, ?) RETURNING reservation_id";
        int newId = jdbcTemplate.queryForObject(sql, int.class,
                hotelId, reservation.getFullName(), reservation.getCheckinDate(), reservation.getCheckoutDate(), reservation.getGuests());
        return get(newId);
    }

    @Override
    public Reservation update(Reservation reservation, int id) {
        String sql = "UPDATE reservation SET full_name = ?, checkin_date = ?, checkout_date = ?, guests = ? " +
                "WHERE reservation_id = ?";
        jdbcTemplate.update(sql, reservation.getFullName(), reservation.getCheckinDate(), reservation.getCheckoutDate(), reservation.getGuests(), id);
        return get(id);
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM reservation WHERE reservation_id = ?";
        jdbcTemplate.update(sql, id);
    }
}
