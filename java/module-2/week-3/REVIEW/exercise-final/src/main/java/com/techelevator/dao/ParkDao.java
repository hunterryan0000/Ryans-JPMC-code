package com.techelevator.dao;

import com.techelevator.model.Park;

import java.util.List;

public interface ParkDao {

    // Basic CRUD

    Park getPark(int parkId);
    List<Park> getAllParks();

    Park createPark(Park park);

    boolean updatePark(Park park);

    void deletePark(int parkId);
}
