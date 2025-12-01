package com.example.competition_organizer.service;


import com.example.competition_organizer.dto.FighterResponseDto;
import com.example.competition_organizer.dto.FighterResponseDto.Fighter;
import com.example.competition_organizer.model.Pair;
import com.example.competition_organizer.repozitory.PairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class PairService {

    @Value(value = "${fightClub.api.base-url}")
    private String REMOTE_FIGHTER_API_URL;

    private final RestTemplate restTemplate;
    private final PairRepository pairRepository;

    @Autowired
    public PairService(RestTemplate restTemplate, PairRepository pairRepository) {
        this.restTemplate = restTemplate;
        this.pairRepository = pairRepository;
    }

    public List<Fighter> getAllParticipantAndRunTournament() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>("param", headers);
        try {
            ResponseEntity<FighterResponseDto> exchange = restTemplate.exchange(
                    REMOTE_FIGHTER_API_URL + "/api/fighter/getAll", HttpMethod.GET, entity,
                    FighterResponseDto.class);

            FighterResponseDto body = exchange.getBody();

            return body.getFighters();

        } catch (Exception e) {
            throw new RuntimeException("Ошибка при получении учасников с сервера");
        }
    }

    public List<Fighter> createPair(Long firstId, Long secondId) {
        List<Fighter> fighters = new ArrayList<>(2);

        List<Fighter> fighterList = getAllParticipantAndRunTournament();

        Fighter first = fighterList.stream()
                .filter(fighter -> fighter.getId().equals(firstId))
                .findFirst().orElse(null);

        Fighter second = fighterList.stream()
                .filter(fighter -> fighter.getId().equals(secondId))
                .findFirst().orElse(null);
        fighters.add(first);
        fighters.add(second);
        return fighters;
    }

    public void savePair(Pair pair) {
        pairRepository.save(pair);
    }

    public List<Pair> getAll() {
        return pairRepository.findAll();
    }

}
