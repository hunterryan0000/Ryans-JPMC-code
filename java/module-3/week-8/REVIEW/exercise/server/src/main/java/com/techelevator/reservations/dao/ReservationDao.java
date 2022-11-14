package com.techelevator.reservations.dao;

import java.util.List;

import com.techelevator.reservations.model.Reservation;

public interface ReservationDao {

    List<Reservation> list();

    List<Reservation> listByHotel(int hotelId);

    Reservation get(int reservationId);

    Reservation create(Reservation reservation, int hotelId);

    Reservation update(Reservation reservation, int id);

    void delete(int id);

}
