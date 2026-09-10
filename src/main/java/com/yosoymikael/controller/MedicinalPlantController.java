package com.yosoymikael.controller;

import com.yosoymikael.model.MedicinalPlant;
import com.yosoymikael.model.MedicinalPlantReservation;
import com.yosoymikael.service.MedicinalPlantReservationService;
import com.yosoymikael.service.MedicinalPlantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/medicinal-plants")
@RequiredArgsConstructor
public class MedicinalPlantController {

    private final MedicinalPlantService plantService;
    private final MedicinalPlantReservationService reservationService;

    @GetMapping
    public ResponseEntity<List<MedicinalPlant>> getAll() {
        return ResponseEntity.ok(plantService.getAllPlants());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicinalPlant> getById(@PathVariable Long id) {
        return ResponseEntity.ok(plantService.getPlantById(id));
    }

    @PostMapping
    public ResponseEntity<MedicinalPlant> create(@Valid @RequestBody MedicinalPlant plant) {
        return ResponseEntity.status(HttpStatus.CREATED).body(plantService.createPlant(plant));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicinalPlant> update(@PathVariable Long id, @Valid @RequestBody MedicinalPlant plant) {
        return ResponseEntity.ok(plantService.updatePlant(id, plant));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        plantService.deletePlant(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/reservations")
    public ResponseEntity<List<MedicinalPlantReservation>> getReservations(@PathVariable Long id) {
        return ResponseEntity.ok(reservationService.getReservationsByPlantId(id));
    }

    @PostMapping("/{id}/reservations")
    public ResponseEntity<MedicinalPlantReservation> createReservation(@PathVariable Long id, @Valid @RequestBody MedicinalPlantReservation reservation) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationService.createReservation(id, reservation));
    }
}
