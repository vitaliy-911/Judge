<<<<<<<< HEAD:src/main/java/com/example/competition_organizer/controler/TournamentController.java
package com.example.competition_organizer.controler;

import com.example.competition_organizer.dto.FighterResponseDto.Fighter;
import com.example.competition_organizer.dto.PairDto;
import com.example.competition_organizer.service.PairService;
import com.example.competition_organizer.service.TournamentService;
========
package competitionOrganizer.controler;

import competitionOrganizer.dto.FighterResponseDto;
import competitionOrganizer.dto.PairDto;
import competitionOrganizer.model.HitLocation;
import competitionOrganizer.service.PairService;
import competitionOrganizer.service.TournamentService;
import lombok.extern.slf4j.Slf4j;
>>>>>>>> upstream/main:src/main/java/competitionOrganizer/controler/TournamentController.java
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/competition")
public class TournamentController {

    private final TournamentService competitionService;
    private final PairService pairService;
    private final TournamentService tournamentService;

<<<<<<<< HEAD:src/main/java/com/example/competition_organizer/controler/TournamentController.java

    private List<Fighter> fighterList = new ArrayList<>();
========
    private List<FighterResponseDto.Fighter> fighterList = new ArrayList<>();
>>>>>>>> upstream/main:src/main/java/competitionOrganizer/controler/TournamentController.java

    public TournamentController(TournamentService competitionService, PairService pairService, TournamentService tournamentService) {
        this.competitionService = competitionService;
        this.pairService = pairService;
        this.tournamentService = tournamentService;
    }

    @PostMapping("/startModem")
    public ResponseEntity<String> startCompetition() {
        try {
            Fighter fighter = competitionService.organizerCompetition();
            return ResponseEntity.ok("Соревнования успешно проведены победитель id " + fighter.getId() + " Имя " + fighter.getName() + " пояс " + fighter.getBeltColorFighter());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ошибка при проведении соревнований: " + e.getMessage());
        }
    }

    @GetMapping("/getAllFighters")
    public List<FighterResponseDto.Fighter> getFighterList() {
        return pairService.getAllParticipantAndRunTournament();
    }


    @PostMapping("/select-fighters")
    public List<Fighter> selectFighters(@RequestBody PairDto pair) {
        List<Fighter> fighters = pairService.createPair(pair.getFirstId(), pair.getSecondId());
        fighterList = fighters;
        return fighters;
    }

    @PostMapping("/attack")
    @ResponseBody
<<<<<<<< HEAD:src/main/java/com/example/competition_organizer/controler/TournamentController.java
    public Fighter attack() {
        Fighter fighter1 = fighterList.get(0);
        Fighter fighter2 = fighterList.get(1);
        return tournamentService.createFight2(fighter1, fighter2);
========
    public FighterResponseDto.Fighter attack(HitLocation hitLocation) {
        FighterResponseDto.Fighter fighter1 = fighterList.get(0);
        FighterResponseDto.Fighter fighter2 = fighterList.get(1);

        FighterResponseDto.Fighter victim = tournamentService.createFight2(fighter1, fighter2, hitLocation);
>>>>>>>> upstream/main:src/main/java/competitionOrganizer/controler/TournamentController.java

        if (victim.getHeilsFighters() > 0) {
            fighterList.set(0, victim);
            fighterList.set(1, fighter1);
        } else if (victim.getHeilsFighters() < 0) {
            System.out.println("Победил " + fighter1.getName());
            System.out.println("Выберите новых игроков");
        }
        return fighter1;
    }
}