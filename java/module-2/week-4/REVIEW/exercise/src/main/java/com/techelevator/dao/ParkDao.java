package com.techelevator.dao;

import com.techelevator.model.Park;

import java.util.List;

public interface ParkDao {

    Park getPark(int parkId);

    List<Park> getAllParks();
}
