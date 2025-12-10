<<<<<<<< HEAD:src/main/java/com/example/competition_organizer/controler/LocationController.java
package com.example.competition_organizer.controler;

import com.example.competition_organizer.model.Location;
import com.example.competition_organizer.service.LocationService;
========
package competitionOrganizer.controler;

import competitionOrganizer.model.Location;
import competitionOrganizer.service.LocationService;
>>>>>>>> upstream/main:src/main/java/competitionOrganizer/controler/LocationController.java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/location")
public class LocationController {

    private final LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("/getAll")
    public List<Location> getAllLocations() {
        return locationService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Location> getLocationById(@PathVariable Long id) {
        return locationService.getLocationById(id);
    }

    @PostMapping
    public Location createLocation(@RequestBody Location location) {
        return locationService.createLocation(location);
    }

    @DeleteMapping("/{id}")
    public void deleteLocationById(@PathVariable Long id) {
        locationService.deleteLocationById(id);
    }

}
