<<<<<<<< HEAD:src/main/java/com/example/competition_organizer/model/HitLocation.java
package com.example.competition_organizer.model;
========
package competitionOrganizer.model;
>>>>>>>> upstream/main:src/main/java/competitionOrganizer/model/HitLocation.java

import lombok.Getter;

@Getter
public enum HitLocation {
    HEAD("Голову",1.5),
    STOMACH("Туловище",1.0),
    LEGS("Ноги",0.7);

    private final String displayName;
    private final double damageMultiplier;

    HitLocation(String displayName, double damageMultiplier) {
        this.displayName = displayName;
        this.damageMultiplier = damageMultiplier;
    }
}
