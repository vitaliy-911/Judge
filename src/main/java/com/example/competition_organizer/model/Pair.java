<<<<<<<< HEAD:src/main/java/com/example/competition_organizer/model/Pair.java
package com.example.competition_organizer.model;
========
package competitionOrganizer.model;
>>>>>>>> upstream/main:src/main/java/competitionOrganizer/model/Pair.java

import jakarta.persistence.*;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "pair")
@Entity
public class Pair {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "participantIdFirst")
    private Long participantIdFirst;

    @Column(name = "participantIdSecond")
    private Long participantIdSecond;

    @Column(name = "winnerId")
    private Long winnerId;

}
