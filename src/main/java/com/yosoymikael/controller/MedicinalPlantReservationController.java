package com.yosoymikael.controller;

import com.yosoymikael.model.MedicinalPlantReservation;
import com.yosoymikael.service.MedicinalPlantReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/medicinal-plant-reservations")
@RequiredArgsConstructor
public class MedicinalPlantReservationController {

    private final MedicinalPlantReservationService reservationService;

    @GetMapping
    public ResponseEntity<List<MedicinalPlantReservation>> getAll() {
        return ResponseEntity.ok(reservationService.getAllReservations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicinalPlantReservation> getById(@PathVariable Long id) {
        return ResponseEntity.ok(reservationService.getReservationById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicinalPlantReservation> update(@PathVariable Long id, @Valid @RequestBody MedicinalPlantReservation reservation) {
        return ResponseEntity.ok(reservationService.updateReservation(id, reservation));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        reservationService.deleteReservation(id);
        return ResponseEntity.noContent().build();
    }
}
