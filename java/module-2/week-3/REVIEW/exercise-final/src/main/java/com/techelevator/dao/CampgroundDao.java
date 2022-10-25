package com.techelevator.dao;

import com.techelevator.model.Campground;

import java.util.List;

public interface CampgroundDao {
    // Basic CRUD
    Campground getCampground(int campgroundId);

    List<Campground> getCampgroundsByParkId(int parkId);

    Campground createCampground(Campground campground);

    boolean updateCampground(Campground campground);

    void deleteCampground(int campgroundId);

//    List<Campground> getCampgroundsByParkId(int parkId);
}
