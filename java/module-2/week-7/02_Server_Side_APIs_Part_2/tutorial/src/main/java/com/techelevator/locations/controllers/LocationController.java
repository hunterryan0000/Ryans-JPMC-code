package com.techelevator.locations.controllers;

import com.techelevator.locations.dao.LocationDao;
import com.techelevator.locations.model.Location;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/locations")
public class LocationController {

    private LocationDao dao;

    //constructor instantiates a new DAO instance at runtime
    // by the Spring framework using Dependency Injection:
    public LocationController(LocationDao dao) {
        this.dao = dao;
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<Location> list() {
        return dao.list();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Location get(@PathVariable int id) {
        Location location = dao.get(id);
        if (location == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Location not found");
        } else {
            return location;
        }
    }

    @ResponseStatus(HttpStatus.CREATED) //gives 201 created response instead of 200
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Location add(@Valid /**Receives valid argument*/  @RequestBody Location location) {
        return dao.create(location);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public Location update(@Valid @RequestBody Location location, @PathVariable int id){
        Location updatedLocation = dao.update(location, id);
        if (updatedLocation == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Location not found");
        } return updatedLocation;
    }

}
