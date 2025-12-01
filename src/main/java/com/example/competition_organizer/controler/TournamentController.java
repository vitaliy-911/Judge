package com.example.competition_organizer.controler;

import com.example.competition_organizer.dto.FighterResponseDto.Fighter;
import com.example.competition_organizer.dto.PairDto;
import com.example.competition_organizer.service.PairService;
import com.example.competition_organizer.service.TournamentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/competition")
public class TournamentController {

    private final TournamentService competitionService;
    private final PairService pairService;
    private final TournamentService tournamentService;


    private List<Fighter> fighterList = new ArrayList<>();

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

    @PostMapping("/select-fighters")
    public List<Fighter> selectFighters(@RequestBody PairDto pair) {
        List<Fighter> fighters = pairService.createPair(pair.getFirstId(), pair.getSecondId());
        fighterList = fighters;
        return fighters;
    }

    @PostMapping("/attack")
    @ResponseBody
    public Fighter attack() {
        Fighter fighter1 = fighterList.get(0);
        Fighter fighter2 = fighterList.get(1);
        return tournamentService.createFight2(fighter1, fighter2);

    }
}