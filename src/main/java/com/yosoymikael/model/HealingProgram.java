package com.yosoymikael.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "healing_programs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HealingProgram {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "healing_program_id")
    private Long healingProgramId;

    @Column(name = "healing_image")
    private String healingImage;

    @Column(name = "healing_description", columnDefinition = "TEXT")
    private String healingDescription;

    @OneToMany(mappedBy = "healingProgram", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    @Builder.Default
    private List<HealingProgramReservation> reservations = new ArrayList<>();

    public void addReservation(HealingProgramReservation reservation) {
        reservations.add(reservation);
        reservation.setHealingProgram(this);
    }

    public void removeReservation(HealingProgramReservation reservation) {
        reservations.remove(reservation);
        reservation.setHealingProgram(null);
    }
}
