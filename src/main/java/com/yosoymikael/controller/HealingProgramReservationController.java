package com.yosoymikael.controller;

import com.yosoymikael.model.HealingProgramReservation;
import com.yosoymikael.service.HealingProgramReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/healing-program-reservations")
@RequiredArgsConstructor
public class HealingProgramReservationController {

    private final HealingProgramReservationService reservationService;

    @GetMapping
    public ResponseEntity<List<HealingProgramReservation>> getAll() {
        return ResponseEntity.ok(reservationService.getAllReservations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HealingProgramReservation> getById(@PathVariable Long id) {
        return ResponseEntity.ok(reservationService.getReservationById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HealingProgramReservation> update(@PathVariable Long id, @Valid @RequestBody HealingProgramReservation reservation) {
        return ResponseEntity.ok(reservationService.updateReservation(id, reservation));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        reservationService.deleteReservation(id);
        return ResponseEntity.noContent().build();
    }
}
