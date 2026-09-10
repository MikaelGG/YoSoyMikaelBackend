package com.yosoymikael.controller;

import com.yosoymikael.model.LiveClass;
import com.yosoymikael.service.LiveClassService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/live-classes")
@RequiredArgsConstructor
public class LiveClassController {

    private final LiveClassService liveClassService;

    @GetMapping
    public ResponseEntity<List<LiveClass>> getAll() {
        return ResponseEntity.ok(liveClassService.getAllLiveClasses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LiveClass> getById(@PathVariable Long id) {
        return ResponseEntity.ok(liveClassService.getLiveClassById(id));
    }

    @PostMapping
    public ResponseEntity<LiveClass> create(@Valid @RequestBody LiveClass liveClass) {
        return ResponseEntity.status(HttpStatus.CREATED).body(liveClassService.createLiveClass(liveClass));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LiveClass> update(@PathVariable Long id, @Valid @RequestBody LiveClass liveClass) {
        return ResponseEntity.ok(liveClassService.updateLiveClass(id, liveClass));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        liveClassService.deleteLiveClass(id);
        return ResponseEntity.noContent().build();
    }
}
