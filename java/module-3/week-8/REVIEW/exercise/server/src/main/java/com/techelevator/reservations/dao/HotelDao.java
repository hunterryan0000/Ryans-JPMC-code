package com.techelevator.reservations.dao;

import com.techelevator.reservations.model.Hotel;

import java.util.List;

public interface HotelDao {

    List<Hotel> list(String state, String city);

    Hotel create(Hotel hotel);

    Hotel get(int id);

    List<String> getStatesList();

}
