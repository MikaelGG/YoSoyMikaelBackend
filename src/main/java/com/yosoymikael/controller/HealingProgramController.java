package com.yosoymikael.controller;

import com.yosoymikael.model.HealingProgram;
import com.yosoymikael.model.HealingProgramReservation;
import com.yosoymikael.service.HealingProgramReservationService;
import com.yosoymikael.service.HealingProgramService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/healing-programs")
@RequiredArgsConstructor
public class HealingProgramController {

    private final HealingProgramService programService;
    private final HealingProgramReservationService reservationService;

    @GetMapping
    public ResponseEntity<List<HealingProgram>> getAll() {
        return ResponseEntity.ok(programService.getAllPrograms());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HealingProgram> getById(@PathVariable Long id) {
        return ResponseEntity.ok(programService.getProgramById(id));
    }

    @PostMapping
    public ResponseEntity<HealingProgram> create(@Valid @RequestBody HealingProgram program) {
        return ResponseEntity.status(HttpStatus.CREATED).body(programService.createProgram(program));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HealingProgram> update(@PathVariable Long id, @Valid @RequestBody HealingProgram program) {
        return ResponseEntity.ok(programService.updateProgram(id, program));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        programService.deleteProgram(id);
        return ResponseEntity.noContent().build();
    }

    // Reservas anidadas del programa
    @GetMapping("/{id}/reservations")
    public ResponseEntity<List<HealingProgramReservation>> getReservations(@PathVariable Long id) {
        return ResponseEntity.ok(reservationService.getReservationsByProgramId(id));
    }

    @PostMapping("/{id}/reservations")
    public ResponseEntity<HealingProgramReservation> createReservation(@PathVariable Long id, @Valid @RequestBody HealingProgramReservation reservation) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationService.createReservation(id, reservation));
    }
}
