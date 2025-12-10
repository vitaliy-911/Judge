package com.example.competition_organizer.controler;

import com.example.competition_organizer.dto.FighterResponseDto.Fighter;
import com.example.competition_organizer.dto.FightDto;
import com.example.competition_organizer.model.FightState;
import com.example.competition_organizer.model.HitLocation;
import com.example.competition_organizer.service.PairService;
import com.example.competition_organizer.service.TournamentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.UUID;

@Controller
@SessionAttributes("fightState")
public class FightWebController {

    private final PairService pairService;
    private final TournamentService tournamentService;

    public FightWebController(PairService pairService, TournamentService tournamentService) {
        this.pairService = pairService;
        this.tournamentService = tournamentService;
    }

    @GetMapping("/")
    public String index(Model model) {
        try {
            List<Fighter> fighters = pairService.getAllParticipantAndRunTournament();
            model.addAttribute("fighters", fighters);
            return "index";
        } catch (Exception e) {
            model.addAttribute("error", "Ошибка при загрузке бойцов: " + e.getMessage());
            return "index";
        }
    }

    @PostMapping("/fight/start")
    public String startFight(@RequestParam Long fighter1Id, 
                            @RequestParam Long fighter2Id,
                            Model model,
                            RedirectAttributes redirectAttributes) {
        try {
            if (fighter1Id.equals(fighter2Id)) {
                redirectAttributes.addFlashAttribute("error", "Нельзя выбрать одного и того же бойца дважды");
                return "redirect:/";
            }

            List<Fighter> allFighters = pairService.getAllParticipantAndRunTournament();
            Fighter fighter1 = allFighters.stream()
                    .filter(f -> f.getId().equals(fighter1Id))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Боец 1 не найден"));
            
            Fighter fighter2 = allFighters.stream()
                    .filter(f -> f.getId().equals(fighter2Id))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Боец 2 не найден"));

            // Создаем копии бойцов для боя (чтобы не изменять оригиналы)
            Fighter fighter1Copy = createFighterCopy(fighter1);
            Fighter fighter2Copy = createFighterCopy(fighter2);

            FightState fightState = FightState.builder()
                    .fighter1(fighter1Copy)
                    .fighter2(fighter2Copy)
                    .currentAttacker(fighter1Copy)
                    .fightLog(new java.util.ArrayList<>())
                    .fightOver(false)
                    .fightId(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE)
                    .fighter1InitialHp(fighter1Copy.getHeilsFighters())
                    .fighter2InitialHp(fighter2Copy.getHeilsFighters())
                    .build();

            fightState.addLogEntry("Бой начался! " + fighter1Copy.getName() + " против " + fighter2Copy.getName());
            fightState.addLogEntry(fighter1Copy.getName() + " начинает бой");

            model.addAttribute("fightState", fightState);
            return "redirect:/fight";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Ошибка при создании боя: " + e.getMessage());
            return "redirect:/";
        }
    }

    @GetMapping("/fight")
    public String fightPage(@ModelAttribute("fightState") FightState fightState, Model model) {
        if (fightState == null || fightState.getFighter1() == null) {
            return "redirect:/";
        }

        FightDto fightDto = FightDto.fromFightState(fightState);
        model.addAttribute("fight", fightDto);
        model.addAttribute("hitLocations", HitLocation.values());
        
        return "fight";
    }

    @PostMapping("/fight/hit")
    public String performHit(@ModelAttribute("fightState") FightState fightState,
                             @RequestParam String hitLocation,
                             RedirectAttributes redirectAttributes) {
        if (fightState == null || fightState.isFightOver()) {
            redirectAttributes.addFlashAttribute("error", "Бой уже окончен");
            return "redirect:/fight";
        }

        try {
            HitLocation location = HitLocation.valueOf(hitLocation);
            tournamentService.performHit(fightState, location);

            // Переключаем атакующего, если бой не окончен
            if (!tournamentService.isFightOver(fightState)) {
                fightState.switchAttacker();
            }

            return "redirect:/fight";
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", "Неверное место удара");
            return "redirect:/fight";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Ошибка при выполнении удара: " + e.getMessage());
            return "redirect:/fight";
        }
    }

    private Fighter createFighterCopy(Fighter original) {
        Fighter copy = new Fighter();
        copy.setId(original.getId());
        copy.setName(original.getName());
        copy.setAge(original.getAge());
        copy.setPower(original.getPower());
        copy.setBeltColorFighter(original.getBeltColorFighter());
        copy.setDodgeChance(original.getDodgeChance());
        copy.setHeilsFighters(original.getHeilsFighters());
        return copy;
    }
}

