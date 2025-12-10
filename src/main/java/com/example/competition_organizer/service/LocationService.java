<<<<<<<< HEAD:src/main/java/com/example/competition_organizer/service/LocationService.java
package com.example.competition_organizer.service;

import com.example.competition_organizer.model.Location;
import com.example.competition_organizer.repozitory.LocationRepository;
========
package competitionOrganizer.service;

import competitionOrganizer.model.Location;
import competitionOrganizer.repozitory.LocationRepository;
>>>>>>>> upstream/main:src/main/java/competitionOrganizer/service/LocationService.java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService {

    private final LocationRepository locationRepository;

    @Autowired
    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public Location createLocation(Location location) {
        return locationRepository.save(location);
    }

    public List<Location> getAll() {
        return locationRepository.findAll();
    }

    public Optional<Location> getLocationById(Long id) {
        return locationRepository.findById(id);
    }

    public void deleteLocationById(Long id) {
        locationRepository.deleteById(id);
    }
}
