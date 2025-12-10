<<<<<<<< HEAD:src/main/java/com/example/competition_organizer/controler/PairController.java
package com.example.competition_organizer.controler;

import com.example.competition_organizer.model.Pair;
import com.example.competition_organizer.service.PairService;
========
package competitionOrganizer.controler;

import competitionOrganizer.model.Pair;
import competitionOrganizer.service.PairService;
>>>>>>>> upstream/main:src/main/java/competitionOrganizer/controler/PairController.java
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pairs")
public class PairController {

    private final PairService pairService;

    public PairController(PairService pairService) {
        this.pairService = pairService;
    }

    @GetMapping("getAll")
    public List<Pair> getAll(){
       return pairService.getAll();
    }
}
