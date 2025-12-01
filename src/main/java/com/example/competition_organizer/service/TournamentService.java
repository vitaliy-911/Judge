package com.example.competition_organizer.service;

import com.example.competition_organizer.dto.FighterResponseDto.Fighter;
import com.example.competition_organizer.model.HitLocation;
import com.example.competition_organizer.model.Pair;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Slf4j
@Service
public class TournamentService {

    private final PairService pairService;
    private final LocationService locationService;
    private final Random random = new Random();

    @Autowired
    public TournamentService(PairService pairService, LocationService locationService) {
        this.pairService = pairService;
        this.locationService = locationService;
    }

    public Fighter organizerCompetition() {
        List<Fighter> allParticipantAndRunTournament = pairService.getAllParticipantAndRunTournament();
        Collections.shuffle(allParticipantAndRunTournament);
        return createFight(allParticipantAndRunTournament);
    }


    public Fighter createFight(List<Fighter> fighterList) {
        List<Fighter> fighters = new ArrayList<>(fighterList);
        List<Fighter> nextRound = new ArrayList<>();

        for (int i = 0; i < fighters.size(); i += 2) {
            if (fighters.size() > 1) {
                Fighter fighterFirst = fighters.get(i);
                Fighter fighterSecond = (i + 1 < fighters.size()) ? fighters.get(i + 1) : null;
                if (fighterSecond == null) {
                    // если нечётное число участников, 1 автоматически проходит
                    nextRound.add(fighterFirst);
                } else {
                    Fighter winner = fight(fighterFirst, fighterSecond);
                    savePairInBaz(fighterFirst, fighterSecond, winner);
                    nextRound.add(winner);
                }
            }
        }
        if (nextRound.size() == 1) {
            // Это последний победитель
            return nextRound.getFirst();
        } else {
            fighters = nextRound;
            return createFight(fighters);
        }
    }

    public void savePairInBaz(Fighter fighter, Fighter
            fighter1, Fighter winner) {
        Pair pair = Pair.builder()
                .participantIdFirst(fighter.getId())
                .participantIdSecond(fighter1.getId())
                .winnerId(winner.getId())
                .build();
        pairService.savePair(pair);
    }


    public Fighter fight(Fighter first, Fighter second) {
        Fighter winner;

        while (true) {
            int powerFirst = random.nextInt(first.getPower() + 10);
            int powerSecond = random.nextInt(second.getPower() + 10);
            double dodgeChanceFirst = random.nextDouble(first.getDodgeChance() + 1);
            double dodgeChanceSecond = random.nextDouble(second.getDodgeChance() + 1);

            if (dodgeChanceFirst < first.getDodgeChance()) {
                int i = first.getHeilsFighters() - powerSecond;
                first.setHeilsFighters(i);
                log.info("{} пропустил урон {} осталоссь HP {}", first.getName(), powerSecond, i);
                if (first.getHeilsFighters() <= 0) {
                    winner = second;
                    break;
                }
            } else {
                log.info("{} промахнулся ", second.getName());
            }

            if (dodgeChanceSecond < second.getDodgeChance()) {
                int i = second.getHeilsFighters() - powerFirst;
                second.setHeilsFighters(i);
                log.info("{} пропустил урон {} осталоссь HP {}", second.getName(), powerFirst, i);
                if (second.getHeilsFighters() <= 0) {
                    winner = first;
                    break;
                }
            } else {
                log.info("{} промахнулся ", first.getName());
            }
        }
        return winner;
    }


    private boolean isAlive(Fighter fighter) {
        return fighter.getHeilsFighters() > 0;
    }

    private int hitDamage(Fighter fighter, HitLocation hitLocation) {
        int fighterPower = fighter.getPower();
        double damageMultiplier = hitLocation.getDamageMultiplier();
        return (int) (fighterPower * damageMultiplier);
    }

    private boolean dodgeChance(Fighter fighter) {
        double randomDodgeChance = random.nextDouble(0, 2.0);
        return randomDodgeChance < fighter.getDodgeChance();
    }

    private HitLocation getRandomHitLocation() {
        HitLocation[] locations = HitLocation.values();
        return locations[random.nextInt(locations.length)];
    }

    public Fighter createFight2(Fighter first, Fighter second) {
        Fighter winner = null;
        while (isAlive(first) && isAlive(second)) {
            HitLocation randomHitLocation = getRandomHitLocation();
            fight2(first, second, randomHitLocation);
            fight2(second, first, randomHitLocation);
            if (!isAlive(first)) {
                winner = second;
            } else if (!isAlive(second)) {
                winner = first;
            }
        }
        return winner;
    }


    public void fight2(Fighter first, Fighter second, HitLocation hitLocation) {
        if (isAlive(first)) {
            if (dodgeChance(first)) {
                int damage = hitDamage(second, hitLocation);
                int heils = first.getHeilsFighters() - damage;
                first.setHeilsFighters(heils);
            } else if (!isAlive(first)) {
                log.info("{} Бить не может мертв", first.getName());
            }
        } else {
            log.info("{} ударив в {}промахнулся", second.getName(), hitLocation.getDisplayName());
        }
    }
}



