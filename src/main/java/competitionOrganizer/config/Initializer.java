package competitionOrganizer.config;

import competitionOrganizer.model.Location;
import competitionOrganizer.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Initializer implements CommandLineRunner {

    private final LocationService locationService;

    @Autowired
    public Initializer(LocationService locationService) {
        this.locationService = locationService;
    }

    @Override
    public void run(String... args) throws Exception {
        Location location = new Location();
        Location location1 = new Location();
        Location location2 = new Location();
        Location location3 = new Location();
        Location location4 = new Location();
        location.setName("Витебск");
        location1.setName("Минск");
        location2.setName("Брест");
        location3.setName("Гомель");
        location4.setName("Могилев");
        locationService.createLocation(location);
        locationService.createLocation(location1);
        locationService.createLocation(location2);
        locationService.createLocation(location3);
        locationService.createLocation(location4);
    }
}
