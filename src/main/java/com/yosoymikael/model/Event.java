package com.yosoymikael.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "events")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Long eventId;

    @Column(name = "event_image")
    private String eventImage;

    @Column(name = "event_description", columnDefinition = "TEXT")
    private String eventDescription;

    @Column(name = "event_date")
    private LocalDate eventDate;

    @Column(name = "event_price", precision = 10, scale = 2)
    private BigDecimal eventPrice;

    @Column(name = "event_details", columnDefinition = "TEXT")
    private String eventDetails;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    @Builder.Default
    private List<EventReservation> reservations = new ArrayList<>();

    public void addReservation(EventReservation reservation) {
        reservations.add(reservation);
        reservation.setEvent(this);
    }

    public void removeReservation(EventReservation reservation) {
        reservations.remove(reservation);
        reservation.setEvent(null);
    }
}
