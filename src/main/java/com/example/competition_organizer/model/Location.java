<<<<<<<< HEAD:src/main/java/com/example/competition_organizer/model/Location.java
package com.example.competition_organizer.model;
========
package competitionOrganizer.model;
>>>>>>>> upstream/main:src/main/java/competitionOrganizer/model/Location.java

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
@Setter
@Entity
@Table(name = "location")
@AllArgsConstructor
@NoArgsConstructor

public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
<<<<<<<< HEAD:src/main/java/com/example/competition_organizer/model/Location.java
========

>>>>>>>> upstream/main:src/main/java/competitionOrganizer/model/Location.java
}
