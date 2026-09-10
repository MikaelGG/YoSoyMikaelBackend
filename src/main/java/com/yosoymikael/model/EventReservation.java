package com.yosoymikael.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "event_reservations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_reservation_id")
    private Long eventReservationId;

    @Column(name = "reservator_full_name", nullable = false)
    private String reservatorFullName;

    @Column(name = "reservator_phone")
    private String reservatorPhone;

    @Column(name = "reservator_mail")
    private String reservatorMail;

    @Column(name = "reservator_address")
    private String reservatorAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    @JsonBackReference
    private Event event;
}
