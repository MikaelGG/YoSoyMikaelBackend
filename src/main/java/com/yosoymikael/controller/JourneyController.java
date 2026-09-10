package com.yosoymikael.controller;

import com.yosoymikael.model.Journey;
import com.yosoymikael.model.JourneyReservation;
import com.yosoymikael.service.JourneyReservationService;
import com.yosoymikael.service.JourneyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/journeys")
@RequiredArgsConstructor
public class JourneyController {

    private final JourneyService journeyService;
    private final JourneyReservationService reservationService;

    @GetMapping
    public ResponseEntity<List<Journey>> getAll() {
        return ResponseEntity.ok(journeyService.getAllJourneys());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Journey> getById(@PathVariable Long id) {
        return ResponseEntity.ok(journeyService.getJourneyById(id));
    }

    @PostMapping
    public ResponseEntity<Journey> create(@Valid @RequestBody Journey journey) {
        return ResponseEntity.status(HttpStatus.CREATED).body(journeyService.createJourney(journey));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Journey> update(@PathVariable Long id, @Valid @RequestBody Journey journey) {
        return ResponseEntity.ok(journeyService.updateJourney(id, journey));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        journeyService.deleteJourney(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/reservations")
    public ResponseEntity<List<JourneyReservation>> getReservations(@PathVariable Long id) {
        return ResponseEntity.ok(reservationService.getReservationsByJourneyId(id));
    }

    @PostMapping("/{id}/reservations")
    public ResponseEntity<JourneyReservation> createReservation(@PathVariable Long id, @Valid @RequestBody JourneyReservation reservation) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationService.createReservation(id, reservation));
    }
}
