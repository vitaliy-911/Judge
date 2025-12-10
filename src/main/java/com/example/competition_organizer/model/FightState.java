package com.example.competition_organizer.model;

import com.example.competition_organizer.dto.FighterResponseDto.Fighter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FightState {
    private Fighter fighter1;
    private Fighter fighter2;
    private Fighter currentAttacker;
    private List<String> fightLog;
    private boolean fightOver;
    private Fighter winner;
    private Long fightId;
    private int fighter1InitialHp;
    private int fighter2InitialHp;

    public void addLogEntry(String entry) {
        if (fightLog == null) {
            fightLog = new ArrayList<>();
        }
        fightLog.add(entry);
    }

    public void switchAttacker() {
        if (currentAttacker == null || currentAttacker.getId().equals(fighter1.getId())) {
            currentAttacker = fighter2;
        } else {
            currentAttacker = fighter1;
        }
    }

    public Fighter getDefender() {
        if (currentAttacker == null) {
            return fighter2;
        }
        return currentAttacker.getId().equals(fighter1.getId()) ? fighter2 : fighter1;
    }
}

