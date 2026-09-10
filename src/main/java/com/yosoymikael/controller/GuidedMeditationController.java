package com.yosoymikael.controller;

import com.yosoymikael.model.GuidedMeditation;
import com.yosoymikael.service.GuidedMeditationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/guided-meditations")
@RequiredArgsConstructor
public class GuidedMeditationController {

    private final GuidedMeditationService guidedMeditationService;

    @GetMapping
    public ResponseEntity<List<GuidedMeditation>> getAll() {
        return ResponseEntity.ok(guidedMeditationService.getAllMeditations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GuidedMeditation> getById(@PathVariable Long id) {
        return ResponseEntity.ok(guidedMeditationService.getMeditationById(id));
    }

    @PostMapping
    public ResponseEntity<GuidedMeditation> create(@Valid @RequestBody GuidedMeditation meditation) {
        return ResponseEntity.status(HttpStatus.CREATED).body(guidedMeditationService.createMeditation(meditation));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GuidedMeditation> update(@PathVariable Long id, @Valid @RequestBody GuidedMeditation meditation) {
        return ResponseEntity.ok(guidedMeditationService.updateMeditation(id, meditation));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        guidedMeditationService.deleteMeditation(id);
        return ResponseEntity.noContent().build();
    }
}
