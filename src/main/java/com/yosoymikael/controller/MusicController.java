package com.yosoymikael.controller;

import com.yosoymikael.model.Music;
import com.yosoymikael.service.MusicService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/music")
@RequiredArgsConstructor
public class MusicController {

    private final MusicService musicService;

    @GetMapping
    public ResponseEntity<List<Music>> getAll() {
        return ResponseEntity.ok(musicService.getAllMusic());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Music> getById(@PathVariable Long id) {
        return ResponseEntity.ok(musicService.getMusicById(id));
    }

    @PostMapping
    public ResponseEntity<Music> create(@Valid @RequestBody Music music) {
        return ResponseEntity.status(HttpStatus.CREATED).body(musicService.createMusic(music));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Music> update(@PathVariable Long id, @Valid @RequestBody Music music) {
        return ResponseEntity.ok(musicService.updateMusic(id, music));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        musicService.deleteMusic(id);
        return ResponseEntity.noContent().build();
    }
}
