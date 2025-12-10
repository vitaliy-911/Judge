<<<<<<<< HEAD:src/main/java/com/example/competition_organizer/repozitory/LocationRepository.java
package com.example.competition_organizer.repozitory;

import com.example.competition_organizer.model.Location;
========
package competitionOrganizer.repozitory;

import competitionOrganizer.model.Location;
>>>>>>>> upstream/main:src/main/java/competitionOrganizer/repozitory/LocationRepository.java
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
}
