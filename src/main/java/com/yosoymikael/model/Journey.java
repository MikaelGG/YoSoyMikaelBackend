package com.yosoymikael.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "journeys")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Journey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "journey_id")
    private Long journeyId;

    @Column(name = "journey_name", nullable = false)
    private String journeyName;

    @Column(name = "journey_image")
    private String journeyImage;

    @Column(name = "journey_description", columnDefinition = "TEXT")
    private String journeyDescription;

    @Column(name = "journey_date")
    private LocalDate journeyDate;

    @Column(name = "journey_price", precision = 10, scale = 2)
    private BigDecimal journeyPrice;

    @OneToMany(mappedBy = "journey", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    @Builder.Default
    private List<JourneyReservation> reservations = new ArrayList<>();

    public void addReservation(JourneyReservation reservation) {
        reservations.add(reservation);
        reservation.setJourney(this);
    }

    public void removeReservation(JourneyReservation reservation) {
        reservations.remove(reservation);
        reservation.setJourney(null);
    }
}
