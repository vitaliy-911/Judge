package com.example.competition_organizer.repozitory;

import com.example.competition_organizer.model.Pair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface PairRepository extends JpaRepository<Pair,Long> {
}
