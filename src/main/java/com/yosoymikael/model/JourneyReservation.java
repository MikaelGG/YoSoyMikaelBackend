package com.yosoymikael.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "journey_reservations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JourneyReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "journey_reservation_id")
    private Long journeyReservationId;

    @Column(name = "travelers_full_name", nullable = false)
    private String travelersFullName;

    @Column(name = "travelers_phone")
    private String travelersPhone;

    @Column(name = "travelers_mail")
    private String travelersMail;

    @Column(name = "travelers_address")
    private String travelersAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "journey_id", nullable = false)
    @JsonBackReference
    private Journey journey;
}
