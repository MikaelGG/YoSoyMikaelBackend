package com.yosoymikael.controller;

import com.yosoymikael.model.JourneyReservation;
import com.yosoymikael.service.JourneyReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/journey-reservations")
@RequiredArgsConstructor
public class JourneyReservationController {

    private final JourneyReservationService reservationService;

    @GetMapping
    public ResponseEntity<List<JourneyReservation>> getAll() {
        return ResponseEntity.ok(reservationService.getAllReservations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<JourneyReservation> getById(@PathVariable Long id) {
        return ResponseEntity.ok(reservationService.getReservationById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<JourneyReservation> update(@PathVariable Long id, @Valid @RequestBody JourneyReservation reservation) {
        return ResponseEntity.ok(reservationService.updateReservation(id, reservation));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        reservationService.deleteReservation(id);
        return ResponseEntity.noContent().build();
    }
}
