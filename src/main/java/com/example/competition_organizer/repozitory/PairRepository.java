<<<<<<<< HEAD:src/main/java/com/example/competition_organizer/repozitory/PairRepository.java
package com.example.competition_organizer.repozitory;

import com.example.competition_organizer.model.Pair;
========
package competitionOrganizer.repozitory;

import competitionOrganizer.model.Pair;
>>>>>>>> upstream/main:src/main/java/competitionOrganizer/repozitory/PairRepository.java
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface PairRepository extends JpaRepository<Pair,Long> {
}
