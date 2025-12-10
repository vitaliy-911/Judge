package com.example.competition_organizer.dto;

import com.example.competition_organizer.dto.FighterResponseDto.Fighter;
import com.example.competition_organizer.model.FightState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FightDto {
    private Fighter fighter1;
    private Fighter fighter2;
    private Fighter currentAttacker;
    private List<String> fightLog;
    private boolean fightOver;
    private Fighter winner;
    private Long fightId;
    private int fighter1InitialHp;
    private int fighter2InitialHp;

    public static FightDto fromFightState(FightState fightState) {
        return new FightDto(
                fightState.getFighter1(),
                fightState.getFighter2(),
                fightState.getCurrentAttacker(),
                fightState.getFightLog(),
                fightState.isFightOver(),
                fightState.getWinner(),
                fightState.getFightId(),
                fightState.getFighter1InitialHp(),
                fightState.getFighter2InitialHp()
        );
    }
}

