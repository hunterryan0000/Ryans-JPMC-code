package com.techelevator.reservations.controllers;

import com.techelevator.reservations.dao.HotelDao;
import com.techelevator.reservations.dao.ReservationDao;
import com.techelevator.reservations.model.Hotel;
import com.techelevator.reservations.model.Reservation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin
@RestController
public class HotelController {

    private HotelDao hotelDao;
    private ReservationDao reservationDao;

    public HotelController(HotelDao hotelDao, ReservationDao reservationDao) {
        this.hotelDao = hotelDao;
        this.reservationDao = reservationDao;
    }

    /**
     * /hotels
     * /hotels?state=oh
     * /hotels?state=oh&city=cleveland
     *
     * @param state the state to filter by
     * @param city  the city to filter by
     * @return a list of hotels. If there are no filter parameters, all hotels in the system
     * are returned. If there are filter parameters, return the hotels that match the city & state
     */
    @RequestMapping(path = "/hotels", method = RequestMethod.GET)
    public List<Hotel> listHotels(@RequestParam(required = false) String state, @RequestParam(required = false) String city) {
        return hotelDao.list(state, city);
    }

    /**
     * Return a list of states in which there are Hotels
     *
     * @return a list of states as strings
     */
    @RequestMapping(path = "/hotels/states", method = RequestMethod.GET)
    public List<String> getStates() {
        return hotelDao.getStatesList();
    }

    /**
     * Return hotel information
     *
     * @param id the id of the hotel
     * @return all info for a given hotel
     */
    @RequestMapping(path = "/hotels/{id}", method = RequestMethod.GET)
    public Hotel getHotel(@PathVariable int id) {
        Hotel hotel = hotelDao.get(id);
        if (hotel == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Hotel not found.");
        } else {
            return hotel;
        }
    }

    /**
     * List of reservations by hotel
     *
     * @param hotelId
     * @return all reservations for a given hotel
     */
    @RequestMapping(path = "/hotels/{id}/reservations", method = RequestMethod.GET)
    public List<Reservation> listReservationsByHotel(@PathVariable("id") int hotelId) {
        List<Reservation> reservations = reservationDao.listByHotel(hotelId);
        if (reservations == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Hotel not found.");
        } else {
            return reservations;
        }
    }
}
